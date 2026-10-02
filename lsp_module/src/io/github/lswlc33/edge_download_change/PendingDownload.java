package io.github.lswlc33.edge_download_change;

import android.content.Context;

/** One intercepted Edge download that is currently owned by this module. */
final class PendingDownload {

    interface NameListener {
        /** Called on the main thread when the display name becomes known or changes. */
        void onNameChanged(String newName);
    }

    final String guid;
    final String namespace;
    final Object otrProfileId;
    final String otrProfileIdSerialized;
    final String url;
    final String mime;
    final String referrer;
    final Object downloadManagerService;
    final Context appContext;

    /**
     * The name is refined asynchronously (redirect probing) after the item was created,
     * so it is volatile and read through {@link #fileName()}/{@link #displayName()}.
     */
    private volatile String fileName;
    private volatile NameListener nameListener;

    PendingDownload(String guid, String namespace, Object otrProfileId, String otrProfileIdSerialized,
            String url, String fileName, String mime, String referrer,
            Object downloadManagerService, Context appContext) {
        this.guid = guid;
        this.namespace = namespace;
        this.otrProfileId = otrProfileId;
        this.otrProfileIdSerialized = otrProfileIdSerialized;
        this.url = url;
        this.fileName = fileName;
        this.mime = mime;
        this.referrer = referrer;
        this.downloadManagerService = downloadManagerService;
        this.appContext = appContext;
    }

    String fileName() {
        String name = fileName;
        return name == null ? "" : name;
    }

    void setFileName(String newName) {
        if (newName == null || newName.length() == 0) return;
        synchronized (this) {
            String old = fileName;
            if (old != null && old.length() > 0 && DownloadHooks.namesMatch(old, newName)) return;
            fileName = newName;
        }
        final NameListener listener = nameListener;
        if (listener != null) {
            Bg.post(new Runnable() {
                @Override
                public void run() {
                    listener.onNameChanged(newName);
                }
            });
        }
    }

    void setNameListener(NameListener listener) {
        nameListener = listener;
    }

    String displayName() {
        String name = fileName;
        if (name != null && name.length() > 0) return name;
        String guessed = DownloadHooks.guessFileName(url);
        return guessed.length() > 0 ? guessed : "download";
    }
}
