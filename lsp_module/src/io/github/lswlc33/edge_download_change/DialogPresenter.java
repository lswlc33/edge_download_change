package io.github.lswlc33.edge_download_change;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

/**
 * Shows the replacement download dialog ("复制" / "下载") inside Edge's process and
 * performs the actions the user picks.
 */
final class DialogPresenter {

    static final int ACTION_COPY = 1;
    static final int ACTION_DOWNLOAD = 2;
    static final int ACTION_DISMISS = 3;

    private DialogPresenter() {}

    /** Dialog for a download whose real URL is already known. */
    static void showForPending(final Activity activity, final PendingDownload download) {
        final AlertDialog dialog = show(activity, messageFor(download),
                new Runnable() {
                    @Override
                    public void run() {
                        DownloadHooks.resolvePending(download, ACTION_COPY);
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        DownloadHooks.resolvePending(download, ACTION_DOWNLOAD);
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        DownloadHooks.resolvePending(download, ACTION_DISMISS);
                    }
                });
        // The name is probed in the background; refresh the text when it arrives.
        download.setNameListener(new PendingDownload.NameListener() {
            @Override
            public void onNameChanged(String newName) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.setMessage(messageFor(download));
                }
            }
        });
    }

    private static String messageFor(PendingDownload download) {
        return download.displayName() + "\n\n" + download.url
                + "\n\n「下载」将交给 " + RemoteSettings.downloaderLabel();
    }

    /**
     * Dialog for Edge's confirmation prompt, which arrives before the download URL is
     * known. Answering 复制/下载 accepts Edge's dialog; the decision is applied to the
     * download item as soon as it exists.
     */
    static void showForConfirm(final Activity activity, final ConfirmRequest request) {
        StringBuilder message = new StringBuilder(request.text);
        if (request.size > 0) {
            message.append("\n").append(formatSize(request.size));
        }
        message.append("\n\n「下载」将交给 ").append(RemoteSettings.downloaderLabel());
        show(activity, message.toString(),
                new Runnable() {
                    @Override
                    public void run() {
                        DownloadHooks.resolveConfirm(request, ACTION_COPY);
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        DownloadHooks.resolveConfirm(request, ACTION_DOWNLOAD);
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        DownloadHooks.resolveConfirm(request, ACTION_DISMISS);
                    }
                });
    }

    private static AlertDialog show(final Activity activity, String message,
            final Runnable onCopy, final Runnable onDownload, final Runnable onDismiss) {
        if (activity == null || activity.isFinishing()
                || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed())) {
            DownloadHooks.log(4, "no usable activity for the dialog, dismissing");
            onDismiss.run();
            return null;
        }
        try {
            AlertDialog dialog = new AlertDialog.Builder(activity)
                    .setTitle(Str.dialogTitle())
                    .setMessage(message)
                    .setPositiveButton(Str.dialogDownload(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int which) {
                            onDownload.run();
                        }
                    })
                    .setNegativeButton(Str.dialogCopy(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int which) {
                            onCopy.run();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface d) {
                            onDismiss.run();
                        }
                    })
                    .create();
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
            return dialog;
        } catch (Throwable t) {
            DownloadHooks.log(5, "showing the dialog failed", t);
            onDismiss.run();
            return null;
        }
    }

    static void copyLink(PendingDownload download) {
        try {
            ClipboardManager clipboard = (ClipboardManager) download.appContext
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null) {
                clipboard.setPrimaryClip(ClipData.newPlainText("download-url", download.url));
            }
        } catch (Throwable t) {
            DownloadHooks.log(5, "copy failed", t);
            toast(download, Str.copyFailed());
        }
    }

    static void startSystemDownload(PendingDownload download) {
        Context context = download.appContext;
        String name = download.displayName();
        try {
            android.app.DownloadManager manager =
                    (android.app.DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            if (manager == null) {
                toast(download, Str.systemUnavailable());
                copyLink(download);
                return;
            }
            android.app.DownloadManager.Request request =
                    new android.app.DownloadManager.Request(Uri.parse(download.url));
            request.setTitle(name);
            request.setDescription(download.url);
            String mime = normalizeMime(download.mime);
            if (mime.length() > 0) request.setMimeType(mime);
            request.setNotificationVisibility(
                    android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setAllowedOverMetered(true);
            request.setAllowedOverRoaming(true);
            if (download.referrer != null && download.referrer.length() > 0) {
                request.addRequestHeader("Referer", download.referrer);
            }
            try {
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);
            } catch (Throwable ignored) {
                // Some ROMs reject sub-paths; fall back to the manager's default destination.
            }
            long id = manager.enqueue(request);
            DownloadHooks.log(4, "handed over to system DownloadManager, id=" + id + ", url=" + download.url);
        } catch (Throwable t) {
            DownloadHooks.log(5, "system download enqueue failed", t);
            toast(download, Str.systemFailed());
            copyLink(download);
        }
    }

    private static String normalizeMime(String mime) {
        if (mime == null || mime.length() == 0) return "";
        int semicolon = mime.indexOf(';');
        String value = (semicolon > 0 ? mime.substring(0, semicolon) : mime).trim();
        if (value.indexOf('/') < 0) return "";
        return value;
    }

    static String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        double kb = bytes / 1024.0;
        if (kb < 1024) return String.format(java.util.Locale.US, "%.2f KB", kb);
        double mb = kb / 1024.0;
        if (mb < 1024) return String.format(java.util.Locale.US, "%.2f MB", mb);
        return String.format(java.util.Locale.US, "%.2f GB", mb / 1024.0);
    }

    static void toast(PendingDownload download, String text) {
        Context context = download.appContext;
        if (context == null) return;
        try {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        } catch (Throwable ignored) {
        }
    }
}
