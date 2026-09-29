import os, sys
from loguru import logger
logger.remove()
from androguard.core.dex import DEX

TARGETS = [
    "Lorg/chromium/chrome/browser/download/DownloadManagerService;",
    "Lorg/chromium/chrome/browser/download/DownloadItem;",
    "Lorg/chromium/chrome/browser/download/DownloadInfo;",
    "Lorg/chromium/url/GURL;",
    "Lorg/chromium/chrome/browser/profiles/OtrProfileId;",
    "Lorg/chromium/chrome/browser/download/EdgeOneDriveDownloadBridge;",
    "Lorg/chromium/chrome/browser/download/DownloadDialogBridge;",
    "Lorg/chromium/chrome/browser/download/DownloadController;",
]
dexfiles = []
for sub in ("base", "chrome"):
    d = os.path.join(r"E:\edge_download_change\dex", sub)
    for f in sorted(os.listdir(d)):
        if f.endswith(".dex"):
            dexfiles.append((sub + "/" + f, os.path.join(d, f)))

out = open(r"E:\edge_download_change\hook_targets.txt", "w", encoding="utf-8")
for name, path in dexfiles:
    dd = DEX(open(path, "rb").read())
    for c in dd.get_classes():
        if c.get_name() in TARGETS:
            out.write(f"\n########## {c.get_name()}  (in {name})  access=0x{c.get_access_flags():x} super={c.get_superclassname()}\n")
            out.write("--- fields ---\n")
            for f_ in c.get_fields():
                out.write(f"  {f_.get_name()} : {f_.get_descriptor()}  access=0x{f_.get_access_flags():x}\n")
            out.write("--- methods ---\n")
            for m in c.get_methods():
                out.write(f"  {m.get_name()}{m.get_descriptor()} access=0x{m.get_access_flags():x}\n")
out.close()
print("done")
