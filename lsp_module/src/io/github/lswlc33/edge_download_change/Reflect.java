package io.github.lswlc33.edge_download_change;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Reflection helpers for reading fields of Edge's obfuscated download classes.
 *
 * Edge 153 ships the Chromium download classes with JNI-kept method names but
 * single-letter field names (a, b, c, ...). We look up fields by their known name
 * first and fall back to type/value based scanning so small Edge updates do not
 * break the module.
 */
final class Reflect {

    private Reflect() {}

    static Field findField(Class<?> type, String preferredName, Class<?> fieldType) {
        if (preferredName != null) {
            try {
                Field f = type.getDeclaredField(preferredName);
                if (fieldType == null || fieldType.isAssignableFrom(f.getType())) return f;
            } catch (Throwable ignored) {
            }
        }
        if (fieldType != null) {
            for (Field f : type.getDeclaredFields()) {
                if (fieldType.isAssignableFrom(f.getType())) return f;
            }
        }
        return null;
    }

    static Object read(Field f, Object target) {
        if (f == null || target == null) return null;
        try {
            f.setAccessible(true);
            return f.get(target);
        } catch (Throwable t) {
            return null;
        }
    }

    static Object get(Object target, String preferredName) {
        if (target == null) return null;
        return read(findField(target.getClass(), preferredName, null), target);
    }

    static Field gurlField(Class<?> infoType, String preferredName) {
        try {
            Class<?> gurl = Class.forName("org.chromium.url.GURL", false, infoType.getClassLoader());
            return findField(infoType, preferredName, gurl);
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Reads the URL string out of an {@code org.chromium.url.GURL} instance.
     *
     * Edge 153 stores the spec in the String field {@code a}; its {@code toString()} is the
     * inherited Object implementation (an identity string such as
     * "org.chromium.url.GURL@1a2b3c"), which must never be used as the URL.
     */
    static String urlString(Object gurl) {
        if (gurl == null) return "";
        String spec = string(get(gurl, "a"));
        if (spec.length() > 0 && !looksLikeIdentityString(spec)) return spec;
        // Fallbacks for builds where the field layout differs: any no-arg String getter
        // that returns something URL shaped.
        try {
            for (Method m : gurl.getClass().getMethods()) {
                if (m.getParameterTypes().length != 0 || m.getReturnType() != String.class) continue;
                Object value = m.invoke(gurl);
                if (value instanceof String && isHttp((String) value)) return (String) value;
            }
        } catch (Throwable ignored) {
        }
        return looksLikeIdentityString(spec) ? "" : spec;
    }

    /** Filters out Java identity strings ("pkg.Class@1a2b3c") produced by toString(). */
    private static boolean looksLikeIdentityString(String value) {
        int at = value.lastIndexOf('@');
        if (at <= 0 || at != value.length() - 8) return false;
        for (int i = at + 1; i < value.length(); i++) {
            char ch = value.charAt(i);
            if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f')) return false;
        }
        return value.indexOf('/') < 0 && value.indexOf('.') < at;
    }

    /** Numeric field access (e.g. DownloadInfo.j = bytes already received). */
    static long longValue(Object target, String preferredName) {
        Object value = get(target, preferredName);
        return value instanceof Number ? ((Number) value).longValue() : -1L;
    }

    static String string(Object o) {
        return o instanceof String ? (String) o : "";
    }

    static String callString(Object target, String methodName) {
        if (target == null) return "";
        try {
            Method m = target.getClass().getMethod(methodName);
            Object r = m.invoke(target);
            return r instanceof String ? (String) r : "";
        } catch (Throwable t) {
            return "";
        }
    }

    /**
     * Picks a String field from a DownloadInfo-like object.
     *
     * @param kind one of "file", "mime", "path"; used for the heuristic fallback when the
     *             expected field name is not present in another Edge build.
     */
    static String pickString(Object info, String preferredName, String kind) {
        if (info == null) return "";
        Class<?> c = info.getClass();
        Field f = findField(c, preferredName, String.class);
        String v = string(read(f, info));
        if (looksLike(v, kind)) return v;
        for (Field candidate : c.getDeclaredFields()) {
            if (candidate.getType() != String.class) continue;
            String candidateValue = string(read(candidate, info));
            if (looksLike(candidateValue, kind)) return candidateValue;
        }
        return "";
    }

    private static boolean looksLike(String value, String kind) {
        if (value == null || value.length() == 0) return false;
        if ("mime".equals(kind)) return value.indexOf('/') > 0 && value.indexOf("://") < 0 && value.indexOf(' ') < 0;
        if ("path".equals(kind)) return value.charAt(0) == '/';
        // "file": a plausible file name - has an extension, no path separators, not a URL.
        return value.indexOf('/') < 0 && value.indexOf("://") < 0 && value.indexOf('.') > 0;
    }

    /** Picks the download URL out of the GURL fields of a DownloadInfo-like object. */
    static String pickUrl(Object info) {
        if (info == null) return "";
        Class<?> c = info.getClass();
        String preferred = urlString(read(gurlField(c, "a"), info));
        if (isHttp(preferred)) return preferred;
        for (Field f : c.getDeclaredFields()) {
            if (!"org.chromium.url.GURL".equals(f.getType().getName())) continue;
            String spec = urlString(read(f, info));
            if (isHttp(spec)) return spec;
        }
        return "";
    }

    /**
     * Picks the original (pre-redirect) download URL from the GURL field {@code i}.
     *
     * The final URL ({@code a}) is often a signed CDN/blob address whose last path segment is
     * an opaque id (GitHub release assets, OSS/S3 buckets); the original URL keeps the
     * user-visible file name and is therefore a much better source for the download name.
     */
    static String pickOriginalUrl(Object info) {
        if (info == null) return "";
        try {
            Class<?> gurl = Class.forName("org.chromium.url.GURL", false, info.getClass().getClassLoader());
            Field f = info.getClass().getDeclaredField("i");
            if (!gurl.isAssignableFrom(f.getType())) return "";
            String spec = urlString(read(f, info));
            return isHttp(spec) ? spec : "";
        } catch (Throwable t) {
            return "";
        }
    }

    /** Picks the page/referrer URL out of the remaining GURL fields. */
    static String pickReferrer(Object info, String downloadUrl) {
        if (info == null) return "";
        for (String name : new String[] {"h", "i"}) {
            String spec = urlString(read(gurlField(info.getClass(), name), info));
            if (isHttp(spec) && !spec.equals(downloadUrl)) return spec;
        }
        return "";
    }

    static boolean isHttp(String url) {
        return url != null && (url.startsWith("http://") || url.startsWith("https://"));
    }
}
