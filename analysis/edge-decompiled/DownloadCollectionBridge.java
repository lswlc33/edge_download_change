// source: split_chrome base/classes4.dex  class: org/chromium/components/download/DownloadCollectionBridge
package org.chromium.components.download;
public final class DownloadCollectionBridge {
    public static final java.util.ArrayList a;
    public static s8d b;

    static DownloadCollectionBridge()
    {
        org.chromium.components.download.DownloadCollectionBridge.a = new java.util.ArrayList(java.util.Arrays.asList(new String[] {"tar.gz", "tar.z", "tar.bz2", "tar.bz", "user.js"})));
        org.chromium.components.download.DownloadCollectionBridge.b = new s8d();
        return;
    }

    public static android.net.Uri a(String p8, String p9, String p10, String p11)
    {
        Long v9_7;
        Long v9_2 = org.chromium.components.download.DownloadCollectionBridge.b.remapGenericMimeType(p9, p10, p8);
        android.net.Uri v0_1 = android.provider.MediaStore$Downloads.EXTERNAL_CONTENT_URI;
        java.util.Objects.requireNonNull(v0_1);
        long v1_5 = (System.currentTimeMillis() / 1000);
        android.content.ContentValues v5_0 = new android.content.ContentValues();
        java.util.Objects.requireNonNull(p8);
        v5_0.put("_display_name", p8);
        java.util.Objects.requireNonNull(v9_2);
        v5_0.put("mime_type", v9_2);
        v5_0.put("date_added", Long.valueOf(v1_5));
        v5_0.put("date_modified", Long.valueOf(v1_5));
        v5_0.put("is_pending", Integer.valueOf(1));
        android.net.Uri v8_6 = org.chromium.components.download.DownloadCollectionBridge.b.parseOriginalUrl(p10);
        if (!android.text.TextUtils.isEmpty(p11)) {
            v9_7 = android.net.Uri.parse(p11);
        } else {
            v9_7 = 0;
        }
        if (v8_6 != null) {
            v5_0.put("download_uri", v8_6.toString());
        } else {
            v5_0.remove("download_uri");
        }
        if (v9_7 != null) {
            v5_0.put("referer_uri", v9_7.toString());
        } else {
            v5_0.remove("referer_uri");
        }
        v5_0.put("date_expires", Long.valueOf((((((long) J.N.I(15)) * 86400000) + System.currentTimeMillis()) / 1000)));
        try {
            return af9.a.getContentResolver().insert(v0_1, v5_0);
        } catch (Exception) {
            return 0;
        }
    }

    public static android.net.Uri b(String p10)
    {
        int v1 = 0;
        try {
            String v2_0 = android.provider.MediaStore$Downloads.EXTERNAL_CONTENT_URI;
            int v10_1 = af9.a.getContentResolver().query(android.provider.MediaStore.setIncludePending(v2_0), new String[] {"_id"}), "_display_name LIKE ?1", new String[] {p10}), 0);
        } catch (android.net.Uri v0_2) {
            v10_1 = 0;
            android.util.Log.e("cr_DownloadCollection", "Unable to check file name existence.", v0_2);
            if (v10_1 == 0) {
                return 0;
            } else {
                v10_1.close();
                return 0;
            }
        } catch (android.net.Uri v0_1) {
            if (v1 != 0) {
                v1.close();
            }
            throw v0_1;
        } catch (android.net.Uri v0_1) {
            v1 = v10_1;
        }
        if (v10_1 != 0) {
            try {
                if (!v10_1.moveToNext()) {
                } else {
                    android.net.Uri v0_5 = android.content.ContentUris.withAppendedId(v2_0, ((long) v10_1.getInt(v10_1.getColumnIndexOrThrow("_id"))));
                    v10_1.close();
                    return v0_5;
                }
            } catch (android.net.Uri v0_2) {
            }
        } else {
            if (v10_1 != 0) {
                v10_1.close();
            }
            return 0;
        }
    }

    public static kwq c(String p2)
    {
        android.content.Context v0 = af9.a;
        android.net.Uri v2_1 = android.net.Uri.parse(p2);
        kwq v1_1 = new kwq();
        java.util.Objects.requireNonNull(v0);
        v1_1.a = v0;
        java.util.Objects.requireNonNull(v2_1);
        v1_1.b = v2_1;
        return v1_1;
    }

    public static boolean copyFileToIntermediateUri(String p1, String p2)
    {
        try {
            org.chromium.components.download.DownloadCollectionBridge.b.protect(p1, p2);
            String v2_2 = org.chromium.components.download.DownloadCollectionBridge.c(p2);
            String v2_4 = v2_2.a.getContentResolver().openOutputStream(v2_2.b);
            String v0_2 = new java.io.FileInputStream(p1);
            android.os.FileUtils.copy(v0_2, v2_4);
            v0_2.close();
            v2_4.close();
            return 1;
        } catch (int v1_2) {
            android.util.Log.e("cr_DownloadCollection", "Unable to copy content to pending Uri.", v1_2);
            return 0;
        }
    }

    public static String createIntermediateUriForPublish(String p5, String p6, String p7, String p8)
    {
        String v0_0 = org.chromium.components.download.DownloadCollectionBridge.a(p5, p6, p7, p8);
        if (v0_0 == null) {
            String v0_3 = new java.text.SimpleDateFormat("yyyy-MM-dd\'T\'HHmmss.SSS", java.util.Locale.getDefault());
            StringBuilder v1_0 = org.chromium.components.download.DownloadCollectionBridge.a.iterator();
            while (v1_0.hasNext()) {
                java.util.Date v2_3 = ((String) v1_0.next());
                if (p5.endsWith(v2_3)) {
                    java.util.Date v2_5 = p5.substring(0, (p5.length() - v2_3.length()));
                    if (v2_5.endsWith(".")) {
                        StringBuilder v1_3 = tsi.g(1, 0, v2_5);
                    }
                }
                String v5_1 = p5.substring(v1_3.length());
                StringBuilder v1_6 = tsi.s(v1_3, " - ");
                v1_6.append(v0_3.format(new java.util.Date()));
                v1_6.append(v5_1);
                String v5_3 = org.chromium.components.download.DownloadCollectionBridge.a(v1_6.toString(), p6, p7, p8);
                if (v5_3 != null) {
                    return v5_3.toString();
                } else {
                    return 0;
                }
            }
            StringBuilder v1_2 = p5.lastIndexOf(46);
            if (v1_2 != -1) {
                v1_3 = p5.substring(0, v1_2);
            } else {
                v1_3 = p5;
            }
        } else {
            return v0_0.toString();
        }
    }

    public static void deleteIntermediateUri(String p2)
    {
        Exception v2_1 = org.chromium.components.download.DownloadCollectionBridge.c(p2);
        try {
            v2_1.a.getContentResolver().delete(v2_1.b, 0, 0);
            return;
        } catch (Exception v2_3) {
            android.util.Log.e("MediaStoreUtils", "Unable to delete pending session.", v2_3);
            return;
        }
    }

    public static boolean fileNameExists(String p0)
    {
        if (org.chromium.components.download.DownloadCollectionBridge.b(p0) == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public static String getDisplayName(String p8)
    {
        int v1_1 = 0;
        try {
            int v8_1 = af9.a.getContentResolver().query(android.net.Uri.parse(p8), new String[] {"_display_name"}), 0, 0, 0);
        } catch (String v0_2) {
            v8_1 = 0;
            android.util.Log.e("cr_DownloadCollection", "Unable to get display name for download.", v0_2);
            if (v8_1 == 0) {
                return 0;
            } else {
                v8_1.close();
                return 0;
            }
            if (v8_1 != 0) {
                v8_1.close();
            }
            return 0;
        } catch (String v0_1) {
            if (v1_1 != 0) {
                v1_1.close();
            }
            throw v0_1;
        } catch (String v0_1) {
            v1_1 = v8_1;
        }
        if (v8_1 != 0) {
            try {
                if (v8_1.getCount() != 0) {
                    if (!v8_1.moveToNext()) {
                    } else {
                        String v0_4 = v8_1.getString(v8_1.getColumnIndexOrThrow("_display_name"));
                        v8_1.close();
                        return v0_4;
                    }
                } else {
                }
            } catch (String v0_2) {
            }
        }
    }

    public static org.chromium.components.download.DownloadCollectionBridge$DisplayNameInfo[] getDisplayNamesForDownloads()
    {
        int v3 = 0;
        if (!org.chromium.components.download.DownloadCollectionBridge.b.shouldBlockMediaStoreAccess()) {
            try {
                String v2_0 = android.provider.MediaStore$Downloads.EXTERNAL_CONTENT_URI;
                int v4_0 = af9.a.getContentResolver().query(android.provider.MediaStore.setIncludePending(v2_0), new String[] {"_id", "_display_name"}), 0, 0, 0);
            } catch (org.chromium.components.download.DownloadCollectionBridge$DisplayNameInfo[] v0_2) {
                v4_0 = 0;
                java.util.ArrayList v5_5 = new StringBuilder("cr_");
                v5_5.append("DownloadCollection");
                android.util.Log.e(v5_5.toString(), "Unable to get display names for downloads.", v0_2);
                if (v4_0 == 0) {
                    return v3;
                } else {
                    v4_0.close();
                    return v3;
                }
                if (v4_0 != 0) {
                    v4_0.close();
                    return 0;
                }
            } catch (org.chromium.components.download.DownloadCollectionBridge$DisplayNameInfo[] v0_1) {
                if (v3 != 0) {
                    v3.close();
                }
                throw v0_1;
            }
            if (v4_0 != 0) {
                if (v4_0.getCount() != 0) {
                    java.util.ArrayList v5_3 = new java.util.ArrayList();
                    while (v4_0.moveToNext()) {
                        String v6_3 = v4_0.getString(v4_0.getColumnIndexOrThrow("_display_name"));
                        String v7_5 = android.content.ContentUris.withAppendedId(v2_0, ((long) v4_0.getInt(v4_0.getColumnIndexOrThrow("_id")))).toString();
                        org.chromium.components.download.DownloadCollectionBridge$DisplayNameInfo v8_2 = new org.chromium.components.download.DownloadCollectionBridge$DisplayNameInfo();
                        v8_2.a = v7_5;
                        v8_2.b = v6_3;
                        v5_3.add(v8_2);
                    }
                    org.chromium.components.download.DownloadCollectionBridge$DisplayNameInfo[] v0_4 = new org.chromium.components.download.DownloadCollectionBridge$DisplayNameInfo[0];
                    org.chromium.components.download.DownloadCollectionBridge$DisplayNameInfo[] v0_6 = ((org.chromium.components.download.DownloadCollectionBridge$DisplayNameInfo[]) v5_3.toArray(v0_4));
                    v4_0.close();
                    return v0_6;
                } else {
                }
            }
        }
        return v3;
    }

    public static int openIntermediateUri(String p9)
    {
        try {
            String v0_3 = af9.a.getContentResolver().openFileDescriptor(android.net.Uri.parse(p9), "rw");
            String v1_3 = new android.content.ContentValues();
            v1_3.put("date_expires", Long.valueOf((((((long) J.N.I(15)) * 86400000) + System.currentTimeMillis()) / 1000)));
            af9.a.getContentResolver().update(android.net.Uri.parse(p9), v1_3, 0, 0);
            return v0_3.detachFd();
        } catch (int v9_3) {
            android.util.Log.e("cr_DownloadCollection", "Cannot open intermediate Uri.", v9_3);
            return -1;
        }
    }

    public static String publishDownload(String p10)
    {
        android.content.ContentResolver v3 = af9.a.getContentResolver();
        int v9 = 0;
        try {
            String v0_6;
            String v4_0 = v3.query(android.net.Uri.parse(p10), new String[] {"mime_type"}), 0, 0, 0);
            try {
                if ((v4_0 == null) || ((v4_0.getCount() == 0) || (!v4_0.moveToNext()))) {
                    v0_6 = 0;
                } else {
                    v0_6 = v4_0.getString(v4_0.getColumnIndexOrThrow("mime_type"));
                }
            } catch (String v0_1) {
                android.util.Log.e("cr_DownloadCollection", "Unable to get mimeType.", v0_1);
                if (v4_0 != null) {
                    v4_0.close();
                }
                String v4_1 = 0;
                Exception v10_2 = org.chromium.components.download.DownloadCollectionBridge.c(p10);
                android.net.Uri v5_1 = v10_2.b;
                String v0_9 = new android.content.ContentValues();
                v0_9.put("is_pending", Integer.valueOf(0));
                v0_9.putNull("date_expires");
                try {
                    v10_2.a.getContentResolver().update(v5_1, v0_9, 0, 0);
                } catch (String v0_10) {
                    android.util.Log.e("MediaStoreUtils", "Unable to publish pending session.", v0_10);
                }
                if (!android.text.TextUtils.isEmpty(v4_1)) {
                    try {
                        Exception v10_8 = new android.content.ContentValues();
                        v10_8.put("mime_type", v4_1);
                        v3.update(v5_1, v10_8, 0, 0);
                    } catch (String v0_12) {
                        android.util.Log.e("cr_DownloadCollection", "Unable to modify mimeType.", v0_12);
                    }
                }
                return v5_1.toString();
            }
            if (v4_0 != null) {
                v4_0.close();
            }
            v4_1 = v0_6;
        } catch (String v0_1) {
            v4_0 = 0;
        } catch (String v0_0) {
            Exception v10_1 = v0_0;
            if (v9 != 0) {
                v9.close();
            }
            throw v10_1;
        } catch (String v0_5) {
            v10_1 = v0_5;
            v9 = v4_0;
        }
    }

    public static boolean renameDownloadUri(String p2, String p3)
    {
        android.content.ContentValues v0_1 = new android.content.ContentValues();
        int v2_3 = android.net.Uri.parse(p2);
        v0_1.put("_display_name", p3);
        if (af9.a.getContentResolver().update(v2_3, v0_1, 0, 0) != 1) {
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean shouldPublishDownload(String p1)
    {
        if ((org.chromium.components.download.DownloadCollectionBridge.b.isSaveToLocalAllowed()) && (p1 != 0)) {
            return (org.chromium.components.download.DownloadCollectionBridge.b.isDownloadOnSDCard(p1) ^ 1);
        } else {
            return 0;
        }
    }
}
