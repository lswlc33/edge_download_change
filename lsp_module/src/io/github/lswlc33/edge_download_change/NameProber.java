package io.github.lswlc33.edge_download_change;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Resolves the real download file name when the item was created before Edge's redirect
 * chain played out.
 *
 * Interception cancels Edge's download at item-creation time, so Edge never follows its
 * redirects and the URL we see is frequently an opaque link (an id, a token) whose real
 * name only exists in the Content-Disposition header of the final response - or in the
 * query string of a redirect target. This prober replays just the redirect chain with a
 * 1-byte range request (no body download), on the shared background thread, and refines
 * the pending download's name with what it finds.
 */
final class NameProber {

    /** How long the whole chain (all hops together) may take. */
    private static final int TIMEOUT_MS = 6_000;
    private static final int MAX_HOPS = 8;

    private NameProber() {}

    /**
     * Probes the URL when the known name is empty or extension-less (an id, an opaque
     * token, "download"): exactly the cases where the redirect chain likely holds the
     * real name. Safe to call from any thread; the work runs on {@link Bg}.
     */
    static void probeIfNeeded(final PendingDownload download) {
        if (download == null) return;
        if (DownloadHooks.hasExtension(download.fileName())) return;
        Bg.run(new Runnable() {
            @Override
            public void run() {
                probe(download);
            }
        });
    }

    private static void probe(PendingDownload download) {
        try {
            Result result = follow(download.url);
            if (result == null) return;
            String name = result.name;
            if (name.length() > 0 && !DownloadHooks.hasExtension(name)
                    && download.mime.length() > 0) {
                String ext = DownloadHooks.extensionFromMime(download.mime);
                if (ext.length() > 0) name = name + "." + ext;
            }
            if (name.length() > 0) {
                name = DownloadHooks.sanitizePathChars(name);
                DownloadHooks.log(4, "probed file name: '" + name + "' <- " + result.source);
                download.setFileName(name);
            }
        } catch (Throwable t) {
            DownloadHooks.log(4, "file name probe failed: " + t);
        }
    }

    private static Result follow(String url) {
        String current = url;
        for (int hop = 0; hop < MAX_HOPS && current != null; hop++) {
            HttpURLConnection connection = null;
            try {
                connection = open(current);
                int code = connection.getResponseCode();
                String location = connection.getHeaderField("Location");
                if (code >= 300 && code < 400 && location != null && location.length() > 0) {
                    String next = new URL(new URL(current), location).toString();
                    // Names found on the way to the final hop still count.
                    String viaName = nameFromHeaders(connection, current);
                    if (viaName.length() == 0) viaName = DownloadHooks.fileNameFromQuery(next);
                    Result hopResult = follow(next);
                    if (hopResult != null && hopResult.name.length() > 0) return hopResult;
                    return viaName.length() > 0 ? new Result(viaName, "redirect") : null;
                }
                if (code >= 200 && code < 400) {
                    String name = nameFromHeaders(connection, current);
                    if (name.length() > 0) return new Result(name, "header");
                    // The response body starts with the disposition for some servers even
                    // without the header on a HEAD reply - a GET with a range covers that.
                    if ("HEAD".equals(connection.getRequestMethod())) {
                        connection.disconnect();
                        connection = open(current);
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("Range", "bytes=0-0");
                        code = connection.getResponseCode();
                        name = nameFromHeaders(connection, current);
                        if (name.length() > 0) return new Result(name, "header(get)");
                    }
                }
                return null;
            } catch (IOException t) {
                DownloadHooks.log(4, "probe connection failed for " + current + ": " + t);
                return null;
            } finally {
                if (connection != null) connection.disconnect();
            }
        }
        return null;
    }

    private static HttpURLConnection open(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("HEAD");
        connection.setConnectTimeout(TIMEOUT_MS);
        connection.setReadTimeout(TIMEOUT_MS);
        connection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 Chrome/153 Mobile");
        return connection;
    }

    private static String nameFromHeaders(HttpURLConnection connection, String url) {
        String disposition = connection.getHeaderField("Content-Disposition");
        if (disposition != null && disposition.length() > 0) {
            String name = DownloadHooks.dispositionFileName(decodeHeader(disposition));
            if (name.length() > 0) return name;
        }
        return DownloadHooks.fileNameFromQuery(url);
    }

    private static String decodeHeader(String value) {
        return DownloadHooks.looksPercentEncoded(value) ? DownloadHooks.decode(value) : value;
    }

    private static final class Result {
        final String name;
        final String source;

        Result(String name, String source) {
            this.name = name;
            this.source = source;
        }
    }
}
