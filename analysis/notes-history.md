# Edge Android 153.0.4234.49 浏览历史：本地存储 / 同步上传 / 拉取 —— 取证笔记

对象：`com.microsoft.emmx` 153.0.4234.49（Edge Android，arm64）。
素材：`dex/base/classes*.dex`(4)、`dex/chrome/classes*.dex`(3)、`analysis/class_index.txt`、
`so/strings/libchrome.strings.txt`（**libchrome.so 全量 ASCII 字符串，含行号，本笔记所有 `L####` 均指该文件行号**）、
`analysis/scan_classes.py`。
交叉验证：Chromium 上游源码（`raw.githubusercontent.com/chromium/chromium/main/...`，文中标注「上游对照」）。
未证实项一律显式标注 **【未证实】**。
相关笔记：同目录 `analysis/notes-sync-protocol.md`（同步传输层/协议，另行产出）；本笔记聚焦**历史数据模型 + 本地历史库 + 历史专用端点**。

---

## 0. 结论速览

1. 本地历史库就是 **Chromium 标准 History 库**（文件名 `History`），Edge 在其上**追加** 5 组自有表：
   `edge_urls` / `edge_favicons` / `edge_visits`(旧名 `edge_visits_without_thumbnails`,`edge_thumbnails`) /
   `CriticalActions` / `edge_meta`，并新增 `components/history/core/browser/edge_*_database.cc` 4 个自有实现文件。
2. Android 端历史列表走 **Java `BrowsingHistoryBridge` → JNI → `BrowsingHistoryService` → `HistoryService`(本地) + `WebHistoryService`(远端)**，
   UI 的 HistoryManager/HistoryAdapter/HistoryItem 被 R8 改名（`auk`/`ksk`/`qtk`），但 JNI 边界类名与回调方法名保留在 .so 里。
3. 同步是 **标准 Sync 协议（sync_pb）的历史数据模型**：`sync_pb.HistorySpecifics`（含 `EdgeHistorySpecifics` 扩展）
   + 独立数据模型 `HISTORY_DELETE_DIRECTIVE`（三种指令：global_id / time_range / url）。
   → 结论：**增量上传（nudge→commit）+ 增量拉取（GetUpdates + progress marker）+ 单独删除指令流**，不是"全量重传"。
4. 数据模型 `HistoryStatusResponse{has_derived_data, min_poll_interval_seconds}` + prefs `sync.last_poll_time` /
   `sync.short_poll_interval` → 服务端可下发"最短轮询间隔"，属**服务端驱动的轮询式拉取**。
5. Edge 侧另有**自有 HTTP 历史接口**：`/v1/me/browsehistory/lookup|delete?client=chrome`（配 `X-AFS-Tracking` 头、
   `Microsoft.Sync.GetHistoryRequestLatency` 等 UMA），与 `edge.microsoft.com/sync` / AFS(`*.activity.windows.com`) 同一体系。
6. Google 的 `WebHistoryService`（`history.google.com`）代码在，但 OAuth 端点已被替换为
   `https://permanently-removed.invalid/auth/webhistory` → **Google web history 在 Edge 中已废置**。

---

## 1. 本地历史数据库

### 1.1 库文件与标识

| 项 | 值 | 证据 |
|---|---|---|
| 库文件名（tag） | `History` | L238551（同簇 L238550 `Favicons`、L238552 `Top Sites`，三者正是 Chromium 各 SQLite 库的 tag/文件名）；L149020-149022 `History file could not be deleted.`；L140640 `History database is too new.` |
| 版本日志 | `History database version ` / `History failed to migrate from version ` | L159897 / L159895 |
| meta 表 | `CREATE TABLE meta(key LONGVARCHAR NOT NULL UNIQUE PRIMARY KEY, value LONGVARCHAR)` | L154572；读写 `SELECT value FROM meta WHERE key=?`(L131208)、`DELETE FROM meta WHERE key=?`(L131209)、`INSERT OR REPLACE INTO meta(key,value) VALUES(?,?)`(L154716) |
| Edge 附加 meta | `CREATE TABLE edge_meta(key LONGVARCHAR NOT NULL UNIQUE PRIMARY KEY, value LONGVARCHAR)`，实现文件 `../../sql/edge_meta_table.cc` | L154571、L114263、`SELECT value FROM edge_meta WHERE key=?`(L131207)、`INSERT OR REPLACE INTO edge_meta (key,value) VALUES (?,?)`(L154720) |
| WAL | `kHistoryDatabaseWriteAheadLogging`（Feature 名） | L74614 |
| History 库内 meta 键 | `early_expiration_threshold`、`may_contain_foreign_visits`、`delete_foreign_visits_until_id`、`known_to_sync_visits_exist` | L238553-238556（与 `history_database.cc` 同簇）。**上游对照**：这 4 个正是 Chromium `history_database.cc` 的 meta 键常量，字段名逐一吻合 → 说明这 4 个键是上游行为，不是 Edge 新增 |
| 历史同步 meta 键 | `history_model_type_state` | L238557（与上同一簇）→ History 库内保存同步 ModelTypeState（说明同步状态**持久化在本地历史库的 metadata store 里**） |

**版本号**：字符串表里只有格式化文案，没有数值；`kCurrentVersionNumber/kCompatibleVersionNumber` 是编译期常量，**本 APK 内的具体数值【未证实】**。
（上游对照：Chromium main 的 `history_database.cc` 为 `kCurrentVersionNumber = 70`、`kCompatibleVersionNumber = 16`；Edge 153 至少 ≥ 该值，但 Edge 是否另加迁移步无法从字符串判定。）

### 1.2 核心表 DDL（Chromium 标准部分）

| 表 | DDL（节选，逐字来自字符串） | 证据 |
|---|---|---|
| `urls` | `CREATE TABLE ` + `urls` + `(id INTEGER PRIMARY KEY AUTOINCREMENT,url LONGVARCHAR,title LONGVARCHAR,visit_count INTEGER DEFAULT 0 NOT NULL,typed_count INTEGER DEFAULT 0 NOT NULL,last_visit_time INTEGER NOT NULL,hidden INTEGER DEFAULT 0 NOT NULL)` | L154622（表体）+ 上游对照 url_database.cc 用 `base::StrCat("CREATE TABLE ", name, ...)` 拼接，吻合。索引：`urls_url_index`(L153677) |
| `visits` | `CREATE TABLE visits(id INTEGER PRIMARY KEY AUTOINCREMENT,url INTEGER NOT NULL,visit_time INTEGER NOT NULL,from_visit INTEGER,external_referrer_url TEXT,transition INTEGER DEFAULT 0 NOT NULL,segment_id INTEGER,visit_duration INTEGER DEFAULT 0 NOT NULL,incremented_omnibox_typed_score BOOLEAN DEFAULT FALSE NOT NULL,opener_visit INTEGER,originator_cache_guid TEXT,originator_visit_id INTEGER,originator_from_visit INTEGER,originator_opener_visit INTEGER,is_known_to_sync BOOLEAN DEFAULT FALSE NOT NULL,consider_for_ntp_most_visited BOOLEAN DEFAULT FALSE NOT NULL,visited_link_id INTEGER DEFAULT 0 NOT NULL,app_id TEXT)` | L154525。**上游对照：与 Chromium main `visit_database.cc::InitVisitTable` 逐列一致（含 `external_referrer_url TEXT` 与 `app_id TEXT`）→ 二者均为上游列，不是 Edge 扩展** |
| `visits` 全列读取 SQL | `SELECT visits.id,visits.url,visits.visit_time,visits.from_visit,visits.external_referrer_url,visits.transition,visits.segment_id,visits.visit_duration,visits.incremented_omnibox_typed_score,visits.opener_visit,visits.originator_cache_guid,visits.originator_visit_id,visits.originator_from_visit,visits.originator_opener_visit,visits.is_known_to_sync,visits.consider_for_ntp_most_visited,visits.visited_link_id,visits.app_id FROM visits ...` | L129293、L129294、L131258、L131297、L131355-131357、L131365-131369、L161454 |
| `visit_source` | `CREATE TABLE visit_source(id INTEGER PRIMARY KEY,source INTEGER NOT NULL)` | L154611；`SELECT source FROM visit_source WHERE id=?`(L131312)、`DELETE FROM visit_source WHERE id=?`(L131313) |
| `keyword_search_terms` | `CREATE TABLE keyword_search_terms (keyword_id INTEGER NOT NULL,url_id INTEGER NOT NULL,term LONGVARCHAR NOT NULL,normalized_term LONGVARCHAR NOT NULL)` | L154614；索引 L153650/153651/154140 |
| `segments` | `CREATE TABLE segments (id INTEGER PRIMARY KEY,name VARCHAR,url_id INTEGER NON NULL)` | L154625；迁移 L25271-25274、L30865；索引 `segments_name`(L153981)、`segments_url_id`(L154138) |
| `segment_usage` | `CREATE TABLE segment_usage (id INTEGER PRIMARY KEY,segment_id INTEGER NOT NULL,time_slot INTEGER NOT NULL,visit_count INTEGER DEFAULT 0 NOT NULL)` | L154621；索引 L154125/154126 |
| `content_annotations` | `CREATE TABLE IF NOT EXISTS content_annotations(visit_id INTEGER PRIMARY KEY,visibility_score NUMERIC,floc_protected_score NUMERIC,categories VARCHAR,page_topics_model_version INTEGER,annotation_flags INTEGER NOT NULL,entities VARCHAR,related_searches VARCHAR,search_normalized_url VARCHAR,search_terms LONGVARCHAR,alternative_title VARCHAR,page_language VARCHAR,password_state INTEGER DEFAULT 0 NOT NULL,has_url_keyed_image BOOLEAN NOT NULL)` | L154616 |
| `context_annotations` | `CREATE TABLE IF NOT EXISTS context_annotations(visit_id INTEGER PRIMARY KEY,context_annotation_flags INTEGER NOT NULL,duration_since_last_visit INTEGER,page_end_reason INTEGER,total_foreground_duration INTEGER,browser_type INTEGER DEFAULT 0 NOT NULL,window_id INTEGER DEFAULT -1 NOT NULL,tab_id INTEGER DEFAULT -1 NOT NULL,task_id INTEGER DEFAULT -1 NOT NULL,root_task_id INTEGER DEFAULT -1 NOT NULL,parent_task_id INTEGER DEFAULT -1 NOT NULL,response_code INTEGER DEFAULT 0 NOT NULL)` | L154623 |
| `clusters` / `cluster_keywords` / `clusters_and_visits` / `cluster_visit_duplicates`（History Clusters/Journeys） | `clusters`: L154612；`cluster_keywords`: L154615；`clusters_and_visits`: L128212；`cluster_visit_duplicates`: L128211；索引 `clusters_for_visit`(L154128)、`cluster_keywords_cluster_id_index`(L154132) | 见对应行 |
| `visited_links` | `CREATE TABLE visited_links(id INTEGER PRIMARY KEY AUTOINCREMENT,link_url_id INTEGER NOT NULL,top_level_url LONGVARCHAR NOT NULL,frame_url LONGVARCHAR NOT NULL,visit_count INTEGER DEFAULT 0 NOT NULL)` | L238567（注意行首多了一个 `d`，是字符串拼接产物） |
| `downloads` 系列（与历史同库，download_database.cc） | `CREATE TABLE downloads(...)` / `downloads_url_chains` / `downloads_slices` | L238545-238549 |

### 1.3 Edge 自有扩展表（本笔记重点）

| 表 | DDL | 实现文件（.so 内含源码路径） | 证据 |
|---|---|---|---|
| `edge_urls` | `CREATE TABLE edge_urls (url_id INTEGER PRIMARY KEY,topics TEXT,favicon_id INTEGER)` | `../../components/history/core/browser/edge_urls_database.cc` (L113974) | DDL L154562；迁移 `ALTER TABLE edge_urls ADD COLUMN favicon_id INTEGER`(L123268)、`DROP TABLE IF EXISTS edge_urls`(L30852)；SQL `INSERT OR REPLACE INTO edge_urls ( url_id, topics, favicon_id ) VALUES (?, (SELECT topics FROM edge_urls WHERE url_id = ?) ,?)`(L154724)、`SELECT favicon_id FROM edge_urls WHERE url_id = ?`(L131483)、`SELECT url_id FROM edge_urls WHERE favicon_id = ?`(L131475)、`DELETE FROM edge_urls WHERE url_id = ?`(L131484) |
| `edge_favicons` | `CREATE TABLE edge_favicons (id INTEGER PRIMARY KEY AUTOINCREMENT,url TEXT)` | `../../components/history/core/browser/edge_favicons_database.cc` (L113972) | DDL L154524；索引 `CREATE INDEX IF NOT EXISTS edge_favicons_url_index ON edge_favicons(url)`(L153675)；`INSERT INTO edge_favicons (url) VALUES (?)`(L154736)、`SELECT id, url FROM edge_favicons WHERE url=?`/`WHERE id=?`(L131231/L131301)、`DELETE FROM edge_favicons WHERE id=?`(L131501)、`DROP TABLE IF EXISTS edge_favicons`(L30069) |
| `edge_visits` | `CREATE TABLE edge_visits (visit_id INTEGER PRIMARY KEY,has_user_interaction BOOLEAN DEFAULT FALSE NOT NULL,active_visit_duration INTEGER DEFAULT 0 NOT NULL,conversation_id TEXT)` + `CREATE INDEX IF NOT EXISTS idx_edge_visits_conversation_id ON edge_visits(conversation_id) WHERE conversation_id IS NOT NULL` | `../../components/history/core/browser/edge_visit_database.cc` (L113964) | DDL L238578-238579；**旧表名迁移链**：`ALTER TABLE edge_visits_without_thumbnails RENAME TO edge_visits`(L25571)、`ALTER TABLE edge_thumbnails RENAME TO edge_visits`(L25572)、`INSERT INTO edge_visits_without_thumbnails SELECT visit_id, has_user_interaction, active_visit_duration, conversation_id FROM edge_visits`(L25573)、旧 DDL `CREATE TABLE edge_visits_without_thumbnails (visit_id INTEGER PRIMARY KEY,has_user_interaction BOOLEAN DEFAULT FALSE NOT NULL,active_visit_duration INTEGER DEFAULT 0 NOT NULL,conversation_id TEXT)`(L154526)；增量迁移 `ALTER TABLE edge_visits ADD COLUMN conversation_id TEXT`(L120859)、`ADD COLUMN has_user_interaction BOOLEAN DEFAULT FALSE NOT NULL`(L124942)、`ADD COLUMN active_visit_duration INTEGER DEFAULT 0 NOT NULL`(L124955)；读写 `SELECT has_user_interaction FROM edge_visits WHERE visit_id = ?`(L131465)、`SELECT active_visit_duration FROM edge_visits WHERE visit_id = ?`(L131466)、`INSERT OR REPLACE INTO edge_visits (...)`(L155201-155202) |
| `CriticalActions`（Edge/AI 关键操作，挂 visit_id/conversation_id） | `CREATE TABLE IF NOT EXISTS CriticalActions (  critical_action_id TEXT PRIMARY KEY NOT NULL,  timestamp INTEGER NOT NULL,  visit_id INTEGER,  conversation_id TEXT,  actor_task_id TEXT,  action_type INTEGER NOT NULL,  url TEXT,  metadata TEXT)` | 索引：L153555/153674/153953/154129/154135/154141 | L154527；`INSERT INTO CriticalActions (...)`(L154789)、`DELETE FROM CriticalActions WHERE visit_id = ?`(L131467)、`DELETE FROM CriticalActions WHERE timestamp >= ? AND timestamp < ?`(L131525) |
| `edge_meta` | 见 1.1 | `../../sql/edge_meta_table.cc` | L154571 |

**表归属判定**：`edge_urls/edge_favicons/edge_visits/CriticalActions` 由 `components/history/core/browser/edge_*_database.cc` 创建，
且 `edge_urls.url_id`/`edge_visits.visit_id` 直接以历史库的 `urls.id`/`visits.id` 为主键，
因此 **高度可能** 与 `urls/visits/segments` 同处 `History` 这一个 SQLite 文件（Chromium 的 `HistoryDatabase` 就是这样把多个子 DB 类挂在同一连接上）。
注：`edge_favicons` 的字符串（`DROP TABLE IF EXISTS edge_favicons`、`SELECT id, url, icon_type FROM favicons`、`FaviconBackend::SetFavicons`）
在字符串表里与 favicon 组件同簇（L30065-30079），**不排除 `edge_favicons` 建在 `Favicons` 库中**。
→ **【未证实】**：需在真机/模拟器上 `sqlite3 <profile>/History ".tables"` 与 `Favicons` 对照一次即可定论。

### 1.4 编译进 .so 的历史相关源码文件（证明实现存在）

```
components/history/core/browser/history_database.cc          L113955
components/history/core/browser/in_memory_database.cc        L113957
components/history/core/browser/visitsegment_database.cc     L113962
components/history/core/browser/edge_visit_database.cc       L113964   ← Edge 自有
components/history/core/browser/visit_database.cc            L113965
components/history/core/browser/visit_annotations_database.cc L113971
components/history/core/browser/edge_favicons_database.cc    L113972   ← Edge 自有
components/history/core/browser/edge_urls_database.cc        L113974   ← Edge 自有
components/history/core/browser/top_sites_database.cc        L113976
components/history/core/browser/url_database.cc              L113994
components/history/core/browser/visited_link_database.cc     L113996
components/history/core/browser/edge_download_database.cc    L114002   ← Edge 自有
components/history/core/browser/download_database.cc         L114003
components/history/core/browser/sync/history_sync_bridge.cc  L114346
components/history/core/browser/sync/delete_directive_handler.cc L111765
components/history/core/browser/browsing_history_service.cc  L114482
components/history/core/browser/web_history_service.cc       L114483
components/history/core/browser/history_service.cc           L114484
components/history/core/browser/expire_history_backend.cc    L114677
components/history/core/browser/history_backend.cc           L114678
components/history/core/browser/top_sites_backend.cc         L114681
components/browsing_data/core/counters/history_counter.cc    L111221
```
另有 50 处 `HistoryBackend::xxx` 符号串（如 `HistoryBackend::Commit` L19991、`ScheduleCommit` L19980、`DeleteURLs` L37839、`DeleteURLsUntil` L64609、`HideVisits` L25591、`BeginSingletonTransaction` L54776、`KillHistoryDatabase` L81771、Edge 自有 `EdgeUpdateWithUserInteraction` L54849、`EdgeUpdateWithPageActiveDuration` L55881、`EdgeGetFaviconsAndSetIconMapping` L73783）。

### 1.5 易混淆项

- L249017 的 `CREATE TABLE urls(url_id INTEGER PRIMARY KEY NOT NULL,url TEXT NOT NULL,last_timestamp INTEGER NOT NULL,counter INTEGER,title TEXT,profile_id TEXT)`
  **不是历史库的 urls 表**：它与同簇的 `metrics`/`uma_metrics`（UKM/指标库，含 `ukm_source_id`、`profile_id`）一起出现（L249008-249024），属指标库。
  因此 L120860 的 `ALTER TABLE urls ADD COLUMN profile_id TEXT` **很可能也属于该指标库，而不是历史库**【未证实】。
  （上游对照：`url_database.cc` 里不存在 `profile_id`。）

---

## 2. Java 侧访问路径（历史列表 UI 如何取数据）

### 2.1 保留原名的类（dex 索引可见）

| 类 | dex | 说明 |
|---|---|---|
| `org.chromium.chrome.browser.history.BrowsingHistoryBridge` | dex/chrome/classes.dex | 历史列表的 Java 门面 |
| `org.chromium.chrome.browser.history.HistoryActivity` / `HistoryItemView` / `HistoryManagerToolbar` | dex/chrome/classes.dex | 历史页 Activity/条目/工具栏 |
| `org.chromium.chrome.browser.history.HistoryDeletionBridge` / `HistoryDeletionInfo` | dex/chrome/classes2.dex | 删除事件回调 |
| `org.chromium.chrome.browser.edge_hub.history.EdgeNewHubHistoryFragment` | dex/chrome/classes.dex | Edge 自己的"新 Hub 历史"入口（字段 `c : Lauk;` = HistoryManager） |
| `org.chromium.chrome.browser.browsing_data.*`（`BrowsingDataBridge`、`ClearBrowsingDataFragment`、`ClearBrowsingDataFetcher`、`BrowsingDataCounterBridge`、`UrlFilterBridge`、`OtherFormsOfHistoryDialogFragment`、`EdgeClearBrowsingDataFragment`） | dex/chrome/classes{,2}.dex | 清除浏览数据（含"其他形式的历史"对话框 = 云端历史） |
| `org.chromium.chrome.browser.provider.ChromeBrowserProvider` | dex/base/classes4.dex | 仅剩壳（只有 `<init>`），**无可用的跨应用 HistoryProvider** |

### 2.2 被 R8 改名但可确证的类

| 混淆名 | 真实身份 | 证据 |
|---|---|---|
| `Lauk;` | `HistoryManager` | 构造函数签名：`<init>(Profile; WindowAndroid; Activity; Z; Llv30; Supplier; Supplier; Ljq; Supplier; BrowsingHistoryBridge; Lkvk; String; Z Z Z Z; Runnable; Function)`——**直接持有 `BrowsingHistoryBridge`**，并持有 `HistoryManagerToolbar`、`SelectableListLayout`、`PrefService`、`Profile`；字段 `l : Lorg/chromium/chrome/browser/history/HistoryManagerToolbar;` |
| `Lksk;` | `HistoryAdapter` | 父类 `Ltbb;`(RecyclerView.Adapter)，方法 `s(Lqtk;)`(setItem)/`y(Lqtk;)`；`BrowsingHistoryBridge` 的字段 `a : Lksk;` |
| `Lqtk;` | `HistoryItem` | 父类 `Lsbb;`，字段 `a : Lorg/chromium/url/GURL;`、`b/c/d : String`、`g : J`、`h : [J`、`j : Ljava/util/ArrayList;`，方法 `getStableId()J`；`HistoryItemView.setItem(Lqtk;)` |
| `Lbuk;` | 历史工具栏 delegate 接口 | `HistoryManagerToolbar.setMenuDelegate(Lbuk;)` |
| `Lcuk;` | `isVisible()/setVisible(Z)` 抽象 = 历史工具栏可见性接口 | — |

### 2.3 JNI 边界（重要）

- libchrome.so 的 **JNI 类名表**里保留了：`org.chromium.chrome.browser.history.BrowsingHistoryBridge`(L175886)、
  `HistoryDeletionBridge`(L175887)、`HistoryDeletionInfo`(L175888)、
  `org.chromium.chrome.browser.browsing_data.BrowsingDataBridge$OtherFormsOfBrowsingHistoryListener`(L175723)。
- libchrome.so 里同时保留了 **native 调回 Java 的方法名字符串**（JNI 注册用）：
  `createHistoryItemAndAddToList`(L171690)、`hasOtherFormsOfBrowsingData`(L172742)、
  `onQueryAppsComplete`(L173377)、`onQueryHistoryComplete`(L173378)、`onRemoveComplete`(L173395)、`onURLsDeleted`(L173480)。
  → 即：`BrowsingHistoryBridge.onQueryHistoryComplete(List, boolean)` / `onQueryAppsComplete(List)` /
  `onRemoveComplete()` / `onRemoveFailed()` / `hasOtherFormsOfBrowsingData(boolean)` / `onHistoryDeleted()`
  与 `HistoryDeletionBridge.onURLsDeleted(HistoryDeletionInfo)` 均**由 native 回调**。
- Java 侧 `private native ...` 方法名被 R8 混淆到 1 字符（全 dex 共 1898 个 native 方法，**没有一个**类名/方法名含 history/browsing/visit），
  所以 `nativeQueryHistory/nativeRemoveHistory/...` 这类名字**读不出来**【未证实】；`BrowsingHistoryBridge` 里形如 `a()V` 的无参方法即候选。
- `HistoryDeletionInfo`：字段 `a : J`（native 指针），`static create(J)HistoryDeletionInfo`（native 构造后回填）。

### 2.4 本地查询 SQL（列表数据来源）

`SELECT urls.url, visits.originator_cache_guid, IFNULL(visit_source.source, ?) FROM urls INNER JOIN visits ON urls.id=visits.url LEFT JOIN visit_source ON visits.id=visit_source.id WHERE (transition & ?)!=0 AND (transition & ?) NOT IN (?, ?, ?) AND hidden=0 AND visit_time>=? AND visit_time<? ORDER BY visit_time DESC, visits.id DESC`
（L129283；另一变体带 `context_annotations.response_code!=404` 过滤，L129284/L131365-131366）
→ 列表项 **同时带出 `originator_cache_guid`（来自其它设备的同步来源）与 `visit_source.source`**，这正是 `BrowsingHistoryService` 能区分"本地历史/远端历史"的字段基础。

另：`BrowsingHistoryService`(L114482) 负责合并本地 + 远端结果，配套 UMA：
`History.BrowsingHistoryResult.{LocalOnly|RemoteOnly|Combined}.{Pre|Post}ExpiryThreshold`(L98369-98374)、
`History.BrowsingHistoryResult.DuplicateVisitsCount`(L15487)、`History.WebHistory.QueryHistoryResultsCount`(L15486)、
`BrowsingDataBridge_RequestInfoAboutOtherFormsOfBrowsingHistory`(L4555)。

---

## 3. 历史同步实现

### 3.1 已编入的同步代码

| 组件 | 证据 |
|---|---|
| `components/history/core/browser/sync/history_sync_bridge.cc` | L114346（源码路径串在 .so 内） |
| `components/history/core/browser/sync/delete_directive_handler.cc` | L111765 |
| 同步引擎：`components/sync/engine/commit.cc` L110374、`get_updates_processor.cc` L110972、`data_type_worker.cc` L111792、`backend_migrator.cc` L110880 | 同上 |
| `components/sync/model/client_tag_based_data_type_processor.cc` L110981、`components/sync/service/data_type_controller.cc` L111635、`non_ui_syncable_service_based_data_type_controller.cc` L111634 | 同上 |
| `components/browser_sync/sync_engine_factory_impl.cc` L113049 | 同上 |
| `components/sync/service/history_sync_session_durations_metrics_recorder.cc` L112335 | 同上（登录/同意流程用，非数据模型） |

### 3.2 `sync_pb.HistorySpecifics` 字段（Java 生成类 → 逐字证据）

类位置：`dex/chrome/classes3.dex`。以下为 **Java proto 类字段**（`getXxx/setXxx/hasXxx` 全名在类中，字段名即 proto 名）：

```
HistorySpecifics:
  visit_time_windows_epoch_micros (j)   originator_cache_guid (String)
  redirect_entries (repeated RedirectEntry)   page_transition (PageTransition)
  originator_referring_visit_id (j)     originator_opener_visit_id (j)
  visit_duration_micros (j)             browser_type (i)
  window_id / tab_id (i)                task_id / root_task_id / parent_task_id (j)
  http_response_code (i)                page_language (String)
  password_state (i)                    favicon_url (String)
  referrer_url (String)                 redirect_chain_start_incomplete / _middle_trimmed / _end_incomplete (boolean)
  originator_cluster_id (j)             has_url_keyed_image (boolean)
  categories (repeated HistorySpecifics.Category)
  related_searches (repeated String)    app_id (String)
  edge_history_specifics (EdgeHistorySpecifics)      ← Edge 扩展
  edge_topic_categories (repeated EdgeTopicCategory) ← Edge 扩展
HistorySpecifics.RedirectEntry: url, title, hidden, redirect_type, originator_visit_id
HistorySpecifics.PageTransition: core_transition, blocked, forward_back, from_address_bar, home_page
HistorySpecifics.Category: id(String), weight(int)
EdgeHistorySpecifics（dex/chrome/classes3.dex，Edge 独有）: summary(String), active_visit_duration_micros(j)
EdgeTopicCategory（dex/chrome/classes3.dex）: 同名类型在 native 侧见 L298119 `sync_pb.EdgeTopicCategory`
```
注意：**没有 `page_url` 字段**——访问 URL 由 `redirect_entries[0].url` 承载（上游对照：`history_specifics.proto` 中 `redirect_entries` 注释"The first entry is the URL the user originally navigated to; the last one is where they ended up."）。
native 侧同名字段串：`visit_time_windows_epoch_micros`(L28660)、`originator_visit_id`/`originator_opener_visit_id`/`originator_referring_visit_id`(L99237-99239)、`redirect_entries`(L35265)、`edge_history_specifics`(L37318)、`edge_topic_categories`(L35370)、`history_delete_directive`(L77463)、proto 类型名 `sync_pb.HistorySpecifics{,.RedirectEntry,.PageTransition,.Category}`(L297913-297916)、`sync_pb.EdgeHistorySpecifics`(L298120)。

### 3.3 删除指令（独立数据模型）

- Java proto：`HistoryDeleteDirectiveSpecifics`（oneof：`global_id_directive` / `time_range_directive` / `url_directive`）、
  `GlobalIdDirective`、`TimeRangeDirective`、`UrlDirective`、`HistoryDeleteDirectives{enabled}`（dex/chrome/classes3.dex）。
- native：`url_directive`/`time_range_directive`/`global_id_directive`(L77459-77461)、
  `sync_pb.GlobalIdDirective/TimeRangeDirective/UrlDirective`(L297910-297912)、
  `sync_pb.HistoryDeleteDirectives`(L297891)、`sync_pb.HistoryDeleteDirectiveSpecifics`(L297909)、
  数据模型名 `History Delete Directives`(L33189) 与 `HISTORY_DELETE_DIRECTIVE`(L126351)、`history_delete_directives`(L33187)。
- 本地落地：`DeleteDirectiveHandler`(L111765) 把指令翻译成历史库的删除（`HistoryBackend::DeleteURLsUntil` L64609、`DeleteURLs` L37839）。

### 3.4 Android 端接入点 / 数据模型控制器

- **没有** `HistoryDataTypeController` 之类的字符串（native 只编入通用控制器 `data_type_controller.cc`、`non_ui_syncable_service_based_data_type_controller.cc`）。
- **【未证实】** 该 build 里历史数据模型由哪个 `DataTypeController` 注册（可能由 `sync_engine_factory_impl.cc` 内联注册，无独立类名串）。
- 但 Java 侧确凿存在 history 同步的**同意/开关 UI**：
  `org/chromium/chrome/browser/firstrun/HistorySyncFirstRunFragment`、
  `org/chromium/chrome/browser/ui/signin/history_sync/HistorySyncView`、
  `org/chromium/chrome/browser/signin/SigninAndHistorySyncActivity`、
  `org/chromium/chrome/browser/privacy_guide/HistorySyncFragment`（均 dex/chrome/classes2.dex），
  以及 prefs `signin.history_sync.last_declined_timestamp`、`signin.history_sync.successive_decline_count`（dex 字符串）。
  → 即 Edge Android **确实宣传并开关"同步浏览历史"**。

---

## 4. 上传 / 拉取策略

**结论：增量 + 删除指令（不是全量重传）。**

| 策略要素 | 证据 |
|---|---|
| 增量拉取 GetUpdates | `ProcessGetUpdates`(L33748)、`Initial GetUpdates`(L33749)、`DownloadUpdates`(L33777)、`ApplyUpdates`(L33741)、`Microsoft.Sync.GetUpdatesItemCount`(L15640)、`Microsoft.Sync.PostedGetUpdatesOrigin`(L58820)、`Sync.PostedDataTypeGetUpdatesRequest`(L13157)、`Sync.MissingProgressMarkerInGetUpdatesResponse`(L81335)、`GetUpdates Response`(L81495)；`GetUpdatesProcessor` 源码 L110972 |
| 全量/增量语义 | `TombstoneInFullUpdate`(L235563)、`TombstoneForNonexistentInIncrementalUpdate`(L235564)、`Sync.SearchEngine.ChangesCommittedUponIncrementalUpdate`(L80617)、`Sync.CDUTMismatchOnFullUpdate2`(L137246) → 协议同时支持 full/incremental，**包含 progress marker 的增量是常态**；本地持久化 `history_model_type_state`(L238557) 保证断点续拉 |
| 上传（Commit）由 nudge 触发 | `NudgeForCommit`(L19946)、`ScheduleNudgeImpl`(L63471)、`Nudged types: %s`(L38425)、`local_modification_nudges`/`datatype_refresh_nudges`/`MinPageCountBetweenNudges`/`MinTimeBetweenNudges`(L35738-35741)、`custom_nudge_delays`(L23420)、Edge 侧 `msEdgeSyncSingleDeviceCommitNudgeDelaySessions`(L29910)、`singleDeviceSessionsNudgeDelayMinutes`(L235513)；提交统计 `Sync.CommitLatency`(L235524)/`Sync.CommitResponse`(L235525)/`Microsoft.Sync.Batch.Time.Commit`(L235526)/`Microsoft.Sync.Size.Commit`(L235528) |
| 批量上限 | `max_history_entries`(L35261) —— 位于 Feature/param 名簇中，**语义【未证实】**（疑为历史上传/查询批大小上限） |
| 删除 | 三种 `HistoryDeleteDirective`（见 3.3）+ `HistoryDeleteDirectives.enabled` 开关；提交侧 `Sync.DataTypeUpdateDrop.`(L235553)、`Sync.DataTypeCount.`(L235555)、`Sync.EntitySizeOnCommit.*`(L235556-235558) 等监控 |
| 轮询/节流 | `sync.last_poll_time`(L235868)、`sync.short_poll_interval`(L235869)、`set_sync_poll_interval`(L65391)、`poll_interval_ms`(L30745)；数据模型 `HistoryStatusRequest{}` / `HistoryStatusResponse{has_derived_data, min_poll_interval_seconds}`（dex3）→ **服务端通过 `min_poll_interval_seconds` 规定下次拉取间隔**（客户端轮询式增量拉取） |
| 过期/清理 | 库内 `early_expiration_threshold`(L238553，见 1.1)；`expire_history_backend.cc`(L114677)；`History.WebHistoryRequestOutcome.ExpireHistory`(L4564)、`ExpireHistoryBetween`(L59772)、`Microsoft.Sync.ExpireHistoryRequestLatency`/`BatchExpireHistoryRequestLatency`(L238570-238571)；远端过期分界由 `History.BrowsingHistoryResult.*.{Pre,Post}ExpiryThreshold`(L98369-98374) 观测。**`kExpireDaysThreshold` 这类数值常量不在字符串表中，具体天数【未证实】**（上游语义为 90 天阈值，本 build 未直接取证） |

---

## 5. 与服务器交互（补充）

### 5.1 Edge 自有同步后端（AFS=Activity Feed Service）

| 项 | 证据 |
|---|---|
| 同步服务地址 | `https://edge.microsoft.com/sync`(L235575)、`https://edge.microsoft.com/sync-ppe`(L235574)、开关前缀 `sync-url`(L235573)、`sync-ppe-test-mode`(L235530) |
| 同步实体接口 | `/v1/feeds/me/syncEntities`(L235542)、`/v1/feeds/me/notification/telemetry`(L235533)、PPE 全量 URL `https://ppe.activity.windows.com/v1/feeds/me/syncEntities/`(L139782) |
| AFS 请求头 | `X-AFS-Tracking`(L235502、L238573)、`X-AFS-ClientInfo`(L235544)、`X-AFS-CV`(L235545)、`sync-token`(L235546)、`X-RateLimit-Debug-UpdateRule`(L235547) |
| AFS 主机名 | `edge.activity.windows.com` 与 `*.activity.windows.com`(L61240-61241)；`https://edgesync.microsoft.com/UserActivity.ReadWrite.CreatedByApp` OAuth scope(L48771) → AFS 生产域为 `edge.activity.windows.com`（**host 与 path 的拼接方式【未证实】**，字符串里 path 与 host 分开存放） |
| Edge 历史(浏览记录)HTTP 接口 | `/v1/me/browsehistory/lookup?client=chrome`(L238568)、`/v1/me/browsehistory/delete?client=chrome`(L238575)，同簇含 `application/json`(L238572)、`profile_bearer_token`(L238540)、`cloud_secure_gateway`(L238541)、`maximumDurationInSeconds`(L238577)、`edge_downloads`(L238576) |
| 配套 UMA | `Microsoft.Sync.GetHistoryRequestLatency`(L238569)、`Microsoft.Sync.ExpireHistoryRequestLatency`(L238570)、`Microsoft.Sync.BatchExpireHistoryRequestLatency`(L238571)、`Microsoft.WebHistory.GetHistoryAuthError`(L238574)、`Microsoft.WebHistory.QueryCompletion`(L53986)、`Microsoft.HistoryPage.WebHistoryServerResponseTime`(L86258)、`History.WebHistoryRequestOutcome.QueryHistory`(L4533) |
| 数据模型开关名（服务端同步配置） | L235582-235594：`addressesAndMore, cookies, extensions, favorites, **history**, openTabs, passwords, readingList, settings, themes, collections, edgeWallet, edgeWorkspaces`；L235897-235899 另有 `history`/`openTabs` |

### 5.2 Google `WebHistoryService` 在 Edge 中已废置

- 代码在：`components/history/core/browser/web_history_service.cc`(L114483)、`WebHistoryServiceFactory`(L4749)。
- 但 OAuth 端点被换成占位符：`https://permanently-removed.invalid/auth/webhistory`(L234232)（与 `permanently-removed.invalid/auth/chrome-context-memory` 等并列，属"功能已移除"写法）。
- 仍留有域名 `history.google.com`(L61680) 与 Feature 名 `kWebHistoryUseNewApi`(L68620)、`kWebHistoryUseSpecificScope`(L84567)、
  以及 `WebHistory.OAuthTokenResponseCode`(L94187)、`History.RemoveVisitsFromWebHistory.EntryCount`(L15349)。
  → **结论**：Google 版 web history 拉取/删除在 Edge 中不可用（占位域名），历史远端能力改由 Edge 自有 `browsehistory`/AFS 承担。

---

## 6. 开关与状态

### 6.1 prefs 键（native 字符串，均为 prefs 名，能在 .so 中找到 `sync.`/`history.` 前缀）

| 键 | 含义 | 证据 |
|---|---|---|
| `history.saving_disabled` | 停止记录历史（Chromium `kSavingBrowserHistoryDisabled`） | L235606 |
| `history.deleting_enabled` | 允许删除历史 | L235607、L235907 |
| `sync.history_type_disabled` | **Edge 自有**：禁用 history 同步类型 | L205509 |
| `sync.history_toggled` | 用户在设置里拨动"历史"同步开关的 UMA/状态 | L235945 |
| `sync.keep_everything_synced` | 全量同步 | L235908 |
| `sync.selected_types_per_account` | 每账号已选类型集合 | L235909 |
| `sync.has_been_enabled` / `sync.sync_to_signin_migration_state` | 同步启用/迁移状态 | L235910-235911 |
| `sync.force_types` / `sync.types_list_disabled` / `sync.managed` / `sync.forced` / `sync.disabled_types_list` | 策略强制/禁用类型 | L235894-235905、L235900-235904 |
| `sync.typed_urls`（旧 URL 类型开关）、`sync.tabs`、`sync.reading_list` … | 各数据类型开关（**注意：列表里没有 `sync.history` 独立键**，历史归入 keep-everything/selected types 体系） | L235925-235938 |
| `sync.short_poll_interval` / `sync.last_poll_time` / `sync.last_synced_time` | 轮询与上次同步时间 | L235868-235869、L235867 |
| `sync.cache_guid` / `sync.gaia_id` / `sync.bag_of_chips` | 设备/账号标识 | L235866-235872 |
| `signin.history_sync.last_declined_timestamp` / `signin.history_sync.successive_decline_count` | 历史同步登录提示衰减（dex 字符串） | dex/chrome/classes2.dex |

### 6.2 Java ↔ native 状态接口

- `org.chromium.components.sync.SyncService`(dex/chrome/classes2.dex) / `SyncServiceImpl`(dex/chrome/classes.dex)：
  方法被 R8 改名（`a()`…`z()`、`A()`…`Q()`），但 **JNI 回调方法保留了原名**：
  `onResyncData()`、`syncStateChanged()`、`syncResetComplete(Z)`、`remoteSyncResetReceived()`、
  `onGetAllNodesResult(Callback;String)`、`onGetLocalDataDescriptionsResult(Callback;[I[LLocalDataDescription;)`、
  `getNativeSyncServiceAndroidBridge()J`、以及 native 侧同名串 `getNativeSyncServiceAndroidBridge`(L172391)。
- 因此从 Java 侧读取同步状态（是否启用、类型状态、认证错误）走的是这套回调 + 混淆 getter；
  **具体"哪个混淆方法=哪个语义"需要反编译 `SyncServiceImpl` 结合 JNI 注册表才能一一对应【未证实】**。

---

## 7. 待证实清单（建议下一步做的实验）

1. 真机打开 profile：`sqlite3 files/History ".tables"` 确认 `edge_urls/edge_favicons/edge_visits/CriticalActions` 是否在 History 库；若不在，再看 `Favicons`。
2. `sqlite3 files/History "select * from meta"` 读 `version`（History 库真实 DB 版本号），以及 `early_expiration_threshold` 的真实值（→ 过期天数）。
3. 抓包确认 `browsehistory` 请求的真实 host（`edge.activity.windows.com` vs `edge.microsoft.com`）与 `client=chrome` 之外的参数。
4. 反编译 `SyncServiceImpl`（dex/chrome/classes.dex）逐个确认混淆 getter 与历史类型状态的对应关系。
5. 若要把历史同步接到 Via：需重点复刻 ①`HistorySpecifics` 的 `redirect_entries[0].url` 语义与 `originator_*` 回填；②`HISTORY_DELETE_DIRECTIVE` 三类指令；③本地 `visit_source.source`/`is_known_to_sync`/`known_to_sync_visits_exist` 这套"本地-同步"标记；④服务端 `min_poll_interval_seconds` 轮询节流。

---

## 8. 证据索引（精确行号，便于复核）

- 1.x 本地库：L154525, L154611, L154614, L154616, L154621-154626, L154623, L154571-154572, L154524-154527, L154562, L238545-238552（downloads 表 + 库 tag）, L238553-238557（meta 键）, L238578-238579, L153675-153677, L153966, L154127, L120856-120860, L123258-123270, L124942-124957, L25571-25577, L30069, L30852, L131207-131209, L131231, L131301, L131465-131467, L131475-131484, L131501, L131525, L154716-154736, L155201-155202, L249017（易混淆）
- 1.4 源码路径：L113955-L114003, L113964/113972/113974/114002, L111221, L114346, L111765, L114482-114484, L114677-114681
- 2 Java/JNI：dex/chrome/classes.dex（BrowsingHistoryBridge/HistoryActivity/HistoryItemView/HistoryManagerToolbar/EdgeNewHubHistoryFragment/auk/ksk/qtk/buk/cuk）, dex/chrome/classes2.dex（HistoryDeletionBridge/HistoryDeletionInfo/SyncService）, L175886-175888, L171690, L172742, L173377-173395, L173480, L129283-129284
- 3 同步：dex/chrome/classes3.dex（HistorySpecifics 等 8 个类）, L297902-297916, L298117-298120, L28660, L35265, L35370, L37318, L77459-77463, L99237-99239, L33187-33189, L126351, L111634-111635, L110981
- 4 上传/拉取：L33741-33777, L13157, L15640, L58820, L81335, L81495, L19946, L38425, L63471, L35738-35741, L23420, L29910, L235523, L235563-235564, L80617, L137246, L235868-235869, L65391, L30745, L35261, L98369-98374, L238553, L59772, L4564
- 5 服务器：L235502, L235530-235547, L235553-235556, L235574-235575, L139782, L61240-61241, L48771, L238540-238577, L53986, L86258, L4749, L61680, L234232, L68620, L84567, L94187, L15349
- 6 开关：L235606-235607, L235894-235945, L205509, L172391
