// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/DangerousDownloadDialogBridge
package org.chromium.chrome.browser.download;
public final class DangerousDownloadDialogBridge {
    public long a;

    public static org.chromium.chrome.browser.download.DangerousDownloadDialogBridge create(long p1)
    {
        org.chromium.chrome.browser.download.DangerousDownloadDialogBridge v0_1 = new org.chromium.chrome.browser.download.DangerousDownloadDialogBridge();
        v0_1.a = p1;
        return v0_1;
    }

    public final void destroy()
    {
        this.a = 0;
        return;
    }

    public final void showDialog(org.chromium.ui.base.WindowAndroid p19, String p20, String p21, long p22, String p24, int p25, boolean p26)
    {
        int v7_0 = ((android.app.Activity) p19.x().get());
        if ((v7_0 instanceof o3s)) {
            int v8_5 = ((o3s) v7_0).getModalDialogManager();
            mgy v9_1 = new d6b();
            v9_1.a = this;
            v9_1.b = p20;
            v9_1.c = p19;
            if (!bhe.b()) {
                int v16_2;
                String v0_11 = v7_0.getResources();
                int v1_6 = new c6b();
                v1_6.a = v9_1;
                v1_6.b = v8_5;
                v1_6.c = p26;
                mgy v9_3 = new mgy(s3s.T);
                v9_3.f(s3s.b, v1_6);
                v9_3.f(s3s.r, v0_11.getString(tzy.cancel));
                v9_3.b();
                int v1_9 = s3s.I;
                sgy v10_3 = s3s.o;
                sgy v13 = s3s.i;
                int v14_3 = s3s.d;
                if (!p26) {
                    java.util.ArrayList v3_10;
                    v16_2 = v8_5;
                    v9_3.f(v14_3, v0_11.getString(uzy.non_dangerous_download_dialog_title));
                    if (!p24.isEmpty()) {
                        int v2_20 = v7_0.getString(uzy.non_dangerous_download_dialog_text_with_domain);
                        java.util.ArrayList v3_6 = new java.util.ArrayList();
                        int v4_3 = new android.text.SpannableString(p24);
                        v4_3.setSpan(new android.text.style.ForegroundColorSpan(t820.h(v7_0)), 0, p24.length(), 33);
                        v3_6.add(v4_3);
                        int v4_4 = new CharSequence[0];
                        v3_10 = new java.util.ArrayList(java.util.List.of(android.text.TextUtils.expandTemplate(v2_20, ((CharSequence[]) v3_6.toArray(v4_4)))));
                    } else {
                        v3_10 = new java.util.ArrayList(java.util.List.of(v7_0.getString(uzy.non_dangerous_download_dialog_text)));
                    }
                    v9_3.f(v13, v3_10);
                    v9_3.f(v10_3, v0_11.getString(uzy.non_dangerous_download_dialog_confirm_text));
                    v9_3.d(v1_9, 1);
                } else {
                    int v12_2;
                    v9_3.f(v14_3, v0_11.getString(tzy.dangerous_download_dialog_title));
                    if (p22 <= 0) {
                        v12_2 = 0;
                    } else {
                        v12_2 = 1;
                    }
                    int v16_1;
                    int v14_0 = p24.isEmpty();
                    if ((v12_2 == 0) || (v14_0 != 0)) {
                        if (v12_2 == 0) {
                            if (v14_0 != 0) {
                                v16_1 = tzy.dangerous_download_dialog_text;
                            } else {
                                v16_1 = tzy.dangerous_download_dialog_text_with_domain;
                            }
                        } else {
                            v16_1 = tzy.dangerous_download_dialog_text_with_size;
                        }
                    } else {
                        v16_1 = tzy.dangerous_download_dialog_text_with_size_and_domain;
                    }
                    String v15_1 = v7_0.getString(v16_1);
                    java.util.ArrayList v11_1 = new java.util.ArrayList();
                    android.text.SpannableString v6_1 = new android.text.SpannableString(p21);
                    p25 = v12_2;
                    v16_2 = v8_5;
                    boolean v20_1 = v14_0;
                    v6_1.setSpan(new android.text.style.StyleSpan(1), 0, p21.length(), 33);
                    v11_1.add(v6_1);
                    if (p25 != 0) {
                        v11_1.add(jdd.b(v7_0, jdd.a, p22));
                    }
                    int v8_4;
                    if (v20_1) {
                        v8_4 = 0;
                    } else {
                        int v2_5 = new android.text.SpannableString(p24);
                        v8_4 = 0;
                        v2_5.setSpan(new android.text.style.ForegroundColorSpan(t820.h(v7_0)), 0, p24.length(), 33);
                        v11_1.add(v2_5);
                    }
                    int v2_6 = new CharSequence[v8_4];
                    v9_3.f(v13, new java.util.ArrayList(java.util.List.of(android.text.TextUtils.expandTemplate(v15_1, ((CharSequence[]) v11_1.toArray(v2_6))))));
                    v9_3.f(v10_3, v0_11.getString(tzy.dangerous_download_dialog_confirm_text));
                    v9_3.d(v1_9, v8_4);
                }
                String v0_2;
                if (!p26) {
                    v0_2 = "Download.NonDangerousDialog.Events";
                } else {
                    v0_2 = "Download.DangerousDialog.Events";
                }
                kkz.j(0, 4, v0_2);
                v16_2.m(0, v9_3.a(), 0);
                return;
            } else {
                String v0_6;
                if (p24.isEmpty()) {
                    v0_6 = v7_0.getResources().getString(tzy.edge_download_harmful_message, new Object[] {p21}));
                } else {
                    v0_6 = v7_0.getResources().getString(tzy.edge_download_harmful_message_with_domain, new Object[] {p21, p24}));
                }
                rge.a(v0_6, p22, v9_1);
                return;
            }
        } else {
            J.N.VJO(55, this.a, p20);
            k5t.d2(p19);
            return;
        }
    }
}
