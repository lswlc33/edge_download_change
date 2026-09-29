// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/settings/DownloadLocationPreference
package org.chromium.chrome.browser.download.settings;
public class DownloadLocationPreference extends erc implements w8d {
    public final qad g;
    public pad h;

    public DownloadLocationPreference(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.f = ozy.download_location_preference;
        super.g = new qad(super.getContext(), super);
        return;
    }

    public final void c()
    {
        this.k();
        return;
    }

    public final void e()
    {
        qad v0 = this.g;
        if (v0.a == -1) {
            v0.c();
        }
        this.k();
        return;
    }

    public final pad f()
    {
        return this.h;
    }

    public final void k()
    {
        int v0_0 = this.g;
        android.text.SpannableStringBuilder v1_0 = v0_0.a;
        if (v1_0 >= null) {
            int v0_4 = ((fwc) v0_0.getItem(v1_0));
            android.text.SpannableStringBuilder v1_2 = new android.text.SpannableStringBuilder();
            v1_2.append(v0_4.a);
            v1_2.append(" ");
            v1_2.append(v0_4.b);
            if (v0_4.a != null) {
                v1_2.setSpan(new android.text.style.StyleSpan(1), 0, v0_4.a.length(), 33);
            }
            this.setSummary(v1_2);
            return;
        } else {
            return;
        }
    }
}
