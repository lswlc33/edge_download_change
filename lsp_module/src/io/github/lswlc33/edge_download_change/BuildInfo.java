package io.github.lswlc33.edge_download_change;

/** Shared constants for the module app and the injected (Edge) side. */
final class BuildInfo {

    /** Module version, shown in the UI and reported by the injected side. */
    static final String VERSION = "2.12";

    /** SharedPreferences file used for settings and as remote-preference fallback. */
    static final String PREFS_SETTINGS = "settings";
    static final String KEY_ENABLED = "intercept_enabled";
    static final String KEY_DOWNLOADER = "downloader_id";
    static final String KEY_CUSTOM_PACKAGE = "downloader_custom_package";

    /** ContentProvider channel between the module app and the injected process. */
    static final String AUTHORITY = "io.github.lswlc33.edge_download_change.settings";
    static final String METHOD_SETTINGS = "settings";
    static final String METHOD_REPORT = "report";

    /** Log file name inside the module app's files directory. */
    static final String LOG_FILE = "module.log";
    static final int LOG_MAX_BYTES = 96 * 1024;

    private BuildInfo() {}
}
