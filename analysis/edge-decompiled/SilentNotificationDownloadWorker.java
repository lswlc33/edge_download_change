// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/edge_silent_notification/SilentNotificationDownloadWorker
package org.chromium.chrome.browser.edge_silent_notification;
public final class SilentNotificationDownloadWorker extends androidx.work.Worker {
    public volatile ff30 a;

    public SilentNotificationDownloadWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    public static void b()
    {
        cad0 v2_0 = new f49(new k4t(0), androidx.work.NetworkType.b, 0, 0, 0, 0, -1, -1, kotlin.collections.a.q0(new java.util.LinkedHashSet()));
        java.util.List v1_2 = new fau(org.chromium.chrome.browser.edge_silent_notification.SilentNotificationDownloadWorker);
        v1_2.c.j = v2_0;
        new cad0(zbd0.a(af9.a), "edge_silent_notification_download", androidx.work.ExistingWorkPolicy.a, java.util.Collections.singletonList(v1_2.a())).a();
        return;
    }

    public final p4p a()
    {
        int v16;
        String v1_0 = this;
        int v2_1 = new ff30();
        this.a = v2_1;
        String v0_58 = this.getApplicationContext();
        rf30 v3 = rf30.d;
        ef30 v4 = v3.e();
        int v5_1 = 0;
        int v6_0 = 1;
        if ((v4 != null) && ((v4.i != null) && (v4.j == 1))) {
            if (!v4.b(System.currentTimeMillis())) {
                int v7_5 = (v4.k + 1);
                java.io.File v8_0 = v4.a;
                java.io.File v8_3;
                java.io.FileDescriptor v9_5 = v3.c();
                if (v9_5 == null) {
                    v8_3 = 0;
                } else {
                    if (v9_5.a.equals(v8_0)) {
                        java.io.File v8_2 = rf30.b(v9_5);
                        v8_2.k = v7_5;
                        v8_3 = v3.d(v8_2);
                    } else {
                    }
                }
                if (v8_3 != null) {
                    int v19;
                    int v2_2;
                    String v1_1;
                    sf30.b(0);
                    java.io.File v8_4 = v4.a;
                    java.io.FileDescriptor v9_9 = v4.i;
                    v16 = 1;
                    int v15_4 = new java.io.File(v0_58.getNoBackupFilesDir(), "edge_silent_notification");
                    if ((v15_4.exists()) || (v15_4.mkdirs())) {
                        int v6_6 = ff30.b(v0_58, v8_4);
                        int v15_5 = v6_6.getPath();
                        v19 = 4;
                        int v10_7 = new StringBuilder();
                        v10_7.append(v15_5);
                        v10_7.append(".tmp");
                        java.io.File v8_6 = new java.io.File(v10_7.toString());
                        if ((!v8_6.exists()) || (v8_6.delete())) {
                            try {
                                java.io.FileDescriptor v9_10 = v2_1.a(v9_9);
                            } catch (String v0_42) {
                                v5_1 = 0;
                                v2_1.a = v5_1;
                                if (v8_6.exists()) {
                                    if (!v8_6.delete()) {
                                        android.util.Log.w("cr_SilentNotifHtml", "Failed to clean up the temporary content file.");
                                    }
                                }
                                throw v0_42;
                            }
                            if (v9_10 != null) {
                                try {
                                    int v10_11 = v9_10.g;
                                } catch (String v0_4) {
                                    int v21 = v9_10;
                                    int v6_1 = 2;
                                    v1_1 = 0;
                                    int v5_20 = v0_4;
                                    try {
                                        v21.close();
                                    } catch (String v0_35) {
                                        v5_20.addSuppressed(v0_35);
                                    } catch (String v0_21) {
                                        if (!v2_1.b) {
                                            java.io.FileDescriptor v9_7 = new StringBuilder("cr_");
                                            v9_7.append("SilentNotifHtml");
                                            android.util.Log.w(v9_7.toString(), "Failed to download or persist content.", v0_21);
                                        }
                                        if (!v2_1.b) {
                                            int v10_4 = v6_1;
                                        } else {
                                            v10_4 = 3;
                                        }
                                        sf30.b(v10_4);
                                        v2_1.a = 0;
                                        if (!v8_6.exists()) {
                                            v2_2 = 3;
                                            if (v7_5 < v2_2) {
                                                v5_1 = 0;
                                                v6_0 = v1_1;
                                                v1_0 = this;
                                            } else {
                                                v3.f(v19, v4.a);
                                                v5_1 = 0;
                                                v1_0 = this;
                                                v6_0 = v16;
                                            }
                                            String v0_56;
                                            v1_0.a = v5_1;
                                            if (v6_0 == 0) {
                                                v0_56 = new n4p();
                                            } else {
                                                v0_56 = new o4p();
                                                v0_56.a = q6b.b;
                                            }
                                            return v0_56;
                                        } else {
                                            if (v8_6.delete()) {
                                            } else {
                                                android.util.Log.w("cr_SilentNotifHtml", "Failed to clean up the temporary content file.");
                                            }
                                        }
                                        sf30.b(String v0_41);
                                        v2_1.a = 0;
                                        if ((!v8_6.exists()) || (v8_6.delete())) {
                                            v2_2 = 3;
                                            v1_1 = 0;
                                        }
                                    }
                                    throw v5_20;
                                }
                                if (v10_11.d() <= 5242880) {
                                    int v15_2 = new java.io.BufferedInputStream(v10_11.a());
                                    try {
                                        int v10_0 = new java.io.FileOutputStream(v8_6);
                                        try {
                                            v5_1 = new java.io.BufferedOutputStream(v10_0);
                                            v21 = v9_10;
                                            try {
                                                java.io.FileDescriptor v9_1 = new byte[16384];
                                                int v23 = v10_0;
                                                int v22_0 = 0;
                                                try {
                                                    while (!v2_1.b) {
                                                        try {
                                                            int v10_2 = v15_2.read(v9_1);
                                                            int v24 = v15_2;
                                                        } catch (String v0_3) {
                                                            v24 = -1;
                                                            v1_1 = 0;
                                                            java.io.FileDescriptor v9_4 = v0_3;
                                                            v6_1 = 2;
                                                            try {
                                                                v5_1.close();
                                                            } catch (String v0_25) {
                                                                v9_4.addSuppressed(v0_25);
                                                            } catch (String v0_1) {
                                                                int v5_18 = v0_1;
                                                                try {
                                                                    v23.close();
                                                                } catch (String v0_27) {
                                                                    v5_18.addSuppressed(v0_27);
                                                                } catch (String v0_0) {
                                                                    int v5_19 = v0_0;
                                                                    try {
                                                                        v24.close();
                                                                    } catch (String v0_29) {
                                                                        v5_19.addSuppressed(v0_29);
                                                                    } catch (String v0_4) {
                                                                    }
                                                                    throw v5_19;
                                                                }
                                                                throw v5_18;
                                                            }
                                                            throw v9_4;
                                                        }
                                                        if (v10_2 == -1) {
                                                            v1_1 = 0;
                                                            try {
                                                                if (!v2_1.b) {
                                                                    v5_1.flush();
                                                                    v23.getFD().sync();
                                                                    try {
                                                                        v5_1.close();
                                                                        try {
                                                                            v23.close();
                                                                            try {
                                                                                v24.close();
                                                                            } catch (String v0_4) {
                                                                                v6_1 = 2;
                                                                            }
                                                                            if (v22_0 != 0) {
                                                                                v21.close();
                                                                                if ((!v6_6.exists()) || (v6_6.delete())) {
                                                                                    if (v8_6.renameTo(v6_6)) {
                                                                                        sf30.b(1);
                                                                                        v2_1.a = 0;
                                                                                        v1_1 = v8_6.exists();
                                                                                        if ((v1_1 != null) && (!v8_6.delete())) {
                                                                                            android.util.Log.w("cr_SilentNotifHtml", "Failed to clean up the temporary content file.");
                                                                                        }
                                                                                        if (v3.f(2, v4.a)) {
                                                                                        } else {
                                                                                            String v0_5 = ff30.b(v0_58, v4.a);
                                                                                            if ((!v0_5.exists()) || (v0_5.delete())) {
                                                                                            } else {
                                                                                                android.util.Log.w("cr_SilentNotifHtml", "Failed to delete the content file.");
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        int v5_7 = new StringBuilder("cr_");
                                                                                        v5_7.append("SilentNotifHtml");
                                                                                        android.util.Log.w(v5_7.toString(), "Failed to replace the content file.");
                                                                                        sf30.b(2);
                                                                                        v2_1.a = 0;
                                                                                        if ((!v8_6.exists()) || (v8_6.delete())) {
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    int v5_11 = new StringBuilder("cr_");
                                                                                    v5_11.append("SilentNotifHtml");
                                                                                    android.util.Log.w(v5_11.toString(), "Failed to remove the previous content file.");
                                                                                    sf30.b(2);
                                                                                    v2_1.a = 0;
                                                                                    if ((!v8_6.exists()) || (v8_6.delete())) {
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                int v5_15 = new StringBuilder("cr_");
                                                                                v5_15.append("SilentNotifHtml");
                                                                                android.util.Log.w(v5_15.toString(), "The downloaded content is empty.");
                                                                                sf30.b(4);
                                                                                v21.close();
                                                                                v2_1.a = 0;
                                                                                if ((!v8_6.exists()) || (v8_6.delete())) {
                                                                                }
                                                                            }
                                                                        } catch (String v0_0) {
                                                                            v6_1 = 2;
                                                                        }
                                                                    } catch (String v0_1) {
                                                                        v6_1 = 2;
                                                                    }
                                                                } else {
                                                                    sf30.b(3);
                                                                    try {
                                                                        v5_1.close();
                                                                        try {
                                                                            v23.close();
                                                                        } catch (String v0_18) {
                                                                            v5_19 = v0_18;
                                                                            v6_1 = 2;
                                                                        }
                                                                        v24.close();
                                                                        v21.close();
                                                                        v2_1.a = 0;
                                                                        if ((!v8_6.exists()) || (v8_6.delete())) {
                                                                        }
                                                                        v6_1 = 2;
                                                                        v1_1 = 0;
                                                                    } catch (String v0_17) {
                                                                        v5_18 = v0_17;
                                                                        v6_1 = 2;
                                                                    }
                                                                }
                                                            } catch (String v0_2) {
                                                                v6_1 = 2;
                                                                v9_4 = v0_2;
                                                            } catch (String v0_19) {
                                                                v5_20 = v0_19;
                                                                v6_1 = 2;
                                                            } catch (String v0_21) {
                                                                v6_1 = 2;
                                                            }
                                                        } else {
                                                            int v15_1 = (v22_0 + v10_2);
                                                            if (v15_1 <= 5242880) {
                                                                v5_1.write(v9_1, 0, v10_2);
                                                                v1_1 = this;
                                                                v22_0 = v15_1;
                                                                v15_2 = v24;
                                                            } else {
                                                                try {
                                                                    String v1_11 = new StringBuilder("cr_");
                                                                    v1_11.append("SilentNotifHtml");
                                                                    android.util.Log.w(v1_11.toString(), "The downloaded content size exceeds the limit.");
                                                                    sf30.b(4);
                                                                    try {
                                                                        v5_1.close();
                                                                        try {
                                                                            v23.close();
                                                                            try {
                                                                                v24.close();
                                                                            } catch (String v0_30) {
                                                                                v5_20 = v0_30;
                                                                            }
                                                                            v21.close();
                                                                            v2_1.a = 0;
                                                                            if ((!v8_6.exists()) || (v8_6.delete())) {
                                                                            } else {
                                                                                android.util.Log.w("cr_SilentNotifHtml", "Failed to clean up the temporary content file.");
                                                                            }
                                                                        } catch (String v0_28) {
                                                                            v5_19 = v0_28;
                                                                            v6_1 = 2;
                                                                            v1_1 = 0;
                                                                        }
                                                                    } catch (String v0_26) {
                                                                        v5_18 = v0_26;
                                                                        v6_1 = 2;
                                                                        v1_1 = 0;
                                                                    }
                                                                } catch (String v0_24) {
                                                                    v9_4 = v0_24;
                                                                    v6_1 = 2;
                                                                    v1_1 = 0;
                                                                }
                                                            }
                                                        }
                                                    }
                                                } catch (String v0_2) {
                                                    v24 = v15_2;
                                                    v6_1 = 2;
                                                    v1_1 = 0;
                                                } catch (String v0_3) {
                                                }
                                                v24 = v15_2;
                                            } catch (String v0_2) {
                                                v23 = v10_0;
                                            }
                                        } catch (String v0_1) {
                                            v21 = 16384;
                                            v23 = v10_0;
                                            v24 = v15_2;
                                            v6_1 = 2;
                                            v1_1 = 0;
                                        }
                                    } catch (String v0_0) {
                                        v21 = v9_10;
                                        v24 = v15_2;
                                        v6_1 = 2;
                                        v1_1 = 0;
                                    }
                                } else {
                                    try {
                                        android.util.Log.w("cr_SilentNotifHtml", "The declared content size exceeds the limit.");
                                        sf30.b(4);
                                    } catch (String v0_34) {
                                        v5_20 = v0_34;
                                        v21 = v9_10;
                                    }
                                    v9_10.close();
                                    v2_1.a = 0;
                                    if ((!v8_6.exists()) || (v8_6.delete())) {
                                    }
                                }
                            } else {
                                if (!v2_1.b) {
                                    android.util.Log.w("cr_SilentNotifHtml", "The content request was rejected or unsuccessful.");
                                }
                                if (!v2_1.b) {
                                    v0_41 = 2;
                                } else {
                                    v0_41 = 3;
                                }
                            }
                        } else {
                            android.util.Log.w("cr_SilentNotifHtml", "Failed to remove the stale temporary content file.");
                            sf30.b(2);
                        }
                    } else {
                        android.util.Log.w("cr_SilentNotifHtml", "Failed to create the content directory.");
                        sf30.b(2);
                        v2_2 = 3;
                        v1_1 = 0;
                        v19 = 4;
                    }
                }
            } else {
                v3.a(v4.a);
                String v0_52 = ff30.b(v0_58, v4.a);
                if ((v0_52.exists()) && (!v0_52.delete())) {
                    android.util.Log.w("cr_SilentNotifHtml", "Failed to delete the content file.");
                }
            }
        } else {
            v16 = 1;
        }
    }

    public final void onStopped()
    {
        int v0_0 = this.a;
        if (v0_0 != 0) {
            v0_0.b = 1;
            int v0_1 = v0_0.a;
            if (v0_1 != 0) {
                v0_1.cancel();
            }
            this.a = 0;
        }
        return;
    }
}
