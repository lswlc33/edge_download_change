// source: split_chrome base/classes4.dex  class: org/chromium/components/download/InMemoryDownloadFile
package org.chromium.components.download;
public final class InMemoryDownloadFile {
    public java.io.FileOutputStream a;
    public android.os.ParcelFileDescriptor b;

    public static org.chromium.components.download.InMemoryDownloadFile createFile(String p2)
    {
        try {
            org.chromium.components.download.InMemoryDownloadFile v0_1 = new org.chromium.components.download.InMemoryDownloadFile();
            int v2_2 = android.system.Os.memfd_create(p2, 0);
            v0_1.b = android.os.ParcelFileDescriptor.dup(v2_2);
            v0_1.a = new java.io.FileOutputStream(v2_2);
            return v0_1;
        } catch (Exception) {
            return 0;
        }
    }

    public final void destroy()
    {
        try {
            String v0_0 = this.a;
        } catch (android.os.ParcelFileDescriptor v2_2) {
            android.util.Log.e("cr_InMemoryDownload", "failed to close memory file.", v2_2);
            return;
        }
        if (v0_0 != null) {
            v0_0.close();
        }
        android.os.ParcelFileDescriptor v2_1 = this.b;
        if (v2_1 != null) {
            v2_1.close();
        }
        return;
    }

    public final void finish()
    {
        try {
            int v0_0 = this.a;
        } catch (android.os.ParcelFileDescriptor v2_2) {
            android.util.Log.e("cr_InMemoryDownload", "failed to close output stream.", v2_2);
            return;
        }
        if (v0_0 != 0) {
            v0_0.close();
            this.a = 0;
        }
        android.os.ParcelFileDescriptor v2_1 = this.b;
        if (v2_1 != null) {
            v2_1.detachFd();
        }
        return;
    }

    public final int getFd()
    {
        int v0_1 = this.b;
        if (v0_1 == 0) {
            return 0;
        } else {
            return v0_1.getFd();
        }
    }

    public final void writeData(byte[] p2)
    {
        try {
            this.a.write(p2);
            return;
        } catch (Exception v1_2) {
            android.util.Log.e("cr_InMemoryDownload", "failed to write data to file.", v1_2);
            return;
        }
    }
}
