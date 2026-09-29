import sys, os, re
from androguard.misc import AnalyzeDex
from androguard.core.dex import DEX

DEXDIR = r"E:\edge_download_change\dex"
OUT = r"E:\edge_download_change\download_classes.txt"

results = {}
files = []
for sub in ("base", "chrome"):
    d = os.path.join(DEXDIR, sub)
    for f in sorted(os.listdir(d)):
        if f.endswith(".dex"):
            files.append((sub, os.path.join(d, f)))

for sub, path in files:
    with open(path, "rb") as fh:
        d = DEX(fh.read())
    for c in d.get_classes():
        name = c.get_name()  # Lorg/...;
        if re.search(r"download", name, re.I):
            results.setdefault(name, []).append(os.path.basename(path))

with open(OUT, "w", encoding="utf-8") as f:
    for name in sorted(results):
        f.write(f"{name}\t{'|'.join(sorted(set(results[name])))}\n")
print(f"total classes: {len(results)}")
