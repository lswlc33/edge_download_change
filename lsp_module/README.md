# edge_download_change（LSPosed 模块）

针对 Microsoft Edge for Android（`com.microsoft.emmx`，已验证 153.0.4234.49）的 LSPosed 模块，
libxposed API（modern Xposed API）**102**（minApi 101）。当前版本 **2.4**。

应用名 = 项目文件夹名（`edge_download_change`，ASCII，不随语言变化）；
界面文字提供**英文（values，默认）与中文（values-zh）**两套，跟随系统语言；
注入侧（运行在 Edge 进程内，读不到本模块资源）使用 `Str.java` 里按系统语言选择的字符串表。

## 界面与设置（v2.0）

App 内提供状态、设置与日志三块：

**状态**
- 模块版本（versionName + versionCode）；
- 是否已被 LSPosed 激活：由注入端每 60 秒心跳 + 每次事件上报，5 分钟内有上报即显示「● 已激活」，
  并显示最近注入时间、进程名与 Xposed 框架版本；无上报则引导用户启用；
- 作用域：本模块用静态作用域（`scope.list` = `com.microsoft.emmx`，`staticScope=true`），无需手动勾选；
- 按钮：【刷新状态】【打开 LSPosed】（自动尝试 LSPosed/经典 Xposed 管理器的入口，失败给出步骤提示）。

**设置**
- 开启拦截（开关，默认开）——关闭后 Edge 恢复原生下载行为，模块不再弹窗；
- 下载目标——可选「系统下载器（DownloadManager）」或已安装的第三方下载器
  （ADM / IDM / 1DM Lite / ADM Lite / ADM(Vanda) / 迅雷 / FDM / DVGet / Download Navi /
  Aria2App / Gopeed / AB DM / FluxDown），也可填自定义包名；列表会标注「已安装 / 未安装」。
  第三方启动失败时会自动回退到系统下载器并写日志。

**日志**
- 【查看日志】进入日志页（单色字体、可选中），支持【复制全部】【刷新】【清空】；
- 日志记录：注入/hook 安装结果、每次拦截与跳过（含原因）、下载跳转结果。

### 设置与状态的通信方式

| 方向 | 通道 | 说明 |
|---|---|---|
| App → 注入端（设置） | ① 框架远程偏好 `getRemotePreferences("settings")`（同步、无 IPC）② 模块 App 的 `ContentProvider`（异步校准） | 注入端同步路径只读本地映射，绝不阻塞 Edge；每次日志/心跳上报的**返回值**会带回最新设置，改设置后最多多拦截一次即生效 |
| 注入端 → App（状态/日志） | `ContentResolver.call("report")` 推送到 `ModuleProvider`（后台线程、批量 1.2 s、失败退避 30 s 重试） | App 落盘到 `files/module.log`（超 96 KB 保留后半段）并更新状态偏好 |

Provider 只暴露「读设置 / 写状态日志」两类调用，日志内容不会通过 Provider 被外部读取。

## 功能

Edge 原本点击下载链接后走自带的下载管理器/确认弹窗。本模块将其替换为：

- 点击下载 → 弹出自定义对话框「下载此文件？」，含两个按钮：
  - **下载**：把该 URL 交给**安卓系统内建下载器**（`android.app.DownloadManager`），
    在系统通知栏显示进度，不再进入 Edge 内置下载管理器；
  - **复制**：复制下载链接，并取消 Edge 内置下载（可粘贴到任意第三方下载器）。
- 点击对话框外/返回键 = 取消：同时取消 Edge 内置下载。
- 在模块接管期间（对话框存活期间），Edge 自己的下载弹窗
  （位置选择对话框、"另存为/OneDrive" 底部抽屉、新版确认面板 `rge.a`）一律被抑制。

## 工作原理（hook 点）

**行为保证**：Edge 内置下载在"下载项创建"的那一刻就被取消（早于响应体传输开始），
模块**从不**用 `onResult(true)` 放行 Edge 的下载 —— 用户点「下载」后真正被创建的，只有
系统下载器（DownloadManager）的下载任务。

| Hook | 作用 |
|---|---|
| `DownloadManagerService.onDownloadItemCreated(DownloadItem)` | **主拦截点**。取到真实 URL（`DownloadInfo.a` GURL）、文件名（`e`）、MIME（`c`）、下载项 id（`DownloadItem.a()`）后**立即取消 Edge 的下载项**（`removeDownload` + `DOWNLOAD_CANCEL` 双通道，1.5s 后再重试一次），然后弹本模块的「复制 / 下载」对话框 |
| Edge 下载确认框工厂 `(String 文件名, long 大小, org.chromium.base.Callback)V`（Edge 153 为混淆类 `rge.a`） | 按方法签名在 dex 中定位（`DexIndex`，不依赖混淆类名）。接管期间抑制 Edge 的确认框；若确认框先于下载项出现（理论上不会，架构上下载项先建），同样弹本模块的对话框，并在等待到下载项后执行选择；**从不回调 true**，所以 Edge 下载不会被启动 |
| `Activity.dispatchTouchEvent` / `onUserInteraction` / `onResume` / `onPause` | 跟踪前台 Activity（弹窗挂靠）与最近用户操作；`onResume` 同时作为 chrome 分包延迟加载后的重试时机 |
| `DownloadDialogBridge.showDialog`、`EdgeOneDriveDownloadBridge.queryUserChoice` | 接管期间抑制 Edge 剩余下载弹窗 |
| 取消动作 | `removeDownload(guid, otrProfileId, true)` + 向 `DownloadBroadcastManager` 发送字符串解析出的 `DOWNLOAD_CANCEL` 动作，两者同时执行 |

- **字符串/签名锚定（对抗 Edge 版本更新）**：
  - 确认框工厂按**方法签名**定位：`DexIndex` 在运行时解析 Edge 各 dex 的方法表（仅读 名称/描述符，
    不解析字节码），筛选"默认包（混淆名）+ 该签名"的类；已验证在 Edge 153 上唯一命中 `rge`。
  - 取消通道按**字符串**定位：运行时扫描 dex 字符串池，按
    `org.chromium.chrome.browser.download.` 前缀解析出 `DOWNLOAD_CANCEL` 动作与
    `DownloadContentId_Id` / `_Namespace` / `OTR_PROFILE_ID` / `IS_OFF_THE_RECORD` 附加键（`DexStrings`）。
  - 核心链路的类/方法名（`DownloadManagerService.onDownloadItemCreated`、`DownloadDialogBridge.showDialog`、
    `EdgeOneDriveDownloadBridge.queryUserChoice`）是 JNI keep 的稳定名。
  - 无法用字符串定位的只剩 `DownloadInfo` 的单字母混淆字段（靠类型 + 取值兜底，`Reflect`）
    与 `rge` 的混淆类名本身（已改为按签名定位，见上）。

字段名（`a/e/c/g/p`）是 Edge 153 的 R8 混淆名；`Reflect.java` 同时做了按类型/取值的兜底扫描，
Edge 小版本更新时通常仍能工作。

### 拦截条件（全部满足才接管）

1. URL 为 http/https（blob:/data: 等仍走 Edge 原流程）；
2. 非"系统下载器托管项"（`DownloadItem.b == false`）；
3. 最近 30 秒内有用户触摸操作，且存在前台 Edge Activity；
4. 目标文件尚不存在（过滤恢复中的下载）；
5. 非"保存网页"产物（.mhtml/.webarchive/multipart-related）；
6. 8 秒内未对同一 URL+文件名重复弹窗。

## 版本记录

**2.4**
- 修复「启动 Edge 时弹出历史下载」：日志显示 Edge 启动会恢复**未完成的历史下载**
  （`j=0` 但 `q=2`），此前的"已接收字节为 0 即视为新下载"挡不住它。设备 dump 对比确认：
  **真实新下载 `q=0`**，恢复/历史条目 `q∈{1,2,3}` —— 现在要求 `j==0 && q==0`。
- 降低崩溃风险（用户报告启动时崩溃，崩溃窗口内唯一的重活是"按描述符全量扫 dex"）：
  - 描述符扫描只在**名字定位失败**时才做，且延迟 5 秒、执行前再次确认 hook 未安装（不再做无用功）；
  - 扫描改为**先按类名过滤**再构造方法描述符（此前每个方法都构造一次字符串，十几万次分配）；
  - **框架 hook 精简为只挂 `Activity.onResume`**，去掉此前挂在最热路径上的
    `dispatchTouchEvent` / `onUserInteraction` / `onPause` / `Application.onCreate`；
  - 相应移除已无数据来源的"用户操作时间窗"过滤与"目标文件已存在"过滤
    （后者此前会误伤重名文件的再次下载）。

**2.3（关键修复）**
- **URL 提取修复**：真机 dump 证明 Edge 153 的 `GURL` **没有重写 `toString()`**，返回的是
  `org.chromium.url.GURL@1a2b3c` 这样的标识串，而真正的 URL 在它的 String 字段 `a` 里。
  此前 `Reflect.urlString()` 优先用 `toString()` → 每个下载都被判成 `non-http` 而跳过
  （日志里的 `skip: non-http download ()` 就是它）。现在改为**优先读字段 `a`**，
  并显式过滤 Java 标识串，只在其它字段布局下才回退到无参 String 方法。
- **历史条目过滤**：真机日志显示启动时会为 10+ 条**已完成**的历史记录投递 created 事件；
  现在要求 `DownloadInfo.j`（已接收字节）为 0 才视为新下载，已完成/恢复的条目一律跳过。
- **弹窗与下载项的先后顺序**：实测 Edge 会**先建下载项、28 毫秒后弹确认框**。
  现在会记住"刚刚创建、尚未消费"的下载项，弹窗出现时若文件名匹配（或名字还没填充）
  就直接用它的 URL，不再干等一个新事件。

**2.2**
- 真机日志显示：确认弹窗被成功接管，但点「下载」后 **8 秒内没有收到 `onDownloadItemCreated`**，
  因此拿不到 URL、也无法取消 Edge 下载 → Edge 自己把文件下完了。本版针对这条路径做覆盖与修复：
  - 新增回调覆盖 hook：`DownloadController.enqueueAndroidDownloadManagerRequest`
    （Edge 自己把下载交给系统 DownloadManager，**此路径不创建下载项**）、
    `DownloadController.onDownloadUpdated/onDownloadCancelled`、
    `DownloadManagerService.onDownloadItemUpdated/onDownloadItemRemoved`；
  - `onDownloadItemUpdated` 现在也能**落实待定决策**（取消 Edge 下载 + 复制/交给下载器），
    不再只依赖 `onDownloadItemCreated`；
  - 前 12 个下载项输出字段级 dump（`DUMP created item: … | gurls: …`），用于确证 URL 字段位置；
  - 等待下载项的窗口 8 秒 → 15 秒。
- 安装过程更安静：首次 `onPackageLoaded/onPackageReady` 时 chrome 分包尚未加载，属于预期情况，
  现在记为「deferred」并**自动退避重试 6 次**（约 400 ms 起），不再报两次"安装失败"。

**2.1**
- 修复状态误报「未检测到激活」：注入发生在 Edge 的 Application 创建之前，此前状态上报与启动日志
  因拿不到 Context 被静默丢弃。现在改为：状态上报「待发 + 2 秒重试」、日志入队不再丢弃、
  首次心跳提前到 3 秒、一旦拿到 Context（首个 Activity onResume）立即补发状态/日志并提示「已加载 ✓」。
- 性能：扫描宿主 dex 改用 `ZipFile` 随机读取（实测 32 ms，此前 `ZipInputStream` 需顺序读约 290 MB），
  且所有 dex 扫描都移到后台线程；`DexStrings` 未就绪时先返回内置默认值。
- 正确性：Edge 弹窗的抑制/回绝改为「文件名匹配」，其他下载的弹窗会原样交给 Edge 处理
  （此前在接管窗口内会把别的下载弹窗回绝掉，导致那个下载被中止或卡住）。
- 正确性：目标文件已存在的过滤只在「没有检测到用户操作」时生效，重名文件再次下载不再被绕过。
- 界面：未激活时给出 4 步排查清单；作用域说明补充「部分管理器仍需手动勾选」；
  自定义包名改用「未检测到（可能受系统包可见性限制）」措辞。

**2.0**
- 新增模块界面：状态（版本 / 是否激活 / 作用域 + 打开 LSPosed）、设置（拦截开关 / 下载目标选择）、日志页；
- 设置与状态通过 ContentProvider + 框架远程偏好双向打通；
- 下载目标支持系统下载器与 13 种第三方下载器（含自定义包名），失败自动回退系统下载器。

## 自检与排查

本版本带**诊断提示（Toast）**，无需看日志即可判断状态：

| 提示 | 含义 |
|---|---|
| 打开 Edge 后出现「Edge 系统下载器已加载 ✓」 | 模块已注入且 hook 安装成功 |
| 「Edge 系统下载器：hook 安装失败…」 | 模块已注入但 hook 失败（看 LSPosed 日志） |
| 点下载后出现「已接管 Edge 下载：xxx」 | 拦截生效（无确认框的下载走此路径） |
| 出现本模块的「下载此文件？」（复制 / 下载） | 确认框接管成功 |
| 「已交给系统下载器下载」/「已复制下载链接」 | 动作完成 |

完全没有提示 = 模块未注入（检查 LSPosed 是否启用、作用域是否勾选 Edge、是否强制停止过 Edge）。
日志标签 `EdgeSysDL`，每次拦截/跳过都会写明原因（如 `skip: no resumed Edge activity`）。

## 安装使用

1. 安装 `Edge系统下载器-1.0.apk`；
2. LSPosed 管理器 → 模块 → 启用「Edge 系统下载器」；模块自带静态作用域
   `com.microsoft.emmx`（`scope.list` + `staticScope=true`），确认作用域已勾选 Edge；
3. **强制停止 Edge** 后重新打开；
4. 点击任意下载链接测试：应弹出「下载此文件？」（复制 / 下载 两个按钮）。

排查：LSPosed 管理器 → 日志，过滤标签 `EdgeSysDL`。每次拦截/跳过都会记录原因。

## 已知限制

- 需要 Cookie/Referer 才能下载的文件（如网盘直链），系统下载器拿不到会话，
  可能失败或下到登录页 —— 此类文件用「复制」后粘贴到已登录的浏览器/下载工具；
- Edge 大版本更新若改变混淆字段布局，可能需要按新版本调整 `Reflect` 中的字段名；
- PDF 等原先会被 Edge 内置查看器打开的文件，现在同样交给系统下载器（设计如此）。

## 构建

无 Gradle，仅用 Android SDK build-tools（见 `build.sh`）：

```bash
# 依赖：JDK 17、Android SDK (platforms;android-35, build-tools;35.0.0)
# ../xposed_api/ 为 libxposed api 102.0.0 源码（Maven Central 下载）+ 注解 stub
bash build.sh
# 产物：Edge系统下载器-1.0.apk（已用 module.keystore 签名，密码 edgesysdl）
```

## 文件结构

```
lsp_module/
├── AndroidManifest.xml          # 两个 Activity + SettingsProvider + 下载器包可见性(<queries>)
├── res/values/strings.xml       # 英文（默认）
├── res/values-zh/strings.xml    # 中文
├── META-INF/xposed/{java_init.list, module.prop, scope.list}
├── xposed_api/                  # vendor 的 libxposed API 102 源码（仅编译期使用）
│   ├── src/io/github/libxposed/…        # Maven Central: io.github.libxposed:api:102.0.0 (Apache-2.0)
│   └── annstub/                          # androidx / libxposed 注解的最小 stub
├── src/com/edge/systemdownload/
│   ├── Module.java              # XposedModule 入口（onPackageLoaded/Ready 时安装 hook）
│   ├── DownloadHooks.java       # hook 安装、拦截过滤、取消 Edge 下载、按设置分发下载器
│   ├── RemoteSettings.java      # 注入端：读设置（远程偏好/Provider）、上报状态与日志、心跳
│   ├── DexFileReader.java       # 运行时随机读取宿主 APK 的 dex（ZipFile）
│   ├── DexIndex.java            # 按方法签名定位混淆类（确认弹框工厂）
│   ├── DexStrings.java          # 按字符串定位下载动作/附加键（取消通道）
│   ├── Reflect.java             # 混淆字段/方法的反射读取（名称优先 + 类型兜底）
│   ├── Dump.java                # 诊断：打印下载项字段布局
│   ├── Bg.java                  # 后台线程 + 主线程调度
│   ├── PendingDownload.java     # 被接管下载的数据模型
│   ├── ConfirmRequest.java      # 「先弹窗后建项」时的待决请求
│   ├── DialogPresenter.java     # 「复制/下载」对话框 + 系统下载器入队 + 剪贴板
│   ├── Downloaders.java         # 下载目标注册表（系统 + 第三方下载器 Intent 构造）
│   ├── Str.java                 # 注入侧的中英文案表（按系统语言切换）
│   ├── BuildInfo.java           # 共享常量（版本、偏好键、Provider 方法名）
│   ├── MainActivity.java        # 主界面：状态 / 设置 / 日志入口
│   ├── LogActivity.java         # 日志查看（复制/刷新/清空）
│   ├── ModuleProvider.java      # 读写通道（设置下发 + 状态日志上报）
│   ├── ModulePrefs.java         # App 侧偏好与状态存储
│   ├── LsposedLauncher.java     # 打开 LSPosed 管理器
│   └── UiKit.java               # 卡片式 UI 主题助手
├── package_apk.py               # 把 dex + META-INF/xposed 合入 APK
├── build.sh                     # 一键构建（输出 edge_download_change-<版本>.apk）
└── module.keystore              # 本地自签名密钥（storepass/keypass: edgesysdl）
```

## 构建

```bash
# 依赖：JDK 17、Android SDK（platforms;android-35、build-tools;35.0.0）、python3
bash build.sh                       # 产物：edge_download_change-<versionName>.apk（已签名）
APK_SUFFIX=-nightly-abc1234 bash build.sh   # 自定义后缀（CI 的 nightly 用）
```

- 脚本跨平台：Windows Git Bash / Linux / macOS 均可；SDK 路径依次取
  `ANDROID_SDK_ROOT` → `ANDROID_HOME` → Windows 默认目录 → `~/Android/Sdk` / `~/Library/Android/sdk`。
- **签名**优先读环境变量，其次用本地 `module.keystore`：

  | 变量 | 说明 |
  |---|---|
  | `SIGNING_KEYSTORE_BASE64` | base64 编码的 keystore（GitHub Actions Secrets 用） |
  | `SIGNING_KEYSTORE_FILE` | keystore 文件路径（默认 `module.keystore`） |
  | `SIGNING_STORE_PASSWORD` / `SIGNING_KEY_ALIAS` / `SIGNING_KEY_PASSWORD` | 密码 / 别名（默认 `edgesysdl`） |

- 仓库**不包含**密钥文件（见根目录 `.gitignore`）：CI 用 Secrets，本地用 `module.keystore`。
  **请备份密钥**——丢失后已发布版本无法被覆盖安装。

## 持续集成与发布

| 工作流 | 触发 | 产物 |
|---|---|---|
| `.github/workflows/nightly.yml` | push 到 `main` / 手动 | 预发布 `nightly`（tag 固定 `nightly`，文件名含短 SHA） |
| `.github/workflows/release.yml` | 手动，输入 `version`（须等于 `android:versionName`） | 正式 Release，**tag = `<versionCode>-<versionName>`**（官方模块仓库要求） |

发版步骤：递增 `AndroidManifest.xml` 的 `versionCode`/`versionName`（并同步 `BuildInfo.VERSION`）→ push →
Actions → Release → Run workflow。发布到 LSPosed 官方仓库的完整流程见
根目录 [`docs/publish-to-lsposed-repo.md`](../docs/publish-to-lsposed-repo.md)。
另见仓库根目录的 `README.md`（中文说明）/ `README.en.md`（English）与 `analysis/README.md`（hook 目标的推导过程）。
