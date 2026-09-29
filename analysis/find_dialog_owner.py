import os, struct, bisect
from loguru import logger
logger.remove()
from androguard.core.dex import DEX

TARGETS = {
    0x7f141292: "title 下载此文件？",
    0x7f141c61: "是否要下载此文件?",
    0x7f141c62: "是否要从 %1$s 下载此文件?",
    0x7f141c60: "取消",
}

dexf = []
for sub in ("base", "chrome"):
    d = os.path.join(r"E:\edge_download_change\dex", sub)
    for f in sorted(os.listdir(d)):
        if f.endswith(".dex"):
            dexf.append((sub + "/" + f, os.path.join(d, f)))

for target, label in TARGETS.items():
    pat = struct.pack("<I", target)
    print(f"\n===== {label} 0x{target:08x} =====")
    hits_by_dex = {}
    for name, path in dexf:
        data = open(path, "rb").read()
        offs = []
        at = data.find(pat)
        while at >= 0:
            offs.append(at)
            at = data.find(pat, at + 1)
        if not offs:
            continue
        dd = DEX(data)
        table = []
        for c in dd.get_classes():
            cn = c.get_name()
            for m in c.get_methods():
                code = m.get_code()
                if code is None:
                    continue
                try:
                    off = code.offset
                except Exception:
                    continue
                table.append((off, cn, m.get_name(), m.get_descriptor()))
        table.sort()
        starts = [t[0] for t in table]
        found = {}
        for hit in offs:
            i = bisect.bisect_right(starts, hit) - 1
            if i >= 0:
                entry = table[i]
                key = (entry[1], entry[2])
                found[key] = found.get(key, 0) + 1
        if found:
            print(f"  -- {name}")
            for (cn, mn), n in sorted(found.items()):
                print(f"     {cn}.{mn}  (x{n})")
