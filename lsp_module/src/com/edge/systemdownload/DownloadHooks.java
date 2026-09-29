package com.edge.systemdownload;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedInterface.Chain;
import io.github.libxposed.api.XposedInterface.ExceptionMode;
import io.github.libxposed.api.XposedInterface.Hooker;

/**
 * Hooks Edge for Android (com.microsoft.emmx) and replaces its download flow.
 *
 * Required behaviour: a dialog appears first; only when the user taps 下载 is a download
 * created - and that download is the Android system DownloadManager's, never Edge's own.
 * Edge's download is therefore cancelled the moment its item is created (before the body
 * transfer starts, which happens after target determination), and Edge's dialog callback
 * is never answered with "accepted" (that is what would start Edge's download).
 *
 * Interception points:
 *
 *  1. {@code DownloadManagerService.onDownloadItemCreated(DownloadItem)} - first Java
 *     callback of the native engine. Provides the real URL and is where Edge's item is
 *     cancelled. Our dialog is shown from here.
 *  2. Edge's confirmation dialog factory
 *     {@code (String fileName, long size, org.chromium.base.Callback)void} (Edge 153:
 *     obfuscated class {@code rge}) - located by method descriptor via DexIndex. It is
 *     suppressed; if a dialog arrives before its item is known, our dialog asks for the
 *     link and waits for the item instead of letting Edge download.
 */
final class DownloadHooks {

    static final String TARGET_PACKAGE = "com.microsoft.emmx";
    static final String TAG = "EdgeSysDL";

    /** How long a takeover (dialog shown / decision pending) stays valid. */
    private static final long TAKEOVER_WINDOW_MS = 60_000L;
    /** An item created within this window belongs to a confirmation dialog that follows. */
    private static final long RECENT_ITEM_WINDOW_MS = 120_000L;
    /** How long to wait for the download item after a dialog without a known URL. */
    private static final long WAIT_ITEM_WINDOW_MS = 15_000L;
    /** Same download is not reported twice within this window. */
    private static final long DEDUP_WINDOW_MS = 8_000L;
    /** Delayed so the descriptor scan never competes with Edge's own startup. */
    private static final long CONFIRM_SCAN_DELAY_MS = 5_000L;
    /** Bounded retries while Edge's chrome split is still being loaded. */
    private static final int INSTALL_RETRY_MAX = 6;
    /** Shows short toasts so behaviour can be verified without reading logs. */
    private static final boolean DIAGNOSTIC_TOAST = true;

    static volatile String processName;

    private static XposedInterface sApi;
    private static ClassLoader sTargetClassLoader;
    private static volatile boolean sActivityHooksReady;
    private static volatile boolean sDownloadHooksReady;
    private static volatile boolean sInstallLogged;
    private static volatile boolean sConfirmResolveStarted;
    private static volatile boolean sLoadedToastShown;
    private static volatile int sInstallRetries;
    private static volatile int sDumpCount;

    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private static volatile Activity sCurrentActivity;
    private static volatile Application sApplication;

    /** Takeover of a download item; our dialog is on screen or a decision may follow. */
    private static volatile PendingDownload sPending;
    /** Dialog shown before any item was known (waiting for the URL). */
    private static volatile ConfirmRequest sConfirm;
    /** Action to apply as soon as a matching download item appears. */
    private static volatile int sWaitDecision;
    private static volatile String sWaitName;
    /** Item created moments before a confirmation dialog (usually the same download). */
    private static volatile PendingDownload sRecentItem;
    private static volatile long sRecentItemAt;
    private static volatile long sWaitUntilMs;
    private static volatile long sTakeoverUntilMs;

    private static final Map<String, Long> sRecentDownloads = new LinkedHashMap<String, Long>();
    private static final java.util.Set<String> sInstalledHooks = new java.util.HashSet<String>();

    private DownloadHooks() {}

    static synchronized void install(XposedInterface api, ClassLoader classLoader) {
        if (api != null) sApi = api;
        if (sApi == null) return;
        if (classLoader != null) sTargetClassLoader = classLoader;
        installActivityHooks();
        if (classLoader == null) return;
        if (isSecondaryProcess()) {
            if (!sInstallLogged) {
                sInstallLogged = true;
                log(4, "secondary process " + processName + ", download hooks not needed");
            }
            return;
        }
        installDownloadHooks(classLoader);
    }

    private static boolean isSecondaryProcess() {
        String name = processName;
        return name != null && name.indexOf(':') >= 0;
    }

    // ------------------------------------------------------------------ framework hooks

    private static void installActivityHooks() {
        if (sActivityHooksReady) return;
        try {
            // Only Activity.onResume is hooked: it is the least invasive way to know the
            // current activity and to get a context. The previously hooked
            // dispatchTouchEvent/onUserInteraction/onPause/Application.onCreate are gone -
            // they ran in Edge's hottest paths and are not needed any more (freshness is
            // decided by the download item state, see handleNewDownload).
            hookOnce("onResume", Activity.class.getDeclaredMethod("onResume"), new Hooker() {
                @Override
                public Object intercept(Chain chain) throws Throwable {
                    Object self = chain.getThisObject();
                    if (self instanceof Activity) {
                        sCurrentActivity = (Activity) self;
                        Application app = ((Activity) self).getApplication();
                        if (app != null) {
                            sApplication = app;
                            // First real context of the process: deliver reports and logs that
                            // were produced before the Application existed.
                            RemoteSettings.onContextAvailable();
                            showLoadedToastOnce();
                        }
                        // The chrome split may not be loaded when the module first installs;
                        // an activity resume is a reliable moment to retry.
                        if (!sDownloadHooksReady) {
                            install(null, ((Activity) self).getClassLoader());
                        }
                    }
                    return chain.proceed();
                }
            });
            sActivityHooksReady = true;
            log(4, "activity hooks installed");
        } catch (Throwable t) {
            // already-hooked entries are recorded in sInstalledHooks, so a retry never double-hooks
            log(5, "activity hook installation incomplete, will retry", t);
        }
    }

    private static void hookOnce(String key, Method method, Hooker hooker) throws Throwable {
        synchronized (sInstalledHooks) {
            if (sInstalledHooks.contains(key)) return;
        }
        hook(method, hooker);
        synchronized (sInstalledHooks) {
            sInstalledHooks.add(key);
        }
    }

    // ------------------------------------------------------------------ download hooks

    private static void installDownloadHooks(ClassLoader cl) {
        if (sDownloadHooksReady) return;
        try {
            // 1) Edge's download confirmation dialog (located by descriptor, not by name).
            Method confirm = reflectConfirmMethod(cl, "rge");
            if (confirm != null) {
                hookConfirm(confirm);
            } else {
                // Known obfuscated name missing: resolve by descriptor off the main thread
                // (the dex scan must never run on Edge's UI thread).
                log(4, "confirm dialog class not resolved by name, scanning dex in background");
                resolveConfirmDialogAsync(cl);
            }

            // 2) Download items: URL source and the place where Edge's download is cancelled.
            Class<?> serviceClass = Class.forName(
                    "org.chromium.chrome.browser.download.DownloadManagerService", false, cl);
            Class<?> itemClass = Class.forName(
                    "org.chromium.chrome.browser.download.DownloadItem", false, cl);
            Method onCreated = serviceClass.getDeclaredMethod("onDownloadItemCreated", itemClass);
            hook(onCreated, new Hooker() {
                @Override
                public Object intercept(Chain chain) throws Throwable {
                    try {
                        handleNewDownload(chain.getArg(0), chain.getThisObject());
                    } catch (Throwable t) {
                        log(5, "handleNewDownload failed", t);
                    }
                    return chain.proceed();
                }
            });

            // Suppress Edge's remaining download prompts while a download is owned by the module.
            suppressClassMethods(cl, "org.chromium.chrome.browser.download.DownloadDialogBridge",
                    new String[] {"showDialog"});
            suppressClassMethods(cl, "org.chromium.chrome.browser.download.EdgeOneDriveDownloadBridge",
                    new String[] {"queryUserChoice"});

            // Coverage/diagnostic hooks: a download that never reaches onDownloadItemCreated
            // still has to be observable (and actionable) through the other callbacks.
            installCallbackHooks(cl);

            sDownloadHooksReady = true;
            if (!sInstallLogged) {
                sInstallLogged = true;
                log(4, "download hooks installed in " + processName);
                RemoteSettings.reportState("inject", "hooks installed");
                RemoteSettings.startHeartbeat();
                showLoadedToastOnce();
            }
        } catch (Throwable t) {
            // The chrome split is usually not loaded yet on the first attempts; that is
            // expected and retried shortly (see scheduleInstallRetry).
            if (isClassLoadingIssue(t)) {
                log(4, "download hooks deferred (chrome split not loaded yet): " + t);
                toastAsync(Str.hookDeferred());
            } else {
                log(5, "download hook installation failed: " + t);
                RemoteSettings.reportState("hook_failed", String.valueOf(t));
                toastAsync(Str.hookFailed());
            }
            scheduleInstallRetry(cl);
        }
    }

    private static boolean isClassLoadingIssue(Throwable t) {
        return t instanceof ClassNotFoundException || t instanceof NoClassDefFoundError
                || t instanceof LinkageError || t instanceof UnsupportedClassVersionError;
    }

    /** Retries the installation a few times with backoff (the chrome split loads lazily). */
    private static void scheduleInstallRetry(final ClassLoader cl) {
        if (sInstallRetries >= INSTALL_RETRY_MAX) return;
        final int attempt = ++sInstallRetries;
        Bg.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sDownloadHooksReady) return;
                log(4, "retrying hook installation (attempt " + attempt + ")");
                install(null, cl);
            }
        }, 400L * attempt);
    }

    /**
     * Logs (and where possible acts on) all other download callbacks. Some Edge flows -
     * notably the confirmation-dialog flow - do not deliver onDownloadItemCreated, so the
     * "updated" callback doubles as an interception trigger.
     */
    private static void installCallbackHooks(ClassLoader cl) {
        try {
            Class<?> controller = Class.forName(
                    "org.chromium.chrome.browser.download.DownloadController", false, cl);

            // enqueueAndroidDownloadManagerRequest: Edge itself hands a download to the system
            // DownloadManager; log it because no download item is created in that case.
            for (Method m : controller.getDeclaredMethods()) {
                if (!m.getName().equals("enqueueAndroidDownloadManagerRequest")) continue;
                hookOnce("enqueueSystem", m, new Hooker() {
                    @Override
                    public Object intercept(Chain chain) throws Throwable {
                        log(4, "evt enqueueAndroidDownloadManagerRequest url="
                                + Reflect.urlString(chain.getArg(0))
                                + " name=" + Reflect.string(chain.getArg(1))
                                + " mime=" + Reflect.string(chain.getArg(3)));
                        return chain.proceed();
                    }
                });
            }

            Class<?> infoClass = Class.forName(
                    "org.chromium.chrome.browser.download.DownloadInfo", false, cl);
            for (Method m : controller.getDeclaredMethods()) {
                if (m.getParameterTypes().length > 0 && m.getParameterTypes()[0] == infoClass) {
                    final String name = m.getName();
                    hookOnce("controller." + name, m, new Hooker() {
                        @Override
                        public Object intercept(Chain chain) throws Throwable {
                            Object info = chain.getArg(0);
                            log(4, "evt " + name + ": " + Dump.info(info));
                            return chain.proceed();
                        }
                    });
                }
            }

            Class<?> serviceClass = Class.forName(
                    "org.chromium.chrome.browser.download.DownloadManagerService", false, cl);
            Class<?> itemClass = Class.forName(
                    "org.chromium.chrome.browser.download.DownloadItem", false, cl);
            hookOnce("onDownloadItemUpdated",
                    serviceClass.getDeclaredMethod("onDownloadItemUpdated", itemClass),
                    new Hooker() {
                        @Override
                        public Object intercept(Chain chain) throws Throwable {
                            Object item = chain.getArg(0);
                            try {
                                handleItemUpdated(item, chain.getThisObject());
                            } catch (Throwable t) {
                                log(5, "handleItemUpdated failed", t);
                            }
                            return chain.proceed();
                        }
                    });
            hookOnce("onDownloadItemRemoved",
                    serviceClass.getDeclaredMethod("onDownloadItemRemoved", String.class),
                    new Hooker() {
                        @Override
                        public Object intercept(Chain chain) throws Throwable {
                            log(4, "evt onDownloadItemRemoved id=" + Reflect.string(chain.getArg(0)));
                            return chain.proceed();
                        }
                    });
            log(4, "callback coverage hooks installed");
        } catch (Throwable t) {
            log(4, "callback coverage hooks failed", t);
        }
    }

    /**
     * A download item update with a usable URL also resolves a waiting confirmation dialog
     * (covers flows where no "created" callback is delivered).
     */
    private static void handleItemUpdated(Object item, Object service) {
        if (item == null || service == null) return;
        Object info = Reflect.get(item, "c");
        if (info == null) return;
        String url = Reflect.pickUrl(info);
        String fileName = Reflect.pickString(info, "e", "file");
        log(4, "evt onDownloadItemUpdated name=" + fileName + " url=" + url);
        if (!Reflect.isHttp(url)) return;
        if (sWaitDecision == 0) return;
        if (!(sWaitName == null || fileName.length() == 0 || namesMatch(sWaitName, fileName))) return;
        int decision = sWaitDecision;
        clearWaitState();
        PendingDownload download = buildPending(item, info, service, url, fileName,
                Reflect.pickString(info, "c", "mime"));
        if (download == null) return;
        log(4, "resolved waiting dialog from item update: " + url);
        cancelEdgeDownload(download);
        applyAction(download, decision);
    }

    /**
     * Installs the confirmation-dialog hook (idempotent). The dialog is replaced by ours;
     * Edge's callback is only ever answered with "declined", never with "accepted", so no
     * Edge download can be started from this dialog.
     */
    private static void hookConfirm(Method method) {
        if (isHookInstalled("confirmDialog")) return;
        try {
            hook(method, new Hooker() {
                @Override
                public Object intercept(Chain chain) throws Throwable {
                    try {
                        Object text = chain.getArg(0);
                        Long size = (Long) chain.getArg(1);
                        Object callback = chain.getArg(2);
                        if (takeOverConfirmDialog(text instanceof String ? (String) text : "",
                                size == null ? 0L : size.longValue(), callback)) {
                            return null; // Edge's dialog is replaced by ours
                        }
                    } catch (Throwable t) {
                        log(5, "confirm dialog interception failed", t);
                    }
                    return chain.proceed();
                }
            });
            markHookInstalled("confirmDialog");
            log(4, "confirm dialog hook installed: " + method.getDeclaringClass().getName()
                    + "." + method.getName());
        } catch (Throwable t) {
            log(5, "confirm dialog hook failed", t);
        }
    }

    private static void resolveConfirmDialogAsync(final ClassLoader cl) {
        if (sConfirmResolveStarted) return;
        sConfirmResolveStarted = true;
        // Delayed: Edge is still starting up (the scan reads the dex files) and the
        // name based lookup usually succeeds anyway.
        Bg.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isHookInstalled("confirmDialog")) {
                    log(4, "descriptor scan skipped (confirm dialog hook already installed)");
                    return;
                }
                Bg.run(new Runnable() {
                    @Override
                    public void run() {
                        scanForConfirmDialog(cl);
                    }
                });
            }
        }, CONFIRM_SCAN_DELAY_MS);
    }

    private static void scanForConfirmDialog(ClassLoader cl) {
        for (int attempt = 0; attempt < 5; attempt++) {
            if (isHookInstalled("confirmDialog")) return;
            Context context = application();
            if (context != null) {
                for (final String name : DexIndex.findDefaultPackageClasses(context,
                        DexIndex.CONFIRM_DIALOG_PROTO)) {
                    if (isHookInstalled("confirmDialog")) return;
                    final Method found = reflectConfirmMethod(cl, name);
                    if (found != null) {
                        log(4, "located confirm dialog class by descriptor: " + name);
                        Bg.post(new Runnable() {
                            @Override
                            public void run() {
                                hookConfirm(found);
                            }
                        });
                        return;
                    }
                }
                log(5, "confirm dialog class not found by descriptor either");
                return;
            }
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                return;
            }
        }
        log(4, "gave up resolving the confirm dialog class (no context)");
    }

    private static boolean isHookInstalled(String key) {
        synchronized (sInstalledHooks) {
            return sInstalledHooks.contains(key);
        }
    }

    private static void markHookInstalled(String key) {
        synchronized (sInstalledHooks) {
            sInstalledHooks.add(key);
        }
    }

    private static Method reflectConfirmMethod(ClassLoader cl, String className) {
        try {
            Class<?> c = Class.forName(className, false, cl);
            for (Method m : c.getDeclaredMethods()) {
                if (!Modifier.isStatic(m.getModifiers())) continue;
                Class<?>[] p = m.getParameterTypes();
                if (p.length == 3 && p[0] == String.class && p[1] == long.class
                        && "org.chromium.base.Callback".equals(p[2].getName())) {
                    return m;
                }
            }
        } catch (Throwable ignored) {
        }
        return null;
    }

    private static void suppressClassMethods(ClassLoader cl, String className, String[] methodNames) {
        try {
            Class<?> c = Class.forName(className, false, cl);
            for (Method m : c.getDeclaredMethods()) {
                boolean wanted = false;
                for (String name : methodNames) {
                    if (m.getName().equals(name)) wanted = true;
                }
                if (!wanted) continue;
                hook(m, new Hooker() {
                    @Override
                    public Object intercept(Chain chain) throws Throwable {
                        if (isPending() && pendingMatchesArgs(chain.getArgs())) {
                            log(4, "suppressed Edge download dialog: " + chain.getExecutable().getName());
                            return null;
                        }
                        return chain.proceed();
                    }
                });
            }
        } catch (Throwable t) {
            log(4, "suppressor hook skipped for " + className, t);
        }
    }

    /** Name of the download currently owned by this module, or null. */
    private static String pendingName() {
        PendingDownload pending = sPending;
        if (pending != null) return pending.displayName();
        return sWaitName;
    }

    /**
     * True when one of the call's string arguments names the download we own. Dialogs for
     * other downloads must reach Edge untouched, otherwise they would be stalled or aborted.
     */
    private static boolean pendingMatchesArgs(java.util.List<Object> args) {
        String pendingName = pendingName();
        if (pendingName == null) return true;
        for (Object arg : args) {
            if (arg instanceof String && namesMatch((String) arg, pendingName)) return true;
        }
        return false;
    }

    private static void hook(Method method, final Hooker hooker) {
        sApi.hook(method)
                .setExceptionMode(ExceptionMode.PROTECTIVE)
                .intercept(hooker);
    }

    // ------------------------------------------------------------------ interception: item first

    private static void handleNewDownload(Object item, Object service) {
        if (item == null || service == null) return;
        if (!RemoteSettings.enabled()) {
            log(4, "interception disabled in settings, letting Edge download normally");
            return;
        }

        Object info = Reflect.get(item, "c");
        if (info == null) return;

        // Field layout dump for the first few items: makes the URL field verifiable from
        // the module log (Edge's fields are obfuscated).
        if (sDumpCount < 12) {
            sDumpCount++;
            log(4, "DUMP created item: " + Dump.info(info) + " | gurls: " + Dump.gurlFields(info));
        }

        String url = Reflect.pickUrl(info);
        if (!Reflect.isHttp(url)) {
            log(4, "skip: non-http download (" + url + ")");
            return;
        }

        String fileName = Reflect.pickString(info, "e", "file");
        String mime = Reflect.pickString(info, "c", "mime");
        boolean systemManaged = Boolean.TRUE.equals(Reflect.get(item, "b"));
        PendingDownload download = buildPending(item, info, service, url, fileName, mime);
        if (download == null) return;

        // A dialog without a known URL is waiting for this item.
        if (sWaitDecision != 0 && SystemClock.elapsedRealtime() < sWaitUntilMs
                && (sWaitName == null || fileName.length() == 0 || namesMatch(sWaitName, fileName))) {
            int decision = sWaitDecision;
            clearWaitState();
            log(4, "resolved waiting dialog with item " + url);
            cancelEdgeDownload(download);
            applyAction(download, decision);
            return;
        }

        // Items already delegated to the system download manager need no redirection.
        if (systemManaged) return;

        // Restored/history items also produce this callback (observed on device: 10+ entries
        // at startup, including unfinished ones Edge wants to resume). The device dumps show
        // a brand new download has DownloadInfo.j == 0 and q == 0, while restored entries
        // carry q in 1..3 - so both must be zero to treat an item as a new download.
        long receivedBytes = Reflect.longValue(info, "j");
        long stateValue = Reflect.longValue(info, "q");
        if (receivedBytes > 0 || (stateValue >= 0 && stateValue != 0)) {
            log(4, "skip: restored/history item (received=" + receivedBytes
                    + ", state=" + stateValue + ")");
            return;
        }

        // Edge usually creates the download item a few milliseconds *before* the
        // confirmation dialog; remember it so the dialog can still be mapped to its URL.
        if (download != null) {
            sRecentItem = download;
            sRecentItemAt = SystemClock.elapsedRealtime();
        }

        if (isPending()) {
            log(4, "skip: takeover already in progress");
            return;
        }

        Activity activity = currentActivity();
        if (activity == null) {
            log(4, "skip: no resumed Edge activity");
            return;
        }
        if (isPageSaveArtifact(fileName, mime)) {
            log(4, "skip: page-save artifact (" + fileName + ")");
            return;
        }

        String dedupKey = url + '|' + fileName;
        if (isDuplicate(dedupKey)) {
            log(4, "skip: duplicate event for " + url);
            return;
        }
        markRecent(dedupKey);

        // Stop Edge's download before any body data is transferred (target determination
        // and the transfer only happen after this callback); the download the user finally
        // gets is created by the system DownloadManager.
        cancelEdgeDownload(download);

        sPending = download;
        sRecentItem = null;
        sTakeoverUntilMs = SystemClock.elapsedRealtime() + TAKEOVER_WINDOW_MS;
        log(4, "intercepted download: " + fileName + " (" + mime + ") " + url);
        toastAsync(Str.intercepted(download.displayName()));
        showPendingAsync(activity, download);
    }

    private static PendingDownload buildPending(Object item, Object info, Object service,
            String url, String fileName, String mime) {
        String guid = Reflect.callString(item, "a");
        if (guid.length() == 0) {
            log(5, "skip: empty download id");
            return null;
        }
        String namespace = "";
        Object platformId = Reflect.get(item, "a");
        if (platformId != null) {
            namespace = Reflect.string(Reflect.get(platformId, "a"));
        }
        Object otrProfileId = Reflect.get(info, "p");
        String referrer = Reflect.pickReferrer(info, url);
        Context context = application();
        if (context == null) return null;
        return new PendingDownload(guid, namespace, otrProfileId, serializeOtrProfileId(otrProfileId),
                url, fileName, mime, referrer, service, context);
    }

    // ------------------------------------------------------------------ interception: dialog first

    /** @return true when Edge's dialog must not be shown (we took over). */
    private static boolean takeOverConfirmDialog(String text, long size, Object callback) {
        if (!RemoteSettings.enabled()) {
            log(4, "interception disabled in settings, leaving Edge's dialog");
            return false;
        }
        String name = text == null ? "" : text.trim();
        // Messages of the harmful-file / blocked dialogs also travel through this factory;
        // only plain file names are taken over so safety warnings stay intact.
        if (!looksLikeFileName(name)) {
            log(4, "confirm dialog passed through (not a plain file name)");
            return false;
        }
        Activity activity = currentActivity();
        if (activity == null) {
            log(4, "no activity for confirm dialog, leaving Edge's dialog");
            return false;
        }
        if (isPending()) {
            String pendingName = pendingName();
            if (pendingName != null && !namesMatch(name, pendingName)) {
                // A dialog for a different download: it must not be answered by us, or that
                // download would be silently aborted.
                log(4, "confirm dialog for another download (" + name + "), leaving it to Edge");
                return false;
            }
            // Our own takeover: never answer Edge with "accept", so no Edge download can start.
            invokeCallback(callback, Boolean.FALSE);
            log(4, "suppressed repeated confirm dialog for " + name);
            return true;
        }

        // Edge creates the download item just before showing this dialog in the common
        // case, so prefer that item (it carries the real URL).
        PendingDownload recent = recentItemFor(name);
        if (recent != null) {
            sPending = recent;
            sTakeoverUntilMs = SystemClock.elapsedRealtime() + TAKEOVER_WINDOW_MS;
            log(4, "confirm dialog matched the item created moments ago: " + recent.url);
            showPendingAsync(activity, recent);
            return true;
        }

        // Wait for the download item (it usually exists already) so our dialog can show the
        // real URL; the dialog never answers Edge's callback with "accepted".
        sConfirm = new ConfirmRequest(name, size, callback);
        sTakeoverUntilMs = SystemClock.elapsedRealtime() + TAKEOVER_WINDOW_MS;
        log(4, "confirm dialog taken over, waiting for the download item: " + name);
        showConfirmAsync(activity, sConfirm);
        return true;
    }

    /**
     * Returns the download item created shortly before the dialog (its file name may still
     * be empty at creation time, in which case the dialog's name is used as a hint only).
     */
    private static PendingDownload recentItemFor(String dialogName) {
        PendingDownload recent = sRecentItem;
        if (recent == null) return null;
        if (SystemClock.elapsedRealtime() - sRecentItemAt > RECENT_ITEM_WINDOW_MS) {
            sRecentItem = null;
            return null;
        }
        String itemName = recent.fileName;
        boolean nameKnown = itemName != null && itemName.length() > 0
                && dialogName != null && dialogName.length() > 0;
        if (nameKnown && !namesMatch(dialogName, itemName)) {
            log(4, "recent item '" + itemName + "' does not match the dialog '" + dialogName + "'");
            return null;
        }
        sRecentItem = null;
        return recent;
    }

    // ------------------------------------------------------------------ dialog plumbing

    private static void showPendingAsync(final Activity activity, final PendingDownload download) {
        final long takeoverAt = sTakeoverUntilMs;
        MAIN.post(new Runnable() {
            @Override
            public void run() {
                if (SystemClock.elapsedRealtime() > takeoverAt) return;
                DialogPresenter.showForPending(activity, download);
            }
        });
    }

    private static void showConfirmAsync(final Activity activity, final ConfirmRequest request) {
        final long takeoverAt = sTakeoverUntilMs;
        MAIN.post(new Runnable() {
            @Override
            public void run() {
                if (SystemClock.elapsedRealtime() > takeoverAt) return;
                DialogPresenter.showForConfirm(activity, request);
            }
        });
    }

    /** Called by the dialog when the user answered a known-download dialog. */
    static void resolvePending(PendingDownload download, int action) {
        if (sPending == download) sPending = null;
        // Already cancelled at item creation; repeated calls are idempotent insurance.
        cancelEdgeDownload(download);
        applyAction(download, action);
    }

    /**
     * Called by the dialog when the user answered a dialog that appeared without a known
     * download item. Edge's dialog is answered with "accepted" only here - and only after
     * the user asked for the download - because that is the only way to make Edge hand out
     * the real URL. The item is cancelled the moment it is created (before any transfer),
     * so nothing is ever downloaded by Edge itself.
     */
    static void resolveConfirm(ConfirmRequest request, int action) {
        if (sConfirm == request) sConfirm = null;
        if (action == DialogPresenter.ACTION_DISMISS) {
            invokeCallback(request.callback, Boolean.FALSE);
            toastAsync(Str.cancelled());
            return;
        }
        sWaitDecision = action;
        sWaitName = request.text;
        sWaitUntilMs = SystemClock.elapsedRealtime() + WAIT_ITEM_WINDOW_MS;
        sTakeoverUntilMs = sWaitUntilMs;
        invokeCallback(request.callback, Boolean.TRUE);
        toastAsync(Str.waitingForLink());
        final long token = sWaitUntilMs;
        MAIN.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sWaitUntilMs != token) return;          // superseded by a newer dialog
                if (SystemClock.elapsedRealtime() < token) return;
                log(4, "no download item appeared for the confirm dialog");
                clearWaitState();
                toastAsync(Str.linkFailed());
            }
        }, WAIT_ITEM_WINDOW_MS + 200L);
    }

    private static void clearWaitState() {
        sWaitDecision = 0;
        sWaitName = null;
        sWaitUntilMs = 0;
    }

    private static void applyAction(PendingDownload download, int action) {
        switch (action) {
            case DialogPresenter.ACTION_COPY:
                DialogPresenter.copyLink(download);
                break;
            case DialogPresenter.ACTION_DOWNLOAD:
                startSelectedDownloader(download);
                break;
            default:
                toastAsync(Str.cancelled());
                break;
        }
    }

    /** Hands the download to the downloader selected in the module settings. */
    private static void startSelectedDownloader(PendingDownload download) {
        String id = RemoteSettings.downloaderId();
        Downloaders.Entry entry = Downloaders.byId(id);
        boolean custom = Downloaders.ID_CUSTOM.equals(id);
        if (entry == null && !custom) {
            log(4, "unknown downloader '" + id + "', using the system downloader");
            DialogPresenter.startSystemDownload(download);
            return;
        }
        if (entry != null && entry.isSystem()) {
            DialogPresenter.startSystemDownload(download);
            return;
        }
        String customPackage = RemoteSettings.customPackage();
        String pkg = custom ? customPackage : entry.pkg;
        if (custom && (customPackage == null || customPackage.length() == 0)) {
            log(4, "custom downloader package not set, using the system downloader");
            DialogPresenter.startSystemDownload(download);
            return;
        }
        String label = custom ? customPackage : entry.label();
        if (custom) {
            entry = new Downloaders.Entry(Downloaders.ID_CUSTOM, customPackage, customPackage,
                    Downloaders.MODE_VIEW, null);
        }
        // No installed-check here: Edge's process has its own package visibility, so the
        // launch result is the only reliable signal.
        if (launchExternal(download, entry)) {
            log(4, "handed over to " + label + " (" + pkg + ")");
            toastAsync(Str.handedTo(label));
            return;
        }
        log(4, "starting " + pkg + " failed, using the system downloader");
        toastAsync(Str.downloaderFailed(label));
        DialogPresenter.startSystemDownload(download);
    }

    private static boolean launchExternal(PendingDownload download, Downloaders.Entry entry) {
        android.content.Context context = download.appContext;
        try {
            android.content.Intent intent = Downloaders.buildIntent(context, entry, entry.pkg,
                    download.url, download.displayName(), download.mime);
            if (intent != null) {
                context.startActivity(intent);
                return true;
            }
        } catch (Throwable t) {
            log(4, "hand-off to " + entry.pkg + " failed: " + t);
        }
        for (android.content.Intent fallback : Downloaders.fallbacks(context, entry, download.url,
                download.mime)) {
            try {
                context.startActivity(fallback);
                return true;
            } catch (Throwable ignored) {
            }
        }
        return false;
    }

    private static void invokeCallback(Object callback, Boolean value) {
        if (callback == null) return;
        try {
            Method onResult = callback.getClass().getMethod("onResult", Object.class);
            onResult.invoke(callback, value);
        } catch (Throwable t) {
            try {
                Method onResult = callback.getClass().getMethod("onResult", Boolean.class);
                onResult.invoke(callback, value);
            } catch (Throwable t2) {
                log(5, "failed to answer confirm dialog callback", t2);
            }
        }
    }

    private static boolean isPending() {
        if (SystemClock.elapsedRealtime() > sTakeoverUntilMs) return false;
        return sPending != null || sConfirm != null || sWaitDecision != 0;
    }

    /**
     * Distinguishes a plain file name from a warning message. Both are passed to the same
     * dialog factory: dangerous-file / policy prompts hand over a full sentence
     * ("此文件可能有害。是否仍然下载 "x.apk"？"), the normal confirmation a bare name.
     * Parentheses are legal in file names ("Setup (3).apk") and stay allowed.
     */
    private static boolean looksLikeFileName(String text) {
        if (text.length() == 0 || text.length() > 120) return false;
        final String forbidden = "\n\r?？:：*\"<>|/\\。，、；;！!「」【】（）“”‘’";
        for (int i = 0; i < text.length(); i++) {
            if (forbidden.indexOf(text.charAt(i)) >= 0) return false;
        }
        return true;
    }

    /** Tolerant file-name comparison ("x (3).apk" vs "x.apk" style differences). */
    private static boolean namesMatch(String a, String b) {
        if (a == null || b == null) return false;
        if (a.equals(b)) return true;
        String na = normalizeName(a);
        String nb = normalizeName(b);
        if (na.length() == 0 || nb.length() == 0) return false;
        if (na.equals(nb)) return true;
        int prefix = Math.min(12, Math.min(na.length(), nb.length()));
        if (prefix >= 6 && na.substring(0, prefix).equals(nb.substring(0, prefix))) return true;
        return na.contains(nb) || nb.contains(na);
    }

    private static String normalizeName(String value) {
        StringBuilder sb = new StringBuilder(value.length());
        String lower = value.toLowerCase(java.util.Locale.ROOT);
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) sb.append(ch);
        }
        return sb.toString();
    }

    private static boolean isPageSaveArtifact(String fileName, String mime) {
        String name = fileName == null ? "" : fileName.toLowerCase(java.util.Locale.ROOT);
        if (name.endsWith(".mhtml") || name.endsWith(".webarchive")) return true;
        return mime != null && mime.startsWith("multipart/related");
    }

    private static boolean isDuplicate(String key) {
        synchronized (sRecentDownloads) {
            Long when = sRecentDownloads.get(key);
            return when != null && SystemClock.elapsedRealtime() - when < DEDUP_WINDOW_MS;
        }
    }

    private static void markRecent(String key) {
        synchronized (sRecentDownloads) {
            sRecentDownloads.put(key, SystemClock.elapsedRealtime());
            while (sRecentDownloads.size() > 16) {
                String oldest = sRecentDownloads.keySet().iterator().next();
                sRecentDownloads.remove(oldest);
            }
        }
    }

    // ------------------------------------------------------------------ actions

    /** Cancels the in-app download so Edge's own download manager never owns the file. */
    static void cancelEdgeDownload(PendingDownload download) {
        if (download == null) return;
        cancelNow(download);
        // Retry shortly after in case the first attempt was not effective on this build.
        final PendingDownload target = download;
        MAIN.postDelayed(new Runnable() {
            @Override
            public void run() {
                cancelNow(target);
            }
        }, 1500L);
    }

    /** Runs both cancel channels; either one alone is enough to stop the download. */
    private static void cancelNow(PendingDownload download) {
        boolean removed = cancelByRemove(download);
        // The notification-action channel is cheap and idempotent, so it is always sent too.
        cancelByNotificationAction(download);
        if (!removed) log(4, "removeDownload path failed for " + download.guid);
    }

    private static boolean cancelByRemove(PendingDownload download) {
        try {
            Class<?> otrClass = download.otrProfileId != null
                    ? download.otrProfileId.getClass()
                    : Class.forName("org.chromium.chrome.browser.profiles.OtrProfileId", false,
                            download.downloadManagerService.getClass().getClassLoader());
            Method remove = download.downloadManagerService.getClass()
                    .getMethod("removeDownload", String.class, otrClass, boolean.class);
            remove.invoke(download.downloadManagerService, download.guid, download.otrProfileId, Boolean.TRUE);
            log(4, "cancelled Edge download " + download.guid);
            return true;
        } catch (Throwable t) {
            log(4, "removeDownload reflection failed", t);
            return false;
        }
    }

    private static void cancelByNotificationAction(PendingDownload download) {
        try {
            String[] strings = DexStrings.resolve(download.appContext);
            android.content.Intent intent = new android.content.Intent(strings[DexStrings.ACTION_CANCEL]);
            intent.setClassName(download.appContext,
                    "org.chromium.chrome.browser.download.DownloadBroadcastManager");
            intent.putExtra(strings[DexStrings.EXTRA_ID], download.guid);
            intent.putExtra(strings[DexStrings.EXTRA_NAMESPACE], download.namespace);
            intent.putExtra(strings[DexStrings.EXTRA_IS_OTR], download.otrProfileId != null);
            if (download.otrProfileIdSerialized != null) {
                intent.putExtra(strings[DexStrings.EXTRA_OTR_PROFILE_ID], download.otrProfileIdSerialized);
            }
            download.appContext.startService(intent);
            log(4, "sent " + strings[DexStrings.ACTION_CANCEL] + " to DownloadBroadcastManager for "
                    + download.guid);
        } catch (Throwable t) {
            log(5, "cancel via DownloadBroadcastManager failed for " + download.guid, t);
        }
    }

    private static String serializeOtrProfileId(Object otrProfileId) {
        if (otrProfileId == null) return null;
        try {
            Method serialize = otrProfileId.getClass().getMethod("serialize", otrProfileId.getClass());
            Object result = serialize.invoke(null, otrProfileId);
            return result instanceof String ? (String) result : null;
        } catch (Throwable t) {
            return null;
        }
    }

    // ------------------------------------------------------------------ misc

    static String guessFileName(String url) {
        try {
            String path = url;
            int query = path.indexOf('?');
            if (query >= 0) path = path.substring(0, query);
            int hash = path.indexOf('#');
            if (hash >= 0) path = path.substring(0, hash);
            int slash = path.lastIndexOf('/');
            if (slash >= 0) path = path.substring(slash + 1);
            path = android.net.Uri.decode(path);
            if (path.length() == 0) return "";
            StringBuilder sb = new StringBuilder(path.length());
            for (int i = 0; i < path.length(); i++) {
                char ch = path.charAt(i);
                if (ch == '/' || ch == '\\' || ch == ':' || ch == '*' || ch == '?' || ch == '"'
                        || ch == '<' || ch == '>' || ch == '|' || ch < 0x20) {
                    sb.append('_');
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString().replace("..", "_");
        } catch (Throwable t) {
            return "";
        }
    }

    static XposedInterface api() {
        return sApi;
    }

    static Application application() {
        Application app = sApplication;
        if (app != null) return app;
        try {
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Method current = activityThread.getDeclaredMethod("currentApplication");
            current.setAccessible(true);
            Object result = current.invoke(null);
            if (result instanceof Application) {
                sApplication = (Application) result;
                return sApplication;
            }
        } catch (Throwable ignored) {
        }
        return null;
    }

    /**
     * Currently resumed Edge activity; falls back to the same tracking Edge itself uses
     * (org.chromium.base.ApplicationStatus.d) so we do not depend on our own hooks.
     */
    static Activity currentActivity() {
        Activity tracked = sCurrentActivity;
        if (tracked != null && !tracked.isFinishing()) return tracked;
        ClassLoader cl = sTargetClassLoader;
        if (cl != null) {
            try {
                Class<?> status = Class.forName("org.chromium.base.ApplicationStatus", false, cl);
                Field field = status.getField("d");
                Object value = field.get(null);
                if (value instanceof Activity && !((Activity) value).isFinishing()) {
                    return (Activity) value;
                }
            } catch (Throwable ignored) {
            }
        }
        return null;
    }

    static void toastAsync(final String text) {
        if (!DIAGNOSTIC_TOAST) return;
        MAIN.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = application();
                    if (context != null) {
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                    }
                } catch (Throwable ignored) {
                }
            }
        });
    }

    /**
     * The "module loaded" toast cannot be shown during hook installation (Edge's
     * Application does not exist yet), so it is shown with the first available context.
     */
    private static void showLoadedToastOnce() {
        if (sLoadedToastShown || !sInstallLogged) return;
        sLoadedToastShown = true;
        toastAsync(Str.loaded());
    }

    static void log(int priority, String message) {
        log(priority, message, null);
    }

    static void log(int priority, String message, Throwable t) {
        RemoteSettings.log(message);
        XposedInterface api = sApi;
        if (api == null) return;
        try {
            api.log(priority, TAG, message, t);
        } catch (Throwable ignored) {
        }
    }
}
