// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/edge_hub/downloads/EdgeDownloadDelegateImpl
package org.chromium.chrome.browser.edge_hub.downloads;
public class EdgeDownloadDelegateImpl extends t8d {

    public EdgeDownloadDelegateImpl()
    {
        return;
    }

    public boolean isSaveToLocalAllowed()
    {
        return com.microsoft.edge.managedbehavior.MAMEdgeManager.isSaveToLocalAllowed();
    }

    public void protect(String p4, String p5)
    {
        if (com.microsoft.edge.managedbehavior.MAMEdgeManager.m()) {
            bng v0_1 = new java.io.File(p4);
            if (!v0_1.exists()) {
                String v1_1;
                if (p4 != null) {
                    v1_1 = qpe.a(af9.a, android.net.Uri.parse(p4));
                } else {
                    v1_1 = 0;
                }
                v0_1 = new java.io.File(v1_1);
            }
            String v1_4;
            String v1_2 = bmj.b();
            if ((v1_2 == null) || (!v1_2.isIncognito())) {
                v1_4 = 0;
            } else {
                v1_4 = 1;
            }
            com.microsoft.intune.mam.client.identity.MAMFileProtectionManager.protectForOID(v0_1, com.microsoft.edge.managedbehavior.MAMEdgeManager.h(v1_4));
            com.microsoft.edge.managedbehavior.MAMEdgeManager.d.add(p5);
            return;
        }
        return;
    }

    public boolean shouldBlockMediaStoreAccess()
    {
        if ((!uzf.e()) || (ze9.a.getBoolean("Edge.China.Privacy", 0))) {
            return 0;
        } else {
            return 1;
        }
    }
}
