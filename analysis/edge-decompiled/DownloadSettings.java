// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/download/settings/DownloadSettings
package org.chromium.chrome.browser.download.settings;
public class DownloadSettings extends org.chromium.chrome.browser.settings.ChromeBaseSettingsFragment implements jhx {
    public static final vcd r;
    public org.chromium.chrome.browser.download.settings.DownloadLocationPreference n;
    public org.chromium.components.browser_ui.settings.ChromeSwitchPreference o;
    public org.chromium.components.browser_ui.settings.ChromeSwitchPreference p;
    public final bxt q;

    static DownloadSettings()
    {
        org.chromium.chrome.browser.download.settings.DownloadSettings.r = new vcd(org.chromium.chrome.browser.download.settings.DownloadSettings.getName(), zzy.download_preferences, 1);
        return;
    }

    public DownloadSettings()
    {
        this.q = cxt.a();
        return;
    }

    public final void S1(android.os.Bundle p3, String p4)
    {
        String v3_18;
        this.q.set(this.getString(uzy.menu_downloads));
        wp20.a(this, zzy.download_preferences);
        String v3_1 = ((org.chromium.components.browser_ui.settings.ChromeSwitchPreference) this.P1("location_prompt_enabled"));
        this.o = v3_1;
        v3_1.setManagedPreferenceDelegate(new ucd(this, this.j));
        this.j.getClass();
        this.o.setOnPreferenceChangeListener(this);
        String v3_7 = ((org.chromium.chrome.browser.download.settings.DownloadLocationPreference) this.P1("location_change"));
        this.n = v3_7;
        v3_7.h = new pad(this.j);
        v3_7.g.b();
        this.p = ((org.chromium.components.browser_ui.settings.ChromeSwitchPreference) this.P1("auto_open_pdf_enabled"));
        this.j.getClass();
        this.p.setOnPreferenceChangeListener(this);
        if (org.chromium.chrome.browser.download.MimeUtils.a().size() != 1) {
            v3_18 = this.getActivity().getString(tzy.auto_open_pdf_enabled_description);
        } else {
            Object[] v0_3;
            String v3_19 = this.getActivity();
            Object[] v0_2 = org.chromium.chrome.browser.download.MimeUtils.a();
            if (v0_2.size() <= 0) {
                v0_3 = 0;
            } else {
                v0_3 = ((android.content.pm.ResolveInfo) v0_2.get(0)).loadLabel(af9.a.getPackageManager()).toString();
            }
            v3_18 = v3_19.getString(tzy.auto_open_pdf_enabled_with_app_description, new Object[] {v0_3}));
        }
        this.p.setSummaryOn(v3_18);
        return;
    }

    public final void U1(erc p4)
    {
        if (!(p4 instanceof org.chromium.chrome.browser.download.settings.DownloadLocationPreference)) {
            super.U1(p4);
            return;
        } else {
            org.chromium.chrome.browser.download.settings.DownloadLocationPreferenceDialog v0_2 = new org.chromium.chrome.browser.download.settings.DownloadLocationPreferenceDialog();
            android.os.Bundle v1_1 = new android.os.Bundle(1);
            v1_1.putString("key", ((org.chromium.chrome.browser.download.settings.DownloadLocationPreference) p4).getKey());
            v0_2.setArguments(v1_1);
            v0_2.setTargetFragment(this, 0);
            v0_2.show(this.getParentFragmentManager(), "DownloadLocationPreferenceDialog");
            return;
        }
    }

    public final xbs b()
    {
        return this.q;
    }

    public final boolean b1(androidx.preference.Preference p3, Object p4)
    {
        if (!"location_prompt_enabled".equals(p3.getKey())) {
            if ("auto_open_pdf_enabled".equals(p3.getKey())) {
                d3b0.b(this.j).g("download.auto_open_pdf_enabled", ((Boolean) p4).booleanValue());
            }
        } else {
            String v4_4 = this.j;
            if (!((Boolean) p4).booleanValue()) {
                org.chromium.chrome.browser.download.DownloadDialogBridge.g(2, v4_4);
                return 1;
            } else {
                if (org.chromium.chrome.browser.download.DownloadDialogBridge.b(v4_4) != 0) {
                    org.chromium.chrome.browser.download.DownloadDialogBridge.g(1, this.j);
                    return 1;
                }
            }
        }
        return 1;
    }

    public final void onStart()
    {
        super.onStart();
        this.n.k();
        if (!d3b0.b(this.j.f()).f("download.prompt_for_download")) {
            org.chromium.components.browser_ui.settings.ChromeSwitchPreference v0_4;
            if (org.chromium.chrome.browser.download.DownloadDialogBridge.b(this.j) == 2) {
                v0_4 = 0;
            } else {
                v0_4 = 1;
            }
            this.o.setChecked(v0_4);
            this.o.setEnabled(1);
        } else {
            this.o.setChecked(d3b0.b(this.j.f()).b("download.prompt_for_download"));
        }
        this.j.getClass();
        this.p.setChecked(d3b0.b(this.j).b("download.auto_open_pdf_enabled"));
        this.p.setEnabled(1);
        return;
    }

    public final String s()
    {
        return "downloads";
    }
}
