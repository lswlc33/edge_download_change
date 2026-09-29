// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/service/DownloadTaskScheduler
package org.chromium.chrome.browser.download.service;
public final class DownloadTaskScheduler {

    public static int a(int p1)
    {
        if (p1 == 0) {
            return 53;
        } else {
            if (p1 == 1) {
                return 54;
            } else {
                if (p1 == 2) {
                    return 56;
                } else {
                    if (p1 == 3) {
                        return 57;
                    } else {
                        if (p1 == 4) {
                            return 58;
                        } else {
                            if (p1 == 5) {
                                return 59;
                            } else {
                                return -1;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void cancelTask(int p2)
    {
        ((h23) e23.a()).a(org.chromium.chrome.browser.download.service.DownloadTaskScheduler.a(p2), af9.a);
        return;
    }

    public static void scheduleTask(int p8, boolean p9, boolean p10, int p11, long p12, long p14)
    {
        int v5_0;
        int v12_1;
        long v14_1;
        android.os.PersistableBundle v0_1 = new android.os.PersistableBundle();
        v0_1.putInt("extra_task_type", p8);
        v0_1.putInt("extra_optimal_battery_percentage", p11);
        v0_1.putBoolean("extra_battery_requires_charging", p10);
        int v11_2 = org.chromium.chrome.browser.download.service.DownloadTaskScheduler.a(p8);
        boolean v1_0 = org.chromium.chrome.browser.download.DownloadUtils.h(v11_2);
        h23 v2_0 = e23.a();
        int v4 = 0;
        if (v1_0) {
            v12_1 = 0;
            v14_1 = 0;
            v5_0 = 0;
        } else {
            v12_1 = (p12 * 1000);
            v14_1 = (p14 * 1000);
            v5_0 = 1;
        }
        int v6 = v5_0;
        org.chromium.components.background_task_scheduler.b v7_1 = new org.chromium.components.background_task_scheduler.b();
        v7_1.a = v12_1;
        v7_1.b = v14_1;
        v7_1.c = v5_0;
        v7_1.d = v6;
        v7_1.e = 0;
        if (p8 == null) {
            if (p9 == null) {
                v4 = 1;
            } else {
                v4 = 2;
            }
        } else {
            if (p8 != 1) {
                if (p8 == 2) {
                } else {
                    if (p8 == 3) {
                    } else {
                        if ((p8 == 4) || (p8 == 5)) {
                        } else {
                            v4 = -1;
                        }
                    }
                }
            }
        }
        org.chromium.components.background_task_scheduler.TaskInfo v9_2 = new org.chromium.components.background_task_scheduler.TaskInfo();
        v9_2.a = v11_2;
        v9_2.b = v0_1;
        v9_2.c = v4;
        v9_2.d = p10;
        v9_2.e = v1_0;
        v9_2.f = 1;
        v9_2.g = 1;
        v9_2.h = v7_1;
        ((h23) v2_0).b(af9.a, v9_2);
        return;
    }
}
