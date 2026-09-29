// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/DownloadManagerBridge
package org.chromium.chrome.browser.download;
public final class DownloadManagerBridge {
    public static final Object a;

    static DownloadManagerBridge()
    {
        org.chromium.chrome.browser.download.DownloadManagerBridge.a = new Object();
        return;
    }

    public static wad a(long p7)
    {
        wad v1_1 = new wad();
        android.app.DownloadManager v2_2 = ((android.app.DownloadManager) af9.a.getSystemService("download"));
        try {
            String v4_0 = new android.app.DownloadManager$Query();
            boolean v5_1 = new long[1];
            v5_1[0] = p7;
            android.database.Cursor v3_0 = v2_2.query(v4_0.setFilterById(v5_1));
        } catch (String v7_2) {
            android.util.Log.e("cr_DownloadDelegate", "unable to query android DownloadManager", v7_2);
            if (v3_0 == null) {
                return v1_1;
            } else {
                v3_0.close();
                return v1_1;
            }
        } catch (String v7_3) {
            if (v3_0 != null) {
                v3_0.close();
            }
            throw v7_3;
        }
        if (v3_0 != null) {
            if (v3_0.moveToNext()) {
                v3_0.getInt(v3_0.getColumnIndexOrThrow("status"));
                v3_0.getString(v3_0.getColumnIndexOrThrow("title"));
                v3_0.getInt(v3_0.getColumnIndexOrThrow("reason"));
                v3_0.getLong(v3_0.getColumnIndexOrThrow("last_modified_timestamp"));
                v3_0.getLong(v3_0.getColumnIndexOrThrow("bytes_so_far"));
                v3_0.getLong(v3_0.getColumnIndexOrThrow("total_size"));
                String v4_17 = v3_0.getString(v3_0.getColumnIndexOrThrow("local_uri"));
                if (!android.text.TextUtils.isEmpty(v4_17)) {
                    android.net.Uri.parse(v4_17).getPath();
                }
            }
            v1_1.b = v2_2.getUriForDownloadedFile(p7);
            v1_1.a = v2_2.getMimeTypeForDownloadedFile(p7);
        } else {
            if (v3_0 == null) {
                return v1_1;
            } else {
                v3_0.close();
                return v1_1;
            }
        }
    }

    public static void addCompletedDownload(long p3)
    {
        try {
            new tad(p3).executeOnExecutor(qu1.THREAD_POOL_EXECUTOR);
            return;
        } catch (java.util.concurrent.RejectedExecutionException) {
            android.util.Log.e("cr_DownloadDelegate", "Thread limit reached, reschedule notification update later.");
            J.N.VJJ(8, p3, -1);
            return;
        }
    }

    public static void removeCompletedDownload(String p1, boolean p2)
    {
        sad v0_1 = new sad();
        v0_1.a = p1;
        v0_1.b = p2;
        org.chromium.base.task.PostTask.c(1, v0_1);
        return;
    }
}
