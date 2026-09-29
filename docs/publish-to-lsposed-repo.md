# 发布到 LSPosed 官方模块仓库（指引）

> 结论先行：**官方模块仓库不是一个"上传 APK"的地方，而是一组 GitHub 仓库**——
> 每个模块对应 `Xposed-Modules-Repo/<你的包名>` 一个仓库，LSPosed 管理器的「仓库」页
> 从 `modules.lsposed.org` 读取这些仓库的 **Release**。
> 你要做两件事：**① 用官方表单提交申请（会生成一个预填的 GitHub Issue）；② 之后在自己仓库里
> 持续发 Release，官方侧会同步到模块仓库并把 tag 规范成 `版本号-版本名`。**

## 一、官方的硬性要求（摘自 Xposed-Modules-Repo 组织说明）

一个"有效仓库"必须满足：

1. **仓库名 = 模块的包名**（本项目为 `com.edge.systemdownload`，由官方创建）；
2. **仓库必须有非空的描述**，内容就是模块名称；
3. **至少有一个有效 Release**；
4. **每个有效 Release 至少要有一个 `.apk` 资产，且 tag 名必须是 `VersionCode-VersionName`**
   （例如我们的 `versionCode=6`、`versionName=2.4` → tag 必须是 **`6-2.4`**）。

官方还给了一条 "Best Practice to release"：

> 你可以先用任意 tag 创建 Release 并上传 APK，机器人会帮你把 tag 改成规范格式。
> **注意：只修改 Release 里的资产不会触发机器人**，所以不要把 APK 单独另外上传而不改动 Release 内容。

（也就是说：**每次发版都要"新建 Release 或改动 Release 内容"**，而不是替换附件。）

## 二、提交申请（当前唯一入口）

打开官方提交页：**<https://modules.lsposed.org/submission>**

页面上有四个选项，选 **“I'd like to Submit a new package”（提交新包）**，然后填：

| 字段 | 填什么 |
|---|---|
| Package name | `com.edge.systemdownload` |
| Description or reason | 模块简介 + 说明它是 LSPosed（libxposed API 102）模块、作用、源码地址 |

点 **Submit on GitHub** → 会跳转到 `Xposed-Modules-Repo/submission` 仓库里一个**预填好的 Issue**，
确认无误后直接提交 Issue 即可。审核通过后，官方会创建
`https://github.com/Xposed-Modules-Repo/com.edge.systemdownload`，其中包含：

- `SOURCE_URL`：你的源码仓库地址
- `SUMMARY`：一句话简介（显示在管理器列表里）
- `README.md`

> 另外：如果**你拥有 APK applicationId 对应的域名**，可以在该域名根上添加一条 TXT 记录
> `lsposed-modules-repo-verification=你的GitHub用户名`，可用于快速通过验证。
> 我们的包名 `com.edge.systemdownload` 不对应自有域名，因此走正常审核流程。

**建议的 Issue 内容（可直接复制）**

```text
Package name: com.edge.systemdownload

Module name: edge_download_change

Description:
Replace Edge for Android's download confirmation dialog with a Copy/Download dialog and
hand the download over to the system DownloadManager (or a chosen third-party downloader
app) instead of Edge's built-in download manager. LSPosed module built against the
libxposed API 102.

Source: https://github.com/<你的用户名>/edge_download_change
Latest release (APK attached): https://github.com/<你的用户名>/edge_download_change/releases

Verified on Edge 153.0.4234.49 (com.microsoft.emmx). Static scope: com.microsoft.emmx.
```

## 三、之后的每次发版（本项目已经自动化）

本项目已经按官方要求配置好 `.github/workflows/release.yml`：

1. 先把 `lsp_module/AndroidManifest.xml` 的 `versionCode` / `versionName` 递增
   （`BuildInfo.VERSION` 同步改一下，仅用于界面显示）；
2. push 到 `main`；
3. GitHub → **Actions → Release → Run workflow**，输入 `version`（例如 `2.4`，必须与
   `android:versionName` 一致）；
4. 工作流会：用 Secrets 里的密钥签名构建 → 创建 **tag = `<versionCode>-<versionName>`** 的
   Release（标题 `v2.4`）→ 上传 APK。

这样发布的 Release **天然满足官方第 4 条要求**，官方同步后即可在 LSPosed 管理器的「仓库」页
看到更新。若某次临时用了不合规的 tag，官方机器人会自动纠正（但不要只替换附件）。

> nightly（`.github/workflows/nightly.yml`）只用于自测，**不要**用 nightly 去发版：
> 它的 tag 固定为 `nightly`，不符合官方要求，也不会被同步。

## 四、检查清单（提交前）

- [ ] 仓库 **description** 非空（= 模块名，例如 `edge_download_change`）——本项目已设置；
- [ ] 仓库 **公开**（public）——本项目已设置；
- [ ] 至少一个 Release，且 tag 为 `<versionCode>-<versionName>`，且**附带了 APK**；
- [ ] APK 是**已签名**的（本项目用 GitHub Secrets 统一签名，保证每次一致、可覆盖安装）；
- [ ] 仓库根有 `README.md`（说明用途、安装方法、限制）——本项目有中英两版；
- [ ] 确认 `module.prop` 的 `targetApiVersion` 与你的框架匹配（本项目为 `102`）。

## 五、常见问题

- **提交后多久生效？** 审核由官方人工/机器人处理，通过后 `modules.lsposed.org` 与 LSPosed
  管理器「仓库」页会列出该模块；此后每次发版只需在自己的仓库发 Release。
- **能改包名或换签名吗？** 不行。包名决定仓库名，签名决定用户能否覆盖安装——本项目自
  2.4 起用固定密钥（见下），**请务必备份好密钥**。
- **签名密钥在哪里？** 不在仓库里（`.gitignore` 已排除）。GitHub Actions 从仓库 Secrets 读取：
  `SIGNING_KEYSTORE_BASE64` / `SIGNING_STORE_PASSWORD` / `SIGNING_KEY_ALIAS` / `SIGNING_KEY_PASSWORD`；
  本地构建时使用 `lsp_module/module.keystore`（密码 `edgesysdl`）。**丢失密钥 = 用户无法覆盖升级**。
- **能否同时发到 Coolapk/酷安等？** 可以，但那些渠道与官方仓库无关，官方仓库只认上面的规则。
