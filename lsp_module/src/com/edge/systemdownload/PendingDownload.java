package com.edge.systemdownload;

import android.content.Context;

/** One intercepted Edge download that is currently owned by this module. */
final class PendingDownload {

    final String guid;
    final String namespace;
    final Object otrProfileId;
    final String otrProfileIdSerialized;
    final String url;
    final String fileName;
    final String mime;
    final String referrer;
    final Object downloadManagerService;
    final Context appContext;

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

    String displayName() {
        if (fileName != null && fileName.length() > 0) return fileName;
        String guessed = DownloadHooks.guessFileName(url);
        return guessed.length() > 0 ? guessed : "download";
    }
}
