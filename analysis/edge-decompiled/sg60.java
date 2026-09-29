// classes.dex Lsg60;
public final class Lsg60 {
    public hcd a;

    public final void a(rg60 p6)
    {
        long v0_0 = d090.a;
        v89 v2_1 = p6.b.s;
        int v3 = p6.c;
        qg60 v4_1 = new qg60();
        v4_1.a = this;
        v4_1.b = p6;
        zcw v1_1 = new zcw();
        v1_1.a = v2_1;
        v1_1.b = v3;
        v1_1.c = v4_1;
        v1_1.d = android.os.SystemClock.elapsedRealtime();
        v0_0.getClass();
        android.os.Handler v5_2 = v0_0.a(v2_1);
        if (v5_2 != null) {
            v1_1.d = v5_2.d;
        }
        if (!v0_0.c) {
            v0_0.c = 1;
            v4_1.run();
            android.os.Handler v5_6 = v0_0.b;
            b090 v6_2 = new b090();
            v6_2.a = v0_0;
            v5_6.postDelayed(v6_2, 350);
            return;
        } else {
            v0_0.a.add(v1_1);
            return;
        }
    }

    public final hcd b()
    {
        hcd v0 = this.a;
        if (v0 == null) {
            v0 = gcd.a;
            this.a = v0;
        }
        return v0;
    }

    public final void c(v89 p7)
    {
        d090.a.a(p7);
        uc3 v6_2 = this.b();
        ycd v0_1 = v6_2.c;
        int v1_0 = v0_1.b(p7);
        if (v1_0 != 0) {
            int v1_1 = v1_0.a;
            v6_2.d.e(af9.a, 3, v1_1, 0);
            v6_2.e.e(af9.a, 3, v1_1, 0);
            v6_2.a.h(v1_1);
            v0_1.c(p7);
            return;
        } else {
            return;
        }
    }
}
