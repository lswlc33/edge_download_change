package mark.via.download;
public class j1 {
    public static volatile mark.via.download.j1 b;
    public mark.via.download.y1 a;

    public j1()
    {
        return;
    }

    public static mark.via.download.j1 e()
    {
        if (mark.via.download.j1.b == null) {
            if (mark.via.download.j1.b == null) {
                mark.via.download.j1.b = new mark.via.download.j1();
            }
        }
        return mark.via.download.j1.b;
    }

    public boolean a(android.content.Context p8, String p9)
    {
        if ((this.a != null) && (!android.text.TextUtils.isEmpty(p9))) {
            android.content.Intent v0_3 = new android.content.Intent();
            v0_3.addFlags(268435456);
            v0_3.setType("text/plain");
            v0_3.setAction("android.intent.action.SEND");
            v0_3.putExtra("android.intent.extra.TEXT", p9);
            String v9_2 = this.a.c();
            String[] v2_4 = this.a.a();
            if (v2_4 != null) {
                int v3 = v2_4.length;
                int v4 = 0;
                while (v4 < v3) {
                    v0_3.setComponent(new android.content.ComponentName(v9_2, v2_4[v4]));
                    try {
                        p8.startActivity(v0_3);
                        p8 = 1;
                        return 1;
                    } catch (Exception v5_1) {
                        pc.a.b(v5_1);
                        v4++;
                    }
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    public x6.j b(mark.via.download.x1 p1, String p2, String p3)
    {
        return x6.j.l();
    }

    public long c(android.content.Context p8, String p9, String p10, String p11, String p12)
    {
        if ((!android.text.TextUtils.isEmpty(p9)) && (!android.text.TextUtils.isEmpty(p10))) {
            android.app.DownloadManager$Request v0_1 = new android.app.DownloadManager$Request(android.net.Uri.parse(p9));
            int v3_0 = android.os.Build$VERSION.SDK_INT;
            v0_1.setNotificationVisibility(1);
            if (v3_0 < 29) {
                v0_1.allowScanningByMediaScanner();
            }
            v0_1.setTitle(p10);
            v0_1.setDescription(p9);
            if (p12 != null) {
                v0_1.setMimeType(p12);
            }
            v0_1.addRequestHeader("Cookie", android.webkit.CookieManager.getInstance().getCookie(p9));
            v0_1.addRequestHeader("Referer", p9);
            if (p11 != null) {
                v0_1.addRequestHeader("User-Agent", p11);
            }
            v0_1.setDestinationInExternalPublicDir(android.os.Environment.DIRECTORY_DOWNLOADS, p10);
            if (v3_0 < 29) {
                v0_1.setVisibleInDownloadsUi(1);
            }
            return ((android.app.DownloadManager) p8.getSystemService("download")).enqueue(v0_1);
        }
        return 0;
    }

    public String[][] d(android.content.Context p10)
    {
        String[] v0_0 = p10.getPackageManager();
        if (v0_0 != null) {
            String[][] v2_3 = new java.util.ArrayList();
            java.util.ArrayList v3_1 = new java.util.ArrayList();
            v2_3.add("");
            v3_1.add(p10.getString(x7.u.E1));
            java.util.Iterator v4_3 = mark.via.download.z1.a().iterator();
            while (v4_3.hasNext()) {
                String v5_2 = ((mark.via.download.y1) v4_3.next());
                String v7 = v5_2.c();
                if (!"system".equals(v7)) {
                    if (!"rpc".equals(v7)) {
                        try {
                            String v5_3 = v0_0.getPackageInfo(v7, 1);
                        } catch (Exception) {
                            v5_3 = 0;
                        }
                        if ((v5_3 != null) && (v5_3.applicationInfo.enabled)) {
                            v2_3.add(v7);
                            v3_1.add(v0_0.getApplicationLabel(v5_3.applicationInfo).toString());
                        }
                    } else {
                        v2_3.add(v7);
                        v3_1.add(v5_2.d());
                    }
                } else {
                    v2_3.add(v7);
                    v3_1.add(p10.getString(x7.u.Ed));
                }
            }
            String[] v0_1 = new String[0];
            String[] v1_1 = new String[0];
            String[] v1_3 = ((String[]) v3_1.toArray(v1_1));
            String[][] v2_1 = new String[][2];
            v2_1[0] = ((String[]) v2_3.toArray(v0_1));
            v2_1[1] = v1_3;
            return v2_1;
        } else {
            return 0;
        }
    }

    public boolean f()
    {
        int v0_0 = this.a;
        if ((v0_0 == 0) || (("system".equals(v0_0.c())) || ("rpc".equals(this.a.c())))) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean g()
    {
        int v0_0 = this.a;
        if ((v0_0 == 0) || (!"rpc".equals(v0_0.c()))) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean h()
    {
        int v0_0 = this.a;
        if ((v0_0 == 0) || (!"system".equals(v0_0.c()))) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean i(android.content.Context p9)
    {
        if (this.a != null) {
            android.content.Intent v0_2 = new android.content.Intent();
            String v2_2 = this.a.c();
            if (!"system".equals(v2_2)) {
                String[] v3_2 = this.a.b();
                if (v3_2 != null) {
                    int v5 = v3_2.length;
                    int v6 = 0;
                    while (v6 < v5) {
                        v0_2.setClassName(v2_2, v3_2[v6]);
                        try {
                            p9.startActivity(v0_2);
                            return 1;
                        } catch (Exception v7_1) {
                            pc.a.b(v7_1);
                            v6++;
                        }
                    }
                    return 0;
                } else {
                    return 0;
                }
            } else {
                v0_2.setAction("android.intent.action.VIEW_DOWNLOADS");
                try {
                    p9.startActivity(v0_2);
                    return 1;
                } catch (Exception v9_1) {
                    pc.a.b(v9_1);
                    return 0;
                }
            }
        } else {
            return 0;
        }
    }

    public void j(String p4)
    {
        this.a = 0;
        if (!android.text.TextUtils.isEmpty(p4)) {
            java.util.Iterator v0_3 = mark.via.download.z1.a().iterator();
            while (v0_3.hasNext()) {
                mark.via.download.y1 v1_2 = ((mark.via.download.y1) v0_3.next());
                if (p4.equals(v1_2.c())) {
                    this.a = v1_2;
                    break;
                }
            }
        }
        return;
    }
}
