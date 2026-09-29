package io.github.lswlc33.edge_download_change;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Reads every classes*.dex of the host app (base + splits) and hands the bytes to a consumer.
 *
 * Uses random access (ZipFile) instead of a sequential ZipInputStream: the host APK is
 * ~313 MB with a 220 MB native library, and streaming through it to reach the dex files
 * would cost hundreds of megabytes of I/O per scan. ZipFile reads only the dex entries.
 */
final class DexFileReader {

    interface DexConsumer {
        void accept(byte[] dex);
    }

    private static final int MAX_DEX = 32 * 1024 * 1024;

    private DexFileReader() {}

    static void forEach(Context context, DexConsumer consumer) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            readApk(info.sourceDir, consumer);
            String[] splits = info.splitSourceDirs;
            if (splits != null) {
                for (String split : splits) readApk(split, consumer);
            }
        } catch (Throwable t) {
            DownloadHooks.log(4, "dex read failed", t);
        }
    }

    private static void readApk(String apkPath, DexConsumer consumer) {
        if (apkPath == null) return;
        ZipFile zip = null;
        try {
            zip = new ZipFile(apkPath);
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String name = entry.getName();
                if (!name.startsWith("classes") || !name.endsWith(".dex")) continue;
                long size = entry.getSize();
                if (size <= 0 || size > MAX_DEX) continue;
                byte[] data = readAll(zip.getInputStream(entry), (int) size);
                if (data != null) consumer.accept(data);
            }
        } catch (Throwable ignored) {
        } finally {
            try {
                if (zip != null) zip.close();
            } catch (Throwable ignored) {
            }
        }
    }

    private static byte[] readAll(InputStream in, int expected) {
        ByteArrayOutputStream out = new ByteArrayOutputStream(expected);
        try {
            byte[] buffer = new byte[64 * 1024];
            int read;
            while ((read = in.read(buffer)) > 0) out.write(buffer, 0, read);
            return out.toByteArray();
        } catch (Throwable t) {
            return null;
        } finally {
            try {
                in.close();
            } catch (Throwable ignored) {
            }
        }
    }

    // ------------------------------------------------------------------ dex primitives

    static int u32(byte[] d, int off) {
        return (d[off] & 0xFF) | ((d[off + 1] & 0xFF) << 8) | ((d[off + 2] & 0xFF) << 16)
                | ((d[off + 3] & 0xFF) << 24);
    }

    static int u16(byte[] d, int off) {
        return (d[off] & 0xFF) | ((d[off + 1] & 0xFF) << 8);
    }

    /** Reads the length-prefixed MUTF-8 string of string_id at the given index. */
    static String string(byte[] dex, int index) {
        int stringIdsSize = u32(dex, 0x38);
        if (index < 0 || index >= stringIdsSize) return "";
        int offset = u32(dex, u32(dex, 0x3C) + index * 4);
        int at = offset;
        while (at < dex.length && (dex[at] & 0x80) != 0) at++;
        at++;
        int end = at;
        while (end < dex.length && dex[end] != 0) end++;
        try {
            return new String(dex, at, end - at, "UTF-8");
        } catch (Throwable t) {
            return "";
        }
    }

    static String type(byte[] dex, int typeIndex) {
        int typeIdsSize = u32(dex, 0x40);
        if (typeIndex < 0 || typeIndex >= typeIdsSize) return "";
        return string(dex, u32(dex, u32(dex, 0x44) + typeIndex * 4));
    }

    /** Builds a JVM-style descriptor "(params)ret" for a proto index. */
    static String proto(byte[] dex, int protoIndex) {
        int protoIdsSize = u32(dex, 0x48);
        if (protoIndex < 0 || protoIndex >= protoIdsSize) return "";
        int base = u32(dex, 0x4C) + protoIndex * 12;
        int returnType = u32(dex, base + 4);
        int parametersOff = u32(dex, base + 8);
        StringBuilder sb = new StringBuilder("(");
        if (parametersOff != 0) {
            int count = u32(dex, parametersOff);
            for (int i = 0; i < count; i++) {
                sb.append(type(dex, u16(dex, parametersOff + 4 + i * 2)));
            }
        }
        sb.append(')').append(type(dex, returnType));
        return sb.toString();
    }

    static String methodName(byte[] dex, int methodIndex) {
        int methodIdsSize = u32(dex, 0x58);
        if (methodIndex < 0 || methodIndex >= methodIdsSize) return "";
        int base = u32(dex, 0x5C) + methodIndex * 8;
        return string(dex, u32(dex, base + 4));
    }

    static String methodClass(byte[] dex, int methodIndex) {
        int methodIdsSize = u32(dex, 0x58);
        if (methodIndex < 0 || methodIndex >= methodIdsSize) return "";
        int base = u32(dex, 0x5C) + methodIndex * 8;
        return type(dex, u16(dex, base));
    }

    static String methodProto(byte[] dex, int methodIndex) {
        int methodIdsSize = u32(dex, 0x58);
        if (methodIndex < 0 || methodIndex >= methodIdsSize) return "";
        int base = u32(dex, 0x5C) + methodIndex * 8;
        return proto(dex, u16(dex, base + 2));
    }

    static int methodCount(byte[] dex) {
        return u32(dex, 0x58);
    }
}
