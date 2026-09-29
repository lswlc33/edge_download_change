package io.github.lswlc33.edge_download_change;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Lightweight dex index used to locate obfuscated Edge classes by their method signature
 * instead of by their (unstable) obfuscated name.
 *
 * Edge's "download this file?" confirmation dialog is created by a class with a single
 * static method {@code (String fileName, long size, org.chromium.base.Callback)void}.
 * That descriptor matches exactly one short obfuscated class in the default package
 * (plus one stable, package-qualified WebView class we filter out).
 */
final class DexIndex {

    /** JVM-style descriptor of Edge's download confirmation dialog factory. */
    static final String CONFIRM_DIALOG_PROTO = "(Ljava/lang/String;JLorg/chromium/base/Callback;)V";

    private DexIndex() {}

    /**
     * Finds candidate class names in the default package (obfuscated names such as "rge")
     * that declare or reference a method with the given descriptor.
     */
    static List<String> findDefaultPackageClasses(Context context, final String proto) {
        final LinkedHashSet<String> result = new LinkedHashSet<String>();
        if (context == null) return new ArrayList<String>();
        DexFileReader.forEach(context, new DexFileReader.DexConsumer() {
            @Override
            public void accept(byte[] dex) {
                int count = DexFileReader.methodCount(dex);
                for (int i = 0; i < count; i++) {
                    // Filter by the (short) class descriptor first: building the full
                    // prototype string for every method would allocate tens of thousands
                    // of strings per dex and stall Edge's startup.
                    String cls = DexFileReader.methodClass(dex, i);
                    if (cls.length() < 3 || cls.charAt(0) != 'L'
                            || cls.charAt(cls.length() - 1) != ';') continue;
                    String inner = cls.substring(1, cls.length() - 1);
                    if (inner.length() == 0 || inner.length() > 12) continue;
                    if (inner.indexOf('/') >= 0 || inner.indexOf('$') >= 0) continue;
                    if (!proto.equals(DexFileReader.methodProto(dex, i))) continue;
                    result.add(inner);
                }
            }
        });
        return new ArrayList<String>(result);
    }
}
