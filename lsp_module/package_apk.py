"""Merge classes.dex + META-INF/xposed/* into the aapt2-produced APK."""
import os
import zipfile

ROOT = os.path.dirname(os.path.abspath(__file__))
SRC = os.path.join(ROOT, "build", "base-unsigned.apk")
OUT = os.path.join(ROOT, "build", "module-unsigned.apk")
DEX = os.path.join(ROOT, "build", "dex", "classes.dex")

extra = {
    "classes.dex": DEX,
    "META-INF/xposed/java_init.list": os.path.join(ROOT, "META-INF", "xposed", "java_init.list"),
    "META-INF/xposed/module.prop": os.path.join(ROOT, "META-INF", "xposed", "module.prop"),
    "META-INF/xposed/scope.list": os.path.join(ROOT, "META-INF", "xposed", "scope.list"),
}

with zipfile.ZipFile(SRC) as src, zipfile.ZipFile(OUT, "w") as out:
    for info in src.infolist():
        # keep original compression (resources.arsc must stay stored)
        out.writestr(info, src.read(info.filename), compress_type=info.compress_type)
    for name, path in extra.items():
        with open(path, "rb") as f:
            method = zipfile.ZIP_STORED if name == "resources.arsc" else zipfile.ZIP_DEFLATED
            out.writestr(zipfile.ZipInfo(name), f.read(), compress_type=method)

print("wrote", OUT, os.path.getsize(OUT), "bytes")
