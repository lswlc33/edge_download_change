package com.edge.systemdownload;

import android.content.Context;
import android.content.SharedPreferences;

/** Module app side storage: user settings and the status reported by the injected side. */
final class ModulePrefs {

    private static final String PREFS_STATUS = "status";
    private static final String KEY_LAST_TIME = "last_time";
    private static final String KEY_LAST_PROCESS = "last_process";
    private static final String KEY_LAST_FRAMEWORK = "last_framework";
    private static final String KEY_LAST_STATE = "last_state";
    private static final String KEY_EVER = "ever_reported";

    private ModulePrefs() {}

    static SharedPreferences settings(Context context) {
        return context.getSharedPreferences(BuildInfo.PREFS_SETTINGS, Context.MODE_PRIVATE);
    }

    static boolean isEnabled(Context context) {
        return settings(context).getBoolean(BuildInfo.KEY_ENABLED, true);
    }

    static void setEnabled(Context context, boolean enabled) {
        settings(context).edit().putBoolean(BuildInfo.KEY_ENABLED, enabled).apply();
    }

    static String downloader(Context context) {
        return settings(context).getString(BuildInfo.KEY_DOWNLOADER, Downloaders.ID_SYSTEM);
    }

    static void setDownloader(Context context, String id) {
        settings(context).edit().putString(BuildInfo.KEY_DOWNLOADER, id).apply();
    }

    static String customPackage(Context context) {
        return settings(context).getString(BuildInfo.KEY_CUSTOM_PACKAGE, "");
    }

    static void setCustomPackage(Context context, String pkg) {
        settings(context).edit().putString(BuildInfo.KEY_CUSTOM_PACKAGE, pkg == null ? "" : pkg).apply();
    }

    static void recordStatus(Context context, String process, String framework, String state) {
        settings(context).edit()
                .putLong(KEY_LAST_TIME, System.currentTimeMillis())
                .putString(KEY_LAST_PROCESS, process)
                .putString(KEY_LAST_FRAMEWORK, framework)
                .putString(KEY_LAST_STATE, state)
                .putBoolean(KEY_EVER, true)
                .apply();
    }

    static long lastReportTime(Context context) {
        return settings(context).getLong(KEY_LAST_TIME, 0);
    }

    static String lastProcess(Context context) {
        return settings(context).getString(KEY_LAST_PROCESS, "");
    }

    static String lastFramework(Context context) {
        return settings(context).getString(KEY_LAST_FRAMEWORK, "");
    }

    static String lastState(Context context) {
        return settings(context).getString(KEY_LAST_STATE, "");
    }

    static boolean everReported(Context context) {
        return settings(context).getBoolean(KEY_EVER, false);
    }

    /** A heartbeat is considered live when it is not older than this. */
    static boolean isActive(Context context) {
        long last = lastReportTime(context);
        return last > 0 && System.currentTimeMillis() - last < 5 * 60 * 1000L;
    }
}
