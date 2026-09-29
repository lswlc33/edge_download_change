# 发布到 LSPosed 官方模块仓库（指引）

> 结论先行：**官方模块仓库不是一个"上传 APK"的地方，而是一组 GitHub 仓库**——
> 每个模块对应 `Xposed-Modules-Repo/<你的包名>` 一个仓库，LSPosed 管理器的「仓库」页
> 从 `modules.lsposed.org` 读取这些仓库的 **Release**。
> 你要做两件事：**① 用官方表单提交申请（会生成一个预填的 GitHub Issue）；② 之后在自己仓库里
> 持续发 Release，官方侧会同步到模块仓库并把 tag 规范成 `版本号-版本名`。**

## 一、官方的硬性要求（摘自 Xposed-Modules-Repo 组织说明）

一个"有效仓库"必须满足：

1. **仓库名 = 模块的包名**（本项目为 `io.github.lswlc33.edge_download_change`，由官方创建）；
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
| Package name | `io.github.lswlc33.edge_download_change` |
| Description or reason | 模块简介 + 说明它是 LSPosed（libxposed API 102）模块、作用、源码地址 |

点 **Submit on GitHub** → 会跳转到 `Xposed-Modules-Repo/submission` 仓库里一个**预填好的 Issue**，
确认无误后直接提交 Issue 即可。审核通过后，官方会创建
`https://github.com/Xposed-Modules-Repo/io.github.lswlc33.edge_download_change`，其中包含：

- `SOURCE_URL`：你的源码仓库地址
- `SUMMARY`：一句话简介（显示在管理器列表里）
- `README.md`

> 另外：如果**你拥有 APK applicationId 对应的域名**，可以在该域名根上添加一条 TXT 记录
> `lsposed-modules-repo-verification=你的GitHub用户名`，可用于快速通过验证。
> 我们的包名 `io.github.lswlc33.edge_download_change` 不对应自有域名，因此走正常审核流程。

**建议的 Issue 内容（可直接复制）**

```text
Package name: io.github.lswlc33.edge_download_change

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

## 六、官方是怎么"同步"你的仓库的（实证调查）

我直接查了官方仓库（`Xposed-Modules-Repo/com.tsng.hidemyapplist`，即 HMA 模块）与官网数据，结论如下：

**1. 元数据不是自动同步的。** 官方仓库里的 `SOURCE_URL` / `SUMMARY` / `README.md` 提交历史停在
**2021 年**，作者是模块开发者（`Dr-TSNG`）和维护者（`Nullptr`）——这部分由官方建库时写入，之后基本不动。

**2. Release/APK 由官方侧的自动化同步。** 证据链：
- 官方仓库里 release 的 **tag 对象 tagger = `github-actions[bot]`**，而 **release 作者 = 维护者账号 `aviraxp`**；
- 但该模块仓库**没有任何 workflow、也没有 Actions 运行记录**（`actions/workflows` 与 `actions/runs` 均为 0），
  组织 `.github` 仓库里也没有 workflow。

→ 也就是说：同步逻辑跑在**官方自己的基础设施/账号**上（读你仓库的 release、在组织仓库建 tag/release），
**不是**由你的仓库触发的，你也不需要为同步做任何配置。官方原话那句
"The bot will help you to correct the tag name" 说的就是这个机器人。

**3. 分发走官方 CDN。** 组织仓库的 release 资产通过 `assets.lsposed.org` 代理分发，形如
`https://assets.lsposed.org/Xposed-Modules-Repo/<包名>/releases/download/<tag>/<文件名>.apk`
（带签名与时效参数）——设备上 LSPosed 管理器/网站就是从这里下载的。

**4. 官网/管理器看到的数据字段**（`modules.lsposed.org` 首页内嵌索引，实测样本 30 个模块）：

| 字段 | 来源 |
|---|---|
| `name` | 组织仓库名 = **模块包名** |
| `description` | 组织仓库的 **description**（= 模块名称） |
| `summary` | 组织仓库的 **`SUMMARY` 文件**（一句话简介） |
| `sourceUrl` | 组织仓库的 **`SOURCE_URL` 文件**（你的仓库） |
| `latestRelease` | 组织仓库里最新的**稳定 release 的 tag**，格式 `版本号-版本名`（样本 30/30 全部符合） |
| `latestBetaRelease` | versionName 带 `alpha`/`beta` 的 release（实测 tag 例：`2-1.0.2-alpha.1`、`3-0.2.0-beta.2`） |
| `latestSnapshotRelease` | 快照通道（样本中暂无模块使用） |

### 对发版的实际影响（务必遵守）

1. **只在你的仓库发 Release**，不要尝试往组织仓库推东西（你也没有权限）。
2. tag 一定用 `版本号-版本名`（本项目的 `release.yml` 已保证）。
3. **beta 通道**：versionName 带 `alpha`/`beta` 的 release 会被官方归入 beta 通道。本项目的
   `.github/workflows/nightly.yml` 已经把每次 push 的构建自动打成 `2.6-beta.<运行号>` 并用
   规范 tag `8-2.6-beta.<运行号>` 发布（GitHub 上标为 pre-release），因此 **push 即产出官方 beta 版本**；
   稳定版仍走手动 Release 工作流。
4. **只替换 Release 附件不触发同步**（官方明确说明），必须"新建 Release 或改动 Release 内容"。
5. 组织仓库的 **description = 模块显示名**、**SUMMARY = 简介**：提交申请时把这两项写清楚
   （description 建议就是 `edge_download_change`，summary 一句话说明用途）。
6. **包名与"品牌"风险**：`io.github.lswlc33.edge_download_change` 不含自有域名 → 走人工审核，审阅者也可能对
   `com.edge.*`（冒充 Microsoft Edge 品牌）有顾虑。官方样本里 15/30 使用 `io.github.<用户名>.*`
   形式（如 `io.github.xiaotong6666.fusehide`）。若想换成 `io.github.lswlc33.edge_download_change`，
   **现在是最便宜的时机**（尚未提交；代价是设备上要卸载 v2.4 再装新包名版本，签名可保持不变）。

## 七、当前提交状态（2026-09-29）

- **申请 Issue：<https://github.com/Xposed-Modules-Repo/submission/issues/1968>**（`[New Package] io.github.lswlc33.edge_download_change`，OPEN，等待官方审核）
- 已满足的前置条件：
  - 仓库描述非空（模块名）✓
  - 稳定版 Release **`8-2.6`**（tag = 版本号-版本名，带 APK）✓
  - beta 通道：**`8-2.6-beta.9`**（versionName `2.6-beta.9`，pre-release）✓
  - 每次构建同签名（证书 SHA-256 `9064aa71…`）✓
- **审核通过后**：官方会创建 `Xposed-Modules-Repo/io.github.lswlc33.edge_download_change`（含 `SOURCE_URL` / `SUMMARY` / `README.md`），
  之后你只需继续在自己的仓库发版：
  - 稳定版：Actions → **Release** → 填 `version`（等于 `android:versionName`）；
  - beta：**每次 push 到 main 自动发布**（`-beta.<运行号>`，官方归入 beta 通道）。
- 若官方在 Issue 里提出修改要求，按需调整后在本仓库重新发版即可（tag 规则不要变）。
