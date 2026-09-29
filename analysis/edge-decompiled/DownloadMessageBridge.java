// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/DownloadMessageBridge
package org.chromium.chrome.browser.download;
public final class DownloadMessageBridge {

    public static void showIncognitoDownloadMessage(org.chromium.ui.base.WindowAndroid p5, org.chromium.base.JniOnceCallback p6)
    {
        tbd v0_4 = org.chromium.chrome.browser.download.DownloadManagerService.a().f;
        sgy v1_0 = af9.a;
        if (p5 == 0) {
            v0_4.l.a();
        }
        int v5_1;
        if (p5 == 0) {
            v5_1 = v0_4.f();
        } else {
            v0_4.getClass();
            v5_1 = pir.a(p5);
        }
        if (v5_1 != 0) {
            tbd v0_3 = org.chromium.ui.modelutil.PropertyModel.b(java.util.Arrays.asList(yfr.N));
            v0_3.put(yfr.a, Integer.valueOf(36));
            org.chromium.ui.modelutil.PropertyModel v2_3 = new org.chromium.ui.modelutil.PropertyModel(0, v0_3);
            v2_3.s(yfr.g, v1_0.getString(tzy.incognito_download_message_title));
            v2_3.s(yfr.i, v1_0.getString(tzy.incognito_download_message_detail));
            v2_3.s(yfr.c, v1_0.getString(tzy.incognito_download_message_button));
            v2_3.s(yfr.m, c81.a(izy.ic_incognito_download_message, v1_0));
            tbd v0_14 = new rbd();
            v0_14.a = p6;
            v2_3.s(yfr.e, v0_14);
            tbd v0_16 = new tbd(0);
            v0_16.b = p6;
            v2_3.s(yfr.y, v0_16);
            v5_1.d(v2_3, 1);
            acd.i(0);
            return;
        } else {
            p6.onResult(Boolean.TRUE);
            acd.i(5);
            return;
        }
    }

    public static void showUnsupportedDownloadMessage(org.chromium.ui.base.WindowAndroid p5)
    {
        lv30 v0 = nv30.a(p5);
        if (v0 != null) {
            int v5_5 = ((android.content.Context) p5.g.get());
            rt30 v1_0 = rt30.a(v5_5.getString(tzy.download_file_type_not_supported), 0, 1, 4);
            v1_0.e = v5_5.getString(uzy.ok);
            v1_0.f = 0;
            v1_0.g = 0;
            v0.t(v1_0);
            return;
        } else {
            return;
        }
    }
}
