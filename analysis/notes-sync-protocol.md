# Edge Android 153.0.4234.49 — 同步传输层与协议（native 侧）调查笔记

> 目标：搞清楚 Edge(Android) 与微软同步服务器之间「到底怎么通信」，为后续用 Java/Kotlin 在 Via
> 里重做一个兼容客户端提供依据。
>
> **本文只基于已导出的纯文本素材做静态分析**，没有真机抓包、没有登录实测。凡属推断的结论都显式标注。

---

## 0. 证据来源与阅读方法（先看这一节，否则会误读本文）

### 0.1 素材

| 文件 | 内容 | 本文中的简称 |
|---|---|---|
| `so/strings/libchrome.strings.txt` | Chromium 主体（299,800 行 ASCII 串，含 C++ `__FILE__`、SQL、URL、protobuf 描述符碎片） | **libchrome** |
| `so/strings/libOneAuth.strings.txt` | 微软 OneAuth 认证库 | libOneAuth |
| `so/strings/libmsaoaidauth.strings.txt` / `libmsaoaidsec.strings.txt` | MSA OAuth / 设备安全库 | — |
| `analysis/class_index.txt` | Java 类名索引（78,338 行，格式 `L<类名>;<TAB><dex路径>`） | **class_index** |

导出脚本见 `analysis/dump_so_strings.py`。

### 0.2 方法论陷阱（**重要**，直接影响"某字符串不存在"的解读）

1. **导出下限是 6 个字符**：`dump_so_strings.py` 默认 `MIN_LEN = 6`（`re.compile(rb"[\x20-\x7e]{6,}")`）。
   因此 **长度 < 6 的串根本不在 dump 里**。这解释了为什么 `share`(5)、`token`(5)、`name`(4)、
   `ctime`(5)、`mtime`(5) 等 protobuf 字段名搜不到——
   **"搜不到"≠"不存在"**，只说明"无法从本素材确认"。
   反之，≥6 字符的串若精确匹配不到（如 `cache_guids`、`client_defined_unique_tag`、`device_name`），
   更可能是**被并进了更长的粘连串**、或**本 build 未生成该串常量**——两种可能用现有素材无法区分。
2. **dump 里没有地址、没有文件偏移**，只有行号。行号近似等于该串在 `.rodata` 中的**物理顺序**。
3. **同长度/同内容的串会被链接器合并（string merging）**，所以短串（`version`、`server`、
   `client`、`platform`…）在 dump 里散落各处，**不能靠邻近性判断归属**。只有**长且唯一**的串
   （如 `/command/`、`X-AFS-ClientInfo`、`sync.cache_guid`）邻近关系才有意义。
4. 反过来，**长 enum 名字会粘连成一大串**（因为 protobuf 描述符里长度字节 ≥ 0x20 时本身可打印），
   例如 `ENHANCED_PROTECTIONEXTENDED_REPORTINGNONEREPORTING_POPULATION_UNSPECIFIEDSCOUT`。
   这类粘连串可以用来一次性拿到整个 enum 的取值集合。
5. `.so` 里存在 `sync_pb.*` 的**消息名字符串常量**（protobuf 生成的 `kXxxMsgName`），
   以及**从序列化描述符字节数组中被 `strings` 截出来的字段名**。字段名碎片是**不完整**的。

### 0.3 结论标签约定

- ✅ **确证**：有明确、唯一、可复现的字符串证据。
- 🟡 **推断**：基于字符串证据 + 上游 Chromium/Edge 公开行为的合理推论，**未在 APK 内直接确证**。
- ❌ **无法静态确认**：必须真机抓包 / 登录实测。

所有行号形如 `libchrome:235511`。

---

## 1. 总览：Edge Android 上其实有 **三条并行**的"同步/推送"通道

这是本次调查最重要的结论。把三条混为一谈会导致协议实现跑偏。

| # | 通道 | 端点 | 作用 | 证据类型 |
|---|---|---|---|---|
| **A** | Chromium sync engine（`components/sync`） | `https://edge.microsoft.com/sync` + `/command/` | 经典类型：书签、偏好、密码、自动填充、主题、历史、扩展、reading list、标签页、cookie… 走 `ClientToServerMessage` protobuf | ✅ 确证 |
| **B** | Edge AFS / **Anaheim** Feed Service（REST+JSON） | `/v1/feeds/me/syncEntities`、`/v1/me/browsehistory/*`、`/v1/diagnosticData/...`；PPE 主机 `https://ppe.activity.windows.com` | Edge 自有类型：collections / workspaces / wallet / 浏览历史（独立于 `/command/`）；一致性自检上报 | ✅ 确证 |
| **C** | **Edge Cloud Messaging（ECM）** —— WebSocket 长连接 | `wss://access-point.cloudmessaging.edge.microsoft.com` | 替代 Chromium 原来的 FCM/GCM **invalidation 推送通道**（拿 `sync.cache_guid` 当客户端标识） | ✅ 确证 |

旁证（通道 A 的编译单元确实在包里）：

```
libchrome:112335  ../../components/sync/service/history_sync_session_durations_metrics_recorder.cc
libchrome:113175  ../../components/sync/engine/sync_scheduler_impl.cc
libchrome:110972  ../../components/sync/engine/get_updates_processor.cc
libchrome:110374  ../../components/sync/engine/commit.cc
libchrome:113489  ../../components/sync/engine/syncer_proto_util.cc
libchrome:114327  ../../components/sync/engine/net/http_bridge.cc
libchrome:114696  ../../components/sync/model/data_type_store_backend.cc
libchrome:111933  ../../components/sync/engine/edge_encryption_keys_fetcher.cc          ← Edge 私有
libchrome:114871  ../../components/sync/engine/edge_encryption_keys_manager_aad.cc      ← Edge 私有
libchrome:110113  ../../components/sync/engine/edge_sync_diagnostic_request.cc          ← Edge 私有
libchrome:110985  ../../components/sync/engine/edge_sync_diagnostic_processor.cc        ← Edge 私有
libchrome:111366  ../../components/sync/engine/edge_reliability_telemetry_helper.cc     ← Edge 私有
libchrome:114350  ../../components/sync_edge_collections/collections_sync_bridge.cc     ← Edge 私有
libchrome:112118  ../../components/edge_cloud_messaging/public/diagnostics/diagnostics_manager.cc  ← 通道 C
```

共 **62 个 `components/sync*` 源文件路径**出现在二进制中（44 个 `.cc`）。

---

## 2. 【Q1】同步请求的完整 URL 形态

### 2.1 通道 A：Chromium sync engine

四种类型的常量在 dump 里**紧邻**（`libchrome:235501`–`235508`），这段几乎可以断定是
`SyncServerConnectionManager` 的常量块：

```
libchrome:235501  SyncServerConnectionManagerRequest
libchrome:235502  X-AFS-Tracking
libchrome:235503  server
libchrome:235504  Microsoft.Sync.ServerConnectionManager.HttpStatusCode
libchrome:235505  client
libchrome:235506  Chromium
libchrome:235507  client_id
libchrome:235508  sync-fetch-encryption-keys
...
libchrome:235509  Clear server data found more data to delete.
libchrome:235510  Failed to clear server data, set up sync to retry.
libchrome:235511  /command/
```

另有一个**独立的、唯一的**查询串片段：

```
libchrome:132676  ?client_id=
```

（注意它带前导 `?`，说明是拼 query string，而不是 URL 的一部分。）

默认 base URL（紧邻 `sync-url` 开关名）：

```
libchrome:235573  sync-url
libchrome:235574  https://edge.microsoft.com/sync-ppe
libchrome:235575  https://edge.microsoft.com/sync
libchrome:235577  encryption-key-url
```

#### ✅ 确证的结论

- 命令路径 = **`/command/`**（注意结尾斜杠）。
- 查询参数 = **`client_id=<值>`**，且形如 `?client_id=`，即 base URL 自身不带 query。
- 默认 base URL 有两个候选：`https://edge.microsoft.com/sync`
  与 `https://edge.microsoft.com/sync-ppe`（后者由 `--sync-ppe-test-mode` 选择）。
- 命令开关：`--sync-url`（`libchrome:62971/235573`）、`--sync-ppe-test-mode`（`libchrome:235530`）。

#### 🟡 推断的完整形态

```
POST https://edge.microsoft.com/sync/command/?client_id=<26位随机串>
```

即 `SyncServerConnectionManager` 的做法是：把 base URL 与 `/command/` 做 Resolve，再用
`GURL::Replacements` 把 query 设为 `client_id=…`。这与上游 Chromium
`components/sync/engine/net/sync_server_connection_manager.cc` 的行为一致，
且 `?client_id=` 这个带前导 `?` 的片段正是 `replacements.SetQueryStr("client_id=" + ...)`
写法的典型痕迹（若用 `GURL::Resolve("?client_id=")` 也会产生同样片段）。

> ⚠️ **`/command/` 的确切拼接顺序（`/sync/command/` vs `/sync/command`）以及是否带尾部斜杠后的
> 额外路径段，建议真机抓包确认。** 上游代码用 `Resolve()`，如果 base URL 没有以 `/` 结尾，
> 结果会变成 `https://edge.microsoft.com/command/`（覆盖掉 `sync`），这是最容易踩的坑。

### 2.2 通道 B：Edge AFS / Anaheim（REST + JSON）

关键块 `libchrome:235533`–`235551`（**整块同源**，因为是长且唯一的串）：

```
libchrome:235533  /v1/feeds/me/notification/telemetry
libchrome:235534  telemetry_period
libchrome:235537  v1/diagnosticData/Diagnostic.SendCheckResult()
libchrome:235542  /v1/feeds/me/syncEntities
libchrome:235543  %s:%s                       ← 注意：冒号后有一个空格，HTTP 头 "Name: value " 的拼装格式
libchrome:235544  X-AFS-ClientInfo
libchrome:235545  X-AFS-CV
libchrome:235546  sync-token
libchrome:235547  X-RateLimit-Debug-UpdateRule
libchrome:235548  PerUserAnaheimWrites_default
libchrome:235549  PerUserAnaheimReads_default
libchrome:235550  platform=%s; os=%s; osVer=%s; app=%s; appVer=%s; appChannel=%s; appInstallationId=%s; region=%s;
libchrome:235551  platform=%s; os=%s; osVer=%s; app=%s; appVer=%s; appChannel=%s; region=%s;
```

PPE 主机（唯一一处完整 AFS URL）：

```
libchrome:139782  https://ppe.activity.windows.com/v1/feeds/me/syncEntities/
libchrome:61240   edge.activity.windows.com
libchrome:61241   *.activity.windows.com
```

浏览历史（另一组 Edge REST 端点，`libchrome:238568` / `238575`）：

```
libchrome:238568  /v1/me/browsehistory/lookup?client=chrome
libchrome:238573  X-AFS-Tracking
libchrome:238575  /v1/me/browsehistory/delete?client=chrome
libchrome:238569  Microsoft.Sync.GetHistoryRequestLatency
libchrome:238570  Microsoft.Sync.ExpireHistoryRequestLatency
libchrome:238571  Microsoft.Sync.BatchExpireHistoryRequestLatency
libchrome:238572  application/json
libchrome:238574  Microsoft.WebHistory.GetHistoryAuthError
```

以及（`libchrome:238861`–`238877`）Edge 的另外两个微软云服务：

```
libchrome:238859  https://edge.microsoft.com/passwordbreachservice/
libchrome:238860  breach_detection
libchrome:238861  Bearer                       ← 注意有尾随空格
libchrome:238862  application/json
libchrome:238891  application/x-protobuf
libchrome:238893  https://edge.microsoft.com/autofillservice/
```

#### ✅ 确证 / 🟡 推断

- ✅ 端点：`/v1/feeds/me/syncEntities`、`/v1/feeds/me/notification/telemetry`、
  `/v1/diagnosticData/Diagnostic.SendCheckResult()`、`/v1/me/browsehistory/{lookup,delete}?client=chrome`。
- ✅ 自定义头：**`X-AFS-ClientInfo`**（值模板就是 `platform=%s; os=%s; osVer=%s; …`）、
  **`X-AFS-CV`**（关联向量 correlation vector）、**`X-AFS-Tracking`**、**`sync-token`**。
- ✅ 服务端会回速率限制相关的头 `X-RateLimit-Debug-UpdateRule`，规则名 `PerUserAnaheimWrites_default` /
  `PerUserAnaheimReads_default` —— 说明 **AFS = Anaheim Feed Service**。
- 🟡 **AFS 生产主机**：dump 里只找到 PPE 版 `ppe.activity.windows.com`；生产版大概率是
  `edge.activity.windows.com`（该域名出现在 `libchrome:61240` 的"允许域名"清单里，
  且与 `ppe.` 前缀对称）。**未直接确证。**
- 🟡 `/v1/feeds/me/syncEntities` 与 `/command/` 是**两套独立协议**：AFS 侧是 JSON（`%s:%s ` + `application/json`），
  `/command/` 侧是 protobuf。两者**不能互相替代**。

### 2.3 通道 C：Edge Cloud Messaging（WebSocket）

整块同源，`libchrome:249111`–`249147`：

```
libchrome:249111  sync.cache_guid
libchrome:249112  edge.services.account_id
libchrome:249113  sync.transport_data_per_account
libchrome:249114  reauthentication
libchrome:249115  authorization
libchrome:249116  category
libchrome:249117  reconnect-url
libchrome:249118  ping-interval
libchrome:249119  authentication-expire-time
libchrome:249120  supported-features
libchrome:249121  error-code
libchrome:249122  error-text
libchrome:249123  status
libchrome:249124  headers
libchrome:249125  payload
libchrome:249126  {"category":"system", "type":"pong"}
libchrome:249127  edge.microsoft.com
libchrome:249128  request-ack
libchrome:249129  request
libchrome:249130  target
libchrome:249131  HandleRegistrations
libchrome:249132  cloud-messaging-authorization
libchrome:249133  platform=%s; os=%s; osVer=%s; app=%s; appVer=%s; appChannel=%s; ecmVer=2.1
libchrome:249134  X-Client-ID
libchrome:249135  X-AFS-ClientInfo
libchrome:249136  X-Has-Multiple-Syncing-Devices
libchrome:249137  stable
libchrome:249138  canary
libchrome:249139  unknown
libchrome:249140  edge_cloud_messaging.cached_reconnect_url.url
libchrome:249141  edge_cloud_messaging.cached_reconnect_url.time
libchrome:249142  edge_cloud_messaging.cached_target_token.target_token
libchrome:249143  edge_cloud_messaging.cached_target_token.cv
libchrome:249144  edge_cloud_messaging.cached_target_token.time
libchrome:249145  wss://access-point-ppe.cloudmessaging.edge.microsoft.com
libchrome:249146  wss://access-point.cloudmessaging.edge.microsoft.com
libchrome:249147  cloud-messaging-url
```

#### ✅ 确证

- WebSocket 端点（生产）：`wss://access-point.cloudmessaging.edge.microsoft.com`；
  PPE：`wss://access-point-ppe.cloudmessaging.edge.microsoft.com`；可用 `--cloud-messaging-url` 覆盖。
- 协议是**文本 JSON 帧**：心跳 ping/pong 用 `{"category":"system", "type":"pong"}`；
  消息字段 `category / request / request-ack / target / payload / headers / status / error-code / error-text`；
  连接/鉴权元数据 `reconnect-url / ping-interval / authentication-expire-time / supported-features / authorization / reauthentication`。
- 请求头：`X-Client-ID`、`X-AFS-ClientInfo`、`X-Has-Multiple-Syncing-Devices`，UA 附加指纹
  `ecmVer=2.1`。
- 身份用 **`sync.cache_guid`**（不是 device name，也不是 client_id）。
- **推送通道被 Edge 自己替换掉了**：Chromium 的 FCM 相关串仍在（`Sync.InvalidationFcmDeliveryLatency`
  `libchrome:6610`、`FCMInvalidations.*` `libchrome:23684`），但 Edge 新增了整套 ECM。

#### 🟡 推断

- ECM 的消息是**同步 nudge + 设备注册**（`HandleRegistrations`、`sync.notification`
  `libchrome:235974` 附近还有 `serverSendTimestamp` / `protocolVersion` / `messages` / `version`）。
- 通道 C 与通道 A 的关系：ECM 收到通知后触发 Channel A 的 `GetUpdates`（而非直接传数据）。

---

## 3. 【Q2】HTTP 层细节

### 3.1 鉴权

| 证据 | 内容 | 位置 |
|---|---|---|
| `Bearer `（带尾随空格） | 多处独立串 | libchrome:159502 / 190914 / 190916 / 193275 / 193368 / 193373 / 194419 / 195906 / 196937 / 200539 / 200596 / 201091 / 238864 / 238861 / 240198 / 249115 / 249760 附近 |
| `Authorization` | 头名 | libchrome:187775 / 188099 / 249760 |
| `browser.user_level_features_context` | Edge 登录上下文 pref | libchrome:235990 |

**AAD/Azure AD 资源与 scope（确证）**：

```
libchrome:48771   https://edgesync.microsoft.com/UserSettings.ReadWrite.CreatedByApp.Secure https://edgesync.microsoft.com/UserSettings.ReadWrite.CreatedByApp https://edgesync.microsoft.com/UserActivity.ReadWrite.CreatedByApp
libchrome:82743   https://edgesync.microsoft.com/Secrets.ReadWrite.CreatedByApp.Secure
libchrome:61223   https://edgesync.microsoft.com
libchrome:48770   https://passwordbreach.microsoft.com/UserSettings.ReadWrite.CreatedByApp.Secure https://passwordbreach.microsoft.com/UserSettings.ReadWrite.CreatedByApp https://passwordbreach.microsoft.com/UserActivity.ReadWrite.CreatedByApp
```

- ✅ **AAD 账号**：资源 `https://edgesync.microsoft.com`，scope
  `UserSettings.ReadWrite.CreatedByApp`（+ `.Secure` 变体）/ `UserActivity.ReadWrite.CreatedByApp`。
- ✅ **凭据服务**用 `Secrets.ReadWrite.CreatedByApp.Secure`。
- 🟡 `.Secure` 后缀是 Edge/OneAuth 的"需要设备绑定/受保护"标记，普通 scope 是它的非安全版本。

**MSA（个人微软账号）路径（确证）**：

```
libchrome:235576  https://login.live.com/ppsecure/GetUserKeyData.srf
libchrome:235577  encryption-key-url
libchrome:124529  service::http://Passport.NET/purpose::PURPOSE_GETKEYDATA_ANAHEIM
libchrome:6287    AnaheimCredentialKey
```

- ✅ `--encryption-key-url` 默认 `https://login.live.com/ppsecure/GetUserKeyData.srf`。
- ✅ 取该 URL 的 token 用的 OneAuth purpose 是 `service::http://Passport.NET/purpose::PURPOSE_GETKEYDATA_ANAHEIM`。

**Token 名称 / 缓存（确证）**：

```
libchrome:235883  Microsoft.Sync.AuthManager.%s.FetchToken.%s.Timeout
libchrome:235881  Microsoft.Sync.AuthManager.%s.FetchToken.TimeWindow.%s
libchrome:235879  Microsoft.Sync.AuthManager.LastAuthError.
libchrome:235880  Microsoft.Auth.InvalidateAccessToken.
libchrome:235884  SyncKeyToken
libchrome:235885  SyncTokenNew
libchrome:235886  Microsoft.Sync.AuthManager.UserInteractionFlowResult
libchrome:79755   SyncAuthManager::EdgeLogTokenErrorState
libchrome:40500   SyncAuthManager::SetLastAuthError
```

### 3.2 请求头清单（Edge 自定义 vs Chromium/Google 继承）

| 头名 | 出现位置 | 归属 | 备注 |
|---|---|---|---|
| `X-AFS-Tracking` | libchrome:235502, **238573** | **Edge 自定义** | 出现在 `SyncServerConnectionManager` 常量块 **和** browsehistory 端点，说明它被同时用于 `/command/` 和 AFS |
| `X-AFS-ClientInfo` | libchrome:235544, 249135 | **Edge 自定义** | 值 = `platform=…; os=…; osVer=…; app=…; appVer=…; appChannel=…; appInstallationId=…; region=…;` |
| `X-AFS-CV` | libchrome:235545 | **Edge 自定义** | 关联向量（correlation vector），配合 `edge_ux_config.latestcorrelationid`（libchrome:235988） |
| `X-Client-ID` | libchrome:249134 | **Edge 自定义** | ECM 用 |
| `X-Has-Multiple-Syncing-Devices` | libchrome:249136 | **Edge 自定义** | ECM 用；开关 pref `msCloudMessagingHasMultipleSyncingDevicesHeader`（libchrome:46991） |
| `sync-token` | libchrome:235546 | **Edge 自定义** | 小写，出现在 AFS 块中 |
| `X-Client-Data` | libchrome:116493(`x-client-data`), 233980, 246258 | **Chromium（variations）** | 仅在 Google 域 + `--append-variations-headers-to-localhost-for-testing`（libchrome:233972）下附加。🟡 **edge.microsoft.com 上很可能不发**，需实测 |
| `X-Goog-*` | libchrome:3633/42781/107520/195121-195123/196386/235995 | Chromium（组件更新/API key 等） | **与同步无关**，不要误用 |
| `Content-Type: application/octet-stream` | libchrome:192820, 196320, 196321, 200335 | 通用 | 见下 |
| `Authorization: Bearer <token>` | 见 3.1 | 通用 | |

⚠️ 注意 `X-Goog-*` 系列全部来自 Chromium 组件更新器 / API key 场景，**不是同步请求头**。

### 3.3 Content-Type / 压缩

- 上游 Chromium 同步一直用 **`application/octet-stream`** 承载 protobuf。
  本 APK 内确实有 `Content-Type: application/octet-stream` 串（libchrome:192820），
  🟡 但**未能把它和 `/command/` 直接绑定**（该串邻近的是扩展/上传代码）。
- 压缩：只有通用的 `Accept-Encoding`（libchrome:74753, 181989, 200332）、
  `gzip, deflate, br`（libchrome:47352）、`gzip, deflate`（libchrome:80274）。
  🟡 Edge 未对同步请求做自定义 gzip 请求体压缩；响应解压由网络栈处理。
- ❌ **无法静态确认**请求体是否 gzip、是否 `Content-Encoding`。

### 3.4 超时 / 重试 / 退避

- 同步调度器：`sync_scheduler_impl.cc`（libchrome:113175）存在，
  `Microsoft.Sync.Scheduler.Configuration.2Min.Timeout`（libchrome:235514）。
- 轮询：pref `sync.short_poll_interval`（libchrome:235869）、`set_sync_poll_interval`、`poll_interval_ms`（libchrome:30745）。
  🟡 上游 Chromium 的默认值是长轮询 30 分钟 / 短轮询 60 秒（`kPollInterval` / `kShortPollInterval`），
  本 APK 未暴露常量数值。
- 退避：`error_backoff_duration_ms`（libchrome:30736）、`exponential_backoff_on_initial_delay`（libchrome:7474）、
  `backoffFactor`（libchrome:39582）、`backoff_entry`（libchrome:233868）、`min_retry_backoff`（libchrome:75884）。
- 启动/重试开关（全部在 `libchrome:235970`–`235972`）：

```
libchrome:235970  sync-deferred-startup-timeout-seconds
libchrome:235971  sync-short-initial-retry-override
libchrome:235972  sync-short-nudge-delay-for-test
libchrome:235487  sync-protocol-log-buffer-size
libchrome:235508  sync-fetch-encryption-keys
libchrome:196984  sync-force-enable-data-types-for-test
libchrome:248942  sync-include-specifics
```

❌ **具体超时数值（毫秒）一律无法从 APK 静态确认**，需抓包或读 `edge://sync-internals`。

### 3.5 其他传输层事实

- 网络实现是 Chromium 的 `HttpBridge`（`libchrome:114327  ../../components/sync/engine/net/http_bridge.cc`）。
  🟡 Android 上它走 Chromium 网络栈，**不是** `HttpURLConnection`/OkHttp —— 这意味着 Via 侧**无法**
  1:1 复刻底层行为，但**协议层（URL/头/体）可以对齐**。
- `SyncServerConnectionManager` 的 HTTP 状态码被上报为
  `Microsoft.Sync.ServerConnectionManager.HttpStatusCode`（libchrome:235504）。
- 上传/下载体积与延迟指标（说明请求/响应都是单个 protobuf 消息，不是流式分片）：

```
libchrome:33457   Microsoft.Sync.PostedClientToServerMessageSizeBytes
libchrome:92109   Sync.PostedClientToServerMessage
libchrome:6706    Sync.PostedClientToServerMessageLatency
libchrome:84368   Sync.PostedClientToServerMessagePartialErrorDataType
libchrome:136984  Sync.PostedClientToServerMessageError2
libchrome:159201  PostClientToServerMessage() failed during GetUpdates with error
```

---

## 4. 【Q3】客户端标识与设备信息

### 4.1 `client_id`

- 仅用于 URL 查询参数（`?client_id=`，libchrome:132676）。
- 同一个常量 `client_id` 也在 `SyncServerConnectionManager` 常量块（libchrome:235507）。
- ❌ **生成算法无法静态确认**。dump 中**不存在** `sync.client_id` 这个 pref key。
  🟡 上游 Chromium 的 `client_id` 是 26 字符随机串（不是 GUID），
  存储在 sync 的 "credentials" 里而不是普通 pref；本 APK 里 dump 出的 sync pref key 清单
  （见 4.3）**没有** `sync.client_id`，支持"它由 `SyncCredentials` 运行时生成/缓存"这一推断。
- 🟡 **重要**：`client_id` 与 `cache_guid` 是**两个不同的值**，不要混用。URL 里用 `client_id`；
  ECM 推送和 DeviceInfo 用 `cache_guid`。

### 4.2 `cache_guid` / 设备标识

```
libchrome:235870  sync.cache_guid
libchrome:238581  sync.local_device_guids_with_timestamp
libchrome:238582  cache_guid
libchrome:238583  timestamp
libchrome:238584  DeviceInfo_
libchrome:238585  DESKTOP-
libchrome:238586  LAPTOP-
libchrome:98720   DeviceInfoPrefs::IsRecentLocalCacheGuid
libchrome:98721   DeviceInfoPrefs::AddLocalCacheGuid
libchrome:114354  ../../components/sync_device_info/device_info_sync_bridge.cc
libchrome:113490  ../../components/sync_device_info/local_device_info_util.cc
```

- ✅ `sync.cache_guid` 是同步的**本机标识**（GUID）。
- ✅ pref `sync.local_device_guids_with_timestamp` 是一个 {cache_guid → timestamp} 映射，
  由 `DeviceInfoPrefs` 维护（用于判断"最近活跃的本地 cache guid"，即 `IsRecentLocalCacheGuid`）。
- ✅ `DeviceInfo_` 是设备实体名的前缀（上游 `device_info_sync_bridge.cc` 里
  `non_unique_name = "DeviceInfo_" + cache_guid`）。🟡 邻近性推断，但 `DeviceInfo_` 这个串本身极特殊。
- 🟡 `DESKTOP-` / `LAPTOP-` 是 Windows 风格的设备名前缀。**Android 上一般不会命中**
  （这段代码来自共享的 `local_device_info_util.cc`，只是被一起编进来了）。

### 4.3 同步 pref 全清单（✅ 确证，`libchrome:235850`–`235953` 连续块）

这是**最有价值的一段**：它把 Edge 的同步状态存储、类型开关、加密状态全列出来了。

**传输/身份类**
```
sync.gaia_id                              libchrome:235865
sync.transport_data_per_account           libchrome:235866
sync.last_synced_time                     libchrome:235867
sync.last_poll_time                       libchrome:235868
sync.short_poll_interval                  libchrome:235869
sync.cache_guid                           libchrome:235870
sync.birthday                             libchrome:235871
sync.bag_of_chips                         libchrome:235872
sync.device_statistics_timestamp          libchrome:235850
```
> ✅ `sync.birthday` 对应 protobuf 的 `store_birthday`（libchrome:7647），`sync.bag_of_chips`
> 对应 `ChipBag` / `bag_of_chips`。二者都参与"防止误连到别的服务器"的校验。
> 上游有一个著名的错误 `ServerReturnNotMyBirthday`（libchrome:7648 附近），
> 即服务器返回的 birthday 与本地不一致 → 客户端拒绝同步。

**加密/密钥状态类**
```
sync.cached_passphrase_type                             libchrome:235915
sync.cached_trusted_vault_auto_upgrade_experiment_group libchrome:235916
sync.encryption_bootstrap_token                         libchrome:235917
sync.keystore_encryption_key_state                      libchrome:235918
sync.cached_persistent_auth_error                       libchrome:235919
sync.encryption_bootstrap_token_per_account             libchrome:235920
sync.encryption_bootstrap_token_per_account_migration_done  libchrome:235921
sync.passphrase_prompt_muted_product_version            libchrome:235923
```

**Edge 本地（非账号）同步后端**
```
sync.local_sync_backend_dir      libchrome:196980
sync.enable_local_sync_backend   libchrome:198876
sync.edge_account_type           libchrome:198879
```
🟡 Edge 支持"不登录账号只在本地同步"的模式，后端目录由 `sync.local_sync_backend_dir` 指定。

**类型开关（14 个类型 × 3 组）**
见第 8 节。

**其他**
```
sync.managed / sync.not_recommended / sync.forced / sync.force_types  libchrome:235900-235903
sync.types_list_disabled / sync.force_types_list / sync.disabled_types_list  libchrome:235904, 235894-235895
sync.edge_feature_usage / sync.edge_user_opted_in                     libchrome:235905-235906
sync.keep_everything_synced / sync.selected_types_per_account         libchrome:235908-235909
sync.has_been_enabled / sync.sync_to_signin_migration_state           libchrome:235910-235911
sync.ai_subscription_tier                                             libchrome:194433
sync.session_sync_guid                                                libchrome:198777
sync.history_type_disabled                                            libchrome:205509
sync.demographics / sync.demographics_birth_year_offset               libchrome:235991-235992
```

### 4.4 DeviceInfo / DeviceInfoSpecifics 字段

✅ 已确证存在于本二进制的字段名（protobuf 描述符碎片）：

| 字段串 | 行号 |
|---|---|
| `cache_guid` | libchrome:238582 |
| `client_name` | libchrome:87266 |
| `sync_user_agent` | libchrome:18443 |
| `full_hardware_class` | libchrome:27177 |
| `signin_scoped_device_id` | libchrome:99604 |
| `device_type` | libchrome:83482 |
| `last_updated_timestamp` | libchrome:49192 |
| `google_play_services_version_info` | libchrome:51112 |
| `chrome_version_info` | libchrome:51113 |
| `send_tab_to_self_receiving_enabled` | libchrome:104120 |
| `os_version` | libchrome:249880 |

`sync_pb.DeviceInfoSpecifics` 消息本体 ✅ 确证（libchrome:297864 附近，完整名单见第 5 节）。
Java 侧 ✅ `org/chromium/components/sync/protocol/DeviceInfoSpecifics$ClientVersionInfoCase`（class_index）。

❌ **未确认**：`device_name`、`model`、`manufacturer`、`platform`、`mobile`、`pulse_interval`
等短字段名——要么 < 6 字符（`model` 5、`mobile` 6 但未命中），要么被链接器合并散落。
再次强调：**不等于不存在**。

### 4.5 Edge 特有：同步诊断与可靠性上报

```
libchrome:235535  Microsoft.Sync.DataConsistencyCompare.BookmarkWithoutGhost
libchrome:235536  Microsoft.Sync.DataConsistencyCompare.Result.%s
libchrome:235537  v1/diagnosticData/Diagnostic.SendCheckResult()
libchrome:235538  Microsoft.Sync.DataConsistencyCompare.Result.CountNonZero.%s
libchrome:235539  Microsoft.Sync.DataConsistencyCompare.FirstCheckResult.%s
libchrome:235540  Microsoft.Sync.DataConsistencyCompare.FinalCheckResult.%s
libchrome:235541  Microsoft.Sync.DataConsistencyCompare.PostConsistentResult.%s
libchrome:235596  sync_diagnostic.log
libchrome:183302  EdgeSyncDiagnosticsV1
libchrome:110113  ../../components/sync/engine/edge_sync_diagnostic_request.cc
libchrome:110985  ../../components/sync/engine/edge_sync_diagnostic_processor.cc
libchrome:111366  ../../components/sync/engine/edge_reliability_telemetry_helper.cc
libchrome:114450  ../../chrome/browser/edge_feedback/system_logs/log_sources/edge_sync_log_source.cc
libchrome:68160   IncludeSyncDiagnosticLogInFeedback
libchrome:200977  sync_diagnostic_log_file
libchrome:37160   syncer.mojom.SyncDiagnostics
libchrome:84368   Sync.PostedClientToServerMessagePartialErrorDataType
libchrome:58820   Microsoft.Sync.PostedGetUpdatesOrigin
```

- ✅ Edge 增加了**书签一致性自检**：客户端把本地书签的 hash 摘要打包成
  `ClientToServerDiagnosticMessage` → `ConsistencyCheckResultRequest` 发到服务端（AFS `diagnosticData` 端点），
  服务端比对后回 `ConsistencyCheckResultResponse` / `DiagnosticResponse`。
  相关 protobuf（✅ libchrome:298096-298104）：
  `sync_pb.ConsistencyCheckResultRequest`、`sync_pb.BookmarkConsistencyCheckExtraInfo`、
  `sync_pb.ClientToServerDiagnosticMessage`、`sync_pb.ConsistencyCheckResultResponse`、
  `sync_pb.CommandInfo`、`sync_pb.ClientToServerDiagnosticResponse`、`sync_pb.BasicDataInfos`、
  `sync_pb.BookmarkExtraInfo`、`sync_pb.ExcludeHashItem`、`sync_pb.DiagnosticResponse`、
  `sync_pb.DataDetailEntity`、`sync_pb.DataDetail`。
- ✅ `edge_reliability_telemetry_helper.cc` 负责可靠性上报；端点疑似
  `/v1/feeds/me/notification/telemetry`（`telemetry_period` 控制周期）。

---

## 5. 【Q4】protobuf 结构

### 5.1 `sync_pb.*` 消息总览

`libchrome` 中共 **373 条** `sync_pb.` 字符串常量（`grep -c "sync_pb\."`），集中在
`libchrome:297770`–`298142`。Java 侧共 **1038 个** `org/chromium/components/sync/protocol/*`
类（含内部类，全部在 `dex/chrome/classes3.dex`，少数在 `classes2.dex`）。

#### 传输层核心消息（✅ 全部确证）

```
sync_pb.CommitMessage                             libchrome:298010
sync_pb.GetUpdatesMessage                         libchrome:298011
sync_pb.ClearServerDataMessage                    libchrome:298012
sync_pb.ClearServerDataResponse                   libchrome:298013
sync_pb.EncryptionKeyMessage                      libchrome:298009
sync_pb.ClientCommand                             libchrome:297844
sync_pb.ChipBag                                   libchrome:298014
sync_pb.ClientStatus                              libchrome:298015
sync_pb.ClientToServerMessage                     libchrome:298016
sync_pb.CommitResponse.EntryResponse.DatatypeSpecificError    libchrome:298017
sync_pb.CommitResponse.EntryResponse              libchrome:298018
sync_pb.CommitResponse                            libchrome:298019
sync_pb.GetUpdatesResponse                        libchrome:298020
sync_pb.ClientToServerResponse.Error.DisableDataType          libchrome:298021
sync_pb.ClientToServerResponse.Error              libchrome:298022
sync_pb.ClientToServerResponse                    libchrome:298023
sync_pb.SyncEntity                                libchrome:298028
sync_pb.EdgeSyncEntity                            libchrome:298130
```

#### 进度/状态

```
sync_pb.DataTypeProgressMarker   libchrome:297864     ← 注意：不是 DataTypeProgressToken
sync_pb.DataTypeContext          libchrome:297865
sync_pb.DataTypeState.Invalidation   libchrome:297869
sync_pb.DataTypeState            libchrome:297870
sync_pb.DataTypeStoreSchemaDescriptor  libchrome:297871
sync_pb.DeviceInfoSpecifics      libchrome:297873
sync_pb.GetUpdatesCallerInfo     libchrome:297908
sync_pb.GetUpdateTriggers        libchrome:297868
sync_pb.GarbageCollectionDirective  libchrome:297867
sync_pb.EntityMetadata           libchrome:297887
sync_pb.EntityMetadata.CollaborationMetadata  libchrome:297886
sync_pb.EntityMetadata.CollaborationMetadata.Attribution  libchrome:297885
sync_pb.EntitySpecifics          libchrome:297888
sync_pb.EmptySpecifics           libchrome:297889
sync_pb.ClientCommand            libchrome:297844
sync_pb.UniquePosition           libchrome:298052
sync_pb.LoopbackServerEntity / LoopbackServerProto   libchrome:297920-297921
sync_pb.DebugInfo / SyncCycleCompletedEventInfo / DatatypeAssociationStats / TypeActions / ActionList / SyncTypeSpecificAction
                                 libchrome:297845-297852（DebugInfo=297852）
sync_pb.SavedTabGroup / EdgeOptionalCustomColor / SavedTabGroupTab / SavedTabGroupSpecifics
                                 libchrome:297967-297970
```

#### Edge 私有消息（✅ 确证，这是 Edge 相对上游的增量）

```
sync_pb.MicrosoftCollectionSpecifics          libchrome:298107
sync_pb.MicrosoftCollectionDetails            libchrome:298108
sync_pb.MicrosoftCollectionItemDetails        libchrome:298109
sync_pb.MicrosoftCollectionsMetadataSpecifics libchrome:298110
sync_pb.EdgeConnectedAccountInfo              libchrome:298111
sync_pb.EdgeCookie                            libchrome:298116
sync_pb.EdgeCookie.UnguessableToken           libchrome:298112
sync_pb.EdgeCookie.Origin                     libchrome:298113
sync_pb.EdgeCookie.SchemefulSite              libchrome:298114
sync_pb.EdgeCookie.CookiePartitionKey         libchrome:298115
sync_pb.EdgeEDropSpecifics                    libchrome:298117
sync_pb.EdgeEntityMetadata                    libchrome:298118
sync_pb.EdgeTopicCategory                     libchrome:298119
sync_pb.EdgeHistorySpecifics                  libchrome:298120
sync_pb.ExtendedInfo                         libchrome:298121
sync_pb.EdgeHubAppSpecifics                   libchrome:298122
sync_pb.EdgeHubAppUsageSpecifics              libchrome:298123
sync_pb.JourneyPageContentData                libchrome:298124
sync_pb.JourneyEventData                      libchrome:298125
sync_pb.JourneyData                          libchrome:298126
sync_pb.EdgeJourneySpecifics                  libchrome:298127
sync_pb.EdgePimEncryptedValue.KeyInfo         libchrome:298128
sync_pb.EdgePimEncryptedValue                 libchrome:298129
sync_pb.EdgeSyncEntity                        libchrome:298130
sync_pb.Barcode                               libchrome:298131
sync_pb.MembershipCard                        libchrome:298132
sync_pb.EventSeat                             libchrome:298133
sync_pb.EdgeEventTicket                       libchrome:298134
sync_pb.BoardingPass                          libchrome:298135
sync_pb.IDCard                                libchrome:298136
sync_pb.GenericCardField                      libchrome:298137
sync_pb.GenericCard                           libchrome:298138
sync_pb.EdgeWalletSpecifics                   libchrome:298139
sync_pb.Workspace                             libchrome:298140
sync_pb.WorkspaceTab                          libchrome:298141
sync_pb.EdgeWorkspaceSpecifics                libchrome:298142
sync_pb.AutofillEdgeExtendedMetaDataSpecifics libchrome:297816
sync_pb.EdgeAutofillFormFieldDataSpecifics    libchrome:297817
sync_pb.EdgeAutofillFormFieldClusterDataSpecifics  libchrome:297818
sync_pb.UserConsentTypes.EdgeContinuousImportConsent  libchrome:298063
sync_pb.ConsistencyCheckResultRequest / BookmarkConsistencyCheckExtraInfo /
sync_pb.ClientToServerDiagnosticMessage / ConsistencyCheckResultResponse / CommandInfo /
sync_pb.ClientToServerDiagnosticResponse / BasicDataInfos / BookmarkExtraInfo /
sync_pb.ExcludeHashItem / DiagnosticResponse
                                              libchrome:298095-298106
sync_pb.SharedUrlContext                      libchrome:297985
sync_pb.SharedCommentSpecifics                libchrome:297992
（另：`sync_pb.PasswordSpecificsData.EdgePasswordBreachAlertState/EdgePasswordBreachStatus`
  只作为 **Java 内部类**存在 —— `PasswordSpecificsData$EdgePasswordBreachAlertState`
  / `…$EdgePasswordBreachStatus`，见 class_index，未在 libchrome 里找到同名 sync_pb 串）
```

#### Nigori / 密钥（✅）

```
sync_pb.NigoriModel                       libchrome:297925
sync_pb.NigoriLocalData                   libchrome:297926
sync_pb.NigoriKey                         libchrome:297927
sync_pb.NigoriSpecifics                   libchrome:297933
sync_pb.NigoriSpecifics.TrustedVaultDebugInfo  libchrome:297932
sync_pb.CryptographerData                 libchrome:297923
sync_pb.CustomPassphraseKeyDerivationParams    libchrome:297924
sync_pb.CrossUserSharingPrivateKey        libchrome:297928
sync_pb.CrossUserSharingPublicKey         libchrome:297929
sync_pb.EncryptionKeys                    libchrome:297930
sync_pb.TrustedVaultAutoUpgradeExperimentGroup libchrome:297931
sync_pb.KeystoreEncryptionFlags           libchrome:297890
sync_pb.AgileSymmetricKey / AgileSymmetricKeySet / Aes256GcmKey / Chacha20Poly1305Key  libchrome:297771-297775
sync_pb.EncryptedData / EncryptedTabContextContainerSpecifics / EncryptedTabContextItemSpecifics  libchrome:297882-297884
```

### 5.2 字段名（**已确证存在于本二进制**的部分）

以下字段名字符串在 `libchrome` 中 ✅ 存在（`grep -x` 精确匹配）。这一批是**同步特有的长名字**，
几乎不可能来自其他模块，可信度高：

| 字段 | 行号 | 归属（🟡） |
|---|---|---|
| `store_birthday` | 7647 | ClientToServerMessage |
| `position_in_parent` | 17221 | SyncEntity |
| `sync_user_agent` | 18443 | DeviceInfoSpecifics |
| `full_hardware_class` | 27177 | DeviceInfoSpecifics |
| `config_params` | 30564 | CommitMessage |
| `get_updates` | 33732 | ClientToServerMessage |
| `max_entries` | 35264 | GetUpdatesMessage |
| `new_progress_marker` | 44649 | GetUpdatesResponse |
| `from_progress_marker` | 44650 | GetUpdatesMessage |
| `create_mobile_bookmarks_folder` | 46456 | GetUpdatesMessage |
| `auto_upgrade_experiment_group` | 48033 | NigoriSpecifics |
| `last_updated_timestamp` | 49192 | DeviceInfoSpecifics |
| `google_play_services_version_info` | 51112 | DeviceInfoSpecifics |
| `chrome_version_info` | 51113 | DeviceInfoSpecifics |
| `trusted_vault_debug_info` | 51125 | NigoriSpecifics |
| `unique_position` | 53742 | SyncEntity |
| `server_version` | 57854 | SyncEntity |
| `protocol_version` | 57874 | ClientToServerMessage |
| `get_updates_origin` | 58716 | GetUpdatesCallerInfo |
| `keystore_decryptor_token` | 59546 | NigoriSpecifics |
| `cross_user_sharing_public_key` | 6135 | NigoriSpecifics |
| `client_tag_hash` | 69945 | EntityMetadata |
| `parent_id_string` | 73094 | SyncEntity |
| `encrypt_everything` | 74450 | NigoriSpecifics |
| `server_defined_unique_tag` | 75483 | SyncEntity |
| `passphrase_type` | 83438 | NigoriSpecifics |
| `device_type` | 83482 | DeviceInfoSpecifics / SyncEnums |
| `custom_passphrase_time` | 85736 | NigoriSpecifics |
| `client_name` | 87266 | DeviceInfoSpecifics |
| `non_unique_name` | 87405 | SyncEntity |
| `originator_cache_guid` | 98695 | SyncEntity |
| `originator_client_item_id` | 99442 | SyncEntity |
| `insert_after_item_id` | 99444 | SyncEntity |
| `signin_scoped_device_id` | 99604 | DeviceInfoSpecifics |
| `authenticate` | 171450 | ClientToServerMessage（已废弃字段） |
| `os_version` | 249880 | DeviceInfoSpecifics |

**🟡 未命中但极可能存在（因 <6 字符下限或被合并）**：
`share`、`id_string`、`version`、`ctime`、`mtime`、`deleted`、`folder`、`name`、`specifics`、
`cache_guids`、`entries`、`keybag`、`has_keybag`、`client_defined_unique_tag`、`sync_base_url`、
`message_contents`、`is_last_batch`、`device_name`、`model`、`mobile`、`poll_interval`。
→ 需要读上游公开 `.proto` 补齐，或用真机 `edge://sync-internals` 的 protobuf 文本化输出来确认。

### 5.3 command 类型编号（**重点，也是最不确定的一环**）

**Java 侧给了结构的确证**：

```
Lorg/chromium/components/sync/protocol/ClientToServerMessage$Contents;    class_index:76929
Lorg/chromium/components/sync/protocol/ClientToServerMessage;             class_index:76930
Lorg/chromium/components/sync/protocol/ClientToServerResponse;            class_index:76933
Lorg/chromium/components/sync/protocol/ClearServerDataMessage;            class_index:76931 附近
Lorg/chromium/components/sync/protocol/CommitMessage;
Lorg/chromium/components/sync/protocol/GetUpdatesMessage;
```

- ✅ `ClientToServerMessage` 有一个 **oneof** 叫 `Contents`（`$Contents` 是 protobuf-java 给 oneof 生成的 case 枚举类）。
- ✅ 存在 `ClientToServerResponse$Error$DisableDataType`（错误详情里能禁用某个数据类型）。
- ✅ 存在 `CommitResponse$ResponseType`、`CommitResponse$EntryResponse`（提交结果按条目返回）。
- ✅ 存在 `GetUpdatesCallerInfo`（无 `$GetUpdatesSource` 内部类 → 枚举被提升或在其他类里）。

**枚举值集合**：在 `libchrome` 里 ✅ 找到了
`SyncEnums` 的**全部内部枚举名**（Java class_index，说明 proto 里有一个 `SyncEnums` 消息把所有
通用枚举包起来）：

```
SyncEnums$Action                    SyncEnums$BookmarkAddSource
SyncEnums$BrowserType               SyncEnums$ChangeNotificationType
SyncEnums$ClientInactiveDuration    SyncEnums$DeduplicationSource
SyncEnums$DeviceFormFactor          SyncEnums$DeviceType
SyncEnums$EdgeDeletionOrigin        SyncEnums$ErrorType
SyncEnums$GetUpdatesOrigin          SyncEnums$GlicExperimentalTriggeringState
SyncEnums$MobilePromoOnDesktopPromoType   SyncEnums$OsType
SyncEnums$PageTransition            SyncEnums$PageTransitionRedirectType
SyncEnums$PasswordState             SyncEnums$SendTabReceivingType
SyncEnums$SingletonDebugEventType
```
（来源：`class_index.txt` 中 `org/chromium/components/sync/protocol/SyncEnums$*`）

- ✅ `SyncEnums$GetUpdatesOrigin` 存在 → `GetUpdatesCallerInfo.origin` 的枚举叫 `GetUpdatesOrigin`。
- ✅ `SyncEnums$ErrorType` 存在 → `ClientToServerResponse.Error.error_type` 的枚举。
- ✅ `SyncEnums$DeviceType` / `SyncEnums$DeviceFormFactor` / `SyncEnums$OsType` / `SyncEnums$BrowserType`
  → DeviceInfo 上报的枚举。

❌ **枚举的具体数值（COMMIT=1/GET_UPDATES=2/…）无法从本 APK 静态确认**：
`GET_UPDATES`、`CLEAR_SERVER_DATA`、`COMMIT` 这些**枚举值名字符串在 libchrome dump 中一条都没有**
（`grep "GET_UPDATES"` → 0 命中）。原因见 §0.2：protobuf 的枚举值名只存在于**序列化描述符byte数组**中，
`strings` 未必能完整切出来。

> 🟡 **上游 Chromium 公开 schema（`components/sync/protocol/sync.proto`）**，仅供实现时参考，
> **必须真机验证**：
> ```proto
> message ClientToServerMessage {
>   optional string share = 1;
>   optional int32 protocol_version = 2 [default = 31];
>   required Contents message_contents = 3;
>   optional string store_birthday = 4;
>   optional string client_id = 5;
>   optional string sync_base_url = 6 [deprecated = true];
>   optional bool is_last_batch = 7 [deprecated = true];
>   optional ClientStatus client_status = 8;
>   optional ChipBag bag_of_chips = 9;
>   optional CommitMessage commit = 10;
>   optional GetUpdatesMessage get_updates = 11;
>   optional SyncEntity authenticate = 12 [deprecated = true];
>   optional ClearServerDataMessage clear_server_data = 13;
>   optional string earned_gaia_id = 14;
>   enum Contents { COMMIT = 1; GET_UPDATES = 2; AUTHENTICATE = 3; CLEAR_SERVER_DATA = 5; }
> }
> ```
> ⚠️ `protocol_version` 的默认值、`Contents` 的编号在 Edge 153 上**未被本素材证实**。
> 好消息：**`protocol_version` 是客户端上报字段，服务端会容忍多个版本**；
> 实现时应先抓一次真机的请求体，直接读出 Edge 实际发的 `protocol_version`、`store_birthday`、
> `client_id`、`bag_of_chips`。

### 5.4 实战建议

由于 protobuf 是**双向**的，**先抓包拿到一个真实 `ClientToServerMessage` 十六进制体**，
再反推字段编号，比对着上游 `.proto` 盲写可靠得多。抓包点建议：
`edge://sync-internals` → "Events" 面板（本 APK 里 ✅ 同步内部页的 JS 资源存在：
`sync_index.js`(31791)、`sync_search.js`(32388)、`sync_node_browser.js`(32046)、
`edge_sync_log.js`(32390)、`chrome_sync.js`(32452)，
且 `chrome_sync_internals_message_handler.cc`(111773) 被编译进来）。

---

## 6. 【Q5】加密与密钥：Edge 与 Chromium nigori 的差异

### 6.1 两套并存

**（1）Chromium 原生 nigori**（保留，✅ 确证存在）

```
libchrome:235842  NigoriStorageKey
libchrome:235843  NigoriClientTagHash
libchrome:235851  Nigori.bin
libchrome:235852  Microsoft.Sync.Existing.InitialUpdate.Nigori
libchrome:235521  Microsoft.Sync.Syncer.Configure.Download.Nigori
libchrome:235522  Microsoft.Sync.Syncer.Configure.Commit.Nigori
libchrome:113350  ../../components/sync/nigori/nigori_sync_bridge_impl.cc
libchrome:110980  ../../components/sync/nigori/nigori_data_type_processor.cc
libchrome:112743  ../../components/sync/service/sync_service_crypto.cc
libchrome:59546   keystore_decryptor_token
libchrome:74450   encrypt_everything
libchrome:85736   custom_passphrase_time
libchrome:81636   PassphraseType::kKeystorePassphrase
libchrome:51125   trusted_vault_debug_info
libchrome:23305   AddTrustedVaultDecryptionKeys
libchrome:103824  Sync.KeystoreDecryptionFailed
libchrome:19047   Sync.BootstrapTokenEncryptionResult
libchrome:19049   Sync.BootstrapTokenDecryptionResult
libchrome:19048   Sync.NigoriStorageEncryptionResult
libchrome:19051   Sync.NigoriStorageDecryptionResult
```

**（2）Edge 的 AAD 密钥管理器**（✅ 确证，这是与上游最大的不同）

```
libchrome:114871  ../../components/sync/engine/edge_encryption_keys_manager_aad.cc
libchrome:111933  ../../components/sync/engine/edge_encryption_keys_fetcher.cc
```

指标名（Edge 自造，`libchrome`）：

```
libchrome:18902   Microsoft.Sync.EncryptionKeyAAD.ProcessRequestKeyResult
libchrome:18903   Microsoft.Sync.EncryptionKeyAAD.ProcessCommitKeyResult
libchrome:18904   Microsoft.Sync.EncryptionKey.SetKeyResult
libchrome:18905   Microsoft.Sync.EncryptionKeyAAD.PackEncryptionKeyResult
libchrome:19046   Microsoft.Sync.EncryptionKeyAAD.ProtectionJsonResult
libchrome:19168   Microsoft.Sync.EncryptionKeyAAD.ProcessingResult
libchrome:19234   Microsoft.Sync.EncryptionKeyAAD.UnpackKeyMessageResult
libchrome:19356   Microsoft.Sync.EncryptionKeyAAD.InitializeMip.Result
libchrome:19411   Microsoft.Sync.EncryptionKeyAAD.EncryptData.Result
libchrome:19412   Microsoft.Sync.EncryptionKeyAAD.DecryptData.Result
libchrome:19966   Microsoft.Sync.EncryptionKeyAAD.KeyCountOnCommit
libchrome:21929   Microsoft.Sync.EncryptionKeyAAD.KeyCountOnGet
libchrome:41824   Microsoft.Sync.EncryptionKeyAAD.KeyCountOnRollover
libchrome:44794   Microsoft.Sync.EncryptionKeyAAD.UnpackEncryptionKey.CircuitBreaker
libchrome:44795   Microsoft.Sync.EncryptionKeyAAD.PackEncryptionKey.CircuitBreaker
libchrome:23304   ValidateEncryptionKeys
```

MSA（个人账号）分支：

```
libchrome:15368   Microsoft.Sync.EncryptionKeyMSA.KeyCount
libchrome:18979   Microsoft.Sync.EncryptionKeyMSA.ProcessResult
libchrome:19262   Microsoft.Sync.EncryptionKeyMSA.DownloadResult
libchrome:6262    FetchEncryptionKey
libchrome:6263    UpdateEncryptionKey
libchrome:57088   msEdgeSyncEncryptionKeyValidation
libchrome:6264    msEdgeAsyncDownloadEncryptionKey
```

### 6.2 ✅ 确证 / 🟡 推断

- ✅ **两条密钥通路**：`…EncryptionKeyMSA.*`（微软个人账号）走
  `https://login.live.com/ppsecure/GetUserKeyData.srf`（`--encryption-key-url`），
  OneAuth purpose `PURPOSE_GETKEYDATA_ANAHEIM`；
  `…EncryptionKeyAAD.*`（工作/学校账号）走 **MIP（Microsoft Information Protection）**。
- ✅ **MIP 证据**：
  ```
  libchrome:111742  ../../components/edge_mip/mip_callback_handler.cc
  libchrome:113190  ../../components/edge_mip/protection_handler_impl.cc
  libchrome:134087  AAD.Mip.Pack:
  libchrome:134126  Unpack.Mip.Initialize:
  libchrome:134127  Pack.Mip.Initialize:
  libchrome:67733   AAD.Mip.Unpack
  libchrome:19426   MIP Result
  libchrome:101596  MIP service encryption rejected
  libchrome:101597  MIP service decryption rejected
  libchrome:106169  MIP SDK not loaded
  libchrome:140321   is for testing MIP service disabled cases only.
  libchrome:183302  EdgeSyncDiagnosticsV1（同区还有指纹）
  ```
  Java 侧还有 `Lorg/chromium/components/sync/MipHelper;`（class_index:61607，
  **在 `dex/chrome/classes.dex`，不是 classes3** → 说明它是很早加载的核心 bridge）。
- ✅ 加密算法族：`AgileSymmetricKey`（AES-256-GCM / ChaCha20-Poly1305）、`EncryptedData`、
  `AgileSymmetricKeySet`、密钥轮换（`KeyCountOnRollover`）、断路器（CircuitBreaker，MIP 不可用时降级）。
- 🟡 **差异总结**：
  - Chromium 上游：keystore 密钥放在 **Google 的 keystore 服务**（`keystore_decryptor_token` /
    `trusted_vault`），用 Google 账号的密码学服务托管。
  - Edge：**没有 Google keystore**，改为——
    - MSA → 微软的 `GetUserKeyData.srf`（Anaheim 密钥服务）；
    - AAD → **MIP/AIP 服务**（`InitializeMip` → `PackEncryptionKey` / `ProtectionJson`）。
  - 两者都仍复用 Chromium 的 nigori **数据结构**（`NigoriSpecifics`、`EncryptedData`、
    `AgileSymmetricKey`）和 nigori 数据类型本身，只是"密钥从哪来"这一环被替换。
- ✅ **密码等敏感类型走"同一把 nigori 密钥"，但额外套一层 Edge PIM 加密**：
  `sync_pb.EdgePimEncryptedValue` + `EdgePimEncryptedValue.KeyInfo`（libchrome:298128-298129），
  `Microsoft.Sync.EncryptionKeyAAD.EncryptData.Result` / `DecryptData.Result`。
  🟡 推断：PIM（Personal Information Management）加密用于密码/钱包这类字段级加密。
- 🟡 `Microsoft.Sync.EncryptionKey.SetKeyResult`（libchrome:18904，注意**不带 AAD 后缀**）
  可能是 MSA/通用路径。
- ❌ `trusted_vault` / `cross_user_sharing` 在 Edge Android 上的**实际可用性**无法静态确认
  （`AddTrustedVaultDecryptionKeys` 等串存在，但 Android 上这些 UI 通常在
  `edge://settings` 的"高级加密"里；相关 Activity `SyncTrustedVaultProxyActivity`
  ✅ 存在于 class_index）。

---

## 7. 【Q6】本地同步状态存储

### 7.1 ✅ 关键结论：ModelTypeStore 用的是 **LevelDB，不是 SQLite**

**直接证据（同源连续块，`libchrome:235836`–235843）**：

```
libchrome:235836  Microsoft.SpellCheck.LanguageUsed
libchrome:235837  -GlobalMetadata
libchrome:235838  Sync.DataTypeErrorSite.
libchrome:235839  _mts_schema_descriptor
libchrome:235840  Sync Data
libchrome:235841  LevelDB
libchrome:235842  NigoriStorageKey
libchrome:235843  NigoriClientTagHash
```

其他佐证：

```
libchrome:114696  ../../components/sync/model/data_type_store_backend.cc
libchrome:71270   ../../components/sync/model/data_type_store_backend.h
libchrome:113333  ../../components/sync/model/data_type_store_impl.cc
libchrome:113332  ../../components/sync/model/blocking_data_type_store_impl.cc
libchrome:113387  ../../components/sync/model/data_type_store_service_impl.cc
libchrome:26811   Sync.DataTypeStoreBackendInitializationSuccess
libchrome:143499  Sync.DataTypeStoreBackendError.
libchrome:297871  sync_pb.DataTypeStoreSchemaDescriptor
libchrome:103600  DataTypeStore for Drop initialization failed
```

**推断的持久化布局（🟡 基于上游 Chromium + 上述串名）**：

- 目录：`<profile>/Sync Data/`（`kSyncDataFolderName = "Sync Data"`）。
- 每个数据类型一个 **LevelDB 实例**（子目录名 = ModelType 名，如 `Bookmarks`、`Preferences`…）。
- 每个库内的键：
  - `_mts_schema_descriptor` → 序列化的 `sync_pb.DataTypeStoreSchemaDescriptor`（存数据版本号）；
  - `<ModelType>-GlobalMetadata` → 全局元数据（progress marker / 加密状态等）；
  - 其余键 → 序列化的 `sync_pb.EntityMetadata` / 实体数据 blob。
- 另有 nigori 的独立存储键：`NigoriStorageKey` / `NigoriClientTagHash`，以及落盘的 `Nigori.bin`
  （libchrome:235851，🟡 疑似是 **本地（非账号）同步后端**的 nigori 文件）。

> ❗ **所以：题目里预期的 `data_type_store_backend.cc` 建表 SQL 在本版本中并不存在**——
> 该文件已改为 LevelDB 实现。我用 `grep -n "CREATE TABLE metadata\|CREATE TABLE data \|
> CREATE TABLE IF NOT EXISTS metadata"` 复查，**0 命中**。

### 7.2 ✅ 仍然存在的、与同步有关的 SQL（原文抄录）

**(a) 通用同步元数据表**（`SyncableService` 型数据类型的 SQLite 元数据，模式串带 `%s` 占位）：

```
CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s BLOB)
```
（`libchrome:154662`，上下文是 `components/...` 的 SQL 常量池）

```
CREATE TABLE meta(key LONGVARCHAR NOT NULL UNIQUE PRIMARY KEY, value LONGVARCHAR)
```
（`libchrome:154572`）

```
CREATE TABLE edge_meta(key LONGVARCHAR NOT NULL UNIQUE PRIMARY KEY, value LONGVARCHAR)
```
（`libchrome:154571`，Edge 自己的 meta 表）

```
INSERT OR REPLACE INTO meta(key,value) VALUES(?,?)
```
（`libchrome:154716`）

**(b) History 的同步元数据表**（sync 与 history 直连的那张表）：

```
CREATE TABLE history_sync_metadata (storage_key INTEGER PRIMARY KEY NOT NULL, value BLOB)
```
（`libchrome:154665`）

配套 DML：

```
SELECT storage_key, value FROM history_sync_metadata
```
（`libchrome:116184`）

```
DELETE FROM history_sync_metadata WHERE storage_key=?
```
（`libchrome:131204`）

**(c) 通用 metadata/entity 读写（由 `blocking_data_type_store` / `syncable_service` 使用）：**

```
SELECT storage_key, metadata FROM %s
CREATE TABLE %s (%s VARCHAR PRIMARY KEY, %s VARCHAR)
CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s VARCHAR, %s VARCHAR)
CREATE TABLE %s (%s VARCHAR PRIMARY KEY, %s VARCHAR, %s VARCHAR)
DELETE FROM %s WHERE storage_key=?
model_type=? AND storage_key=?
```
（`libchrome:38359`, `154573`–`154575`, `131203`, `131205`）

**(d) Edge Collections 的 SQLite（通道 B 的本地库，非 `/command/`）** —
如果要在 Via 里实现 collections 同步，这套表就是服务端 `syncEntities` 的本地镜像：

```
CREATE TABLE IF NOT EXISTS collections ( id LONGVARCHAR PRIMARY KEY, date_created REAL NOT NULL, date_modified REAL NOT NULL, title LONGVARCHAR NOT NULL, position INTEGER NOT NULL, is_syncable INTEGER DEFAULT 1)
CREATE TABLE IF NOT EXISTS items ( id LONGVARCHAR PRIMARY KEY, date_created REAL NOT NULL, date_modified REAL NOT NULL, title LONGVARCHAR, source BLOB, entity_blob BLOB, favicon_url LONGVARCHAR REFERENCES favicons(url) ON DELETE CASCADE, canonical_image_data BLOB, canonical_image_url LONGVARCHAR, text_content LONGVARCHAR, html_content LONGVARCHAR, type LONGVARCHAR, progressing INTEGER, is_syncable INTEGER DEFAULT 1)
CREATE TABLE IF NOT EXISTS collections_sync ( collection_id LONGVARCHAR, is_syncable INTEGER DEFAULT 1, server_id LONGVARCHAR NULL, date_last_synced REAL NULL, FOREIGN KEY(collection_id) REFERENCES collections(id))
CREATE TABLE IF NOT EXISTS items_sync ( item_id LONGVARCHAR, is_syncable INTEGER DEFAULT 1, server_id LONGVARCHAR NULL, date_last_synced REAL NULL, FOREIGN KEY(item_id) REFERENCES items(id))
CREATE TABLE IF NOT EXISTS collections_items_relationship ( item_id LONGVARCHAR NOT NULL REFERENCES items(id) ON DELETE CASCADE, parent_id LONGVARCHAR NOT NULL REFERENCES collections(id) ON DELETE CASCADE, position INTEGER NOT NULL)
CREATE TABLE IF NOT EXISTS favicons ( url LONGVARCHAR PRIMARY KEY, data BLOB)
CREATE TABLE IF NOT EXISTS items_offline_data ( item_id LONGVARCHAR NOT NULL REFERENCES items(id) ON DELETE CASCADE PRIMARY KEY, offline_file_data LONGVARCHAR NOT NULL)
CREATE TABLE IF NOT EXISTS comments ( id LONGVARCHAR PRIMARY KEY, parent_id LONGVARCHAR NOT NULL, text LONGVARCHAR NOT NULL, properties BLOB, FOREIGN KEY (parent_id) REFERENCES items(id) ON DELETE CASCADE)
CREATE TABLE IF NOT EXISTS collections_prism ( id LONGVARCHAR PRIMARY KEY, date_modified REAL NOT NULL, title LONGVARCHAR NOT NULL)
```
（`libchrome:240109`–`240116`、`240120`）

**(e) Edge Send-Tab / e-drop 的消息表**（`sync_entity` 本地镜像）：

```
CREATE TABLE IF NOT EXISTS messages ( id LONGVARCHAR PRIMARY KEY, date_created REAL NOT NULL, device_id LONGVARCHAR NOT NULL, device_name LONGVARCHAR NOT NULL, device_type INTEGER NOT NULL, content LONGVARCHAR NOT NULL, content_url LONGVARCHAR, message_type INTEGER NOT NULL, user_id LONGVARCHAR NOT NULL, extended_info LONGVARCHAR)
```
（`libchrome:246299`）

**(f) 其他相关但非同步主链路**：
```
CREATE TABLE IF NOT EXISTS secure_payment_confirmation_browser_bound_key (...)
SELECT relying_party_id, credential_id, browser_bound_key_id, last_used FROM secure_payment_confirmation_browser_bound_key
```
（`libchrome:6131`）

### 7.3 相关开关/日志

```
libchrome:235487  sync-protocol-log-buffer-size
libchrome:235596  sync_diagnostic.log
libchrome:200977  sync_diagnostic_log_file
libchrome:196980  sync.local_sync_backend_dir
libchrome:198876  sync.enable_local_sync_backend
```

---

## 8. 【Q7】同步的数据类型集合

### 8.1 ✅ 确证的 Edge 类型清单（三组 pref 一一对应，`libchrome:235925`–235966）

| # | pref 前缀 `<T>` | `sync.<T>` | `sync.<T>_toggled` | `sync.<T>_edge_supported` |
|---|---|---|---|---|
| 1 | `bookmarks` | 235925 | 235940 | 235954 |
| 2 | `preferences` | 235926 | 235941 | 235955 |
| 3 | `passwords` | 235927 | 235942 | 235956 |
| 4 | `autofill` | 235928 | 235943 | 235957 |
| 5 | `themes` | 235929 | 235944 | 235958 |
| 6 | `typed_urls` | 235930 | 235945 | — （history 走 `history`） |
| 7 | `extensions` | 235931 | 235946 | 235960 |
| 8 | `apps` | 235932 | 235947 | 235959 |
| 9 | `reading_list` | 235933 | 235948 | 235961 |
| 10 | `tabs` | 235934 | 235949 | 235964 |
| 11 | `collections` | 235935 | 235950 | 235962 |
| 12 | `edge_wallet` | 235936 | 235951 | 235965 |
| 13 | `edge_workspaces` | 235937 | 235952 | 235966 |
| 14 | `cookies` | 235938 | 235953 | 235967 |

（`history` 出现在 `sync.history_type_disabled`(205509) 和 `_toggled`(235945)、
`sync.history_edge_supported`(235963)；`typed_urls` 是它的旧名。）

### 8.2 ✅ 面向用户的显示名（Edge 的 UI 名 → 内部类型）

`libchrome:235582`–235594，**接连排列**，是 Edge 的 data type 名称映射表：

```
libchrome:235582  addressesAndMore
libchrome:235583  cookies
libchrome:235584  extensions
libchrome:235585  favorites          ← BOOKMARKS
libchrome:235586  history
libchrome:235587  openTabs           ← SESSIONS / TABS
libchrome:235588  passwords
libchrome:235589  readingList
libchrome:235590  settings           ← PREFERENCES
libchrome:235591  themes
libchrome:235592  collections
libchrome:235593  edgeWallet
libchrome:235594  edgeWorkspaces
```

### 8.3 ✅ 其他类型名证据

```
libchrome:12751   reading_list
libchrome:4513    edge_history
libchrome:21398   autofill_wallet
libchrome:21399   edge_wallet
libchrome:51082   contact_info
libchrome:52940   product_comparison
libchrome:55625   wifi_configuration
libchrome:66113   webauthn_credential
libchrome:66388   workspace_desk
libchrome:66856   web_apk
libchrome:75767   send_tab_to_self
libchrome:92023   sharing_message
libchrome:96019   edge_workspace
libchrome:106852  ai_thread
libchrome:116359  shared_tab_group_account_data
libchrome:116392  shared_tab_group_data
libchrome:125579  NIGORI
libchrome:127300  AUTOFILL_PROFILE
libchrome:51568   Device Info
libchrome:13020   Reading List
```

### 8.4 Android 端的可见子集（🟡 交叉验证）

Java 侧 `org/chromium/components/sync/protocol/*` **1038 个类全部存在**（`classes3.dex` 为主），
即 **protobuf 定义是全量编入的**，不能据此区分平台子集。
真正的平台差异在 **bridge/controller** 层：

- ✅ 有 `components/sync_bookmarks/*`（书签）、`components/sync_preferences/*`（偏好）、
  `components/sync_device_info/*`（设备）、`components/sync_sessions/*`（标签页）、
  `components/sync_edge_collections/*`（collections）、`components/sync_tab_context/*`（加密标签上下文）。
- 🟡 未见 Android 侧的 Windows 专属 bridge（如 `wifi_configuration`、`printer`、`workspace_desk`）——
  它们存在 protobuf 定义但在 Android 上不会激活。
- ✅ Java 侧 UI 面：`ManageSyncSettings`、`PassphraseActivity`、`PassphraseTypeDialogFragment`、
  `SyncTrustedVaultProxyActivity`、`GoogleServicesSettings`、`BatchUploadCardPreference`
  （均 `classes2.dex`）。
- ✅ **Android 实际会用的类型（推断自 pref + UI）**：
  `favorites(bookmarks)`、`settings(preferences)`、`passwords`、`addressesAndMore(autofill)`、
  `themes`、`history`、`openTabs(sessions)`、`readingList`、`extensions`、`apps`、
  `collections`、`edgeWallet`、`edgeWorkspaces`、`cookies`（共 14）。
  🟡 `extensions`/`apps` 在 Android 上基本是 no-op；`favorites`/`settings`/`passwords`/`history`/
  `openTabs`/`readingList` 是 Via 最可能用到的。

---

## 9. ❌ 无法从 APK 静态确认、**必须真机抓包 / 登录实测**的清单

按对实现的关键程度排序：

1. **`/command/` 的最终 URL**（base 与 path 的拼接、是否 `/sync/command/`、是否带尾随斜杠、是否有额外段）。
   → mitmproxy 抓 `edge.microsoft.com`。
2. **`ClientToServerMessage` / `ClientToServerResponse` 的字段编号与 `Contents` 枚举值**。
   → 抓一个真实请求体的 hex，直接反推。
3. **`protocol_version` 实际取值**、`store_birthday` 与 `bag_of_chips` 的格式/生成方式。
4. **`client_id` 的实际取值格式与生成算法**（长度、字符集、是否 26 字符 base32）。
5. **`Authorization: Bearer` 的获取**：AAD 走哪个 client_id / authority / redirect_uri；
   scope 具体用哪个变体（带不带 `.Secure`）；refresh token 生命周期。
   → 用 Fiddler/mitmproxy 抓 `login.microsoftonline.com` / `edgesync.microsoft.com`。
6. **MSA 路径下 `GetUserKeyData.srf` 的请求/响应格式**（是否 POST、body 是 JSON 还是表单、
   返回的密钥结构）。
7. **MIP 服务的具体调用链**（`InitializeMip` 的参数、`ProtectionJson` 的内容、
   `PackEncryptionKey` 的输入输出；是否有设备绑定/attestation）。
   → 这部分在 `edge_mip` 组件里，且很可能有 native SDK 依赖，**Via 重实现难度最高**。
8. **`/v1/feeds/me/syncEntities` 的 JSON schema**（请求体、响应体、分页、`sync-token` 语义）。
9. **AFS 生产主机名**（`edge.activity.windows.com`？还是别的）。
10. **ECM WebSocket 的手shake（`cloud-messaging-authorization` 怎么拿）、订阅消息格式、
    `HandleRegistrations` 的 body**。
11. **所有超时/轮询/退避的数值**（长轮询 30min？短轮询 60s？2 分钟配置超时？）。
12. **Edge 是否真的给 `edge.microsoft.com` 加 `X-Client-Data`**。
13. **同步 protobuf 的 `partial_failure`/quota/`DisableDataType` 语义细节**。
14. **`edge_reliability_telemetry_helper` 上报到哪个端点、什么 schema**。
15. **`sync.local_sync_backend_dir` 的实际目录名与"仅本地同步"是否对 Via 可用**。

---

## 10. 给 Via（Java/Kotlin）实现的最小可行清单

### 10.1 通道 A（`/command/`）最小实现

```
POST {base}/command/?client_id={26字符随机串}
Host: edge.microsoft.com
Authorization: Bearer {AAD access token, resource=https://edgesync.microsoft.com}
Content-Type: application/octet-stream
（可选，Edge 特有）X-AFS-Tracking: ...
（可选，Edge 特有）X-AFS-ClientInfo: platform=Android; os=...; osVer=...; app=...; appVer=...; appChannel=stable; appInstallationId=...; region=...
body: serialized sync_pb.ClientToServerMessage
```

其中：

- `{base}` 默认 `https://edge.microsoft.com/sync`（测试用 `/sync-ppe`）。
- `ClientToServerMessage` 至少要有：`protocol_version`、`message_contents`、
  `store_birthday`、`client_id`、`share`（先抓包确认 `share` 的取值，通常是空串或账号 ID）、
  `bag_of_chips`（ChipBag）。**这些字段的实际取值务必先从真机抓包抄一遍。**
- 首轮流程：`GET_UPDATES`（带 `GetUpdatesCallerInfo` + `from_progress_marker` 空）
  → 收 `GetUpdatesResponse`（含 `DataTypeProgressMarker`、`sync_pb.SyncEntity` 列表、
  `newest_server_version`）→ `COMMIT`（带 `SyncEntity` + `version`/`ctime`/`mtime`/`deleted`）。
- `sync_pb.SyncEntity` 关键字段：`id_string`、`parent_id_string`、`server_defined_unique_tag`、
  `originator_client_item_id`、`originator_cache_guid`、`client_defined_unique_tag`、
  `non_unique_name`、`version`、`ctime`、`mtime`、`deleted`、`folder`、`position_in_parent`、
  `unique_position`、`specifics`(EntitySpecifics)、`server_version`。

### 10.2 需要自造的本地状态

| 状态 | 用途 | 建议格式 |
|---|---|---|
| `client_id` | URL 参数 | 26 字符随机串（对齐 Chromium 风格），持久化 |
| `cache_guid` | DeviceInfo / ECM 身份 | GUID，持久化 |
| `store_birthday` | 防串服校验 | 首次同步后由服务端下发，持久化 |
| `bag_of_chips` | 防串服校验 | 首次同步后由服务端下发，persist 原始 bytes |
| `DataTypeProgressMarker` | 增量下载游标 | 每类型一份，持久化 |
| `DataTypeState` | 加密状态/初始同步状态 | 每类型一份 |
| nigori 密钥 | 解密密码等 | Via 若不复刻密码同步可暂不实现 |

### 10.3 现实建议

1. **先只做 `favorites`（书签）一个类型**，用真机抓包把 `ClientToServerMessage` 的字段
   全部对齐后再扩展。
2. **不要试图复刻 MIP/AAD 密钥服务**——对 Via 来说，"同步书签"不需要 nigori；
   密码同步才需要，而那条路依赖 MIP SDK，**在第三方 App 内几乎不可能合规复刻**。
3. 通道 B（AFS collections/workspaces）是 **JSON**，比通道 A 好实现，可作为第二阶段。
4. 通道 C（ECM）只是推送，**不做也不影响正确性**，只是同步会变成定时轮询。

---

## 附录 A：本文用到的关键 grep（便于 Edge 升级后复现）

```bash
S=so/strings/libchrome.strings.txt
C=analysis/class_index.txt

# 传输层常量块
sed -n '235495,235560p' $S

# URL
grep -n -x -F -e "/command/" -e "?client_id=" -e "sync-url" -e "encryption-key-url" $S
grep -n "edge.microsoft.com/sync\|edgesync.microsoft.com\|activity.windows.com" $S

# 头
grep -n -x -F -e "X-AFS-Tracking" -e "X-AFS-ClientInfo" -e "X-AFS-CV" -e "X-Client-ID" \
  -e "X-Has-Multiple-Syncing-Devices" -e "sync-token" -e "X-Client-Data" $S

# pref
grep -n "^sync\.[a-z_]*$" $S

# 存储
grep -n -x -F -e "Sync Data" -e "LevelDB" -e "-GlobalMetadata" -e "_mts_schema_descriptor" \
  -e "NigoriStorageKey" -e "NigoriClientTagHash" -e "Nigori.bin" $S

# 加密
grep -n "Microsoft.Sync.EncryptionKey" $S
grep -n "edge_mip\|AAD.Mip\|MIP " $S

# protobuf 消息
grep -n "sync_pb\." $S | sed -n '1,400p'

# Java 侧类型
grep -oE "org/chromium/components/sync/protocol/[A-Za-z0-9_]+[$][A-Za-z0-9_]+" $C | sort -u
grep "org/chromium/components/sync/protocol/" $C | wc -l      # → 1038
```

## 附录 B：本次调查统计

| 指标 | 数值 |
|---|---|
| `sync_pb.` 字符串常量 | 373 |
| Java `sync/protocol/*` 类（含内部类） | 1038 |
| `components/sync*` 源文件路径 | 62（其中 `.cc` 44） |
| `sync.*` pref key | 60+ |
| Edge 私有同步 protobuf 消息 | 25+ |
| Edge 私有同步指标（`Microsoft.Sync.*`） | 100+ |
