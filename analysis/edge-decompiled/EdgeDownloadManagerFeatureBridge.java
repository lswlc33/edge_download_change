// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/EdgeDownloadManagerFeatureBridge
package org.chromium.chrome.browser.download;
public final class EdgeDownloadManagerFeatureBridge {
    public static final java.util.ArrayList a;

    static EdgeDownloadManagerFeatureBridge()
    {
        org.chromium.chrome.browser.download.EdgeDownloadManagerFeatureBridge.a = new java.util.ArrayList();
        return;
    }

    public static boolean isUseNewDownloadDialogFlowEnabled()
    {
        if (!bhe.a()) {
            return bhe.b();
        } else {
            return 0;
        }
    }

    public static boolean shouldConfirmDownload()
    {
        if ((bhe.a()) || ((!org.chromium.chrome.browser.download.EdgeDownloadManagerFeatureBridge.isUseNewDownloadDialogFlowEnabled()) || ((org.chromium.chrome.browser.download.DownloadDialogBridge.b(org.chromium.chrome.browser.profiles.ProfileManager.b()) != 2) && ((android.os.Build$VERSION.SDK_INT < 31) && (org.chromium.chrome.browser.download.EdgeDownloadManagerFeatureBridge.a.size() != 1))))) {
            return 0;
        } else {
            return 1;
        }
    }
}
