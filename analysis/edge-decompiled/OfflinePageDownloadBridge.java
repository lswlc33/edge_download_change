// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/offlinepages/downloads/OfflinePageDownloadBridge
package org.chromium.chrome.browser.offlinepages.downloads;
public final class OfflinePageDownloadBridge {
    public static org.chromium.chrome.browser.offlinepages.downloads.OfflinePageDownloadBridge a;

    public static void openItem(String p0, long p1, int p3, boolean p4, boolean p5)
    {
        h1u v0_1 = new h1u();
        v0_1.a = p3;
        v0_1.b = p5;
        v0_1.c = p4;
        org.chromium.chrome.browser.offlinepages.b.b(p1, p3, v0_1, org.chromium.chrome.browser.profiles.ProfileManager.b());
        return;
    }

    public static void showDownloadingToast()
    {
        acd v0_1 = org.chromium.chrome.browser.download.DownloadManagerService.a().f;
        v0_1.l.getClass();
        if (!org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadManagerHelper.isInAppNotificationEnabled()) {
            v0_1.b(0, 1, 0, 0);
            return;
        } else {
            return;
        }
    }
}
