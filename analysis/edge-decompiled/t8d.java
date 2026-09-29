// classes.dex Lt8d;
public abstract class Lt8d extends s8d {

    public boolean isDownloadOnSDCard(String p1)
    {
        return d9d.d(p1);
    }

    public android.net.Uri parseOriginalUrl(String p3)
    {
        android.net.Uri v2_1;
        if (!android.text.TextUtils.isEmpty(p3)) {
            v2_1 = android.net.Uri.parse(p3);
        } else {
            v2_1 = 0;
        }
        if (v2_1 != null) {
            boolean v3_2 = v2_1.normalizeScheme().getScheme();
            if ((!v3_2) || ((!v3_2.equals("https")) && (!v3_2.equals("http")))) {
                return 0;
            }
        }
        return v2_1;
    }

    public String remapGenericMimeType(String p1, String p2, String p3)
    {
        return org.chromium.chrome.browser.download.MimeUtils.remapGenericMimeType(p1, p2, p3);
    }
}
