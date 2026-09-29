package io.github.lswlc33.edge_download_change;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/** Diagnostic dumper for Edge's obfuscated download objects (field name -> type -> value). */
final class Dump {

    private Dump() {}

    static String info(Object target) {
        if (target == null) return "null";
        StringBuilder sb = new StringBuilder(target.getClass().getName());
        sb.append(" {");
        try {
            for (Field f : target.getClass().getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) continue;
                try {
                    f.setAccessible(true);
                    Object value = f.get(target);
                    sb.append(f.getName()).append('=');
                    if (value == null) {
                        sb.append("null");
                    } else if (value instanceof String) {
                        sb.append('"').append(shorten((String) value)).append('"');
                    } else if (value instanceof Long || value instanceof Integer
                            || value instanceof Boolean || value instanceof Short) {
                        sb.append(value);
                    } else if (value.getClass().getName().equals("org.chromium.url.GURL")) {
                        sb.append("GURL(").append('"').append(shorten(Reflect.urlString(value))).append('"')
                                .append(" field a=\"").append(shorten(Reflect.string(Reflect.get(value, "a"))))
                                .append("\")");
                    } else {
                        sb.append(value.getClass().getSimpleName());
                    }
                    sb.append(' ');
                } catch (Throwable t) {
                    sb.append(f.getName()).append("=<err> ");
                }
            }
        } catch (Throwable ignored) {
        }
        sb.append('}');
        return sb.toString();
    }

    /** All GURL fields of an object, with their spec, for locating the real download URL. */
    static String gurlFields(Object target) {
        if (target == null) return "null";
        StringBuilder sb = new StringBuilder();
        try {
            for (Field f : target.getClass().getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) continue;
                if (!"org.chromium.url.GURL".equals(f.getType().getName())) continue;
                Object value = Reflect.read(f, target);
                sb.append(f.getName()).append("=\"").append(shorten(Reflect.urlString(value))).append("\" ");
            }
        } catch (Throwable ignored) {
        }
        return sb.toString();
    }

    private static String shorten(String value) {
        if (value == null) return "";
        return value.length() > 120 ? value.substring(0, 120) + "…" : value;
    }
}
