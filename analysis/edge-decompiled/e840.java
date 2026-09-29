// classes4.dex Le840;
public abstract class Le840 extends android.app.Service {
    public static final synthetic int c;
    public final String a;
    public d840 b;

    public e840(String p1)
    {
        this.a = p1;
        return;
    }

    public final int a(int p1, int p2, android.content.Intent p3)
    {
        return super.onStartCommand(p3, p1, p2);
    }

    public void attachBaseContext(android.content.Context p3)
    {
        org.chromium.base.BundleUtils.g(p3, org.chromium.base.BundleUtils.c("chrome"));
        d840 v0_2 = ((d840) org.chromium.base.BundleUtils.f(this.a, "chrome"));
        this.b = v0_2;
        v0_2.a = this;
        super.attachBaseContext(p3);
        return;
    }

    public final boolean b(android.content.Intent p1)
    {
        return super.onUnbind(p1);
    }

    public android.os.IBinder onBind(android.content.Intent p1)
    {
        return this.b.a(p1);
    }

    public final void onCreate()
    {
        super.onCreate();
        this.b.b();
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        this.b.c();
        return;
    }

    public final void onLowMemory()
    {
        super.onLowMemory();
        this.b.d();
        return;
    }

    public final int onStartCommand(android.content.Intent p1, int p2, int p3)
    {
        return this.b.e(p1, p2, p3);
    }

    public final void onTaskRemoved(android.content.Intent p1)
    {
        super.onTaskRemoved(p1);
        this.b.f(p1);
        return;
    }

    public final boolean onUnbind(android.content.Intent p1)
    {
        return this.b.g(p1);
    }
}
