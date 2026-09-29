// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/service/DownloadBackgroundTaskCallback
package org.chromium.chrome.browser.download.service;
public final class DownloadBackgroundTaskCallback {
    public org.chromium.base.Callback a;

    public final void finishTask(boolean p1)
    {
        this.a.onResult(Boolean.valueOf(p1));
        return;
    }

    public final void notify(org.chromium.components.offline_items_collection.OfflineItem p16)
    {
        v89 v1 = p16.a;
        if (v1 != null) {
            hcd v0 = gcd.a;
            String v2 = p16.b;
            org.chromium.chrome.browser.profiles.OtrProfileId v8 = org.chromium.chrome.browser.profiles.OtrProfileId.deserializeWithoutVerify(p16.v);
            boolean v9 = p16.A;
            boolean v10 = p16.e;
            org.chromium.url.GURL v12 = p16.t;
            v0.getClass();
            v0.f(v1, v2, new o0u(0, 0, 2), 0, 0, v8, v9, v10, 0, v12, 0, 1);
            return;
        } else {
            return;
        }
    }
}
