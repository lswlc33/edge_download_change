#!/usr/bin/env python3
"""按正则扫描 dex 里的类名 / 字符串池（Edge 反混淆检索的通用入口）。

用法:
  python scan_classes.py <regex> [dexdir ...]        # 默认 dex/base dex/chrome
  python scan_classes.py --strings <regex> [dexdir ...]
  python scan_classes.py --members <regex> [dexdir ...]

--members 额外打印每个命中类的方法签名与字段（用于判断混淆类的真实语义）。
"""
import os
import re
import sys

from loguru import logger

logger.remove()
from androguard.core.dex import DEX

BASE = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DEFAULT_DIRS = [os.path.join(BASE, "dex", "base"), os.path.join(BASE, "dex", "chrome")]


def dex_files(dirs):
    for d in dirs:
        if not os.path.isdir(d):
            continue
        for f in sorted(os.listdir(d)):
            if f.endswith(".dex"):
                yield d, f, os.path.join(d, f)


def main():
    args = sys.argv[1:]
    mode = "classes"
    if args and args[0] in ("--strings", "--members"):
        mode = args[0][2:]
        args = args[1:]
    if not args:
        print(__doc__)
        return 1
    pat = re.compile(args[0], re.I)
    dirs = args[1:] or DEFAULT_DIRS

    for d, f, path in dex_files(dirs):
        dx = DEX(open(path, "rb").read())
        if mode == "strings":
            for s in dx.get_strings():
                if pat.search(s):
                    print(f"{s}\t{d}/{f}")
            continue
        for c in dx.get_classes():
            if not pat.search(c.get_name()):
                continue
            print(f"{c.get_name()}\t{d}/{f}\tsuper={c.get_superclassname()}")
            if mode == "members":
                for fld in c.get_fields():
                    print(f"    F {fld.get_name()} : {fld.get_descriptor()}")
                for m in c.get_methods():
                    print(f"    M {m.get_name()}{m.get_descriptor()}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
