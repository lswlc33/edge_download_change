// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/MimeUtils
package org.chromium.chrome.browser.download;
public final class MimeUtils {
    public static final java.util.HashSet a;
    public static final java.util.ArrayList b;

    static MimeUtils()
    {
        String v3 = "binary/octet-stream";
        String v5 = "octet/stream";
        org.chromium.chrome.browser.download.MimeUtils.a = new java.util.HashSet(java.util.Arrays.asList(new String[] {"text/plain", "application/unknown"})));
        v3 = "application/x-x509-user-cert";
        v5 = "application/x-pkcs12";
        org.chromium.chrome.browser.download.MimeUtils.b = new java.util.ArrayList(java.util.Arrays.asList(new String[] {"application/pdf", "application/x-wifi-config"})));
        return;
    }

    public static java.util.List a()
    {
        java.util.List v0_2 = new android.content.Intent("android.intent.action.VIEW");
        v0_2.setDataAndType(android.net.Uri.fromFile(new java.io.File("/empty.pdf")), "application/pdf");
        return hvu.c(0, v0_2);
    }

    public static boolean canAutoOpenMimeType(String p1)
    {
        if (!"application/pdf".equals(p1)) {
            return org.chromium.chrome.browser.download.MimeUtils.b.contains(p1);
        } else {
            return 0;
        }
    }

    public static String remapGenericMimeType(String p1, String p2, String p3)
    {
        if (android.text.TextUtils.isEmpty(p1)) {
            p1 = "application/unknown";
        }
        if (org.chromium.chrome.browser.download.MimeUtils.a.contains(p1)) {
            String v2_1;
            if (android.text.TextUtils.isEmpty(p3)) {
                v2_1 = android.webkit.MimeTypeMap.getFileExtensionFromUrl(p2);
            } else {
                int v0_2 = p3.lastIndexOf(".");
                if (v0_2 <= 0) {
                } else {
                    v2_1 = p3.substring((v0_2 + 1));
                }
            }
            String v2_2 = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(v2_1);
            if (v2_2 != null) {
                return v2_2;
            }
        }
        return p1;
    }
}
