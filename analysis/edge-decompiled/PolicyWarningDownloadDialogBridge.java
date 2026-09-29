// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/PolicyWarningDownloadDialogBridge
package org.chromium.chrome.browser.download;
public final class PolicyWarningDownloadDialogBridge {
    public long a;

    public static org.chromium.chrome.browser.download.PolicyWarningDownloadDialogBridge create(long p1)
    {
        org.chromium.chrome.browser.download.PolicyWarningDownloadDialogBridge v0_1 = new org.chromium.chrome.browser.download.PolicyWarningDownloadDialogBridge();
        v0_1.a = p1;
        return v0_1;
    }

    public final void destroy()
    {
        this.a = 0;
        return;
    }

    public final void showDialog(org.chromium.ui.base.WindowAndroid p6, String p7, String p8)
    {
        String v0_6 = ((android.app.Activity) p6.x().get());
        if ((v0_6 instanceof o3s)) {
            n3s v1_0 = ((o3s) v0_6).getModalDialogManager();
            int v2_1 = new xbx();
            v2_1.a = this;
            v2_1.b = p7;
            v2_1.c = p6;
            String v5_1 = v0_6.getResources();
            int v6_2 = new wbx();
            v6_2.a = v2_1;
            v6_2.b = v1_0;
            java.util.Map v7_3 = org.chromium.ui.modelutil.PropertyModel.b(java.util.Arrays.asList(s3s.T));
            v7_3.put(s3s.b, v6_2);
            v7_3.put(s3s.d, v5_1.getString(uzy.policy_warning_download_dialog_title));
            String v0_5 = v5_1.getString(uzy.policy_warning_download_dialog_text);
            CharSequence[] v3 = new CharSequence[1];
            v3[0] = p8;
            v7_3.put(s3s.i, new java.util.ArrayList(java.util.List.of(android.text.TextUtils.expandTemplate(v0_5, v3))));
            v7_3.put(s3s.o, v5_1.getString(tzy.cancel));
            v7_3.put(s3s.r, v5_1.getString(tzy.dangerous_download_dialog_confirm_text));
            v7_3.put(s3s.I, Integer.valueOf(1));
            v7_3.put(s3s.L, Long.valueOf(600));
            String v5_6 = new org.chromium.ui.modelutil.PropertyModel;
            v5_6(0, v7_3);
            v1_0.m(0, v5_6, 0);
            kkz.j(0, 4, "Download.PolicyWarningDialog.Events");
            return;
        } else {
            J.N.VJO(61, this.a, p7);
            k5t.d2(p6);
            return;
        }
    }
}
