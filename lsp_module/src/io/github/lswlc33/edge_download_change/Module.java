package io.github.lswlc33.edge_download_change;

import io.github.libxposed.api.XposedModule;
import io.github.libxposed.api.XposedModuleInterface;

/**
 * Xposed module entry point (libxposed API 102).
 *
 * Replaces Edge for Android's built-in download popups with a two-button dialog
 * ("复制" / "下载"); "下载" hands the file over to the Android system DownloadManager.
 */
public class Module extends XposedModule {

    @Override
    public void onModuleLoaded(XposedModuleInterface.ModuleLoadedParam param) {
        String process = param.getProcessName();
        DownloadHooks.processName = process;
        log(4, DownloadHooks.TAG, "module loaded, process=" + process + ", framework="
                + getFrameworkName() + " " + getFrameworkVersion());
    }

    @Override
    public void onPackageLoaded(XposedModuleInterface.PackageLoadedParam param) {
        if (!DownloadHooks.TARGET_PACKAGE.equals(param.getPackageName())) return;
        try {
            DownloadHooks.install(this, param.getDefaultClassLoader());
        } catch (Throwable t) {
            log(5, DownloadHooks.TAG, "install() from onPackageLoaded failed", t);
        }
    }

    @Override
    public void onPackageReady(XposedModuleInterface.PackageReadyParam param) {
        if (!DownloadHooks.TARGET_PACKAGE.equals(param.getPackageName())) return;
        try {
            DownloadHooks.install(this, param.getClassLoader());
        } catch (Throwable t) {
            log(5, DownloadHooks.TAG, "install() from onPackageReady failed", t);
        }
    }
}
