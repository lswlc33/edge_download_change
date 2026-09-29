package mark.via.download;
public class m extends k8.a {
    public mark.via.download.e C0;
    public android.widget.EditText D0;
    public android.widget.TextView E0;
    public android.widget.TextView F0;
    public mark.via.download.m$a G0;

    public m()
    {
        return;
    }

    public static synthetic void h3(mark.via.download.m p2, android.view.View p3)
    {
        g6.n.a(p2.I(), p2.C0.h(), x7.u.ig);
        p2.V2();
        return;
    }

    public static synthetic void i3(mark.via.download.m p4, mark.via.download.e p5)
    {
        p4.r3();
        android.widget.EditText v5_6 = p4.C0.d();
        if (!z8.u1.a(p4.D0.getText().toString(), v5_6)) {
            p4.D0.setText(v5_6);
            if (p4.D0.hasFocus()) {
                p4.D0.clearFocus();
                p4.D0.requestFocus();
            }
        }
        int v4_3;
        p4.s3();
        if (p4.C0.c() <= 64198568) {
            v4_3 = 0;
        } else {
            v4_3 = 8;
        }
        p4.F0.setVisibility(v4_3);
        return;
    }

    public static synthetic void j3(mark.via.download.m p1, android.view.View p2)
    {
        if (p1.G0 != null) {
            mark.via.download.m$a v2_7 = p1.D0.getText().toString().trim();
            p1.C0.n(v2_7);
            mark.via.download.e v0_1 = v2_7.lastIndexOf(46);
            if (v0_1 >= null) {
                p1.C0.p(l5.c.c(v2_7.substring((v0_1 + 1)), "application/octet-stream"));
            }
            p1.G0.a(p1.C0);
        }
        p1.V2();
        return;
    }

    public static synthetic void k3(mark.via.download.m p1, android.view.View p2, boolean p3)
    {
        if (p3 == 0) {
            p1.getClass();
            return;
        } else {
            int v2_5 = p1.D0.getText().toString();
            int v3_1 = v2_5.lastIndexOf(".");
            if ((v3_1 == -1) || ((v2_5.length() - v3_1) >= 7)) {
                p1.D0.selectAll();
                return;
            } else {
                p1.D0.setSelection(0, v3_1);
                return;
            }
        }
    }

    public static synthetic mark.via.download.e l3(mark.via.download.m p7)
    {
        p7.getClass();
        int v0 = 0;
        mark.via.download.e v1 = 0;
        try {
            String v2_2 = p7.C0.h();
            int v3_0 = ((java.net.HttpURLConnection) new java.net.URL(v2_2).openConnection());
        } catch (String v2_0) {
            v3_0 = 0;
            pc.a.i(v2_0);
            if (v3_0 == 0) {
                if (v0 != 0) {
                    v1 = p7.C0;
                }
                return v1;
            } else {
                v3_0.disconnect();
            }
            v0 = 1;
        } catch (Throwable v7) {
            if (v1 != null) {
                v1.disconnect();
            }
            throw p7;
        }
        v3_0.setRequestProperty("Cookie", android.webkit.CookieManager.getInstance().getCookie(v2_2));
        v3_0.setRequestProperty("Referer", v2_2);
        if (p7.C0.i() != null) {
            v3_0.setRequestProperty("User-Agent", p7.C0.i());
        }
        v3_0.setRequestMethod("HEAD");
        v3_0.connect();
        if (v3_0.getResponseCode() != 200) {
        } else {
            mark.via.download.e v4_10;
            if (android.os.Build$VERSION.SDK_INT < 24) {
                v4_10 = ((long) v3_0.getContentLength());
            } else {
                v4_10 = mark.via.download.f.a(v3_0);
            }
            String v5_6;
            p7.C0.m(v4_10);
            mark.via.download.e v4_11 = v3_0.getContentType();
            if (v4_11 != null) {
                v5_6 = v4_11.indexOf(59);
            } else {
                v5_6 = -1;
            }
            if (v5_6 > null) {
                v4_11 = v4_11.substring(0, v5_6);
            }
            if (v4_11 != null) {
                p7.C0.p(v4_11);
            }
            mark.via.download.e v4_13 = v3_0.getHeaderField("Content-Disposition");
            if (v4_13 != null) {
                p7.C0.l(v4_13);
            }
            if (p7.C0.j()) {
                String v2_3 = l5.b.b(v2_2, p7.C0.b(), p7.C0.e());
                if (v2_3.endsWith(".apk")) {
                    p7.C0.p("application/vnd.android.package-archive");
                }
                p7.C0.n(v2_3);
            }
        }
    }

    public static synthetic void m3(mark.via.download.m p0, android.view.View p1)
    {
        p0.V2();
        return;
    }

    public static mark.via.download.m n3(mark.via.download.e p4)
    {
        android.os.Bundle v0_1 = new android.os.Bundle();
        v0_1.putString("url", p4.h());
        v0_1.putString("fileName", p4.d());
        v0_1.putString("userAgent", p4.i());
        v0_1.putString("contentDisposition", p4.b());
        v0_1.putString("mimeType", p4.e());
        v0_1.putString("referer", p4.g());
        v0_1.putString("path", p4.f());
        v0_1.putLong("contentLength", p4.c());
        v0_1.putBoolean("fileNameOverwrittable", p4.j());
        v0_1.putString("authorization", p4.a());
        mark.via.download.m v4_3 = new mark.via.download.m();
        v4_3.F2(v0_1);
        return v4_3;
    }

    public android.view.View A1(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        return p2.inflate(x7.r.d, p3, 0);
    }

    public void R1()
    {
        super.R1();
        android.app.Dialog v0 = this.X2();
        if (v0 != null) {
            v0.setCanceledOnTouchOutside(0);
        }
        return;
    }

    public void V1(android.view.View p4, android.os.Bundle p5)
    {
        super.V1(p4, p5);
        String v5_1 = this.C0;
        if ((v5_1 != null) && (v5_1.h() != null)) {
            x8.g.f(p4);
            String v5_2 = this.C0.d();
            if (this.C0.h().startsWith("data:")) {
                if ((this.C0.e() == null) || ((this.C0.e().isEmpty()) || ((this.C0.e().contains("/*")) || ("application/octet-stream".equals(this.C0.e()))))) {
                    this.C0.p(l5.a.d(this.C0.h()));
                }
                if ((v5_2 != null) && (!v5_2.isEmpty())) {
                    if ((v5_2.indexOf(46, Math.max(0, (v5_2.length() - 7))) < 0) && (this.C0.e() != null)) {
                        mark.via.download.e v0_44 = l5.c.b(this.C0.e());
                        if (v0_44 != null) {
                            StringBuilder v1_10 = new StringBuilder();
                            v1_10.append(v5_2);
                            v1_10.append(".");
                            v1_10.append(v0_44);
                            v5_2 = v1_10.toString();
                            this.C0.n(v5_2);
                        }
                    }
                } else {
                    v5_2 = l5.a.c(this.C0.e(), 0);
                    this.C0.n(v5_2);
                }
                this.r3();
            }
            if (v5_2 == null) {
                v5_2 = l5.b.b(this.C0.h(), this.C0.b(), this.C0.e());
                this.C0.n(v5_2);
                if (v5_2.endsWith(".apk")) {
                    this.C0.p("application/vnd.android.package-archive");
                }
                this.r3();
            }
            mark.via.download.e v0_7 = ((android.widget.TextView) p4.findViewById(x7.p.p1));
            this.E0 = v0_7;
            v0_7.setTextDirection(5);
            mark.via.download.e v0_11 = ((android.widget.EditText) p4.findViewById(x7.p.j));
            this.D0 = v0_11;
            v0_11.setText(v5_2);
            this.D0.setSelectAllOnFocus(1);
            this.D0.setOnFocusChangeListener(new mark.via.download.g(this));
            p4.findViewById(x7.p.d1).setOnClickListener(new mark.via.download.h(this));
            String v5_9 = ((android.widget.TextView) p4.findViewById(x7.p.e1));
            this.F0 = v5_9;
            v5_9.setOnClickListener(new mark.via.download.i(this));
            this.s3();
            p4.findViewById(x7.p.i1).setOnClickListener(new mark.via.download.j(this));
            if (!android.webkit.URLUtil.isNetworkUrl(this.C0.h())) {
                if ((android.webkit.URLUtil.isDataUrl(this.C0.h())) && (this.C0.c() <= 0)) {
                    this.o3();
                }
            } else {
                if (this.C0.c() <= 0) {
                    this.p3();
                    return;
                }
            }
            return;
        } else {
            this.V2();
            return;
        }
    }

    public final void o3()
    {
        this.C0.m(l5.a.b(this.C0.h()));
        if (this.C0.c() > 1019904) {
            this.F0.setVisibility(8);
        }
        this.s3();
        this.r3();
        return;
    }

    public final void p3()
    {
        ((autodispose2.m) x6.f.h(new mark.via.download.k(this)).n(g7.a.c()).k(w6.b.b()).p(u8.b.a(this.b1()))).a(new mark.via.download.l(this), new x7.g0());
        return;
    }

    public void q3(mark.via.download.m$a p1)
    {
        this.G0 = p1;
        return;
    }

    public final void r3()
    {
        if (this.C0 != null) {
            android.os.Bundle v0_2 = new android.os.Bundle();
            v0_2.putString("url", this.C0.h());
            v0_2.putString("fileName", this.C0.d());
            v0_2.putString("contentDisposition", this.C0.b());
            v0_2.putString("mimeType", this.C0.e());
            v0_2.putString("userAgent", this.C0.i());
            v0_2.putString("referer", this.C0.g());
            v0_2.putString("authorization", this.C0.a());
            v0_2.putLong("contentLength", this.C0.c());
            this.F2(v0_2);
            return;
        } else {
            this.F2(0);
            return;
        }
    }

    public final void s3()
    {
        android.widget.TextView v2 = this.E0;
        Object[] v1_0 = new Object[1];
        v1_0[0] = z8.b0.w(this.I(), this.C0.c());
        v2.setText(this.Y0(x7.u.t5, v1_0));
        return;
    }

    public void w1(android.os.Bundle p4)
    {
        super.w1(p4);
        mark.via.download.e v4_1 = this.w0();
        if ((v4_1 != null) && (!v4_1.isEmpty())) {
            this.C0 = new mark.via.download.e$b().j(v4_1.getString("url")).e(v4_1.getString("fileName")).k(v4_1.getString("userAgent")).c(v4_1.getString("contentDisposition")).g(v4_1.getString("mimeType")).i(v4_1.getString("referer")).a(v4_1.getString("authorization")).h(v4_1.getString("path")).d(v4_1.getLong("contentLength")).f(v4_1.getBoolean("fileNameOverwrittable")).b();
        }
        return;
    }
}
