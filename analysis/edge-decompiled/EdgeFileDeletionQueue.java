// source: split_chrome chrome/classes.dex  class: org/chromium/chrome/browser/edge_hub/downloads/EdgeFileDeletionQueue
package org.chromium.chrome.browser.edge_hub.downloads;
public class EdgeFileDeletionQueue {
    private final org.chromium.base.Callback mDeleter;
    private final java.util.Queue mFilePaths;
    private org.chromium.chrome.browser.edge_hub.downloads.EdgeFileDeletionQueue$FileDeletionTask mTask;

    public EdgeFileDeletionQueue(org.chromium.base.Callback p2)
    {
        this.mFilePaths = new java.util.ArrayDeque();
        this.mDeleter = p2;
        return;
    }

    public static bridge synthetic org.chromium.base.Callback a(org.chromium.chrome.browser.edge_hub.downloads.EdgeFileDeletionQueue p0)
    {
        return p0.mDeleter;
    }

    public static bridge synthetic void b(org.chromium.chrome.browser.edge_hub.downloads.EdgeFileDeletionQueue p1)
    {
        p1.mTask = 0;
        return;
    }

    public static bridge synthetic void c(org.chromium.chrome.browser.edge_hub.downloads.EdgeFileDeletionQueue p0)
    {
        p0.deleteNextFile();
        return;
    }

    private void deleteNextFile()
    {
        if (this.mTask == null) {
            String v0_3 = ((String) this.mFilePaths.poll());
            if (v0_3 != null) {
                org.chromium.chrome.browser.edge_hub.downloads.EdgeFileDeletionQueue$FileDeletionTask v1_1 = new org.chromium.chrome.browser.edge_hub.downloads.EdgeFileDeletionQueue$FileDeletionTask(this, v0_3);
                this.mTask = v1_1;
                v1_1.executeOnExecutor(qu1.THREAD_POOL_EXECUTOR);
                return;
            }
        }
        return;
    }

    public static org.chromium.chrome.browser.edge_hub.downloads.EdgeFileDeletionQueue get()
    {
        return org.chromium.chrome.browser.edge_hub.downloads.EdgeFileDeletionQueue$LazyHolder.b();
    }

    public void delete(String p2)
    {
        this.mFilePaths.add(p2);
        this.deleteNextFile();
        return;
    }

    public void delete(java.util.List p2)
    {
        this.mFilePaths.addAll(p2);
        this.deleteNextFile();
        return;
    }
}
