package io.github.lswlc33.edge_download_change;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/** Shows the log written by the injected Edge process (and cleared on demand). */
public class LogActivity extends Activity {

    private ScrollView scroll;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.log_title);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(UiKit.dp(this, 16), UiKit.dp(this, 12), UiKit.dp(this, 16), UiKit.dp(this, 12));

        Button copy = UiKit.button(this, getString(R.string.log_copy_all), false);
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = readLog(LogActivity.this);
                if (TextUtils.isEmpty(content)) {
                    toast(getString(R.string.toast_no_log));
                    return;
                }
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard != null) {
                    clipboard.setPrimaryClip(ClipData.newPlainText("edge_download_change log", content));
                }
                toast(getString(R.string.toast_log_copied));
            }
        });

        Button refresh = UiKit.button(this, getString(R.string.log_refresh), true);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });

        Button clear = UiKit.button(this, getString(R.string.log_clear_short), false);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearLog(LogActivity.this);
                load();
                toast(getString(R.string.toast_log_cleared));
            }
        });

        root.addView(UiKit.buttonRow(this, copy, refresh, clear));

        TextView header = UiKit.hint(this, getString(R.string.log_hint));
        header.setPadding(0, UiKit.dp(this, 10), 0, 0);
        root.addView(header);

        text = new TextView(this);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        text.setTypeface(Typeface.MONOSPACE);
        text.setTextColor(Color.parseColor("#C9D1D9"));
        text.setTextIsSelectable(true);
        text.setPadding(UiKit.dp(this, 10), UiKit.dp(this, 10), UiKit.dp(this, 10), UiKit.dp(this, 10));
        UiKit.terminalBg(this, text);

        scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.addView(text);
        root.addView(scroll, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));

        setContentView(root);
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }

    private void load() {
        String content = readLog(this);
        if (TextUtils.isEmpty(content)) {
            text.setText(R.string.log_empty);
            return;
        }
        text.setText(content);
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    static String readLog(Context context) {
        File file = new File(context.getFilesDir(), BuildInfo.LOG_FILE);
        if (!file.exists()) return "";
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) > 0) out.write(buffer, 0, read);
            return out.toString("UTF-8");
        } catch (Throwable t) {
            return "";
        } finally {
            try {
                if (in != null) in.close();
            } catch (Throwable ignored) {
            }
        }
    }

    static void clearLog(Context context) {
        File file = new File(context.getFilesDir(), BuildInfo.LOG_FILE);
        try {
            FileOutputStream out = new FileOutputStream(file, false);
            out.close();
        } catch (Throwable ignored) {
        }
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
