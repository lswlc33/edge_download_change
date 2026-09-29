// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/DownloadUtils
package org.chromium.chrome.browser.download;
public final class DownloadUtils {
    public static long a;
    public static String b;

    public static boolean a(jcd p23)
    {
        int v2_3 = p23.b;
        android.content.Context v3 = p23.h;
        boolean v4_7 = p23.a;
        String v5 = p23.c;
        org.chromium.chrome.browser.profiles.OtrProfileId v6 = p23.d;
        if ("application/pdf".equals(v2_3)) {
            org.chromium.chrome.browser.download.DownloadManagerService v7_0 = p23.i;
            dhe.c(1);
            int v10_0 = org.chromium.base.ApplicationStatus.d;
            if ((v10_0 == 0) || (android.text.TextUtils.isEmpty(v7_0))) {
                dhe.c(3);
            } else {
                org.chromium.chrome.browser.edge_pdf.EdgePdfUtils.openPDFByOffline(v10_0, v7_0, org.chromium.chrome.browser.download.DownloadUtils.e(v4_7).toString());
                return 1;
            }
        }
        String v11_1;
        org.chromium.chrome.browser.download.DownloadManagerService v7_1 = org.chromium.chrome.browser.download.DownloadManagerService.a();
        if (v6 == null) {
            v11_1 = 0;
        } else {
            v11_1 = 1;
        }
        boolean v4_4;
        h27 v12 = h27.b;
        if (!v12.h("OpenDownloadInPreferredApp")) {
            if ((!v7_1.isDownloadOpenableInBrowser(v2_3, v11_1)) || (!org.chromium.chrome.browser.download.DownloadUtils.k(p23))) {
                if (!org.chromium.chrome.browser.download.DownloadUtils.j(p23.a, p23.b, p23.e, p23.f, 0, p23.h)) {
                    v4_4 = 1;
                } else {
                    v7_1.updateLastAccessTime(v5, v6);
                    return 1;
                }
            } else {
                v7_1.updateLastAccessTime(v5, v6);
                dhe.c(4);
                return 1;
            }
        } else {
            boolean v4_10;
            if (!org.chromium.base.ContentUriUtils.d(v4_7)) {
                v4_10 = org.chromium.chrome.browser.download.DownloadUtils.f(v4_7);
            } else {
                v4_10 = android.net.Uri.parse(v4_7);
            }
            int v9_6;
            boolean v4_11 = dyq.a(v4_10, v2_3, p23.e, p23.f);
            String v13_4 = v3.getPackageName();
            String v15_2 = hvu.d(65536, v4_11);
            if (v15_2 == null) {
                v9_6 = 0;
            } else {
                int v9_5 = v15_2.activityInfo;
                if (v9_5 == 0) {
                } else {
                    v9_6 = v9_5.packageName;
                }
            }
            if (!v13_4.equals(v9_6)) {
                if (v9_6 != 0) {
                    if (v15_2 == null) {
                        kkz.j(3, 4, "Android.Download.OpenTarget");
                    } else {
                        int v9_7 = v15_2.activityInfo;
                        if (v9_7 != 0) {
                            int v9_8 = v9_7.name;
                            if ((!"android".equals(v9_7.packageName)) && ((v9_8 == 0) || (!v9_8.contains("ResolverActivity")))) {
                                kkz.j(2, 4, "Android.Download.OpenTarget");
                            }
                        }
                    }
                    if (org.chromium.chrome.browser.download.DownloadUtils.c(v3, v4_11)) {
                        v7_1.updateLastAccessTime(v5, v6);
                        return 1;
                    }
                }
            } else {
                if ((v7_1.isDownloadOpenableInBrowser(v2_3, v11_1)) && (org.chromium.chrome.browser.download.DownloadUtils.k(p23))) {
                    kkz.j(0, 4, "Android.Download.OpenTarget");
                    v7_1.updateLastAccessTime(v5, v6);
                    dhe.c(4);
                    return 1;
                }
            }
            if ((!v7_1.isDownloadOpenableInBrowser(v2_3, v11_1)) || (!org.chromium.chrome.browser.download.DownloadUtils.k(p23))) {
                v4_4 = 1;
            } else {
                kkz.j(1, 4, "Android.Download.OpenTarget");
                v7_1.updateLastAccessTime(v5, v6);
                dhe.c(4);
                return 1;
            }
        }
        try {
            if (("application/zip".equals(v2_3)) && ((!v12.h("OpenDownloadInFilesAppIfNoHandlerFound")) && (com.microsoft.intune.mam.client.content.pm.MAMPackageManagement.getPackageInfo(v3.getPackageManager(), "com.android.documentsui", v4_4) != null))) {
                boolean v0_10 = new android.content.Intent("android.intent.action.VIEW_DOWNLOADS");
                v0_10.addFlags(268435456);
                v0_10.setPackage("com.android.documentsui");
                v3.startActivity(v0_10);
                return 1;
            }
        } catch (boolean v0_8) {
            android.util.Log.e("cr_download", "Cannot find files app for opening zip files", v0_8);
        }
        return 0;
    }

    public static void b(android.content.Context p9, org.chromium.chrome.browser.tab.Tab p10, boolean p11)
    {
        if (p10 != null) {
            rv90 v0_1 = sv90.a(p10.getProfile());
            int v1_2 = p10.C();
            if ((v1_2 == 0) || (!v1_2.h())) {
                int v11_2 = new i1u(p9, p10);
                if ((!p10.O()) || (p10.isIncognito())) {
                    J.N.VOO(41, p10, v11_2.a());
                } else {
                    J.N.VIJOOOO(0, 1, org.chromium.chrome.browser.offlinepages.OfflinePageBridge.a(p10.getProfile()).a, p10.getWebContents(), "async_loading", p10.getUrl().j(), v11_2.a());
                }
                v0_1.notifyEvent("download_page_started");
                return;
            } else {
                J.N.VOO(18, p10.getUrl().j(), p10.getWebContents());
                if (p11 != 0) {
                    v0_1.notifyEvent("app_menu_pdf_page_downloaded");
                }
            }
        }
        return;
    }

    public static boolean c(android.content.Context p3, android.content.Intent p4)
    {
        if (!android.text.TextUtils.equals(p4.getPackage(), p3.getPackageName())) {
            p3.startActivity(p4);
        } else {
            y5n.C(0, p4, 0);
        }
        return 1;
    }

    public static CharSequence d(String p6, String p7, boolean p8, long p9, android.text.style.ClickableSpan p11)
    {
        android.text.SpannableString v0_1 = new android.text.SpannableString(p7);
        v0_1.setSpan(new android.text.style.StyleSpan(1), 0, p7.length(), 33);
        v0_1.setSpan(p11, 0, p7.length(), 33);
        if (p8 != null) {
            String v7_4;
            if (p9 <= 0) {
                v7_4 = "";
            } else {
                String v7_6 = jdd.b(af9.a, jdd.a, p9);
                CharSequence[] v8_3 = new StringBuilder(" (");
                v8_3.append(v7_6);
                v8_3.append(")");
                v7_4 = v8_3.toString();
            }
            CharSequence[] v8_5 = new CharSequence[2];
            v8_5[0] = v0_1;
            v8_5[1] = v7_4;
            return android.text.TextUtils.expandTemplate(p6, v8_5);
        } else {
            String v7_8 = new CharSequence[1];
            v7_8[0] = v0_1;
            return android.text.TextUtils.expandTemplate(p6, v7_8);
        }
    }

    public static android.net.Uri e(String p1)
    {
        if (!org.chromium.base.ContentUriUtils.d(p1)) {
            if (!d9d.d(p1)) {
                return org.chromium.base.FileUtils.c(new java.io.File(p1));
            } else {
                return org.chromium.chrome.browser.download.DownloadFileProvider.g(p1);
            }
        } else {
            return android.net.Uri.parse(p1);
        }
    }

    public static android.net.Uri f(String p2)
    {
        if (!org.chromium.base.PathUtils.getManagedDownloadsDirectory().equals(new java.io.File(p2).getParent())) {
            return org.chromium.chrome.browser.download.DownloadUtils.e(p2);
        } else {
            return org.chromium.chrome.browser.download.DownloadUtils.e(p2);
        }
    }

    public static boolean g(org.chromium.chrome.browser.tab.Tab p3)
    {
        if (((p3 != 0) && ((!p3.isIncognito()) || (h27.b.h("EnableSavePackageForOffTheRecord")))) && ((!J.N.ZO(26, p3.getProfile().f())) && (J.N.ZO(68, p3.getUrl())))) {
            if (!p3.O()) {
                if (!org.chromium.chrome.browser.offlinepages.b.e(p3)) {
                    return 1;
                }
            } else {
                long v0_8 = org.chromium.chrome.browser.offlinepages.OfflinePageBridge.a(p3.getProfile());
                if (v0_8 != 0) {
                    return J.N.ZJO(24, v0_8.a, p3.getWebContents());
                }
            }
        }
        return 0;
    }

    public static String getUriStringForPath(String p1)
    {
        if (!org.chromium.base.ContentUriUtils.d(p1)) {
            String v1_2 = org.chromium.chrome.browser.download.DownloadUtils.e(p1);
            if (v1_2 == null) {
                return new String();
            } else {
                return v1_2.toString();
            }
        } else {
            return p1;
        }
    }

    public static boolean h(int p2)
    {
        if ((p2 == 58) || (p2 == 59)) {
            if (android.os.Build$VERSION.SDK_INT < 34) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public static boolean i(jcd p13)
    {
        int v6_1;
        String v0 = p13.c;
        android.content.Context v1 = p13.h;
        String v2 = p13.i;
        org.chromium.chrome.browser.profiles.OtrProfileId v3_0 = p13.b;
        int v4 = p13.g;
        String v5 = p13.a;
        if (v0 == null) {
            v6_1 = v5;
        } else {
            v6_1 = v0;
        }
        boolean v7_0 = android.os.SystemClock.elapsedRealtime();
        boolean v9_0 = org.chromium.chrome.browser.download.DownloadUtils.b;
        if ((!v9_0) || ((!v9_0.equals(v6_1)) || ((v7_0 - org.chromium.chrome.browser.download.DownloadUtils.a) >= ((long) android.view.ViewConfiguration.getDoubleTapTimeout())))) {
            org.chromium.chrome.browser.download.DownloadUtils.a = v7_0;
            org.chromium.chrome.browser.download.DownloadUtils.b = v6_1;
            ccd.a(v4, v3_0);
            dhe.c(0);
            boolean v7_1 = org.chromium.chrome.browser.download.DownloadUtils.a(p13);
            if (!v7_1) {
                jcd v8_1;
                if (android.text.TextUtils.isEmpty(v2)) {
                    v8_1 = v5;
                } else {
                    v8_1 = v2;
                }
                jcd v8_2 = org.chromium.base.FileUtils.b(v8_1);
                jcd v10 = 0;
                if (!android.text.TextUtils.isEmpty(v8_2)) {
                    jcd v8_4 = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(v8_2.toLowerCase(java.util.Locale.ROOT));
                    if ((!android.text.TextUtils.isEmpty(v8_4)) && (!android.text.TextUtils.equals(v8_4, v3_0))) {
                        v10 = v8_4;
                    }
                }
                if (v10 != null) {
                    org.chromium.chrome.browser.profiles.OtrProfileId v3_2 = p13.d;
                    boolean v7_2 = p13.e;
                    String v13_1 = p13.f;
                    jcd v8_6 = new jcd();
                    v8_6.a = v5;
                    v8_6.b = v10;
                    v8_6.c = v0;
                    v8_6.d = v3_2;
                    v8_6.e = v7_2;
                    v8_6.f = v13_1;
                    v8_6.g = v4;
                    v8_6.h = v1;
                    v8_6.i = v2;
                    v7_1 = org.chromium.chrome.browser.download.DownloadUtils.a(v8_6);
                }
            }
            if ((!v7_1) && (v4 != 8)) {
                p790.c(v1, v1.getString(tzy.download_cant_open_file), 0).e();
            }
            return v7_1;
        } else {
            return 1;
        }
    }

    public static boolean j(String p1, String p2, String p3, String p4, int p5, android.content.Context p6)
    {
        try {
            int v1_5;
            if (!org.chromium.base.ContentUriUtils.d(p1)) {
                v1_5 = org.chromium.chrome.browser.download.DownloadUtils.f(p1);
            } else {
                v1_5 = android.net.Uri.parse(p1);
            }
        } catch (int v1_6) {
            android.util.Log.e("cr_download", "Cannot start activity to open file", v1_6);
            return 0;
        }
        p6.startActivity(dyq.a(v1_5, p2, p3, p4));
        kkz.j(p5, 4, "Download.OpenDownloads.OpenWithExternalAppsSource");
        return 1;
    }

    public static boolean k(jcd p11)
    {
        android.content.Intent v0_0 = p11.b;
        int v2_0 = p11.a;
        if (!"application/pdf".equalsIgnoreCase(v0_0)) {
            android.net.Uri v5;
            android.net.Uri v6 = org.chromium.chrome.browser.download.DownloadUtils.e(v2_0);
            if (org.chromium.base.ContentUriUtils.d(v2_0)) {
                v5 = v6;
            } else {
                v5 = android.net.Uri.fromFile(new java.io.File(v2_0));
            }
            String v7 = android.content.Intent.normalizeMimeType(v0_0);
            int v8 = (org.chromium.base.DeviceInfo.b() ^ 1);
            y5n.C(p11.h, dyq.b(v5, v6, v7, v8, v8, p11.h), 0);
            return 1;
        } else {
            android.content.Intent v0_5 = org.chromium.chrome.browser.pdf.PdfUtils.c(org.chromium.chrome.browser.download.DownloadUtils.e(v2_0).toString());
            int v1_6 = 0;
            if (v0_5 == null) {
                return 0;
            } else {
                int v2_2 = new org.chromium.content_public.browser.LoadUrlParams(v0_5, 0);
                if (p11.d != null) {
                    v1_6 = 1;
                }
                y5n.C(0, y5n.b(new eu1(v2_2, 0, 0), -1, 2, v1_6), 0);
                return 1;
            }
        }
    }

    public static void l(android.app.Activity p5, org.chromium.chrome.browser.tab.Tab p6, org.chromium.chrome.browser.profiles.OtrProfileId p7, int p8)
    {
        if (p5 == null) {
            p5 = org.chromium.base.ApplicationStatus.d;
        }
        int v0_0 = af9.a;
        if ((p6 == null) && ((p5 instanceof org.chromium.chrome.browser.ChromeTabbedActivity))) {
            p6 = ((org.chromium.chrome.browser.ChromeTabbedActivity) p5).F2();
        }
        long v1_1 = new android.content.Intent(v0_0, org.chromium.chrome.browser.ChromeTabbedActivity);
        int v3 = 0;
        v1_1.putExtra("org.chromium.chrome.browser.download.SHOW_PREFETCHED_CONTENT", 0);
        v1_1.putExtra("com.microsoft.edge.open_hub", org.chromium.chrome.browser.edge_hub.EdgeHubManager$PageType.c.a());
        if (p7 != null) {
            v1_1.putExtra("org.chromium.chrome.browser.download.OTR_PROFILE_ID", p7.toString());
        }
        if (p5 != null) {
            v1_1.addFlags(131072);
            p5.startActivity(v1_1);
        } else {
            v1_1.addFlags(268435456);
            v0_0.startActivity(v1_1);
        }
        if (org.chromium.content.browser.BrowserStartupControllerImpl.e().f()) {
            String v5_6;
            if (p7 != null) {
                v5_6 = ((org.chromium.chrome.browser.profiles.Profile) J.N.OJOZ(org.chromium.chrome.browser.profiles.ProfileManager.b().b, p7, 1));
            } else {
                v5_6 = org.chromium.chrome.browser.profiles.ProfileManager.b();
            }
            sv90.a(v5_6).notifyEvent("download_home_opened");
        }
        kkz.j(p8, 16, "Android.DownloadPage.OpenSource");
        if (p6 != null) {
            String v5_9 = p6.getProfile();
            if (v5_9.l()) {
                String v5_10 = v5_9.a;
                if ((v5_10 == null) || (!v5_10.equals(org.chromium.chrome.browser.profiles.OtrProfileId.b))) {
                    v3 = 4;
                } else {
                    v3 = 1;
                }
            }
            kkz.j(v3, 4, "Download.OpenDownloads.PerProfileType");
            if (p8 == 9) {
                kkz.j(v3, 4, "Download.OpenDownloadsFromMenu.PerProfileType");
            }
        }
        return;
    }

    public static void openDownload(String p8, String p9, String p10, org.chromium.chrome.browser.profiles.OtrProfileId p11, String p12, String p13, int p14, String p15)
    {
        android.content.Context v9_9 = org.chromium.chrome.browser.download.MimeUtils.remapGenericMimeType(p9, p12, p8);
        android.content.Context v0 = org.chromium.base.ApplicationStatus.d;
        if ((v0 == null) || ((!"application/pdf".equals(v9_9)) || (android.text.TextUtils.isEmpty(p15)))) {
            jcd v4_3 = org.chromium.chrome.browser.download.DownloadManagerService.a().f;
            if (v4_3 != null) {
                v89 v5_1 = new org.chromium.url.GURL(p12);
                java.util.HashSet v6 = v4_3.e;
                jcd v4_4 = v4_3.g;
                if ((v4_4 == null) || (!v4_4.contains(v5_1))) {
                    if (v6 != null) {
                        jcd v4_6 = v6.iterator();
                        while (v4_6.hasNext()) {
                            v89 v5_4 = ((v89) v4_6.next());
                            if (java.util.Objects.equals(v5_4.b, p10)) {
                                v6.remove(v5_4);
                                return;
                            }
                        }
                    }
                } else {
                    return;
                }
            }
            jcd v4_8;
            if (!(v0 instanceof org.chromium.chrome.browser.ChromeTabbedActivity)) {
                v4_8 = 0;
            } else {
                v4_8 = ((org.chromium.chrome.browser.ChromeTabbedActivity) v0).F2();
            }
            if ((p11 == null) && (v4_8 != null)) {
                p11 = v4_8.getProfile().a;
            }
            if (v0 == null) {
                v0 = af9.a;
            }
            jcd v4_12 = new jcd();
            v4_12.a = p8;
            v4_12.b = v9_9;
            v4_12.c = p10;
            v4_12.d = p11;
            v4_12.e = p12;
            v4_12.f = p13;
            v4_12.g = p14;
            v4_12.h = v0;
            v4_12.i = p15;
            if (!org.chromium.chrome.browser.download.DownloadUtils.i(v4_12)) {
                try {
                    if ((h27.b.h("OpenDownloadInFilesAppIfNoHandlerFound")) && ("content".equals(android.net.Uri.parse(p8).getScheme()))) {
                        android.content.Intent v8_11 = new android.content.Intent("android.intent.action.VIEW_DOWNLOADS");
                        v8_11.addFlags(268435456);
                        if (v8_11.resolveActivity(af9.a.getPackageManager()) != null) {
                            af9.a.startActivity(v8_11);
                            return;
                        }
                    }
                } catch (android.content.Intent v8_12) {
                    android.util.Log.e("cr_download", "Cannot open download with system files app", v8_12);
                }
                org.chromium.chrome.browser.download.DownloadUtils.l(0, 0, p11, p14);
                kkz.j(1, 4, "Microsoft.Mobile.DownloadManager.Hub.Show");
            }
        } else {
            if (((p14 == 3) || (p14 == 0)) && (org.chromium.chrome.browser.edge_pdf.EdgePdfUtils.isPdfFileFromFileName(p15))) {
                java.util.LinkedHashMap v11_1 = android.os.SystemClock.uptimeMillis();
                if ((android.text.TextUtils.equals(p15, o4f.b)) && ((v11_1 - o4f.a) < 300000)) {
                    kkz.j(4, 6, "Microsoft.Mobile.Pdf.PdfDownloadOpenFunnel");
                }
            }
            org.chromium.chrome.browser.edge_pdf.EdgePdfUtils.openPDFByOffline(v0, p15, org.chromium.chrome.browser.download.DownloadUtils.e(p8).toString());
            if (!android.text.TextUtils.isEmpty(p10)) {
                android.content.Intent v8_6 = org.chromium.chrome.browser.download.DownloadManagerService.a().f;
                if (v8_6 != null) {
                    String v10_1 = jko.a(p10, 0);
                    v8_6.l.getClass();
                    if ((!org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadManagerHelper.isInAppNotificationEnabled()) && (v8_6.f.add(v10_1))) {
                        v8_6.j(v10_1);
                        if (v8_6.b.remove(v10_1) != null) {
                            v8_6.b(0, 0, 0, 1);
                            return;
                        }
                    }
                }
            }
        }
        return;
    }

    public static void showEdgePolicyBlockedDownloadToast(String p2)
    {
        android.content.Context v0 = af9.a;
        p790.c(v0, v0.getString(tzy.download_blocked_by_policy_message, new Object[] {p2})), 1).e();
        return;
    }
}
