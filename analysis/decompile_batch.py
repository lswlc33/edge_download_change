import os, sys, time
from loguru import logger
logger.remove()

from androguard.core.dex import DEX
from androguard.core.analysis.analysis import Analysis
from androguard.decompiler.decompiler import DecompilerDAD

TARGETS = {
    # class name (without L ;) -> dex file that contains it
    "org/chromium/chrome/browser/download/DownloadController": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DownloadManagerService": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DownloadItem": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DownloadInfo": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DownloadMessageBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DownloadDialogBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DangerousDownloadDialogBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DuplicateDownloadDialogBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/InsecureDownloadDialogBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/OpenDownloadDialogBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/PolicyWarningDownloadDialogBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DownloadManagerBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DownloadNotificationServiceObserver": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DownloadUtils": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/MimeUtils": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/EdgeDownloadManagerFeatureBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/EdgeOneDriveDownloadBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/service/DownloadTaskScheduler": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/service/DownloadBackgroundTaskCallback": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/settings/DownloadSettings": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/settings/DownloadLocationPreference": "chrome/classes.dex",
    "org/chromium/chrome/browser/download/DownloadBroadcastManager": "chrome/classes2.dex",
    "org/chromium/chrome/browser/download/DownloadForegroundService": "base/classes4.dex",
    "org/chromium/chrome/browser/download/DownloadFileProvider": "base/classes4.dex",
    "org/chromium/components/download/DownloadCollectionBridge": "base/classes4.dex",
    "org/chromium/components/download/InMemoryDownloadFile": "base/classes4.dex",
    "org/chromium/components/download/NetworkStatusListenerAndroid": "base/classes4.dex",
    "org/chromium/chrome/browser/app/download/home/DownloadActivity": "chrome/classes.dex",
    "org/chromium/chrome/browser/app/download/home/EdgeHubDownloadFragment": "chrome/classes.dex",
    "org/chromium/chrome/browser/edge_hub/downloads/EdgeDownloadRequestBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/edge_hub/downloads/EdgeDownloadManagerHelper": "chrome/classes.dex",
    "org/chromium/chrome/browser/edge_hub/downloads/EdgeBackendProvider": "chrome/classes.dex",
    "org/chromium/chrome/browser/edge_hub/downloads/EdgeDownloadDelegateImpl": "chrome/classes.dex",
    "org/chromium/chrome/browser/edge_hub/downloads/EdgeDownloadMessageUiControllerImpl": "chrome/classes.dex",
    "org/chromium/chrome/browser/edge_hub/downloads/EdgeFileDeletionQueue": "chrome/classes.dex",
    "org/chromium/chrome/browser/offlinepages/downloads/OfflinePageDownloadBridge": "chrome/classes.dex",
    "org/chromium/chrome/browser/edge_silent_notification/SilentNotificationDownloadWorker": "chrome/classes.dex",
}

DEXDIR = r"E:\edge_download_change\dex"
OUTDIR = r"E:\edge_download_change\decompiled"
os.makedirs(OUTDIR, exist_ok=True)

by_dex = {}
for cls, dex in TARGETS.items():
    by_dex.setdefault(dex, []).append(cls)

for dex, classes in by_dex.items():
    path = os.path.join(DEXDIR, dex.replace("/", os.sep))
    t0 = time.time()
    data = open(path, "rb").read()
    d = DEX(data)
    dx = Analysis(d)
    d.set_decompiler(DecompilerDAD(d, dx))
    found = set()
    for c in d.get_classes():
        name = c.get_name()[1:-1]
        if name in classes:
            found.add(name)
            try:
                src = c.get_source()
            except Exception as e:
                src = f"// DECOMPILE FAILED: {e}\n"
                for m in c.get_methods():
                    src += f"\n// method {m.get_name()} {m.get_descriptor()}\n"
            out = os.path.join(OUTDIR, name.split("/")[-1].replace("/", "_") + ".java")
            with open(out, "w", encoding="utf-8") as f:
                f.write(f"// source: split_chrome {dex}  class: {name}\n" + src)
            print(f"OK {name} -> {out} ({len(src)} chars, {time.time()-t0:.0f}s)")
    for missing in set(classes) - found:
        print(f"MISSING {missing} in {dex}")
