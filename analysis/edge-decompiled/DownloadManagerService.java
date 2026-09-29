// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/DownloadManagerService
package org.chromium.chrome.browser.download;
public final class DownloadManagerService implements x6y, org.chromium.chrome.browser.edge_hub.downloads.EdgeBackendProvider$DownloadDelegate {
    public static final java.util.HashSet h;
    public static org.chromium.chrome.browser.download.DownloadManagerService i;
    public java.util.HashMap a;
    public sg60 b;
    public android.os.Handler c;
    public jxt d;
    public zcd e;
    public acd f;
    public long g;

    static DownloadManagerService()
    {
        org.chromium.chrome.browser.download.DownloadManagerService.h = new java.util.HashSet();
        return;
    }

    public static org.chromium.chrome.browser.download.DownloadManagerService a()
    {
        org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadDelegateImpl v0_0 = org.chromium.chrome.browser.download.DownloadManagerService.i;
        if (v0_0 != null) {
            return v0_0;
        } else {
            org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadDelegateImpl v0_6 = new sg60();
            java.util.ArrayList v2_1 = new android.os.Handler();
            org.chromium.chrome.browser.download.DownloadManagerService v1_1 = new org.chromium.chrome.browser.download.DownloadManagerService();
            v1_1.a = new java.util.HashMap(4, 1061158912);
            v1_1.d = new jxt();
            v1_1.b = v0_6;
            v1_1.c = v2_1;
            v1_1.e = new zcd();
            org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadDelegateImpl v0_4 = new org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadDelegateImpl();
            bs40 vtmp1 = bs40.e();
            org.chromium.components.download.DownloadCollectionBridge.b = v0_4;
            org.chromium.chrome.browser.download.DownloadManagerService.i = v1_1;
            return v1_1;
        }
    }

    public static android.content.Intent b(String p8, String p9, String p10, long p11, boolean p13, String p14)
    {
        if (p11 != -1) {
            java.io.File v11_7 = org.chromium.chrome.browser.download.DownloadManagerBridge.a(p11);
            if (p14 == null) {
                p14 = v11_7.a;
            }
            java.io.File v11_1;
            if (p8 != null) {
                v11_1 = org.chromium.chrome.browser.download.DownloadUtils.f(p8);
            } else {
                v11_1 = v11_7.b;
            }
            android.net.Uri v3_0 = v11_1;
            if ((v3_0 != null) && (!android.net.Uri.EMPTY.equals(v3_0))) {
                android.net.Uri v2_0;
                if (p8 != null) {
                    v2_0 = android.net.Uri.fromFile(new java.io.File(p8));
                } else {
                    v2_0 = v3_0;
                }
                if (!p13) {
                    return dyq.a(v3_0, p14, p9, p10);
                } else {
                    int v5_0 = (org.chromium.base.DeviceInfo.b() ^ 1);
                    return dyq.b(v2_0, v3_0, p14, v5_0, v5_0, af9.a);
                }
            }
        } else {
            if (org.chromium.base.ContentUriUtils.d(p8)) {
                android.net.Uri v2_1 = android.net.Uri.parse(p8);
                if (p14 == null) {
                    android.net.Uri v3_1 = v2_1;
                    android.content.Intent v8_7 = com.microsoft.intune.mam.client.content.MAMContentResolverManagement.query(af9.a.getContentResolver(), v3_1, 0, 0, 0, 0);
                    v2_1 = v3_1;
                    if (v8_7 != null) {
                        try {
                            if (v8_7.getCount() != 0) {
                                v8_7.moveToNext();
                                p14 = v8_7.getString(v8_7.getColumnIndexOrThrow("mime_type"));
                                v8_7.close();
                                v8_7.close();
                                if (!p13) {
                                    return dyq.a(v2_1, p14, p9, p10);
                                } else {
                                    int v5_2 = (org.chromium.base.DeviceInfo.b() ^ 1);
                                    return dyq.b(v2_1, v2_1, p14, v5_2, v5_2, af9.a);
                                }
                            } else {
                            }
                        } catch (Throwable v0_2) {
                            Throwable v9_1 = v0_2;
                            try {
                                v8_7.close();
                            } catch (Throwable v0_3) {
                                v9_1.addSuppressed(v0_3);
                            }
                            throw v9_1;
                        }
                    }
                    if (v8_7 != null) {
                        v8_7.close();
                        return 0;
                    }
                    return 0;
                }
            }
        }
        return 0;
    }

    public static void onDownloadItemCanceled(org.chromium.chrome.browser.download.DownloadItem p1, boolean p2)
    {
        int v2_1;
        org.chromium.chrome.browser.download.DownloadManagerService v0 = org.chromium.chrome.browser.download.DownloadManagerService.a();
        if (p2 == 0) {
            v2_1 = 1009;
        } else {
            v2_1 = 1007;
        }
        v0.d(p1, v2_1);
        return;
    }

    public static void openDownloadsPage(org.chromium.chrome.browser.profiles.OtrProfileId p3, int p4)
    {
        kkz.j(1, 4, "Microsoft.Mobile.DownloadManager.Hub.Show");
        org.chromium.chrome.browser.download.DownloadUtils.l(0, 0, p3, p4);
        return;
    }

    public final void addDownloadItemToList(java.util.List p1, org.chromium.chrome.browser.download.DownloadItem p2)
    {
        p1.add(p2);
        return;
    }

    public final void addDownloadObserver(nbd p1)
    {
        this.d.a(p1);
        xcd.a.b.a(p1);
        return;
    }

    public final void broadcastDownloadAction(org.chromium.chrome.browser.download.DownloadItem p3, String p4)
    {
        this = af9.a;
        this.startService(fcd.a(this, p4, jko.a(p3.a(), 0), p3.c.p));
        return;
    }

    public final long c()
    {
        if (this.g == 0) {
            long v0_3 = org.chromium.chrome.browser.profiles.ProfileManager.b;
            this.g = J.N.JOZ(0, this, v0_3);
            if (v0_3 == 0) {
                org.chromium.chrome.browser.profiles.ProfileManager.a(this);
            }
        }
        return this.g;
    }

    public final java.util.List createDownloadItemList()
    {
        this = new java.util.ArrayList;
        this();
        return this;
    }

    public final void d(org.chromium.chrome.browser.download.DownloadItem p5, int p6)
    {
        int v0_2;
        lv30 v4_1 = this.e;
        int v0_4 = p5.c.e;
        int v1_0 = af9.a;
        switch (p6) {
            case 1001:
                v0_2 = v1_0.getString(tzy.download_failed_reason_file_system_error, new Object[] {v0_4}));
                break;
            case 1002:
            case 1005:
                v0_2 = v1_0.getString(tzy.download_failed_reason_server_issues, new Object[] {v0_4}));
                break;
            case 1003:
            default:
                v0_2 = v1_0.getString(tzy.download_failed_reason_unknown_error, new Object[] {v0_4}));
                break;
            case 1004:
            case 1008:
                v0_2 = v1_0.getString(tzy.download_failed_reason_network_failures, new Object[] {v0_4}));
                break;
            case 1006:
                v0_2 = v1_0.getString(tzy.download_failed_reason_insufficient_space, new Object[] {v0_4}));
                break;
            case 1007:
                v0_2 = v1_0.getString(tzy.download_failed_reason_storage_not_found, new Object[] {v0_4}));
                break;
            case 1009:
                v0_2 = v1_0.getString(tzy.download_failed_reason_file_already_exists, new Object[] {v0_4}));
                break;
        }
        if (v4_1.a() == null) {
            p790.c(af9.a, v0_2, 0).e();
            return;
        } else {
            int v6_1;
            if (p6 != 1009) {
                v6_1 = 0;
            } else {
                v6_1 = 1;
            }
            rt30 v5_3 = org.chromium.chrome.browser.download.DownloadManagerService.a().f;
            if (((v5_3 == null) || (v5_3.m == null)) && (v4_1.a() != null)) {
                rt30 v5_7 = rt30.a(v0_2, v4_1, 1, 10);
                v5_7.g = 0;
                v5_7.i = 7000;
                if (v6_1 != 0) {
                    v5_7.e = af9.a.getString(uzy.open_downloaded_label);
                    v5_7.f = 0;
                }
                v4_1.a().t(v5_7);
                return;
            } else {
                return;
            }
        }
    }

    public final void f(org.chromium.chrome.browser.profiles.Profile p3)
    {
        org.chromium.chrome.browser.profiles.ProfileManager.c(this);
        J.N.VJO(57, this.g, p3);
        return;
    }

    public final void getAllDownloads(org.chromium.chrome.browser.profiles.OtrProfileId p3)
    {
        J.N.VJO(56, this.c(), nkm.a(p3));
        return;
    }

    public final void h(org.chromium.chrome.browser.profiles.Profile p1)
    {
        return;
    }

    public final boolean isDownloadOpenableInBrowser(String p1, boolean p2)
    {
        if (!"application/pdf".equalsIgnoreCase(p1)) {
            return J.N.ZO(25, p1);
        } else {
            return 0;
        }
    }

    public final void onAllDownloadsRetrieved(java.util.List p3, org.chromium.chrome.browser.profiles.ProfileKey p4)
    {
        org.chromium.components.prefs.PrefService v4_2 = this.d.iterator();
        while(true) {
            d9d v0_6 = ((ixt) v4_2);
            if (!v0_6.hasNext()) {
                break;
            }
            ((nbd) v0_6.next()).getClass();
        }
        org.chromium.components.prefs.PrefService v4_1 = d3b0.b(org.chromium.chrome.browser.profiles.ProfileManager.b());
        if (v4_1.b("download.show_missing_sd_card_error_android")) {
            d9d v0_2 = b9d.a;
            kbd v1_1 = new kbd();
            v1_1.a = this;
            v1_1.b = v0_2;
            v1_1.c = p3;
            v1_1.d = v4_1;
            v0_2.a(v1_1);
            return;
        } else {
            return;
        }
    }

    public final void onDownloadItemCreated(org.chromium.chrome.browser.download.DownloadItem p3)
    {
        dhe.b(0);
        if (p3) {
            boolean v3_7 = p3.c;
            if (v3_7) {
                if (v3_7.z == 0) {
                    dhe.b(7);
                }
                kkz.j(0, 3, "Microsoft.Mobile.DownloadManager.RequestInitialAction");
                if ((org.chromium.chrome.browser.edge_signin.account.EdgeAccountManager.b() != null) && (org.chromium.chrome.browser.edge_signin.account.EdgeAccountManager.b().l())) {
                    if (!com.microsoft.edge.managedbehavior.MAMEdgeManager.m()) {
                        kkz.c("Microsoft.Mobile.DownloadManager.AADProtection.PolicyEnabled", 0);
                    } else {
                        kkz.c("Microsoft.Mobile.DownloadManager.AADProtection.PolicyEnabled", 1);
                        kkz.c("Microsoft.Mobile.DownloadManager.AADProtection.AllowDownloadToLocal", com.microsoft.edge.managedbehavior.MAMEdgeManager.isSaveToLocalAllowed());
                        kkz.c("Microsoft.Mobile.DownloadManager.AADProtection.AllowDownloadToOneDrive", com.microsoft.edge.managedbehavior.MAMEdgeManager.o());
                        kkz.c("Microsoft.Mobile.DownloadManager.AADProtection.AllowDownloadToSharePoint", com.microsoft.edge.managedbehavior.MAMEdgeManager.p());
                    }
                }
            }
        }
        java.util.Iterator v2_2 = this.d.iterator();
        while(true) {
            boolean v3_11 = ((ixt) v2_2);
            if (!v3_11.hasNext()) {
                break;
            }
            ((nbd) v3_11.next()).getClass();
        }
        return;
    }

    public final void onDownloadItemRemoved(String p2)
    {
        java.util.Iterator v1_2 = this.d.iterator();
        while(true) {
            nbd v2_3 = ((ixt) v1_2);
            if (!v2_3.hasNext()) {
                break;
            }
            ((nbd) v2_3.next()).getClass();
        }
        return;
    }

    public final void onDownloadItemUpdated(org.chromium.chrome.browser.download.DownloadItem p5)
    {
        java.util.Iterator v4_2 = this.d.iterator();
        do {
            String v0_6 = ((ixt) v4_2);
            if (!v0_6.hasNext()) {
                return;
            } else {
                ((h4f) ((nbd) v0_6.next())).getClass();
                String v0_3 = p5.c;
                if (v0_3 != null) {
                    org.chromium.url.GURL v1_0 = v0_3.i;
                }
            }
        } while((v0_3.q != 1) || (!android.text.TextUtils.equals(v0_3.c, "application/pdf")));
        if (android.text.TextUtils.isEmpty(v1_0.j())) {
            v1_0 = v0_3.a;
        }
        J.N.VOO(87, v1_0, "Microsoft.Mobile.Pdf.PageUrlRecord");
    }

    public final void onResumptionFailed(String p6)
    {
        sg60 v0 = this.b;
        org.chromium.chrome.browser.download.DownloadInfo v1_1 = new r9d();
        v1_1.m = p6;
        v1_1.E = 1;
        org.chromium.chrome.browser.download.DownloadInfo v1_2 = v1_1.a();
        v0.getClass();
        v0.a(new rg60(3, v1_2, 0));
        this.a.remove(p6);
        org.chromium.chrome.browser.download.DownloadManagerService.h.remove(p6);
        return;
    }

    public final void openDownloadItem(org.chromium.chrome.browser.download.DownloadItem p8, int p9)
    {
        boolean v7_0 = p8.c;
        String v0 = v7_0.g;
        String v1 = v7_0.c;
        String v2 = v7_0.l;
        org.chromium.chrome.browser.profiles.OtrProfileId v3 = v7_0.p;
        String v4_1 = v7_0.i.j();
        String v5_0 = v7_0.h.j();
        boolean v7_1 = v7_0.e;
        jcd v6_1 = new jcd();
        v6_1.a = v0;
        v6_1.b = v1;
        v6_1.c = v2;
        v6_1.d = v3;
        v6_1.e = v4_1;
        v6_1.f = v5_0;
        v6_1.g = p9;
        v6_1.h = af9.a;
        v6_1.i = v7_1;
        if (!org.chromium.chrome.browser.download.DownloadUtils.i(v6_1)) {
            org.chromium.chrome.browser.download.DownloadManagerService.openDownloadsPage(v3, p9);
        }
        return;
    }

    public final void removeDownload(String p2, org.chromium.chrome.browser.profiles.OtrProfileId p3, boolean p4)
    {
        p4 = this.c;
        ibd v0_1 = new ibd();
        v0_1.a = this;
        v0_1.b = p2;
        v0_1.c = p3;
        p4.post(v0_1);
        return;
    }

    public final void removeDownloadObserver(nbd p1)
    {
        this.d.f(p1);
        xcd.a.b.f(p1);
        return;
    }

    public final void updateLastAccessTime(String p3, org.chromium.chrome.browser.profiles.OtrProfileId p4)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            J.N.VJOO(18, this.c(), p3, nkm.a(p4));
            return;
        } else {
            return;
        }
    }
}
