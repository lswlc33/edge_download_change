package c8;
public class s6 extends androidx.fragment.app.Fragment implements o8.b, c8.wa, r4.f, android.content.ComponentCallbacks2, o8.e, e8.n0$a, e8.k$a, e8.l$a, e8.c0$a, e8.i$a, e8.i0$a, e8.k0$a, e8.j$a, o8.d, o8.c {
    public mark.via.common.widget.t A0;
    public int A1;
    public mark.via.common.widget.m B0;
    public boolean B1;
    public mark.via.common.widget.f C0;
    public boolean C1;
    public android.content.BroadcastReceiver D0;
    public boolean D1;
    public android.webkit.ValueCallback E0;
    public boolean E1;
    public android.webkit.ValueCallback F0;
    public final v5.c F1;
    public android.webkit.WebChromeClient$CustomViewCallback G0;
    public j8.q G1;
    public long H0;
    public boolean H1;
    public int I0;
    public final android.view.View$OnLongClickListener I1;
    public c8.xa J0;
    public int J1;
    public d8.e K0;
    public e8.z0$a K1;
    public o4.a L0;
    public String[] L1;
    public ab.a M0;
    public final androidx.activity.result.b M1;
    public android.view.GestureDetector N0;
    public mark.via.download.e N1;
    public mark.via.download.j1 O0;
    public String O1;
    public c5.a P0;
    public final androidx.activity.result.b P1;
    public n5.b Q0;
    public final androidx.activity.result.b Q1;
    public final c8.tc R0;
    public final androidx.activity.result.b R1;
    public final java.util.Map S0;
    public final androidx.activity.result.b S1;
    public long T0;
    public long T1;
    public int U0;
    public com.tuyafeng.support.widget.z U1;
    public boolean V0;
    public int W0;
    public int X0;
    public boolean Y0;
    public boolean Z0;
    public boolean a1;
    public boolean b1;
    public final android.graphics.drawable.ColorDrawable c1;
    public long d1;
    public ref.WeakReference e1;
    public String f1;
    public int g1;
    public j8.d h1;
    public String i1;
    public mark.via.common.widget.k1 j1;
    public boolean k1;
    public boolean l1;
    public c8.ua m0;
    public o5.a$a m1;
    public w9.l n0;
    public boolean n1;
    public c8.f8 o0;
    public final androidx.activity.o o1;
    public android.widget.FrameLayout p0;
    public final j5.c p1;
    public android.widget.ProgressBar q0;
    public boolean q1;
    public android.view.View r0;
    public boolean r1;
    public com.tuyafeng.support.widget.v s0;
    public int s1;
    public android.widget.ImageView t0;
    public int[] t1;
    public com.tuyafeng.support.widget.a u0;
    public mark.via.common.widget.v0 u1;
    public mark.via.common.widget.g0 v0;
    public boolean v1;
    public android.widget.LinearLayout w0;
    public boolean w1;
    public mark.via.common.widget.n0 x0;
    public final wa.a x1;
    public android.widget.LinearLayout y0;
    public final java.util.Map y1;
    public android.view.ViewGroup z0;
    public int z1;

    public s6()
    {
        this.R0 = new c8.tc();
        this.S0 = new java.util.HashMap();
        this.T0 = 0;
        this.V0 = 0;
        this.W0 = -1;
        this.X0 = -1;
        this.Y0 = 0;
        this.Z0 = 0;
        this.a1 = 0;
        this.b1 = 0;
        this.c1 = new android.graphics.drawable.ColorDrawable(0);
        this.d1 = -1;
        this.e1 = 0;
        this.g1 = 0;
        this.i1 = 0;
        this.j1 = 0;
        this.k1 = 0;
        this.l1 = 0;
        this.m1 = 0;
        this.n1 = 0;
        this.o1 = new c8.s6$k(this, 1);
        this.p1 = new c8.s6$m(this);
        this.q1 = 1;
        this.r1 = 0;
        this.v1 = 0;
        this.w1 = 0;
        this.x1 = new wa.r(this);
        this.y1 = new java.util.HashMap();
        this.z1 = 0;
        this.A1 = 0;
        this.B1 = 0;
        this.C1 = 0;
        this.D1 = 0;
        this.E1 = 0;
        this.F1 = new c8.s6$e(this);
        this.G1 = 0;
        this.H1 = 0;
        this.I1 = new c8.s6$g(this);
        this.M1 = this.w2(new e.f(), new c8.p1(this));
        this.N1 = 0;
        this.O1 = 0;
        this.P1 = z8.z1.d(this, new c8.a2(this));
        this.Q1 = z8.z1.d(this, new c8.l2(this));
        this.R1 = this.w2(new x5.d(), new c8.w2(this));
        this.S1 = z8.z1.e(this, 0, 0);
        return;
    }

    public static synthetic void A3(c8.s6 p0, String p1)
    {
        j9.a.c(p0.I(), p1);
        return;
    }

    public static synthetic void A4(c8.s6 p0, String p1, android.os.Bundle p2)
    {
        p0.getClass();
        if (p2.getString("id") != null) {
            p0.m0.y1();
        }
        p0.L0().x("bookmarkDialogResult2");
        return;
    }

    public static synthetic void A5(c8.s6 p2, mark.via.common.widget.o0 p3)
    {
        p2.getClass();
        c8.h6 v0_1 = new Object[0];
        pc.a.a("init home view done", v0_1);
        p2.o0.setWindowBackgroundImage(p3);
        p2.p0.post(new c8.h6(p2));
        return;
    }

    public static synthetic void A6(c8.s6 p0)
    {
        p0.fb();
        return;
    }

    public static synthetic void A7(c8.s6 p0, String p1)
    {
        p0.v8(p1);
        return;
    }

    public static synthetic j7.f B3(c8.s6 p8, String p9, String p10, String p11, int p12, String p13)
    {
        p8.getClass();
        java.util.List v5 = j8.u.a(p13);
        if (!v5.isEmpty()) {
            z8.h.c(p8.j0(), new c8.e5(p8, p9, p10, p11, v5, p12));
            return 0;
        } else {
            g6.n.q(p8.I(), x7.u.L1);
            return 0;
        }
    }

    public static synthetic void B4(c8.s6 p3, mark.via.download.e p4, w.d p5)
    {
        p3.getClass();
        int v0_6 = ((Integer) p5.a).intValue();
        if (v0_6 != 2) {
            if (v0_6 != 3) {
                android.content.Context v4_2 = p3.P0.j(((g5.c) p5.b));
                if (v4_2 > 0) {
                    mark.via.download.a.c(p3.I(), v4_2);
                    g6.n.r(p3.I(), x7.u.b4, x7.u.yh, new c8.c3(p3));
                }
                return;
            } else {
                g6.n.r(p3.I(), x7.u.Wf, x7.u.S5, new c8.b3(p3, p4));
                return;
            }
        } else {
            g6.n.q(p3.I(), x7.u.R3);
            return;
        }
    }

    public static synthetic void B5(c8.s6 p2, String p3, String p4, android.view.View p5)
    {
        p2.getClass();
        if (z8.b0.p(p3)) {
            android.content.Context v3_2 = p2.I();
            Object[] v0_1 = new Object[1];
            v0_1[0] = p4;
            g6.n.s(v3_2, p2.Y0(x7.u.gg, v0_1));
        }
        return;
    }

    public static synthetic String B6(c8.s6 p0)
    {
        return p0.G8();
    }

    public static synthetic void B7(c8.s6 p0, boolean p1)
    {
        p0.u8(p1);
        return;
    }

    public static synthetic void C3(c8.s6 p3, String p4, android.os.Bundle p5)
    {
        p3.getClass();
        java.util.Iterator v4_1 = l8.b.a(p5);
        if ((v4_1 != null) && (!v4_1.isEmpty())) {
            java.util.Iterator v4_2 = v4_1.iterator();
            while (v4_2.hasNext()) {
                int v5_3 = ((l8.a) v4_2.next());
                r4.b v0_0 = v5_3.b();
                if (v0_0 == 1) {
                    p3.m0.j1(v5_3.a(), 1);
                } else {
                    if (v0_0 == 2) {
                        p3.m0.j1(v5_3.a(), 0);
                    } else {
                        if (v0_0 == 3) {
                            p3.g(new r4.b(v5_3.a()), p3.m0.S0(), 0);
                        } else {
                            p3.Da(v5_3.a());
                        }
                    }
                }
            }
        }
        p3.L0().x("result");
        return;
    }

    public static synthetic void C4(c8.s6 p2, android.view.View p3, w5.k$p p4)
    {
        p2.getClass();
        if (!g6.e.e(p4.c, 1)) {
            autodispose2.n v3_7 = p2.n0.v();
            int v1_0 = p4.b;
            if (v3_7.p() != v1_0) {
                v3_7.I(v1_0);
                p2.n0.q0(v3_7);
            }
            ((autodispose2.n) p2.m0.O1(z8.c1.n(p4.c[0]), p4.b).I(u8.b.a(p2.b1()))).a(new c8.h0(p2), new x7.g0());
            return;
        } else {
            return;
        }
    }

    public static synthetic void C5(c8.s6 p3, android.animation.ValueAnimator p4)
    {
        if (p3.g1()) {
            android.widget.LinearLayout v4_3;
            int v0_3 = ((Integer) p4.getAnimatedValue()).intValue();
            if (p4.getAnimatedFraction() == 1065353216) {
                v4_3 = 0;
            } else {
                v4_3 = 1;
            }
            p3.o0.k(v0_3, v4_3);
            p3.w0.setBackgroundColor(v0_3);
            mark.via.common.widget.m v3_1 = p3.B0;
            if (v3_1 != null) {
                v3_1.setBackgroundColor(v0_3);
            }
        }
        return;
    }

    public static synthetic mark.via.common.widget.m C6(c8.s6 p0)
    {
        return p0.B0;
    }

    public static synthetic c8.f8 C7(c8.s6 p0)
    {
        return p0.o0;
    }

    public static synthetic void D3(c8.s6 p2)
    {
        androidx.fragment.app.q v0 = p2.s0();
        if (v0 != null) {
            v0.runOnUiThread(new c8.g0(p2, v0));
            return;
        } else {
            return;
        }
    }

    public static synthetic void D4(String p2)
    {
        pa.r.i().c(p2, 0);
        return;
    }

    public static synthetic void D5(c8.s6 p5)
    {
        int v0_1;
        int v0_2 = p5.n0.K1();
        android.content.Context v1_0 = (v0_2 ^ 1);
        p5.n0.b(v1_0);
        p5.Ma(v1_0);
        p5.ub();
        android.content.Context v1_1 = p5.I();
        if (v0_2 != 0) {
            v0_1 = x7.u.a7;
        } else {
            v0_1 = x7.u.b7;
        }
        Object[] v3_1 = new Object[1];
        v3_1[0] = p5.X0(x7.u.G);
        g6.n.s(v1_1, p5.Y0(v0_1, v3_1));
        return;
    }

    public static synthetic void D6(c8.s6 p0, int p1, int p2, String p3, String p4, String p5)
    {
        p0.n9(p1, p2, p3, p4, p5);
        return;
    }

    public static synthetic void D7(c8.s6 p0)
    {
        p0.W9();
        return;
    }

    public static synthetic void E3(c8.s6 p0, android.view.View p1)
    {
        p0.b8(1);
        return;
    }

    public static synthetic void E4(c8.s6 p3, android.widget.ImageView p4)
    {
        p3.getClass();
        p4.setClickable(1);
        p4.setFocusable(1);
        p4.setId(x7.p.s);
        p4.setImageResource(x7.o.E0);
        p4.setImageDrawable(lb.b.a(p3.I(), x7.o.E0, x7.u.ge));
        p4.setColorFilter(-1);
        p4.setBackgroundResource(x7.o.d);
        p4.setContentDescription(p3.X0(x7.u.kg));
        p4.setVisibility(8);
        if (android.os.Build$VERSION.SDK_INT >= 21) {
            c8.g.a(p4, ((float) g6.y.h(p3.I(), 1094713344)));
        }
        p3.z0.addView(p4);
        return;
    }

    public static synthetic void E5(c8.s6 p1, String p2, android.os.Bundle p3)
    {
        p1.getClass();
        p1.m0.F1(p3.getStringArray("tabs"), p3.getString("selected"));
        p1.L0().x("restore_tabs");
        return;
    }

    public static synthetic void E6(c8.s6 p0, String p1)
    {
        p0.m9(p1);
        return;
    }

    public static synthetic void E7(c8.s6 p0, int p1, int p2)
    {
        p0.Z7(p1, p2);
        return;
    }

    public static synthetic void F3(c8.s6 p3, String p4)
    {
        if (p4 != 0) {
            p3.getClass();
            if (!p4.isEmpty()) {
                int v1_5;
                w5.k vtmp9 = w5.k.l(p3.I()).d0(x7.u.xa).J(p4).N(17039360, 0).R(17039361, new c8.o2(p3, p4));
                if (!i6.i0.a.t(p4)) {
                    v1_5 = x7.u.Mb;
                } else {
                    v1_5 = x7.u.R;
                }
                vtmp9.V(v1_5, new c8.p2(p3, p4)).f0();
                return;
            }
        }
        g6.n.q(p3.I(), x7.u.wa);
        return;
    }

    public static synthetic void F4(c8.s6 p0)
    {
        p0.zb();
        return;
    }

    public static synthetic void F5(c8.s6 p0, android.view.View p1)
    {
        p0.V9();
        return;
    }

    public static synthetic void F6(c8.s6 p0, v9.f p1)
    {
        p0.t8(p1);
        return;
    }

    public static synthetic void F7(c8.s6 p0, r4.a p1)
    {
        p0.i8(p1);
        return;
    }

    public static synthetic void G3(c8.s6 p1)
    {
        p1.getClass();
        ia.d.g(pa.r.f(), p1.Q0.d());
        return;
    }

    public static synthetic j7.f G4(c8.s6 p2, Integer p3)
    {
        int v3_6;
        p2.getClass();
        if (p3 != 0) {
            v3_6 = p3.intValue();
        } else {
            v3_6 = 4;
        }
        if (v3_6 != 0) {
            if (v3_6 != 3) {
                if (v3_6 != 1) {
                    if (v3_6 == 4) {
                        g6.n.q(p2.I(), x7.u.M1);
                    }
                } else {
                    g6.n.q(p2.I(), x7.u.N1);
                }
            } else {
                g6.n.q(p2.I(), x7.u.yf);
            }
        } else {
            p2.M7();
            g6.n.q(p2.I(), x7.u.Fa);
            android.content.Context v2_4 = p2.x0;
            if (v2_4 != null) {
                v2_4.v(0);
            }
        }
        return 0;
    }

    public static synthetic void G5(c8.s6 p2, android.view.View p3, w5.k$p p4)
    {
        int v3_0 = p2.i();
        if (v3_0 > 0) {
            p4 = new int[v3_0];
            int v3_1 = (v3_0 - 1);
            int v0 = 0;
            while (v3_1 >= 0) {
                int v1 = (v0 + 1);
                p4[v0] = v3_1;
                v3_1--;
                v0 = v1;
            }
            p2.Y7(p4);
            return;
        } else {
            return;
        }
    }

    public static synthetic void G6(c8.s6 p0)
    {
        p0.gb();
        return;
    }

    public static synthetic void G7(c8.s6 p0, String[] p1, e8.z0$a p2)
    {
        p0.pa(p1, p2);
        return;
    }

    public static synthetic void H3(c8.s6 p0, String[] p1, android.widget.AdapterView p2, android.view.View p3, int p4, long p5)
    {
        p0.getClass();
        p0.N7(p1[p4], p0.E8());
        return;
    }

    public static synthetic Integer H4(c8.s6 p0)
    {
        return Integer.valueOf(w8.e.e(p0.I()));
    }

    public static synthetic void H5(c8.s6 p1, o9.b p2)
    {
        p1.getClass();
        if (p2.c() == null) {
            p1.V7(p2.g(), p2.e());
            return;
        } else {
            p1.l8(p2.c());
            return;
        }
    }

    public static synthetic mark.via.common.widget.f H6(c8.s6 p0)
    {
        return p0.C0;
    }

    public static synthetic boolean H7(c8.s6 p0)
    {
        return p0.v1;
    }

    public static synthetic void I3(c8.s6 p3, int p4, android.widget.TextView p5)
    {
        p3.getClass();
        Object[] v1_1 = new Object[1];
        v1_1[0] = Integer.valueOf(p4);
        p5.setText(String.format(java.util.Locale.ROOT, "%d%%", v1_1));
        p5.setGravity(17);
        p5.setTextSize(0, ((float) x8.h.s(p3.I())));
        p5.setTextColor(g6.e.a(p3.I(), x7.k.k));
        return;
    }

    public static synthetic void I4(e8.z0$a p0, android.view.View p1, w5.k$p p2)
    {
        String[] v1_1 = new String[0];
        p0.a(v1_1, p2.b);
        return;
    }

    public static synthetic void I5(c8.s6 p1, String p2, android.os.Bundle p3)
    {
        p1.getClass();
        if (p3.getInt("result_id", -1) >= 0) {
            w9.n.e().w(1);
            if (!u9.d.m(p1.I(), p1.G8())) {
                g6.n.q(p1.I(), x7.u.z0);
            } else {
                p1.m0.y1();
            }
        }
        p1.L0().x("fav_result");
        return;
    }

    public static synthetic void I6(c8.s6 p0)
    {
        p0.ta();
        return;
    }

    public static synthetic boolean I7(c8.s6 p0)
    {
        return p0.n1;
    }

    public static synthetic x5.a J3(String p1, String p2)
    {
        o9.c v0 = pa.r.d();
        x5.a v1_1 = v0.f(p1);
        if (v1_1 == null) {
            v1_1 = v0.j(p2);
        }
        return x5.a.f(v1_1);
    }

    public static synthetic x5.a J4(String p2)
    {
        x5.a v2_1;
        v9.g v0 = pa.r.i();
        if (v0.j()) {
            v2_1 = 0;
        } else {
            v2_1 = v0.e(p2);
        }
        return x5.a.f(v2_1);
    }

    public static synthetic void J5(c8.s6 p7, float p8, boolean p9)
    {
        int v0_0;
        p7.getClass();
        int v4 = 0;
        int v5 = 1;
        if (((double) Math.abs(p8)) <= 4605380978949069210) {
            v0_0 = 0;
        } else {
            v0_0 = 1;
        }
        if ((p9 != 0) && (v0_0 != 0)) {
            int v9_3;
            if (p8 <= 0) {
                v9_3 = p7.n0.j1();
            } else {
                v9_3 = p7.n0.b2();
            }
            p7.fa(v9_3);
        }
        if (v0_0 != 0) {
            int v8_3;
            if (p8 <= 0) {
                v8_3 = p7.n0.j1();
            } else {
                v8_3 = p7.n0.b2();
            }
            if (v8_3 != 10) {
                if (v8_3 == 11) {
                    v4 = 1;
                }
            } else {
                v4 = -1;
            }
        }
        if (p7.z1 != v4) {
            p7.z1 = v4;
            int v8_6 = p7.i();
            if (v4 != 0) {
                int v9_8 = ((p7.k() + 1) + v4);
                if (v9_8 > 0) {
                    if (v9_8 <= v8_6) {
                        v5 = v9_8;
                    }
                } else {
                    v5 = v8_6;
                }
                p7.v0.l(v5, v8_6);
                return;
            } else {
                p7.v0.l(-1, v8_6);
                return;
            }
        } else {
            return;
        }
    }

    public static synthetic void J6(c8.s6 p0, int p1)
    {
        p0.fa(p1);
        return;
    }

    public static synthetic void K3(c8.s6 p1, boolean p2)
    {
        if (p1.g1()) {
            p1.wb();
            if (!p1.n1) {
                p1.m0.R1(p2);
                return;
            } else {
                android.content.Intent v2_2 = p1.j0().getIntent();
                if (v2_2 != null) {
                    p1.m0.q1(v2_2);
                }
            }
        }
        return;
    }

    public static synthetic void K4(c8.s6 p0, android.view.View p1)
    {
        p0.Y9();
        return;
    }

    public static synthetic void K5(c8.s6 p0, String p1, android.os.Bundle p2)
    {
        p0.m0.w1();
        p0.L0().x("favoriteChanged");
        return;
    }

    public static synthetic android.widget.ImageView K6(c8.s6 p0)
    {
        return p0.t0;
    }

    public static synthetic void L3(c8.s6 p1, x5.a p2)
    {
        int v2_1;
        p1.getClass();
        if (!p2.d()) {
            v2_1 = 0;
        } else {
            v2_1 = ((o9.a) p2.b()).b();
        }
        p1.M9(v2_1);
        return;
    }

    public static synthetic void L4(c8.s6 p3, String p4, String p5, Integer p6)
    {
        p3.getClass();
        if ((p6.intValue() != -1) && ((!w8.e.a.contains("huawei")) || (p3.n0.M2() >= 1))) {
            a8.u1.v3(p4, p5).f3(p3.x0(), 0);
            return;
        } else {
            a8.u1 v4_2 = p3.n0;
            v4_2.a1((v4_2.M2() + 1));
            w5.k.l(p3.I()).d0(x7.u.w0).I(x7.u.x8).V(x7.u.gc, new c8.j2(p3)).N(17039360, 0).f0();
            return;
        }
    }

    public static synthetic void L5(c8.s6 p0, String p1, android.os.Bundle p2)
    {
        p0.getClass();
        String v1_2 = p2.getString("id");
        if (v1_2 != null) {
            String v1_3 = pa.r.i().h(v1_2);
            if (v1_3 != null) {
                p0.t8(v1_3);
            }
        }
        p0.x0().x("passresult");
        return;
    }

    public static synthetic boolean L6(c8.s6 p0)
    {
        return p0.R8();
    }

    private void La(int p3, int p4, boolean p5)
    {
        f8.h v0_2 = ((f8.h) this.x0().n0(f8.h.v0));
        if (v0_2 != null) {
            v0_2.f3(p3, p4, p5);
            return;
        } else {
            return;
        }
    }

    public static synthetic void M3(c8.s6 p0, android.content.Intent p1, android.view.View p2)
    {
        p0.getClass();
        try {
            p0.N2(p1);
            return;
        } catch (Exception) {
            g6.n.q(p0.I(), x7.u.qg);
            return;
        }
    }

    public static synthetic void M4(c8.s6 p0, ref.WeakReference p1, Integer p2)
    {
        p0.getClass();
        if ((((t4.b) p1.get()) != null) && (p2.intValue() == 3)) {
            p0.M7();
        }
        return;
    }

    public static synthetic void M5(c8.s6 p1)
    {
        p1.C0.setTranslationY(0);
        return;
    }

    public static synthetic mark.via.common.widget.n0 M6(c8.s6 p0)
    {
        return p0.x0;
    }

    public static synthetic void N3(c8.s6 p0, String p1, android.view.View p2, w5.k$p p3)
    {
        if (p0.m0.z0(p1)) {
            w9.n.e().s(1);
            p0.m0.y1();
        }
        return;
    }

    public static synthetic void N4(c8.s6 p0, Boolean p1)
    {
        p0.getClass();
        if (p1.booleanValue()) {
            g6.n.q(p0.j0(), x7.u.da);
        }
        return;
    }

    public static synthetic void N5(c8.s6 p1, com.tuyafeng.support.widget.z p2)
    {
        p2.setTitle(p1.X0(x7.u.Qg));
        return;
    }

    public static synthetic void N6(c8.s6 p0)
    {
        p0.r8();
        return;
    }

    public static synthetic void O3(c8.s6 p0, java.util.List p1, int p2, boolean p3, String[] p4, android.widget.AdapterView p5, android.view.View p6, int p7, long p8)
    {
        p0.getClass();
        boolean v1_3 = ((ja.c) p1.get(p7));
        if (v1_3.d() != p2) {
            if (p3 == 0) {
                p0.n0.I(v1_3.d());
                if (z8.b4.f(v1_3.d())) {
                    p0.n0.a2(v1_3.a());
                }
            } else {
                p0.n0.v2(v1_3.d());
                if (z8.b4.f(v1_3.d())) {
                    p0.n0.H2(v1_3.a());
                }
            }
            p0.m0.l1();
            boolean v1_6 = p0.G8();
            if (!u9.d.m(p0.I(), v1_6)) {
                boolean v1_7 = i6.i0.a.f(v1_6);
                if ((!android.text.TextUtils.isEmpty(v1_7)) && ((!"pan.baidu.com".equalsIgnoreCase(v1_7)) && ((!"yun.baidu.com".equalsIgnoreCase(v1_7)) && (!"eyun.baidu.com".equalsIgnoreCase(v1_7))))) {
                    p0.ka();
                }
            }
            boolean v1_9 = p0.I();
            String v4_1 = p4[p7];
            Object[] v5_2 = new Object[2];
            v5_2[0] = p0.X0(x7.u.C0);
            v5_2[1] = v4_1;
            g6.n.s(v1_9, p0.Y0(x7.u.n7, v5_2));
            p0.R8();
            return;
        } else {
            return;
        }
    }

    public static synthetic void O4(c8.s6 p0, android.content.DialogInterface p1)
    {
        r4.a v0_2 = p0.L0.d();
        if (v0_2 != null) {
            v0_2.o();
        }
        return;
    }

    public static synthetic void O5(c8.s6 p5, android.app.Activity p6)
    {
        p5.getClass();
        int v0 = z8.b0.B(p6);
        int v2 = 1;
        Object[] v3 = new Object[1];
        v3[0] = Integer.valueOf(v0);
        pc.a.a("on network changed, current: %d", v3);
        if (v0 != 0) {
            boolean v1_0 = p5.P0;
            if ((v1_0) && (v1_0.i())) {
                mark.via.download.a.d(p6);
            }
        }
        if (2 == v0) {
            v2 = 0;
        }
        if (v2 != p5.n0.g2().x()) {
            p5.n0.g2().a0(v2);
            p5.m0.l1();
        }
        return;
    }

    public static synthetic boolean O6(c8.s6 p0)
    {
        return p0.j9();
    }

    public static synthetic void P3(c8.s6 p0, android.view.View p1, w5.k$p p2)
    {
        p0.getClass();
        if (p2.b) {
            w9.a v1_2 = p0.n0.v();
            v1_2.C(0);
            p0.n0.q0(v1_2);
        }
        p0.j0().finish();
        return;
    }

    public static synthetic void P4(e8.z0$a p0, String[] p1, android.view.View p2, w5.k$p p3)
    {
        p0.a(p1, p3.b);
        return;
    }

    public static synthetic void P5(c8.s6 p2)
    {
        if (p2.C1) {
            p2.w0.setVisibility(0);
        }
        if (p2.D1) {
            p2.ab();
        }
        p2.E1 = 0;
        return;
    }

    public static synthetic void P6(c8.s6 p0)
    {
        p0.mb();
        return;
    }

    public static synthetic void Q3(c8.s6 p7, mark.via.common.widget.t p8)
    {
        int v4_2;
        c8.d6 v0_1 = p7.n0.d();
        int v1_1 = p7.U0;
        int v2 = 0;
        if (v1_1 != 0) {
            v4_2 = 0;
        } else {
            v4_2 = 1;
        }
        i9.a v5_0 = p7.n0.E2();
        if (v0_1 == null) {
            if ((v4_2 == 0) || (!v5_0.c())) {
                if (v4_2 != 0) {
                    v1_1 = p7.n0.c0();
                }
                if (!g6.y.C(v1_1)) {
                    int v4_0;
                    int v1_4 = p7.I();
                    int v3 = 17170443;
                    if (v0_1 == null) {
                        if (v2 == 0) {
                            v4_0 = 17170443;
                        } else {
                            v4_0 = x7.m.g;
                        }
                    } else {
                        v4_0 = x7.m.h;
                    }
                    int v1_5 = g6.f.b(v1_4, v4_0);
                    int v4_1 = p7.I();
                    if (v0_1 == null) {
                        if (v2 != 0) {
                            v3 = x7.m.d;
                        }
                    } else {
                        v3 = x7.m.e;
                    }
                    p8.y(v1_5, g6.f.b(v4_1, v3));
                    p8.setTabs(p7.L0.c());
                    p8.setOnDeleteItemClickListener(new c8.y5(p7));
                    p8.setOnNewTabButtonClickListener(new c8.z5(p7));
                    p8.setOnTabItemClickListener(new c8.a6(p7));
                    p8.setOnMoveTabListener(new c8.b6(p7));
                    p8.setOnDeleteTabsListener(new c8.c6(p7));
                    p8.setOnDuplicateTabListener(new c8.d6(p7));
                    return;
                }
            } else {
                if (!v5_0.d()) {
                }
            }
            v2 = 1;
        }
    }

    public static synthetic void Q4(c8.s6 p2, android.net.Uri p3)
    {
        if (p3 != null) {
            p2.I().getContentResolver().takePersistableUriPermission(p3, 3);
            p2.n0.p(p3.toString());
            p2.M8();
            return;
        } else {
            p2.getClass();
            return;
        }
    }

    public static synthetic void Q5(c8.s6 p1, Integer p2)
    {
        p1.getClass();
        if (p2.intValue() != 3) {
            p1.r8();
            return;
        } else {
            p1.c8();
            return;
        }
    }

    public static synthetic void Q6(c8.s6 p0)
    {
        p0.U9();
        return;
    }

    public static synthetic void R3(c8.s6 p1, j8.t p2)
    {
        p1.ib(p2, 0);
        return;
    }

    public static synthetic x5.a R4(String p2, String p3)
    {
        x5.a v2_1;
        v9.g v0 = pa.r.i();
        x5.a v2_4 = v0.m(i6.i0.a.e(p2), p3);
        if (v2_4 != null) {
            v2_1 = v0.f(v2_4);
        } else {
            v2_1 = 0;
        }
        return x5.a.f(v2_1);
    }

    public static synthetic void R5(c8.s6 p1, boolean p2)
    {
        p1.x0.t((p2 ^ 1), 1);
        return;
    }

    public static synthetic void R6(c8.s6 p0)
    {
        p0.rb();
        return;
    }

    public static synthetic void S2(c8.s6 p9, java.util.Map p10)
    {
        java.util.HashSet v0_0 = p9.K1;
        if (v0_0 != null) {
            if (p10) {
                java.util.HashSet v0_2 = new java.util.HashSet();
                String[] v3_3 = p9.L1;
                if (v3_3 != null) {
                    int v4 = v3_3.length;
                    int v5 = 0;
                    while (v5 < v4) {
                        String v6 = v3_3[v5];
                        if (p10.get(v6) != Boolean.FALSE) {
                            v0_2.add(v6);
                        }
                        v5++;
                    }
                }
                String[] v3_0 = new String[0];
                p9.K1.a(((String[]) v0_2.toArray(v3_0)), 0);
                p9.K1 = 0;
                p9.L1 = 0;
                if (v0_2.isEmpty()) {
                    z8.z1.f(p9.j0());
                }
                return;
            } else {
                v0_0.a(p9.L1, 0);
                p9.K1 = 0;
                p9.L1 = 0;
                return;
            }
        } else {
            p9.L1 = 0;
            return;
        }
    }

    public static synthetic void S3(c8.s6 p0, androidx.lifecycle.h p1, androidx.lifecycle.Lifecycle$Event p2)
    {
        p0.getClass();
        if (p2 == androidx.lifecycle.Lifecycle$Event.ON_DESTROY) {
            int v1_1 = p0.o0;
            if (v1_1 != 0) {
                v1_1.setBlurEnabled(0);
                p0.o0.setDescendantFocusability(262144);
            }
        }
        return;
    }

    public static synthetic void S4(c8.s6 p1, int p2, boolean p3, int p4, android.widget.TextView p5, String p6, mark.via.common.widget.w0 p7)
    {
        p1.getClass();
        p7.setMax(30);
        p7.setProgress(((p2 - 50) / 5));
        if (!p3) {
            p4 = 100;
        }
        p7.setHighlightProgress(((p4 - 50) / 5));
        z8.r3.g(p7);
        p7.setMinimumHeight(g6.y.h(p1.I(), 1073741824));
        p7.setOnSeekBarChangeListener(new c8.s6$f(p1, p5, p3, p6));
        return;
    }

    public static synthetic void S5(c8.s6 p1)
    {
        p1.C0.setVisibility(8);
        return;
    }

    public static synthetic void S6(c8.s6 p0)
    {
        p0.ka();
        return;
    }

    public static synthetic j7.f T2(c8.s6 p2, Integer p3)
    {
        int v3_5;
        p2.getClass();
        if (p3 != 0) {
            v3_5 = p3.intValue();
        } else {
            v3_5 = 2;
        }
        if (v3_5 != 0) {
            if (v3_5 == 1) {
                g6.n.q(p2.I(), x7.u.N1);
            }
        } else {
            int v3_2 = p2.C8();
            d8.g.c().e(v3_2.getUrl());
            v3_2.setAccentColor(-1);
            p2.yb(-1, 0);
            g6.n.q(p2.I(), x7.u.Ea);
        }
        return 0;
    }

    public static synthetic void T3(c8.s6 p5, int[] p6, String p7, android.view.View p8, w5.k$p p9)
    {
        int v3;
        p5.getClass();
        String v8_0 = p9.a;
        if ((v8_0 == null) || (v8_0.length <= 0)) {
            v3 = 0;
        } else {
            int v2 = 0;
            v3 = 0;
            while (v2 < v8_0.length) {
                int v4_0 = v8_0[v2];
                if (v4_0 > 0) {
                    v3 += p6[v4_0];
                }
                v2++;
            }
        }
        int v6_2;
        int v6_1 = p9.c;
        if ((v6_1 == 0) || (v6_1.length <= 0)) {
            v6_2 = 0;
        } else {
            v6_2 = v6_1[0];
        }
        if ((!g6.p.f(v6_2)) || (v3 != 0)) {
            z8.h.b(new c8.k2(p7, p5.E8(), v3, v6_2));
            g6.n.q(p5.I(), x7.u.Pa);
            return;
        } else {
            return;
        }
    }

    public static synthetic void T4(c8.s6 p1, android.view.View p2)
    {
        p2 = p1.G8();
        if ((p2 != null) && (!p2.isEmpty())) {
            z8.f1.k(p1.I(), p2);
        }
        return;
    }

    public static synthetic void T5(c8.s6 p0, int p1)
    {
        p0.m0.X1(p1);
        return;
    }

    public static synthetic void T6(c8.s6 p0)
    {
        p0.ba();
        return;
    }

    public static synthetic void U2(c8.s6 p0, String p1, android.view.View p2, w5.k$p p3)
    {
        p0.Da(p1);
        return;
    }

    public static synthetic void U3(c8.s6 p7, java.util.List p8, android.view.View p9, w5.k$p p10)
    {
        int v9_1 = (p7.k() + 1);
        int v0 = p8.size();
        int v2 = 0;
        while (v2 < v0) {
            int v5_1;
            c8.ua v3 = p7.m0;
            String v4_0 = p8.get(v2);
            if (v2 != (v0 - 1)) {
                v5_1 = 0;
            } else {
                v5_1 = 1;
            }
            int v6 = (v9_1 + 1);
            v3.g1(((String) v4_0), v5_1, v9_1);
            v2++;
            v9_1 = v6;
        }
        return;
    }

    public static synthetic void U4(c8.s6 p1, android.view.View p2)
    {
        p1.m0.j1(0, 1);
        return;
    }

    public static synthetic void U5(c8.s6 p0, Boolean p1)
    {
        p0.getClass();
        if (p1.booleanValue()) {
            g6.n.q(p0.j0(), x7.u.ca);
        }
        return;
    }

    public static synthetic void U6(c8.s6 p0)
    {
        p0.eb();
        return;
    }

    public static synthetic j7.f V2(k8.o p0, Integer p1)
    {
        Integer v1_1;
        if (p1 != null) {
            v1_1 = p1.intValue();
        } else {
            v1_1 = 0;
        }
        p0.a(Integer.valueOf(v1_1));
        return 0;
    }

    public static synthetic void V3(c8.s6 p1, androidx.fragment.app.FragmentManager p2, String p3, android.os.Bundle p4)
    {
        p1.getClass();
        String v3_1 = p4.getString("text", 0);
        if (v3_1 != null) {
            p1.n0.e2(v3_1);
            p1.M7();
        }
        p2.x("edit_text_result");
        return;
    }

    public static synthetic void V4(c8.s6 p1)
    {
        p1.y0.setVisibility(0);
        return;
    }

    public static synthetic void V5(c8.s6 p0, int p1)
    {
        p0.m0.C0(p1);
        return;
    }

    public static synthetic o4.a V6(c8.s6 p0)
    {
        return p0.L0;
    }

    public static synthetic void W2(c8.s6 p0, boolean p1, int p2, android.content.DialogInterface p3)
    {
        if (p1 == 0) {
            p0.getClass();
            return;
        } else {
            p0.aa((p2 | 4));
            return;
        }
    }

    public static synthetic void W3(c8.s6 p0, String p1, String p2, String p3, android.widget.AdapterView p4, android.view.View p5, int p6, long p7)
    {
        p0.getClass();
        if (p6 != 1) {
            if (p6 != 2) {
                v9.f v4_3 = new v9.f();
                v4_3.j(java.util.UUID.randomUUID().toString().toLowerCase(java.util.Locale.ROOT));
                v4_3.k(i6.i0.a.e(p1));
                v4_3.o(p1);
                v4_3.p(p2);
                v4_3.m(p3);
                v4_3.i(System.currentTimeMillis());
                v4_3.n(v4_3.a());
                ((autodispose2.r) x6.o.g(new c8.j3(v4_3)).l(g7.a.c()).j(w6.b.b()).m(u8.b.a(p0.b1()))).a(new c8.k3(p0), new x7.g0());
                return;
            } else {
                return;
            }
        } else {
            g7.a.c().d(new c8.i3(p1));
            return;
        }
    }

    public static synthetic void W4(c8.s6 p0, android.view.View p1)
    {
        p0.j0().finish();
        return;
    }

    public static synthetic x.k0 W5(c8.s6 p17, android.view.View p18, x.k0 p19)
    {
        p17.getClass();
        int v5_2 = r.f.e;
        int v6 = 0;
        x.k0 v3_3 = new x.k0$b(p19).b(((x.k0$m.f() | x.k0$m.b()) | x.k0$m.c()), v5_2).e(x.k0$m.c(), 0).a();
        z8.l3.s(p19);
        if (z8.l3.i(p19, p18)) {
            int v4_13 = p19.f(x.k0$m.b());
            if (!v5_2.equals(v4_13)) {
                if ((p17.n0.K1()) || (!p17.k1)) {
                    int v4_14 = p19.e();
                    int v5_7 = p18.getMeasuredWidth();
                    int v9 = p18.getMeasuredHeight();
                    if ((v4_14 != 0) && ((v5_7 > 0) && (v9 > 0))) {
                        int v2_13 = p19.f(x.k0$m.c());
                        int v4_15 = v4_14.a();
                        int[] v11 = new int[4];
                        v11[0] = 0;
                        v11[1] = 0;
                        v11[2] = 0;
                        v11[3] = 0;
                        int[] v10_2 = new int[4];
                        v10_2[0] = 0;
                        v10_2[1] = 0;
                        v10_2[2] = 0;
                        v10_2[3] = 0;
                        int v13_1 = g6.y.h(p18.getContext(), 1098907648);
                        int v4_17 = v4_15.iterator();
                        while (v4_17.hasNext()) {
                            android.graphics.Rect v14_1 = ((android.graphics.Rect) v4_17.next());
                            if (v14_1.top > v13_1) {
                                if ((v2_13.d <= 0) && (v14_1.bottom >= (v9 - v13_1))) {
                                    int v8_2 = v14_1.left;
                                    int v12_1 = (v13_1 * 2);
                                    if (v8_2 >= v12_1) {
                                        if (v14_1.right >= (v5_7 - v12_1)) {
                                            v10_2[2] = Math.max(v10_2[2], (v5_7 - v8_2));
                                        }
                                    } else {
                                        v10_2[0] = Math.max(v10_2[0], v14_1.right);
                                    }
                                    v10_2[3] = Math.max(v10_2[3], (v9 - v14_1.top));
                                }
                            } else {
                                int v15_2 = v14_1.left;
                                int v8_9 = (v13_1 * 2);
                                if (v15_2 >= v8_9) {
                                    if (v14_1.right >= (v5_7 - v8_9)) {
                                        v11[2] = Math.max(v11[2], (v5_7 - v15_2));
                                    }
                                } else {
                                    v11[0] = Math.max(v11[0], v14_1.right);
                                }
                                v11[1] = Math.max(v11[1], v14_1.bottom);
                            }
                            int v12 = 3;
                        }
                        if ((!p17.b1) || (p17.A0 == null)) {
                            p17.y0.setPadding(v11[0], 0, v11[2], 0);
                            p17.w0.setPadding(v10_2[v6], v6, v10_2[2], v6);
                            p17.u0.setPadding(0, 0, 0, 0);
                        } else {
                            if (p17.W0 != 1) {
                                if (v10_2[3] >= ((p17.w0.getMeasuredHeight() / 2) * 3)) {
                                }
                            } else {
                                if (v11[1] >= ((p17.y0.getMeasuredHeight() / 2) * 3)) {
                                }
                            }
                            p17.y0.setPadding(0, 0, 0, 0);
                            p17.w0.setPadding(0, 0, 0, 0);
                            if (p17.W0 != 1) {
                                p17.u0.setPadding(v10_2[0], 0, v10_2[2], 0);
                            } else {
                                p17.u0.setPadding(v11[0], 0, v11[2], 0);
                            }
                        }
                        p18.setPadding(0, 0, 0, 0);
                    }
                    return v3_3;
                } else {
                    if (android.content.res.Resources.getSystem().getConfiguration().orientation != 2) {
                        p18.setPadding(0, v4_13.b, 0, v4_13.d);
                    } else {
                        p18.setPadding(v4_13.a, 0, v4_13.c, 0);
                    }
                    p17.y0.setPadding(0, 0, 0, 0);
                    p17.w0.setPadding(0, 0, 0, 0);
                    p17.u0.setPadding(0, 0, 0, 0);
                    return v3_3;
                }
            } else {
                p17.x8(p18, p19, 0);
                return v3_3;
            }
        } else {
            p17.x8(p18, p19, 1);
            return v3_3;
        }
    }

    public static synthetic void W6(c8.s6 p0)
    {
        p0.ga();
        return;
    }

    public static synthetic void X2(c8.s6 p4, String p5, String p6)
    {
        p4.getClass();
        int v1_0 = 0;
        if ((p6.length() <= 0) || (!android.text.TextUtils.isDigitsOnly(p6))) {
            p4.S9(0, 0, p5);
            return;
        } else {
            try {
                int v6_1 = Integer.parseInt(p6);
            } catch (Exception) {
                v6_1 = 0;
            }
            int v0_0 = (v6_1 & 63);
            int v6_2 = (v6_1 >> 6);
            if (v6_2 > 0) {
                int v2_2 = (p4.z0.getHeight() - g6.y.h(p4.I(), ((float) v6_2)));
                if (p4.y0.getVisibility() == 0) {
                    v1_0 = p4.y0.getHeight();
                }
                v1_0 = (((v2_2 - v1_0) + g6.f.d(p4.I(), x7.n.B)) + g6.y.h(p4.I(), 1082130432));
            }
            p4.S9(v0_0, v1_0, p5);
            return;
        }
    }

    public static synthetic x5.a X3(String p1)
    {
        return x5.a.f(pa.r.d().j(p1));
    }

    public static synthetic void X4(c8.s6 p0, android.app.PendingIntent p1, android.view.View p2)
    {
        p0.Ha(p1);
        return;
    }

    public static synthetic void X5(c8.s6 p0, String p1)
    {
        p0.m9(p1);
        return;
    }

    public static synthetic void X6(c8.s6 p0, String p1)
    {
        p0.ca(p1);
        return;
    }

    public static synthetic void Y2(c8.s6 p7, mark.via.common.widget.m p8)
    {
        int v4_5;
        boolean v0_1 = p7.n0.d();
        int v1 = p7.U0;
        int v2 = 0;
        if (v1 != 0) {
            v4_5 = 0;
        } else {
            v4_5 = 1;
        }
        int v5_0 = p7.n0.E2();
        if (!v0_1) {
            if ((v4_5 == 0) || (!v5_0.c())) {
                int v4_0;
                if (v4_5 == 0) {
                    v4_0 = v1;
                } else {
                    v4_0 = p7.n0.c0();
                }
                if (!g6.y.C(v4_0)) {
                    int v5_1;
                    int v3_0 = p7.I();
                    int v4_4 = 17170443;
                    if (!v0_1) {
                        if (v2 == 0) {
                            v5_1 = 17170443;
                        } else {
                            v5_1 = x7.m.g;
                        }
                    } else {
                        v5_1 = x7.m.h;
                    }
                    int v3_1 = g6.f.b(v3_0, v5_1);
                    int v7_1 = p7.I();
                    if (!v0_1) {
                        if (v2 != 0) {
                            v4_4 = x7.m.d;
                        }
                    } else {
                        v4_4 = x7.m.e;
                    }
                    p8.l(v3_1, g6.f.b(v7_1, v4_4));
                    p8.setBackgroundColor(v1);
                    return;
                }
            } else {
                if (!v5_0.d()) {
                }
            }
            v2 = 1;
        }
    }

    public static synthetic void Y3(c8.s6 p0, String p1, android.os.Bundle p2)
    {
        p0.getClass();
        if (p2.getString("id") != null) {
            p0.m0.y1();
        }
        p0.L0().x("bookmarkDialogResult2");
        return;
    }

    public static synthetic void Y4(c8.s6 p7, java.util.List p8, android.view.View p9, w5.k$p p10)
    {
        int v9_1 = (p7.k() + 1);
        int v0 = p8.size();
        int v2 = 0;
        while (v2 < v0) {
            int v5_1;
            c8.ua v3 = p7.m0;
            String v4_0 = p8.get(v2);
            if (v2 != (v0 - 1)) {
                v5_1 = 0;
            } else {
                v5_1 = 1;
            }
            int v6 = (v9_1 + 1);
            v3.g1(((String) v4_0), v5_1, v9_1);
            v2++;
            v9_1 = v6;
        }
        return;
    }

    public static synthetic void Y5(c8.s6 p0, android.view.View p1)
    {
        p0.O0.i(p0.I());
        return;
    }

    public static synthetic void Y6(c8.s6 p0, mark.via.download.e p1)
    {
        p0.Xa(p1);
        return;
    }

    public static synthetic void Z2(String p0, String p1, int p2, String p3)
    {
        z8.h2.a(p0, p1, p2, p3);
        return;
    }

    public static synthetic void Z3(c8.s6 p3, Boolean p4)
    {
        p3.getClass();
        if (p4.booleanValue()) {
            g6.n.r(p3.j0(), x7.u.vb, x7.u.yh, new c8.m2(p3));
        }
        return;
    }

    public static synthetic void Z4(c8.s6 p3)
    {
        int v1_4;
        p3.getClass();
        c8.pb vtmp5 = new c8.pb().g(p3.s0).b(p3.C8()).e(p3.J1);
        if ((p3.k1) || (!p3.n0.A1())) {
            v1_4 = 0;
        } else {
            v1_4 = 1;
        }
        vtmp5.c(v1_4).f((p3.k1 ^ 1)).d();
        return;
    }

    public static synthetic void Z5(c8.s6 p0, String p1, String p2, int p3, int p4, int p5, String p6, String p7, android.widget.AdapterView p8, android.view.View p9, int p10, long p11)
    {
        p0.getClass();
        p9 = 0;
        switch (((int) p11)) {
            case 0:
            case 1:
                if (((int) p11) == 1) {
                    p9 = 1;
                }
                p0.m0.i1(p1, p9);
                return;
            case 2:
                p0.m0.k1(p2, 1);
                return;
            case 3:
                String v1_19;
                w5.k v0_43 = p0.I();
                if (p3 != 6) {
                    v1_19 = p1;
                } else {
                    v1_19 = p1.substring((p1.indexOf("://") + 3));
                }
                g6.n.a(v0_43, v1_19, x7.u.ig);
                return;
            case 4:
            case 5:
            case 28:
            case 30:
            default:
                break;
            case 6:
                p0.xa(p2);
                return;
            case 7:
                p0.Ca(p2, p4, p5);
                return;
            case 8:
                p0.p8();
                return;
            case 9:
                p0.j8(p1);
                return;
            case 10:
                if (!p0.m0.l0(p1)) {
                } else {
                    w9.n.e().w(1);
                    g6.n.q(p0.I(), x7.u.z0);
                }
                break;
            case 11:
                if (!p0.m0.A0(p1)) {
                } else {
                    w9.n.e().s(1);
                    p0.m0.y1();
                    return;
                }
            case 12:
            case 13:
                w5.k v0_27;
                if (!z8.w2.t(p1)) {
                    if (!z8.w2.p(p1)) {
                        v0_27 = 0;
                    } else {
                        v0_27 = z8.w2.i(p1);
                    }
                } else {
                    p1.substring(9);
                    try {
                        w5.k v3_12 = java.net.URLDecoder.decode(p1.substring(9), "utf-8");
                    } catch (java.io.UnsupportedEncodingException) {
                    }
                    w5.k v0_31 = pa.r.d().j(v3_12);
                    if (v0_31 == null) {
                    } else {
                        v0_27 = v0_31.b();
                    }
                }
                if (v0_27 != null) {
                    if (((int) p11) != 12) {
                        w5.k v3_16 = w5.k.l(p0.I()).d0(x7.u.x);
                        c8.u2 v5_10 = new Object[1];
                        v5_10[0] = v0_27;
                        v3_16.J(p0.Y0(x7.u.g3, v5_10)).V(x7.u.x, new c8.u2(p0, v0_27)).N(17039360, 0).f0();
                    } else {
                        p0.L0().y1(a8.d0.w0, p0, new c8.t2(p0));
                        g6.i.h(p0, a8.d0, a8.d0.q3(v0_27, 0, 1));
                    }
                } else {
                }
                break;
            case 14:
                p0.m8(p1);
                return;
            case 15:
                w5.k v0_24 = p0.I();
                String v1_15 = p0.m0;
                java.util.Objects.requireNonNull(v1_15);
                t9.a.a(v0_24, p1, new c8.v2(v1_15));
                return;
            case 16:
                t9.e.f(p0.I(), p1);
                p0.m0.y1();
                return;
            case 17:
                w5.k v0_21 = p0.I();
                String v1_14 = p0.m0;
                java.util.Objects.requireNonNull(v1_14);
                t9.e.e(v0_21, new c8.v2(v1_14));
                return;
            case 18:
                w5.k v0_17 = android.net.Uri.decode(p1.substring((p1.lastIndexOf("/") + 1)));
                String v1_11 = new StringBuilder();
                v1_11.append(p1.substring(7, (p1.lastIndexOf(47) + 1)));
                v1_11.append(v0_17);
                w5.k v0_18 = v1_11.toString();
                if (!z8.c1.g(v0_18)) {
                    String v1_13 = p0.I();
                    w5.k v3_11 = new StringBuilder();
                    v3_11.append(p0.R0().getString(x7.u.i3));
                    v3_11.append(v0_18);
                    g6.n.s(v1_13, v3_11.toString());
                    return;
                } else {
                    p0.m0.y1();
                    return;
                }
            case 19:
                p0.N7(p1, p0.F8(p1));
                return;
            case 20:
                p0.Xa(new mark.via.download.e$b().j(p1).k(p0.n0.H1()).c("attachment").d(-1).b());
                return;
            case 21:
                g6.n.e(p0.I(), x7.u.y, x7.u.x3, new c8.x2(p0));
                return;
            case 22:
                w5.k v0_2 = new StringBuilder();
                String v1_1 = p0.F8(p6);
                if ((v1_1 != null) && (!v1_1.isEmpty())) {
                    v0_2.append(p0.X0(x7.u.O9));
                    v0_2.append("\n");
                    v0_2.append(v1_1);
                }
                if ((p6 != null) && (!p6.isEmpty())) {
                    if (v0_2.length() > 0) {
                        v0_2.append("\n\n");
                    }
                    v0_2.append(p0.X0(x7.u.P9));
                    v0_2.append("\n");
                    v0_2.append(p6);
                }
                if ((p1 != null) && (!p1.isEmpty())) {
                    if (v0_2.length() > 0) {
                        v0_2.append("\n\n");
                    }
                    v0_2.append(p0.X0(x7.u.N9));
                    v0_2.append("\n");
                    v0_2.append(p1);
                }
                g6.n.l(p0.I(), x7.u.S, v0_2.toString());
                return;
            case 23:
                p0.q9();
                return;
            case 24:
            case 25:
                if (((int) p11) != 24) {
                    p0.m0.A1(p1);
                } else {
                    p0.m0.j0(p1);
                }
                w5.k v0_52;
                String v1_29 = p0.I();
                if (((int) p11) != 24) {
                    v0_52 = x7.u.Ig;
                } else {
                    v0_52 = x7.u.u1;
                }
                int v4_24 = new Object[1];
                v4_24[0] = i6.i0.a.f(p1);
                g6.n.s(v1_29, p0.Y0(v0_52, v4_24));
                return;
            case 26:
            case 27:
                if (((int) p11) != 26) {
                    p0.m0.B1(p1);
                } else {
                    p0.m0.k0(p1);
                }
                w5.k v0_49;
                String v1_25 = p0.I();
                if (((int) p11) != 26) {
                    v0_49 = x7.u.Ig;
                } else {
                    v0_49 = x7.u.u1;
                }
                int v4_23 = new Object[1];
                v4_23[0] = p1.substring((p1.indexOf("://") + 3));
                g6.n.s(v1_25, p0.Y0(v0_49, v4_23));
                return;
            case 29:
                if (p7 == null) {
                } else {
                    g6.n.a(p0.I(), p7.trim(), x7.u.hg);
                    return;
                }
            case 31:
                z8.f1.k(p0.I(), p1);
                return;
            case 32:
                p0.R9(p1);
                return;
            case 33:
                p0.Sa(p2);
                return;
            case 34:
                p0.Ba(p2);
                return;
            case 35:
                p0.y8(p4, p5);
                return;
            case 36:
                p0.o9(p4, p5, p1, p2, p7, 1);
                return;
            case 37:
                g6.i.g(p0, i8.w);
                return;
            case 38:
                p0.g8(p2);
                return;
        }
        return;
    }

    public static synthetic boolean Z6(c8.s6 p0, String p1)
    {
        return p0.i9(p1);
    }

    public static synthetic void a3(c8.s6 p1)
    {
        p1.N0.setIsLongpressEnabled(1);
        return;
    }

    public static synthetic void a4(c8.s6 p1, t4.b p2, String p3, String p4)
    {
        if (p1.C8() != null) {
            s4.b.g(p2, p1.Q0.a().b(p1.Q0.c(), p3, p4));
            return;
        } else {
            return;
        }
    }

    public static synthetic void a5(c8.s6 p0)
    {
        p0.m0.h2();
        return;
    }

    public static synthetic void a6(c8.s6 p2, String p3, int p4, x5.a p5)
    {
        p2.getClass();
        if (p5.d()) {
            java.io.File v5_2 = ((java.io.File) p5.b());
            if (p3 != 0) {
                p2.S0.put(p3, v5_2.getAbsolutePath());
            }
            p2.L8(p3, v5_2, p4);
            return;
        } else {
            g6.n.q(p2.I(), x7.u.R3);
            return;
        }
    }

    public static synthetic void a7(c8.s6 p0, String p1)
    {
        p0.h9(p1);
        return;
    }

    public static synthetic void b3(c8.s6 p2, String p3, android.os.Bundle p4)
    {
        p2.getClass();
        String v3_2 = p4.getInt("id", 0);
        int v4_1 = p4.getInt("flags", 0);
        if (v3_2 != null) {
            if ((v4_1 & 2) == 0) {
                if ((v4_1 & 1) != 0) {
                    p2.C9(v3_2, v4_1);
                }
            } else {
                p2.D9(v3_2, v4_1);
            }
        }
        p2.x0().x("menu_result");
        return;
    }

    public static synthetic boolean b4(c8.s6 p2, String p3, android.content.Intent p4, android.view.View p5)
    {
        p2.getClass();
        if (!android.text.TextUtils.isEmpty(p3)) {
            w5.k.l(p2.I()).d0(x7.u.d9).J(p3).V(x7.u.b9, new c8.q2(p2, p4)).N(17039360, 0).R(17039361, new c8.r2(p2, p3)).f0();
            return 1;
        } else {
            return 0;
        }
    }

    public static synthetic void b5(c8.s6 p4, String p5, String p6, String p7, x5.a p8)
    {
        p4.getClass();
        if (p8.d()) {
            if (!p7.equals(((v9.f) p8.b()).e())) {
                int v6_3 = ((v9.f) p8.b());
                w5.k v8_3 = w5.k.l(p4.I()).d0(x7.u.Ug);
                Object[] v2_1 = new Object[1];
                v2_1[0] = i6.i0.a.e(p5);
                v8_3.J(p4.Y0(x7.u.Vg, v2_1)).V(17039370, new c8.d2(p4, v6_3, p7, p5)).N(17039360, 0).f0();
                return;
            } else {
                return;
            }
        } else {
            p4.L7(p5, p6, p7);
            return;
        }
    }

    public static synthetic void b6(c8.s6 p0, String p1, android.view.View p2, w5.k$p p3)
    {
        g6.n.a(p0.I(), p1, x7.u.hg);
        return;
    }

    public static synthetic n5.b b7(c8.s6 p0)
    {
        return p0.Q0;
    }

    public static synthetic j7.f c3(c8.s6 p1, String p2, String p3, String p4, int p5, String p6)
    {
        p1.getClass();
        b9.g.r3(p2, new c9.z(p3, p4, p6), p5).f3(p1.x0(), 0);
        return 0;
    }

    public static synthetic void c4(c8.s6 p2, Integer p3)
    {
        p2.getClass();
        if (p3.intValue() != 1) {
            if (p3.intValue() == 5) {
                p2.r8();
            }
            return;
        } else {
            p2.x0.v(1);
            return;
        }
    }

    public static synthetic void c5(c8.s6 p0)
    {
        p0.ub();
        return;
    }

    public static synthetic void c6(c8.s6 p1)
    {
        p1.t0.setVisibility(8);
        return;
    }

    public static synthetic boolean c7(c8.s6 p0)
    {
        return p0.r1;
    }

    public static synthetic void d3(c8.s6 p0, android.view.View p1, w5.k$p p2)
    {
        w8.e.g(p0.I());
        return;
    }

    public static synthetic String d4(c8.s6 p2, android.net.Uri p3)
    {
        p2.getClass();
        try {
            Throwable v2_5 = z8.b1.i(p2.I(), p3);
            try {
                Throwable v3_2 = android.graphics.BitmapFactory.decodeStream(v2_5);
                String v0 = t5.z.c(v3_2);
            } catch (Throwable v3_1) {
                if (v2_5 != null) {
                    try {
                        v2_5.close();
                    } catch (Throwable v2_1) {
                        v3_1.addSuppressed(v2_1);
                    }
                }
                throw v3_1;
            }
            if ((v3_2 != null) && (!v3_2.isRecycled())) {
                v3_2.recycle();
            }
            if (v2_5 != null) {
                v2_5.close();
            }
            return v0;
        } catch (Throwable v2_3) {
            pc.a.i(v2_3);
            return "";
        }
    }

    public static synthetic void d5(c8.s6 p1, w.d p2, android.view.View p3)
    {
        p1.getClass();
        try {
            p1.N2(z8.f1.i(p1.I(), ((android.net.Uri) p2.a), ((String) p2.b)));
            return;
        } catch (int v2_1) {
            pc.a.i(v2_1);
            g6.n.q(p1.I(), x7.u.qg);
            return;
        }
    }

    public static synthetic void d6(c8.s6 p0, java.util.List p1, android.widget.AdapterView p2, android.view.View p3, int p4, long p5)
    {
        p0.m0.i1(((String) p1.get(p4)), 1);
        return;
    }

    public static synthetic c8.tc d7(c8.s6 p0)
    {
        return p0.R0;
    }

    public static synthetic void e3(c8.s6 p1, String p2, android.view.View p3)
    {
        g6.n.b(p1.I(), p2, p1.R0().getString(x7.u.hg));
        return;
    }

    public static synthetic void e4(android.view.ViewGroup p1)
    {
        p1.setTranslationY(0);
        return;
    }

    public static synthetic void e5(c8.s6 p1, String p2, android.os.Bundle p3)
    {
        p1.x0().x("menu_result");
        if (p3.getInt("action", 0) != 0) {
            g6.i.g(p1, a9.o);
        }
        return;
    }

    public static synthetic void e6(c8.s6 p0, String p1, android.view.View p2)
    {
        g6.n.a(p0.I(), p1, x7.u.hg);
        return;
    }

    public static synthetic void e7(c8.s6 p0, String p1, String p2, int p3)
    {
        p0.h8(p1, p2, p3);
        return;
    }

    public static synthetic void f3(c8.s6 p1)
    {
        p1.q0.setTranslationY(0);
        return;
    }

    public static synthetic void f4(c8.s6 p0, v9.f p1, String p2, String p3, android.view.View p4, w5.k$p p5)
    {
        p0.getClass();
        p1.m(p2);
        p1.o(p3);
        p1.n(System.currentTimeMillis());
        ((autodispose2.r) x6.o.g(new c8.e3(p1)).l(g7.a.c()).j(w6.b.b()).m(u8.b.a(p0.b1()))).a(new c8.f3(p0), new x7.g0());
        return;
    }

    public static synthetic void f5(t4.b p2, String p3, android.webkit.WebView$WebViewTransport p4, android.os.Message p5)
    {
        p2.setTag(e8.i.d, Boolean.TRUE);
        p2.setReferer(p3);
        p4.setWebView(p2);
        p5.sendToTarget();
        return;
    }

    public static synthetic void f6(c8.s6 p1)
    {
        p1.w0.setVisibility(8);
        return;
    }

    public static synthetic void f7(c8.s6 p0)
    {
        p0.q9();
        return;
    }

    private void f9()
    {
        this.z0 = this.o0.e;
        android.widget.ProgressBar v0_7 = this.j0().getWindow();
        if (v0_7 != null) {
            android.widget.FrameLayout v1_4 = android.os.Build$VERSION.SDK_INT;
            if (v1_4 >= 29) {
                c8.e.a(v0_7, 0);
            }
            if (v1_4 >= 21) {
                c8.d.a(v0_7, 0);
                c8.i.a(v0_7, 0);
                if (v1_4 >= 28) {
                    c8.j.a(v0_7, 0);
                }
            }
        }
        this.o0.l.setOnClickListener(this.F1);
        android.widget.ProgressBar v0_4 = this.o0;
        this.y0 = v0_4.n;
        this.w0 = v0_4.o;
        this.p0 = v0_4.j;
        this.q0 = v0_4.k;
        this.d9();
        this.e9();
        return;
    }

    public static synthetic void g3(c8.s6 p0, androidx.fragment.app.FragmentManager p1, androidx.fragment.app.Fragment p2)
    {
        if (!(p2 instanceof f8.l0)) {
            if (!(p2 instanceof f8.h)) {
                if (!(p2 instanceof mark.via.download.m)) {
                    if (!(p2 instanceof ua.y)) {
                        if (!(p2 instanceof f8.q)) {
                            if (!(p2 instanceof f8.w)) {
                                if (!(p2 instanceof i8.k)) {
                                    p0.getClass();
                                    return;
                                } else {
                                    p0.s9(((i8.k) p2));
                                    return;
                                }
                            } else {
                                p0.F9(((f8.w) p2));
                                return;
                            }
                        } else {
                            p0.B9(((f8.q) p2));
                            return;
                        }
                    } else {
                        p0.E9(((ua.y) p2));
                        return;
                    }
                } else {
                    p0.t9(((mark.via.download.m) p2));
                    return;
                }
            } else {
                p0.v9(((f8.h) p2));
                return;
            }
        } else {
            p0.G9(((f8.l0) p2));
            return;
        }
    }

    public static synthetic void g4(ref.WeakReference p1, Integer p2)
    {
        i8.k v1_2 = ((i8.k) p1.get());
        if ((v1_2 != null) && (v1_2.g1())) {
            v1_2.y3(p2.intValue());
        }
        return;
    }

    public static synthetic x5.a g5(c8.s6 p2, String p3, String p4)
    {
        p2.getClass();
        java.io.File v0_0 = l5.c.b(l5.a.d(p3));
        String v1_2 = new StringBuilder();
        if (p4 == 0) {
            p4 = p3;
        }
        v1_2.append(z8.v0.g(p4));
        v1_2.append(".");
        if (v0_0 == null) {
            v0_0 = "png";
        }
        v1_2.append(v0_0);
        java.io.File v0_3 = new java.io.File(z8.c1.k(p2.I(), "download", v1_2.toString()));
        try {
            int v4_5 = new java.io.FileOutputStream(v0_3);
            try {
                if (!l5.a.h(p3, v4_5)) {
                    v0_3.delete();
                    g6.j.a(v4_5);
                    return x5.a.f(0);
                } else {
                    x5.a v2_6 = x5.a.f(v0_3);
                    g6.j.a(v4_5);
                    return v2_6;
                }
            } catch (java.io.IOException v3_2) {
                pc.a.i(v3_2);
            }
        } catch (java.io.IOException v3_2) {
            v4_5 = 0;
        } catch (java.io.IOException v3_1) {
            v4_5 = 0;
            x5.a v2_4 = v3_1;
            g6.j.a(v4_5);
            throw v2_4;
        } catch (x5.a v2_4) {
        }
    }

    public static synthetic void g6(c8.s6 p1, String p2, String p3, android.os.Bundle p4)
    {
        p1.getClass();
        c8.ua v3_2 = p4.getInt("changed", 0);
        if ((v3_2 & 2) == 0) {
            if ((v3_2 & 1) != 0) {
                p1.m0.v1(p2);
            }
        } else {
            p1.bb(1, v3_2);
        }
        p1.L0().x("result");
        return;
    }

    public static synthetic void g7(c8.s6 p0)
    {
        p0.ia();
        return;
    }

    private void gb()
    {
        this.x0().y1("passresult", this, new c8.b0(this));
        va.c0.v3(i6.i0.a.e(this.G8())).f3(this.x0(), va.c0.getSimpleName());
        return;
    }

    public static synthetic void h3(c8.s6 p0)
    {
        p0.wb();
        return;
    }

    public static synthetic void h4(c8.s6 p1, android.webkit.HttpAuthHandler p2, String p3, String p4, String p5, android.os.Bundle p6)
    {
        p1.getClass();
        String v5_1 = p6.getString("username");
        String v6_1 = p6.getString("password");
        if ((!g6.p.f(v5_1)) && (!g6.p.f(v6_1))) {
            p2.proceed(v5_1, v6_1);
            p1.wa(p3, v5_1, v6_1);
            p1.T7(p4, v5_1, v6_1);
        } else {
            p2.cancel();
        }
        p1.L0().x("url");
        return;
    }

    public static synthetic Boolean h5(v9.f p1)
    {
        return Boolean.valueOf(pa.r.i().k(p1));
    }

    public static synthetic void h6(c8.s6 p1)
    {
        p1.y0.setVisibility(8);
        return;
    }

    public static synthetic void h7(c8.s6 p0, boolean p1)
    {
        p0.X9(p1);
        return;
    }

    public static synthetic void i3(ref.WeakReference p1, Integer p2)
    {
        f8.w v1_2 = ((f8.w) p1.get());
        if ((v1_2 != null) && (v1_2.g1())) {
            v1_2.j3(p2.intValue());
        }
        return;
    }

    public static synthetic j7.f i4(c8.s6 p0, Boolean p1)
    {
        p0.getClass();
        if (p1.booleanValue()) {
            p0.S7();
        }
        return 0;
    }

    public static synthetic java.util.List i5(c8.s6 p4)
    {
        int[] v0_3 = g6.p.p(g6.p.m(p4.n0.C()), 44);
        int v1_0 = p4.n0.I1();
        java.util.List v4_2 = z8.v2.e(p4.I());
        int v2_1 = (v4_2.size() - 1);
        while (v2_1 >= 0) {
            boolean v3_2 = ((ja.c) v4_2.get(v2_1)).d();
            if ((v1_0 != v3_2) && (g6.a.b(v0_3, v3_2))) {
                v4_2.remove(v2_1);
            }
            v2_1--;
        }
        return v4_2;
    }

    public static synthetic void i6(c8.s6 p0, androidx.lifecycle.h p1, androidx.lifecycle.Lifecycle$Event p2)
    {
        p0.getClass();
        if (p2 == androidx.lifecycle.Lifecycle$Event.ON_DESTROY) {
            int v1_3 = p0.o0;
            if (v1_3 != 0) {
                v1_3.setBlurEnabled(0);
                p0.o0.setDescendantFocusability(262144);
            }
            if (p0.B0 != null) {
                p0.Ra(1);
            }
        }
        return;
    }

    public static synthetic void i7(c8.s6 p0, String p1, String p2, String p3)
    {
        p0.T7(p1, p2, p3);
        return;
    }

    public static synthetic void j3(c8.s6 p4, String p5, android.os.Bundle p6)
    {
        p5 = p4.d();
        if ((p5 != null) && (p6.containsKey("qrcode"))) {
            String v6_2 = p6.getString("qrcode", "");
            i6.i0 v1_0 = i6.i0.a;
            if (v1_0.t(v6_2)) {
                String v2_3 = v1_0.v(v6_2, p4.n0.X0());
                if ((v1_0.s(v2_3)) || ((v1_0.l(v2_3)) || (v1_0.k(v2_3)))) {
                    p5.v(v2_3);
                    p4.L0().x("qrcode");
                    return;
                }
            }
            p5.v(v1_0.a(v6_2, p4.X0(x7.u.xa)));
        }
        p4.L0().x("qrcode");
        return;
    }

    public static synthetic x5.a j4(g5.c p1, java.io.File p2, m5.j p3)
    {
        if (!p3.b()) {
            p1.P(200);
            if (p3.a() != null) {
                p1.A(p3.a().c());
                p1.B(p3.a().d());
                mark.via.download.i1.e(p1);
                p2.delete();
            }
            return x5.a.f(0);
        } else {
            p1.P(100);
            return x5.a.f(p2);
        }
    }

    public static synthetic w.d j5(c8.s6 p4, mark.via.download.e p5, String p6)
    {
        java.util.HashMap v1_0;
        p4.getClass();
        g5.c v0_1 = new g5.c();
        v0_1.J(p5.d());
        if (p5.f() != null) {
            v1_0 = p5.f();
        } else {
            v1_0 = p6;
        }
        v0_1.K(v1_0);
        v0_1.S(p5.h());
        v0_1.N(1);
        v0_1.I(p5.e());
        v0_1.Q(p5.c());
        v0_1.P(90);
        if (!android.webkit.URLUtil.isNetworkUrl(v0_1.t())) {
            v0_1.x(1);
        } else {
            v0_1.x(8);
            java.util.HashMap v1_5 = new java.util.HashMap();
            if (!g6.p.f(p5.i())) {
                v1_5.put("User-Agent", p5.i());
            }
            if (!g6.p.f(p5.g())) {
                v1_5.put("Referer", p5.g());
            }
            if (!g6.p.f(p5.a())) {
                v1_5.put("Authorization", p5.a());
            }
            if (!v1_5.isEmpty()) {
                v0_1.G(v1_5);
            }
        }
        return w.d.a(Integer.valueOf(mark.via.download.i1.h(p4.I(), v0_1, p6)), v0_1);
    }

    public static synthetic Boolean j6(v9.f p2)
    {
        return Boolean.valueOf(pa.r.i().b(p2.b(), p2));
    }

    public static synthetic void j7(c8.s6 p0, android.webkit.HttpAuthHandler p1, String p2, String p3)
    {
        p0.cb(p1, p2, p3);
        return;
    }

    public static synthetic void k3(c8.s6 p0, String p1)
    {
        p0.m9(p1);
        return;
    }

    public static synthetic void k4(c8.s6 p2, Throwable p3)
    {
        p2.getClass();
        pc.a.d(p3);
        g6.n.g(p2.I(), "Download error", p3.toString(), 0);
        return;
    }

    public static synthetic void k5(c8.s6 p0, android.view.View p1, w5.k$p p2)
    {
        p0.b8(0);
        return;
    }

    public static synthetic void k6(c8.s6 p2, x5.a p3)
    {
        if (p2.C0 != null) {
            if (!p3.d()) {
                p2.Bb(0);
                return;
            } else {
                p2.C0.setPassList(((java.util.List) p3.c()));
                if (p2.C0.getVisibility() != 0) {
                    p2.C0.setAlpha(0);
                    p2.C0.setVisibility(0);
                    x.r.c(p2.C0).a(1065353216).d(150).f();
                    return;
                }
            }
        }
        return;
    }

    public static synthetic void k7(c8.s6 p0, boolean p1)
    {
        p0.Bb(p1);
        return;
    }

    public static synthetic void l3(c8.s6 p1, ref.WeakReference p2)
    {
        p1.getClass();
        if (((t4.b) p2.get()) != null) {
            p1.U7(new c8.w(p1, p2));
            return;
        } else {
            return;
        }
    }

    public static synthetic void l4(c8.s6 p1)
    {
        p1.q0.setTranslationY(0);
        return;
    }

    public static synthetic void l5(c8.s6 p0, java.util.Map p1, android.widget.AdapterView p2, android.view.View p3, int p4, long p5)
    {
        p0.getClass();
        int v1_5 = ((android.app.PendingIntent) p1.get(Integer.valueOf(((int) p5))));
        if (v1_5 == 0) {
            int v1_6 = p0.G8();
            switch (((int) p5)) {
                case 990:
                    if ((v1_6 == 0) || (v1_6.isEmpty())) {
                    } else {
                        z8.f1.k(p0.I(), v1_6);
                    }
                    break;
                case 991:
                default:
                    break;
                case 992:
                    if ((v1_6 == 0) || (v1_6.isEmpty())) {
                    } else {
                        g6.n.a(p0.I(), v1_6, x7.u.ig);
                        return;
                    }
                case 993:
                    p0.fa(14);
                    return;
                case 994:
                    p0.fa(6);
                    return;
                case 995:
                    if ((v1_6 == 0) || (v1_6.isEmpty())) {
                    } else {
                        z8.b0.P(p0.I(), v1_6);
                        p0.j0().finish();
                        return;
                    }
                case 996:
                    p0.fa(15);
                    return;
                case 997:
                    if ((v1_6 == 0) || (v1_6.isEmpty())) {
                    } else {
                        p0.jb(v1_6);
                        return;
                    }
            }
            return;
        } else {
            p0.Ha(v1_5);
            return;
        }
    }

    public static synthetic boolean l6(c8.s6 p0, String[] p1, android.widget.AdapterView p2, android.view.View p3, int p4, long p5)
    {
        g6.n.a(p0.I(), p1[p4], x7.u.ig);
        return 1;
    }

    public static synthetic void l7(c8.s6 p0, boolean p1)
    {
        p0.ob(p1);
        return;
    }

    public static synthetic void m3(c8.s6 p6, String p7, String p8, String p9, java.util.List p10, int p11)
    {
        p6.getClass();
        j8.t v0_1 = new j8.t(p7, p8, p9, p10, p11);
        int v7_1 = p6.G1.i(v0_1);
        if (v7_1 != 0) {
            p6.i1 = v7_1;
            j8.e.b(p6.I(), v7_1);
            g6.n.r(p6.I(), x7.u.bd, x7.u.yh, new c8.x0(p6));
            p6.ib(v0_1, 0);
        }
        return;
    }

    public static synthetic void m4(c8.s6 p0)
    {
        p0.N8();
        return;
    }

    public static synthetic void m5(ua.y p2, String p3)
    {
        if (!"null".equals(p3)) {
            if (p3.length() >= 2) {
                p3 = p3.substring(1, (p3.length() - 1)).replace("\\\"", "\"");
            }
            p2.n3(p3);
            return;
        } else {
            return;
        }
    }

    public static synthetic boolean m6(c8.s6 p5, android.view.View p6, android.view.DragEvent p7)
    {
        p5.getClass();
        long v0_1 = p7.getAction();
        if (v0_1 == 3) {
            x.r.c(p6).a(1065353216).d(180).f();
            return p5.K8(p7.getClipData());
        } else {
            if (v0_1 == 4) {
                x.r.c(p6).a(1065353216).d(100).f();
            } else {
                if (v0_1 == 5) {
                    x.r.c(p6).a(1050253722).d(100).f();
                } else {
                    if (v0_1 == 6) {
                    }
                }
            }
            return 1;
        }
    }

    public static synthetic void m7(c8.s6 p0, String p1)
    {
        p0.Da(p1);
        return;
    }

    public static synthetic void n3(c8.s6 p0, android.os.Message p1, android.view.View p2)
    {
        p0.ea(p1);
        return;
    }

    public static synthetic void n4(c8.s6 p0, android.view.View p1)
    {
        p0.Q9();
        return;
    }

    public static synthetic void n5(c8.s6 p0, String p1, android.view.View p2, w5.k$p p3)
    {
        p0.getClass();
        p0.m0.i1(i6.i0.a.v(p1, p0.n0.X0()), 1);
        return;
    }

    public static synthetic void n6(c8.s6 p3, int p4, android.widget.AdapterView p5, android.view.View p6, int p7, long p8)
    {
        if (p7 != p4) {
            int v4_2;
            p3.n0.O0((p7 + 1));
            int v4_5 = p3.n0.i0();
            if (v4_5 == 2) {
                v4_2 = 10;
            } else {
                if (v4_5 == 3) {
                    v4_2 = 1;
                } else {
                    if (v4_5 == 4) {
                        v4_2 = 0;
                    } else {
                        v4_2 = 2;
                    }
                }
            }
            z8.n3.i(p3.I(), v4_2);
            int v4_3 = p3.I();
            String v0_1 = p3.X0(x7.u.I9);
            String v7_1 = g6.e.c(p3.I(), x7.j.g, p7);
            Object[] v6_0 = new Object[2];
            v6_0[0] = v0_1;
            v6_0[1] = v7_1;
            g6.n.s(v4_3, p3.Y0(x7.u.n7, v6_0));
            return;
        } else {
            p3.getClass();
            return;
        }
    }

    public static synthetic int n7(c8.s6 p0)
    {
        return p0.g1;
    }

    public static synthetic void o3(c8.s6 p0, mark.via.download.e p1)
    {
        p0.e8(p1);
        return;
    }

    public static synthetic void o4(c8.s6 p0, android.content.Intent p1, android.view.View p2, w5.k$p p3)
    {
        p0.getClass();
        try {
            p0.N2(p1);
            return;
        } catch (Exception) {
            g6.n.q(p0.I(), x7.u.qg);
            return;
        }
    }

    public static synthetic void o5(c8.s6 p1, x5.a p2)
    {
        int v2_1;
        p1.getClass();
        if (!p2.d()) {
            v2_1 = 0;
        } else {
            v2_1 = ((o9.a) p2.b()).b();
        }
        p1.M9(v2_1);
        return;
    }

    public static synthetic void o6(c8.s6 p0, android.view.View p1)
    {
        p0.Va(p1);
        return;
    }

    public static synthetic int o7(c8.s6 p0, int p1)
    {
        p0.g1 = p1;
        return p1;
    }

    public static synthetic void p3(c8.s6 p0, mark.via.download.e p1, android.net.Uri p2, android.view.View p3, w5.k$p p4)
    {
        p0.N1 = p1;
        try {
            p0.R1.a(p2);
            return;
        } catch (Exception v0_2) {
            pc.a.i(v0_2);
            return;
        }
    }

    public static synthetic void p4(c8.s6 p1, int p2)
    {
        if (p2 < null) {
            p1.getClass();
            return;
        } else {
            if (p2 < p1.i()) {
                p1.i8(((r4.a) p1.c().get(p2)));
            }
            return;
        }
    }

    public static synthetic void p5(c8.s6 p0, String p1, android.os.Bundle p2)
    {
        p0.L0().x(a8.d0.w0);
        return;
    }

    public static synthetic mark.via.common.widget.o0 p6(c8.s6 p0, boolean p1, String p2, String p3, int p4, int p5)
    {
        android.graphics.Bitmap v1_1;
        p0.getClass();
        if (p1 == null) {
            v1_1 = z8.i.c(p3, p4, p5);
            mark.via.common.widget.o0 v2_4 = new Object[0];
            pc.a.a("using file does not exists, load from original picture", v2_4);
        } else {
            v1_1 = android.graphics.BitmapFactory.decodeFile(p2);
        }
        if (v1_1 != null) {
            return new mark.via.common.widget.o0(p0.R0(), v1_1);
        } else {
            return 0;
        }
    }

    public static synthetic void p7(c8.s6 p0)
    {
        p0.lb();
        return;
    }

    public static synthetic void q3(c8.s6 p0)
    {
        p0.M8();
        return;
    }

    public static synthetic void q4(c8.s6 p1)
    {
        p1.w0.setVisibility(0);
        return;
    }

    public static synthetic void q5(c8.s6 p0, String p1, android.widget.AdapterView p2, android.view.View p3, int p4, long p5)
    {
        int v2_1;
        p0.getClass();
        switch (((int) p5)) {
            case 2:
                v2_1 = "https://lens.google.com/uploadbyurl?url=";
                break;
            case 3:
                v2_1 = "https://www.bing.com/images/search?view=detailv2&iss=sbi&form=SBIVSP&sbisrc=UrlPaste&q=imgurl:";
                break;
            case 4:
                v2_1 = "https://tineye.com/search/?url=";
                break;
            case 5:
                v2_1 = "https://yandex.com/images/touch/search?family=yes&rpt=imageview&url=";
                break;
            case 6:
                v2_1 = "https://graph.baidu.com/details?isfromtusoupc=1&tn=pc&carousel=0&promotion_name=pc_image_shituindex&extUiData%5bisLogoShow%5d=1&image=";
                break;
            case 7:
                v2_1 = "https://st.so.com/r?img_url=";
                break;
            case 8:
                v2_1 = "https://saucenao.com/search.php?db=999&url=";
                break;
            case 9:
                v2_1 = "https://iqdb.org/?url=";
                break;
            case 10:
                v2_1 = "https://3d.iqdb.org/?url=";
                break;
            case 11:
                v2_1 = "https://trace.moe/?url=";
                break;
            case 12:
                v2_1 = "https://ascii2d.net/search/url/";
                break;
            default:
                v2_1 = "https://www.google.com/searchbyimage?safe=off&sbisrc=tg&image_url=";
        }
        c8.ua v0_1 = p0.m0;
        StringBuilder v3_1 = new StringBuilder();
        v3_1.append(v2_1);
        v3_1.append(android.net.Uri.encode(p1));
        v0_1.i1(v3_1.toString(), 1);
        return;
    }

    public static synthetic void q6(c8.s6 p0)
    {
        p0.Q9();
        return;
    }

    public static synthetic void q7(c8.s6 p0, String p1)
    {
        p0.Ua(p1);
        return;
    }

    public static synthetic x5.a r3(c8.s6 p0, java.io.File p1, String p2)
    {
        return x5.a.f(p0.ya(p1, p2));
    }

    public static synthetic void r4(e8.z0$a p1, android.content.DialogInterface p2)
    {
        String[] v0 = new String[0];
        p1.a(v0, 0);
        return;
    }

    public static synthetic void r5(c8.s6 p2, android.widget.TextView p3)
    {
        p2.getClass();
        p3.setText(x7.u.Y8);
        p3.setGravity(17);
        p3.setTextSize(0, ((float) x8.h.u(p2.I())));
        p3.setTextColor(x8.h.q(p2.I()));
        return;
    }

    public static synthetic c8.xa r6(c8.s6 p0)
    {
        return p0.J0;
    }

    public static synthetic void r7(c8.s6 p0)
    {
        p0.Z9();
        return;
    }

    public static synthetic void s3(c8.s6 p3, String p4, android.os.Bundle p5)
    {
        p3.getClass();
        Exception v4_6 = p5.getString("input", 0);
        if (!android.text.TextUtils.isEmpty(v4_6)) {
            if (p5.getInt("input_action", 0) != 2) {
                if (!v4_6.startsWith("VIA-SWITCH-TAB:")) {
                    p3.Da(v4_6);
                } else {
                    try {
                        c8.ua v5_4 = p3.m0;
                        v5_4.X1(v5_4.F0(Integer.parseInt(v4_6.substring(15))));
                    } catch (Exception v4_4) {
                        v4_4.printStackTrace();
                    }
                }
            } else {
                p3.Ea(p5.getString("input_query", v4_6), p5.getInt("input_engine"));
            }
        }
        p3.L0().x("input");
        return;
    }

    public static synthetic void s4(c8.s6 p4, String p5, StringBuilder p6, e8.z0$a p7, String[] p8)
    {
        w5.k v0_1 = w5.k.l(p4.I());
        Object[] v2_1 = new Object[1];
        v2_1[0] = g6.p.n(p5, p4.X0(x7.u.Kg));
        v0_1.e0(p4.Y0(x7.u.Y7, v2_1)).J(p6.toString()).w(x7.u.Ma, 0).V(x7.u.f, new c8.g2(p7, p8)).P(x7.u.z, new c8.h2(p7)).T(new c8.i2(p7)).f0();
        return;
    }

    public static synthetic void s5(c8.s6 p0, int p1, String[] p2, android.widget.AdapterView p3, android.view.View p4, int p5, long p6)
    {
        if (p5 != p1) {
            String v1_4 = p0.n0.g2();
            if (p5 == 0) {
                v1_4.U(0);
                v1_4.T(1);
            } else {
                if (p5 == 1) {
                    v1_4.U(0);
                    v1_4.T(0);
                } else {
                    if (p5 == 2) {
                        v1_4.U(1);
                        v1_4.T(1);
                    }
                }
            }
            p0.n0.g0(v1_4);
            p0.m0.m1(1);
            g6.n.s(p0.I(), p2[p5]);
            return;
        } else {
            p0.getClass();
            return;
        }
    }

    public static synthetic boolean s6(c8.s6 p0)
    {
        return p0.k1;
    }

    public static synthetic void s7(c8.s6 p0, String p1)
    {
        p0.hb(p1);
        return;
    }

    public static synthetic void t3(c8.s6 p1)
    {
        p1.B0.setTranslationY(0);
        return;
    }

    public static synthetic void t4(c8.s6 p2)
    {
        p2.t0.setAlpha(0);
        p2.t0.setVisibility(0);
        return;
    }

    public static synthetic void t5(c8.s6 p0, String p1, android.view.View p2, w5.k$p p3)
    {
        p0.Da(p1);
        return;
    }

    public static synthetic c8.xa t6(c8.s6 p0, c8.xa p1)
    {
        p0.J0 = p1;
        return p1;
    }

    public static synthetic void t7(c8.s6 p0, String p1)
    {
        p0.jb(p1);
        return;
    }

    public static synthetic void u3(c8.s6 p2)
    {
        int v0_0 = p2.j1;
        if (v0_0 != 0) {
            int v0_2 = ((android.view.ViewGroup) v0_0.getParent());
            if (v0_2 != 0) {
                v0_2.removeView(p2.j1);
            }
            p2.j1 = 0;
            return;
        } else {
            return;
        }
    }

    public static synthetic void u4(ref.WeakReference p1)
    {
        android.webkit.WebView v1_2 = ((android.webkit.WebView) p1.get());
        if (v1_2 != null) {
            v1_2.setVisibility(0);
        }
        return;
    }

    public static synthetic void u5(c8.s6 p4, int p5)
    {
        p4.getClass();
        Object[] v2 = new Object[1];
        v2[0] = Integer.valueOf(p5);
        pc.a.a("on action: %d", v2);
        if (p5 == 1) {
            j8.e.b(p4.I(), p4.i1);
            return;
        } else {
            if (p5 == 2) {
                j8.e.a(p4.I(), p4.i1);
                return;
            } else {
                if (p5 == 3) {
                    j8.e.d(p4.I());
                    p4.i1 = 0;
                    p4.ib(0, 0);
                    return;
                } else {
                    if (p5 == 4) {
                        p4.V9();
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public static synthetic boolean u6(c8.s6 p0)
    {
        return p0.q1;
    }

    public static synthetic void u7(c8.s6 p0)
    {
        p0.Fb();
        return;
    }

    public static synthetic void v3(android.widget.LinearLayout p1)
    {
        p1.setOrientation(1);
        return;
    }

    public static synthetic void v4(c8.s6 p0, mark.via.download.e p1, android.view.View p2)
    {
        p0.na(p1);
        return;
    }

    public static synthetic void v5(c8.s6 p0, int p1, int p2)
    {
        p0.Z7(p1, p2);
        return;
    }

    public static synthetic boolean v6(c8.s6 p0)
    {
        return p0.O7();
    }

    public static synthetic void v7(c8.s6 p0, String p1, String[] p2, e8.z0$a p3)
    {
        p0.qa(p1, p2, p3);
        return;
    }

    public static synthetic void w3(c8.s6 p0, java.util.List p1, android.widget.AdapterView p2, android.view.View p3, int p4, long p5)
    {
        p0.m0.i1(((String) p1.get(p4)), 1);
        return;
    }

    public static synthetic o9.b w4(String p1, String p2)
    {
        o9.b v0_1 = pa.r.d().a(p1);
        if (v0_1 == null) {
            v0_1 = new o9.b();
            v0_1.n(p1);
            v0_1.l(p2);
        }
        return v0_1;
    }

    public static synthetic boolean w5(c8.s6 p0, int p1, int p2)
    {
        return p0.L0.p(p1, p2);
    }

    public static synthetic boolean w6(c8.s6 p0)
    {
        return p0.P7();
    }

    public static synthetic void w7(c8.s6 p0)
    {
        p0.c8();
        return;
    }

    private void w8()
    {
        if (android.os.Build$VERSION.SDK_INT >= 21) {
            x.r.d0(this.z0, new c8.a5(this));
            return;
        } else {
            return;
        }
    }

    public static synthetic void x3(c8.s6 p4, x5.a p5)
    {
        p4.getClass();
        if (!p5.d()) {
            g6.n.q(p4.I(), x7.u.qg);
            return;
        } else {
            g6.n.r(p4.I(), x7.u.s6, x7.u.yh, new c8.c0(p4, ((w.d) p5.b())));
            return;
        }
    }

    public static synthetic void x4(c8.s6 p0, android.view.View p1, w5.k$p p2)
    {
        p0.m0.t0();
        return;
    }

    public static synthetic void x5(c8.s6 p2, String p3)
    {
        g6.n.g(p2.I(), "Download message", p3, 0);
        return;
    }

    public static synthetic void x6(c8.s6 p0)
    {
        p0.I8();
        return;
    }

    public static synthetic void x7(c8.s6 p0)
    {
        p0.o8();
        return;
    }

    public static synthetic void y3(c8.s6 p0, String p1, android.view.View p2)
    {
        g6.n.a(p0.I(), p1, x7.u.ig);
        return;
    }

    public static synthetic void y4(c8.s6 p1, String[][] p2, android.widget.AdapterView p3, android.view.View p4, int p5, long p6)
    {
        p1.getClass();
        android.content.Context v4_1 = p2[0][p5];
        p1.n0.C0(v4_1);
        p1.O0.j(v4_1);
        android.content.Context v4_2 = p1.I();
        String v2_2 = p2[1][p5];
        Object[] v5_2 = new Object[2];
        v5_2[0] = p1.X0(x7.u.A0);
        v5_2[1] = v2_2;
        g6.n.s(v4_2, p1.Y0(x7.u.n7, v5_2));
        p1.R8();
        return;
    }

    public static synthetic void y5(c8.s6 p2, java.util.List p3)
    {
        p2.getClass();
        if (p3.size() <= 1) {
            if (p2.B0 != null) {
                p2.Ra(0);
            }
        } else {
            if (p2.B0 == null) {
                p2.b9();
            }
            if (p2.B0 != null) {
                java.util.Collections.sort(p3, new ib.n(p2.n0.E1()));
                p2.B0.m(p3, 0);
                p2.Cb(p2.G8());
                return;
            }
        }
        return;
    }

    public static synthetic void y6(c8.s6 p0)
    {
        p0.J8();
        return;
    }

    public static synthetic void y7(c8.s6 p0)
    {
        p0.M7();
        return;
    }

    public static synthetic void z3(c8.s6 p0, int p1, int p2, boolean p3)
    {
        p0.La(p1, p2, p3);
        return;
    }

    public static synthetic void z4(c8.s6 p0, String p1, android.view.View p2)
    {
        g6.n.a(p0.I(), p1, x7.u.ig);
        return;
    }

    public static synthetic void z5(c8.s6 p0, String p1, String p2, android.widget.AdapterView p3, android.view.View p4, int p5, long p6)
    {
        p0.getClass();
        switch (((int) p6)) {
            case 1:
            case 2:
            case 3:
                String v1_1;
                if (p6 != 1) {
                    if (p6 != 2) {
                        v1_1 = i6.f0.a.c(p1, p2);
                    } else {
                        v1_1 = i6.f0.a.d(p1, p2);
                    }
                } else {
                    v1_1 = i6.f0.a.a(p1, p2);
                }
                p0.m0.i1(v1_1, 1);
                return;
            case 4:
                p0.m0.a2();
                return;
            case 5:
                p0.pb(0);
                return;
            case 6:
                p0.m0.b2();
                return;
            default:
                return;
        }
    }

    public static synthetic void z6(c8.s6 p0)
    {
        p0.U8();
        return;
    }

    public static synthetic i6.e z7(c8.s6 p0)
    {
        return p0.H8();
    }

    public void A(int p4)
    {
        mark.via.common.widget.n0 v4_1 = (p4 + 20);
        this.o0.setProgress(v4_1);
        if (v4_1 < 100) {
            this.x0.s(1, 0);
            return;
        } else {
            this.x0.s(0, u9.d.o(this.I(), this.G8()));
            return;
        }
    }

    public android.view.View A1(android.view.LayoutInflater p4, android.view.ViewGroup p5, android.os.Bundle p6)
    {
        Long v5_0 = new Object[0];
        pc.a.a("BrowserFragment::onCreateView", v5_0);
        Long v5_2 = android.os.SystemClock.elapsedRealtime();
        c8.f8 v0_1 = new c8.f8(this.I());
        v0_1.setLayoutParams(new android.widget.FrameLayout$LayoutParams(-1, -1));
        this.o0 = v0_1;
        Object[] v6_2 = new Object[1];
        v6_2[0] = Long.valueOf((android.os.SystemClock.elapsedRealtime() - v5_2));
        pc.a.a("BrowserFragment::onCreateView, cost time: %d", v6_2);
        return v0_1;
    }

    public android.net.http.SslCertificate A8()
    {
        android.net.http.SslCertificate v0_1 = this.L0.d();
        if (v0_1 != null) {
            return v0_1.w();
        } else {
            return 0;
        }
    }

    public boolean A9(int p2, android.view.KeyEvent p3)
    {
        if ((p2 == 24) || (p2 == 25)) {
            if ((this.s0 != null) || (this.r0 != null)) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public final void Aa(android.net.Uri p3)
    {
        if (p3 != null) {
            ((autodispose2.r) x6.o.g(new c8.b2(this, p3)).l(g7.a.c()).j(w6.b.b()).m(u8.b.a(this.b1()))).a(new c8.c2(this), new x7.g0());
            return;
        } else {
            return;
        }
    }

    public final void Ab()
    {
        if (!this.i1()) {
            Object[] v1_1;
            int v5_0 = this.n0.c();
            if ((v5_0 != 0) && (!v5_0.isEmpty())) {
                v1_1 = new java.io.File(v5_0);
            } else {
                v1_1 = 0;
            }
            if ((v1_1 != null) && (v1_1.exists())) {
                int v4_0 = j9.a.b(this.I());
                c8.s3 v0_3 = new java.io.File(v4_0);
                c8.f8 v3_0 = v0_3.exists();
                c8.s3 v0_6 = (v0_3.lastModified() + ((long) this.n0.E2().a()));
                int v6_5 = g6.y.s(this.I());
                int v7_1 = g6.y.q(this.I());
                if ((v3_0 == null) || (v0_6 != this.d1)) {
                    this.d1 = v0_6;
                    ((autodispose2.m) x6.f.h(new c8.q3(this, v3_0, v4_0, v5_0, v6_5, v7_1)).n(g7.a.c()).k(w6.b.b()).p(u8.b.a(this.b1()))).a(new c8.r3(this), new x7.c0());
                    if (v3_0 == null) {
                        z8.h.b(new c8.s3(this, v5_0));
                        return;
                    }
                } else {
                    Object[] v1_5 = new Object[0];
                    pc.a.a("init home view, no changed", v1_5);
                    return;
                }
            } else {
                int v4_3 = new Object[0];
                pc.a.a("no background image, use default color", v4_3);
                if (this.P8()) {
                    this.o0.k(g6.e.a(this.I(), x7.k.b), 0);
                    this.o0.setWindowBackgroundImage(0);
                    this.zb();
                }
            }
        }
        return;
    }

    public void B(boolean p3)
    {
        if ((this.a1) && (p3 != this.H1)) {
            this.H1 = p3;
            this.x0.post(new c8.j5(this, p3));
        }
        return;
    }

    public void B1()
    {
        if ((this.G1 != null) && (this.i1 != null)) {
            j8.e.d(this.I());
        }
        this.M0.b(this.I());
        this.I().unregisterReceiver(this.D0);
        this.P1.c();
        this.M1.c();
        super.B1();
        return;
    }

    public final int B8()
    {
        int v0_2;
        int v0_0 = this.z0;
        if (v0_0 != 0) {
            v0_2 = Math.min(v0_0.getMeasuredWidth(), this.z0.getMeasuredHeight());
        } else {
            v0_2 = 0;
        }
        if (v0_2 == 0) {
            v0_2 = g6.y.p(this.I());
        }
        if ((this.Z0) && (this.W0 != 0)) {
            v0_2 = Math.min((v0_2 - g6.y.h(this.I(), 1119879168)), g6.y.h(this.I(), 1136656384));
        }
        return v0_2;
    }

    public final void B9(f8.q p2)
    {
        p2.c3(new c8.s6$i(this));
        return;
    }

    public final void Ba(String p2)
    {
        this.sa(p2, 2);
        return;
    }

    public final void Bb(boolean p3)
    {
        long v0_0 = this.C0;
        if (v0_0 != 0) {
            if (p3 != null) {
                ((autodispose2.r) x6.o.g(new c8.o6(this.G8())).l(g7.a.c()).j(w6.b.b()).m(u8.b.a(this.b1()))).a(new c8.p6(this), new x7.g0());
                return;
            } else {
                if (v0_0.getVisibility() != 8) {
                    x.r.c(this.C0).a(0).i(new c8.n6(this)).d(150).f();
                }
            }
        }
        return;
    }

    public void C(n4.a p2, String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            t4.b v2_4 = p2.a();
            if (v2_4 != e8.i.f) {
                if (v2_4 != e8.i.g) {
                    if (v2_4 == e8.i.i) {
                        this.pb(p3);
                    }
                } else {
                    this.C8().k();
                    this.Za(p3);
                    return;
                }
            } else {
                if (!this.u(p3)) {
                    this.Ga(p3, 0, 1);
                    return;
                }
            }
        }
        return;
    }

    public t4.b C8()
    {
        t4.b v0_0 = this.d();
        if (v0_0 != null) {
            return v0_0.p();
        } else {
            return 0;
        }
    }

    public final void C9(int p7, int p8)
    {
        if (p7 != 21) {
            t8.f.a().f(p7);
        }
        String v0_7 = 2;
        int v1_0 = 0;
        switch (p7) {
            case 1:
                Class v7_36 = (this.n0.d() ^ 1);
                int v8_23 = g6.q.a(this.I());
                if ((v7_36 == v8_23) && (android.os.Build$VERSION.SDK_INT >= 29)) {
                    g6.n.q(this.I(), x7.u.D5);
                }
                this.n0.R0(v7_36, v8_23);
                this.tb();
                return;
            case 2:
            case 3:
            case 13:
                if (p7 == 13) {
                    this.Y9();
                    return;
                } else {
                    if (p7 == 2) {
                        v1_0 = 1;
                    }
                    this.X9(v1_0);
                    return;
                }
            case 4:
                if (this.O0.i(this.I())) {
                } else {
                    this.Q9();
                }
                break;
            case 5:
                this.Oa();
                return;
            case 6:
                this.m0.S1();
                return;
            case 7:
                this.J7();
                return;
            case 8:
                int v8_16;
                Class v7_26 = this.n0.g2();
                int v8_15 = v7_26.l();
                String v0_9 = (v8_15 ^ 1);
                boolean v3_3 = this.I();
                if (v8_15 != 0) {
                    v8_16 = x7.u.a7;
                } else {
                    v8_16 = x7.u.b7;
                }
                Object[] v5 = new Object[1];
                v5[0] = this.X0(x7.u.T);
                g6.n.s(v3_3, this.Y0(v8_16, v5));
                v7_26.O(v0_9);
                this.n0.g0(v7_26);
                this.m0.m1(1);
                if (u9.d.m(this.I(), this.G8())) {
                } else {
                    this.la(v0_9);
                    return;
                }
            case 9:
            case 22:
            default:
                break;
            case 10:
                this.fa(26);
                return;
            case 11:
                this.fa(14);
                return;
            case 12:
                this.fa(19);
                return;
            case 14:
                this.fa(15);
                return;
            case 15:
                this.fa(20);
                return;
            case 16:
                this.o0.postDelayed(new c8.s(this), 100);
                return;
            case 17:
                Class v7_15 = new String[] {this.X0(x7.u.r6), this.X0(x7.u.q6), this.X0(x7.u.p6)});
                int v8_8 = this.n0.g2();
                if (!v8_8.r()) {
                    if (!v8_8.q()) {
                        v0_7 = 1;
                    } else {
                        v0_7 = 0;
                    }
                }
                w5.k.l(this.I()).d0(x7.u.t6).b0(v7_15, v0_7, new c8.t(this, v0_7, v7_15)).f0();
                return;
            case 18:
                this.m0.t1();
                return;
            case 19:
                this.Pa();
                return;
            case 20:
                this.m0.r1();
                return;
            case 21:
                this.s8();
                return;
            case 23:
                this.q9();
                return;
            case 24:
                this.ka();
                return;
            case 25:
                this.U9();
                return;
            case 26:
                this.Z9();
                return;
            case 27:
                Class v7_3 = this.n0.g2().D();
                int v8_1 = this.G8();
                if ((i6.i0.a.s(v8_1)) && ((!r9.g.a().e(v8_1)) && (c8.sc.c().d().C(v8_1)))) {
                    v1_0 = 1;
                }
                if (v7_3 != null) {
                    if (v1_0 == 0) {
                        g6.i.g(this, sa.d1);
                        return;
                    } else {
                        this.R8();
                        this.jb(this.G8());
                        return;
                    }
                } else {
                    Class v7_7 = this.n0.g2();
                    v7_7.i0(1);
                    this.n0.g0(v7_7);
                    if (v1_0 != 0) {
                        this.ka();
                    }
                    g6.n.q(this.I(), x7.u.Cb);
                    return;
                }
            case 28:
                Class v7_47;
                Class v7_45 = this.n0.g2();
                v7_45.I((v7_45.g() ^ 1));
                this.n0.g0(v7_45);
                this.m0.l1();
                int v8_32 = this.I();
                if (!v7_45.g()) {
                    v7_47 = x7.u.a7;
                } else {
                    v7_47 = x7.u.b7;
                }
                Object[] v2_2 = new Object[1];
                v2_2[0] = this.X0(x7.u.t1);
                g6.n.s(v8_32, this.Y0(v7_47, v2_2));
                return;
            case 29:
                this.bb(0, 0);
                return;
            case 30:
                Class v7_42 = (this.n0.i0() - 1);
                w5.k.l(this.I()).d0(x7.u.I9).Z(x7.j.g, v7_42, new c8.u(this, v7_42)).f0();
                return;
            case 31:
                z8.h0.e(this.I(), this.n0, pa.r.m().d(), pa.r.l(), pa.r.f());
                return;
            case 32:
                this.ia();
                return;
            case 33:
                this.ja(this.d());
                return;
            case 34:
                this.Ia();
                return;
            case 35:
                g6.i.g(this, i8.s);
                return;
            case 36:
                if ((p8 & 8) == 0) {
                    this.r8();
                    return;
                } else {
                    this.c8();
                    return;
                }
            case 37:
                this.da(this.G8());
                return;
            case 38:
                this.Na((this.n0.Q0() ^ 1));
                return;
            case 39:
                this.P9();
                return;
            case 40:
                this.K7();
                return;
            case 41:
                this.ma();
                return;
        }
        return;
    }

    public final void Ca(String p6, int p7, int p8)
    {
        if ((!z8.c0.f()) && (!z8.f.h())) {
            w5.k v0_10 = 0;
        } else {
            v0_10 = 1;
        }
        java.util.ArrayList v2_0 = new java.util.ArrayList();
        v2_0.add(new w5.k$l(1, this.X0(x7.u.R5)));
        v2_0.add(new w5.k$l(2, this.X0(x7.u.Q5)));
        v2_0.add(new w5.k$l(3, this.X0(x7.u.q1)));
        v2_0.add(new w5.k$l(4, this.X0(x7.u.Ef)));
        v2_0.add(new w5.k$l(5, this.X0(x7.u.Lh)));
        if (v0_10 != null) {
            v2_0.add(new w5.k$l(6, this.X0(x7.u.p1)));
            v2_0.add(new w5.k$l(7, this.X0(x7.u.U5)));
        }
        v2_0.add(new w5.k$l(8, this.X0(x7.u.nb)));
        v2_0.add(new w5.k$l(9, this.X0(x7.u.Z6)));
        v2_0.add(new w5.k$l(10, this.X0(x7.u.Y6)));
        v2_0.add(new w5.k$l(11, this.X0(x7.u.Hh)));
        v2_0.add(new w5.k$l(12, this.X0(x7.u.g1)));
        w5.k.l(this.I()).d0(x7.u.Kb).C(v2_0, new c8.z2(this, p6)).g0(p7, p8);
        return;
    }

    public final void Cb(String p5)
    {
        mark.via.common.widget.m v0_0 = this.B0;
        if (v0_0 != null) {
            if ((this.k1) || (!v0_0.n(p5))) {
                android.view.ViewGroup v5_1 = 0;
            } else {
                v5_1 = 1;
            }
            mark.via.common.widget.m v0_3;
            if (this.B0.getParent() == null) {
                v0_3 = 0;
            } else {
                v0_3 = 1;
            }
            if (v5_1 != v0_3) {
                if (v5_1 != null) {
                    android.view.ViewGroup v5_4 = this.B0.getLayoutParams().height;
                    this.z0.addView(this.B0, (Math.max(this.z0.indexOfChild(this.o0), 0) + 1));
                    this.p0.setPadding(0, 0, 0, v5_4);
                    return;
                } else {
                    this.p0.setPadding(0, 0, 0, 0);
                    android.view.ViewGroup v5_7 = this.B0.getParent();
                    if ((v5_7 instanceof android.view.ViewGroup)) {
                        ((android.view.ViewGroup) v5_7).removeView(this.B0);
                    }
                }
            }
        }
        return;
    }

    public void D1()
    {
        c8.ua v0_0 = this.G1;
        if (v0_0 != null) {
            j8.d v1 = this.h1;
            if (v1 != null) {
                v0_0.d(v1);
            }
        }
        this.p0.removeAllViews();
        this.L0.h();
        this.m0.o1();
        super.D1();
        return;
    }

    public final com.tuyafeng.support.widget.z D8()
    {
        if (this.U1 == null) {
            com.tuyafeng.support.widget.z v0_7 = ((com.tuyafeng.support.widget.z) new h6.a(new com.tuyafeng.support.widget.z(this.I()), new android.widget.RelativeLayout$LayoutParams(-1, -2)).o(g6.y.l()).V(new c8.a4(this)).l());
            com.tuyafeng.support.widget.z$b v1_4 = this.w0();
            if (v1_4 != null) {
                String v4_3;
                c8.e4 v2_5 = ((android.graphics.Bitmap) g6.d.a(v1_4, "android.support.customtabs.extra.CLOSE_BUTTON_ICON", android.graphics.Bitmap));
                if (v2_5 == null) {
                    v4_3 = lb.b.a(this.I(), x7.o.z, x7.u.Nd);
                } else {
                    v4_3 = new android.graphics.drawable.BitmapDrawable(this.R0(), v2_5);
                }
                v0_7.l(v4_3, this.X0(x7.u.w8), new c8.b4(this));
                com.tuyafeng.support.widget.z$b v1_6 = v1_4.getBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE");
                if (v1_6 == null) {
                    v0_7.c(new com.tuyafeng.support.widget.z$b(990, 0, lb.b.a(this.I(), x7.o.n1, x7.u.mf), this.X0(x7.u.d0)), new c8.d4(this));
                } else {
                    c8.c4 v3_8;
                    c8.c4 v3_6 = ((android.graphics.Bitmap) g6.d.a(v1_6, "android.support.customtabs.customaction.ICON", android.graphics.Bitmap));
                    String v4_10 = v1_6.getString("android.support.customtabs.customaction.DESCRIPTION");
                    if (v3_6 != null) {
                        v3_8 = new android.graphics.drawable.BitmapDrawable(this.R0(), v3_6);
                    } else {
                        v3_8 = 0;
                    }
                    v0_7.c(new com.tuyafeng.support.widget.z$b(2, 0, v3_8, v4_10), new c8.c4(this, ((android.app.PendingIntent) g6.d.a(v1_6, "android.support.customtabs.customaction.PENDING_INTENT", android.app.PendingIntent))));
                }
                v0_7.c(new com.tuyafeng.support.widget.z$b(991, 0, lb.b.a(this.I(), x7.o.K0, x7.u.Ve), this.X0(x7.u.p8)), new c8.e4(this));
            }
            this.U1 = v0_7;
        }
        return this.U1;
    }

    public final void D9(int p7, int p8)
    {
        if (p7 == 1) {
            g6.i.g(this, hb.p4);
            return;
        } else {
            if (p7 == 2) {
                this.K9();
                return;
            } else {
                if (p7 == 3) {
                    this.m0.s1(3, 0);
                    return;
                } else {
                    if (p7 == 4) {
                        Class v7_9 = this.O0.d(this.j0());
                        int v8_4 = this.n0.C2();
                        int v1_1 = 0;
                        if (v8_4 != 0) {
                            int v3 = 0;
                            int v4 = 0;
                            while (v3 < v7_9[0].length) {
                                if (v8_4.equals(v7_9[0][v3])) {
                                    v4 = v3;
                                }
                                v3++;
                            }
                            v1_1 = v4;
                        }
                        w5.k.l(this.I()).d0(x7.u.A0).b0(v7_9[1], v1_1, new c8.l6(this, v7_9)).f0();
                        return;
                    } else {
                        if (p7 == 14) {
                            this.ob(1);
                            return;
                        } else {
                            if (p7 == 16) {
                                g6.i.g(this, hb.l7);
                                return;
                            } else {
                                if (p7 != 23) {
                                    if (p7 == 33) {
                                        this.V9();
                                        return;
                                    } else {
                                        if (p7 == 36) {
                                            if ((p8 & 8) == 0) {
                                                g6.i.g(this, hb.o5);
                                                return;
                                            } else {
                                                this.mb();
                                                return;
                                            }
                                        } else {
                                            if (p7 == 39) {
                                                g6.i.g(this, a9.o);
                                                return;
                                            } else {
                                                if (p7 == 19) {
                                                    g6.i.g(this, hb.w7);
                                                    return;
                                                } else {
                                                    if (p7 != 20) {
                                                        switch (p7) {
                                                            case 6:
                                                                this.da(this.G8());
                                                                return;
                                                            case 7:
                                                                this.Ia();
                                                                return;
                                                            case 8:
                                                                this.Pa();
                                                                return;
                                                            case 9:
                                                                this.m0.t1();
                                                                return;
                                                            case 10:
                                                                g6.i.g(this, i8.s);
                                                                return;
                                                            default:
                                                                switch (p7) {
                                                                    case 26:
                                                                        g6.i.g(this, jb.y4);
                                                                        return;
                                                                    case 27:
                                                                        g6.i.g(this, sa.d1);
                                                                        return;
                                                                    case 28:
                                                                        g6.i.g(this, z7.d);
                                                                        return;
                                                                    case 29:
                                                                        g6.i.g(this, jb.k5);
                                                                        return;
                                                                    default:
                                                                        return;
                                                                }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                g6.i.g(this, z7.v);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public final void Da(String p2)
    {
        this.Fa(p2, 0);
        return;
    }

    public final void Db(int p2)
    {
        if ((p2 != null) && (p2 != 3)) {
            this.v0.f(this.x0);
            return;
        } else {
            this.v0.k();
            return;
        }
    }

    public boolean E(android.view.View p3, android.view.MotionEvent p4)
    {
        mark.via.common.widget.v0 v0_0 = 0;
        if (p3) {
            if ((this.w1) && (((t4.b) p3).l())) {
                v0_0 = 1;
            }
            this.v1 = v0_0;
            if (this.u1 == null) {
                this.u1 = new mark.via.common.widget.v0().e(this.o0.p).d(new c8.s6$t(this));
            }
            if (!this.k1) {
                this.N0.onTouchEvent(p4);
            }
            return this.u1.b(p3, p4);
        } else {
            return 0;
        }
    }

    public void E1()
    {
        this.I().unregisterComponentCallbacks(this);
        super.E1();
        return;
    }

    public final String E8()
    {
        String v0_1 = this.L0.d();
        if (v0_1 != null) {
            return v0_1.getTitle();
        } else {
            return 0;
        }
    }

    public final void E9(ua.y p4)
    {
        t4.b v0 = this.C8();
        if (v0 != null) {
            v0.evaluateJavascript(this.Q0.a().c(this.Q0.c()), new c8.k5(p4));
            p4.m3(new c8.l5(this, v0));
        }
        return;
    }

    public final void Ea(String p4, int p5)
    {
        if (p4 != null) {
            c8.ua v4_1 = p4.trim();
            if (!v4_1.isEmpty()) {
                String v0_0 = z8.v2.e(this.I()).iterator();
                while (v0_0.hasNext()) {
                    t8.f v1_1 = ((ja.c) v0_0.next());
                    if (v1_1.d() == p5) {
                    }
                    if (v1_1 != null) {
                        int v5_2 = z8.v2.c(v1_1.d(), v1_1.a());
                        String v0_3 = i6.g0.a.h(v5_2.getUrl(), v4_1);
                        t8.f.a().h(v5_2.b());
                        this.m0.m0(v4_1);
                        c8.ua v4_3 = this.L0.d();
                        if (v4_3 == null) {
                            this.m0.i1(v0_3, 1);
                        } else {
                            v4_3.v(v0_3);
                            v4_3.o();
                        }
                        this.R8();
                    } else {
                        this.Da(v4_1);
                        return;
                    }
                }
                v1_1 = 0;
            }
        }
        return;
    }

    public final void Eb(int p9)
    {
        if (p9 != null) {
            android.graphics.drawable.LayerDrawable v2_7;
            int v1_5 = new g6.g();
            if (!this.n0.d()) {
                v2_7 = 268435456;
            } else {
                v2_7 = 587202559;
            }
            int v3_1 = new android.graphics.drawable.Drawable[1];
            v3_1[0] = v1_5.h(v2_7).c(((float) x8.h.e(this.I()))).a();
            android.graphics.drawable.LayerDrawable v2_4 = new android.graphics.drawable.LayerDrawable(v3_1);
            int v4_0 = g6.y.h(this.I(), 1082130432);
            if (p9 != 3) {
                int v5_0 = v4_0;
                this.x0.setPadding(0, 0, 0, 0);
                v2_4.setLayerInset(0, v4_0, v5_0, v4_0, v4_0);
            } else {
                int v5_1 = v4_0;
                int v4_1 = (v5_1 * 4);
                this.x0.setPadding(v4_1, 0, v4_1, 0);
                v2_4.setLayerInset(0, v4_1, v5_1, v4_1, v5_1);
            }
            g6.y.O(this.x0, v2_4);
            return;
        } else {
            this.x0.setBackgroundColor(0);
            return;
        }
    }

    public void F(String p5)
    {
        String v5_1 = z8.w2.f(p5);
        this.xb(v5_1);
        this.x0.u(android.webkit.URLUtil.isHttpsUrl(v5_1), (this.m0.W1(v5_1) ^ 1));
        int v0_1 = this.n0.d1();
        if ((this.b1) && (v0_1 == 0)) {
            v0_1 = 2;
        }
        this.Cb(v5_1);
        int v1_1 = this.U1;
        if (v1_1 != 0) {
            v1_1.setTitle(i6.i0.a.f(v5_1));
        }
        if ((v0_1 != 0) && ((!android.text.TextUtils.isEmpty(v5_1)) && (!u9.d.m(this.I(), v5_1)))) {
            String v5_2 = z8.b0.E(v5_1);
            if (v0_1 != 2) {
                if (android.webkit.URLUtil.isNetworkUrl(v5_2)) {
                    v5_2 = v5_2.substring((v5_2.indexOf("://") + 3));
                    int v0_8 = v5_2.indexOf(47);
                    if (v0_8 == (v5_2.length() - 1)) {
                        v5_2 = v5_2.substring(0, v0_8);
                    }
                }
            } else {
                int v0_10 = i6.i0.a.f(v5_2);
                if ((v0_10 != 0) && (!v0_10.isEmpty())) {
                    v5_2 = v0_10;
                }
            }
            this.x0.setTitle(v5_2);
        }
        return;
    }

    public final String F8(String p2)
    {
        return z8.b0.G(this.E8(), p2);
    }

    public final void F9(f8.w p3)
    {
        this.U7(new c8.q5(new ref.WeakReference(p3)));
        p3.i3(new c8.s6$h(this));
        return;
    }

    public final void Fa(String p2, boolean p3)
    {
        this.Ga(p2, p3, 0);
        return;
    }

    public final void Fb()
    {
        int v0_4 = i6.i0.a.b(this.G8());
        if (!v0_4.isEmpty()) {
            v5.b.a().e("HISTORY_CACHE").f(180).d("query", v0_4).a();
        } else {
            v5.b.d().b("HISTORY_CACHE");
        }
        this.X9(0);
        return;
    }

    public void G(int p2, int p3, int p4)
    {
        mark.via.common.widget.t v0 = this.A0;
        if (v0 != null) {
            v0.v(p2, p3);
            this.A0.x(p4);
            return;
        } else {
            return;
        }
    }

    public void G1(boolean p2)
    {
        super.G1(p2);
        if (p2 == 0) {
            this.ua();
            this.R1();
            return;
        } else {
            this.za();
            int v2_2 = x8.h.a(this.I());
            z8.l3.r(this.I(), v2_2);
            z8.l3.q(this.I(), v2_2);
            this.M1();
            return;
        }
    }

    public final String G8()
    {
        String v0_1 = this.L0.d();
        if (v0_1 != null) {
            return v0_1.getUrl();
        } else {
            return 0;
        }
    }

    public final void G9(f8.l0 p3)
    {
        c8.s6$l v0_0 = this.L0;
        if (v0_0 != null) {
            p3.q3(v0_0.c(), this.L0.m());
            p3.p3(new c8.s6$l(this));
            return;
        } else {
            return;
        }
    }

    public final void Ga(String p4, boolean p5, boolean p6)
    {
        if (p4 != null) {
            String v4_1 = p4.trim();
            if (!v4_1.isEmpty()) {
                String v4_2;
                c8.ua v0_4 = this.n0.X0();
                String v1_2 = i6.i0.a;
                if ((!v1_2.t(v4_1)) || ((p5 != null) && (v1_2.p(v4_1)))) {
                    c8.ua v5_3 = i6.g0.a.h(v0_4, v4_1);
                    t8.f.a().h(this.n0.I2());
                    this.m0.m0(v4_1);
                    v4_2 = v5_3;
                } else {
                    v4_2 = v1_2.v(v4_1, v0_4);
                    if (this.u(v4_2)) {
                        return;
                    }
                }
                c8.ua v5_6 = this.L0.d();
                if ((v5_6 == null) || (p6 != 0)) {
                    this.m0.i1(v4_2, 1);
                } else {
                    v5_6.v(v4_2);
                    v5_6.o();
                }
                this.R8();
            }
        }
        return;
    }

    public void H(int p2)
    {
        this.r9();
        mark.via.common.widget.t v0 = this.A0;
        if (v0 != null) {
            v0.w(p2);
            return;
        } else {
            return;
        }
    }

    public final i6.e H8()
    {
        int v0_0 = this.C8();
        if (v0_0 != 0) {
            return new i6.e(v0_0);
        } else {
            return 0;
        }
    }

    public final void H9(tb.k0 p7)
    {
        this.o0.setBlurEnabled(1);
        this.o0.setDescendantFocusability(393216);
        this.o0.clearFocus();
        p7.i0().a(new c8.s4(this));
        if (this.L0 != null) {
            java.util.ArrayList v0_3 = new java.util.ArrayList();
            java.util.Iterator v1_5 = this.L0.c().iterator();
            while (v1_5.hasNext()) {
                int v2_2 = ((r4.a) v1_5.next());
                n9.b v3_1 = new n9.b(v2_2.getUrl(), v2_2.getTitle());
                v3_1.m(v2_2.getId());
                v0_3.add(v3_1);
            }
            p7.N3(v0_3);
            return;
        } else {
            return;
        }
    }

    public final void Ha(android.app.PendingIntent p10)
    {
        if (p10 != null) {
            android.app.ActivityOptions v0_1;
            android.app.ActivityOptions v0_4 = this.G8();
            if (v0_4 == null) {
                v0_1 = 0;
            } else {
                if (!v0_4.isEmpty()) {
                    v0_1 = new android.content.Intent().setData(android.net.Uri.parse(v0_4));
                } else {
                }
            }
            android.content.Intent v4 = v0_1;
            if (android.os.Build$VERSION.SDK_INT >= 34) {
                android.app.ActivityOptions v0_3 = c8.m.a();
                z8.d.b(v0_3);
                c8.n.a(p10, this.j0(), 0, v4, 0, 0, 0, v0_3.toBundle());
                return;
            } else {
                p10.send(this.j0(), 0, v4);
                return;
            }
        } else {
            return;
        }
    }

    public final void I8()
    {
        c8.ua v0_4 = this.L0.d();
        if ((!this.R8()) && (v0_4 != null)) {
            if (!v0_4.j()) {
                if (!u9.d.o(this.I(), v0_4.getUrl())) {
                    this.m0.B0();
                }
            } else {
                if (v0_4.q()) {
                    v0_4.s();
                }
                v0_4.g();
                return;
            }
        }
        return;
    }

    public void I9(boolean p1)
    {
        if (this.n0.K1()) {
            this.Ma(1);
        }
        return;
    }

    public final void Ia()
    {
        String v0 = this.G8();
        String v1_0 = this.F8(v0);
        if ((v0 == null) || (u9.d.m(this.I(), v0))) {
            v0 = "";
            v1_0 = "";
        }
        if ((v1_0 == null) || (v1_0.isEmpty())) {
            v1_0 = this.X0(x7.u.e1);
        }
        this.Ja(v0, v1_0);
        return;
    }

    public final void J7()
    {
        String v0 = this.G8();
        String v1_0 = this.F8(v0);
        if ((v1_0 == null) || (v1_0.isEmpty())) {
            v1_0 = this.X0(x7.u.Qg);
        }
        this.k8(v0, v1_0);
        return;
    }

    public final void J8()
    {
        c8.ua v0_3 = this.L0.d();
        if ((!this.R8()) && (v0_3 != null)) {
            if (!v0_3.n()) {
                this.m0.U0();
            } else {
                if (v0_3.q()) {
                    v0_3.s();
                }
                v0_3.l();
                this.m0.s0();
                return;
            }
        }
        return;
    }

    public final boolean J9(String p7)
    {
        android.content.Intent v0_2;
        if (!p7.startsWith("mailto:")) {
            if (!p7.startsWith("tel:")) {
                if (!p7.startsWith("sms:")) {
                    if ((!p7.startsWith("magnet:")) && (!p7.startsWith("intent://"))) {
                        if (!p7.startsWith("tg:")) {
                            if (!p7.contains("://")) {
                                if ((p7.indexOf(58) > 0) && ((!p7.startsWith("data:")) && ((!p7.startsWith("javascript:")) && (!p7.startsWith("about:"))))) {
                                    v0_2 = z8.f1.c(this.I(), p7);
                                    if (v0_2 != null) {
                                        c8.j1 v2_4 = this.m0.P0(this.G8());
                                        if ((v2_4 != 2) && (this.k9())) {
                                            androidx.fragment.app.q v3_5 = z8.f1.a(this.I(), v0_2);
                                            if (v3_5 != null) {
                                                if (v2_4 != 1) {
                                                    boolean v1_3;
                                                    if (!v3_5.isEmpty()) {
                                                        Object[] v5 = new Object[1];
                                                        v5[0] = v3_5;
                                                        v1_3 = this.Y0(x7.u.Y0, v5);
                                                    } else {
                                                        v1_3 = this.X0(x7.u.v8);
                                                    }
                                                    new f6.h$b(this.j0()).h(v1_3).b(17039370).e(new c8.i1(this, v0_2)).f(new c8.j1(this, p7, v0_2)).a().s();
                                                } else {
                                                    try {
                                                        this.N2(v0_2);
                                                    } catch (Exception) {
                                                        g6.n.q(this.I(), x7.u.qg);
                                                    }
                                                    return 1;
                                                }
                                            } else {
                                                return 1;
                                            }
                                        }
                                        return 1;
                                    } else {
                                        return 0;
                                    }
                                }
                            } else {
                                boolean v1_11 = i6.i0.a;
                                if ((!v1_11.l(p7)) && ((!v1_11.s(p7)) && ((!z8.w2.z(p7)) && ((!z8.w2.t(p7)) && ((!z8.w2.s(p7)) && ((!p7.startsWith("data:")) && ((!p7.startsWith("javascript:")) && ((!p7.startsWith("ftp://")) && ((!p7.startsWith("tg:")) && (!p7.startsWith("view-source:"))))))))))) {
                                    v0_2 = z8.f1.c(this.I(), p7);
                                }
                            }
                            v0_2 = 0;
                        } else {
                            android.content.Intent v0_1;
                            if (!p7.startsWith("tg://")) {
                                c8.j1 v2_17 = new StringBuilder();
                                v2_17.append("tg://");
                                v2_17.append(p7.substring((p7.indexOf("tg:") + 3)));
                                v0_1 = v2_17.toString();
                            } else {
                                v0_1 = p7;
                            }
                            v0_2 = z8.f1.c(this.I(), v0_1);
                        }
                    } else {
                        v0_2 = z8.f1.c(this.I(), p7);
                    }
                } else {
                    v0_2 = new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse(p7));
                }
            } else {
                v0_2 = new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse(p7));
            }
        } else {
            android.content.Intent v0_7 = android.net.MailTo.parse(p7);
            v0_2 = z8.f1.g(v0_7.getTo(), v0_7.getSubject(), v0_7.getBody(), v0_7.getCc());
        }
    }

    public final void Ja(String p3, String p4)
    {
        if (p.z.e(this.I())) {
            if (android.os.Build$VERSION.SDK_INT < 26) {
                ((autodispose2.n) x6.j.s(new c8.s1(this)).D(g7.a.c()).z(w6.b.b()).I(u8.b.a(this.b1()))).a(new c8.t1(this, p3, p4), new x7.c0());
                return;
            } else {
                a8.u1.v3(p3, p4).f3(this.x0(), 0);
                return;
            }
        } else {
            g6.n.q(this.I(), x7.u.qg);
            return;
        }
    }

    public void K(boolean p3, boolean p4, android.os.Message p5)
    {
        if (!this.j9()) {
            int v3_7 = this.G8();
            String v1 = 0;
            if (!this.m0.V1(0, v3_7)) {
                if ((!this.n0.g2().h()) || (p4 != null)) {
                    if ((i6.i0.a.s(v3_7)) && (!this.m0.c1(v3_7))) {
                        v1 = this.X0(x7.u.V0);
                    }
                } else {
                    v1 = this.X0(x7.u.v1);
                }
            }
            if (v1 != null) {
                if (this.o1()) {
                    int v3_10 = new f6.h$b(this.j0()).h(v1).b(x7.u.X0).e(new c8.u5(this, p5));
                    java.util.Objects.requireNonNull(p5);
                    v3_10.g(new c8.v5(p5)).a().s();
                    return;
                } else {
                    p5.sendToTarget();
                    return;
                }
            } else {
                this.ea(p5);
                return;
            }
        } else {
            p5.sendToTarget();
            return;
        }
    }

    public final void K7()
    {
        String v0_0 = this.G8();
        if (!u9.d.m(this.I(), v0_0)) {
            this.n8(v0_0, this.E8());
            return;
        } else {
            this.n8("https://", 0);
            return;
        }
    }

    public final boolean K8(android.content.ClipData p8)
    {
        String v1_0;
        if (p8 != 0) {
            v1_0 = p8.getItemCount();
        } else {
            v1_0 = 0;
        }
        if (v1_0 != null) {
            String v2_3 = 0;
            int v3_0 = 0;
            while (v3_0 < v1_0) {
                int v4_0 = p8.getItemAt(v3_0);
                if (v4_0.getUri() == null) {
                    if (v4_0.getText() == null) {
                        if (v4_0.getHtmlText() != null) {
                            if (android.os.Build$VERSION.SDK_INT < 24) {
                                v2_3 = android.text.Html.fromHtml(v4_0.getHtmlText()).toString();
                            } else {
                                v2_3 = c8.h.a(v4_0.getHtmlText(), 0).toString();
                            }
                        }
                    } else {
                        v2_3 = v4_0.getText();
                    }
                } else {
                    v2_3 = v4_0.getUri().toString();
                }
                if ((v2_3 != null) && (v2_3.length() > 0)) {
                    break;
                }
                v3_0++;
            }
            if ((v2_3 != null) && (v2_3.length() != 0)) {
                String v1_2 = v2_3.toString();
                String v2_8 = i6.g0.a.e(v1_2);
                if (v2_8.size() != 1) {
                    this.ca(v1_2);
                } else {
                    this.ca(((String) v2_8.get(0)));
                }
            }
            return 1;
        } else {
            return 0;
        }
    }

    public final void K9()
    {
        this.l9();
        a8.k.m3().f3(this.x0(), a8.k.getSimpleName());
        return;
    }

    public final void Ka(int p11, boolean p12)
    {
        android.widget.FrameLayout v0_0 = Integer.valueOf(p11);
        float v1_2 = Boolean.valueOf(p12);
        int v2_16 = Integer.valueOf(this.A1);
        android.widget.ProgressBar v4_6 = new Object[3];
        int v5 = 0;
        v4_6[0] = v0_0;
        v4_6[1] = v1_2;
        v4_6[2] = v2_16;
        pc.a.a("try to set ui mode: %d, tab bar enabled: %s, orientation: %d", v4_6);
        if ((p11 < 0) || (p11 > 4)) {
            p11 = -1;
        }
        if (this.n1) {
            p11 = 4;
            p12 = 0;
        }
        if (p11 == -1) {
            p11 = this.Z0;
            this.n0.j(p11);
        }
        if ((p11 == 0) || (p11 == 3)) {
            if (this.A1 == 2) {
                if (p11 != 3) {
                    p11 = 1;
                } else {
                    p11 = 2;
                }
            }
            p12 = 0;
        }
        if ((this.W0 != p11) || (p12 != this.b1)) {
            int v2_15;
            this.R8();
            int v6_10 = this.W0;
            if (v6_10 == -1) {
                v2_15 = 1;
            } else {
                int v2_13;
                if (v6_10 != 0) {
                    v2_13 = 0;
                } else {
                    v2_13 = 1;
                }
                int v7_8;
                if (p11 != 0) {
                    v7_8 = 0;
                } else {
                    v7_8 = 1;
                }
                if (v2_13 != v7_8) {
                } else {
                    int v2_14;
                    if (v6_10 != 3) {
                        v2_14 = 0;
                    } else {
                        v2_14 = 1;
                    }
                    int v6_11;
                    if (p11 != 3) {
                        v6_11 = 0;
                    } else {
                        v6_11 = 1;
                    }
                    if (v2_14 == v6_11) {
                        v2_15 = 0;
                    }
                }
            }
            this.y0.removeAllViews();
            this.w0.removeAllViews();
            this.W7(p12);
            if (v2_15 != 0) {
                this.Db(p11);
            }
            if (p11 == 0) {
                this.y0.addView(this.x0);
                int v2_18 = this.A0;
                if (v2_18 != 0) {
                    this.y0.addView(v2_18);
                }
                this.w0.addView(this.u0);
            } else {
                if ((p11 == 1) || (p11 == 2)) {
                    if (p11 != 1) {
                        int v2_20 = this.A0;
                        if (v2_20 != 0) {
                            this.w0.addView(v2_20);
                        }
                        this.w0.addView(this.u0);
                    } else {
                        this.y0.addView(this.u0);
                        int v2_23 = this.A0;
                        if (v2_23 != 0) {
                            this.y0.addView(v2_23);
                        }
                    }
                } else {
                    if (p11 == 3) {
                        this.w0.addView(this.x0);
                        this.w0.addView(this.u0);
                    } else {
                        if (p11 == 4) {
                            this.y0.addView(this.D8());
                        }
                    }
                }
            }
            int v7_1;
            int v2_3 = g6.f.d(this.I(), x7.n.w);
            int vtmp8 = g6.f.d(this.I(), x7.n.F);
            if (p12 == 0) {
                v7_1 = 0;
            } else {
                v7_1 = v2_3;
            }
            android.widget.LinearLayout v9_0;
            int v6_5 = (vtmp8 + v7_1);
            int v7_3 = g6.f.d(this.I(), x7.n.b);
            android.view.ViewGroup$LayoutParams v8_2 = this.y0.getLayoutParams();
            if ((p11 != 2) && (p11 != 3)) {
                v9_0 = v6_5;
            } else {
                v9_0 = 0;
            }
            v8_2.height = v9_0;
            this.y0.setLayoutParams(v8_2);
            android.view.ViewGroup$LayoutParams v8_4 = this.w0.getLayoutParams();
            if (p11 != 3) {
                if ((p11 != 1) && (p11 != 4)) {
                    if ((p11 == 0) || (p12 == 0)) {
                        v2_3 = 0;
                    }
                    v8_4.height = (v7_3 + v2_3);
                } else {
                    v8_4.height = 0;
                }
            } else {
                v8_4.height = (v7_3 + v6_5);
            }
            android.widget.ProgressBar v4_2;
            this.w0.setLayoutParams(v8_4);
            int v2_8 = ((android.widget.RelativeLayout$LayoutParams) this.q0.getLayoutParams());
            if (this.y0.getChildCount() <= 0) {
                v4_2 = 0;
            } else {
                v4_2 = 1;
            }
            int v7_6;
            if (v4_2 == null) {
                v7_6 = 0;
            } else {
                v7_6 = this.y0.getId();
            }
            v2_8.addRule(3, v7_6);
            if (v4_2 == null) {
                v5 = this.w0.getId();
            }
            v2_8.addRule(2, v5);
            this.q0.setLayoutParams(v2_8);
            this.Eb(p11);
            if ((this.q1) && (this.X0 == 1)) {
                if ((p11 != 2) && (p11 != 3)) {
                    float v1_1 = ((float) v6_5);
                } else {
                    v1_1 = 0;
                }
                this.p0.setTranslationY(v1_1);
            }
            this.W0 = p11;
            this.b1 = p12;
            this.oa();
            return;
        } else {
            this.Db(p11);
            return;
        }
    }

    public void L(android.webkit.WebView p4)
    {
        if ((p4 != null) && (p4.getSettings().getJavaScriptEnabled())) {
            i6.s v0_4 = p4.getUrl();
            if ((v0_4 != null) && ((!v0_4.isEmpty()) && (!i6.i0.a.l(v0_4)))) {
                i6.s.a.a(new i6.e(p4), this.f1, String.valueOf(p4.getId()));
            }
        }
        return;
    }

    public final void L7(String p7, String p8, String p9)
    {
        w5.k v0_4 = w5.k.l(this.j0());
        Object[] v4 = new Object[1];
        v4[0] = i6.i0.a.e(p7);
        v0_4.e0(this.Y0(x7.u.tb, v4)).v(1).B(g6.y.h(this.I(), 1132855296)).z(new String[] {this.X0(x7.u.sb), this.X0(x7.u.z8), this.X0(17039360)}), new c8.y2(this, p7, p8, p9)).f0();
        return;
    }

    public final void L8(String p5, java.io.File p6, int p7)
    {
        if ((p7 != null) && (p6 != null)) {
            if ((p7 & 4) != 0) {
                this.R0.d(p5, 4);
                ((autodispose2.r) x6.o.g(new c8.j6(this, p6, p5)).l(g7.a.c()).j(w6.b.b()).m(u8.b.a(this.b1()))).a(new c8.k6(this), new x7.g0());
            }
            android.net.Uri v0_6 = android.net.Uri.fromFile(p6);
            if ((p7 & 1) != 0) {
                this.R0.d(p5, 1);
                int v1_9 = p6.getName().lastIndexOf(46);
                String v3_1 = "image/*";
                if (v1_9 > 0) {
                    v3_1 = l5.c.c(p6.getName().substring((v1_9 + 1)), "image/*");
                }
                z8.f1.m(this.I(), v0_6, v3_1);
            }
            if ((p7 & 2) != 0) {
                this.R0.d(p5, 2);
                this.Aa(v0_6);
            }
        }
        return;
    }

    public final void L9(String p2, String p3)
    {
        ((autodispose2.r) x6.o.g(new c8.m1(p2, p3)).l(g7.a.c()).j(w6.b.b()).m(u8.b.a(this))).a(new c8.n1(this), new x7.g0());
        return;
    }

    public void M(String p2)
    {
        g6.n.s(this.I(), p2);
        return;
    }

    public void M1()
    {
        this.m0.p1();
        if (this.B1) {
            z8.f.e(this.o0);
        }
        c5.a v0_5 = this.s0;
        if ((v0_5 == null) || (!v0_5.z())) {
            this.L0.f();
        }
        this.P0.c(this.p1);
        super.M1();
        return;
    }

    public final void M7()
    {
        int v0_0 = this.H8();
        if (v0_0 != 0) {
            d8.g v1_0 = Math.min(84, Math.max(8, this.n0.V1()));
            int v2_1 = this.n0.X1();
            if (v2_1 == 0) {
                v2_1 = -1;
            }
            i6.c0.a.f(v0_0, v2_1, v1_0, this.n0.w0());
            d8.g.c().f(v0_0.getUrl(), v2_1);
            this.C8().setAccentColor(v2_1);
            this.yb(v2_1, 1);
            return;
        } else {
            return;
        }
    }

    public final void M8()
    {
        int v0_0 = this.N1;
        if (v0_0 != 0) {
            this.Xa(v0_0);
            this.N1 = 0;
            return;
        } else {
            return;
        }
    }

    public final void M9(String p3)
    {
        this.l9();
        a8.k.n3(p3).f3(this.x0(), a8.k.getSimpleName());
        return;
    }

    public final void Ma(boolean p4)
    {
        boolean v1_3 = new Object[1];
        v1_3[0] = Boolean.valueOf(p4);
        pc.a.a("set fullscreen: %s", v1_3);
        mark.via.CustomTab v0_6 = this.j0();
        if (!(v0_6 instanceof mark.via.Shell)) {
            if ((v0_6 instanceof mark.via.CustomTab)) {
                ((mark.via.CustomTab) v0_6).X(p4);
            }
        } else {
            ((mark.via.Shell) v0_6).k0(p4);
        }
        z8.l3.n(this.j0().getWindow(), p4);
        if (p4 == null) {
            this.z0.setPadding(0, 0, 0, 0);
            this.y0.setPadding(0, 0, 0, 0);
            this.w0.setPadding(0, 0, 0, 0);
            this.u0.setPadding(0, 0, 0, 0);
        }
        return;
    }

    public void N(Runnable p1)
    {
        z8.h.d(this, p1);
        return;
    }

    public void N1(boolean p2)
    {
        super.N1(p2);
        int v0_0 = this.s0;
        if (v0_0 != 0) {
            v0_0.setInPipMode(p2);
        }
        if (p2 == null) {
            this.y0.setVisibility(0);
            this.w0.setVisibility(0);
            t4.b v2_1 = this.C8();
            if (v2_1 != null) {
                v2_1.onPause();
            }
            return;
        } else {
            this.y0.setVisibility(8);
            this.w0.setVisibility(8);
            return;
        }
    }

    public final void N7(String p2, String p3)
    {
        if ((p2 != null) && (!p2.isEmpty())) {
            android.content.Intent v2_1 = z8.f1.h(p2, p3);
            android.content.Context v3_7 = this.n0.u0();
            if ((v3_7 == null) || (v3_7.isEmpty())) {
                z8.f1.j(this.I(), v2_1);
            } else {
                v2_1.setPackage(v3_7);
                if (!z8.f1.j(this.I(), v2_1)) {
                    v2_1.setPackage(0);
                    z8.f1.j(this.I(), v2_1);
                    return;
                }
            }
        }
        return;
    }

    public final void N8()
    {
        String v0_0 = this.O1;
        if ((v0_0 != null) && (!v0_0.isEmpty())) {
            String v0_2 = this.O1;
            this.O1 = 0;
            this.sa(v0_2, 4);
        }
        return;
    }

    public final void N9(String p3)
    {
        ((autodispose2.r) x6.o.g(new c8.v1(p3)).l(g7.a.c()).j(w6.b.b()).m(u8.b.a(this))).a(new c8.w1(this), new x7.g0());
        return;
    }

    public final void Na(boolean p6)
    {
        if (this.k1 != p6) {
            this.k1 = p6;
            this.n0.T(p6);
            this.Cb(this.G8());
            if (p6 == null) {
                this.fb();
                this.Ma(this.n0.K1());
                this.p0.postDelayed(new c8.e6(this), 200);
            } else {
                this.Ma(1);
                this.ub();
                this.U8();
            }
            String v6_2;
            android.content.Context v1_3 = this.I();
            if (p6 == null) {
                Object[] v0_2 = new Object[1];
                v0_2[0] = this.X0(x7.u.L5);
                v6_2 = this.Y0(x7.u.a7, v0_2);
            } else {
                v6_2 = this.X0(x7.u.N5);
            }
            g6.n.s(v1_3, v6_2);
            this.wb();
            return;
        } else {
            return;
        }
    }

    public void O(int p2)
    {
        int v2_1 = this.L0.e(p2);
        if (v2_1 >= 0) {
            this.f(this.L0.k(v2_1), v2_1);
            return;
        } else {
            return;
        }
    }

    public final boolean O7()
    {
        int v0_5 = this.L0.d();
        if ((v0_5 == 0) || ((!v0_5.j()) && (u9.d.o(this.I(), v0_5.getUrl())))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean O8()
    {
        if (this.x0().u0() <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void O9(String p3)
    {
        this.l9();
        if (p3 != null) {
            android.os.Bundle v3_2 = p3.trim();
            if (!v3_2.isEmpty()) {
                cb.e0.R3(v3_2);
            }
        }
        g6.i.i(this, bb.i, bb.i.l3(1, 1), "RecordContainerFragment");
        return;
    }

    public final void Oa()
    {
        c8.u0 v2_2;
        w5.k v0_7 = this.n0.g2().s();
        if ((v0_7 == null) || ((this.i() <= 1) && ((!this.O7()) && (!this.P7())))) {
            v2_2 = 0;
        } else {
            v2_2 = 1;
        }
        if (v0_7 != null) {
            if (v2_2 == null) {
                this.b8(1);
                return;
            } else {
                w5.k.l(this.I()).d0(x7.u.If).I(x7.u.O7).V(x7.u.l, new c8.t0(this)).N(x7.u.J, new c8.u0(this)).f0();
                return;
            }
        } else {
            this.q8();
            return;
        }
    }

    public o5.a P(android.webkit.WebView p4)
    {
        return new o5.a(p4, this.Q0, this.m1);
    }

    public final boolean P7()
    {
        int v0_3 = this.L0.d();
        if (((v0_3 == 0) || (!v0_3.n())) && (!this.m0.W0())) {
            return 0;
        } else {
            return 1;
        }
    }

    public final boolean P8()
    {
        return this.o0.h();
    }

    public final void P9()
    {
        if (z8.c0.j()) {
            b9.g v0_7 = this.n0.n0();
            if ((v0_7 == null) || (v0_7.isEmpty())) {
                g6.i.g(this, a9.o);
                return;
            }
        }
        b9.g v0_3;
        this.x0().y1("result", this.b1(), new c8.v3(this));
        if (this.W0 != 1) {
            v0_3 = 80;
        } else {
            v0_3 = 48;
        }
        int v7 = (v0_3 | 8388613);
        b9.g v0_4 = this.C8();
        String v5 = this.G8();
        String v4 = pa.r.e().z(v5);
        if ((v0_4 == null) || ((!v0_4.getSettings().getJavaScriptEnabled()) || (!i6.i0.a.s(v5)))) {
            b9.g.q3(v4, v7).f3(this.x0(), 0);
            return;
        } else {
            i6.c0.a.l(new i6.e(v0_4), new c8.w3(this, v4, v5, this.E8(), v7));
            return;
        }
    }

    public final void Pa()
    {
        boolean v5 = this.n0.g2().l();
        java.util.List v3 = z8.b4.e(this.I());
        if (v5) {
            w5.k v0_2 = (v3.size() - 1);
            while (v0_2 >= null) {
                c8.f0 v1_6 = ((ja.c) v3.get(v0_2)).d();
                if ((v1_6 < null) && ((v1_6 != -3) && ((v1_6 != -4) && (v1_6 != -5)))) {
                    v3.remove(v0_2);
                }
                v0_2--;
            }
        }
        w5.k v0_4;
        w5.k v0_3 = this.n0;
        if (!v5) {
            v0_4 = v0_3.l0();
        } else {
            v0_4 = v0_3.U();
        }
        int v4 = v0_4;
        w5.k v0_6 = v3.size();
        String[] v6 = new String[v0_6];
        c8.f0 v1_0 = 0;
        int v7 = 0;
        while (v1_0 < v0_6) {
            v6[v1_0] = ((ja.c) v3.get(v1_0)).g();
            if (v4 == ((ja.c) v3.get(v1_0)).d()) {
                v7 = v1_0;
            }
            v1_0++;
        }
        c8.f0 v1_1;
        w5.k v0_8 = w5.k.l(this.I());
        if (!v5) {
            v1_1 = x7.u.C0;
        } else {
            v1_1 = x7.u.hh;
        }
        v0_8.d0(v1_1).b0(v6, v7, new c8.f0(this, v3, v4, v5, v6)).f0();
        return;
    }

    public boolean Q()
    {
        return this.l1;
    }

    public final void Q7(int p12, boolean p13, boolean p14)
    {
        int v0_0 = Integer.toHexString(p12);
        Object[] v1_7 = String.valueOf(p13);
        int v3_2 = new Object[2];
        v3_2[0] = v0_0;
        v3_2[1] = v1_7;
        pc.a.a("change color: %s, %s", v3_2);
        Object[] v1_1 = this.e1;
        if (v1_1 != null) {
            Object[] v1_4 = ((android.animation.Animator) v1_1.get());
            if ((v1_4 != null) && (v1_4.isRunning())) {
                v1_4.cancel();
            }
            this.e1 = 0;
        }
        int v3_1;
        Object[] v1_6 = this.P8();
        if (p12 != null) {
            v3_1 = 0;
        } else {
            v3_1 = 1;
        }
        int v6_8;
        int v5_5 = this.n0.d();
        int v6_3 = this.n0.E2();
        if (v5_5 != 0) {
            v6_8 = 0;
        } else {
            if ((v3_1 == 0) || (!v6_3.c())) {
                int v6_4;
                if (v3_1 == 0) {
                    v6_4 = p12;
                } else {
                    v6_4 = this.n0.c0();
                }
                if (!g6.y.C(v6_4)) {
                }
            } else {
                if (!v6_3.d()) {
                }
            }
            v6_8 = 1;
        }
        android.view.Window v9_3;
        boolean v7_2 = this.I();
        int v8_1 = 17170443;
        if (v5_5 == 0) {
            if (v6_8 == 0) {
                v9_3 = 17170443;
            } else {
                v9_3 = x7.m.g;
            }
        } else {
            v9_3 = x7.m.h;
        }
        boolean v7_3 = g6.f.b(v7_2, v9_3);
        android.view.Window v9_4 = this.I();
        if (v5_5 == 0) {
            if (v6_8 != 0) {
                v8_1 = x7.m.d;
            }
        } else {
            v8_1 = x7.m.e;
        }
        int v8_2 = g6.f.b(v9_4, v8_1);
        this.v0.setAccentColor(v8_2);
        this.x0.r(v8_2, v7_3);
        android.view.Window v9_7 = this.A0;
        if (v9_7 != null) {
            v9_7.y(v8_2, v7_3);
        }
        android.view.Window v9_8 = this.B0;
        if (v9_8 != null) {
            v9_8.l(v8_2, v7_3);
        }
        int v8_3 = this.U1;
        if (v8_3 != 0) {
            v8_3.setContentColor(v7_3);
        }
        if ((v5_5 == 0) || (p14 != 0)) {
            int v5_6 = 0;
        } else {
            v5_6 = this.n0.h2();
        }
        this.c1.setColor(android.graphics.Color.argb(v5_6, 0, 0, 0));
        int v5_10 = z8.l3.p(this.j0(), v6_8);
        boolean v7_0 = z8.l3.o(this.j0(), v6_8);
        int v8_0 = android.os.Build$VERSION.SDK_INT;
        if (v8_0 >= 21) {
            android.view.Window v9_2 = this.j0().getWindow();
            if (v9_2 != null) {
                if ((v6_8 != 0) && (v5_10 == 0)) {
                    int v5_0 = android.graphics.Color.argb(51, 0, 0, 0);
                } else {
                    v5_0 = 0;
                }
                int v5_1;
                c8.d.a(v9_2, v5_0);
                if ((v6_8 != 0) && (!v7_0)) {
                    v5_1 = android.graphics.Color.argb(51, 0, 0, 0);
                } else {
                    v5_1 = 0;
                }
                if ((v8_0 < 29) && (v3_1 == 0)) {
                    if ((v6_8 != 0) && (!v7_0)) {
                        v5_1 = g6.y.G(p12, -16777216, 1045220557);
                    } else {
                        v5_1 = p12;
                    }
                }
                c8.i.a(v9_2, v5_1);
                if (v8_0 >= 28) {
                    c8.j.a(v9_2, v5_1);
                }
            }
        }
        int v5_3 = this.U0;
        this.U0 = p12;
        this.V0 = p14;
        if (v5_3 == p12) {
            p13 = 0;
        }
        if (p13 != null) {
            mark.via.common.widget.m v13_2 = new android.animation.ArgbEvaluator();
            int v14_1 = Integer.valueOf(v5_3);
            android.widget.LinearLayout v12_1 = Integer.valueOf(p12);
            Object[] v1_2 = new Object[2];
            v1_2[0] = v14_1;
            v1_2[1] = v12_1;
            android.widget.LinearLayout v12_2 = android.animation.ValueAnimator.ofObject(v13_2, v1_2);
            v12_2.addUpdateListener(new c8.g4(this));
            v12_2.setDuration(((long) this.R0().getInteger(x7.q.a)));
            v12_2.start();
            this.e1 = new ref.WeakReference(v12_2);
            return;
        } else {
            if ((v3_1 == 0) || (v1_6 == null)) {
                this.o0.k(p12, 0);
                this.w0.setBackgroundColor(p12);
                mark.via.common.widget.m v13_12 = this.B0;
                if (v13_12 != null) {
                    v13_12.setBackgroundColor(p12);
                }
                return;
            } else {
                this.w0.setBackgroundColor(0);
                return;
            }
        }
    }

    public final boolean Q8()
    {
        int v0_0 = this.d();
        if ((v0_0 == 0) || (!d8.d.c().g(v0_0.getId()))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void Q9()
    {
        this.l9();
        if (!this.Z0) {
            g6.i.g(this, mark.via.download.b1);
            return;
        } else {
            this.Wa();
            return;
        }
    }

    public final void Qa(boolean p5)
    {
        if (this.a1() != null) {
            if (p5 == 0) {
                int v5_7 = this.C0;
                if (v5_7 != 0) {
                    int v5_19 = v5_7.getParent();
                    if ((v5_19 instanceof android.view.ViewGroup)) {
                        ((android.view.ViewGroup) v5_19).removeView(this.C0);
                    }
                    this.C0 = 0;
                }
            } else {
                if (this.C0 == null) {
                    int v5_16;
                    int v5_11 = ((mark.via.common.widget.f) new h6.a(new mark.via.common.widget.f(this.I()), new android.widget.RelativeLayout$LayoutParams(-1, g6.y.h(this.I(), 1109393408))).g(64, x7.p.U0).f(g6.e.a(this.I(), x7.k.c)).c(1).l());
                    this.C0 = v5_11;
                    v5_11.setVisibility(8);
                    this.C0.setCallback(new c8.s6$b(this));
                    int v5_13 = this.B0;
                    if (v5_13 == 0) {
                        v5_16 = Math.max(this.z0.indexOfChild(this.o0), 0);
                    } else {
                        v5_16 = Math.max(this.z0.indexOfChild(v5_13), 0);
                    }
                    this.z0.addView(this.C0, (v5_16 + 1));
                    return;
                }
            }
        }
        return;
    }

    public void R1()
    {
        j8.t v0_0;
        super.R1();
        this.wb();
        if ((!this.k9()) && (this.i1())) {
            v0_0 = 0;
        } else {
            v0_0 = 1;
        }
        if (v0_0 != null) {
            o4.a v3_0 = this.s0;
            if ((v3_0 == null) || (!v3_0.z())) {
                this.L0.a();
            }
        }
        if ((v0_0 != null) && (this.r0 == null)) {
            this.a9();
            if (w9.n.e().n()) {
                this.m0.m1(1);
            }
            this.m0.w1();
            this.P0.e(this.p1);
            this.m0.Y1();
            j8.t v0_9 = this.G1;
            if (v0_9 != null) {
                String v1_2 = this.i1;
                if (v1_2 != null) {
                    this.ib(v0_9.f(v1_2), 0);
                }
            }
            this.R7();
        }
        return;
    }

    public final void R7()
    {
        if (w9.n.e().m()) {
            w9.n.e().x(0);
            t4.b v0_1 = this.C8();
            if (v0_1 != null) {
                v0_1.post(new c8.r4(this, new ref.WeakReference(v0_1)));
                return;
            }
        }
        return;
    }

    public final boolean R8()
    {
        if (this.x0().u0() <= 0) {
            return 0;
        } else {
            this.ab();
            this.V8();
            this.x0().e1();
            return 1;
        }
    }

    public void R9(String p3)
    {
        int v0_0 = this.C8();
        if (v0_0 == 0) {
            this.S9(0, 0, p3);
            return;
        } else {
            v0_0.evaluateJavascript("javascript:(function(){var a=document.getElementsByClassName(\"box\"),b=0,c=a.length;if(0<c){var d=a[0].getBoundingClientRect().top;for(i=0;i<c;i++)if(a[i].getBoundingClientRect().top==d)b++;else break;return (d>0?d<<6:0)|b}return b})();", new c8.a3(this, p3));
            return;
        }
    }

    public final void Ra(boolean p3)
    {
        if (this.a1() != null) {
            if ((this.k1) || (p3 == 0)) {
                if (this.B0 != null) {
                    this.Cb(0);
                    this.B0 = 0;
                }
            } else {
                ((autodispose2.r) x6.o.g(new c8.y3(this)).l(g7.a.c()).j(w6.b.b()).m(u8.b.a(this.b1()))).a(new c8.z3(this), new x7.g0());
                return;
            }
        }
        return;
    }

    public void S(int p2, int p3, android.view.View$OnClickListener p4)
    {
        g6.n.r(this.I(), p2, p3, p4);
        return;
    }

    public void S1(android.os.Bundle p1)
    {
        super.S1(p1);
        if (!this.n1) {
            this.m0.N1();
        }
        return;
    }

    public final void S7()
    {
        this.U7(new c8.o0(this));
        return;
    }

    public final void S8()
    {
        this.T8(1);
        return;
    }

    public final void S9(int p4, int p5, String p6)
    {
        this.L0().y1("favoriteChanged", this, new c8.g3(this));
        this.L0().q().x(1).v(x7.i.a, x7.i.g, x7.i.g, x7.i.b).c(g6.i.a, qa.e1, qa.e1.a3(p4, p5), 0).g(0).i();
        return;
    }

    public final void Sa(String p2)
    {
        this.sa(p2, 1);
        return;
    }

    public void T()
    {
        boolean v0_0 = 0;
        int v1_0 = new Object[0];
        pc.a.a("on hide custom view", v1_0);
        if ((this.r0 != null) && ((this.G0 != null) && (this.L0.d() != null))) {
            if ((android.os.SystemClock.elapsedRealtime() - this.H0) >= 800) {
                this.I0 = 0;
            } else {
                this.I0 = (this.I0 + 1);
            }
            z8.n3.i(this.I(), this.J1);
            this.p0.setVisibility(0);
            this.r0.setKeepScreenOn(0);
            boolean v3_7 = this.s0;
            if (v3_7) {
                boolean v3_9 = ((android.view.ViewGroup) v3_7.getParent());
                if (v3_9) {
                    v3_9.removeView(this.s0);
                }
                this.s0.removeAllViews();
            }
            if ((this.k1) || (this.n0.K1())) {
                v0_0 = 1;
            }
            this.Ma(v0_0);
            this.s0 = 0;
            this.r0 = 0;
            try {
                this.G0.onCustomViewHidden();
            } catch (Exception) {
            }
            this.G0 = 0;
            if ((!this.k1) && (!z8.g2.d(this.y0()))) {
                this.fb();
            }
            this.wb();
            return;
        } else {
            boolean v0_5 = this.G0;
            if (v0_5) {
                try {
                    v0_5.onCustomViewHidden();
                } catch (Exception) {
                }
                this.G0 = 0;
            }
            return;
        }
    }

    public final void T7(String p3, String p4, String p5)
    {
        if ((!android.text.TextUtils.isEmpty(p3)) && ((!android.text.TextUtils.isEmpty(p4)) && ((!android.text.TextUtils.isEmpty(p5)) && (pa.r.i().o(p3))))) {
            ((autodispose2.r) x6.o.g(new c8.y1(p3, p4)).l(g7.a.c()).j(w6.b.b()).m(u8.b.a(this.b1()))).a(new c8.z1(this, p3, p4, p5), new x7.g0());
        }
        return;
    }

    public final void T8(boolean p3)
    {
        if (this.r1) {
            long v0_5 = this.t0;
            if (v0_5 != 0) {
                if (p3 == 0) {
                    v0_5.setVisibility(8);
                } else {
                    x.r.c(v0_5).i(new c8.p3(this)).h(((float) g6.y.h(this.I(), 1107296256))).a(0).d(160).f();
                }
                this.r1 = 0;
            }
        }
        return;
    }

    public final void T9()
    {
        this.l9();
        g6.i.i(this, bb.i, bb.i.l3(1, 2), "RecordContainerFragment");
        return;
    }

    public final boolean Ta(String p3)
    {
        if ((!this.g1()) || ((!this.k9()) || ((this.O8()) || ((this.s0 != null) || ((this.r0 != null) || ((this.k1) || ((this.n1) || ((this.i() > 1) || (!u9.d.o(this.I(), p3)))))))))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void U7(k8.o p4)
    {
        i6.c0 v0_2;
        if (this.L0 != null) {
            v0_2 = this.C8();
        } else {
            v0_2 = 0;
        }
        if ((p4 != null) && ((v0_2 != null) && (v0_2.isShown()))) {
            i6.c0.a.g(new i6.e(v0_2), new c8.w5(p4));
        }
        return;
    }

    public final void U8()
    {
        if ((this.q1) && ((this.X0 != 0) && (!this.B1))) {
            int v3_2;
            this.q1 = 0;
            x.w v1_0 = this.y0.getHeight();
            c8.n0 v2_1 = this.w0.getHeight();
            if (this.y0.getChildCount() <= 0) {
                v3_2 = 0;
            } else {
                v3_2 = 1;
            }
            if (v1_0 > null) {
                x.r.c(this.p0).h(0).d(180).e(x8.h.b()).f();
                x.w v1_17 = ((float) (- v1_0));
                x.r.c(this.y0).h(v1_17).d(180).e(x8.h.b()).i(new c8.i0(this)).f();
                if (v3_2 != 0) {
                    x.r.c(this.q0).h(v1_17).d(180).e(x8.h.b()).i(new c8.j0(this)).f();
                }
            }
            if (v2_1 > null) {
                c8.n0 v2_5 = ((float) v2_1);
                x.r.c(this.w0).h(v2_5).i(new c8.k0(this)).d(180).e(x8.h.b()).f();
                x.w v1_29 = this.B0;
                if (v1_29 != null) {
                    x.r.c(v1_29).h(v2_5).d(180).i(new c8.l0(this)).e(x8.h.b()).f();
                }
                x.w v1_2 = this.C0;
                if ((v1_2 != null) && (v1_2.getVisibility() == 0)) {
                    x.r.c(this.C0).h(v2_5).d(180).i(new c8.m0(this)).e(x8.h.b()).f();
                }
                if (v3_2 == 0) {
                    x.r.c(this.q0).h(v2_5).d(180).e(x8.h.b()).i(new c8.n0(this)).f();
                }
            }
            this.ha(0);
            this.ab();
        }
        return;
    }

    public final void U9()
    {
        this.L0().y1("qrcode", this, new c8.z4(this));
        g6.i.g(this, fb.q);
        return;
    }

    public final void Ua(String p8)
    {
        int v0_1 = i6.i0.a.f(p8);
        if ((v0_1 != 0) && (!v0_1.isEmpty())) {
            w5.k v1_5 = z8.b0.v(p8);
            if ((v1_5 != null) && (!v1_5.isEmpty())) {
                w5.k v5_2 = w5.k.l(this.I());
                c8.l1 v3_1 = new Object[1];
                v3_1[0] = v0_1;
                v5_2.e0(this.Y0(x7.u.Jf, v3_1)).J(v1_5).V(17039361, new c8.k1(this, v1_5)).N(17039360, 0).R(x7.u.x, new c8.l1(this, p8, v0_1)).f0();
                return;
            } else {
                w5.k v8_3 = w5.k.l(this.I());
                c8.l1 v3_6 = new Object[1];
                v3_6[0] = v0_1;
                v8_3.e0(this.Y0(x7.u.Jf, v3_6)).I(x7.u.X7).V(17039370, 0).f0();
            }
        }
        return;
    }

    public android.webkit.WebResourceResponse V(android.webkit.WebResourceRequest p2, String p3, String p4)
    {
        return this.m0.E0(p2, p3, p4);
    }

    public void V1(android.view.View p6, android.os.Bundle p7)
    {
        super.V1(p6, p7);
        Object[] v0_0 = new Object[0];
        pc.a.a("BrowserFragment::onViewCreated", v0_0);
        Object[] v0_2 = android.os.SystemClock.elapsedRealtime();
        this.n0.Z0();
        String v2_0 = this.n0.P1();
        if ((v2_0 != null) && (!v2_0.isEmpty())) {
            lb.b.c(z8.c1.G(this.I(), v2_0));
        }
        Long v7_1;
        this.X8();
        this.Z8();
        this.g9();
        this.f9();
        this.W8();
        if (p7 == null) {
            v7_1 = 0;
        } else {
            v7_1 = 1;
        }
        this.o0.post(new c8.o3(this, v7_1));
        this.w8();
        Object[] v0_1 = new Object[1];
        v0_1[0] = Long.valueOf((android.os.SystemClock.elapsedRealtime() - v0_2));
        pc.a.a("BrowserFragment::onViewCreated, cost time: %d", v0_1);
        return;
    }

    public final void V7(String p4, String p5)
    {
        this.L0().y1("bookmarkDialogResult2", this, new c8.x5(this));
        b8.n.o3(p4, p5).f3(this.x0(), b8.n.getSimpleName());
        return;
    }

    public final void V8()
    {
        if (this.o0.i()) {
            this.p0.setImportantForAccessibility(0);
            android.view.View[] v2_1 = new android.view.View[1];
            v2_1[0] = this.B0;
            z8.l.b(v2_1);
        }
        return;
    }

    public final void V9()
    {
        if (!g6.i.d(this)) {
            j8.p.C3().f3(this.x0(), 0);
            return;
        } else {
            return;
        }
    }

    public final void Va(android.view.View p9)
    {
        w5.k$l v2_0;
        w5.k v0_1 = new java.util.ArrayList();
        int v1_2 = new java.util.HashMap();
        w5.k$l v2_14 = this.w0();
        if (v2_14 != null) {
            v2_0 = g6.d.b(v2_14, "android.support.customtabs.extra.MENU_ITEMS", android.os.Bundle);
        } else {
            v2_0 = 0;
        }
        if (v2_0 != null) {
            w5.k$l v2_4 = v2_0.iterator();
            String v3_3 = 0;
            while (v2_4.hasNext()) {
                int v4_1 = ((android.os.Bundle) v2_4.next());
                String v5_1 = v4_1.getString("android.support.customtabs.customaction.MENU_ITEM_TITLE");
                int v4_3 = ((android.app.PendingIntent) g6.d.a(v4_1, "android.support.customtabs.customaction.PENDING_INTENT", android.app.PendingIntent));
                if ((v5_1 != null) && (v4_3 != 0)) {
                    v3_3++;
                    v1_2.put(Integer.valueOf(v3_3), v4_3);
                    v0_1.add(new w5.k$l(v3_3, v5_1));
                }
            }
        }
        w5.k$l v2_5 = this.U1;
        if ((v2_5 != null) && (v2_5.findViewById(990) == null)) {
            v0_1.add(new w5.k$l(990, this.X0(x7.u.d0)));
        }
        v0_1.add(new w5.k$l(994, this.X0(x7.u.c)));
        v0_1.add(new w5.k$l(993, this.X0(x7.u.F)));
        v0_1.add(new w5.k$l(996, this.X0(x7.u.g0)));
        w5.k$l v2_18 = this.n0.g2().D();
        String v3_11 = this.G8();
        if ((v2_18 != null) && ((i6.i0.a.s(v3_11)) && ((!r9.g.a().e(v3_11)) && (c8.sc.c().d().C(v3_11))))) {
            v0_1.add(new w5.k$l(997, this.X0(x7.u.Ah)));
        }
        v0_1.add(new w5.k$l(992, this.X0(x7.u.s)));
        v0_1.add(new w5.k$l(995, this.X0(x7.u.l9)));
        w5.k.l(this.I()).C(v0_1, new c8.v(this, v1_2)).i0(p9, p9.getWidth(), g6.y.h(this.I(), 1082130432));
        return;
    }

    public void W(String p3, int p4, android.view.View$OnClickListener p5)
    {
        new f6.h$b(this.j0()).h(p3).j(1).c(p4, p5).a().s();
        return;
    }

    public final void W7(boolean p5)
    {
        android.widget.FrameLayout$LayoutParams v1_2;
        c8.h3 v0_0 = this.A0;
        if (v0_0 == null) {
            v1_2 = 0;
        } else {
            v1_2 = 1;
        }
        if (p5 != v1_2) {
            if (p5 != null) {
                this.A0 = ((mark.via.common.widget.t) new h6.a(new mark.via.common.widget.t(this.I()), new android.widget.FrameLayout$LayoutParams(-1, g6.f.d(this.I(), x7.n.w))).V(new c8.h3(this)).l());
                return;
            } else {
                v0_0.setOnTabItemClickListener(0);
                this.A0.setOnNewTabButtonClickListener(0);
                this.A0.setOnDeleteItemClickListener(0);
                this.A0.setOnMoveTabListener(0);
                this.A0.setOnDeleteTabsListener(0);
                this.A0.setOnDuplicateTabListener(0);
                this.A0 = 0;
                return;
            }
        } else {
            return;
        }
    }

    public final void W8()
    {
        this.yb(0, 0);
        this.p0.setBackgroundColor(0);
        this.p0.setForeground(this.c1);
        this.m0.r0();
        this.m0.q0();
        this.m0.p0();
        if (!this.n0.v().l()) {
            z8.l3.n(z8.l3.e(this.y0()), 0);
        }
        return;
    }

    public final void W9()
    {
        this.L0().y1("restore_tabs", this, new c8.s2(this));
        g6.i.g(this, c8.pc);
        return;
    }

    public final void Wa()
    {
        androidx.fragment.app.l0 v0_6;
        if (this.W0 != 1) {
            v0_6 = 80;
        } else {
            v0_6 = 48;
        }
        this.x0().q().c(x7.p.C, f8.z, f8.d.f().g((v0_6 | 8388613)).j(this.B8()).h(-1).a(), 0).g(0).i();
        this.kb();
        this.S8();
        return;
    }

    public boolean X(android.webkit.ValueCallback p10, android.webkit.WebChromeClient$FileChooserParams p11)
    {
        this.F0 = p10;
        try {
            int v0_0 = c8.o.a(p11);
            String v1_7 = c8.p.a(p11);
            String[] v2_3 = new java.util.ArrayList();
        } catch (Exception) {
            return 1;
        }
        if ((v1_7 != null) && (v1_7.length > 0)) {
            int v4_1 = v1_7.length;
            int v5 = 0;
            while (v5 < v4_1) {
                String v6_0 = v1_7[v5];
                if (v6_0 != null) {
                    String v6_1 = v6_0.trim();
                    if (!v6_1.isEmpty()) {
                        if (v6_1.charAt(0) == 46) {
                            v6_1 = l5.c.c(v6_1.substring(1), 0);
                        }
                        if ((v6_1 != null) && (!v2_3.contains(v6_1))) {
                            v2_3.add(v6_1);
                        }
                    } else {
                    }
                }
                v5++;
            }
        }
        int v4_2 = new Object[1];
        v4_2[0] = v2_3;
        pc.a.a("upload file, mime types: %s", v4_2);
        if (!v2_3.isEmpty()) {
            if (v2_3.size() != 1) {
                v0_0.setType("*/*");
                String[] v3_0 = new String[0];
                v0_0.putExtra("android.intent.extra.MIME_TYPES", ((String[]) v2_3.toArray(v3_0)));
            } else {
                v0_0.setType(((String) v2_3.get(0)));
            }
        } else {
            v0_0.setType("*/*");
        }
        android.content.Intent v11_2 = ((String) c8.q.a(p11));
        if (android.text.TextUtils.isEmpty(v11_2)) {
            v11_2 = this.X0(x7.u.Pf);
        }
        this.P2(android.content.Intent.createChooser(v0_0, v11_2), 111);
        return 1;
    }

    public final void X7(android.webkit.WebView p7)
    {
        if ((p7 != null) && (android.os.Build$VERSION.SDK_INT >= 21)) {
            int v0_2 = this.j0();
            if ((v0_2 instanceof mark.via.Shell)) {
                v0_2 = ((mark.via.Shell) this.j0()).f0();
            }
            int v0_4 = ((android.print.PrintManager) g6.f.f(v0_2, android.print.PrintManager));
            if (v0_4 != 0) {
                String v1_1 = p7.getTitle();
                if ((v1_1 == null) || (v1_1.isEmpty())) {
                    v1_1 = i6.i0.a.f(p7.getUrl());
                }
                if ((v1_1 == null) || (v1_1.isEmpty())) {
                    String v1_4 = java.util.Locale.getDefault();
                    android.print.PrintAttributes v2_4 = this.X0(x7.u.Qg);
                    String v3 = z8.t1.d();
                    Object[] v4_1 = new Object[2];
                    v4_1[0] = v2_4;
                    v4_1[1] = v3;
                    v1_1 = String.format(v1_4, "%s - %s", v4_1);
                }
                try {
                    v0_4.print(v1_1, c8.f.a(p7, v1_1), new android.print.PrintAttributes$Builder().build());
                    return;
                } catch (Exception) {
                    g6.n.q(this.I(), x7.u.qg);
                }
            } else {
                g6.n.q(this.I(), x7.u.qg);
                return;
            }
        }
        return;
    }

    public final void X8()
    {
        ab.a v0_0 = ab.b.a();
        this.M0 = v0_0;
        v0_0.c(new c8.i6(this));
        this.M0.a(this.I());
        return;
    }

    public final void X9(boolean p1)
    {
        if (p1 == 0) {
            this.T9();
            return;
        } else {
            this.O9(0);
            return;
        }
    }

    public final void Xa(mark.via.download.e p5)
    {
        if ((!g6.i.d(this)) && ((p5 != null) && ((p5.h() != null) && ((!this.O0.f()) || (!this.O0.a(this.j0(), p5.h())))))) {
            if ((android.os.Build$VERSION.SDK_INT >= 33) && (!g6.f.g(this.I(), "android.permission.POST_NOTIFICATIONS"))) {
                try {
                    this.S1.a("android.permission.POST_NOTIFICATIONS");
                } catch (int v0_10) {
                    pc.a.i(v0_10);
                }
            }
            if ((this.O0.h()) || (!p5.h().startsWith("blob:"))) {
                if (!p5.h().startsWith("https://wormhole.app/download-stream/")) {
                    int v0_23;
                    int v0_19 = this.n0.x();
                    if (!z8.b1.o(v0_19)) {
                        int v0_20 = android.os.Build$VERSION.SDK_INT;
                        if ((v0_20 < 29) && ((v0_20 >= 23) && (!g6.f.g(this.I(), "android.permission.WRITE_EXTERNAL_STORAGE")))) {
                            v0_23 = 0;
                        } else {
                            v0_23 = 1;
                        }
                    } else {
                        v0_23 = z8.b1.a(this.I(), android.net.Uri.parse(v0_19));
                    }
                    if (v0_23 != 0) {
                        mark.via.download.m.n3(p5).f3(this.x0(), 0);
                    } else {
                        this.na(p5);
                        return;
                    }
                } else {
                    int v0_27 = this.C8();
                    if (v0_27 != 0) {
                        g6.n.q(this.I(), x7.u.ua);
                        String v1_18 = l5.b.b(p5.h(), p5.b(), p5.e());
                        if (!g6.p.f(v1_18)) {
                            v5.b.a().e("dl").f(60).d(p5.h(), v1_18).a();
                        }
                        s4.b.g(v0_27, "javascript:(function(){(function(a){return fetch(a).then(function(c){return c.blob()}).then(function(c){return new Promise(function(d,e){var b=new FileReader;b.onloadend=function(){return d(b.result)};b.onerror=e;b.readAsDataURL(c)})})})(\"__URL__\").then(function(a){window.via.download(\"__SECRET__\",\"__URL__\",a)}).catch(function(a){})})();".replace("__URL__", p5.h()).replace("__SECRET__", this.f1));
                        return;
                    } else {
                        g6.n.q(this.I(), x7.u.K1);
                        return;
                    }
                }
            } else {
                int v0_3 = this.C8();
                if (v0_3 != 0) {
                    g6.n.q(this.I(), x7.u.ua);
                    s4.b.g(v0_3, c8.a.a(this.y0(), this.f1, p5.h()));
                    return;
                } else {
                    g6.n.q(this.I(), x7.u.K1);
                    return;
                }
            }
        }
        return;
    }

    public void Y(int p1, int p2)
    {
        if (this.j9()) {
            this.R8();
        }
        this.Bb(0);
        mark.via.common.widget.t v1_2 = this.A0;
        if (v1_2 != null) {
            v1_2.x(p2);
            return;
        } else {
            return;
        }
    }

    public final void Y7(int[] p10)
    {
        if ((p10 != 0) && (p10.length != 0)) {
            int v1 = 0;
            if (p10.length != 1) {
                o4.a v3_7 = new Object[1];
                v3_7[0] = java.util.Arrays.toString(p10);
                pc.a.a("delete tabs: %s", v3_7);
                int v0_3 = new java.util.ArrayList();
                o4.a v3_0 = this.k();
                int v4_0 = p10.length;
                int v5_0 = 0;
                int v6 = 0;
                while (v5_0 < v4_0) {
                    r4.a v7_0 = p10[v5_0];
                    if (v7_0 == v3_0) {
                        v6 = 1;
                    }
                    r4.a v7_1 = this.L0.k(v7_0);
                    if (v7_1 != null) {
                        v0_3.add(v7_1);
                    }
                    v5_0++;
                }
                if (!v0_3.isEmpty()) {
                    this.m0.L1(v0_3);
                    if (v6 != 0) {
                        int v0_8 = (p10[(p10.length - 1)] - 1);
                        int v2_0 = this.i();
                        if (v0_8 < 0) {
                            v0_8 = p10[0];
                            o4.a v3_3 = (v0_8 + 1);
                            if (v3_3 < v2_0) {
                                v0_8 = v3_3;
                            } else {
                                int v4_1 = 0;
                                while (v4_1 < p10.length) {
                                    if (v0_8 == p10[v4_1]) {
                                        v0_8--;
                                        v4_1++;
                                    }
                                }
                                v0_8 = -1;
                            }
                        }
                        if (v0_8 < 0) {
                            this.m0.g1("", 0, v2_0);
                            if (this.L0.j(v2_0)) {
                                this.m0.V0();
                            }
                        } else {
                            this.L0.j(v0_8);
                        }
                    }
                    int v0_13 = p10.length;
                    while (v1 < v0_13) {
                        this.L0.b(p10[v1]);
                        v1++;
                    }
                }
            } else {
                this.m0.C0(p10[0]);
                return;
            }
        }
        return;
    }

    public final void Y8()
    {
        if (this.t0 == null) {
            this.t0 = ((android.widget.ImageView) new h6.a(new android.widget.ImageView(this.I()), new android.widget.FrameLayout$LayoutParams(-2, -2)).E(g6.y.h(this.I(), 1092616192)).V(new c8.y4(this)).l());
            this.ta();
            new mark.via.common.widget.t0(this.t0).k(new c8.s6$d(this));
            return;
        } else {
            return;
        }
    }

    public final void Y9()
    {
        this.l9();
        g6.i.i(this, bb.i, bb.i.l3(1, 3), "RecordContainerFragment");
        return;
    }

    public final void Ya()
    {
        this.Za(0);
        return;
    }

    public void Z(r4.a p2, int p3)
    {
        this.R8();
        if (this.L0.i() > 1) {
            this.v0.j();
        }
        this.r9();
        mark.via.common.widget.t v2_3 = this.A0;
        if (v2_3 != null) {
            v2_3.u(p3);
            return;
        } else {
            return;
        }
    }

    public final void Z7(int p6, int p7)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        if ((p7 == 4) || ((p7 == 2) || (p7 == 1))) {
            int v2_0;
            int v3_0 = (this.i() - 1);
            if (p7 != 4) {
                v2_0 = (p6 + 1);
            } else {
                v2_0 = 0;
            }
            while (v3_0 >= v2_0) {
                v0_1.add(Integer.valueOf(v3_0));
                v3_0--;
            }
        }
        if ((p7 == 3) || (p7 == 1)) {
            int v6_1 = (p6 - 1);
            while (v6_1 >= 0) {
                v0_1.add(Integer.valueOf(v6_1));
                v6_1--;
            }
        }
        if (!v0_1.isEmpty()) {
            this.Y7(g6.a.d(v0_1));
        }
        return;
    }

    public final void Z8()
    {
        this.D0 = new c8.s6$n(this);
        android.content.IntentFilter v0_3 = new android.content.IntentFilter();
        v0_3.addAction("mark.via.gp.MEDIA_PLAY");
        v0_3.addAction("mark.via.gp.MEDIA_FASTFORWARD");
        v0_3.addAction("mark.via.gp.MEDIA_REWIND");
        if (android.os.Build$VERSION.SDK_INT < 33) {
            this.I().registerReceiver(this.D0, v0_3);
            return;
        } else {
            c8.l.a(this.I(), this.D0, v0_3, 4);
            return;
        }
    }

    public final void Z9()
    {
        this.aa(2);
        return;
    }

    public final void Za(String p5)
    {
        int v0_11 = ((android.widget.EditText) this.o0.m.findViewById(f8.o.e));
        if (v0_11 == 0) {
            int v0_2;
            int v0_12 = this.W0;
            int v1_1 = 1;
            if ((v0_12 != 1) && (v0_12 != 4)) {
                v0_2 = 80;
            } else {
                v0_2 = 48;
            }
            int v0_5 = f8.d.f().g((v0_2 | 8388613)).j(this.B8());
            if ((!this.V0) || (!this.P8())) {
                v1_1 = 0;
            }
            this.x0().q().c(x7.p.C, f8.h, v0_5.i(v1_1).e("TEXT", p5).a(), f8.h.v0).g(0).i();
            this.S8();
            return;
        } else {
            v0_11.setText(p5);
            return;
        }
    }

    public void a(boolean p4)
    {
        if ((this.B1 == p4) && (!this.E1)) {
            if (!p4) {
                boolean v4_2;
                this.E1 = 1;
                if (this.w0.getVisibility() != 0) {
                    v4_2 = 0;
                } else {
                    v4_2 = 1;
                }
                this.C1 = v4_2;
                this.D1 = this.r1;
                if (v4_2) {
                    this.w0.setVisibility(8);
                }
                if (this.D1) {
                    this.T8(0);
                }
                this.E1 = 0;
                this.B1 = 1;
            } else {
                this.E1 = 1;
                this.o0.post(new c8.q0(this));
                this.B1 = 0;
                return;
            }
        }
        return;
    }

    public boolean a0(android.content.Intent p5)
    {
        if (p5 != 0) {
            boolean v0 = z8.b0.H(p5);
            if (v0) {
                this.L0().g1(0, 1);
            }
            this.m0.q1(p5);
            return v0;
        } else {
            return 0;
        }
    }

    public final void a8()
    {
        int v0_0 = this.u1;
        if (v0_0 != 0) {
            v0_0.c();
        }
        this.d1 = -1;
        if ((this.r0 == null) && ((this.g1()) && (!this.n1()))) {
            this.Ka(this.n0.v0(), this.n0.v().t());
        }
        return;
    }

    public final void a9()
    {
        c8.ua v0_1;
        c8.ua v0_11 = this.n0.i0();
        if (v0_11 == 2) {
            v0_1 = 10;
        } else {
            if (v0_11 == 3) {
                v0_1 = 1;
            } else {
                if (v0_11 == 4) {
                    v0_1 = 0;
                } else {
                    v0_1 = 2;
                }
            }
        }
        c8.ua v0_15;
        z8.n3.i(this.I(), v0_1);
        this.O0.j(this.n0.C2());
        c8.ua v0_13 = this.n0.F();
        this.a1 = v0_13;
        if ((v0_13 != null) && (this.Q8())) {
            v0_15 = 0;
        } else {
            v0_15 = 1;
        }
        c8.ua v0_22;
        this.x0.t(v0_15, 0);
        this.w1 = this.n0.p2();
        this.ub();
        this.Ka(this.n0.v0(), this.n0.v().t());
        if (this.n0.l2() != 2) {
            v0_22 = 0;
        } else {
            v0_22 = 1;
        }
        c8.ua v0_25;
        this.Y0 = v0_22;
        if ((!z8.l3.a) || (z8.l.d())) {
            v0_25 = 0;
        } else {
            v0_25 = 1;
        }
        c8.ua v0_29;
        this.o0.n(v0_25);
        this.o0.m(v0_25);
        this.Ab();
        if ((!this.n0.d()) || (this.V0)) {
            v0_29 = 0;
        } else {
            v0_29 = this.n0.h2();
        }
        c8.ua v0_35;
        this.c1.setColor(android.graphics.Color.argb(v0_29, 0, 0, 0));
        if (!g6.y.C(this.n0.c0())) {
            v0_35 = 64;
        } else {
            v0_35 = 128;
        }
        if (!this.n0.d()) {
            v0_35 = 0;
        }
        this.o0.setWindowFilterColor(android.graphics.Color.argb(Math.max(v0_35, ((int) ((((float) this.n0.E2().b()) / 1120403456) * 1132396544))), 0, 0, 0));
        this.m0.Y0();
        this.Ra(this.n0.v().r());
        x8.g.f(this.x0);
        this.m0.g2();
        this.Qa(1);
        return;
    }

    public final void aa(int p5)
    {
        String v0_0 = this.G8();
        if ((v0_0 != null) && (!v0_0.isEmpty())) {
            Class v1_4 = i6.i0.a;
            if (!v1_4.l(v0_0)) {
                String v0_1 = v1_4.e(v0_0);
                if (!android.text.TextUtils.isEmpty(v0_1)) {
                    this.L0().y1("result", this, new c8.d1(this, v0_1));
                    g6.i.h(this, jb.u4, jb.u4.U3(v0_1, p5));
                    return;
                } else {
                    return;
                }
            }
        }
        g6.i.g(this, jb.y4);
        return;
    }

    public final void ab()
    {
        if ((!this.k1) && ((!this.r1) && ((this.Y0) && (!this.q1)))) {
            if (this.t0 == null) {
                this.Y8();
            }
            x.r.c(this.t0).j(new c8.o4(this)).h(0).a(1065353216).d(160).f();
            this.r1 = 1;
        }
        return;
    }

    public void b(int p3)
    {
        this.m0.K1(this.L0.k(p3));
        this.L0.b(p3);
        return;
    }

    public void b0(boolean p1)
    {
        this.tb();
        return;
    }

    public final void b8(boolean p4)
    {
        if (p4 == null) {
            c8.n2 v4_8 = this.i();
            this.m0.g1(0, 1, v4_8);
            c8.n2 v4_9 = (v4_8 - 1);
            while (v4_9 >= null) {
                this.m0.C0(v4_9);
                v4_9--;
            }
            z8.h.b(new c8.n2(this));
        }
        c8.n2 v4_3 = this.n0.g2();
        v4_3.V(0);
        this.n0.g0(v4_3);
        w9.n.e().u(1);
        g6.n.q(this.I(), x7.u.K6);
        this.m0.l1();
        this.x0.setIncognitoModeEnabled((1 ^ this.m0.W1(this.G8())));
        return;
    }

    public final void b9()
    {
        if (this.B0 == null) {
            mark.via.common.widget.m v0_7 = ((mark.via.common.widget.m) new h6.a(new mark.via.common.widget.m(this.I()), new android.widget.RelativeLayout$LayoutParams(-1, g6.y.h(this.I(), 1109393408))).g(64, x7.p.U0).c(1).V(new c8.m6(this)).l());
            this.B0 = v0_7;
            v0_7.m(java.util.Collections.EMPTY_LIST, 0);
            this.B0.setCallback(new c8.s6$a(this));
            return;
        } else {
            return;
        }
    }

    public final void ba()
    {
        this.ca("");
        return;
    }

    public final void bb(boolean p19, int p20)
    {
        int v3_1;
        String v6 = this.G8();
        w5.k v0_9 = this.m0.T0(v6);
        int v4 = this.n0.D0();
        if ((v0_9 <= null) && (!p19)) {
            v3_1 = 0;
        } else {
            v3_1 = 1;
        }
        if ((v3_1 != 0) && (v0_9 == null)) {
            v0_9 = v4;
        }
        if (v3_1 == 0) {
            v0_9 = v4;
        }
        android.widget.LinearLayout v8_5 = ((android.widget.LinearLayout) new h6.a(new android.widget.LinearLayout(this.I()), new android.widget.FrameLayout$LayoutParams(-1, -2)).V(new c8.y0()).l());
        android.widget.TextView v5_9 = ((android.widget.TextView) new h6.a(new android.widget.TextView(this.I()), new android.widget.LinearLayout$LayoutParams(-1, -2)).r(1, 0, 16, 0, 16).V(new c8.z0(this, v0_9)).l());
        w5.k v0_15 = ((mark.via.common.widget.w0) new h6.a(new mark.via.common.widget.w0(new android.view.ContextThemeWrapper(this.j0(), x7.v.f)), new android.widget.LinearLayout$LayoutParams(-1, -2)).H(1, 16, 0, 16, 0).t(1, 16).V(new c8.a1(this, v0_9, v3_1, v4, v5_9, v6)).l());
        v8_5.addView(v5_9);
        v8_5.addView(v0_15);
        if (v3_1 != 0) {
            v8_5.addView(((android.widget.TextView) new h6.a(new android.widget.TextView(this.I()), new android.widget.LinearLayout$LayoutParams(-1, -2)).r(1, 0, 2, 0, 8).V(new c8.b1(this)).l()));
        }
        w5.k.l(this.I()).y(v8_5).s(2).T(new c8.c1(this, p19, p20)).f0();
        return;
    }

    public java.util.List c()
    {
        return this.L0.c();
    }

    public String c0()
    {
        return this.f1;
    }

    public final void c8()
    {
        i6.e v0 = this.H8();
        if (v0 != null) {
            i6.c0.a.i(v0, new c8.e1(this));
            return;
        } else {
            return;
        }
    }

    public final void c9()
    {
        this.f1 = java.util.UUID.randomUUID().toString();
        this.Q0 = c8.sc.c();
        z8.h.b(new c8.r5(this));
        return;
    }

    public final void ca(String p14)
    {
        if (this.k9()) {
            int v8;
            this.L0().y1("input", this, new c8.q4(this));
            androidx.fragment.app.l0 v0_16 = this.W0;
            if ((v0_16 == 2) || (v0_16 == 3)) {
                v8 = 0;
            } else {
                v8 = 1;
            }
            if ((this.U0 == 0) || (this.n0.d())) {
                int v9 = 0;
            } else {
                v9 = 1;
            }
            if ((v8 == 0) || ((v9 != 0) || (!this.P8()))) {
                int v11 = 0;
            } else {
                v11 = 1;
            }
            androidx.fragment.app.l0 v0_6;
            String v5 = this.G8();
            androidx.fragment.app.l0 v0_5 = this.B0;
            if (v0_5 != null) {
                v0_6 = v0_5.i(v5);
            } else {
                v0_6 = 0;
            }
            String v6;
            if (v0_6 != null) {
                v6 = ((String) v0_6.b);
            } else {
                v6 = 0;
            }
            androidx.fragment.app.l0 v0_9;
            if (v0_6 != null) {
                v0_9 = ((ja.c) v0_6.a);
            } else {
                v0_9 = 0;
            }
            if ((v0_9 != null) && (android.text.TextUtils.isEmpty(p14))) {
                int v7 = v0_9.d();
            } else {
                v7 = 0;
            }
            this.L0().q().x(1).v(x7.i.a, x7.i.g, x7.i.g, x7.i.b).c(g6.i.a, tb.k0, tb.k0.A3(p14, v5, v6, v7, v8, v9, this.U0, v11, (this.m0.W1(v5) ^ 1)), "UrlInputFragment").g(0).i();
            return;
        } else {
            return;
        }
    }

    public final void cb(android.webkit.HttpAuthHandler p3, String p4, String p5)
    {
        int v5_1 = new StringBuilder();
        v5_1.append("https://");
        v5_1.append(p4);
        int v5_3 = v5_1.toString();
        this.L0().y1("url", this, new c8.f6(this, p3, p4, v5_3));
        c8.gb.t3(v5_3).f3(this.L0(), 0);
        return;
    }

    public r4.a d()
    {
        r4.a v0_0 = this.L0;
        if (v0_0 != null) {
            return v0_0.d();
        } else {
            return 0;
        }
    }

    public void d0(t4.b p4)
    {
        if (p4 != null) {
            int v0_0 = this.L0;
            if (v0_0 != 0) {
                int v0_1 = v0_0.i();
                int v1 = 0;
                while (v1 < v0_1) {
                    t4.b v2_2 = this.L0.k(v1);
                    if ((v2_2 == null) || (v2_2.p() != p4)) {
                        v1++;
                    } else {
                        this.m0.C0(v1);
                        return;
                    }
                }
            }
        }
        return;
    }

    public boolean d8(android.view.KeyEvent p6)
    {
        if (p6) {
            boolean v1 = this.k9();
            int v2 = p6.getKeyCode();
            int v3 = p6.getAction();
            if (v1) {
                if (v3 != 0) {
                    if (v3 != 1) {
                        return 0;
                    } else {
                        return this.A9(v2, p6);
                    }
                } else {
                    return this.z9(v2, p6);
                }
            } else {
                if ((v3 != 0) || (v2 != 111)) {
                    return 0;
                } else {
                    this.L0().e1();
                    return 1;
                }
            }
        } else {
            return 0;
        }
    }

    public final void d9()
    {
        if (this.u0 == null) {
            this.u0 = ((com.tuyafeng.support.widget.a) new h6.a(new com.tuyafeng.support.widget.a(this.I()), new android.widget.FrameLayout$LayoutParams(-1, -2)).l());
            mark.via.common.widget.g0 v0_8 = ((mark.via.common.widget.g0) new h6.a(new mark.via.common.widget.g0(this.I()), new android.widget.FrameLayout$LayoutParams(-1, g6.f.d(this.I(), x7.n.b))).l());
            this.v0 = v0_8;
            this.u0.addView(v0_8);
            this.u0.setDragDistance(g6.y.h(this.I(), 1118306304));
            this.u0.g(new c8.x1(this));
            this.v0.setOnItemClickListener(this.F1);
            this.v0.setOnItemLongClickListener(this.I1);
        }
        return;
    }

    public final void da(String p2)
    {
        android.content.Context v2_2 = z8.f1.f(this.I(), p2);
        if (v2_2 != null) {
            try {
                this.N2(v2_2);
                return;
            } catch (android.content.ActivityNotFoundException) {
                g6.n.q(this.I(), x7.u.j9);
                return;
            }
        } else {
            g6.n.q(this.I(), x7.u.j9);
            return;
        }
    }

    public final void db()
    {
        androidx.fragment.app.l0 v0_6;
        androidx.fragment.app.l0 v0_0 = this.W0;
        if ((v0_0 != 1) && (v0_0 != 4)) {
            v0_6 = 80;
        } else {
            v0_6 = 48;
        }
        this.x0().q().c(x7.p.C, f8.q, f8.d.f().g((v0_6 | 8388613)).j(this.B8()).a(), f8.q.u0).g(0).i();
        this.S8();
        return;
    }

    public int e(int p2)
    {
        return this.L0.e(p2);
    }

    public void e0(String[] p4, String p5)
    {
        if ((p4 != null) && (p4.length != 0)) {
            if (p4.length != 1) {
                w5.k.l(this.I()).e0(this.E8()).G(p4, new c8.s5(this, p4), new c8.t5(this, p4)).f0();
                return;
            } else {
                this.N7(p4[0], p5);
                return;
            }
        } else {
            this.B(0);
            return;
        }
    }

    public final void e8(mark.via.download.e p8)
    {
        if ((p8 != null) && ((p8.d() != null) && (p8.h() != null))) {
            c8.q1 v0_12 = p8.h();
            if (!this.O0.h()) {
                if (!this.O0.g()) {
                    this.f8(p8);
                } else {
                    if (android.webkit.URLUtil.isNetworkUrl(v0_12)) {
                        ((autodispose2.n) this.O0.b(0, v0_12, p8.d()).z(w6.b.b()).I(u8.b.a(this.b1()))).a(new c8.q1(this), new c8.r1(this));
                        return;
                    } else {
                        g6.n.q(this.I(), x7.u.M1);
                        return;
                    }
                }
            } else {
                if (android.webkit.URLUtil.isNetworkUrl(v0_12)) {
                    if (this.O0.c(this.I(), p8.h(), p8.d(), p8.i(), p8.e()) <= 0) {
                        g6.n.q(this.I(), x7.u.R3);
                        return;
                    } else {
                        g6.n.r(this.I(), x7.u.b4, x7.u.yh, new c8.o1(this));
                        return;
                    }
                } else {
                    g6.n.q(this.I(), x7.u.M1);
                    return;
                }
            }
        }
        return;
    }

    public final void e9()
    {
        if (this.x0 == null) {
            mark.via.common.widget.n0 v0_3 = ((mark.via.common.widget.n0) new h6.a(new mark.via.common.widget.n0(this.I()), new android.widget.FrameLayout$LayoutParams(-1, g6.f.d(this.I(), x7.n.F))).l());
            this.x0 = v0_3;
            v0_3.setOnItemClickListener(this.F1);
            this.x0.setOnItemLongClickListener(this.I1);
            this.x0.setOnDragListener(new c8.g1(this));
            this.U0 = -1;
        }
        return;
    }

    public final void ea(android.os.Message p6)
    {
        if (p6 != null) {
            android.webkit.WebView$WebViewTransport v0_1 = ((android.webkit.WebView$WebViewTransport) p6.obj);
            if (v0_1 != null) {
                String v1 = this.G8();
                this.m0.i1("", 1);
                t4.b v2_1 = this.m0.K0();
                if (v2_1 != null) {
                    t4.b v2_2 = v2_1.p();
                    if (v2_2 != null) {
                        v2_2.post(new c8.u1(v2_2, v1, v0_1, p6));
                        return;
                    }
                }
                p6.sendToTarget();
                return;
            } else {
                p6.sendToTarget();
                return;
            }
        } else {
            return;
        }
    }

    public final void eb()
    {
        if (!this.R8()) {
            i8.k v0_2;
            this.x0().y1("menu_result", this.b1(), new c8.j4(this));
            if (this.W0 != 1) {
                v0_2 = 80;
            } else {
                v0_2 = 48;
            }
            i8.k.w3(this.G8(), this.E8(), this.B8(), (v0_2 | 8388613)).f3(this.x0(), 0);
            return;
        } else {
            return;
        }
    }

    public void f(r4.a p1, int p2)
    {
        p1 = this.A0;
        if (p1 != null) {
            p1.t(p2);
            return;
        } else {
            return;
        }
    }

    public boolean f0(String p1)
    {
        return this.u(p1);
    }

    public final void f8(mark.via.download.e p3)
    {
        if ((p3 != null) && ((p3.d() != null) && (p3.h() != null))) {
            ((autodispose2.r) x6.o.g(new c8.e2(this, p3, this.n0.x())).l(g7.a.c()).j(w6.b.b()).m(u8.b.a(this.b1()))).a(new c8.f2(this, p3), new x7.g0());
        }
        return;
    }

    public final void fa(int p5)
    {
        int v0_0 = 2;
        int v1_0 = 0;
        switch (p5) {
            case 1:
                this.ka();
                return;
            case 2:
            case 3:
                int v3_1 = this.C8();
                if (p5 != 2) {
                    v1_0 = 1;
                }
                z8.b0.V(v3_1, v1_0);
                return;
            case 4:
                this.fb();
                this.ba();
                return;
            case 5:
                this.m0.j1(0, 1);
                return;
            case 6:
                this.J7();
                return;
            case 7:
            case 8:
                if (p5 == 7) {
                    v1_0 = 1;
                }
                this.X9(v1_0);
                return;
            case 9:
                this.m0.B0();
                return;
            case 10:
            case 11:
                c8.ua v5_9;
                int vtmp2 = this.m0.L0();
                if (p5 != 10) {
                    v5_9 = 1;
                } else {
                    v5_9 = -1;
                }
                int v0_5 = (vtmp2 + v5_9);
                c8.ua v5_11 = this.L0.i();
                if (v0_5 >= 0) {
                    if (v0_5 < v5_11) {
                        v1_0 = v0_5;
                    }
                } else {
                    v1_0 = (v5_11 - 1);
                }
                this.m0.X1(v1_0);
                this.R8();
                return;
            case 12:
            case 13:
                if (p5 != 12) {
                    this.J8();
                    return;
                } else {
                    this.I8();
                    return;
                }
            case 14:
                if (this.R8()) {
                } else {
                    this.Ya();
                }
                break;
            case 15:
                this.m0.Z1();
                return;
            case 16:
                this.ra();
                return;
            case 17:
            case 18:
                int v1_1 = this.C8();
                if (p5 != 17) {
                    v0_0 = 3;
                }
                z8.b0.V(v1_1, v0_0);
                return;
            case 19:
                this.m0.P1();
                return;
            case 20:
                this.m0.i2();
                return;
            case 21:
            case 22:
                if (p5 == 21) {
                    v1_0 = 1;
                }
                this.u8(v1_0);
                return;
            case 23:
                if (this.R8()) {
                } else {
                    this.nb();
                    return;
                }
            case 24:
                this.ja(this.d());
                return;
            case 25:
                this.vb();
                return;
            case 26:
                g6.i.g(this, hb.o6);
                return;
            case 27:
                this.i8(this.d());
                return;
            case 28:
                this.Q9();
                return;
            case 29:
                this.P9();
                return;
            case 30:
                this.K9();
                return;
            default:
        }
        return;
    }

    public final void fb()
    {
        if ((!this.q1) && ((this.X0 != 0) && (!this.B1))) {
            x.w v1_1;
            this.q1 = 1;
            if (this.y0.getChildCount() <= 0) {
                v1_1 = 0;
            } else {
                v1_1 = 1;
            }
            android.view.animation.Interpolator v2_12 = this.y0.getHeight();
            int v3_1 = this.w0.getHeight();
            if (v2_12 > null) {
                x.r.c(this.p0).h(((float) v2_12)).d(180).e(x8.h.b()).f();
                x.r.c(this.y0).h(0).d(180).e(x8.h.b()).j(new c8.t3(this)).f();
                if (v1_1 != null) {
                    this.q0.setTranslationY(((float) (- v2_12)));
                    x.r.c(this.q0).h(0).d(180).e(x8.h.b()).f();
                }
            }
            if (v3_1 > 0) {
                x.r.c(this.w0).h(0).j(new c8.u3(this)).d(180).e(x8.h.b()).f();
                android.view.animation.Interpolator v2_26 = this.B0;
                if (v2_26 != null) {
                    v2_26.setTranslationY(((float) v3_1));
                    x.r.c(this.B0).h(0).d(180).e(x8.h.b()).f();
                }
                android.view.animation.Interpolator v2_1 = this.C0;
                if ((v2_1 != null) && (v2_1.getVisibility() == 0)) {
                    this.C0.setTranslationY(((float) v3_1));
                    x.r.c(this.C0).h(0).d(180).e(x8.h.b()).f();
                }
                if (v1_1 == null) {
                    this.q0.setTranslationY(((float) v3_1));
                    x.r.c(this.q0).h(0).d(180).e(x8.h.b()).f();
                }
            }
            this.ha(1);
            this.S8();
        }
        return;
    }

    public void g(r4.g p4, int p5, boolean p6)
    {
        r4.a v0_1 = this.L0.d();
        r4.a v5_1 = this.L0.g(p4, p5, p6);
        if (this.n1) {
            v5_1.x();
        }
        if ((p4 instanceof na.g)) {
            int v2;
            c8.ua v1_0 = this.m0;
            c8.ua v4_2 = ((na.g) p4).c();
            if (v5_1 != null) {
                v2 = v5_1.getId();
            } else {
                v2 = -1;
            }
            v1_0.o0(v4_2, v2, p6);
        }
        if ((v5_1 != null) && ((p6) && ((v0_1 != null) && (!v0_1.q())))) {
            this.m0.M1(v0_1);
        }
        return;
    }

    public void g0(String p2, String p3)
    {
        if (!u9.d.k(this.I(), p3)) {
            this.m0.s0();
        }
        if (this.m0.W1(p3)) {
            t9.e.d(this.I(), p3, z8.b0.G(p2, p3));
        }
        return;
    }

    public final void g8(String p3)
    {
        if (!g6.p.f(p3)) {
            mark.via.download.e v3_2;
            long v0_2 = new mark.via.download.e$b().j(p3).k(this.n0.H1()).c("attachment");
            if (!android.webkit.URLUtil.isNetworkUrl(p3)) {
                v3_2 = 0;
            } else {
                v3_2 = "image/*";
            }
            this.Xa(v0_2.g(v3_2).i(this.G8()).d(-1).b());
            return;
        } else {
            return;
        }
    }

    public final void g9()
    {
        this.c9();
        o4.a v0_14 = c5.b.r(this.I());
        this.P0 = v0_14;
        if (!v0_14.o()) {
            this.P0.k();
        }
        this.O0 = mark.via.download.j1.e();
        this.Z0 = g6.y.E(this.I());
        this.A1 = this.R0().getConfiguration().orientation;
        this.N0 = new android.view.GestureDetector(this.j0(), new c8.s6$o(this));
        o4.a v0_13 = new o4.c(this.I());
        this.L0 = v0_13;
        v0_13.l(new c8.s6$p(this));
        this.L0.l(new e8.n0(this.n0, this));
        this.L0.l(new e8.e0());
        this.L0.l(new e8.j(d8.d.c(), this));
        this.L0.l(new e8.k(this));
        this.L0.l(new e8.l(this.I(), this));
        this.L0.l(new e8.c0(this));
        this.L0.l(new e8.i(pa.r.k(), this.n0, this));
        this.L0.l(new e8.h0(this.n0));
        this.m1 = new c8.s6$q(this);
        this.L0.l(new e8.i0(this));
        this.L0.l(new e8.b0());
        o4.a v0_30 = new e8.m();
        v0_30.G(new c8.s6$r(this));
        this.L0.l(v0_30);
        this.L0.l(new e8.k0(d8.g.c(), this.n0, this));
        this.L0.l(new e8.y0(pa.r.k(), this.n0, new c8.s6$s(this)));
        this.L0.r(this);
        return;
    }

    public final void ga()
    {
        String v0_2 = g6.n.d(this.I());
        if ((v0_2 != null) && (!v0_2.isEmpty())) {
            int v1_0 = i6.g0.a.e(v0_2);
            if (v1_0.size() > 1) {
                int v2_4 = new String[0];
                w5.k.l(this.I()).d0(x7.u.v3).J(v0_2).F(((String[]) v1_0.toArray(v2_4)), new c8.d0(this, v1_0)).V(x7.u.c9, new c8.e0(this, v1_0)).N(17039360, 0).f0();
            } else {
                if (!v1_0.isEmpty()) {
                    v0_2 = ((String) v1_0.get(0));
                }
                this.Da(v0_2);
                return;
            }
        }
        return;
    }

    public void h0(int p2)
    {
        this.yb(p2, 1);
        return;
    }

    public final void h8(String p5, String p6, int p7)
    {
        if (p7 != 0) {
            x6.o v6_8;
            x6.o v0_0 = i6.i0.a;
            if (!v0_0.k(p6)) {
                if (!v0_0.s(p5)) {
                    g6.n.q(this.I(), x7.u.M1);
                    return;
                } else {
                    x6.o v6_2 = z8.v0.g(p5);
                    x6.o v0_2 = mark.via.download.i1.c(p5, "image/*");
                    java.io.File v1_0 = l5.c.b(v0_2);
                    if (v1_0 != null) {
                        c8.y v2_1 = new StringBuilder();
                        v2_1.append(v6_2);
                        v2_1.append(".");
                        v2_1.append(v1_0);
                        v6_2 = v2_1.toString();
                    }
                    java.io.File v1_3 = new java.io.File(z8.c1.k(this.I(), "download", v6_2));
                    x6.o v6_6 = new g5.c();
                    v6_6.J(v1_3.getName());
                    v6_6.S(p5);
                    v6_6.M(this.G8());
                    v6_6.I(v0_2);
                    v6_6.N(1);
                    v6_6.x(1);
                    v6_6.P(80);
                    v6_6.C(android.net.Uri.fromFile(v1_3));
                    v6_8 = x6.o.g(this.P0.l(v6_6)).i(new c8.y(v6_6, v1_3));
                }
            } else {
                v6_8 = x6.o.g(new c8.x(this, p6, p5));
            }
            ((autodispose2.r) v6_8.l(g7.a.c()).j(w6.b.b()).m(u8.b.a(this.b1()))).b(new c8.z(this, p5, p7));
            return;
        } else {
            return;
        }
    }

    public final void h9(String p2)
    {
        new z8.t2(this).o(p2);
        return;
    }

    public final void ha(boolean p7)
    {
        c8.g6 v1_0 = 1;
        if (this.o0.m.getChildCount() == 1) {
            android.view.animation.Interpolator v0_6 = this.o0.m;
            int v3_3 = ((android.view.ViewGroup) v0_6.getChildAt(0)).getChildAt(0).getLayoutParams();
            if ((v3_3 instanceof android.widget.FrameLayout$LayoutParams)) {
                if ((((android.widget.FrameLayout$LayoutParams) v3_3).gravity & 80) != 80) {
                    v1_0 = 0;
                }
                int v2_1 = this.y0.getHeight();
                int v3_8 = this.w0.getHeight();
                if (p7 == null) {
                    c8.g6 v1_2;
                    x.w v7_1 = x.r.c(v0_6);
                    if (v1_0 == null) {
                        v1_2 = ((float) (- v2_1));
                    } else {
                        v1_2 = ((float) v3_8);
                    }
                    v7_1.h(v1_2).d(180).e(x8.h.b()).i(new c8.g6(v0_6)).f();
                    return;
                } else {
                    x.w v7_7;
                    if (v1_0 == null) {
                        v7_7 = ((float) (- v2_1));
                    } else {
                        v7_7 = ((float) v3_8);
                    }
                    v0_6.setTranslationY(v7_7);
                    x.r.c(v0_6).h(0).d(180).e(x8.h.b()).f();
                    return;
                }
            }
        }
        return;
    }

    public final void hb(String p5)
    {
        if ((p5 != null) && (!p5.isEmpty())) {
            if (p5.length() <= 1024) {
                c8.yb.q3(p5).f3(this.x0(), 0);
            } else {
                g6.n.r(this.I(), x7.u.v7, x7.u.s, new c8.w0(this, p5));
                return;
            }
        }
        return;
    }

    public int i()
    {
        int v0_0 = this.L0;
        if (v0_0 != 0) {
            return v0_0.i();
        } else {
            return 0;
        }
    }

    public final void i8(r4.a p5)
    {
        if (p5 != null) {
            int v0_1 = this.e(p5.getId());
            if (v0_1 >= 0) {
                this.m0.h1(new r4.b(p5.b()), 1, (v0_1 + 1));
                return;
            }
        }
        return;
    }

    public final boolean i9(String p2)
    {
        if ((p2 != 0) && ((!p2.isEmpty()) && (p2.equals(this.f1)))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void ia()
    {
        this.X7(this.C8());
        return;
    }

    public final void ib(j8.t p9, boolean p10)
    {
        if (this.a1() != null) {
            if ((p9) && (this.i1 != null)) {
                if ((p10 != null) && (this.j1 != null)) {
                    x.w v10_9 = new android.os.Bundle();
                    v10_9.putInt("y", ((int) this.j1.getTranslationY()));
                    v5.b.d().e(mark.via.common.widget.k1.getName(), v10_9);
                    x.w v10_12 = ((android.view.ViewGroup) this.j1.getParent());
                    if (v10_12 != null) {
                        v10_12.removeView(this.j1);
                    }
                    this.j1 = 0;
                }
                if (this.j1 == null) {
                    x.w v10_23;
                    x.w v10_18 = ((mark.via.common.widget.k1) new h6.a(new mark.via.common.widget.k1(this.I()), new android.widget.RelativeLayout$LayoutParams(-2, -2)).l());
                    this.j1 = v10_18;
                    v10_18.setCallback(new c8.u4(this));
                    this.j1.setAlpha(0);
                    this.z0.addView(this.j1);
                    x.w v10_22 = v5.b.d().c(mark.via.common.widget.k1.getName());
                    if (v10_22 == null) {
                        v10_23 = -1;
                    } else {
                        v10_23 = v10_22.getInt("y", -1);
                    }
                    if (v10_23 == -1) {
                        v10_23 = (this.z0.getHeight() / 2);
                    }
                    this.j1.setTranslationY(((float) v10_23));
                    x.r.c(this.j1).a(1065353216).d(100).f();
                }
                Object[] v0_3 = new Object[1];
                v0_3[0] = Boolean.valueOf(p9.h());
                pc.a.a("playing: %s", v0_3);
                this.j1.setPlaying(p9.h());
                return;
            } else {
                if (this.j1 != null) {
                    boolean v9_3 = new android.os.Bundle();
                    v9_3.putInt("y", ((int) this.j1.getTranslationY()));
                    v5.b.d().e(mark.via.common.widget.k1.getName(), v9_3);
                    x.r.c(this.j1).a(0).d(100).i(new c8.t4(this)).f();
                }
            }
        }
        return;
    }

    public void j(int p3)
    {
        r4.a v0 = this.d();
        if ((this.L0.j(p3)) && ((v0 != null) && (!v0.q()))) {
            this.m0.M1(v0);
        }
        return;
    }

    public final void j8(String p2)
    {
        this.k8(p2, "");
        return;
    }

    public final boolean j9()
    {
        if (this.g1 <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void ja(r4.a p9)
    {
        android.content.Context v9_1;
        if (p9 != null) {
            v9_1 = p9.p();
        } else {
            v9_1 = 0;
        }
        if ((android.os.Build$VERSION.SDK_INT > 19) && ((v9_1 != null) && (v9_1.isShown()))) {
            String v4 = v9_1.getUrl();
            if (this.m0.e1(v4)) {
                if (this.G1 == null) {
                    this.G1 = j8.c.n(this.I());
                    int v0_6 = new c8.m4(this);
                    this.h1 = v0_6;
                    this.G1.b(v0_6);
                    this.G1.g().g(this.n0.O());
                }
                int v0_12 = this.G1.g().e();
                if ((v0_12 == 0) || ((v0_12 == 1) || (v0_12 == 2))) {
                    g6.n.q(this.I(), x7.u.Ch);
                    i6.c0.a.o(new i6.e(v9_1), new c8.n4(this, java.util.UUID.randomUUID().toString().replace("-", ""), v4, v9_1.getTitle(), 0));
                } else {
                    g6.n.q(this.I(), x7.u.Dg);
                    return;
                }
            } else {
                g6.n.q(this.I(), x7.u.N1);
                return;
            }
        }
        return;
    }

    public final void jb(String p3)
    {
        if (!g6.i.d(this)) {
            if (u9.d.m(this.I(), p3)) {
                p3 = 0;
            }
            ua.y.l3(p3).f3(this.x0(), ua.y.getSimpleName());
            return;
        } else {
            return;
        }
    }

    public int k()
    {
        return this.L0.m();
    }

    public void k0(String p5)
    {
        w5.k v5_3 = w5.k.l(this.I()).d0(x7.u.b0).f(0, p5, x7.u.g6, 1).V(17039370, new c8.i5(this)).N(17039360, 0);
        if (android.os.Build$VERSION.SDK_INT >= 21) {
            v5_3.w(x7.u.pb, this.n0.v().p());
        }
        v5_3.f0();
        return;
    }

    public final void k8(String p2, String p3)
    {
        if ((p2 != null) && (!u9.d.m(this.I(), p2))) {
            if (p3 == null) {
                p3 = this.X0(x7.u.Qg);
            }
            ((autodispose2.m) x6.f.h(new c8.k4(p2, p3)).n(g7.a.c()).k(w6.b.b()).p(u8.b.a(this.b1()))).a(new c8.l4(this), new x7.c0());
            return;
        } else {
            this.V7("https://", "");
            return;
        }
    }

    public final boolean k9()
    {
        if (this.L0().u0() != 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void ka()
    {
        r4.a v0_1 = this.L0.d();
        if ((!this.R8()) && (v0_1 != null)) {
            d8.g.c().e(v0_1.getUrl());
            v0_1.c();
        }
        return;
    }

    public final void kb()
    {
        if (this.o0.l()) {
            this.p0.setImportantForAccessibility(4);
            android.view.View[] v1_2 = new android.view.View[1];
            v1_2[0] = this.B0;
            z8.l.c(v1_2);
        }
        return;
    }

    public final void l8(String p4)
    {
        this.L0().y1("bookmarkDialogResult2", this, new c8.p5(this));
        b8.n.m3(p4).f3(this.x0(), b8.n.getSimpleName());
        return;
    }

    public final void l9()
    {
        this.L0().y1("result", this, new c8.p4(this));
        return;
    }

    public final void la(boolean p4)
    {
        r4.a v0_1 = this.L0.d();
        if ((!this.R8()) && (v0_1 != null)) {
            String v4_2 = i6.h.a.m(v0_1.getUrl(), (p4 | this.Z0));
            if ((v4_2 == null) || (v4_2.isEmpty())) {
                v0_1.c();
            } else {
                d8.g.c().e(v4_2);
                v0_1.v(v4_2);
                return;
            }
        }
        return;
    }

    public final void lb()
    {
        w5.k.l(this.I()).d0(x7.u.V1).J(z8.b0.t(this.I(), this.A8())).V(17039370, 0).f0();
        return;
    }

    public void m(androidx.fragment.app.FragmentManager p1, androidx.fragment.app.Fragment p2)
    {
        if (!(p2 instanceof tb.k0)) {
            if ((p2 instanceof qa.e1)) {
                this.u9(((qa.e1) p2));
            }
            return;
        } else {
            this.H9(((tb.k0) p2));
            return;
        }
    }

    public final void m8(String p2)
    {
        this.n8(p2, 0);
        return;
    }

    public final void m9(String p3)
    {
        r4.a v0_1 = this.L0.d();
        if ((!this.R8()) && ((v0_1 != null) && ((p3 != null) && (!p3.isEmpty())))) {
            v0_1.v(p3);
        }
        return;
    }

    public final void ma()
    {
        w5.k v0_0 = this.G8();
        if (i6.i0.a.s(v0_0)) {
            w5.k.l(this.I()).d0(x7.u.Oa).a0(new String[] {this.X0(x7.u.Ja), this.X0(x7.u.Ha), this.X0(x7.u.Ia)}), -1).i("", this.X0(x7.u.La), 1).V(17039370, new c8.a0(this, new int[] {1, 2, 4}), v0_0)).N(17039360, 0).f0();
            return;
        } else {
            return;
        }
    }

    public final void mb()
    {
        androidx.fragment.app.l0 v0_0 = this.G8();
        androidx.fragment.app.l0 v1_0 = this.F8(v0_0);
        int v2_1 = i6.i0.a;
        Class v3_3 = v2_1.s(v0_0);
        if ((v3_3 == null) && ((v0_0 != null) && (v0_0.length() > 0))) {
            int v4_1 = v0_0.length();
            v0_0 = z8.w2.f(v0_0);
            if (v4_1 != v0_0.length()) {
                v1_0 = v2_1.f(v0_0);
            }
        }
        f8.d v5_3;
        int v2_0 = 1;
        if ((v3_3 == null) || (!this.m0.X0(v0_0))) {
            v5_3 = 0;
        } else {
            v5_3 = 1;
        }
        if ((v3_3 == null) || (this.A8() == null)) {
            Class v3_1 = 0;
        } else {
            v3_1 = 1;
        }
        f8.d v5_4;
        if (v5_3 == null) {
            v5_4 = 0;
        } else {
            v5_4 = 2;
        }
        f8.d v5_6;
        Class v3_2 = (v3_1 | v5_4);
        f8.d v5_5 = this.W0;
        if ((v5_5 == 2) || (v5_5 == 3)) {
            v5_6 = 80;
        } else {
            v5_6 = 48;
        }
        f8.d v5_9 = f8.d.f().g((v5_6 | 8388611)).j(this.B8());
        if ((!this.V0) || (!this.P8())) {
            v2_0 = 0;
        }
        this.x0().q().c(x7.p.C, f8.w, v5_9.i(v2_0).e("title", v1_0).e("url", v0_0).d("flag", v3_2).a(), 0).g(0).i();
        this.kb();
        this.S8();
        return;
    }

    public void n(int p2)
    {
        g6.n.q(this.I(), p2);
        return;
    }

    public final void n8(String p4, String p5)
    {
        if (!g6.i.d(this)) {
            this.L0().y1("fav_result", this, new c8.f1(this));
            qa.h0.E3(p4, p5).f3(this.x0(), qa.h0.getSimpleName());
            return;
        } else {
            return;
        }
    }

    public final void n9(int p8, int p9, String p10, String p11, String p12)
    {
        this.o9(p8, p9, p10, p11, p12, 0);
        return;
    }

    public final void na(mark.via.download.e p5)
    {
        this.N1 = 0;
        int v1_3 = this.n0.x();
        if (!z8.b1.o(v1_3)) {
            String v0_2 = android.os.Build$VERSION.SDK_INT;
            if ((v0_2 >= 23) && (v0_2 < 29)) {
                this.N1 = p5;
                try {
                    this.P1.a("android.permission.WRITE_EXTERNAL_STORAGE");
                    return;
                } catch (Exception v5_2) {
                    pc.a.i(v5_2);
                }
            }
            return;
        } else {
            this.N1 = p5;
            w5.k.l(this.I()).e0(this.X0(x7.u.Wf)).J(this.X0(x7.u.b8)).V(17039370, new c8.r(this, p5, android.net.Uri.parse(v1_3))).N(17039360, 0).f0();
            return;
        }
    }

    public final void nb()
    {
        androidx.fragment.app.l0 v0_3;
        androidx.fragment.app.l0 v1_0 = 1;
        if (this.W0 != 1) {
            v0_3 = 80;
        } else {
            v0_3 = 48;
        }
        androidx.fragment.app.l0 v0_9 = (v0_3 | 8388613);
        int v2_1 = (this.o0.m.getHeight() - g6.y.h(this.I(), 1098907648));
        androidx.fragment.app.l0 v0_2 = f8.d.f().g(v0_9).j(this.B8());
        if ((!this.V0) || (!this.P8())) {
            v1_0 = 0;
        }
        this.x0().q().c(x7.p.C, f8.l0, v0_2.i(v1_0).d("max_height", v2_1).a(), 0).g(0).i();
        this.kb();
        this.S8();
        return;
    }

    public void o(android.webkit.WebView p5)
    {
        if (p5 != null) {
            s4.b.g(p5, "javascript:(function(){ window.__VIA_SECRET__=\'__SECRET__\' })();".replace("__SECRET__", this.f1));
            String v0_5 = this.n0.Y0();
            if ((v0_5 != null) && (!v0_5.isEmpty())) {
                android.content.Context v1_1 = this.I();
                StringBuilder v2_1 = new StringBuilder();
                v2_1.append("/");
                v2_1.append(v0_5);
                s4.b.g(p5, c8.ab.a(v1_1, v2_1.toString()));
            }
        }
        return;
    }

    public final void o8()
    {
        android.os.Bundle v0_0 = this.L0();
        v0_0.y1("edit_text_result", this, new c8.v0(this, v0_0));
        g6.i.h(this, hb.i1, hb.i1.e3(this.X0(x7.u.N2), this.n0.w0(), this.X0(x7.u.N2), 1));
        return;
    }

    public final void o9(int p18, int p19, String p20, String p21, String p22, boolean p23)
    {
        this.l1 = (android.text.TextUtils.isEmpty(p20) ^ 1);
        String v7 = this.G8();
        w5.k v0_1 = u9.d.d(this.I(), v7);
        if ((v0_1 != null) || (p21 == null)) {
            if (((this.C8() != null) && ((!android.text.TextUtils.isEmpty(p20)) || (!android.text.TextUtils.isEmpty(p21)))) && ((v0_1 != 1) || ((p20 == null) || (!p20.equals(v7))))) {
                String v5_45 = 13;
                if ((p21 == null) || ((p21.isEmpty()) || (v0_1 != 13))) {
                    if ((v0_1 == -1) && ((this.s0 == null) && (this.r0 == null))) {
                        this.qb(p18, p19);
                    }
                    int v8_4 = new java.util.ArrayList();
                    if ((!this.n1) && (android.webkit.URLUtil.isNetworkUrl(p20))) {
                        v8_4.add(new w5.k$l(0, this.X0(x7.u.Q)));
                        v8_4.add(new w5.k$l(1, this.X0(x7.u.R)));
                    }
                    String v13_0 = 2;
                    if (v0_1 == -1) {
                        w5.k$l v4_45;
                        if (android.text.TextUtils.isEmpty(p21)) {
                            v4_45 = 0;
                        } else {
                            w5.k$l v4_43 = i6.i0.a;
                            if ((!v4_43.s(p21)) && ((!v4_43.k(p21)) && (!v4_43.l(p21)))) {
                            } else {
                                v4_45 = 1;
                            }
                        }
                        if (!android.text.TextUtils.isEmpty(p21)) {
                            v8_4.add(new w5.k$l(2, this.X0(x7.u.k0)));
                            v8_4.add(new w5.k$l(38, this.X0(x7.u.B)));
                            v8_4.add(new w5.k$l(6, this.X0(x7.u.a0)));
                            if (v4_45 != null) {
                                v8_4.add(new w5.k$l(33, this.X0(x7.u.pc)));
                            }
                            if (android.webkit.URLUtil.isNetworkUrl(p21)) {
                                v8_4.add(new w5.k$l(7, this.X0(x7.u.Kb)));
                            }
                            if ((i6.i0.a.s(v7)) && (this.m0.e1(v7))) {
                                v8_4.add(new w5.k$l(8, this.X0(x7.u.U)));
                            }
                        }
                        v8_4.add(new w5.k$l(22, this.X0(x7.u.S)));
                        if ((i6.i0.a.s(v7)) && ((this.m0.b1(v7)) && (this.m0.e1(v7)))) {
                            v8_4.add(new w5.k$l(23, this.X0(x7.u.L)));
                        }
                        if (!android.text.TextUtils.isEmpty(p22)) {
                            v8_4.add(new w5.k$l(29, this.X0(x7.u.u)));
                        }
                        if (v4_45 == null) {
                            v13_0 = 0;
                        } else {
                            v8_4.add(new w5.k$l(34, this.X0(x7.u.wb)));
                        }
                    } else {
                        if (v0_1 == 1) {
                            if (p21 != null) {
                            } else {
                                v8_4.add(new w5.k$l(32, this.X0(x7.u.Nc)));
                                v8_4.add(new w5.k$l(14, this.X0(x7.u.D)));
                                v8_4.add(new w5.k$l(15, this.X0(x7.u.x)));
                                v13_0 = 3;
                            }
                        } else {
                            if (v0_1 != 2) {
                                if (v0_1 == 3) {
                                    v8_4.add(new w5.k$l(16, this.X0(x7.u.x)));
                                    v8_4.add(new w5.k$l(17, this.X0(x7.u.y)));
                                    if ((!android.text.TextUtils.isEmpty(p20)) && ((!z8.w2.t(p20)) && ((!z8.w2.z(p20)) && (!i6.i0.a.l(p20))))) {
                                        v8_4.add(new w5.k$l(3, this.X0(x7.u.s)));
                                        v8_4.add(new w5.k$l(31, this.X0(x7.u.d0)));
                                    }
                                    w5.k$l v4_25 = this.n0.z1();
                                    if (v4_25.length <= 0) {
                                        if (v8_4.size() > v13_0) {
                                            v8_4.add(new w5.k$l(37, this.X0(x7.u.R2)));
                                        }
                                    } else {
                                        String v5_54 = new java.util.ArrayList();
                                        String v9_20 = (v8_4.size() - 1);
                                        while (v9_20 >= null) {
                                            if (g6.a.b(v4_25, ((w5.k$l) v8_4.get(v9_20)).a())) {
                                                v5_54.add(0, ((w5.k$l) v8_4.remove(v9_20)));
                                            }
                                            if (v5_54.size() == v4_25.length) {
                                                break;
                                            }
                                            v9_20--;
                                        }
                                        if (!p23) {
                                            if (v5_54.isEmpty()) {
                                                if (v8_4.size() > v13_0) {
                                                    v8_4.add(new w5.k$l(37, this.X0(x7.u.R2)));
                                                }
                                            } else {
                                                v8_4.add(new w5.k$l(36, this.X0(x7.u.q8)));
                                            }
                                        } else {
                                            if (!v5_54.isEmpty()) {
                                                v5_54.add(new w5.k$l(37, this.X0(x7.u.R2)));
                                            }
                                            v8_4 = v5_54;
                                        }
                                    }
                                    String v9_21 = v8_4;
                                    if (!v9_21.isEmpty()) {
                                        w5.k.l(this.I()).C(v9_21, new c8.r0(this, p20, p21, v0_1, p18, p19, v7, p22)).U(new c8.s0(this)).g0(p18, p19);
                                    }
                                    return;
                                } else {
                                    if (v0_1 != 6) {
                                        if (v0_1 == 7) {
                                            v8_4.add(new w5.k$l(18, this.X0(x7.u.x)));
                                            v13_0 = 1;
                                        } else {
                                            if (v0_1 != 10) {
                                                if (v0_1 != 11) {
                                                }
                                                if ((p20 != null) && (!p20.isEmpty())) {
                                                    String v9_18 = v8_4.size();
                                                    int v11_9 = z8.w2.p(p20);
                                                    if (p20.charAt((p20.length() - 1)) != 61) {
                                                        int v15_1;
                                                        if (v11_9 == 0) {
                                                            v15_1 = 9;
                                                        } else {
                                                            v15_1 = 12;
                                                        }
                                                        v8_4.add(new w5.k$l(v15_1, this.X0(x7.u.D)));
                                                        if (v11_9 == 0) {
                                                            v8_4.add(new w5.k$l(10, this.X0(x7.u.e)));
                                                        }
                                                        if (v11_9 == 0) {
                                                            v5_45 = 11;
                                                        }
                                                        v8_4.add(new w5.k$l(v5_45, this.X0(x7.u.x)));
                                                    }
                                                    v13_0 = (v8_4.size() - v9_18);
                                                }
                                            }
                                        }
                                    }
                                    String v5_43 = v8_4.size();
                                    if ((v0_1 == 6) && (this.m0.b1(p20))) {
                                        String v6_28 = this.m0.v0(p20);
                                        String v9_8 = this.m0.w0(p20);
                                        if ((v6_28 != null) || ((v9_8 != null) || (this.m0.T1(p20)))) {
                                            String v13_1;
                                            if (v6_28 == null) {
                                                v13_1 = 24;
                                            } else {
                                                v13_1 = 25;
                                            }
                                            String v6_29;
                                            if (v6_28 == null) {
                                                v6_29 = x7.u.h;
                                            } else {
                                                v6_29 = x7.u.h0;
                                            }
                                            int v11_5;
                                            v8_4.add(new w5.k$l(v13_1, this.X0(v6_29)));
                                            if (v9_8 == null) {
                                                v11_5 = 26;
                                            } else {
                                                v11_5 = 27;
                                            }
                                            String v9_9;
                                            if (v9_8 == null) {
                                                v9_9 = x7.u.i;
                                            } else {
                                                v9_9 = x7.u.i0;
                                            }
                                            v8_4.add(new w5.k$l(v11_5, this.X0(v9_9)));
                                        }
                                    }
                                    if (v0_1 == 10) {
                                        v8_4.add(new w5.k$l(19, this.X0(x7.u.V)));
                                    }
                                    v8_4.add(new w5.k$l(20, this.X0(x7.u.A)));
                                    v8_4.add(new w5.k$l(21, this.X0(x7.u.y)));
                                    v13_0 = (v8_4.size() - v5_43);
                                }
                            }
                        }
                    }
                } else {
                    this.g8(p21);
                    return;
                }
            }
            return;
        } else {
            this.g8(p21);
            return;
        }
    }

    public final void oa()
    {
        if ((android.os.Build$VERSION.SDK_INT >= 21) && (this.X0 == 1)) {
            x.r.U(this.z0);
        }
        return;
    }

    public final void ob(boolean p8)
    {
        int v0_0 = this.G8();
        if (android.webkit.URLUtil.isNetworkUrl(v0_0)) {
            c8.h1 v2_1;
            String v1_1 = z8.t1.g();
            if ((!z8.c0.f()) && (!z8.f.h())) {
                v2_1 = 0;
            } else {
                v2_1 = 1;
            }
            java.util.ArrayList v4_1 = new java.util.ArrayList();
            if (v2_1 != null) {
                if (p8 != null) {
                    v4_1.add(new w5.k$l(6, this.X0(x7.u.Cg)));
                }
                v4_1.add(new w5.k$l(1, this.X0(x7.u.yg)));
            }
            if (p8 != null) {
                v4_1.add(new w5.k$l(4, this.X0(x7.u.Ag)));
            }
            v4_1.add(new w5.k$l(3, this.X0(x7.u.zg)));
            v4_1.add(new w5.k$l(5, this.X0(x7.u.Bg)));
            w5.k.l(this.I()).d0(x7.u.g0).C(v4_1, new c8.h1(this, v0_0, v1_1)).f0();
            return;
        } else {
            g6.n.q(this.I(), x7.u.M1);
            return;
        }
    }

    public void onConfigurationChanged(android.content.res.Configuration p3)
    {
        super.onConfigurationChanged(p3);
        String v3_1 = p3.orientation;
        if (v3_1 != this.A1) {
            this.A1 = v3_1;
            Object[] v0_2 = new Object[1];
            v0_2[0] = Integer.valueOf(v3_1);
            pc.a.a("on configuration changed, orientation: %d", v0_2);
            this.a8();
        }
        this.Ab();
        return;
    }

    public void onDownloadStart(String p6, String p7, String p8, String p9, long p10)
    {
        if ((this.s0 == null) && (this.o1())) {
            String v2_0;
            String v0_9 = this.C8();
            String v1_0 = 0;
            if ((p6 == null) || (v0_9 == null)) {
                v2_0 = 0;
            } else {
                v2_0 = v0_9.getUrl();
                if ((v2_0 == null) || ((v2_0.isEmpty()) || (v2_0.equals(p6)))) {
                    if (v0_9.getReferer() == null) {
                        v2_0 = 0;
                    } else {
                        v2_0 = v0_9.getReferer();
                    }
                }
                if (v0_9.getProgress() >= 100) {
                    v0_9.stopLoading();
                }
            }
            String v0_3 = v5.b.d().c("dl");
            if (v0_3 != null) {
                v1_0 = v0_3.getString(p6, 0);
            }
            if ((v1_0 != null) && (!v1_0.isEmpty())) {
                String v0_6 = v1_0.lastIndexOf(46);
                if ((v0_6 < null) || ((v0_6 < (v1_0.length() - 11)) || (v0_6 == (v1_0.length() - 1)))) {
                    String v0_8 = z8.c1.r(l5.b.b(p6, p8, p9));
                    if ((v0_8 != null) && ((v0_8.length() < 10) && (!"bin".equals(v0_8)))) {
                        StringBuilder v3_12 = new StringBuilder();
                        v3_12.append(v1_0);
                        v3_12.append(".");
                        v3_12.append(v0_8);
                        v1_0 = v3_12.toString();
                    }
                }
            }
            this.Xa(new mark.via.download.e$b().j(p6).k(p7).c(p8).g(p9).i(v2_0).d(p10).e(v1_0).a(this.z8(i6.i0.a.f(v2_0))).b());
        }
        return;
    }

    public void onTrimMemory(int p8)
    {
        if (this.L0 != null) {
            int v8_1;
            if (p8 > 15) {
                if (p8 <= 80) {
                    if (p8 >= 60) {
                        if (p8 <= 60) {
                            v8_1 = 2;
                            r4.a v4_0 = new Object[1];
                            v4_0[0] = Integer.valueOf(v8_1);
                            pc.a.a("on trim memory, tab level: %d", v4_0);
                            java.util.Iterator v0_7 = this.L0.c().iterator();
                            while (v0_7.hasNext()) {
                                r4.a v4_3 = ((r4.a) v0_7.next());
                                if (v4_3 != null) {
                                    int v5_1 = 4;
                                    if (v8_1 == 1) {
                                        if (!v4_3.d()) {
                                            v4_3.m(4);
                                        }
                                    } else {
                                        if (v8_1 == 2) {
                                            if (!v4_3.d()) {
                                                v5_1 = 2;
                                            }
                                            v4_3.m(v5_1);
                                        } else {
                                            if (v8_1 == 3) {
                                                int v5_3;
                                                if (!v4_3.d()) {
                                                    v5_3 = 1;
                                                } else {
                                                    v5_3 = 2;
                                                }
                                                v4_3.m(v5_3);
                                            }
                                        }
                                    }
                                }
                            }
                            return;
                        }
                    }
                }
                v8_1 = 3;
            } else {
                if (p8 >= 10) {
                    if (p8 <= 10) {
                        v8_1 = 1;
                    }
                }
            }
        }
        return;
    }

    public void p(String p6, java.util.List p7)
    {
        if ((p7 != null) && (!p7.isEmpty())) {
            if (p7.size() != 1) {
                int v2_3 = new String[0];
                w5.k.l(this.I()).d0(x7.u.v3).J(p6).F(((String[]) p7.toArray(v2_3)), new c8.m3(this, p7)).V(x7.u.c9, new c8.n3(this, p7)).N(17039360, 0).f0();
                return;
            } else {
                this.m0.i1(((String) p7.get(0)), 1);
                return;
            }
        } else {
            if ((p6 != null) && (!p6.isEmpty())) {
                w5.k.l(this.I()).d0(x7.u.v3).J(p6).V(x7.u.Mb, new c8.l3(this, p6)).N(17039360, 0).f0();
            }
            return;
        }
    }

    public final void p8()
    {
        if (this.C8() != null) {
            this.m9(c8.kb.a(this.y0()));
            return;
        } else {
            return;
        }
    }

    public final String p9(String p6)
    {
        if (!"android.webkit.resource.AUDIO_CAPTURE".equals(p6)) {
            if ("android.webkit.resource.VIDEO_CAPTURE".equals(p6)) {
                String v0_2 = this.X0(x7.u.ha);
                String v4_1 = this.X0(x7.u.ia);
                Object[] v3_0 = new Object[2];
                v3_0[0] = v0_2;
                v3_0[1] = v4_1;
                p6 = this.Y0(x7.u.la, v3_0);
            }
            return p6;
        } else {
            String v0_5 = this.X0(x7.u.ja);
            String v4_3 = this.X0(x7.u.ka);
            Object[] v3_1 = new Object[2];
            v3_1[0] = v0_5;
            v3_1[1] = v4_3;
            return this.Y0(x7.u.la, v3_1);
        }
    }

    public final void pa(String[] p8, e8.z0$a p9)
    {
        if ((p8 != null) && (p8.length != 0)) {
            String[] v1_4 = new String[p8.length];
            int v2_1 = p8.length;
            int v3 = 0;
            int v4 = 0;
            while (v3 < v2_1) {
                String v5 = p8[v3];
                if (!g6.f.g(this.I(), v5)) {
                    int v6_2 = (v4 + 1);
                    v1_4[v4] = v5;
                    v4 = v6_2;
                }
                v3++;
            }
            String[] v1_1 = ((String[]) java.util.Arrays.copyOf(v1_4, v4));
            if (v1_1.length != 0) {
                this.K1 = p9;
                this.L1 = p8;
                try {
                    this.M1.a(v1_1);
                    return;
                } catch (Exception v8_2) {
                    pc.a.i(v8_2);
                    return;
                }
            } else {
                p9.a(p8, 0);
                return;
            }
        } else {
            Exception v8_3 = new String[0];
            p9.a(v8_3, 0);
            return;
        }
    }

    public final void pb(String p5)
    {
        int v0_11 = ((android.widget.EditText) this.o0.m.findViewById(x7.p.n));
        if (v0_11 == 0) {
            int v0_2;
            int v1_1 = 1;
            if (this.W0 != 1) {
                v0_2 = 80;
            } else {
                v0_2 = 48;
            }
            int v0_5 = f8.d.f().g((v0_2 | 8388613)).j(this.B8());
            if ((!this.V0) || (!this.P8())) {
                v1_1 = 0;
            }
            this.x0().q().c(x7.p.C, f8.w0, v0_5.i(v1_1).e("text", p5).a(), f8.w0.getSimpleName()).g(0).i();
            this.S8();
            return;
        } else {
            v0_11.setText(p5);
            androidx.fragment.app.l0 v5_7 = this.o0.m.findViewById(x7.p.k0);
            if (v5_7 != null) {
                v5_7.performClick();
            }
            return;
        }
    }

    public void q(String p2)
    {
        new z8.t2(this).p(p2);
        return;
    }

    public final void q8()
    {
        mark.via.common.widget.n0 v0_5 = this.n0.g2();
        v0_5.V(1);
        this.n0.g0(v0_5);
        w9.n.e().u(1);
        this.v0.j();
        g6.n.q(this.I(), x7.u.L6);
        this.m0.l1();
        this.x0.setIncognitoModeEnabled((1 ^ this.m0.W1(this.G8())));
        return;
    }

    public final void q9()
    {
        if ((android.os.SystemClock.elapsedRealtime() - this.T1) >= 300) {
            this.T1 = android.os.SystemClock.elapsedRealtime();
            if ((this.g1()) && (!this.R8())) {
                t4.b v0_3 = this.d();
                if (v0_3 != null) {
                    i6.b v2_1 = v0_3.getUrl();
                    if (i6.i0.a.s(v2_1)) {
                        if (this.m0.e1(v2_1)) {
                            if (!r9.g.a().n(v2_1)) {
                                i6.e v1_6 = this.g1;
                                this.g1 = v0_3.getId();
                                i6.b v2_3 = i6.b.a;
                                v2_3.g(this.H8(), this.f1);
                                this.db();
                                if (v1_6 == v0_3.getId()) {
                                    return;
                                } else {
                                    t4.b v0_7 = this.L0.n(v1_6);
                                    if (v0_7 == null) {
                                        return;
                                    } else {
                                        v2_3.a(new i6.e(v0_7.p()));
                                        return;
                                    }
                                }
                            } else {
                                g6.n.q(this.I(), x7.u.O1);
                                return;
                            }
                        } else {
                            g6.n.q(this.I(), x7.u.N1);
                            return;
                        }
                    }
                }
                g6.n.q(this.I(), x7.u.M1);
            }
        }
        return;
    }

    public final void qa(String p9, String[] p10, e8.z0$a p11)
    {
        if ((p10 != null) && (p10.length != 0)) {
            StringBuilder v5_1 = new StringBuilder();
            int v1_1 = p10.length;
            int v2_2 = 0;
            while (v2_2 < v1_1) {
                String v3_1 = p10[v2_2];
                if (v5_1.length() > 0) {
                    v5_1.append("\n");
                }
                Object[] v6_2 = new Object[1];
                v6_2[0] = this.p9(v3_1);
                v5_1.append(this.Y0(x7.u.w7, v6_2));
                v2_2++;
            }
            this.j0().runOnUiThread(new c8.p0(this, p9, v5_1, p11, p10));
            return;
        } else {
            String[] v9_1 = new String[0];
            p11.a(v9_1, 0);
            return;
        }
    }

    public final void qb(int p4, int p5)
    {
        t4.b v0 = this.C8();
        if ((v0 != null) && ((v0.getWidth() > 0) && (v0.getHeight() > 0))) {
            android.content.Context v1_0 = new int[2];
            v0.getLocationOnScreen(v1_0);
            s4.b.g(v0, c8.za.a(this.y0(), (((float) (p4 - v1_0[0])) / ((float) v0.getWidth())), (((float) (p5 - v1_0[1])) / ((float) v0.getHeight()))));
        }
        return;
    }

    public void r(t4.b p4)
    {
        if (((p4 != null) || (this.p0.getChildCount() != 0)) && ((this.p0.getChildCount() != 1) || (this.p0.getChildAt(0) != p4))) {
            this.p0.removeAllViews();
            if (p4 != null) {
                p4.setFocusable(1);
                this.p0.addView(p4);
                return;
            }
        }
        return;
    }

    public void r1(int p5, int p6, android.content.Intent p7)
    {
        int v1 = 0;
        android.net.Uri[] v2 = 0;
        if (p5 != 911) {
            if (p5 == 111) {
                if (android.os.Build$VERSION.SDK_INT < 21) {
                    if (this.E0 != null) {
                        if ((p7 != null) && (p6 == -1)) {
                            android.webkit.ValueCallback v5_2 = p7.getData();
                        } else {
                            v5_2 = 0;
                        }
                        this.E0.onReceiveValue(v5_2);
                        this.E0 = 0;
                    }
                } else {
                    if (this.F0 != null) {
                        if (p6 == -1) {
                            try {
                                android.webkit.ValueCallback v5_4 = p7.getClipData();
                            } catch (android.webkit.ValueCallback v5_5) {
                                pc.a.b(v5_5);
                            }
                            if (v5_4 != null) {
                                v2 = new android.net.Uri[v5_4.getItemCount()];
                                while (v1 < v5_4.getItemCount()) {
                                    v2[v1] = v5_4.getItemAt(v1).getUri();
                                    v1++;
                                }
                            }
                        }
                        if ((v2 == null) || (v2.length == 0)) {
                            v2 = c8.k.a(p6, p7);
                        }
                        this.F0.onReceiveValue(v2);
                        return;
                    }
                }
            }
        } else {
            if ((p7 != null) && (p7.hasExtra("data"))) {
                android.webkit.ValueCallback v5_9 = p7.getStringExtra("data");
                w5.k.l(this.I()).d0(x7.u.v3).J(v5_9).u(0).V(17039370, new c8.g5(this, v5_9)).N(17039360, 0).R(17039361, new c8.h5(this, v5_9)).f0();
                return;
            }
        }
        return;
    }

    public final void r8()
    {
        android.content.Context v0_0 = this.H8();
        if (v0_0 != null) {
            if (!r9.g.a().c(v0_0.getUrl())) {
                i6.c0.a.j(v0_0, new c8.w4(this));
                return;
            } else {
                g6.n.q(this.I(), x7.u.O1);
                return;
            }
        } else {
            return;
        }
    }

    public final void r9()
    {
        this.wb();
        int v0_1 = this.L0.i();
        if (v0_1 != 0) {
            this.v0.setTabSize(v0_1);
            return;
        } else {
            return;
        }
    }

    public final void ra()
    {
        w5.k.l(this.I()).d0(x7.u.If).I(x7.u.N7).V(x7.u.l, new c8.i4(this)).N(x7.u.J, 0).f0();
        return;
    }

    public final void rb()
    {
        int v0_2 = this.L0.d();
        if ((!this.R8()) && (v0_2 != 0)) {
            if (!v0_2.q()) {
                this.A(100);
            } else {
                v0_2.s();
                return;
            }
        }
        return;
    }

    public void s(android.webkit.ValueCallback p2)
    {
        this.E0 = p2;
        android.content.Intent v2_3 = new android.content.Intent("android.intent.action.GET_CONTENT");
        v2_3.addCategory("android.intent.category.OPENABLE");
        v2_3.setType("*/*");
        this.P2(android.content.Intent.createChooser(v2_3, this.X0(x7.u.Pf)), 111);
        return;
    }

    public final void s8()
    {
        this.m0.N1();
        if ((!this.n0.v().k()) || ((!this.n0.g2().s()) || ((u9.d.o(this.I(), this.G8())) && (this.i() <= 1)))) {
            this.j0().finish();
            return;
        } else {
            w5.k.l(this.I()).d0(x7.u.N4).I(x7.u.O4).w(x7.u.L3, 0).V(x7.u.N4, new c8.f5(this)).N(17039360, 0).f0();
            return;
        }
    }

    public final void s9(i8.k p2)
    {
        this.U7(new c8.o5(new ref.WeakReference(p2)));
        return;
    }

    public final void sa(String p10, int p11)
    {
        if ((p10 != null) && (!p10.isEmpty())) {
            long v0_8 = i6.i0.a;
            if (!v0_8.k(p10)) {
                if (!v0_8.l(p10)) {
                    long v0_3 = ((String) this.S0.get(p10));
                    if ((v0_3 != 0) && (!v0_3.isEmpty())) {
                        String v1_2 = new java.io.File(v0_3);
                        if ((v1_2.isFile()) && (v1_2.lastModified() >= (System.currentTimeMillis() - 86400000))) {
                            this.L8(p10, v1_2, p11);
                            return;
                        }
                    }
                    long v0_7 = this.C8();
                    if (v0_7 != 0) {
                        if (!this.m0.e1(v0_7.getUrl())) {
                            this.h8(p10, 0, p11);
                        } else {
                            this.R0.a(p10, p11);
                            s4.b.g(v0_7, "javascript:(function(){var a=new XMLHttpRequest;a.open(\"GET\",\"__URL__\",!0);a.responseType=\"blob\";a.onload=function(){if(200===a.status){var b=new FileReader;b.onloadend=function(){window.via.download(\"__SECRET__\",\"__URL__\",b.result)};b.readAsDataURL(a.response)}else window.via.download(\"__SECRET__\",\"__URL__\",\"\")};a.onerror=function(){window.via.download(\"__SECRET__\",\"__URL__\",\"\")};a.send()})();".replace("__URL__", p10).replace("__SECRET__", this.f1));
                        }
                        g6.n.q(this.I(), x7.u.Ch);
                    }
                } else {
                    this.L8(p10, new java.io.File(android.net.Uri.parse(p10).getPath()), p11);
                    return;
                }
            } else {
                this.h8(0, p10, p11);
                return;
            }
        }
        return;
    }

    public final void sb(boolean p6)
    {
        int v2_2;
        w9.n.e().p(1);
        android.content.res.Configuration v0_12 = this.j0();
        if (p6 == null) {
            v2_2 = x7.v.b;
        } else {
            v2_2 = x7.v.a;
        }
        v0_12.setTheme(v2_2);
        this.R8();
        this.U0 = g6.y.H(this.U0);
        if (!u9.d.m(this.I(), this.G8())) {
            this.yb(g6.e.a(this.I(), x7.k.b), 0);
        } else {
            this.yb(0, 0);
        }
        android.content.res.Configuration v0_11;
        this.Eb(this.W0);
        if (!g6.y.C(this.n0.c0())) {
            v0_11 = 64;
        } else {
            v0_11 = 128;
        }
        if (p6 == null) {
            v0_11 = 0;
        }
        this.o0.setWindowFilterColor(android.graphics.Color.argb(Math.max(v0_11, ((int) ((((float) this.n0.E2().b()) / 1120403456) * 1132396544))), 0, 0, 0));
        this.m0.w1();
        this.m0.n1(0, 1);
        android.content.res.Configuration v0_17 = this.G1;
        if (v0_17 != null) {
            String v3_9 = this.i1;
            if (v3_9 != null) {
                this.ib(v0_17.f(v3_9), 1);
            }
        }
        if (this.C0 != null) {
            this.Qa(0);
            this.Qa(1);
        }
        this.o0.setNightModeEnabled(p6);
        if (!this.n0.Z()) {
            android.widget.FrameLayout v6_3 = this.p0;
            v6_3.dispatchConfigurationChanged(v6_3.getResources().getConfiguration());
        }
        return;
    }

    public void t(String p2, String p3, android.net.http.SslCertificate p4)
    {
        if (p3 != null) {
            mark.via.common.widget.n0 v4_5 = p3.length();
            p3 = z8.w2.f(p3);
            if (v4_5 != p3.length()) {
                p2 = z8.b0.E(p3);
            }
        }
        if (!u9.d.m(this.I(), p3)) {
            if ((this.n0.d1() == 0) && (!this.b1)) {
                if (android.text.TextUtils.isEmpty(p2)) {
                    p2 = this.X0(x7.u.Qg);
                }
                this.x0.setTitle(z8.b0.G(p2, p3));
            }
            return;
        } else {
            this.x0.setTitle(p2);
            return;
        }
    }

    public void t1(android.content.Context p2)
    {
        super.t1(p2);
        this.j0().h().h(this, this.o1);
        this.x0().l(new c8.h4(this));
        this.I().registerComponentCallbacks(this);
        return;
    }

    public final void t8(v9.f p7)
    {
        wa.a v0_0 = this.C8();
        if (v0_0 != null) {
            this.x1.a(this.X0(x7.u.u5), this.X0(x7.u.Og), new c8.s6$c(this, this.I(), p7, new ref.WeakReference(v0_0)));
            return;
        } else {
            return;
        }
    }

    public final void t9(mark.via.download.m p2)
    {
        p2.q3(new c8.b5(this));
        return;
    }

    public final void ta()
    {
        android.widget.RelativeLayout$LayoutParams v0_1 = new android.widget.RelativeLayout$LayoutParams(this.R0().getDimensionPixelSize(x7.n.h), this.R0().getDimensionPixelSize(x7.n.h));
        android.widget.ImageView v1_1 = this.R0().getDimensionPixelSize(x7.n.g);
        int v2_3 = this.n0.S();
        if ((v2_3 & 3) != 3) {
            if ((v2_3 & 5) != 5) {
                v0_1.addRule(14, -1);
                v0_1.addRule(12, -1);
            } else {
                v0_1.addRule(11, -1);
                if ((v2_3 & 80) != 80) {
                    v0_1.addRule(15, -1);
                }
            }
        } else {
            v0_1.addRule(9, -1);
        }
        v0_1.topMargin = v1_1;
        v0_1.bottomMargin = v1_1;
        v0_1.leftMargin = v1_1;
        v0_1.rightMargin = v1_1;
        this.t0.setLayoutParams(v0_1);
        return;
    }

    public final void tb()
    {
        boolean v0_1 = this.n0.d();
        android.view.animation.AccelerateInterpolator v2_6 = new Object[1];
        v2_6[0] = Boolean.valueOf(v0_1);
        pc.a.a("current night mode: %s", v2_6);
        if (!this.o1()) {
            this.sb(v0_1);
            return;
        } else {
            android.view.animation.AccelerateInterpolator v2_1 = new float[2];
            v2_1 = {1045220557, 1065353216};
            android.animation.ObjectAnimator v1_2 = android.animation.ObjectAnimator.ofFloat(this.z0, "alpha", v2_1);
            v1_2.setDuration(300);
            v1_2.setInterpolator(new android.view.animation.AccelerateInterpolator());
            this.sb(v0_1);
            v1_2.start();
            return;
        }
    }

    public boolean u(String p8)
    {
        if ((p8 != null) && ((p8.length() >= 6) && ("via://".equalsIgnoreCase(p8.substring(0, 6))))) {
            String[] v0_10 = new StringBuilder();
            v0_10.append("v://");
            v0_10.append(p8.substring(6));
            p8 = v0_10.toString();
        }
        c8.ua v1_4 = 11;
        if (!z8.w2.t(p8)) {
            if (!z8.w2.u(p8)) {
                if (!z8.w2.z(p8)) {
                    if ((!p8.startsWith("thunder://")) && ((!p8.startsWith("qqdl://")) && (!p8.startsWith("flashget://")))) {
                        if ((!p8.startsWith("baidubox://")) && ((!p8.startsWith("baiduboxapp://")) && (!p8.startsWith("baiduboxlite://")))) {
                            if ((android.os.Build$VERSION.SDK_INT < 21) || ((!p8.startsWith("file://")) || (!"pdf".equals(i6.i0.a.c(p8))))) {
                                return this.J9(p8);
                            } else {
                                g6.i.h(this, za.g, v5.a.b().e("pdfPath", p8).a());
                                return 1;
                            }
                        } else {
                            return 1;
                        }
                    } else {
                        String v8_35 = z8.b0.D(p8);
                        if (!android.text.TextUtils.isEmpty(v8_35)) {
                            this.Xa(new mark.via.download.e$b().j(v8_35).k(this.n0.H1()).c("attachment").d(-1).b());
                        }
                        return 1;
                    }
                } else {
                    if (!z8.w2.o(p8)) {
                        if (!z8.w2.r(p8)) {
                            if (!z8.w2.w(p8)) {
                                if (!z8.w2.y(p8)) {
                                    if (!z8.w2.x(p8)) {
                                        if (!z8.w2.s(p8)) {
                                            if (!z8.w2.q(p8)) {
                                                if (!z8.w2.v(p8)) {
                                                    if (!z8.w2.p(p8)) {
                                                        if (!z8.w2.B(p8)) {
                                                            if (!z8.w2.A(p8)) {
                                                                String v8_8 = ((Integer) z8.b0.Q(p8).a).intValue();
                                                                if (v8_8 == 1) {
                                                                    this.m0.V0();
                                                                } else {
                                                                    this.m0.s1(v8_8, 0);
                                                                }
                                                                return 1;
                                                            } else {
                                                                String v8_10 = z8.w2.j(p8);
                                                                if (v8_10 != null) {
                                                                    String[] v0_17 = v8_10.a;
                                                                    if ((v0_17 != null) && (((String[]) v0_17).length > 0)) {
                                                                        this.m0.F1(((String[]) v0_17), ((String) v8_10.b));
                                                                    }
                                                                }
                                                                return 1;
                                                            }
                                                        } else {
                                                            this.pb(z8.w2.m(p8));
                                                            return 1;
                                                        }
                                                    } else {
                                                        String v8_15 = z8.w2.h(p8);
                                                        String[] v0_20 = u9.d.d(this.I(), this.G8());
                                                        if ((v0_20 != 2) && ((v0_20 != 11) && (v0_20 != 5))) {
                                                            if (v0_20 != 1) {
                                                                this.O9(((String) v8_15.a));
                                                            } else {
                                                                this.L9(((String) v8_15.a), ((String) v8_15.b));
                                                            }
                                                        } else {
                                                            if (g6.p.f(((String) v8_15.a))) {
                                                                v1_4 = 2;
                                                            }
                                                            this.m0.s1(v1_4, ((String) v8_15.a));
                                                        }
                                                        return 1;
                                                    }
                                                } else {
                                                    this.V9();
                                                    return 1;
                                                }
                                            } else {
                                                this.Q9();
                                                return 1;
                                            }
                                        } else {
                                            if (u9.d.d(this.I(), this.G8()) != 5) {
                                                this.T9();
                                            } else {
                                                this.m0.s1(3, 0);
                                            }
                                            return 1;
                                        }
                                    } else {
                                        String v8_25 = z8.w2.k(p8);
                                        if ((v8_25 != null) && (!v8_25.isEmpty())) {
                                            this.Fa(v8_25, 1);
                                        } else {
                                            this.ba();
                                        }
                                        return 1;
                                    }
                                } else {
                                    g6.i.g(this, lb.k);
                                    return 1;
                                }
                            } else {
                                this.U9();
                                return 1;
                            }
                        } else {
                            String v8_27 = z8.w2.f(p8);
                            if ((!android.text.TextUtils.isEmpty(v8_27)) && (!i6.i0.a.p(v8_27))) {
                                this.z0.post(new c8.d5(this, v8_27));
                            }
                            return 1;
                        }
                    } else {
                        String v8_28 = z8.w2.b(p8);
                        String[] v0_32 = i6.i0.a;
                        if (v0_32.s(v8_28)) {
                            this.m0.n0(v0_32.f(v8_28));
                            this.z0.post(new c8.c5(this, v8_28));
                        }
                        return 1;
                    }
                }
            } else {
                this.m0.s1(3, 0);
                return 1;
            }
        } else {
            String v8_30 = p8.substring(9);
            String[] v0_37 = u9.d.d(this.I(), this.G8());
            if ((v0_37 != 2) && (v0_37 != 11)) {
                this.N9(android.net.Uri.decode(v8_30));
            } else {
                if (v8_30.isEmpty()) {
                    v1_4 = 2;
                }
                this.m0.s1(v1_4, v8_30);
            }
            return 1;
        }
    }

    public final void u8(boolean p2)
    {
        t4.b v0 = this.C8();
        if (v0 != null) {
            v0.findNext(p2);
            return;
        } else {
            return;
        }
    }

    public final void u9(qa.e1 p3)
    {
        this.o0.setBlurEnabled(1);
        this.o0.setDescendantFocusability(393216);
        this.o0.clearFocus();
        p3.i0().a(new c8.v4(this));
        return;
    }

    public final void ua()
    {
        if ((android.os.Build$VERSION.SDK_INT >= 21) && (this.s1 == this.U0)) {
            z8.l3.k(this.I(), this.t1);
            return;
        } else {
            return;
        }
    }

    public final void ub()
    {
        int v0_3;
        boolean vtmp1 = this.n0.K1();
        int v2_8 = 1;
        if ((this.n1) || ((!this.k1) && ((!vtmp1) && (this.n0.l2() <= 0)))) {
            v0_3 = 0;
        } else {
            v0_3 = 1;
        }
        if (this.X0 != v0_3) {
            float v1_1 = 0;
            if (v0_3 == 0) {
                this.y0.setVisibility(0);
                this.w0.setVisibility(0);
                this.p0.setTranslationY(0);
                float v1_3 = new android.widget.RelativeLayout$LayoutParams(-1, -1);
                v1_3.addRule(3, this.y0.getId());
                v1_3.addRule(2, this.w0.getId());
                this.p0.setLayoutParams(v1_3);
            } else {
                if (this.n0.v0() != 2) {
                    v2_8 = 0;
                }
                this.p0.setLayoutParams(new android.widget.RelativeLayout$LayoutParams(-1, -1));
                if (this.y0.getVisibility() == 0) {
                    if (v2_8 == 0) {
                        v1_1 = ((float) this.y0.getHeight());
                    }
                    this.p0.setTranslationY(v1_1);
                }
            }
            this.X0 = v0_3;
            return;
        } else {
            return;
        }
    }

    public final void v8(String p5)
    {
        t4.b v0 = this.C8();
        if (v0 != null) {
            if (p5 == null) {
                p5 = "";
            }
            if (!p5.isEmpty()) {
                v0.setFindListener(new c8.q6(this));
            } else {
                v0.setFindListener(0);
                this.La(-1, 0, 1);
            }
            v0.findAllAsync(p5);
            return;
        } else {
            return;
        }
    }

    public final void v9(f8.h p2)
    {
        p2.e3(new c8.s6$j(this));
        return;
    }

    public boolean va(android.webkit.WebView p9, int p10)
    {
        if ((p9) && (p9.getSettings().getJavaScriptEnabled())) {
            i6.c0 v1_3 = p9.getUrl();
            if ((v1_3 != null) && (!v1_3.isEmpty())) {
                String v2_14 = i6.i0.a;
                if (!v2_14.l(v1_3)) {
                    android.content.Context v3_9 = v2_14.f(v1_3);
                    if (!android.text.TextUtils.isEmpty(v3_9)) {
                        if (p10 != 4) {
                            if (p10 != 1) {
                                if ((!this.n0.g2().D()) || ((r9.g.a().e(v1_3)) || (!this.Q0.b(p9, v1_3, 2)))) {
                                    n5.b v10_25 = 0;
                                } else {
                                    v10_25 = 1;
                                }
                                i6.c0.a.q(new i6.e(p9), new c8.n5(this));
                                if (v10_25 > null) {
                                    return 1;
                                }
                            } else {
                                n5.b v10_27 = new StringBuilder();
                                if ((this.m0.b1(v1_3)) && (v2_14.s(v1_3))) {
                                    v10_27.append(m8.b.b(v3_9));
                                    s4.b.g(p9, this.m0.I0(v3_9));
                                }
                                if (("music.163.com".equals(v3_9)) || (("taobao.com".equals(v3_9)) || (!this.m0.d1(v1_3)))) {
                                    if (this.n0.g2().n()) {
                                        v10_27.append(c8.rc.b(this.y0()));
                                    }
                                } else {
                                    v10_27.append(c8.rc.a(this.y0(), 1280));
                                }
                                String v2_57 = this.m0.J0(v1_3);
                                if (v2_57 == 2) {
                                    v10_27.append(c8.ya.b(this.y0()));
                                } else {
                                    if (v2_57 == 3) {
                                        v10_27.append(c8.ya.a(this.y0()));
                                    } else {
                                        v10_27.append(c8.ya.c(this.y0()));
                                    }
                                }
                                if ((this.n0.x1()) && (this.n0.d())) {
                                    v10_27.append("(function(){if(!document.getElementById(\'via_inject_css_night\')){var css=document.createElement(\'style\');css.id=\'via_inject_css_night\';css.type=\'text/css\';css.rel=\"stylesheet\";var textNode=document.createTextNode(\'html{background-color:#000!important}*{color:#999!important;box-shadow:none!important;background-color:transparent!important;border-color:#444!important;border-top-color:#444!important;border-bottom-color:#444!important;border-left-color:#444!important;border-right-color:#444!important}body{background-color:transparent!important}:after,:before{background-color:transparent!important;border-color:#444!important}a,a *{color:#409B9B!important;text-decoration:none!important}.link:hover,.link:hover *,[role=button]:hover *,[role=link]:hover,[role=link]:hover *,[role=menuitem]:hover,[role=menuitem]:hover *,a:hover,a:hover *,a:visited:hover,a:visited:hover *,div[onclick]:hover,span[onclick]:hover{color:#F0F0F0!important}a:visited,a:visited *{color:#607069!important}.selected,.selected *,[href=\"#\"],a.active,a.active *,a.highlight,a.highlight *{color:#DDD!important;font-weight:700!important}[class*=header],[class*=header] td,[class*=headline],[id*=header],[id*=headline],h1,h1 *,h2,h2 *,h3,h3 *,h4,h5,h6,strong{color:#DDD!important}[class*=alert],[class*=error],code,div[onclick],span[onclick]{color:#900!important}::-moz-selection{background-color:#377!important;color:#000!important}::selection{background-color:#377!important;color:#000!important}:focus{outline:0!important}div[role=navigation],div[style=\"display: block;\"]{background-color:rgba(0,0,0,.5)!important}table{background-color:rgba(40,30,30,.6)!important;border-radius:6px!important}table>tbody>tr:nth-child(even),table>tbody>tr>td:nth-child(even){background-color:rgba(0,0,0,.2)!important}#ghostery-purple-bubble,#translator-popup,.hovercard,.menu,.tooltip,.vbmenu_popup,[class*=dropdown],[class*=nav] ul,[class*=popup],[class=title],[id*=Menu],[id*=menu],[id*=nav] ul,a[id*=ghosteryfirefox],a[onclick][style*=display],div[role=dialog],div[role=menu],div[style*=\"position:\"][style*=\"left:\"][style*=visible],div[style*=\"z-index:\"][style*=\"left:\"][style*=visible],div[style*=\"-moz-user-select\"],embed,iframe,label [onclick],nav,nav ul,span[class*=script] div,ul[class*=menu],ul[style*=\"display:\"],ul[style*=\"visibility:\"] ul{background-color:rgba(5,5,5,.9)!important;border-radius:5px;box-shadow:1px 1px 5px #000!important}#footer,#header,footer,header{background-color:rgba(19,19,19,.9)!important;box-shadow:0 0 5px #000!important}body>#dialog,body>.xenOverlay{background-color:rgba(19,19,19,.96)!important;background-clip:padding-box!important;box-shadow:0 0 15px #000,inset 0 0 0 1px rgba(200,200,200,.5),inset 0 0 5px #111!important}[id*=lightbox],[id*=overlay],blockquote{background-color:rgba(35,35,35,.9)!important;border-radius:5px}.Message code,dl,pre{background-color:rgba(5,5,5,.5)!important}.install[onclick],[role=button],a.BigButton,a.TabLink,a.button,a.submit,button,input,select{-moz-appearance:none!important;-webkit-appearance:none!important;transition:border-color .3s!important;background-color:#060606!important;color:#BBB!important;box-shadow:0 0 2px rgba(0,0,0,.9)!important}a[class*=button]:not(:empty),a[href=\"javascript:;\"],a[id*=Button]:not(:empty),a[id*=button]:not(:empty),div[class*=button][onclick]{transition:border-color .3s!important;background-color:#060606!important;color:#BBB!important;border-color:#333!important;box-shadow:0 0 2px rgba(0,0,0,.9)!important}a[class*=button]:not(:empty):hover,a[href=\"javascript:;\"]:hover,a[id*=Button]:not(:empty):hover,a[id*=button]:hover,div[class*=button][onclick]:hover{background-color:#151515!important;color:#FFF!important}a.button *,a.submit *,button *,input *,select *{color:#BBB!important}[role=button]:hover,a.BigButton:hover,a.TabLink:hover,a.button:hover,a.submit:hover,button:hover,input:hover,input[type=button]:hover,select:hover{border-top-color:#555!important;border-bottom-color:#555!important;border-left-color:#555!important;border-right-color:#555!important}input:focus,select:focus{box-shadow:0 0 5px #077!important}input :hover *{color:#F0F0F0!important}button[disabled],button[disabled]:focus,button[disabled]:hover,input[disabled],input[disabled]:focus,input[disabled]:hover,select[disabled],select[disabled]:focus,select[disabled]:hover{opacity:.5!important;border-color:#333!important}input[type=checkbox]{border-radius:1px!important}input[type=radio],input[type=radio]:focus{border-radius:100%!important}input[type=checkbox],input[type=radio]{min-width:12px;min-height:12px}input[type=checkbox]:checked,input[type=radio]:checked{border-color:#077!important;box-shadow:0 0 5px #077!important}select{padding-right:15px!important;background-color:#060606!important;transition:border-color .3s,background-position .3s!important}.Active .TabLink,a.BigButton:active,a.TabLink:active,a.button:active,a.submit:active,a[class*=button]:not(:empty):active,button:active,input[type=button]:active,input[type=submit]:active{background-color:#292929!important;color:#FFF!important}textarea{-moz-appearance:none!important;-webkit-appearance:none!important;background-color:rgba(0,0,0,.3)!important;border-radius:3px!important;box-shadow:inset 0 0 8px #000!important;transition:border-color,background,.3s!important}textarea,textarea *{color:#C8C8C8!important}textarea:focus:hover,textarea:hover{border-color:#333!important}textarea:focus{background-color:rgba(0,0,0,.5)!important;border-color:#222!important}textarea:focus,textarea:focus>*{box-shadow:none!important}optgroup,option{-moz-appearance:none!important;-webkit-appearance:none!important;background-color:0 0!important;color:#666!important}optgroup{background-color:#222!important;color:#DDD!important}option:checked,option:focus,option:not([disabled]):hover{background-color:linear-gradient(#333,#292929)!important;color:#DDD!important}img{opacity:.7!important;transition:opacity .2s}#mpiv-popup,a:hover img,img:hover{opacity:1!important}.read-whole-mask .exp-mask,.se-head-tabcover,.wgt-exp-content .exp-img-mask{background-image:none!important}.s_card{background:0 0!important}\');css.appendChild(textNode);var o=document.getElementsByTagName(\"head\");if(o.length>0&&o[0].appendChild(css)){}};})();");
                                    p9.postDelayed(new c8.m5(new ref.WeakReference(p9)), 300);
                                }
                                v10_27.append(c8.za.b(this.y0(), this.f1));
                                v10_27.append(c8.a.b(this.y0()));
                                v10_27.append("(function(){var n=\"via-fake-print\";if(!window[n])try{window[n]=!0;var i=window.print;\"function\"==typeof i&&/\\{\\s*\\[native code\\]\\s*\\}/.test(Function.prototype.toString.call(i))&&(window.print=function(){window.via.cmd(516)})}catch(n){}})();");
                                v10_27.append("(function(){if(!window[\"via-fake-notification\"])try{window[\"via-fake-notification\"]=!0,window.Notification=function(a,b){},window.Notification.permission=\"denied\",window.Notification.requestPermission=function(a){\"function\"===typeof a&&a(\"denied\");return Promise.resolve(\"denied\")}}catch(a){}})();");
                                if (!this.n0.g2().E()) {
                                    v10_27.append("(function(){if(!window[\"via-fake-vibrate\"])try{window[\"via-fake-vibrate\"]=!0;var b=function(a){return!0}.bind(window);window.navigator.vibrate=b}catch(a){}})();");
                                }
                                String v2_25 = android.os.Build$VERSION.SDK_INT;
                                if ((v2_25 >= 21) && (v2_25 < 28)) {
                                    v10_27.append(i6.q.a.a());
                                }
                                v10_27.append(c8.mb.b(this.I(), this.f1));
                                String v2_31 = this.n0.Y0();
                                if ((v2_31 != null) && (!v2_31.isEmpty())) {
                                    android.content.Context v3_10 = this.I();
                                    StringBuilder v4_1 = new StringBuilder();
                                    v4_1.append("/");
                                    v4_1.append(v2_31);
                                    v10_27.append(c8.ab.a(v3_10, v4_1.toString()));
                                }
                                if (v10_27.length() > 0) {
                                    s4.b.g(p9, v10_27.toString());
                                }
                                if ((this.n0.g2().D()) && ((!r9.g.a().e(v1_3)) && (!this.Q0.b(p9, v1_3, 1)))) {
                                    return 0;
                                } else {
                                    return 1;
                                }
                            }
                        } else {
                            i6.b.a.d(new i6.e(p9), this.f1);
                            this.S7();
                            if (this.n0.g2().D()) {
                                if ((this.n0.g2().D()) && ((!r9.g.a().e(v1_3)) && (!this.Q0.b(p9, v1_3, 4)))) {
                                    return 0;
                                } else {
                                    return 1;
                                }
                            } else {
                                return 1;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public final void vb()
    {
        if (this.C8() != null) {
            this.U7(new c8.x3(this));
        }
        return;
    }

    public void w1(android.os.Bundle p3)
    {
        super.w1(p3);
        pa.r.b(this.I()).h().a(this).a(this);
        int v0_0 = 0;
        if ((this.w0() != null) && (this.w0().getBoolean("CUSTOM_TAB", 0))) {
            v0_0 = 1;
        }
        this.n1 = v0_0;
        this.x0().m(new c8.f4(this));
        return;
    }

    public void w9()
    {
        if (this.k9()) {
            if (!this.R8()) {
                if ((this.s0 == null) && (this.r0 == null)) {
                    if (!this.k1) {
                        if (!this.n1) {
                            if (u9.d.o(this.I(), this.G8())) {
                                if (this.L0.i() > 1) {
                                    this.m0.B0();
                                    return;
                                } else {
                                    if ((android.os.SystemClock.elapsedRealtime() - this.T0) <= 1500) {
                                        this.s8();
                                        return;
                                    } else {
                                        g6.n.s(this.I(), this.R0().getString(x7.u.T7));
                                        this.T0 = android.os.SystemClock.elapsedRealtime();
                                        return;
                                    }
                                }
                            } else {
                                this.I8();
                                return;
                            }
                        } else {
                            long v0_13 = this.L0.d();
                            if ((v0_13 == 0) || (!v0_13.j())) {
                                if (this.i() <= 1) {
                                    this.j0().finish();
                                    return;
                                } else {
                                    this.m0.B0();
                                    return;
                                }
                            } else {
                                v0_13.g();
                                return;
                            }
                        }
                    } else {
                        if ((android.os.SystemClock.elapsedRealtime() - this.T0) <= 1500) {
                            this.T0 = 0;
                            this.Na(0);
                            return;
                        } else {
                            g6.n.q(this.I(), x7.u.M5);
                            this.T0 = android.os.SystemClock.elapsedRealtime();
                            return;
                        }
                    }
                } else {
                    this.T();
                    return;
                }
            } else {
                return;
            }
        } else {
            this.L0().e1();
            return;
        }
    }

    public final void wa(String p2, String p3, String p4)
    {
        if ((!g6.p.f(p2)) && ((!g6.p.f(p3)) || (!g6.p.f(p4)))) {
            this.y1.put(p2, okhttp3.l.a(p3, p4));
        }
        return;
    }

    public final void wb()
    {
        this.xb(this.G8());
        return;
    }

    public final void x8(android.view.View p3, x.k0 p4, boolean p5)
    {
        if ((z8.l3.a) && ((p4 != 0) && (p3 != null))) {
            if (p5 != 0) {
                int v4_1 = p4.g(((x.k0$m.f() | x.k0$m.b()) | x.k0$m.a()));
                p3.setPadding(v4_1.a, v4_1.b, v4_1.c, v4_1.d);
            } else {
                p3.setPadding(0, 0, 0, 0);
                return;
            }
        }
        return;
    }

    public final boolean x9()
    {
        if (!this.n0.r2()) {
            return 0;
        } else {
            this.fa(18);
            return 1;
        }
    }

    public final void xa(String p3)
    {
        boolean v0_0 = android.os.Build$VERSION.SDK_INT;
        if ((v0_0 >= 29) || ((v0_0 < 23) || (g6.f.g(this.I(), "android.permission.WRITE_EXTERNAL_STORAGE")))) {
            this.sa(p3, 4);
            return;
        } else {
            this.O1 = p3;
            try {
                this.Q1.a("android.permission.WRITE_EXTERNAL_STORAGE");
                return;
            } catch (Exception v3_2) {
                pc.a.i(v3_2);
                return;
            }
        }
    }

    public final void xb(String p3)
    {
        if ((!z8.n3.g(this.I())) || (!this.Ta(p3))) {
            int v3_1 = 0;
        } else {
            v3_1 = 1;
        }
        this.o1.j((v3_1 ^ 1));
        return;
    }

    public d8.e y()
    {
        if (this.K0 == null) {
            this.K0 = new c8.s6$u(this, 0);
        }
        return this.K0;
    }

    public final void y8(int p6, int p7)
    {
        t4.b v0 = this.C8();
        if (v0 != null) {
            long v1_7 = c8.za.c(this.y0());
            if (v1_7 != 0) {
                String v2_8 = new int[2];
                v0.getLocationOnScreen(v2_8);
                c8.d3 v6_1 = (p6 - v2_8[0]);
                int v7_1 = (p7 - v2_8[1]);
                s4.b.g(v0, v1_7.replace("__X__", String.valueOf(v6_1)).replace("__Y__", String.valueOf(v7_1)).replace("__WW__", String.valueOf(v0.getWidth())).replace("__WH__", String.valueOf(v0.getHeight())));
                this.N0.setIsLongpressEnabled(0);
                g6.y.Y(v0, v6_1, v7_1);
                v0.postDelayed(new c8.d3(this), 1000);
            }
        }
        return;
    }

    public final boolean y9()
    {
        if (!this.n0.r2()) {
            return 0;
        } else {
            this.fa(17);
            return 1;
        }
    }

    public final w.d ya(java.io.File p8, String p9)
    {
        Exception v0 = 0;
        if ((p8 != null) && (p8.isFile())) {
            int v1_3 = z8.c1.r(p8.getPath());
            w.d v2_1 = l5.c.c(v1_3, "image/*");
            Throwable v9_4 = l5.b.b(p9, 0, v2_1);
            android.net.Uri v3_2 = v9_4.lastIndexOf(46);
            if ((v1_3 != 0) && ((v3_2 > null) && (v3_2 > ((v9_4.length() - v1_3.length()) - 3)))) {
                android.net.Uri v3_3 = (v3_2 + 1);
                if (!v1_3.equals(v9_4.substring(v3_3))) {
                    android.content.Context v4_8 = new StringBuilder();
                    v4_8.append(v9_4.substring(0, v3_3));
                    v4_8.append(v1_3);
                    v9_4 = v4_8.toString();
                }
            }
            try {
                android.net.Uri v3_0;
                Throwable v9_7;
                if (android.os.Build$VERSION.SDK_INT < 29) {
                    android.net.Uri v3_6 = new java.io.File(android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_PICTURES), "Via");
                    android.content.Context v4_13 = new java.io.File(v3_6, z8.c1.K(v3_6.getAbsolutePath(), v9_4));
                    v3_0 = new java.io.FileOutputStream(v4_13);
                    try {
                        v9_7 = android.net.Uri.fromFile(v4_13);
                    } catch (Throwable v8_4) {
                        Throwable v9_1 = 0;
                        w.d v2_0 = 0;
                        try {
                            pc.a.i(v8_4);
                        } catch (Throwable v8_3) {
                            v0 = v9_1;
                        }
                        if (android.os.Build$VERSION.SDK_INT >= 29) {
                            if (v2_0 != null) {
                                z8.b1.e(this.I(), v2_0);
                            }
                        }
                        g6.j.a(v9_1);
                        g6.j.a(v3_0);
                        return 0;
                    }
                    if (v3_0 != null) {
                        try {
                            Throwable v8_2 = z8.b1.i(this.I(), android.net.Uri.fromFile(p8));
                        } catch (Throwable v8_4) {
                            v2_0 = v9_7;
                            v9_1 = 0;
                        }
                        if (v8_2 != null) {
                            try {
                                z8.x2.a(v8_2, v3_0);
                                v3_0.flush();
                                mark.via.download.i1.f(this.I(), v9_7);
                                Throwable v9_2 = w.d.a(v9_7, v2_1);
                                g6.j.a(v8_2);
                                g6.j.a(v3_0);
                                return v9_2;
                            } catch (w.d v2_2) {
                                v9_1 = v8_2;
                                v8_4 = v2_2;
                                v2_0 = v9_2;
                            } catch (Throwable v9_3) {
                                v0 = v8_2;
                                v8_3 = v9_3;
                            }
                        } else {
                            g6.j.a(v8_2);
                            g6.j.a(v3_0);
                            return 0;
                        }
                    } else {
                        g6.j.a(0);
                        g6.j.a(v3_0);
                        return 0;
                    }
                } else {
                    android.net.Uri v3_9 = new android.content.ContentValues();
                    v3_9.put("_display_name", v9_4);
                    v3_9.put("mime_type", v2_1);
                    android.content.Context v4_16 = new StringBuilder();
                    v4_16.append(android.os.Environment.DIRECTORY_PICTURES);
                    v4_16.append("/Via");
                    v3_9.put("relative_path", v4_16.toString());
                    v9_7 = this.I().getContentResolver().insert(android.provider.MediaStore$Images$Media.EXTERNAL_CONTENT_URI, v3_9);
                    if (v9_7 != null) {
                        try {
                            v3_0 = this.I().getContentResolver().openOutputStream(v9_7);
                        } catch (Throwable v8_4) {
                            v2_0 = v9_7;
                            v9_1 = 0;
                            v3_0 = 0;
                        }
                    } else {
                        g6.j.a(0);
                        g6.j.a(0);
                        return 0;
                    }
                }
            } catch (Throwable v8_4) {
                v9_1 = 0;
                v2_0 = 0;
                v3_0 = 0;
            } catch (Throwable v8_3) {
                v3_0 = 0;
            } catch (Throwable v8_3) {
            }
            g6.j.a(v0);
            g6.j.a(v3_0);
            throw v8_3;
        } else {
            return 0;
        }
    }

    public final void yb(int p3, boolean p4)
    {
        if (this.U0 == 0) {
            p4 = 0;
        }
        if (p3 != 0) {
            if ((!this.n0.s2()) || (this.n0.d())) {
                p3 = g6.e.a(this.I(), x7.k.b);
            }
            if ((p3 != this.U0) || (this.V0)) {
                this.Q7(p3, p4, 0);
                return;
            }
        } else {
            if (!this.P8()) {
                p3 = this.n0.c0();
                if (p3 != -1) {
                    if (this.n0.d()) {
                        p3 = g6.y.G(-16777216, p3, 1056964608);
                    }
                } else {
                    p3 = g6.e.a(this.I(), x7.k.b);
                }
            }
            if ((p3 != this.U0) || (!this.V0)) {
                this.Q7(p3, p4, 1);
                return;
            }
        }
        return;
    }

    public void z(android.view.View p5, int p6, android.webkit.WebChromeClient$CustomViewCallback p7)
    {
        android.view.View v0_0 = new Object[0];
        pc.a.a("on show custom view", v0_0);
        if (p5 != null) {
            if ((this.r0 == null) || (this.G0 == null)) {
                this.H0 = android.os.SystemClock.elapsedRealtime();
                if (this.I0 > 1) {
                    this.n0.m(0);
                }
                this.J1 = this.j0().getRequestedOrientation();
                p5.setKeepScreenOn(1);
                this.r0 = p5;
                android.view.View v0_6 = new Object[1];
                v0_6[0] = p5;
                pc.a.a("custom view: %s", v0_6);
                this.G0 = p7;
                android.widget.FrameLayout v5_5 = ((android.widget.FrameLayout) this.j0().getWindow().getDecorView());
                this.s0 = ((com.tuyafeng.support.widget.v) new h6.a(new com.tuyafeng.support.widget.v(this.I()), new android.widget.FrameLayout$LayoutParams(-1, -1)).f(-16777216).l());
                int v7_7 = new android.widget.FrameLayout$LayoutParams(-1, -1);
                v5_5.addView(this.s0, v7_7);
                this.s0.addView(this.r0, 0, v7_7);
                this.s0.setTitle(this.E8());
                this.s0.postDelayed(new c8.x4(this), 150);
                this.p0.setVisibility(4);
                this.Ma(1);
                this.T8(0);
                this.wb();
                return;
            } else {
                try {
                    p7.onCustomViewHidden();
                } catch (Exception) {
                }
                return;
            }
        } else {
            return;
        }
    }

    public final String z8(String p2)
    {
        if ((p2 != null) && (!p2.isEmpty())) {
            return ((String) this.y1.get(p2));
        } else {
            return 0;
        }
    }

    public boolean z9(int p9, android.view.KeyEvent p10)
    {
        int v2_0 = 3;
        if (p9 == 3) {
            if (p10.isAltPressed()) {
                this.m0.V0();
                return 1;
            }
        } else {
            if (p9 == 30) {
                if ((p10.isCtrlPressed()) && (p10.isShiftPressed())) {
                    this.fa(7);
                    return 1;
                }
            } else {
                if (p9 == 40) {
                    if (p10.isCtrlPressed()) {
                        this.fa(4);
                        return 1;
                    }
                } else {
                    if (p9 == 46) {
                        if (p10.isCtrlPressed()) {
                            this.fa(1);
                            return 1;
                        }
                    } else {
                        if (p9 == 51) {
                            if (p10.isCtrlPressed()) {
                                this.fa(9);
                                return 1;
                            }
                        } else {
                            if (p9 == 61) {
                                if (p10.isCtrlPressed()) {
                                    int v9_32;
                                    if (!p10.isShiftPressed()) {
                                        v9_32 = 11;
                                    } else {
                                        v9_32 = 10;
                                    }
                                    this.fa(v9_32);
                                    return 1;
                                }
                            } else {
                                if (p9 != 82) {
                                    if (p9 == 84) {
                                        this.fa(4);
                                        return 1;
                                    } else {
                                        if (p9 == 111) {
                                            this.R8();
                                            return 1;
                                        } else {
                                            if (p9 == 135) {
                                                this.fa(1);
                                                return 1;
                                            } else {
                                                int v4_0 = 21;
                                                if ((p9 == 21) || (p9 == 22)) {
                                                    if (!p10.isAltPressed()) {
                                                        return 0;
                                                    } else {
                                                        int v9_34;
                                                        if (p9 != 21) {
                                                            v9_34 = 13;
                                                        } else {
                                                            v9_34 = 12;
                                                        }
                                                        this.fa(v9_34);
                                                        return 1;
                                                    }
                                                } else {
                                                    if (p9 == 24) {
                                                        if ((this.s0 != null) || ((this.r0 != null) || ((this.k1) || ((this.O8()) || (!this.y9()))))) {
                                                            return 0;
                                                        } else {
                                                            return 1;
                                                        }
                                                    } else {
                                                        if (p9 == 25) {
                                                            if ((this.s0 != null) || ((this.r0 != null) || ((this.k1) || ((this.O8()) || (!this.x9()))))) {
                                                                return 0;
                                                            } else {
                                                                return 1;
                                                            }
                                                        } else {
                                                            if (p9 == 48) {
                                                                if (!p10.isCtrlPressed()) {
                                                                    return 0;
                                                                } else {
                                                                    this.fa(5);
                                                                    this.ba();
                                                                    return 1;
                                                                }
                                                            } else {
                                                                if (p9 == 49) {
                                                                    if (p10.isCtrlPressed()) {
                                                                        this.fa(20);
                                                                        return 1;
                                                                    }
                                                                    return 0;
                                                                } else {
                                                                    if ((p9 == 92) || (p9 == 93)) {
                                                                        c8.ua v10_1 = this.C8();
                                                                        if (p9 == 92) {
                                                                            v2_0 = 2;
                                                                        }
                                                                        z8.b0.V(v10_1, v2_0);
                                                                        return 1;
                                                                    } else {
                                                                        switch (p9) {
                                                                            case 8:
                                                                            case 9:
                                                                            case 10:
                                                                            case 11:
                                                                            case 12:
                                                                            case 13:
                                                                            case 14:
                                                                            case 15:
                                                                            case 16:
                                                                                if (!p10.isCtrlPressed()) {
                                                                                    return 0;
                                                                                } else {
                                                                                    int v9_11;
                                                                                    if (p9 != 16) {
                                                                                        v9_11 = (p9 - 8);
                                                                                    } else {
                                                                                        v9_11 = (this.L0.i() - 1);
                                                                                    }
                                                                                    this.m0.X1(v9_11);
                                                                                    return 1;
                                                                                }
                                                                            default:
                                                                                switch (p9) {
                                                                                    case 32:
                                                                                        if (p10.isCtrlPressed()) {
                                                                                            this.fa(6);
                                                                                            return 1;
                                                                                        }
                                                                                        return 0;
                                                                                    case 33:
                                                                                        break;
                                                                                    case 34:
                                                                                        if (!p10.isCtrlPressed()) {
                                                                                            if (p10.isAltPressed()) {
                                                                                                this.eb();
                                                                                                return 1;
                                                                                            }
                                                                                        } else {
                                                                                            this.fa(14);
                                                                                            return 1;
                                                                                        }
                                                                                        return 0;
                                                                                    case 35:
                                                                                        if (p10.isCtrlPressed()) {
                                                                                            if (p10.isShiftPressed()) {
                                                                                                v4_0 = 22;
                                                                                            }
                                                                                            this.fa(v4_0);
                                                                                            return 1;
                                                                                        }
                                                                                        return 0;
                                                                                    case 36:
                                                                                        if (p10.isCtrlPressed()) {
                                                                                            this.fa(8);
                                                                                            return 1;
                                                                                        }
                                                                                        return 0;
                                                                                    default:
                                                                                        return 0;
                                                                                }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if ((p9 == 82) || (p10.isAltPressed())) {
                                    this.eb();
                                    return 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public final void za()
    {
        if (android.os.Build$VERSION.SDK_INT >= 21) {
            this.t1 = z8.l3.l(this.I());
            this.s1 = this.U0;
            return;
        } else {
            return;
        }
    }

    public final void zb()
    {
        if (u9.d.m(this.I(), this.G8())) {
            Object[] v2 = new Object[0];
            pc.a.a("is in home page, change color for homepage", v2);
            this.U0 = g6.y.H(this.U0);
            this.h0(0);
        }
        return;
    }
}
