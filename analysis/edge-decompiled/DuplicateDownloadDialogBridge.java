// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/DuplicateDownloadDialogBridge
package org.chromium.chrome.browser.download;
public final class DuplicateDownloadDialogBridge {
    public long a;

    public static org.chromium.chrome.browser.download.DuplicateDownloadDialogBridge create(long p1)
    {
        org.chromium.chrome.browser.download.DuplicateDownloadDialogBridge v0_1 = new org.chromium.chrome.browser.download.DuplicateDownloadDialogBridge();
        v0_1.a = p1;
        return v0_1;
    }

    public final void destroy()
    {
        this.a = 0;
        return;
    }

    public final void showDialog(org.chromium.ui.base.WindowAndroid p19, String p20, String p21, long p22, boolean p24, org.chromium.chrome.browser.profiles.OtrProfileId p25, boolean p26, boolean p27, long p28)
    {
        android.app.Activity v7_1 = ((android.app.Activity) p19.x().get());
        if ((v7_1 instanceof o3s)) {
            fpd v9_1 = new fpd();
            int v2_7 = ((o3s) v7_1).getModalDialogManager();
            gpd v10_1 = new gpd();
            v10_1.a = this;
            v10_1.b = p28;
            android.content.res.Resources v11 = v7_1.getResources();
            v9_1.a = v2_7;
            if (!org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadManagerHelper.isUseNewDownloadDialogFlowEnabled()) {
                if (v2_7 == 0) {
                    v2_7 = new n3s(new ud1(v7_1));
                }
                mgy v13_0;
                ngy v12_0;
                sgy v1_5;
                org.chromium.ui.modelutil.PropertyModel v0_11 = new java.util.ArrayList();
                if (!p21.isEmpty()) {
                    long v3_4;
                    epd v5_2 = new epd(v9_1, v7_1, p20);
                    if (!p24) {
                        v3_4 = tzy.duplicate_download_prompt_text;
                    } else {
                        v3_4 = tzy.duplicate_download_request_prompt_text;
                    }
                    v13_0 = v0_11;
                    v12_0 = v2_7;
                    v1_5 = org.chromium.chrome.browser.download.DownloadUtils.d(v7_1.getString(v3_4), p20, 0, 0, v5_2);
                } else {
                    ngy v12_2 = new cpd();
                    v12_2.a = v9_1;
                    epd v5_4 = new bpd();
                    v5_4.a = v12_2;
                    v5_4.b = p25;
                    v5_4.c = p20;
                    v1_5 = org.chromium.chrome.browser.download.DownloadUtils.d(v7_1.getString(tzy.duplicate_download_dialog_text), new java.io.File(p20).getName(), 1, p22, v5_4);
                    v13_0 = v0_11;
                    v12_0 = v2_7;
                }
                v13_0.add(v1_5);
                if (p25 != null) {
                    v13_0.add(v11.getString(tzy.download_location_incognito_warning));
                }
                sgy v1_1;
                org.chromium.ui.modelutil.PropertyModel v0_16 = new mgy(s3s.T);
                v0_16.d(s3s.a, 1);
                sgy v1_9 = new dpd();
                v1_9.a = v10_1;
                v1_9.b = v12_0;
                v1_9.c = v7_1;
                v0_16.f(s3s.b, v1_9);
                if (!p21.isEmpty()) {
                    v1_1 = tzy.duplicate_page_download_dialog_title;
                } else {
                    v1_1 = tzy.duplicate_download_dialog_title;
                }
                v0_16.e(s3s.d, v11, v1_1);
                v0_16.f(s3s.i, v13_0);
                v0_16.e(s3s.o, v11, tzy.duplicate_download_dialog_confirm_text);
                v0_16.e(s3s.r, v11, tzy.cancel);
                org.chromium.ui.modelutil.PropertyModel v0_1 = v0_16.a();
                v9_1.b = v0_1;
                v12_0.m(0, v0_1, 0);
                return;
            } else {
                if ((!p26) && (p27)) {
                    rge.a(new java.io.File(p20).getName(), p22, v10_1);
                    return;
                } else {
                    v10_1.onResult(Boolean.TRUE);
                    return;
                }
            }
        } else {
            J.N.VJJZ(1, this.a, p28, 0);
            return;
        }
    }
}
