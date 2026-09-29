// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/DownloadController
package org.chromium.chrome.browser.download;
public final class DownloadController {
    public static final um1 a;

    static DownloadController()
    {
        org.chromium.chrome.browser.download.DownloadController.a = new um1(0);
        return;
    }

    public static void enqueueAndroidDownloadManagerRequest(org.chromium.url.GURL p1, String p2, String p3, String p4, String p5, org.chromium.url.GURL p6)
    {
        r9d v0_1 = new r9d();
        v0_1.a = p1;
        v0_1.b = p2;
        v0_1.e = p3;
        v0_1.c = p4;
        v0_1.d = p5;
        v0_1.h = p6;
        v0_1.l = 1;
        yad v2_1 = v0_1.a();
        org.chromium.chrome.browser.download.DownloadManagerService v3_1 = org.chromium.chrome.browser.download.DownloadManagerService.a();
        org.chromium.chrome.browser.download.DownloadItem v4_2 = new org.chromium.chrome.browser.download.DownloadItem(1, v2_1);
        uad v5_2 = new uad();
        v5_2.a = v2_1.a.j();
        v5_2.b = v2_1.e;
        v5_2.c = v2_1.f;
        v5_2.d = v2_1.c;
        v5_2.e = v2_1.d;
        v5_2.f = v2_1.h.j();
        v5_2.g = v2_1.b;
        v5_2.h = 1;
        dgp v1_2 = new lbd();
        v1_2.a = v3_1;
        v1_2.b = v4_2;
        yad v2_4 = new yad();
        v2_4.a = v5_2;
        v2_4.b = v1_2;
        v2_4.executeOnExecutor(qu1.THREAD_POOL_EXECUTOR);
        return;
    }

    public static void onDownloadCancelled(org.chromium.chrome.browser.download.DownloadInfo p2)
    {
        int v0_0 = p2.s;
        if (v0_0 != 0) {
            int v0_1 = v0_0.b;
            if (v0_1 != 0) {
                org.chromium.chrome.browser.download.DownloadController.a.remove(v0_1);
            }
            dhe.b(5);
            if (p2.z == 0) {
                dhe.b(13);
            }
        }
        return;
    }

    public static void onDownloadCompleted(org.chromium.chrome.browser.tab.Tab p8, org.chromium.chrome.browser.download.DownloadInfo p9, boolean p10)
    {
        Object[] v10_0 = p9.s;
        String v0 = p9.g;
        String v1_2 = p9.z;
        String v2_2 = p9.j;
        if (v10_0 == null) {
            String v9_1 = p9.c;
            if ((!android.text.TextUtils.isEmpty(v0)) && ((v9_1 != null) && (v9_1.startsWith("image/")))) {
                String v1_1 = new ewq();
                v1_1.a = v0;
                v1_1.b = v9_1;
                b9d.a.a(v1_1);
            }
            if (p8 != null) {
                p8.isIncognito();
            }
        } else {
            Object[] v10_10 = v10_0.b;
            if (v10_10 != null) {
                org.chromium.chrome.browser.download.DownloadController.a.remove(v10_10);
            }
            Object[] v10_5 = v2_2 cmp 0;
            if (v10_5 != null) {
                dhe.b(4);
                String v3_1 = p9.e;
                if (org.chromium.chrome.browser.edge_pdf.EdgePdfUtils.isPdfFileFromFileName(v3_1)) {
                    String v4_2 = android.os.SystemClock.uptimeMillis();
                    if ((android.text.TextUtils.equals(v3_1, o4f.b)) && ((v4_2 - o4f.a) < 300000)) {
                        kkz.j(2, 6, "Microsoft.Mobile.Pdf.PdfDownloadOpenFunnel");
                    }
                }
                String v2_1 = p9.f;
                if (v2_1 == null) {
                    v2_1 = "";
                }
                if (!android.text.TextUtils.isEmpty(v2_1)) {
                    J.N.VO(22, v2_1);
                }
                if (v1_2 == null) {
                    dhe.b(11);
                }
            } else {
                dhe.b(6);
                if (v1_2 == null) {
                    dhe.b(15);
                }
            }
            if (v10_5 == null) {
            } else {
                if (v0 != null) {
                    if ((!com.microsoft.edge.managedbehavior.MAMEdgeManager.m()) || (com.microsoft.edge.managedbehavior.MAMEdgeManager.d.remove(v0))) {
                    } else {
                        String v1_6 = new java.io.File(v0);
                        if (!v1_6.exists()) {
                            v1_6 = new java.io.File(qpe.a(af9.a, android.net.Uri.parse(v0)));
                        }
                        String v2_8;
                        String v2_6 = bmj.b();
                        if ((v2_6 == null) || (!v2_6.isIncognito())) {
                            v2_8 = 0;
                        } else {
                            v2_8 = 1;
                        }
                        com.microsoft.intune.mam.client.identity.MAMFileProtectionManager.protectForOID(v1_6, com.microsoft.edge.managedbehavior.MAMEdgeManager.h(v2_8));
                    }
                }
            }
        }
        return;
    }

    public static void onDownloadUpdated(org.chromium.chrome.browser.download.DownloadInfo p3)
    {
        String v0_0 = p3.s;
        if (v0_0 != null) {
            String v0_4 = v0_0.b;
            if (v0_4 != null) {
                int v1_1 = org.chromium.chrome.browser.download.DownloadController.a;
                if (!v1_1.contains(v0_4)) {
                    v1_1.add(v0_4);
                    org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadManagerUKMHelper.recordFileFormat(p3.f);
                    dhe.b(1);
                    kkz.j(3, 4, "Microsoft.Mobile.DownloadManager.Hub.Show");
                    if (p3.z == 0) {
                        dhe.b(9);
                    }
                }
            }
        }
        return;
    }

    public static void onPdfDownloadStarted(org.chromium.chrome.browser.tab.Tab p0, org.chromium.chrome.browser.download.DownloadInfo p1)
    {
        p0.isIncognito();
        return;
    }
}
