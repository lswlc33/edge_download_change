// source: split_chrome base/classes4.dex  class: org/chromium/chrome/browser/download/DownloadFileProvider
package org.chromium.chrome.browser.download;
public class DownloadFileProvider extends sai {
    public static final String[] g;

    static DownloadFileProvider()
    {
        org.chromium.chrome.browser.download.DownloadFileProvider.g = new String[] {"_display_name", "_size"});
        return;
    }

    public DownloadFileProvider()
    {
        return;
    }

    public static android.net.Uri f(String p3, String p4)
    {
        String v0_4 = new android.net.Uri$Builder().scheme("content");
        String v1_4 = af9.a.getPackageName();
        StringBuilder v2_1 = new StringBuilder();
        v2_1.append(v1_4);
        v2_1.append(".DownloadFileProvider");
        return v0_4.authority(v2_1.toString()).path(p3).appendQueryParameter("file", p4).build();
    }

    public static android.net.Uri g(String p4)
    {
        if (!org.chromium.base.ContentUriUtils.d(p4)) {
            if (!android.text.TextUtils.isEmpty(p4)) {
                String v0_12 = d9d.b();
                if ((p4.indexOf(v0_12.getAbsolutePath()) != 0) || (p4.length() <= v0_12.getAbsolutePath().length())) {
                    String v0_1 = d9d.c();
                    java.io.File v1_3 = v0_1.a.iterator();
                    while (v1_3.hasNext()) {
                        boolean v2_6 = ((java.io.File) v1_3.next());
                        if ((v2_6) && (p4.startsWith(v2_6.getAbsolutePath()))) {
                            return org.chromium.chrome.browser.download.DownloadFileProvider.f("external_volume", p4.substring((v2_6.getAbsolutePath().length() + 1)));
                        }
                    }
                    String v0_3 = v0_1.b.iterator();
                    while (v0_3.hasNext()) {
                        java.io.File v1_6 = ((java.io.File) v0_3.next());
                        if ((v1_6 != null) && (p4.startsWith(v1_6.getAbsolutePath()))) {
                            return org.chromium.chrome.browser.download.DownloadFileProvider.f("download_external", p4.substring((v1_6.getAbsolutePath().length() + 1)));
                        }
                    }
                } else {
                    return org.chromium.chrome.browser.download.DownloadFileProvider.f("download", p4.substring((v0_12.getAbsolutePath().length() + 1)));
                }
            }
            return android.net.Uri.EMPTY;
        } else {
            return android.net.Uri.parse(p4);
        }
    }

    public static String h(android.net.Uri p5, z8d p6)
    {
        if (p5 != null) {
            String v6_0 = p5.getPath();
            if (!android.text.TextUtils.isEmpty(v6_0)) {
                if ((v6_0.charAt(0) == java.io.File.separatorChar) && (v6_0.length() > 1)) {
                    v6_0 = v6_0.substring(1);
                }
                String v5_1 = p5.getQueryParameter("file");
                if (v5_1 != null) {
                    String v1_3 = new StringBuilder("..");
                    String v2_2 = java.io.File.separator;
                    v1_3.append(v2_2);
                    if (!v5_1.contains(v1_3.toString())) {
                        if (!v6_0.equals("download")) {
                            String v1_8 = d9d.c();
                            java.util.ArrayList v3 = v1_8.b;
                            String v1_9 = v1_8.a;
                            if ((!v6_0.equals("external_volume")) || (v1_9.isEmpty())) {
                                if ((v6_0.equals("download_external")) && (!v3.isEmpty())) {
                                    return tsi.k(((java.io.File) v3.get(0)).getAbsolutePath(), v2_2, v5_1);
                                }
                            } else {
                                return tsi.k(((java.io.File) v1_9.get(0)).getAbsolutePath(), v2_2, v5_1);
                            }
                        } else {
                            return tsi.B(String.valueOf(d9d.b()), v2_2, v5_1);
                        }
                    }
                }
            }
        }
        return 0;
    }

    public final void attachInfoMAM(android.content.Context p1, android.content.pm.ProviderInfo p2)
    {
        super.attachInfoMAM(p1, p2);
        if (p2.exported) {
            SecurityException v0_1 = new SecurityException;
            v0_1("Provider must not be exported");
            throw v0_1;
        } else {
            if (!p2.grantUriPermissions) {
                SecurityException v0_3 = new SecurityException;
                v0_3("Provider must grant uri permissions");
                throw v0_3;
            } else {
                return;
            }
        }
    }

    public final int deleteMAM(android.net.Uri p1, String p2, String[] p3)
    {
        this = new UnsupportedOperationException;
        super("No external deletes");
        throw super;
    }

    public final String getType(android.net.Uri p2)
    {
        if (p2 != null) {
            android.webkit.MimeTypeMap v2_3 = p2.getQueryParameter("file");
            if (!android.text.TextUtils.isEmpty(v2_3)) {
                return android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(android.webkit.MimeTypeMap.getFileExtensionFromUrl(android.net.Uri.parse(v2_3).toString().replace(32, 95)));
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public final android.net.Uri insertMAM(android.net.Uri p1, android.content.ContentValues p2)
    {
        this = new UnsupportedOperationException;
        super("No external inserts");
        throw super;
    }

    public final android.os.ParcelFileDescriptor openFileMAM(android.net.Uri p1, String p2)
    {
        int v0_0 = new z8d;
        v0_0();
        int v0_1 = org.chromium.chrome.browser.download.DownloadFileProvider.h(p1, v0_0);
        if (v0_1 == 0) {
            int v0_6 = new java.io.FileNotFoundException;
            v0_6();
            throw v0_6;
        } else {
            int v1_6;
            if (!"r".equals(p2)) {
                if (("w".equals(p2)) || ("wt".equals(p2))) {
                    v1_6 = 738197504;
                } else {
                    if (!"wa".equals(p2)) {
                        if (!"rw".equals(p2)) {
                            if (!"rwt".equals(p2)) {
                                r.v(id5.r("Invalid mode: ", p2));
                                return 0;
                            } else {
                                v1_6 = 1006632960;
                            }
                        } else {
                            v1_6 = 939524096;
                        }
                    } else {
                        v1_6 = 704643072;
                    }
                }
            } else {
                v1_6 = 268435456;
            }
            return android.os.ParcelFileDescriptor.open(new java.io.File(v0_1), v1_6);
        }
    }

    public final android.database.Cursor queryMAM(android.net.Uri p7, String[] p8, String p9, String[] p10, String p11)
    {
        if (p8 == null) {
            p8 = org.chromium.chrome.browser.download.DownloadFileProvider.g;
        }
        Object[] v6_2 = new String[p8.length];
        Object[] v9_1 = new Object[p8.length];
        String[] v7_9 = org.chromium.chrome.browser.download.DownloadFileProvider.h(p7, new z8d());
        if (!android.text.TextUtils.isEmpty(v7_9)) {
            java.io.File v10_2 = new java.io.File(v7_9);
            if ((v10_2.exists()) && (v10_2.isFile())) {
                String[] v7_3 = p8.length;
                int v1 = 0;
                boolean v2 = 0;
                while (v1 < v7_3) {
                    int v3_2;
                    int v3_0 = p8[v1];
                    if (!"_display_name".equals(v3_0)) {
                        if ("_size".equals(v3_0)) {
                            v6_2[v2] = "_size";
                            v3_2 = (v2 + 1);
                            v9_1[v2] = Long.valueOf(v10_2.length());
                            v2 = v3_2;
                        }
                    } else {
                        v6_2[v2] = "_display_name";
                        v3_2 = (v2 + 1);
                        v9_1[v2] = v10_2.getName();
                    }
                    v1++;
                }
                String[] v7_4 = new String[v2];
                System.arraycopy(v6_2, 0, v7_4, 0, v2);
                Object[] v6_1 = new Object[v2];
                System.arraycopy(v9_1, 0, v6_1, 0, v2);
                android.database.MatrixCursor v8_2 = new android.database.MatrixCursor(v7_4, 1);
                v8_2.addRow(v6_1);
                return v8_2;
            } else {
                return new android.database.MatrixCursor(v6_2, 1);
            }
        } else {
            return new android.database.MatrixCursor(v6_2, 1);
        }
    }

    public final int updateMAM(android.net.Uri p1, android.content.ContentValues p2, String p3, String[] p4)
    {
        this = new UnsupportedOperationException;
        super("No external updates");
        throw super;
    }
}
