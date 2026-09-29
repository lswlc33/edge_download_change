// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/edge_hub/downloads/EdgeDownloadManagerHelper
package org.chromium.chrome.browser.edge_hub.downloads;
public class EdgeDownloadManagerHelper {
    static final synthetic boolean $assertionsDisabled;

    public EdgeDownloadManagerHelper()
    {
        return;
    }

    public static synthetic void a(z49 p0, java.util.ArrayList p1)
    {
        org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadManagerHelper.lambda$checkDownloadsOptionDisplay$0(p0, p1);
        return;
    }

    public static void cancelAllDownloadTasks()
    {
        c0u v0 = wzt.a();
        v0.c(new org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadManagerHelper$1(v0));
        return;
    }

    public static void checkDownloadsOptionDisplay(z49 p5)
    {
        if (org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadManagerHelper.isUseNewDownloadDialogFlowEnabled()) {
            if (android.os.Build$VERSION.SDK_INT < 31) {
                if (!org.chromium.chrome.browser.edge_signin.account.EdgeAccountManager.b().l()) {
                    ahe v1_1 = new ahe();
                    v1_1.a = p5;
                    b9d.a.a(v1_1);
                    return;
                } else {
                    kkz.j(1, 2, "Microsoft.Mobile.DownloadManager.Settings.PageAction");
                    p5.accept(Boolean.FALSE);
                    return;
                }
            } else {
                kkz.j(1, 2, "Microsoft.Mobile.DownloadManager.Settings.PageAction");
                p5.accept(Boolean.FALSE);
                return;
            }
        } else {
            kkz.j(1, 2, "Microsoft.Mobile.DownloadManager.Settings.PageAction");
            p5.accept(Boolean.FALSE);
            return;
        }
    }

    public static java.io.File createFile(String p2, String p3, boolean p4, String p5)
    {
        java.io.File v0_2;
        if (!p3.isEmpty()) {
            v0_2 = new java.io.File(p3);
        } else {
            v0_2 = rv20.f();
        }
        if ((!v0_2.exists()) && (!v0_2.mkdir())) {
            return 0;
        } else {
            if (!p4) {
                return rv20.e(p3, p2, p5);
            } else {
                return java.io.File.createTempFile(p2, p5, v0_2);
            }
        }
    }

    public static boolean isDownloadShownInSettings()
    {
        return 0;
    }

    public static boolean isInAppNotificationEnabled()
    {
        if ((org.chromium.chrome.browser.edge_signin.account.EdgeAccountManager.b().l()) || ((!azh.a()) || (!h27.b.h("msEdgeInAppNotificationAndroid")))) {
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean isUseNewDownloadDialogFlowEnabled()
    {
        if ((org.chromium.chrome.browser.edge_signin.account.EdgeAccountManager.b().l()) || ((!azh.a()) || (!h27.b.h("msEdgeNewDownloadDialogFlowAndroid")))) {
            return 0;
        } else {
            return 1;
        }
    }

    private static void lambda$checkDownloadsOptionDisplay$0(z49 p2, java.util.ArrayList p3)
    {
        if ((p3 == null) || (p3.size() < 2)) {
            kkz.j(1, 2, "Microsoft.Mobile.DownloadManager.Settings.PageAction");
            p2.accept(Boolean.FALSE);
            return;
        } else {
            kkz.j(0, 2, "Microsoft.Mobile.DownloadManager.Settings.PageAction");
            p2.accept(Boolean.TRUE);
            return;
        }
    }
}
