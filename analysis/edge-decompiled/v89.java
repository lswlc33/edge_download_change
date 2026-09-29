// classes4.dex Lv89;
public final class Lv89 {
    public String a;
    public String b;

    public v89(String p2, String p3)
    {
        if (p2 == null) {
            p2 = "";
        }
        this.a = p2;
        if (p3 == null) {
            p3 = "";
        }
        this.b = p3;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof v89)) {
                if ((!android.text.TextUtils.equals(this.a, ((v89) p5).a)) || (!android.text.TextUtils.equals(this.b, ((v89) p5).b))) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int hashCode()
    {
        int v0_1;
        int v0_0 = this.a;
        int v1 = 0;
        if (v0_0 != 0) {
            v0_1 = v0_0.hashCode();
        } else {
            v0_1 = 0;
        }
        int v2_3 = ((1891 + v0_1) * 31);
        String v3_1 = this.b;
        if (v3_1 != null) {
            v1 = v3_1.hashCode();
        }
        return (v2_3 + v1);
    }

    public final String toString()
    {
        return tsi.k(this.a, "_", this.b);
    }
}
