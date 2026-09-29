package io.github.lswlc33.edge_download_change;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Channel between the injected Edge process and this app.
 *
 *  - {@code call("settings")} returns the current settings (the injected side reads them
 *    live, so a change applies without restarting Edge).
 *  - {@code call("report", ...)} receives status heartbeats and log lines from the
 *    injected side.
 *
 * Only non-sensitive data is exposed; the log itself can never be read through the
 * provider (it is stored locally and shown by LogActivity).
 */
public class ModuleProvider extends ContentProvider {

    public static final String METHOD_SETTINGS = BuildInfo.METHOD_SETTINGS;
    public static final String METHOD_REPORT = BuildInfo.METHOD_REPORT;

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        Context context = getContext();
        if (context == null || method == null) return null;
        if (METHOD_SETTINGS.equals(method)) {
            Bundle result = new Bundle();
            result.putBoolean("enabled", ModulePrefs.isEnabled(context));
            result.putString("downloader", ModulePrefs.downloader(context));
            result.putString("custom", ModulePrefs.customPackage(context));
            result.putString("version", BuildInfo.VERSION);
            return result;
        }
        if (METHOD_REPORT.equals(method)) {
            String event = extras == null ? "log" : extras.getString("event", "log");
            String text = extras == null ? null : extras.getString("text");
            String process = extras == null ? null : extras.getString("process");
            String framework = extras == null ? null : extras.getString("framework");
            if ("heartbeat".equals(event) || "inject".equals(event) || "hook_failed".equals(event)) {
                ModulePrefs.recordStatus(context, process, framework, event);
            }
            if (!TextUtils.isEmpty(text)) {
                appendLog(context, text);
            }
            // The response doubles as a settings push so the injected side stays in sync
            // without extra IPC.
            return settingsBundle(context);
        }
        return null;
    }

    private static Bundle settingsBundle(Context context) {
        Bundle result = new Bundle();
        result.putBoolean("enabled", ModulePrefs.isEnabled(context));
        result.putString("downloader", ModulePrefs.downloader(context));
        result.putString("custom", ModulePrefs.customPackage(context));
        result.putString("version", BuildInfo.VERSION);
        return result;
    }

    private static void appendLog(Context context, String text) {
        File file = new File(context.getFilesDir(), BuildInfo.LOG_FILE);
        try {
            if (file.exists() && file.length() > BuildInfo.LOG_MAX_BYTES) {
                trimLog(file);
            }
            FileOutputStream out = new FileOutputStream(file, true);
            try {
                out.write(text.getBytes("UTF-8"));
                if (!text.endsWith("\n")) out.write('\n');
            } finally {
                out.close();
            }
        } catch (Throwable ignored) {
        }
    }

    /** Keeps the newest half of an oversized log file. */
    private static void trimLog(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        try {
            long keep = Math.max(1024, file.length() / 2);
            byte[] buffer = new byte[(int) keep];
            raf.seek(file.length() - keep);
            raf.readFully(buffer);
            raf.setLength(0);
            raf.write(buffer);
        } finally {
            raf.close();
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) {
        return null;
    }
}
