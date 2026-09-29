"""Map resource-id constants to the exact method that uses them.

Uses the dex map_list to get precise code_item ranges, then maps raw byte hits
to code items and from there to the owning method via androguard offsets.
"""
import os, struct, bisect
from loguru import logger
logger.remove()
from androguard.core.dex import DEX

TARGETS = {
    0x7f141292: "title 下载此文件？",
    0x7f141c61: "是否要下载此文件?",
    0x7f141c62: "是否要从 %1$s 下载此文件?",
    0x7f141c63: "下载",
}
TYPE_CODE_ITEM = 0x2001


def read_uleb(data, off):
    result = 0
    shift = 0
    while True:
        b = data[off]
        off += 1
        result |= (b & 0x7F) << shift
        if b < 0x80:
            return result, off
        shift += 7


def code_item_ranges(data):
    """Parse map_list -> [(start, end)] of every code_item."""
    map_off = struct.unpack_from("<I", data, 0x34)[0]
    size = struct.unpack_from("<I", data, map_off)[0]
    ranges = []
    for i in range(size):
        base = map_off + 4 + i * 12
        type_code, _unused, count, offset = struct.unpack_from("<HHII", data, base)
        if type_code != TYPE_CODE_ITEM:
            continue
        for j in range(count):
            at = offset + j * 16  # all fields up to insns are 16 bytes
            insns_size = struct.unpack_from("<I", data, at + 12)[0]
            end = at + 16 + insns_size * 2
            ranges.append((at, end))
    ranges.sort()
    return ranges


dexf = []
for sub in ("base", "chrome"):
    d = os.path.join(r"E:\edge_download_change\dex", sub)
    for f in sorted(os.listdir(d)):
        if f.endswith(".dex"):
            dexf.append((sub + "/" + f, os.path.join(d, f)))

for name, path in dexf:
    data = open(path, "rb").read()
    hits_per_target = {}
    for target in TARGETS:
        pat = struct.pack("<I", target)
        offs = []
        at = data.find(pat)
        while at >= 0:
            offs.append(at)
            at = data.find(pat, at + 1)
        if offs:
            hits_per_target[target] = offs
    if not hits_per_target:
        continue

    ranges = code_item_ranges(data)
    starts = [r[0] for r in ranges]

    dd = DEX(data)
    by_offset = {}
    for c in dd.get_classes():
        cn = c.get_name()
        for m in c.get_methods():
            code = m.get_code()
            if code is None:
                continue
            try:
                by_offset[code.offset] = (cn, m.get_name(), m.get_descriptor())
            except Exception:
                pass

    print(f"\n## {name}")
    for target, offs in hits_per_target.items():
        label = TARGETS[target]
        for hit in offs:
            i = bisect.bisect_right(starts, hit) - 1
            where = "non-code(data)"
            owner = ""
            if i >= 0:
                start, end = ranges[i]
                if start <= hit < end:
                    where = f"code_item@0x{start:x}"
                    if start in by_offset:
                        cn, mn, md = by_offset[start]
                        owner = f"{cn}.{mn}{md}"
            print(f"  0x{target:08x} {label:26} @0x{hit:x}  {where}  {owner}")
