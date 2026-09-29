// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/InsecureDownloadDialogBridge
package org.chromium.chrome.browser.download;
public final class InsecureDownloadDialogBridge {
    public long a;

    public static org.chromium.chrome.browser.download.InsecureDownloadDialogBridge create(long p1)
    {
        org.chromium.chrome.browser.download.InsecureDownloadDialogBridge v0_1 = new org.chromium.chrome.browser.download.InsecureDownloadDialogBridge();
        v0_1.a = p1;
        return v0_1;
    }

    public final void destroy()
    {
        this.a = 0;
        return;
    }

    public final void showDialog(org.chromium.ui.base.WindowAndroid p8, String p9, long p10, long p12)
    {
        int v8_4 = ((android.app.Activity) p8.x().get());
        if ((v8_4 instanceof o3s)) {
            n3s v12_3 = ((o3s) v8_4).getModalDialogManager();
            pzm v13_1 = new pzm();
            v13_1.a = this;
            v13_1.b = p12;
            if (p10 > 0) {
                org.chromium.ui.modelutil.PropertyModel v7_3 = jdd.b(v8_4, jdd.a, p10);
                java.util.Map v10_2 = new StringBuilder();
                v10_2.append(p9);
                v10_2.append(" (");
                v10_2.append(v7_3);
                v10_2.append(")");
                p9 = v10_2.toString();
            }
            org.chromium.ui.modelutil.PropertyModel v7_5 = new java.util.ArrayList;
            v7_5(java.util.List.of(p9));
            sgy v9_4 = new ozm();
            v9_4.a = v13_1;
            v9_4.b = v12_3;
            int v8_3 = v8_4.getResources();
            java.util.Map v10_5 = org.chromium.ui.modelutil.PropertyModel.b(java.util.Arrays.asList(s3s.T));
            v10_5.put(s3s.b, v9_4);
            v10_5.put(s3s.d, v8_3.getString(tzy.insecure_download_dialog_title));
            v10_5.put(s3s.i, v7_5);
            v10_5.put(s3s.o, v8_3.getString(tzy.insecure_download_dialog_confirm_text));
            v10_5.put(s3s.r, v8_3.getString(tzy.insecure_download_dialog_discard_text));
            v10_5.put(s3s.I, Integer.valueOf(0));
            v10_5.put(s3s.L, Long.valueOf(600));
            org.chromium.ui.modelutil.PropertyModel v7_13 = new org.chromium.ui.modelutil.PropertyModel;
            v7_13(0, v10_5);
            v12_3.m(1, v7_13, 0);
            return;
        } else {
            J.N.VJJZ(2, this.a, p12, 0);
            return;
        }
    }
}
