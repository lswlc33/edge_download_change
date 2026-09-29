// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/DownloadInfo
package org.chromium.chrome.browser.download;
public final class DownloadInfo {
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
    public String l;
    public o0u m;
    public long n;
    public boolean o;
    public org.chromium.chrome.browser.profiles.OtrProfileId p;
    public int q;
    public int r;
    public v89 s;
    public boolean t;
    public boolean u;
    public android.graphics.Bitmap v;
    public int w;
    public int x;
    public boolean y;
    public int z;

    public static org.chromium.chrome.browser.download.DownloadInfo a(org.chromium.components.offline_items_collection.OfflineItem p8, org.chromium.components.offline_items_collection.OfflineItemVisuals p9)
    {
        r9d v0_0 = p8.y;
        boolean v1_0 = 1;
        long v3_1 = 2;
        if (v0_0 == 2) {
            v3_1 = 1;
        } else {
            if (v0_0 != 3) {
                if ((v0_0 == 4) || (v0_0 == 5)) {
                    v3_1 = 3;
                } else {
                    v3_1 = 0;
                }
            }
        }
        int v4_1;
        r9d v0_2 = new r9d();
        int v4_0 = p8.a;
        v0_2.y = v4_0;
        android.graphics.Bitmap v5 = 0;
        if (v4_0 != 0) {
            v4_1 = v4_0.b;
        } else {
            v4_1 = 0;
        }
        v0_2.m = v4_1;
        v0_2.e = p8.b;
        v0_2.g = p8.q;
        v0_2.f = p8.c;
        v0_2.A = p8.e;
        v0_2.v = p8.o;
        v0_2.z = p8.p;
        v0_2.c = p8.r;
        v0_2.a = p8.s;
        v0_2.i = p8.t;
        int v4_10 = p8.v;
        if (v4_10 == 0) {
            v4_10 = "";
        }
        v0_2.s = org.chromium.chrome.browser.profiles.OtrProfileId.a(v4_10);
        v0_2.u = v3_1;
        if (p8.y != 6) {
            v1_0 = 0;
        }
        v0_2.r = v1_0;
        v0_2.q = p8.z;
        v0_2.j = p8.B;
        v0_2.k = p8.k;
        v0_2.o = p8.C;
        v0_2.p = p8.F;
        v0_2.w = p8.G;
        v0_2.x = p8.H;
        v0_2.B = p8.g;
        if (p9 != 0) {
            v5 = p9.a;
        }
        v0_2.C = v5;
        v0_2.D = p8.X;
        v0_2.E = p8.I;
        v0_2.F = p8.h;
        v0_2.H = 0;
        return v0_2.a();
    }

    public static org.chromium.chrome.browser.download.DownloadInfo createDownloadInfo(String p3, String p4, String p5, org.chromium.url.GURL p6, String p7, long p8, long p10, org.chromium.chrome.browser.profiles.OtrProfileId p12, int p13, int p14, boolean p15, boolean p16, boolean p17, boolean p18, org.chromium.url.GURL p19, org.chromium.url.GURL p20, long p21, long p23, int p25, boolean p26, int p27, boolean p28, boolean p29, int p30)
    {
        r9d v1_3;
        String v7_1 = org.chromium.chrome.browser.download.MimeUtils.remapGenericMimeType(p7, p6.j(), p4);
        if (p14 != -1) {
            v1_3 = Long.valueOf(p10);
        } else {
            v1_3 = 0;
        }
        o0u v0_1 = new o0u(p8, v1_3, 0);
        r9d v1_1 = new r9d();
        v1_1.j = p8;
        v1_1.k = p10;
        v1_1.f = p4;
        v1_1.m = p3;
        v1_1.e = p4;
        v1_1.g = p5;
        v1_1.n = p16;
        v1_1.s = p12;
        v1_1.r = p15;
        v1_1.q = p17;
        v1_1.B = p18;
        v1_1.c = v7_1;
        v1_1.i = p19;
        v1_1.o = v0_1;
        v1_1.h = p20;
        v1_1.u = p13;
        v1_1.p = p21;
        v1_1.v = p23;
        v1_1.w = p25;
        v1_1.x = p26;
        v1_1.a = p6;
        v1_1.E = p27;
        v1_1.G = p30;
        v1_1.A = p28;
        v1_1.H = p29;
        return v1_1.a();
    }
}
