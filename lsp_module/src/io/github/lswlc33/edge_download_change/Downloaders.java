package io.github.lswlc33.edge_download_change;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * The download targets a download can be handed over to: the system DownloadManager plus
 * commonly used downloader apps (same idea as Via's download-engine table).
 */
final class Downloaders {

    static final int MODE_SYSTEM = 0;
    /** ACTION_VIEW with the download URL - how most downloader apps grab links. */
    static final int MODE_VIEW = 1;
    /** ACTION_SEND with the URL as text. */
    static final int MODE_SEND = 2;
    /** Explicit component (the downloader's "add download" screen). */
    static final int MODE_EXPLICIT = 3;

    static final String ID_SYSTEM = "system";
    static final String ID_CUSTOM = "custom";

    static final class Entry {
        final String id;
        final String label;
        final String labelZh;
        final String pkg;
        final int mode;
        final String activity;

        Entry(String id, String label, String pkg, int mode, String activity) {
            this(id, label, label, pkg, mode, activity);
        }

        Entry(String id, String label, String labelZh, String pkg, int mode, String activity) {
            this.id = id;
            this.label = label;
            this.labelZh = labelZh;
            this.pkg = pkg;
            this.mode = mode;
            this.activity = activity;
        }

        /** Label in the system language. */
        String label() {
            return Str.isChinese() ? labelZh : label;
        }

        boolean isSystem() {
            return MODE_SYSTEM == mode;
        }
    }

    private static final Entry[] ENTRIES = {
            new Entry(ID_SYSTEM, "System downloader (DownloadManager)", "系统下载器（DownloadManager）",
                    null, MODE_SYSTEM, null),
            new Entry("adm", "ADM", "com.dv.adm", MODE_VIEW, null),
            new Entry("idm", "IDM", "idm.internet.download.manager", MODE_VIEW, null),
            new Entry("idm_lite", "1DM Lite", "idm.internet.download.manager.adm.lite", MODE_VIEW, null),
            new Entry("adm_lite", "ADM Lite", "idm.internet.download.manager.plus", MODE_VIEW, null),
            new Entry("vanda", "ADM (Vanda)", "com.vanda_adm.vanda", MODE_VIEW, null),
            new Entry("xunlei", "迅雷", "com.xunlei.downloadprovider", MODE_VIEW, null),
            new Entry("fdm", "FDM", "org.freedownloadmanager.fdm", MODE_EXPLICIT,
                    "org.freedownloadmanager.fdm.MyActivity"),
            new Entry("dvget", "DVGet", "com.dv.get", MODE_VIEW, null),
            new Entry("downloadnavi", "Download Navi", "com.tachibana.downloader", MODE_EXPLICIT,
                    "com.tachibana.downloader.ui.adddownload.AddDownloadActivity"),
            new Entry("aria2app", "Aria2App", "com.gianlu.aria2app", MODE_EXPLICIT,
                    "com.gianlu.aria2app.LoadingActivity"),
            new Entry("gopeed", "Gopeed", "com.gopeed", MODE_EXPLICIT, "com.gopeed.MainActivity"),
            new Entry("abdm", "AB DM", "com.abdownloadmanager", MODE_EXPLICIT,
                    "com.abdownloadmanager.android.pages.add.AddDownloadActivity"),
            new Entry("fluxdown", "FluxDown", "com.fluxdown.app", MODE_EXPLICIT,
                    "com.fluxdown.app.MainActivity"),
    };

    private Downloaders() {}

    static List<Entry> all() {
        List<Entry> list = new ArrayList<Entry>();
        for (Entry e : ENTRIES) list.add(e);
        return list;
    }

    static Entry byId(String id) {
        if (id == null) return null;
        for (Entry e : ENTRIES) {
            if (e.id.equals(id)) return e;
        }
        return null;
    }

    static String labelOf(String id, String customPackage) {
        Entry e = byId(id);
        if (e != null) return e.label();
        if (ID_CUSTOM.equals(id)) {
            String prefix = Str.customPrefix();
            if (customPackage == null || customPackage.length() == 0) {
                return prefix + ": " + (Str.isChinese() ? "未设置" : "not set");
            }
            return prefix + ": " + customPackage;
        }
        return Str.systemDownloader();
    }

    static boolean isInstalled(Context context, String pkg) {
        if (pkg == null || pkg.length() == 0) return false;
        try {
            context.getPackageManager().getPackageInfo(pkg, 0);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * Builds the hand-off intent for a downloader app.
     *
     * @return the intent, or null when the target cannot be built / is not installed.
     */
    static Intent buildIntent(Context context, Entry entry, String customPackage,
            String url, String fileName, String mime) {
        if (entry == null) return null;
        if (entry.mode == MODE_EXPLICIT && entry.activity != null && entry.pkg != null) {
            Intent explicit = new Intent(Intent.ACTION_VIEW);
            explicit.setClassName(entry.pkg, entry.activity);
            explicit.setDataAndType(Uri.parse(url), mime == null || mime.length() == 0
                    ? "application/octet-stream" : mime);
            explicit.putExtra(Intent.EXTRA_TITLE, fileName);
            explicit.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return explicit;
        }
        if (ID_CUSTOM.equals(entry.id)) {
            if (customPackage == null || customPackage.length() == 0) return null;
            Intent custom = new Intent(Intent.ACTION_VIEW);
            custom.setPackage(customPackage);
            custom.setDataAndType(Uri.parse(url), "*/*");
            custom.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return custom;
        }
        if (entry.mode == MODE_SEND) {
            Intent send = new Intent(Intent.ACTION_SEND);
            send.setType("text/plain");
            send.putExtra(Intent.EXTRA_TEXT, url);
            send.setPackage(entry.pkg);
            send.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return send;
        }
        Intent view = new Intent(Intent.ACTION_VIEW);
        view.setDataAndType(Uri.parse(url), mime == null || mime.length() == 0
                ? "application/octet-stream" : mime);
        if (entry.pkg != null) view.setPackage(entry.pkg);
        view.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return view;
    }

    /** Fallback chain for a downloader app that could not be started directly. */
    static List<Intent> fallbacks(Context context, Entry entry, String url, String mime) {
        List<Intent> list = new ArrayList<Intent>();
        Intent view = new Intent(Intent.ACTION_VIEW);
        view.setDataAndType(Uri.parse(url), mime == null || mime.length() == 0
                ? "application/octet-stream" : mime);
        if (entry != null && entry.pkg != null) view.setPackage(entry.pkg);
        view.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        list.add(view);
        Intent send = new Intent(Intent.ACTION_SEND);
        send.setType("text/plain");
        send.putExtra(Intent.EXTRA_TEXT, url);
        if (entry != null && entry.pkg != null) send.setPackage(entry.pkg);
        send.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        list.add(send);
        return list;
    }

    @SuppressWarnings("unused")
    private static boolean hasResolver(Context context, Intent intent) {
        try {
            return context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null;
        } catch (Throwable t) {
            return false;
        }
    }
}
