// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/edge_hub/downloads/EdgeDownloadRequestBridge
package org.chromium.chrome.browser.edge_hub.downloads;
public class EdgeDownloadRequestBridge {
    private static final String TAG = "RequestBridge";

    public EdgeDownloadRequestBridge()
    {
        return;
    }

    public static void downloadUrl(org.chromium.content_public.browser.WebContents p3, String p4)
    {
        if (p3 != null) {
            if (!android.text.TextUtils.isEmpty(p4)) {
                org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadRequestBridgeJni.get().downloadUrl(p3, p4, 0, 0);
                return;
            } else {
                android.util.Log.w("cr_RequestBridge", "download url is null or empty");
                return;
            }
        } else {
            android.util.Log.w("cr_RequestBridge", "webContents is null");
            return;
        }
    }

    public static void downloadUrl(org.chromium.content_public.browser.WebContents p2, String p3, int p4, String p5)
    {
        if (p2 != null) {
            if (!android.text.TextUtils.isEmpty(p3)) {
                org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadRequestBridgeJni.get().downloadUrl(p2, p3, p4, p5);
                return;
            } else {
                android.util.Log.w("cr_RequestBridge", "download url is null or empty");
                return;
            }
        } else {
            android.util.Log.w("cr_RequestBridge", "webContents is null");
            return;
        }
    }
}
