#!/usr/bin/env python3
"""从 APK 中流式提取 .so 的 ASCII 字符串（不落盘），可按正则过滤。

用法: python so_strings.py <apk> <entry> [regex ...]
例:   python so_strings.py apks_extracted/base.apk lib/arm64-v8a/libchrome.so download
"""
import re
import sys
import zipfile

MIN_LEN = 6
CHUNK = 1 << 22


def iter_strings(fp, min_len=MIN_LEN):
    pat = re.compile(rb"[\x20-\x7e]{%d,}" % min_len)
    tail = b""
    while True:
        chunk = fp.read(CHUNK)
        if not chunk:
            break
        data = tail + chunk
        tail = b""
        for m in pat.finditer(data):
            if m.end() == len(data):
                tail = m.group()  # 可能被 chunk 边界截断，留给下一轮
                break
            yield m.group().decode("ascii")
    if len(tail) >= min_len:
        yield tail.decode("ascii")


def main():
    if len(sys.argv) < 3:
        print(__doc__)
        return 1
    apk, entry = sys.argv[1], sys.argv[2]
    pats = [re.compile(p, re.I) for p in sys.argv[3:]]
    seen = set()
    with zipfile.ZipFile(apk) as z, z.open(entry) as fp:
        for s in iter_strings(fp):
            if s in seen:
                continue
            if not pats or any(p.search(s) for p in pats):
                seen.add(s)
                print(s, flush=True)
    return 0


if __name__ == "__main__":
    sys.exit(main())
