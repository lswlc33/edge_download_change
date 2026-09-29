// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/DownloadDialogBridge
package org.chromium.chrome.browser.download;
public final class DownloadDialogBridge {
    public long a;
    public nad b;
    public org.chromium.ui.base.WindowAndroid c;
    public int d;
    public String e;
    public org.chromium.chrome.browser.profiles.Profile f;
    public boolean g;

    public static String a(org.chromium.chrome.browser.profiles.Profile p1)
    {
        return d3b0.b(p1.f()).d("download.default_directory");
    }

    public static int b(org.chromium.chrome.browser.profiles.Profile p1)
    {
        return d3b0.b(p1.f()).c("download.prompt_for_download_android");
    }

    public static org.chromium.chrome.browser.download.DownloadDialogBridge create(long p2)
    {
        nad v0_1 = new nad();
        org.chromium.chrome.browser.download.DownloadDialogBridge v1_1 = new org.chromium.chrome.browser.download.DownloadDialogBridge();
        v1_1.a = p2;
        v1_1.b = v0_1;
        v0_1.a = v1_1;
        v0_1.p = 0;
        v0_1.r = 0;
        return v1_1;
    }

    public static void f(org.chromium.chrome.browser.profiles.Profile p1, String p2)
    {
        J.N.VOO(19, d3b0.b(p1.f()), p2);
        return;
    }

    public static void g(int p1, org.chromium.chrome.browser.profiles.Profile p2)
    {
        d3b0.b(p2.f()).h(p1, "download.prompt_for_download_android");
        return;
    }

    public final void c()
    {
        org.chromium.ui.base.WindowAndroid v0_0 = this.a;
        if (v0_0 != 0) {
            J.N.VJ(106, v0_0);
            org.chromium.ui.base.WindowAndroid v0_1 = this.c;
            if (v0_1 != null) {
                k5t.d2(v0_1);
            }
            this.e();
            return;
        } else {
            this.e();
            return;
        }
    }

    public final void d(String p4, boolean p5)
    {
        this.e = p4;
        this.g = p5;
        if (this.d == 6) {
            kkz.c("MobileDownload.Location.Dialog.SuggestionSelected", (p4.equals(org.chromium.chrome.browser.download.DownloadDialogBridge.a(this.f)) ^ 1));
        }
        long v4_2 = this.a;
        if (v4_2 != 0) {
            J.N.VJOZ(9, v4_2, this.e, this.g);
            this.e();
            return;
        } else {
            this.e();
            return;
        }
    }

    public final void destroy()
    {
        this.a = 0;
        nad v0_1 = this.b;
        n3s v1 = v0_1.f;
        if (v1 != null) {
            v1.c(v0_1.b, 4);
        }
        v0_1.d();
        this.e();
        return;
    }

    public final void e()
    {
        this.c = 0;
        this.f = 0;
        this.e = 0;
        return;
    }

    public final void showDialog(org.chromium.ui.base.WindowAndroid p2, long p3, int p5, int p6, String p7, org.chromium.chrome.browser.profiles.Profile p8, boolean p9)
    {
        this.c = p2;
        this.f = p8;
        android.app.Activity v2_3 = ((android.app.Activity) p2.x().get());
        if ((v2_3 instanceof o3s)) {
            u8d v0_1 = new u8d();
            v0_1.a = this;
            v0_1.b = v2_3;
            v0_1.c = p6;
            v0_1.d = p8;
            v0_1.e = p3;
            v0_1.f = p7;
            v0_1.g = p9;
            b9d.a.a(v0_1);
            return;
        } else {
            this.c();
            return;
        }
    }
}
