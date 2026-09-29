// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/app/download/home/DownloadActivity
package org.chromium.chrome.browser.app.download.home;
public class DownloadActivity extends tt30 {
    public static final synthetic int j;
    public gbd d;
    public mn e;
    public n3s f;
    public String g;
    public final h8d h;
    public org.chromium.chrome.browser.profiles.OtrProfileId i;

    public DownloadActivity()
    {
        h8d v0_1 = new h8d();
        v0_1.a = this;
        this.h = v0_1;
        return;
    }

    public final void E1(android.os.Bundle p2)
    {
        String v2_1;
        super.E1(p2);
        if (p2 != null) {
            v2_1 = p2.getString("current_url");
        } else {
            v2_1 = "chrome-native://downloads/";
        }
        this.g = v2_1;
        return;
    }

    public final void F1(org.chromium.chrome.browser.profiles.Profile p5)
    {
        l7u v5_0 = this.getIntent();
        if (org.chromium.chrome.browser.profiles.ProfileManager.b) {
            l7u v5_7 = org.chromium.chrome.browser.profiles.OtrProfileId.deserializeWithoutVerify(b6n.v(v5_0, "org.chromium.chrome.browser.download.OTR_PROFILE_ID"));
            if ((v5_7 == null) || (J.N.ZJO(30, org.chromium.chrome.browser.profiles.ProfileManager.b().b, v5_7))) {
                yzt.a();
                int v2_0 = 0;
                l7u v5_3 = b6n.k(this.getIntent(), "org.chromium.chrome.browser.download.SHOW_PREFETCHED_CONTENT", 0);
                this.e = new mn(new ref.WeakReference(this));
                this.i = org.chromium.chrome.browser.profiles.OtrProfileId.a(b6n.v(this.getIntent(), "org.chromium.chrome.browser.download.OTR_PROFILE_ID"));
                q03[] v0_3 = qbd.a(this);
                v0_3.a = this.i;
                v0_3.b = 1;
                if ((qx6.b().c()) || (af9.a.getResources().getConfiguration().keyboard != 1)) {
                    v2_0 = 1;
                }
                v0_3.f = v2_0;
                v0_3.g = v5_3;
                int v2_2 = new g8d();
                v2_2.a = this;
                v0_3.j = v2_2;
                q03[] v0_5 = v0_3.a();
                int v2_4 = new n3s(new ud1(this));
                this.f = v2_4;
                q03[] v0_6 = bbd.a(this, v0_5, this.c, v2_4);
                this.d = v0_6;
                this.setContentView(v0_6.i);
                if (v5_3 == null) {
                    this.d.b(this.g);
                }
                l7u v5_6 = this.d;
                int v2_5 = this.h;
                v5_6.a.a(v2_5);
                q03[] v0_11 = new fbd();
                v0_11.a = v5_6;
                v0_11.b = v2_5;
                org.chromium.base.task.PostTask.c(7, v0_11);
                l7u v5_9 = this.getOnBackPressedDispatcher();
                q03[] v0_13 = this.d.h;
                int v2_7 = (v0_13.length - 1);
                while (v2_7 >= 0) {
                    v03.a(this, v5_9, v0_13[v2_7]);
                    v2_7--;
                }
                return;
            }
        }
        this.finish();
        return;
    }

    public final n3s getModalDialogManager()
    {
        return this.f;
    }

    public final void onMAMDestroy()
    {
        n3s v0_0 = this.d;
        if (v0_0 != null) {
            v0_0.a.f(this.h);
            this.d.a();
            this.f.b();
        }
        super.onMAMDestroy();
        return;
    }

    public final void onMAMSaveInstanceState(android.os.Bundle p2)
    {
        super.onMAMSaveInstanceState(p2);
        String v1_1 = this.g;
        if (v1_1 != null) {
            p2.putString("current_url", v1_1);
        }
        return;
    }

    public final void onRequestPermissionsResult(int p1, String[] p2, int[] p3)
    {
        this.e.a(p1, p2, p3);
        return;
    }
}
