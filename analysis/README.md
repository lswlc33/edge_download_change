# analysis — Edge 下载链路分析产物

这里保存的是"模块的 hook 目标是怎么找出来的"的全过程与脚本，用于 **Edge 升级后快速重新确认**。

生成这些产物用到的输入：`../Edge_153.0.4234.49.apks`（未提交，见 .gitignore）。

## 复现步骤

```bash
# 0) 依赖
pip install androguard          # 反编译/解析 dex
# Android SDK build-tools 提供 aapt2（解析 manifest / 资源）

# 1) 解包 apks（ZIP）
unzip -o ../Edge_153.0.4234.49.apks -d apks_extracted

# 2) 导出各 split 的 AndroidManifest（下载相关组件清单）
aapt2 dump xmltree --file AndroidManifest.xml apks_extracted/base.apk > manifest_base.txt
aapt2 dump xmltree --file AndroidManifest.xml apks_extracted/split_chrome.apk > manifest_split_chrome.txt

# 3) 提取 dex（下载 Java 代码在 split_chrome 里，base 几乎没有）
unzip -o -j apks_extracted/base.apk "*.dex" -d dex/base
unzip -o -j apks_extracted/split_chrome.apk "*.dex" -d dex/chrome

# 4) 枚举下载相关类 / 反编译关键类 / 导出字段布局
python scan_download_classes.py     # -> download_classes.txt（119 个类）
python decompile_batch.py           # -> edge-decompiled/*.java（核心类）
python dump_members.py              # -> hook_targets.txt（混淆字段与方法描述符）

# 5) 定位"下载确认弹窗"实现（按资源 ID + 字节码）
#    先导出中文资源，找到文案对应的资源 ID，再在 dex 里反查使用它的类
aapt2 dump resources apks_extracted/split_config.zh.apk > zh_res.txt
python map_resid.py                 # 资源 ID -> 方法（候选）
python find_dialog_owner.py         # 按前缀定位弹窗工厂的调用方

# 6) Via 浏览器下载实现（作为参照，非必需）
python via/find_download.py         # 定位 DownloadListener 与系统 DownloadManager 调用
```

## 关键结论速查

| 结论 | 位置 |
|---|---|
| 真机确认的混淆字段语义：`DownloadInfo.a`=URL(GURL，spec 在其实例字段 `a`)、`e`=文件名、`c`=MIME、`g`=路径/URI、`j`=已接收字节、`q`=状态（0=新下载，1..3=历史/恢复）、`l`=guid、`p`=OtrProfileId | `hook_targets.txt` + 真机 dump（见 lsp_module/README.md 版本记录） |
| 下载确认弹窗 = `rge.a(String 文件名, long 大小, org.chromium.base.Callback)`（按方法签名定位，避免依赖混淆类名） | `hook_targets.txt`、`map_resid.py` |
| 通知栏撤销通道的动作/附加键字符串（`DOWNLOAD_CANCEL` 等） | `hook_targets.txt`、`so_strings.py` |
| Via 的做法：WebView `DownloadListener` → 自己弹框 → `DownloadManager.Request` + enqueue，并转发 Cookie/UA/Referer | `via/` |
| Edge 的 `DownloadManagerService.onDownloadItemCreated` 早于弹窗（实测 28ms），但**恢复的历史条目也会触发** | `lsp_module/README.md`（2.4 版本记录） |

> 注意：`GURL.toString()` 在 Edge 153 **没有**被重写（返回 `org.chromium.url.GURL@xxx`），
> 读取 URL 必须取其实例字段 `a`——这一点曾导致早期版本完全失效，务必留意。
