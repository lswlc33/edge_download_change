// classes.dex Lnad;
public final class Lnad implements p3s, w8d {
    public org.chromium.chrome.browser.download.DownloadDialogBridge a;
    public org.chromium.ui.modelutil.PropertyModel b;
    public org.chromium.ui.modelutil.PropertyModel c;
    public xgy d;
    public org.chromium.chrome.browser.download.dialogs.DownloadLocationCustomView e;
    public n3s f;
    public long g;
    public int h;
    public String i;
    public android.content.Context j;
    public boolean k;
    public org.chromium.chrome.browser.profiles.Profile l;
    public boolean m;
    public x8d n;
    public pad o;
    public android.graphics.drawable.LayerDrawable p;
    public int q;
    public idd r;
    public boolean s;

    public final String a()
    {
        if ((!this.m) && ((!this.l.l()) || (this.k))) {
            String v3_1 = tzy.download_location_dialog_title;
        } else {
            v3_1 = tzy.download_location_dialog_title_confirm_download;
        }
        return this.j.getString(v3_1);
    }

    public final void c()
    {
        return;
    }

    public final void d()
    {
        this.j = 0;
        this.f = 0;
        this.i = 0;
        this.l = 0;
        this.n = 0;
        this.o = 0;
        this.c = 0;
        this.b = 0;
        this.e = 0;
        xgy v1 = this.d;
        if (v1 != null) {
            v1.b();
            this.d = 0;
        }
        return;
    }

    public final void e()
    {
        if (this.e != null) {
            jad v0_3 = this.n;
            if (v0_3 != null) {
                int v1_0 = v0_3.a;
                if (v1_0 == -1) {
                    v1_0 = v0_3.c();
                } else {
                    int v3_3 = this.h;
                    if ((v3_3 == 2) || (v3_3 == 3)) {
                    }
                }
                if (this.h == 6) {
                    int v1_3;
                    jad v0_2 = this.n;
                    long v5 = this.g;
                    int v1_2 = v0_2.d.f();
                    if (v1_2 == 0) {
                        v1_3 = 0;
                    } else {
                        v1_3 = org.chromium.chrome.browser.download.DownloadDialogBridge.a(v1_2.a);
                    }
                    double v7 = 0;
                    int v9 = -1;
                    int v3_1 = 0;
                    while (v3_1 < v0_2.getCount()) {
                        double v10_2 = ((fwc) v0_2.getItem(v3_1));
                        if ((v10_2 != 0) && ((v1_3 == 0) || (!v1_3.equals(v10_2.b)))) {
                            double v11_5 = (((double) (v10_2.c - v5)) / ((double) v10_2.d));
                            if (v11_5 > v7) {
                                v9 = v3_1;
                                v7 = v11_5;
                            }
                        }
                        v3_1++;
                    }
                    if (v9 == -1) {
                        v0_2.a();
                        v1_0 = 0;
                    } else {
                        v0_2.a = v9;
                        v1_0 = v9;
                    }
                }
                jad v0_4 = this.e;
                v0_4.f.setAdapter(this.n);
                v0_4.f.setSelection(v1_0);
                int v1_6 = this.e;
                if (!h27.b.h("SmartSuggestionForLargeDownloads")) {
                    jad v0_9 = new jad(1);
                    v0_9.b = this;
                    v1_6.f.setOnItemSelectedListener(v0_9);
                } else {
                    jad v0_11 = new jad(0);
                    v0_11.b = this;
                    v1_6.f.setOnItemSelectedListener(v0_11);
                    return;
                }
            }
        }
        return;
    }

    public final pad f()
    {
        return this.o;
    }

    public final void j(org.chromium.ui.modelutil.PropertyModel p4, int p5)
    {
        int v4_0 = 1;
        if (p5 == 1) {
            org.chromium.chrome.browser.profiles.Profile v5_4 = this.e;
            if (v5_4 != null) {
                org.chromium.chrome.browser.profiles.Profile v5_3;
                org.chromium.chrome.browser.download.DownloadDialogBridge v0_7 = v5_4.d;
                org.chromium.chrome.browser.download.DownloadDialogBridge v1_1 = 0;
                if ((v0_7 != null) && (v0_7.getText() != null)) {
                    v5_3 = v5_4.d.getText().toString();
                } else {
                    v5_3 = 0;
                }
                org.chromium.chrome.browser.download.DownloadDialogBridge v0_1 = this.e.f;
                if (v0_1 != null) {
                    v1_1 = ((fwc) v0_1.getSelectedItem());
                }
                org.chromium.chrome.browser.download.DownloadDialogBridge v0_6;
                org.chromium.chrome.browser.download.DownloadDialogBridge v0_4 = this.e.h;
                if ((v0_4 == null) || (!v0_4.isChecked())) {
                    v0_6 = 0;
                } else {
                    v0_6 = 1;
                }
                if (v1_1 != null) {
                    org.chromium.chrome.browser.download.DownloadDialogBridge v1_2 = v1_1.b;
                    if ((v1_2 != null) && (v5_3 != null)) {
                        org.chromium.chrome.browser.download.DownloadDialogBridge.f(this.l, v1_2);
                        org.chromium.chrome.browser.profiles.Profile v5_5 = new java.io.File(v1_2, v5_3).getAbsolutePath();
                        if ((bhe.a == null) || (!com.microsoft.edge.managedbehavior.MAMEdgeManager.isSaveToLocalAllowed())) {
                            v5_5 = this.i;
                        }
                        this.a.d(v5_5, 1);
                        if (this.m) {
                            this.d();
                            return;
                        } else {
                            if (v0_6 != null) {
                                v4_0 = 2;
                            }
                            org.chromium.chrome.browser.download.DownloadDialogBridge.g(v4_0, this.l);
                            this.d();
                            return;
                        }
                    }
                }
                this.a.c();
            }
        } else {
            this.a.c();
        }
        this.d();
        return;
    }

    public final void l(org.chromium.ui.modelutil.PropertyModel p2, int p3)
    {
        n3s v1_1 = this.f;
        if (v1_1 != null) {
            if (p3 == 0) {
                v1_1.c(p2, 1);
                return;
            } else {
                if (p3 == 1) {
                    v1_1.c(p2, 2);
                    return;
                }
            }
        }
        return;
    }
}
