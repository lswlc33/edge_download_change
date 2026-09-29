// classes.dex Lhcd;
public final class Lhcd {
    public uc3 a;
    public android.graphics.Bitmap b;
    public ycd c;
    public i9d d;
    public gdd e;

    public final void a()
    {
        int v1_1;
        ycd v0 = this.c;
        if ((!org.chromium.content.browser.BrowserStartupControllerImpl.e().f()) || (!J.N.ZJ(51, org.chromium.chrome.browser.profiles.ProfileManager.b().b))) {
            v1_1 = 0;
        } else {
            v1_1 = 1;
        }
        java.util.Iterator v2_1 = new java.util.ArrayList(v0.a).iterator();
        while (v2_1.hasNext()) {
            v89 v3_4 = ((wcd) v2_1.next());
            if (v3_4.b != null) {
                v89 v3_5 = v3_4.f;
                c0u v4_1 = v0.b(v3_5);
                if (v4_1 != null) {
                    c0u v4_2 = v4_1.a;
                    this.d.e(af9.a, 3, v4_2, 0);
                    this.e.e(af9.a, 3, v4_2, 0);
                    this.a.h(v4_2);
                    v0.c(v3_5);
                }
                if (v1_1 != 0) {
                    yzt.a().a.a(v3_5);
                }
            }
        }
        return;
    }

    public final int b(v89 p3)
    {
        int v2_3 = this.c.b(p3);
        if (v2_3 == 0) {
            int v0 = 1000000;
            int v2_5 = ze9.a.getInt("NextDownloadNotificationId", 1000000);
            if (v2_5 != 2147483647) {
                v0 = (v2_5 + 1);
            }
            org.chromium.base.shared_preferences.SharedPreferencesManager.o(v0, "NextDownloadNotificationId");
            return v2_5;
        } else {
            return v2_3.a;
        }
    }

    public final void c(v89 p6, String p7, android.graphics.Bitmap p8, org.chromium.url.GURL p9, boolean p10, org.chromium.chrome.browser.profiles.OtrProfileId p11, int p12)
    {
        if (android.text.TextUtils.isEmpty(p7)) {
            int v7_4 = this.c.b(p6);
            if (v7_4 != 0) {
                p7 = v7_4.d;
            } else {
                return;
            }
        }
        int v3;
        int v0_1 = this.b(p6);
        android.content.Context v1 = af9.a;
        if (p11 == null) {
            v3 = 0;
        } else {
            v3 = 1;
        }
        fdd v4_1 = new fdd();
        v4_1.a = p6;
        v4_1.b = p7;
        v4_1.c = p8;
        v4_1.d = v3;
        v4_1.e = p11;
        v4_1.f = 0;
        v4_1.g = 0;
        v4_1.h = -1;
        if (p9 == null) {
            p9 = ejj.a;
        }
        v4_1.i = p9;
        v4_1.j = p10;
        v4_1.k = 0;
        v4_1.l = 0;
        v4_1.m = 0;
        v4_1.n = 0;
        v4_1.o = p12;
        v4_1.p = 0;
        v4_1.q = 0;
        android.app.Notification v9_1 = fcd.b(v1, 4, v4_1, v0_1);
        this.g(v0_1, v9_1, p6, 0);
        this.d.e(v1, 4, v0_1, v9_1);
        this.e.e(v1, 4, v0_1, v9_1);
        return;
    }

    public final void d(v89 p17, String p18, boolean p19, boolean p20, org.chromium.chrome.browser.profiles.OtrProfileId p21, boolean p22, android.graphics.Bitmap p23, org.chromium.url.GURL p24, boolean p25, boolean p26, int p27)
    {
        int v2_2 = this.c.b(p17);
        if (p19) {
            if ((v2_2 == 0) || ((v2_2.e) || (p26))) {
                long v4_0;
                if (v2_2 != 0) {
                    v4_0 = v2_2.c;
                } else {
                    v4_0 = 0;
                }
                if ((!p20) && (p27 == 0)) {
                    int v2_1;
                    if (v2_2 != 0) {
                        v2_1 = v2_2.a;
                    } else {
                        v2_1 = this.b(p17);
                    }
                    String v5_0;
                    android.content.Context v11_0 = af9.a;
                    if (p21 == null) {
                        v5_0 = 0;
                    } else {
                        v5_0 = 1;
                    }
                    org.chromium.url.GURL v14_1;
                    boolean v7_1 = new fdd();
                    v7_1.a = p17;
                    v7_1.b = p18;
                    v7_1.c = p23;
                    v7_1.d = v5_0;
                    v7_1.e = p21;
                    v7_1.f = 0;
                    v7_1.g = p22;
                    v7_1.h = v2_1;
                    if (p24 != null) {
                        v14_1 = p24;
                    } else {
                        v14_1 = ejj.a;
                    }
                    v7_1.i = v14_1;
                    v7_1.j = p25;
                    v7_1.k = 0;
                    v7_1.l = 0;
                    v7_1.m = 0;
                    v7_1.n = 0;
                    v7_1.o = 0;
                    v7_1.p = 0;
                    v7_1.q = 0;
                    android.app.Notification v9_1 = fcd.b(v11_0, 1, v7_1, v2_1);
                    this.g(v2_1, v9_1, p17, new wcd(p17, v2_1, p21, v4_0, p18, p20, p22));
                    this.d.e(v11_0, 1, v2_1, v9_1);
                    this.e.e(v11_0, 1, v2_1, v9_1);
                    return;
                } else {
                    this.f(p17, p18, new o0u(0, 0, 2), 0, 0, p21, v4_0, p22, p23, p24, p25, p27);
                    return;
                }
            } else {
                return;
            }
        } else {
            this.c(p17, p18, p23, p24, p25, p21, 1);
            return;
        }
    }

    public final void e(int p5, dss p6)
    {
        int v4_1 = this.e;
        java.util.HashMap v0 = v4_1.c;
        if (p6) {
            v4_1.d = 1;
            v4_1.e = 1;
            v0.put(Integer.valueOf(p5), p6);
            v4_1.d(0);
            if (!v4_1.d) {
                kkz.j(0, 4, "Download.Android.NotificationAttachEvent");
            }
            v4_1.e = 0;
            return;
        } else {
            v0.remove(Integer.valueOf(p5));
            if (v4_1.d) {
                kkz.j(2, 4, "Download.Android.NotificationAttachEvent");
            }
            return;
        }
    }

    public final void f(v89 p12, String p13, o0u p14, long p15, long p17, org.chromium.chrome.browser.profiles.OtrProfileId p19, boolean p20, boolean p21, android.graphics.Bitmap p22, org.chromium.url.GURL p23, boolean p24, int p25)
    {
        wcd v0_5;
        int v2 = this.b(p12);
        android.content.Context v8 = af9.a;
        if (p19 == null) {
            v0_5 = 0;
        } else {
            v0_5 = 1;
        }
        wcd v0_0;
        v89 v1_2 = new fdd();
        v1_2.a = p12;
        v1_2.b = p13;
        v1_2.c = p22;
        v1_2.d = v0_5;
        v1_2.e = p19;
        v1_2.f = 0;
        v1_2.g = p21;
        v1_2.h = v2;
        if (p23 != null) {
            v0_0 = p23;
        } else {
            v0_0 = ejj.a;
        }
        v1_2.i = v0_0;
        v1_2.j = p24;
        v1_2.k = p14;
        v1_2.l = p17;
        v1_2.m = p15;
        v1_2.n = 0;
        v1_2.o = 0;
        v1_2.p = p25;
        v1_2.q = 0;
        android.app.Notification v10 = fcd.b(v8, 0, v1_2, v2);
        this.g(v2, v10, p12, new wcd(p12, v2, p19, p20, p13, 1, p21));
        this.d.e(v8, 0, v2, v10);
        this.e.e(v8, 0, v2, v10);
        return;
    }

    public final void g(int p6, android.app.Notification p7, v89 p8, wcd p9)
    {
        int v4 = 1;
        this.a.f(new vmt(p7, new ujt(1, 0, p6), 0));
        ycd v5_1 = this.c;
        if (v5_1.b(p8) == null) {
            if (jko.c(p8)) {
                v4 = 2;
            }
            tmt.a.a(v4, p7);
        }
        if (p9 == null) {
            v5_1.c(p8);
            return;
        } else {
            v5_1.a(p9);
            return;
        }
    }
}
