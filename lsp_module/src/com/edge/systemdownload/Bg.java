package com.edge.systemdownload;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Shared background executor + main-thread poster for the injected side. */
final class Bg {

    private static final ExecutorService POOL = Executors.newSingleThreadExecutor();
    private static final Handler MAIN = new Handler(Looper.getMainLooper());

    private Bg() {}

    static void run(Runnable task) {
        POOL.execute(task);
    }

    static void post(Runnable task) {
        MAIN.post(task);
    }

    static void postDelayed(Runnable task, long delayMs) {
        MAIN.postDelayed(task, delayMs);
    }
}
