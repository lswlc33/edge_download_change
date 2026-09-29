// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/EdgeOneDriveDownloadBridge
package org.chromium.chrome.browser.download;
public final class EdgeOneDriveDownloadBridge {
    public long a;
    public String b;
    public boolean c;

    public static int a(int p0, boolean p1)
    {
        switch (p0) {
            case 0:
                if (!p1) {
                    return 9;
                } else {
                    return 3;
                }
            case 1:
                if (!p1) {
                    return 11;
                } else {
                }
            case 2:
                return 12;
            case 3:
                return 13;
            case 4:
                return 14;
            case 5:
                return 15;
            case 6:
                return 16;
            case 7:
                return 17;
            case 8:
                return 18;
            case 9:
                if (!p1) {
                    return 19;
                } else {
                    return 1;
                }
            case 10:
                if (!p1) {
                    return 20;
                } else {
                }
            default:
                return 10;
        }
        return 4;
    }

    public static org.chromium.chrome.browser.download.EdgeOneDriveDownloadBridge create(long p1)
    {
        org.chromium.chrome.browser.download.EdgeOneDriveDownloadBridge v0_1 = new org.chromium.chrome.browser.download.EdgeOneDriveDownloadBridge();
        v0_1.a = p1;
        return v0_1;
    }

    public final void b(org.chromium.chrome.browser.tab.Tab p3, String p4, boolean p5)
    {
        ly6 v3_2;
        if (p3 != null) {
            v3_2 = ly6.E2(p3.getWebContents());
        } else {
            v3_2 = 0;
        }
        if (v3_2 != null) {
            ooc v4_1;
            if (p5 == null) {
                v4_1 = v3_2.getString(uzy.onedrive_failed_to_save_title);
            } else {
                v4_1 = v3_2.getString(uzy.onedrive_saved_title, new Object[] {p4}));
            }
            android.text.TextUtils$TruncateAt v0_4;
            v3_2.findViewById(16908290);
            ooc v4_3 = v3_2.I0.b(5000, v4_1);
            if (p5 == null) {
                v0_4 = izy.onedrive_upload_failed_icon;
            } else {
                v0_4 = izy.onedrive_upload_done_icon;
            }
            v4_3.u(v0_4);
            if (p5 != null) {
                android.text.TextUtils$TruncateAt v0_6 = new d1f();
                v0_6.a = this;
                v0_6.b = v3_2;
                v4_3.v(uzy.onedrive_upload_done_check_button_text, v0_6);
                b1f v5_2 = v4_3.r();
                if (v5_2 != null) {
                    v5_2.setTextMaxLines(2);
                }
                b1f v5_3 = v4_3.r();
                if (v5_3 != null) {
                    v5_3.setTextEllipsize(android.text.TextUtils$TruncateAt.MIDDLE);
                }
                b1f v5_5 = new b1f(this, v3_2);
                com.microsoft.edge.dewey.snackbar.DeweySnackbarContentLayout v2_1 = v4_3.r();
                if (v2_1 != null) {
                    v2_1.setActionViewAccessibilityDelegate(v5_5);
                }
            }
            v4_3.o();
            return;
        } else {
            return;
        }
    }

    public final void destroy()
    {
        this.a = 0;
        return;
    }

    public final boolean isAADAndAPPProtected()
    {
        return com.microsoft.edge.managedbehavior.MAMEdgeManager.m();
    }

    public final void notifyDownloadFinished(org.chromium.chrome.browser.tab.Tab p1, String p2, String p3, String p4, String p5, String p6, long p7, boolean p9, int p10)
    {
        kkz.j(org.chromium.chrome.browser.download.EdgeOneDriveDownloadBridge.a(p10, 1), 21, "Microsoft.Mobile.SaveToOneDrive.TaskState");
        if (p10 != 0) {
            this.b(p1, p2, p9);
        }
        if ((p1.getContext() != null) && ((android.text.TextUtils.equals("application/pdf", p4)) && ((!android.text.TextUtils.isEmpty(p5)) && (!android.text.TextUtils.isEmpty(p2))))) {
            dhe.c(9);
            String v0_7 = org.chromium.chrome.browser.download.DownloadUtils.e(p5).toString();
            if (org.chromium.base.ContentUriUtils.d(v0_7)) {
                z0f v3_4 = new z0f();
                v3_4.a = v0_7;
                org.chromium.chrome.browser.edge_pdf.EdgePdfViewerFragment.w = v3_4;
            }
            org.chromium.chrome.browser.edge_pdf.EdgePdfUtils.openPDFByOffline(((android.app.Activity) p1.getContext()), p2, v0_7);
        }
        return;
    }

    public final void notifyDownloadStarted(org.chromium.chrome.browser.tab.Tab p1, String p2)
    {
        kkz.j(0, 21, "Microsoft.Mobile.SaveToOneDrive.TaskState");
        return;
    }

    public final void notifyUploadFinished(org.chromium.chrome.browser.tab.Tab p3, String p4, boolean p5, int p6)
    {
        kkz.j(org.chromium.chrome.browser.download.EdgeOneDriveDownloadBridge.a(p6, 0), 21, "Microsoft.Mobile.SaveToOneDrive.TaskState");
        this.b(p3, p4, p5);
        return;
    }

    public final void notifyUploadStarted(org.chromium.chrome.browser.tab.Tab p1, String p2)
    {
        kkz.j(6, 21, "Microsoft.Mobile.SaveToOneDrive.TaskState");
        return;
    }

    public final void queryUserChoice(String p6, org.chromium.chrome.browser.tab.Tab p7, String p8, boolean p9, long p10, String p12)
    {
        long v7_2;
        if (p7 != 0) {
            v7_2 = ly6.E2(p7.getWebContents());
        } else {
            v7_2 = 0;
        }
        if (v7_2 != 0) {
            boolean v9_0;
            boolean v9_16 = com.microsoft.edge.managedbehavior.MAMEdgeManager.o();
            boolean v0_0 = com.microsoft.edge.managedbehavior.MAMEdgeManager.isSaveToLocalAllowed();
            int v1_0 = 2;
            int v3 = 0;
            if ((!v9_16) || (!v0_0)) {
                if (!v9_16) {
                    v9_0 = 2;
                } else {
                    v9_0 = 1;
                }
            } else {
                v9_0 = 0;
            }
            kkz.j(v9_0, 3, "Microsoft.Mobile.SaveAsOptionsPanel.Impression");
            if (h27.b.h("msEdgeMobileOpenInM365App")) {
                if ((!android.text.TextUtils.equals(p8, "application/msword")) && (!android.text.TextUtils.equals(p8, "application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
                    if ((!android.text.TextUtils.equals(p8, "application/vnd.ms-excel")) && (!android.text.TextUtils.equals(p8, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) {
                        if ((!android.text.TextUtils.equals(p8, "application/vnd.ms-powerpoint")) && (!android.text.TextUtils.equals(p8, "application/vnd.openxmlformats-officedocument.presentationml.presentation"))) {
                            v1_0 = 0;
                        } else {
                            v1_0 = 3;
                        }
                    }
                } else {
                    v1_0 = 1;
                }
                v3 = v1_0;
            }
            boolean v9_15 = com.microsoft.edge.managedbehavior.MAMEdgeManager.o();
            boolean v0_3 = com.microsoft.edge.managedbehavior.MAMEdgeManager.isSaveToLocalAllowed();
            boolean v8_1 = android.text.TextUtils.equals("application/pdf", p8);
            int v1_3 = new a1f();
            v1_3.a = p6;
            v1_3.b = p10;
            v1_3.c = v7_2;
            v1_3.d = v3;
            v1_3.e = p12;
            v1_3.f = this;
            g1f v5_1 = new g1f;
            v5_1(v7_2, com.microsoft.edge.dewey.popup.sheet.DeweyBottomSheet$Style.a);
            v5_1.v = v7_2;
            v5_1.x = v1_3;
            v5_1.l = v9_15;
            v5_1.m = v0_3;
            v5_1.n = v8_1;
            v5_1.s = v3;
            if ((!v0_3) && ((!v9_15) && (!v8_1))) {
                v5_1.setContentView(ozy.edge_download_blocked_prompt);
            } else {
                v5_1.setContentView(ozy.edge_download_to_onedrive_prompt);
            }
            v5_1.show();
            return;
        } else {
            long v7_3 = this.a;
            if (v7_3 != 0) {
                J.N.VJO(59, v7_3, p6);
            }
            return;
        }
    }

    public final void setOneDriveFolderInfo(boolean p1, String p2)
    {
        this.c = p1;
        this.b = p2;
        return;
    }
}
