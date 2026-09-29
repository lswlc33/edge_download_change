// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/DownloadItem
package org.chromium.chrome.browser.download;
public final class DownloadItem {
    public final v89 a;
    public final boolean b;
    public final org.chromium.chrome.browser.download.DownloadInfo c;
    public long d;

    public DownloadItem(boolean p4, org.chromium.chrome.browser.download.DownloadInfo p5)
    {
        v89 v0_1 = new v89();
        this.a = v0_1;
        this.d = -1;
        this.b = p4;
        this.c = p5;
        if (p5 != null) {
            v0_1.a = p5.s.a;
        }
        v0_1.b = this.a();
        return;
    }

    public static org.chromium.chrome.browser.download.DownloadItem createDownloadItem(org.chromium.chrome.browser.download.DownloadInfo p0, long p1, long p3, boolean p5)
    {
        return new org.chromium.chrome.browser.download.DownloadItem(0, p0);
    }

    public final String a()
    {
        if (!this.b) {
            return this.c.l;
        } else {
            return String.valueOf(this.d);
        }
    }
}
