package com.edge.systemdownload;

/**
 * An Edge download-confirmation dialog that this module has taken over before the
 * corresponding download item exists.
 *
 * The dialog factory only receives the file name and the size; the real download URL is
 * not known yet. If the user picks 复制/下载 we let Edge proceed (the dialog callback is
 * answered with "accepted") and then apply the decision to the download item as soon as
 * the download engine creates it - see DownloadHooks.handleNewDownload().
 */
final class ConfirmRequest {

    final String text;
    final long size;
    final Object callback;

    ConfirmRequest(String text, long size, Object callback) {
        this.text = text;
        this.size = size;
        this.callback = callback;
    }
}
