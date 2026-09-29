# edge_download_change

[![Nightly](https://github.com/lswlc33/edge_download_change/actions/workflows/nightly.yml/badge.svg)](https://github.com/lswlc33/edge_download_change/actions/workflows/nightly.yml)
[![Release](https://github.com/lswlc33/edge_download_change/actions/workflows/release.yml/badge.svg)](https://github.com/lswlc33/edge_download_change/actions/workflows/release.yml)
[![Latest release](https://img.shields.io/github/v/release/lswlc33/edge_download_change?display_name=tag)](https://github.com/lswlc33/edge_download_change/releases/latest)

An LSPosed module that replaces **Microsoft Edge for Android**'s (`com.microsoft.emmx`)
download confirmation dialog with its own **Copy / Download** dialog, and hands the
download over to the **system DownloadManager** (or a third-party downloader app) instead
of Edge's built-in download manager.

> LSPosed module (libxposed API 102), verified on Edge **153.0.4234.49**.
> 中文说明: [README.md](README.md) ｜ Design notes: [lsp_module/README.md](lsp_module/README.md)
> ｜ How the hook targets were derived: [analysis/README.md](analysis/README.md)

---

## Why it exists

Edge for Android is a good browser with a weak download story: downloads are locked inside
the browser, they cannot be handed to the system or to a download manager app, and large
files / resumable downloads are painful.

This module is for you if you want to:

- send web downloads to the **system downloader** (progress in the notification shade,
  managed in the system Downloads app);
- hand them to an installed **downloader app** (ADM / IDM / 1DM / FDM / Gopeed / Aria2App …);
- **see the real URL before downloading** (verify the domain, avoid misclicks, share the link);
- keep the module **controllable**: interception toggle, download-target picker, activation
  status and a log viewer;
- or study how a Chromium-based browser's download path can be taken over (see `analysis/`).

Requirements: a rooted device with a framework supporting **libxposed API 102** (e.g. LSPosed).

## What it solves

| Before | After |
|---|---|
| Downloads can only be handled by Edge itself | Tapping **Download** creates the download with the **system downloader** or a chosen **downloader app** |
| Edge's dialog shows only name and size | Our dialog shows the **full URL** and copies it with one tap |
| Cancelling may still leave Edge transferring data | Edge's download item is **cancelled the moment it is created** (before the body transfer) |
| Links that need a login cookie fail with the system downloader | **Copy** gives you a path to paste the link into a logged-in tool (known limitation) |
| No way to tell whether the module works | Status page shows **version / activation / last injection / framework version**, plus a checklist |
| Debugging by guesswork | Log page records every interception, skip (with reason) and hand-off result |

## Usage

1. **Install**:
   - **stable**: grab the newest APK from [Releases](https://github.com/lswlc33/edge_download_change/releases/latest) (tag looks like `6-2.4`);
   - **bleeding edge**: the [nightly pre-release](https://github.com/lswlc33/edge_download_change/releases/tag/nightly) is rebuilt on every push;
   - or build locally: `bash lsp_module/build.sh` (artifact `edge_download_change-<version>.apk`).
   All channels use the same signing key, so they can be installed over each other.
2. **Enable** the module in your Xposed/LSPosed manager. The scope `com.microsoft.emmx` is
   declared statically by the module; if your manager ignores static scopes, check Edge manually.
3. **Restart Edge**: force-stop it (switching to background is not enough) and open it again.
4. **Verify**: the module app should show "● Active" with the last injection time and framework
   version. Otherwise follow the 4-step checklist on the status card, or search the LSPosed log
   for `EdgeSysDL`.
5. **Daily use**: tap a download link in Edge → a "Download this file?" dialog appears:
   - **Download** – creates the download with the selected target (system downloader by default);
   - **Copy** – copies the link and cancels Edge's own download;
   - dismiss the dialog – cancels everything, nothing is created.
6. **Settings**: interception on/off; download target (system downloader, ADM, IDM, 1DM, FDM,
   DVGet, Download Navi, Aria2App, Gopeed, AB DM, FluxDown, Xunlei, or a custom package name);
   log viewer. If a third-party downloader cannot be started, the module falls back to the
   system downloader and says so in the log.

## How it works (short version)

Edge is Chromium-based: downloads run in native code and the confirmation dialog is shown by an
obfuscated class (`rge.a(fileName, size, Callback)`), which this module locates **by method
signature** rather than by its unstable name. The module hooks three things:

1. `DownloadManagerService.onDownloadItemCreated` – called right before the dialog; reads the real
   URL/name/MIME, **cancels Edge's download item immediately** (no data transferred yet) and shows
   its own Copy/Download dialog;
2. the confirmation-dialog factory `rge.a` – replaces Edge's dialog; if the dialog appears before
   the download item, it waits for the item and then applies the user's choice;
3. a cancel fallback – `removeDownload()` plus the official `DOWNLOAD_CANCEL` broadcast action
   (resolved from the dex string pool) and one retry after 1.5 s.

Tapping **Download** calls `DownloadManager.enqueue()` (or starts the chosen downloader app with an
explicit intent); tapping **Copy** writes the link to the clipboard and cancels Edge's download.
Edge's download item is cancelled the moment it is created (before any body transfer), so the file
is always fetched by the system/third-party downloader.

(Implementation note: the usual order is item-first, and the module then uses that item's URL
without touching Edge's dialog callback at all. Only in the unusual dialog-first order does it
answer the callback with "accepted" - and only after the user made a choice - cancelling the item
the instant it appears, so no data is transferred either.)

Settings and status travel over a two-way channel between the module app and the injected code
(a `ContentProvider` for live settings and status/log delivery, plus the framework's read-only
remote preferences), with a heartbeat every 60 s driving the activation indicator.

Version-specific gotchas (documented in `lsp_module/README.md`): `GURL.toString()` is not
overridden in Edge 153 (read the field `a`), restored history items must be filtered with
`j == 0 && q == 0`, and only `Activity.onResume` is hooked to keep the framework-hook footprint
minimal.

## Downloads & updates

| Channel | Content | Trigger |
|---|---|---|
| **Release (stable)** | stable builds, tag = `<versionCode>-<versionName>` as required by the official module repository | Actions → **Release** → Run workflow |
| **Nightly (pre-release)** | rebuilt on every push to `main`, file name carries the short SHA | automatic |
| **LSPosed repository** | distribution through the official module repo, installable/updatable from the LSPosed manager | see [publishing guide](docs/publish-to-lsposed-repo.md) (Chinese) |

Builds run on GitHub Actions and are signed with the key stored in the repository secrets
(`SIGNING_KEYSTORE_BASE64` …), so **every build has the same signature** and installs over the
previous one. Local builds use `lsp_module/module.keystore` (the same key).

## Repository layout

```
edge_download_change/
├── README.md / README.en.md     # Chinese / English documentation
├── lsp_module/                  # the module project (sources, resources, build script, docs)
└── analysis/                    # how the Edge hook targets were derived (+ Via browser reference)
```

## Credits / license

- Sources under `lsp_module/xposed_api/` come from **libxposed API 102**
  (`io.github.libxposed:api:102.0.0`, Apache-2.0) and are compile-time only.
- Edge / Chromium class and field names belong to their respective owners.
- Intended for legitimate personal use on your own device.
