#!/usr/bin/env python3
"""把 .so 的 ASCII 字符串全量导出为文本文件（一次性），之后用 grep 反复检索。

直接读已解包的 so 文件（base.apk 里的 .so 未压缩，unzip -j 出来即可）。
用法: python dump_so_strings.py so/libchrome.so out.txt [min_len]
"""
import re
import sys

MIN_LEN = int(sys.argv[3]) if len(sys.argv) > 3 else 6
CHUNK = 1 << 23
pat = re.compile(rb"[\x20-\x7e]{%d,}" % MIN_LEN)

src, dst = sys.argv[1], sys.argv[2]
n = 0
with open(src, "rb") as fi, open(dst, "w", encoding="utf-8", newline="\n") as fo:
    tail = b""
    while True:
        chunk = fi.read(CHUNK)
        if not chunk:
            break
        data = tail + chunk
        tail = b""
        last_end = 0
        for m in pat.finditer(data):
            fo.write(m.group().decode("ascii"))
            fo.write("\n")
            n += 1
            last_end = m.end()
        if last_end < len(data):
            tail = data[last_end:] if len(data) - last_end < 4096 else b""
    if len(tail) >= MIN_LEN:
        fo.write(tail.decode("ascii", "ignore") + "\n")
print(f"{src} -> {dst}: {n} strings")
