package io.github.lswlc33.edge_download_change;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/** Opens the Xposed/LSPosed manager so the user can enable the module or check its scope. */
final class LsposedLauncher {

    /** Manager packages in order of preference (LSPosed, then the classic installer). */
    private static final String[] MANAGER_PACKAGES = {
            "org.lsposed.manager",
            "io.github.lsposed.manager",
            "de.robv.android.xposed.installer",
    };

    private static final ComponentName[] CANDIDATES = {
            new ComponentName("org.lsposed.manager", "org.lsposed.manager.ui.activity.MainActivity"),
            new ComponentName("org.lsposed.manager", "org.lsposed.manager.MainActivity"),
            new ComponentName("org.lsposed.manager", "org.lsposed.manager.ui.activity.ModulesActivity"),
            new ComponentName("io.github.lsposed.manager", "org.lsposed.manager.ui.activity.MainActivity"),
            new ComponentName("de.robv.android.xposed.installer", "de.robv.android.xposed.installer.WelcomeActivity"),
    };

    private LsposedLauncher() {}

    static boolean open(Context context) {
        for (ComponentName component : CANDIDATES) {
            try {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setComponent(component);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                return true;
            } catch (Throwable ignored) {
            }
        }
        for (String pkg : MANAGER_PACKAGES) {
            try {
                Intent launch = context.getPackageManager().getLaunchIntentForPackage(pkg);
                if (launch != null) {
                    launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(launch);
                    return true;
                }
            } catch (Throwable ignored) {
            }
        }
        return false;
    }
}
