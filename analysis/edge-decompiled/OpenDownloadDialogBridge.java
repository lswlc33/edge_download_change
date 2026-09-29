// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/OpenDownloadDialogBridge
package org.chromium.chrome.browser.download;
public final class OpenDownloadDialogBridge {
    public long a;

    public static org.chromium.chrome.browser.download.OpenDownloadDialogBridge create(long p1)
    {
        org.chromium.chrome.browser.download.OpenDownloadDialogBridge v0_1 = new org.chromium.chrome.browser.download.OpenDownloadDialogBridge();
        v0_1.a = p1;
        return v0_1;
    }

    public final void destroy()
    {
        this.a = 0;
        return;
    }

    public final void showDialog(org.chromium.chrome.browser.profiles.Profile p4, String p5)
    {
        int v0_0 = org.chromium.chrome.browser.download.MimeUtils.a();
        if (v0_0.size() != 0) {
            i8d v1_1 = i8d.c;
            if (v1_1 == null) {
                v1_1 = new i8d();
                v1_1.a = 0;
                v1_1.b = new java.util.ArrayList();
                i8d.c = v1_1;
            }
            oau v2_3 = new oau();
            v2_3.a = this;
            v2_3.b = p5;
            v2_3.c = p4;
            v2_3.d = v0_0;
            java.util.ArrayList v3_1 = v1_1.b;
            if (v1_1.a == 1) {
                v3_1.add(v2_3);
                return;
            } else {
                android.app.Activity v4_2 = org.chromium.base.ApplicationStatus.d;
                if (!(v4_2 instanceof o3s)) {
                    v3_1.add(v2_3);
                    return;
                } else {
                    v2_3.onResult(v4_2);
                    return;
                }
            }
        } else {
            J.N.VJOZ(10, this.a, p5, 0);
            return;
        }
    }
}
