import os, sys
from loguru import logger
logger.remove()
from androguard.core.dex import DEX

dirs = ['dex/base','dex/chrome']
out = open('analysis/class_index.txt','w',encoding='utf-8')
pkgcount = {}
for d in dirs:
    for f in sorted(os.listdir(d)):
        if not f.endswith('.dex'): continue
        dx = DEX(open(os.path.join(d,f),'rb').read())
        for c in dx.get_classes():
            n = c.get_name()
            out.write(f"{n}\t{d}/{f}\n")
            pkg = n.rsplit('/',1)[0]
            pkgcount[pkg] = pkgcount.get(pkg,0)+1
out.close()
print("classes:", sum(pkgcount.values()))
with open('analysis/package_index.txt','w',encoding='utf-8') as fo:
    for p,c in sorted(pkgcount.items(), key=lambda x:-x[1]):
        fo.write(f"{c}\t{p}\n")
print("packages:", len(pkgcount))
