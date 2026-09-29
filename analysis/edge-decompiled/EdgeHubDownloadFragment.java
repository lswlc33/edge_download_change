// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/app/download/home/EdgeHubDownloadFragment
package org.chromium.chrome.browser.app.download.home;
public class EdgeHubDownloadFragment extends org.chromium.chrome.browser.edge_hub.base.HubBaseFragment implements rel {
    public gbd c;

    public EdgeHubDownloadFragment()
    {
        return;
    }

    public final android.view.View P1()
    {
        if (this.c == null) {
            return 0;
        } else {
            kkz.j(2, 4, "Microsoft.Mobile.DownloadManager.Hub.Show");
            dhe.a = android.os.SystemClock.uptimeMillis();
            kkz.j(3, 4, "Microsoft.Mobile.DownloadManager.Hub.Show");
            android.widget.FrameLayout v0_3 = this.c.i;
            vyf.a.d(v0_3, this, "Download_Hub");
            return v0_3;
        }
    }

    public final boolean g1()
    {
        return 0;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p8, android.view.ViewGroup p9, android.os.Bundle p10)
    {
        woe v0_0 = this.getActivity();
        if ((v0_0 instanceof ly6)) {
            woe v0_1 = ((ly6) v0_0);
            pbd v1_4 = org.chromium.chrome.browser.profiles.Profile.c(v0_1.O2());
            gbd v2 = 0;
            if (v1_4 != null) {
                lv30 v3_0 = qbd.a(v0_1);
                n3s v4_0 = 0;
                v3_0.b = 0;
                v3_0.l = 0;
                if ((qx6.b().c()) || (af9.a.getResources().getConfiguration().keyboard != 1)) {
                    v4_0 = 1;
                }
                v3_0.f = v4_0;
                pbd v1_1 = v1_4.a;
                if (v1_1 != null) {
                    v3_0.a = v1_1;
                }
                pbd v1_2 = v3_0.a();
                lv30 v3_2 = new lv30(this.getActivity(), ((android.view.ViewGroup) this.getActivity().findViewById(lzy.root_container)), v0_1.getModalDialogManager());
                n3s v4_2 = v0_1.getModalDialogManager();
                if (v4_2 != null) {
                    v2 = bbd.a(v0_1, v1_2, v3_2, v4_2);
                }
            }
            this.c = v2;
            if ((v2 != null) && (v2.i.isAttachedToWindow())) {
                woe v0_5 = new woe();
                v0_5.a = this;
                org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadManagerHelper.checkDownloadsOptionDisplay(v0_5);
            }
        }
        return super.onCreateView(p8, p9, p10);
    }

    public final void onDestroy()
    {
        super.onDestroy();
        int v0_0 = this.c;
        if (v0_0 != 0) {
            v0_0.a();
            this.c = 0;
        }
        return;
    }

    public final void setUserVisibleHint(boolean p2)
    {
        super.setUserVisibleHint(p2);
        if (p2 != 0) {
            int v2_3 = this.getActivity();
            String v1_3 = this.b;
            if ((v2_3 instanceof org.chromium.chrome.browser.ChromeTabbedActivity)) {
                int v2_4 = ((org.chromium.chrome.browser.ChromeTabbedActivity) v2_3);
                if (v2_4.F2() != null) {
                    v1_3.b(v2_4.F2());
                }
            }
            if (n1f.a(v1_3)) {
                kkz.j(1, 2, "Microsoft.Mobile.Hub.Download.Action");
            }
        }
        return;
    }
}
