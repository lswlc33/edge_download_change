package com.edge.systemdownload;

import java.util.Locale;

/**
 * Locale aware strings for the injected side.
 *
 * This code runs inside Edge's process, where the module's resources are not directly
 * available, so the few strings that are shown there (dialog, toasts) are kept here in
 * both languages and selected by the system language. The module app uses resources
 * (values / values-zh) instead.
 */
final class Str {

    private Str() {}

    static boolean isChinese() {
        try {
            return Locale.getDefault().getLanguage().startsWith("zh");
        } catch (Throwable t) {
            return false;
        }
    }

    private static String pick(String en, String zh) {
        return isChinese() ? zh : en;
    }

    // dialog
    static String dialogTitle() {
        return pick("Download this file?", "下载此文件？");
    }

    static String dialogDownload() {
        return pick("Download", "下载");
    }

    static String dialogCopy() {
        return pick("Copy", "复制");
    }

    static String dialogTarget(String target) {
        return pick("\"Download\" will hand it over to ", "「下载」将交给 ") + target;
    }

    // toasts
    static String loaded() {
        return pick("edge_download_change ready ✓", "edge_download_change 已就绪 ✓");
    }

    static String waitingForLink() {
        return pick("Getting the download link…", "正在获取下载链接…");
    }

    static String linkFailed() {
        return pick("Could not get the link; no download was created", "未能获取下载链接，本次下载未创建");
    }

    static String intercepted(String name) {
        return pick("Download taken over: ", "已接管 Edge 下载：") + name;
    }

    static String copied() {
        return pick("Download link copied", "已复制下载链接");
    }

    static String copyFailed() {
        return pick("Copy failed, please copy manually", "复制失败，请手动复制");
    }

    static String handedTo(String target) {
        return pick("Handed over to ", "已交给 ") + target;
    }

    static String cancelled() {
        return pick("Edge download cancelled", "已取消 Edge 内置下载");
    }

    static String downloaderFailed(String target) {
        return pick(target + " could not be started; using the system downloader",
                target + " 启动失败，改用系统下载器");
    }

    static String systemHanded() {
        return pick("Handed over to the system downloader", "已交给系统下载器下载");
    }

    static String systemUnavailable() {
        return pick("System downloader unavailable; link copied", "系统下载器不可用，已复制链接");
    }

    static String systemFailed() {
        return pick("System downloader refused it; link copied", "系统下载器接收失败，已复制链接");
    }

    static String systemManaged() {
        return pick("This download is already handled by the system downloader", "该下载已由系统下载器接管");
    }

    static String hookDeferred() {
        return pick("edge_download_change: waiting for Edge to finish loading…",
                "edge_download_change：等待 Edge 加载完成…");
    }

    static String hookFailed() {
        return pick("edge_download_change: hook installation failed, see the module log",
                "edge_download_change：hook 安装失败，详见模块日志");
    }

    // downloader labels
    static String systemDownloader() {
        return pick("System downloader (DownloadManager)", "系统下载器（DownloadManager）");
    }

    static String customPrefix() {
        return pick("Custom", "自定义");
    }
}
