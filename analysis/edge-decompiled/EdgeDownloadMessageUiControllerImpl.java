// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/edge_hub/downloads/EdgeDownloadMessageUiControllerImpl
package org.chromium.chrome.browser.edge_hub.downloads;
public class EdgeDownloadMessageUiControllerImpl implements fhe {
    private static final int ANNOUNCEMENT_DURATION = 10000;
    private static final String TAG = "EdgeUiControllerImpl";
    private xge mAnnouncement;
    private android.graphics.drawable.Drawable mCompleteDrawable;
    private final ehe mDelegate;
    private android.graphics.drawable.Drawable mFailedDrawable;
    private boolean mHadCompletedDownload;
    private final android.os.Handler mHandler;
    private int mIconState;
    private final java.util.HashSet mIgnoredItems;
    private android.graphics.drawable.Drawable mInProgressDrawable;
    private volatile boolean mPreAppearanceMode;
    private int mPreIconState;
    private final java.util.HashMap mSeenItems;
    private final java.util.LinkedHashMap mTrackedItems;

    public EdgeDownloadMessageUiControllerImpl(ehe p3)
    {
        android.os.Handler v0_1 = new android.os.Handler();
        this.mHandler = v0_1;
        this.mTrackedItems = new java.util.LinkedHashMap();
        this.mSeenItems = new java.util.HashMap();
        this.mIgnoredItems = new java.util.HashSet();
        this.mIconState = -1;
        this.mPreIconState = -1;
        this.mDelegate = p3;
        this.mPreAppearanceMode = p3.isNightMode();
        ghe v3_3 = new ghe();
        v3_3.a = this;
        v0_1.post(v3_3);
        return;
    }

    public static synthetic void a(org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl p0, xge p1)
    {
        p0.lambda$getAnnouncement$1(p1);
        return;
    }

    public static synthetic void b(org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl p0, org.chromium.components.offline_items_collection.OfflineItem p1)
    {
        p0.lambda$onItemUpdated$0(p1);
        return;
    }

    public static synthetic void c(org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl p0)
    {
        p0.lambda$new$0();
        return;
    }

    private void computeNextStepForUpdate(org.chromium.components.offline_items_collection.OfflineItem p5)
    {
        if (p5 != null) {
            Boolean v2_0;
            this.mTrackedItems.put(p5.a, p5);
            if (p5.y != 2) {
                v2_0 = 0;
            } else {
                v2_0 = 1;
            }
            this.mSeenItems.put(p5.a, Boolean.valueOf(v2_0));
        }
        if (!this.mTrackedItems.isEmpty()) {
            String v5_1 = this.getStateResult(p5);
            this.updateNotificationMessage(((String) v5_1.first), ((String) v5_1.second));
            return;
        } else {
            return;
        }
    }

    public static synthetic void d(org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl p0, String p1, xge p2)
    {
        p0.lambda$getAnnouncement$0(p1, p2);
        return;
    }

    private void destroy()
    {
        java.util.Set v1_0 = new Object[0];
        mjp.a("EdgeUiControllerImpl", v1_0);
        this.mAnnouncement = 0;
        this.mHadCompletedDownload = 0;
        this.mIconState = -1;
        this.mPreIconState = -1;
        this.mIgnoredItems.addAll(this.mTrackedItems.keySet());
        this.mTrackedItems.clear();
        return;
    }

    public static bridge synthetic ehe e(org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl p0)
    {
        return p0.mDelegate;
    }

    public static bridge synthetic boolean f(org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl p0)
    {
        return p0.mHadCompletedDownload;
    }

    public static bridge synthetic void g(org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl p0)
    {
        p0.destroy();
        return;
    }

    private xge getAnnouncement(String p9)
    {
        org.chromium.chrome.browser.download.EdgeDownloadManagerAnnouncementView v0_3 = ((org.chromium.chrome.browser.ChromeTabbedActivity) this.mDelegate.getActivity());
        if (v0_3 == null) {
            return 0;
        } else {
            int v3_3;
            int v1_2 = v0_3.I0;
            int v3_1 = ((android.view.ViewGroup) v1_2.a.findViewById(16908290));
            android.widget.TextView v4_1 = v1_2.a;
            vge v2_2 = v4_1.getText(tzy.edge_in_app_notification_completed);
            xge v6_2 = new xge(v4_1, v3_1, ((org.chromium.chrome.browser.download.EdgeDownloadManagerAnnouncementView) android.view.LayoutInflater.from(v4_1).inflate(ozy.edge_download_manager_announcement, v3_1, 0)));
            if (!qc9.a(v4_1)) {
                v3_3 = 10000;
            } else {
                v3_3 = -2;
            }
            v6_2.j = swb0.c(1109917696, v4_1.getResources().getDisplayMetrics());
            v6_2.f();
            if (v6_2.g() != null) {
                android.widget.TextView v4_7 = v6_2.g().a;
                v4_7.setText(v2_2);
                if ((v2_2 instanceof android.text.SpannableString)) {
                    v4_7.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
                }
            }
            v6_2.e = v3_3;
            vge v2_5 = wge.e;
            if (v2_5 == null) {
                v2_5 = new wge();
                wge.e = v2_5;
            }
            v6_2.d = v2_5;
            v1_2.d(v6_2);
            if (v6_2.g() != null) {
                v6_2.g().setImageDrawable(this.mCompleteDrawable);
            }
            int v1_4 = v0_3.getResources().getString(tzy.edge_in_app_notification_completed);
            if (v6_2.g() != null) {
                v6_2.g().b.setText(v1_4);
            }
            org.chromium.chrome.browser.download.EdgeDownloadManagerAnnouncementView v0_5 = v0_3.getResources().getString(tzy.edge_in_app_notification_details);
            int v1_7 = new ihe();
            v1_7.a = this;
            v1_7.b = p9;
            if (v6_2.g() != null) {
                org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl$1 v9_6 = v6_2.g();
                vge v2_15 = new vge(0);
                v2_15.b = v6_2;
                v2_15.c = v1_7;
                v9_6.setPrimaryButton(v0_5, v2_15);
            }
            org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl$1 v9_2 = new jhe();
            v9_2.a = this;
            org.chromium.chrome.browser.download.EdgeDownloadManagerAnnouncementView v0_1 = v6_2.g();
            if (v0_1 != null) {
                v0_1.setShowDismissButton(1);
                vge v2_1 = new vge(1);
                v2_1.b = v6_2;
                v2_1.c = v9_2;
                v0_1.setOnDismissClickListener(v2_1);
            }
            org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl$1 v9_4 = new org.chromium.chrome.browser.edge_hub.downloads.EdgeDownloadMessageUiControllerImpl$1(this);
            java.util.ArrayList v8_1 = v6_2.k;
            if (v8_1 == null) {
                v8_1 = new java.util.ArrayList;
                v8_1();
                v6_2.k = v8_1;
            }
            v8_1.add(v9_4);
            return v6_2;
        }
    }

    private org.chromium.chrome.browser.profiles.OtrProfileId getOTRProfileIDForTrackedItems()
    {
        org.chromium.chrome.browser.profiles.OtrProfileId v3_4 = this.mTrackedItems.values().iterator();
        String v0 = 0;
        while (v3_4.hasNext()) {
            org.chromium.components.offline_items_collection.OfflineItem v1_1 = ((org.chromium.components.offline_items_collection.OfflineItem) v3_4.next());
            if (!android.text.TextUtils.isEmpty(v1_1.v)) {
                v0 = v1_1.v;
            }
        }
        return org.chromium.chrome.browser.profiles.OtrProfileId.deserializeWithoutVerify(v0);
    }

    private c0u getOfflineContentProvider()
    {
        return wzt.a();
    }

    private void initUI()
    {
        int v0_4 = this.mDelegate.getActivity();
        if (v0_4 != 0) {
            this.mCompleteDrawable = c81.a(izy.edge_download_complete, v0_4);
            this.mInProgressDrawable = c81.a(izy.edge_download_in_progress, v0_4);
            this.mFailedDrawable = c81.a(izy.edge_download_warning, v0_4);
            android.graphics.drawable.Drawable v2_1 = this.mFailedDrawable;
            if (!this.mDelegate.isNightMode()) {
                v2_1.setTint(af9.a.getColor(gzy.edge_grey900));
            } else {
                v2_1.setTint(af9.a.getColor(gzy.edge_grey400));
                return;
            }
        }
        return;
    }

    private boolean isVisibleToUser(org.chromium.components.offline_items_collection.OfflineItem p4)
    {
        if ((this.mDelegate.isInAppNotificationEnabled()) && (p4 != null)) {
            Boolean v0_9 = p4.a;
            if ((v0_9 == null) || (!android.text.TextUtils.equals("LEGACY_ANDROID_EDIT_PDF", v0_9.a))) {
                if (this.mIgnoredItems.contains(p4.a)) {
                    Boolean v0_4 = p4.y;
                    if (v0_4 != null) {
                        if ((v0_4 != 2) || (!Boolean.FALSE.equals(this.mSeenItems.getOrDefault(p4.a, Boolean.TRUE)))) {
                            return 0;
                        }
                    } else {
                        this.mTrackedItems.put(p4.a, p4);
                        this.mSeenItems.put(p4.a, Boolean.FALSE);
                        return 0;
                    }
                }
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private synthetic void lambda$getAnnouncement$0(String p2, xge p3)
    {
        this.mDelegate.recordCardDetailsClick(p2);
        this.mDelegate.openDownloadsPage(this.getOTRProfileIDForTrackedItems(), 13);
        p3.a();
        return;
    }

    private synthetic void lambda$getAnnouncement$1(xge p1)
    {
        this.mDelegate.recordCardXClick();
        return;
    }

    private synthetic void lambda$new$0()
    {
        this.initUI();
        this.getOfflineContentProvider().h(this);
        return;
    }

    private synthetic void lambda$onItemUpdated$0(org.chromium.components.offline_items_collection.OfflineItem p1)
    {
        this.computeNextStepForUpdate(p1);
        return;
    }

    private void updateNotificationMessage(String p6, String p7)
    {
        if (this.mPreAppearanceMode != this.mDelegate.isNightMode()) {
            this.mPreAppearanceMode = this.mDelegate.isNightMode();
            this.mAnnouncement = this.getAnnouncement(p7);
        }
        Object v0_2 = this.mAnnouncement;
        if (v0_2 == null) {
            v0_2 = this.getAnnouncement(p7);
            this.mAnnouncement = v0_2;
        }
        if (v0_2 != null) {
            org.chromium.chrome.browser.download.EdgeDownloadManagerAnnouncementView v1_1 = this.mCompleteDrawable;
            org.chromium.chrome.browser.download.EdgeDownloadManagerAnnouncementView v2_0 = this.mIconState;
            if (v2_0 == 1) {
                v1_1 = this.mInProgressDrawable;
            } else {
                if (v2_0 == 2) {
                    v1_1 = this.mFailedDrawable;
                }
            }
            if (v0_2.g() != null) {
                v0_2.g().setImageDrawable(v1_1);
            }
            Object v0_4 = this.mAnnouncement;
            if (v0_4.g() != null) {
                v0_4.g().a.setText(p6);
            }
            ehe v6_1 = this.mAnnouncement;
            if (v6_1.g() != null) {
                v6_1.g().b.setText(p7);
            }
            ehe v6_4 = this.mIconState;
            if (v6_4 != this.mPreIconState) {
                this.mPreIconState = v6_4;
                if (v6_4 == null) {
                    this.mDelegate.recordDownloadCompletedCardShow();
                } else {
                    if (v6_4 == 1) {
                        this.mDelegate.recordDownloadInProgressShow();
                    } else {
                        if (v6_4 == 2) {
                            this.mDelegate.recordDownloadFailedCardShow();
                        }
                    }
                }
            }
            ehe v6_8 = this.mAnnouncement;
            if (!v6_8.d.b(v6_8.l)) {
                this.mDelegate.recordCardShow();
                this.mAnnouncement.d();
                return;
            } else {
                return;
            }
        } else {
            android.util.Log.e("cr_EdgeUiControllerImpl", "mAnnouncement is nullable");
            return;
        }
    }

    public android.util.Pair getStateResult(org.chromium.components.offline_items_collection.OfflineItem p13)
    {
        int v0_0 = "";
        if ((p13 != 0) && (this.mDelegate.getActivity() != null)) {
            android.app.Activity v1_3 = this.mDelegate.getActivity();
            StringBuilder v2_0 = new StringBuilder();
            StringBuilder v3_1 = new StringBuilder();
            int v4_2 = this.mTrackedItems.values().iterator();
            int v6 = 0;
            int v7 = 0;
            int v8 = 0;
            while (v4_2.hasNext()) {
                String v9_3 = ((org.chromium.components.offline_items_collection.OfflineItem) v4_2.next());
                int v11 = v9_3.y;
                if (v11 == 0) {
                    v7++;
                } else {
                    if (v11 == 2) {
                        v6++;
                        v0_0 = v9_3.c;
                    } else {
                        v8++;
                    }
                }
            }
            if (v6 > 0) {
                this.mHadCompletedDownload = 1;
            }
            if (v7 <= 0) {
                if (v6 <= 0) {
                    if (v8 > 0) {
                        v2_0.append(v8);
                        v2_0.append(" ");
                    }
                    this.mIconState = 2;
                    v2_0.append(v1_3.getResources().getString(tzy.edge_in_app_notification_failed));
                    v3_1.append(p13.c);
                } else {
                    this.mIconState = 0;
                    v2_0.append(v6);
                    v2_0.append(" ");
                    v2_0.append(v1_3.getResources().getString(tzy.edge_in_app_notification_completed));
                    v3_1.append(v0_0);
                    if (v6 > 1) {
                        v3_1.append(v1_3.getResources().getString(tzy.edge_in_app_notification_in_progress_more));
                    }
                }
            } else {
                this.mIconState = 1;
                v2_0.append(v7);
                v2_0.append(" ");
                v2_0.append(v1_3.getResources().getString(tzy.edge_in_app_notification_in_progress));
                v3_1.append(p13.c);
                if (v7 > 1) {
                    v3_1.append(v1_3.getResources().getString(tzy.edge_in_app_notification_in_progress_more));
                }
            }
            return android.util.Pair.create(v2_0.toString(), v3_1.toString());
        } else {
            return android.util.Pair.create("", "");
        }
    }

    public void onItemRemoved(v89 p3)
    {
        boolean v0_1 = new Object[0];
        mjp.a("EdgeUiControllerImpl", v0_1);
        if (this.mSeenItems.containsKey(p3)) {
            this.mTrackedItems.remove(p3);
            return;
        } else {
            return;
        }
    }

    public void onItemUpdated(org.chromium.components.offline_items_collection.OfflineItem p2, org.chromium.components.offline_items_collection.UpdateDelta p3)
    {
        android.os.Handler v3_1 = new Object[0];
        mjp.a("EdgeUiControllerImpl", v3_1);
        if (this.isVisibleToUser(p2)) {
            android.os.Handler v3_3 = this.mHandler;
            hhe v0_2 = new hhe();
            v0_2.a = this;
            v0_2.b = p2;
            v3_3.post(v0_2);
            return;
        } else {
            return;
        }
    }

    public void onItemsAdded(java.util.List p1)
    {
        Object[] v0_1 = new Object[0];
        mjp.a("EdgeUiControllerImpl", v0_1);
        return;
    }
}
