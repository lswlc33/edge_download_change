// source: classes2.dex class: org/chromium/components/download/NetworkStatusListenerAndroid
package org.chromium.components.download;
public final class NetworkStatusListenerAndroid implements n13 {
    public static u4t b;
    public long a;

    public static u4t a()
    {
        u4t v0_0 = org.chromium.components.download.NetworkStatusListenerAndroid.b;
        if (v0_0 == null) {
            u4t v0_2 = new u4t();
            v0_2.d = 0;
            v0_2.e = new jxt();
            t4t v1_0 = new baf(daf.a("NetworkStatusListener", "\u200borg.chromium.components.download.NetworkStatusListenerAndroid$Helper.class"));
            v1_0.start();
            android.os.Handler v2_3 = new android.os.Handler(v1_0.getLooper());
            v0_2.a = v2_3;
            t4t v1_3 = new t4t(1);
            v1_3.b = v0_2;
            v2_3.post(v1_3);
            org.chromium.components.download.NetworkStatusListenerAndroid.b = v0_2;
            return v0_2;
        } else {
            return v0_0;
        }
    }

    public static org.chromium.components.download.NetworkStatusListenerAndroid create(long p1)
    {
        org.chromium.components.download.NetworkStatusListenerAndroid v0_1 = new org.chromium.components.download.NetworkStatusListenerAndroid();
        v0_1.a = p1;
        int v1_1 = org.chromium.components.download.NetworkStatusListenerAndroid.a();
        v1_1.e.a(v0_1);
        if (v1_1.c) {
            v0_1.d(v1_1.d);
        }
        return v0_1;
    }

    public final void c(int p5)
    {
        long v0 = this.a;
        if (v0 != 0) {
            J.N.VIJ(43, p5, v0);
        }
        return;
    }

    public final void clearNativePtr()
    {
        long v0_0 = org.chromium.components.download.NetworkStatusListenerAndroid.a();
        android.os.Handler v1 = v0_0.a;
        t4t v2_1 = new t4t(0);
        v2_1.b = v0_0;
        v1.post(v2_1);
        v0_0.e.f(this);
        this.a = 0;
        return;
    }

    public final void d(int p5)
    {
        long v0 = this.a;
        if (v0 != 0) {
            J.N.VIJ(44, p5, v0);
        }
        return;
    }

    public final int getCurrentConnectionType()
    {
        return org.chromium.components.download.NetworkStatusListenerAndroid.a().d;
    }
}
