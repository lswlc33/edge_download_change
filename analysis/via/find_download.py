"""Locate Via's download implementation: DownloadListener impl + DownloadManager usage."""
import re
from loguru import logger
logger.remove()
from androguard.core.dex import DEX

DEX_PATH = r"E:\edge_download_change\via_analysis\dex\classes.dex"
d = DEX(open(DEX_PATH, "rb").read())

print("=== classes implementing android.webkit.DownloadListener ===")
listener_classes = []
for c in d.get_classes():
    ifaces = [str(i) for i in c.get_interfaces()]
    if any("DownloadListener" in i for i in ifaces):
        listener_classes.append(c)
        print(f"  {c.get_name()}  ifaces={ifaces} super={c.get_superclassname()}")
        for m in c.get_methods():
            print(f"     method: {m.get_name()}{m.get_descriptor()}")

print("\n=== methods referencing android/app/DownloadManager ===")
hits = []
for c in d.get_classes():
    for m in c.get_methods():
        if m.get_code() is None:
            continue
        try:
            for ins in m.get_instructions():
                for op in ins.get_operands():
                    if len(op) > 2 and isinstance(op[2], str) and "DownloadManager" in op[2]:
                        hits.append((c.get_name(), m.get_name(), m.get_descriptor(), op[2]))
                        break
                else:
                    continue
                break
        except Exception:
            pass
for h in sorted(set(hits)):
    print(f"  {h[0]}.{h[1]}{h[2]}\n      -> {h[3]}")

print("\n=== methods referencing setDownloadListener ===")
for c in d.get_classes():
    for m in c.get_methods():
        if m.get_code() is None:
            continue
        try:
            for ins in m.get_instructions():
                for op in ins.get_operands():
                    if len(op) > 2 and isinstance(op[2], str) and "setDownloadListener" in op[2]:
                        print(f"  {c.get_name()}.{m.get_name()}{m.get_descriptor()}")
        except Exception:
            pass
