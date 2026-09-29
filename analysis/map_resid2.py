"""Find the exact method that loads a given resource-id constant.

Strategy: raw-scan the dex for the 4-byte literal, take the preceding code item as a
candidate, then VERIFY by decoding that method's instructions (authoritative).
"""
import os, struct, bisect
from loguru import logger
logger.remove()
from androguard.core.dex import DEX

TARGETS = {
    0x7f141292: "title 下载此文件？",
    0x7f141c61: "是否要下载此文件?",
    0x7f141c62: "是否要从 %1$s 下载此文件?",
    0x7f141c63: "bottun 下载",
}

dexf = []
for sub in ("base", "chrome"):
    d = os.path.join(r"E:\edge_download_change\dex", sub)
    for f in sorted(os.listdir(d)):
        if f.endswith(".dex"):
            dexf.append((sub + "/" + f, os.path.join(d, f)))

for name, path in dexf:
    data = open(path, "rb").read()
    hits = {}
    for target in TARGETS:
        pat = struct.pack("<I", target)
        offs = []
        at = data.find(pat)
        while at >= 0:
            offs.append(at)
            at = data.find(pat, at + 1)
        if offs:
            hits[target] = offs
    if not hits:
        continue

    dd = DEX(data)
    entries = []
    for c in dd.get_classes():
        cn = c.get_name()
        for m in c.get_methods():
            code = m.get_code()
            if code is None:
                continue
            try:
                entries.append((code.offset, cn, m))
            except Exception:
                pass
    entries.sort(key=lambda e: e[0])
    starts = [e[0] for e in entries]

    print(f"\n## {name}")
    for target, offs in hits.items():
        for hit in offs:
            i = bisect.bisect_right(starts, hit) - 1
            if i < 0:
                print(f"  0x{target:08x} @0x{hit:x}: before first code item")
                continue
            off, cn, m = entries[i]
            verified = False
            try:
                for ins in m.get_instructions():
                    for op in ins.get_operands():
                        if isinstance(op[1], int) and op[1] == target:
                            verified = True
                            break
                    if verified:
                        break
            except Exception:
                pass
            tag = "VERIFIED" if verified else "candidate?"
            print(f"  0x{target:08x} {TARGETS[target]:24} @0x{hit:x} -> code@0x{off:x} "
                  f"{cn}.{m.get_name()}{m.get_descriptor()} [{tag}]")
