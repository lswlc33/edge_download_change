// classes.dex Lr9d;
public final class Lr9d {
    public boolean A;
    public boolean B;
    public android.graphics.Bitmap C;
    public int D;
    public int E;
    public boolean F;
    public int G;
    public boolean H;
    public org.chromium.url.GURL a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public org.chromium.url.GURL h;
    public org.chromium.url.GURL i;
    public long j;
    public long k;
    public boolean l;
    public String m;
    public boolean n;
    public o0u o;
    public long p;
    public boolean q;
    public boolean r;
    public org.chromium.chrome.browser.profiles.OtrProfileId s;
    public boolean t;
    public int u;
    public long v;
    public int w;
    public boolean x;
    public v89 y;
    public boolean z;

    public r9d()
    {
        this.o = new o0u(0, 0, 2);
        this.q = 1;
        this.u = 0;
        this.z = 1;
        return;
    }

    public final org.chromium.chrome.browser.download.DownloadInfo a()
    {
        org.chromium.chrome.browser.download.DownloadInfo v0_1 = new org.chromium.chrome.browser.download.DownloadInfo();
        boolean v1_5 = this.a;
        if (!v1_5) {
            v1_5 = ejj.a;
        }
        v0_1.a = v1_5;
        v0_1.b = this.b;
        v0_1.c = this.c;
        v0_1.d = this.d;
        v0_1.e = this.e;
        v0_1.f = this.f;
        v0_1.g = this.g;
        boolean v1_4 = this.h;
        if (!v1_4) {
            v1_4 = ejj.a;
        }
        v0_1.h = v1_4;
        boolean v1_6 = this.i;
        if (!v1_6) {
            v1_6 = ejj.a;
        }
        v0_1.i = v1_6;
        v0_1.j = this.j;
        v0_1.k = this.k;
        boolean v1_9 = this.m;
        v0_1.l = v1_9;
        v0_1.m = this.o;
        v0_1.n = this.p;
        v0_1.o = this.q;
        v0_1.p = this.s;
        boolean v2_4 = this.t;
        v0_1.q = this.u;
        v0_1.r = this.w;
        v89 v3_2 = this.y;
        if (v3_2 == null) {
            v0_1.s = jko.a(v1_9, v2_4);
        } else {
            v0_1.s = v3_2;
        }
        v0_1.t = this.z;
        v0_1.u = this.A;
        v0_1.v = this.C;
        v0_1.w = this.D;
        v0_1.x = this.E;
        v0_1.y = this.F;
        v0_1.z = this.G;
        return v0_1;
    }
}
