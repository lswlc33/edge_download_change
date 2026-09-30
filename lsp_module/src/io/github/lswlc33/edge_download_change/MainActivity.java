package io.github.lswlc33.edge_download_change;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/** Module home screen: status, settings (interception + download target) and the log entry. */
public class MainActivity extends Activity {

    private LinearLayout statusPillBox;
    private TextView statusDetail;
    private TextView scopeValue;
    private TextView downloaderValue;
    private Button openLsposedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);

        LinearLayout content = UiKit.content(this);
        content.addView(header());
        content.addView(statusCard());
        content.addView(settingsCard());
        content.addView(logCard());
        content.addView(aboutCard());

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.addView(content);
        // targetSdk 35 draws edge-to-edge on Android 15+: keep the content clear of the bars
        UiKit.applySystemBarPadding(scroll, 0, 0, 0, 0);
        setContentView(scroll);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    // ------------------------------------------------------------------ sections

    private View header() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.HORIZONTAL);
        box.setGravity(Gravity.CENTER_VERTICAL);
        box.setPadding(0, UiKit.dp(this, 14), 0, UiKit.dp(this, 2));

        box.addView(UiKit.monogram(this));

        LinearLayout labels = new LinearLayout(this);
        labels.setOrientation(LinearLayout.VERTICAL);
        TextView title = UiKit.title(this, getString(R.string.app_name));
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        labels.addView(title);
        TextView subtitle = UiKit.hint(this, getString(R.string.main_subtitle));
        subtitle.setPadding(0, UiKit.dp(this, 2), 0, 0);
        labels.addView(subtitle);
        box.addView(labels);
        return box;
    }

    private View statusCard() {
        LinearLayout card = UiKit.card(this);
        card.addView(UiKit.sectionTitle(this, getString(R.string.section_status)));

        statusPillBox = new LinearLayout(this);
        statusPillBox.setOrientation(LinearLayout.HORIZONTAL);
        card.addView(statusPillBox);

        statusDetail = UiKit.hint(this, "");
        statusDetail.setPadding(0, UiKit.dp(this, 10), 0, 0);
        card.addView(statusDetail);

        card.addView(UiKit.divider(this));

        scopeValue = UiKit.hint(this, "");
        card.addView(scopeValue);

        Button refresh = UiKit.button(this, getString(R.string.btn_refresh), false);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                toast(getString(R.string.toast_refreshed));
            }
        });

        openLsposedButton = UiKit.button(this, getString(R.string.btn_open_lsposed), true);
        openLsposedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LsposedLauncher.open(MainActivity.this)) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.manager_missing_title)
                            .setMessage(R.string.manager_missing_body)
                            .setPositiveButton(R.string.btn_ok, null)
                            .show();
                }
            }
        });
        card.addView(UiKit.buttonRow(this, refresh, openLsposedButton));
        return card;
    }

    private View settingsCard() {
        LinearLayout card = UiKit.card(this);
        card.addView(UiKit.sectionTitle(this, getString(R.string.section_settings)));

        LinearLayout switchRow = new LinearLayout(this);
        switchRow.setOrientation(LinearLayout.HORIZONTAL);
        switchRow.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout labels = new LinearLayout(this);
        labels.setOrientation(LinearLayout.VERTICAL);
        labels.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        labels.addView(UiKit.body(this, getString(R.string.settings_intercept)));
        labels.addView(UiKit.hint(this, getString(R.string.settings_intercept_hint)));
        switchRow.addView(labels);

        final Switch toggle = new Switch(this);
        toggle.setChecked(ModulePrefs.isEnabled(this));
        toggle.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(android.widget.CompoundButton buttonView, boolean isChecked) {
                ModulePrefs.setEnabled(MainActivity.this, isChecked);
                toast(getString(isChecked ? R.string.toast_intercept_on : R.string.toast_intercept_off));
            }
        });
        switchRow.addView(toggle);
        card.addView(switchRow);

        card.addView(UiKit.divider(this));

        LinearLayout targetRow = new LinearLayout(this);
        targetRow.setOrientation(LinearLayout.HORIZONTAL);
        targetRow.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout targetLabels = new LinearLayout(this);
        targetLabels.setOrientation(LinearLayout.VERTICAL);
        targetLabels.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        targetLabels.addView(UiKit.body(this, getString(R.string.settings_target)));
        downloaderValue = UiKit.hint(this, "");
        targetLabels.addView(downloaderValue);
        targetRow.addView(targetLabels);

        Button choose = UiKit.button(this, getString(R.string.btn_choose), false);
        LinearLayout.LayoutParams chooseParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        chooseParams.leftMargin = UiKit.dp(this, 10);
        choose.setLayoutParams(chooseParams);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDownloaderPicker();
            }
        });
        targetRow.addView(choose);
        card.addView(targetRow);

        card.addView(UiKit.hint(this, getString(R.string.settings_target_hint)));
        return card;
    }

    private View logCard() {
        LinearLayout card = UiKit.card(this);
        card.addView(UiKit.sectionTitle(this, getString(R.string.section_log)));

        Button open = UiKit.button(this, getString(R.string.log_open), true);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogActivity.class));
            }
        });

        Button clear = UiKit.button(this, getString(R.string.log_clear), false);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogActivity.clearLog(MainActivity.this);
                toast(getString(R.string.toast_log_cleared));
            }
        });
        card.addView(UiKit.buttonRow(this, open, clear));
        card.addView(UiKit.hint(this, getString(R.string.log_hint)));
        return card;
    }

    private View aboutCard() {
        LinearLayout card = UiKit.card(this);
        card.addView(UiKit.sectionTitle(this, getString(R.string.section_about)));
        card.addView(UiKit.hint(this, getString(R.string.about_text)));
        return card;
    }

    // ------------------------------------------------------------------ state

    private void refresh() {
        boolean active = ModulePrefs.isActive(this);
        long last = ModulePrefs.lastReportTime(this);
        int statusColor = active ? UiKit.color(this, R.color.ok) : UiKit.color(this, R.color.bad);

        if (statusPillBox != null) {
            statusPillBox.removeAllViews();
            statusPillBox.addView(UiKit.pill(this,
                    getString(active ? R.string.status_active : R.string.status_inactive), statusColor));
        }
        if (statusDetail != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.status_version, moduleVersion())).append('\n');
            if (ModulePrefs.everReported(this)) {
                String process = ModulePrefs.lastProcess(this);
                sb.append(getString(R.string.status_last_inject, formatTime(last),
                        process == null || process.length() == 0 ? "-" : process)).append('\n');
                String framework = ModulePrefs.lastFramework(this);
                if (framework != null && framework.length() > 0) {
                    sb.append(getString(R.string.status_framework, framework)).append('\n');
                }
                String state = ModulePrefs.lastState(this);
                if ("hook_failed".equals(state)) {
                    sb.append(getString(R.string.status_hook_failed));
                } else if ("inject".equals(state)) {
                    sb.append(getString(R.string.status_hook_ok));
                }
            } else {
                sb.append(getString(R.string.status_never_title)).append('\n')
                        .append(getString(R.string.status_step1)).append('\n')
                        .append(getString(R.string.status_step2)).append('\n')
                        .append(getString(R.string.status_step3)).append('\n')
                        .append(getString(R.string.status_step4));
            }
            if (active && !ModulePrefs.isEnabled(this)) {
                sb.append('\n').append(getString(R.string.status_disabled_hint));
            }
            statusDetail.setText(sb.toString());
        }
        if (scopeValue != null) {
            scopeValue.setText(R.string.scope_text);
        }
        if (downloaderValue != null) {
            String id = ModulePrefs.downloader(this);
            String custom = ModulePrefs.customPackage(this);
            String label = Downloaders.labelOf(id, custom);
            Downloaders.Entry entry = Downloaders.byId(id);
            String pkg = entry != null ? entry.pkg
                    : (Downloaders.ID_CUSTOM.equals(id) && custom.length() > 0 ? custom : null);
            if (pkg != null && pkg.length() > 0) {
                label += getString(Downloaders.isInstalled(this, pkg)
                        ? R.string.picker_installed : R.string.picker_missing);
            }
            downloaderValue.setText(getString(R.string.settings_target_current, label));
        }
        if (openLsposedButton != null) {
            // When not activated, opening the manager is the primary action.
            UiKit.restyle(this, openLsposedButton, !active);
        }
    }

    private String moduleVersion() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            return info.versionName + " (" + info.versionCode + ")";
        } catch (Throwable t) {
            return BuildInfo.VERSION;
        }
    }

    private static String formatTime(long millis) {
        if (millis <= 0) return "-";
        return new SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(millis));
    }

    // ------------------------------------------------------------------ downloader picker

    private void showDownloaderPicker() {
        final List<Downloaders.Entry> entries = Downloaders.all();
        final List<String> labels = new ArrayList<String>();
        final List<String> ids = new ArrayList<String>();
        String current = ModulePrefs.downloader(this);

        for (Downloaders.Entry entry : entries) {
            String label = entry.label();
            if (entry.pkg != null) {
                label += getString(Downloaders.isInstalled(this, entry.pkg)
                        ? R.string.picker_installed : R.string.picker_missing);
            }
            if (entry.id.equals(current)) label = "✓ " + label;
            labels.add(label);
            ids.add(entry.id);
        }
        String custom = ModulePrefs.customPackage(this);
        labels.add(getString(R.string.picker_custom,
                custom.length() == 0 ? getString(R.string.picker_custom_unset) : custom));
        ids.add(Downloaders.ID_CUSTOM);

        new AlertDialog.Builder(this)
                .setTitle(R.string.picker_title)
                .setItems(labels.toArray(new String[0]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = ids.get(which);
                        if (Downloaders.ID_CUSTOM.equals(id)) {
                            askCustomPackage();
                        } else {
                            ModulePrefs.setDownloader(MainActivity.this, id);
                            refresh();
                            toast(getString(R.string.toast_target_changed));
                        }
                    }
                })
                .setNegativeButton(R.string.btn_cancel, null)
                .show();
    }

    private void askCustomPackage() {
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint(R.string.custom_hint);
        input.setText(ModulePrefs.customPackage(this));

        new AlertDialog.Builder(this)
                .setTitle(R.string.custom_title)
                .setView(input)
                .setPositiveButton(R.string.btn_save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String pkg = input.getText().toString().trim();
                        ModulePrefs.setCustomPackage(MainActivity.this, pkg);
                        if (pkg.length() > 0) {
                            ModulePrefs.setDownloader(MainActivity.this, Downloaders.ID_CUSTOM);
                            if (!Downloaders.isInstalled(MainActivity.this, pkg)) {
                                toast(getString(R.string.toast_custom_keep));
                            }
                        }
                        refresh();
                    }
                })
                .setNegativeButton(R.string.btn_cancel, null)
                .show();
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
