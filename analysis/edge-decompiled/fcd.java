// classes.dex Lfcd;
public abstract class Lfcd {

    public static android.content.Intent a(android.content.Context p2, String p3, v89 p4, org.chromium.chrome.browser.profiles.OtrProfileId p5)
    {
        String v0_1;
        String v0_2 = new android.content.ComponentName(p2.getPackageName(), org.chromium.chrome.browser.download.DownloadBroadcastManager.getName());
        android.content.Intent v2_3 = new android.content.Intent(p3);
        v2_3.setComponent(v0_2);
        String v3_1 = "";
        if (p4 == null) {
            v0_1 = "";
        } else {
            v0_1 = p4.b;
        }
        v2_3.putExtra("org.chromium.chrome.browser.download.DownloadContentId_Id", v0_1);
        if (p4 != null) {
            v3_1 = p4.a;
        }
        String v3_2;
        v2_3.putExtra("org.chromium.chrome.browser.download.DownloadContentId_Namespace", v3_1);
        if (p5 == null) {
            v3_2 = 0;
        } else {
            v3_2 = 1;
        }
        v2_3.putExtra("org.chromium.chrome.browser.download.IS_OFF_THE_RECORD", v3_2);
        v2_3.putExtra("org.chromium.chrome.browser.download.OTR_PROFILE_ID", org.chromium.chrome.browser.profiles.OtrProfileId.serialize(p5));
        return v2_3;
    }

    public static android.app.Notification b(android.content.Context p26, int p27, fdd p28, int p29)
    {
        hit v13_1;
        String v3_3 = p28.p;
        int v4_18 = p28.a;
        int v5_26 = p28.m;
        android.graphics.Bitmap v9 = p28.c;
        org.chromium.chrome.browser.profiles.OtrProfileId v10 = p28.e;
        int v11_0 = p28.d;
        int v12 = p28.h;
        if ((!jko.b(v4_18)) || (p27 != 2)) {
            v13_1 = "downloads";
        } else {
            v13_1 = "completed_downloads";
        }
        String v14_4;
        if (!jko.b(v4_18)) {
            v14_4 = 2;
        } else {
            v14_4 = 1;
        }
        String v14_10;
        int v20_1;
        int v21_0;
        boolean v17 = p28.g;
        o0u v18 = p28.k;
        b47 v8_1 = wmt.a(v13_1, new ujt(v14_4, 0, p29));
        hit v13_2 = v8_1.a;
        v13_2.t = 1;
        v13_2.r = "Downloads";
        v13_2.d(1);
        if (!jko.b(v4_18)) {
            v14_10 = 3;
            v20_1 = 4;
            v21_0 = 5;
        } else {
            v14_10 = 0;
            v20_1 = 1;
            v21_0 = 2;
        }
        android.content.Intent v1_1;
        String v2_8;
        int v20_0;
        String v3_0;
        android.content.res.Resources v15_3 = p26.getResources();
        long v22 = v5_26;
        if (p27 == 0) {
            v20_0 = v11_0;
            int v7_19 = v21_0;
            if (v12 == -1) {
                uqs.o();
                return 0;
            } else {
                int v29_1;
                int v21_1;
                if (v3_3 == null) {
                    android.content.Intent v1_13;
                    if (v20_0 == 0) {
                        v21_1 = v3_3;
                        v29_1 = v7_19;
                        v1_13 = v18;
                    } else {
                        v21_1 = v3_3;
                        v29_1 = v7_19;
                        v1_13 = new o0u(0, 0, 2);
                    }
                    v1_1 = ol50.b(v1_13);
                } else {
                    int v11_4 = af9.a;
                    if (!org.chromium.content.browser.BrowserStartupControllerImpl.e().f()) {
                        v1_1 = v11_4.getString(tzy.download_notification_pending);
                    } else {
                        if (v3_3 == 1) {
                            v1_1 = v11_4.getString(tzy.download_notification_pending_network);
                        } else {
                            if (v3_3 == 2) {
                                v1_1 = v11_4.getString(tzy.download_notification_pending_another_download);
                            } else {
                                v1_1 = v11_4.getString(tzy.download_notification_pending);
                            }
                        }
                    }
                    v21_1 = v3_3;
                    v29_1 = v7_19;
                }
                if (v21_1 == 0) {
                    v3_0 = 17301633;
                } else {
                    v3_0 = izy.ic_download_pending;
                }
                int v6_1 = fcd.a(p26, "org.chromium.chrome.browser.download.DOWNLOAD_PAUSE", v4_18, v10);
                int v5_0 = fcd.a(p26, "org.chromium.chrome.browser.download.DOWNLOAD_CANCEL", v4_18, v10);
                v5_0.putExtra("notification_id", v12);
                v8_1.m(1);
                v13_2.d(0);
                v8_1.a(izy.ic_pause_white_24dp, v15_3.getString(tzy.download_notification_pause_button), xcw.c(p26, v12, v6_1, 134217728), v14_10);
                v8_1.a(izy.btn_close_white, v15_3.getString(tzy.download_notification_cancel_button), xcw.c(p26, v12, v5_0, 134217728), v29_1);
                if (v20_0 == 0) {
                    v8_1.l(v9);
                }
                String v2_4 = v18.b();
                if (v21_1 == 0) {
                    int v5_2;
                    if (v2_4 == null) {
                        v5_2 = v18.a();
                    } else {
                        v5_2 = -1;
                    }
                    v13_2.o = 100;
                    v13_2.p = v5_2;
                    v13_2.q = v2_4;
                }
                if ((v2_4 == null) && ((v20_0 == 0) && ((v22 >= 0) && (!jko.c(v4_18))))) {
                    v8_1.p(ol50.c(p26, v22));
                }
                v2_8 = p28;
                int v4_1 = p28.l;
                if (v4_1 > 0) {
                    v13_2.F.when = v4_1;
                }
            }
        } else {
            int v6_9;
            if (p27 == 1) {
                v6_9 = p28;
                if (v12 == -1) {
                    uqs.o();
                    return 0;
                } else {
                    String v2_11 = v15_3.getString(tzy.download_notification_paused);
                    v3_0 = izy.ic_download_pause;
                    int v7_4 = fcd.a(p26, "org.chromium.chrome.browser.download.DOWNLOAD_RESUME", v4_18, v10);
                    int v4_2 = fcd.a(p26, "org.chromium.chrome.browser.download.DOWNLOAD_CANCEL", v4_18, v10);
                    v13_2.d(0);
                    int v29_2 = v2_11;
                    v8_1.a(izy.ic_file_download_white_24dp, v15_3.getString(tzy.download_notification_resume_button), xcw.c(p26, v12, v7_4, 134217728), v20_1);
                    v8_1.a(izy.btn_close_white, v15_3.getString(tzy.download_notification_cancel_button), xcw.c(p26, v12, v4_2, 134217728), v21_0);
                    if (v11_0 == 0) {
                        v8_1.l(v9);
                    }
                    if (v17) {
                        v8_1.k(xcw.c(p26, v12, v4_2, 134217728));
                    }
                    v1_1 = v29_2;
                }
            } else {
                if (p27 == 2) {
                    v6_9 = p28;
                    if (v12 == -1) {
                        uqs.o();
                        return 0;
                    } else {
                        String v2_20;
                        String v2_18 = p28.n;
                        if ((v2_18 <= 0) || (v11_0 != 0)) {
                            v2_20 = v15_3.getString(tzy.download_notification_completed);
                        } else {
                            v2_20 = v15_3.getString(tzy.download_notification_completed_with_size, new Object[] {jdd.b(p26, jdd.a, v2_18)}));
                        }
                        v3_0 = izy.offline_pin;
                        if (!android.text.TextUtils.isEmpty(v4_18.a)) {
                            if (p28.f) {
                                int v4_3 = fcd.a(p26, "org.chromium.chrome.browser.download.DOWNLOAD_OPEN", v4_18, 0);
                                v4_3.setComponent(new android.content.ComponentName(p26.getPackageName(), org.chromium.chrome.browser.download.DownloadBroadcastManager.getName()));
                                try {
                                    v8_1.h(xcw.c(p26, v12, v4_3, 134217728));
                                } catch (xcw v0_1) {
                                    android.content.Intent v1_2 = v0_1.getMessage();
                                    String v2_23 = v4_3.toString();
                                    String v3_2 = new StringBuilder();
                                    v3_2.append(v1_2);
                                    v3_2.append("Notification id: ");
                                    v3_2.append(v12);
                                    v3_2.append("; intent: ");
                                    v3_2.append(v2_23);
                                    v3_2.append(";");
                                    String v2_25 = new RuntimeException(v3_2.toString());
                                    v2_25.setStackTrace(v0_1.getStackTrace());
                                    throw v2_25;
                                }
                            }
                        } else {
                            int v4_5 = new android.content.Intent("android.intent.action.VIEW_DOWNLOADS");
                            v4_5.setFlags(268468224);
                            v8_1.h(xcw.a(p26, 0, v4_5, 134217728, 0));
                        }
                        if (v9 != null) {
                            v8_1.l(v9);
                        }
                        v13_2.C = 3600000;
                        v1_1 = v2_20;
                    }
                } else {
                    if (p27 == 4) {
                        int v4_10;
                        v6_9 = p28;
                        if (!org.chromium.content.browser.BrowserStartupControllerImpl.e().f()) {
                            v4_10 = af9.a.getString(tzy.download_notification_failed);
                        } else {
                            v4_10 = ((String) J.N.OI(1, p28.o));
                        }
                        v13_2.C = 3600000;
                        v3_0 = 17301634;
                        v1_1 = v4_10;
                    } else {
                        if (p27 == 5) {
                            if (v12 == -1) {
                                uqs.o();
                                return 0;
                            } else {
                                String v3_5 = v15_3.getString(tzy.download_notification_dangerous_blocked);
                                int v4_12 = fcd.a(p26, "org.chromium.chrome.browser.download.DOWNLOAD_CANCEL", v4_18, v10);
                                v4_12.putExtra("notification_id", v12);
                                v8_1.a(izy.ic_delete_white_24dp, v15_3.getString(tzy.download_notification_delete_from_history_button), xcw.c(p26, v12, v4_12, 134217728), 41);
                                v13_2.C = 300000;
                                v1_1 = v3_5;
                                v20_0 = v11_0;
                                v3_0 = izy.dangerous_filled_24dp;
                            }
                        } else {
                            v1_1 = "";
                            v20_0 = v11_0;
                            v3_0 = -1;
                        }
                        v2_8 = p28;
                        int v4_16 = new android.os.Bundle();
                        v4_16.putInt("Chrome.NotificationBundleIconIdExtra", v3_0);
                        v8_1.n(v3_0);
                        String v3_6 = v13_2.v;
                        if (v3_6 != null) {
                            v3_6.putAll(v4_16);
                        } else {
                            v13_2.v = new android.os.Bundle(v4_16);
                        }
                        if (v20_0 == 0) {
                            v8_1.i(v1_1);
                        } else {
                            v8_1.j(v1_1);
                        }
                        android.content.Intent v1_7 = v2_8.b;
                        if ((v1_7 != null) && (v20_0 == 0)) {
                            v8_1.j(ol50.a(25, v1_7));
                        }
                        if ((!v17) && ((v12 != -1) && ((p27 != 2) && (p27 != 4)))) {
                            android.content.Intent v1_11 = fcd.a(p26, "android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED", 0, v10);
                            v1_11.putExtra("org.chromium.chrome.browser.download.DOWNLOAD_DANGER_TYPE", v2_8.q);
                            v8_1.h(xcw.c(p26, v12, v1_11, 134217728));
                        }
                        if (v20_0 == 0) {
                            if (v2_8.j) {
                                xcw v0_6 = jdd.a(40, v2_8.i);
                                if (v0_6 != null) {
                                    v8_1.p(v0_6);
                                }
                            }
                        } else {
                            v8_1.p(v15_3.getString(tzy.download_notification_incognito_subtext));
                        }
                        return v8_1.c();
                    }
                }
            }
            v2_8 = v6_9;
            v20_0 = v11_0;
        }
    }
}
