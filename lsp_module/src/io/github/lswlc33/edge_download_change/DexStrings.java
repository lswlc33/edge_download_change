package io.github.lswlc33.edge_download_change;

import android.content.Context;

import java.nio.charset.StandardCharsets;

/**
 * Resolves Edge's download action / extra strings by scanning the string constants of
 * the host APK's dex files at runtime.
 *
 * Chromium keeps these constants stable for years and they all carry the distinctive
 * "org.chromium.chrome.browser.download." prefix (they are the strings behind the
 * download notification buttons). Reading them out of the dex - instead of hardcoding -
 * keeps the cancel channel working across Edge updates.
 */
final class DexStrings {

    private static final String PREFIX = "org.chromium.chrome.browser.download.";

    // slot indexes in the result array
    static final int ACTION_CANCEL = 0;
    static final int EXTRA_ID = 1;
    static final int EXTRA_NAMESPACE = 2;
    static final int EXTRA_OTR_PROFILE_ID = 3;
    static final int EXTRA_IS_OTR = 4;

    // fallbacks if the scan cannot read the dex files
    private static final String[] DEFAULTS = {
            PREFIX + "DOWNLOAD_CANCEL",
            PREFIX + "DownloadContentId_Id",
            PREFIX + "DownloadContentId_Namespace",
            PREFIX + "OTR_PROFILE_ID",
            PREFIX + "IS_OFF_THE_RECORD",
    };

    // wanted tails under PREFIX, aligned with DEFAULTS
    private static final String[] TAILS = {
            "DOWNLOAD_CANCEL",
            "DownloadContentId_Id",
            "DownloadContentId_Namespace",
            "OTR_PROFILE_ID",
            "IS_OFF_THE_RECORD",
    };

    private static volatile String[] sCached;
    private static volatile boolean sResolving;

    private DexStrings() {}

    /**
     * Returns the download action / extra keys. Resolution happens on a background thread:
     * the first caller gets {@link #DEFAULTS} (the same values Chromium has used for
     * years), later calls get the values read from the dex.
     */
    static String[] resolve(final Context context) {
        String[] cached = sCached;
        if (cached != null) return cached;
        if (context != null && !sResolving) {
            sResolving = true;
            Bg.run(new Runnable() {
                @Override
                public void run() {
                    String[] result = scan(context);
                    sCached = result;
                    DownloadHooks.log(4, "resolved download strings: action=" + result[ACTION_CANCEL]);
                }
            });
        }
        return DEFAULTS;
    }

    private static String[] scan(Context context) {
        final String[] result = DEFAULTS.clone();
        final boolean[] resolved = new boolean[TAILS.length];
        DexFileReader.forEach(context, new DexFileReader.DexConsumer() {
            @Override
            public void accept(byte[] dex) {
                scanDex(result, resolved, dex);
            }
        });
        return result;
    }

    /** Finds every occurrence of PREFIX in the raw dex bytes and extends to the NUL terminator. */
    private static void scanDex(String[] result, boolean[] resolved, byte[] data) {
        byte[] prefix = PREFIX.getBytes(StandardCharsets.US_ASCII);
        int at = indexOf(data, prefix, 0);
        while (at >= 0) {
            int end = at + prefix.length;
            while (end < data.length && data[end] != 0 && data[end] > 0x20 && data[end] < 0x7F) end++;
            if (end < data.length && data[end] == 0) {
                String found = new String(data, at, end - at, StandardCharsets.US_ASCII);
                for (int slot = 0; slot < TAILS.length; slot++) {
                    if (!resolved[slot] && found.endsWith(TAILS[slot])
                            && found.length() == PREFIX.length() + TAILS[slot].length()) {
                        result[slot] = found;
                        resolved[slot] = true;
                    }
                }
            }
            at = indexOf(data, prefix, at + prefix.length);
        }
    }

    private static int indexOf(byte[] haystack, byte[] needle, int from) {
        if (needle.length == 0 || haystack.length < needle.length) return -1;
        int last = haystack.length - needle.length;
        for (int i = Math.max(0, from); i <= last; i++) {
            int j = 0;
            while (j < needle.length && haystack[i + j] == needle[j]) j++;
            if (j == needle.length) return i;
        }
        return -1;
    }
}
