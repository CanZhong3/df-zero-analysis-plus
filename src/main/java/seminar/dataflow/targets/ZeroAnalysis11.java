package seminar.dataflow.targets;

public class ZeroAnalysis11 {
    public static int func(int n) {
        int a = 7;
        int b = 2;
        int q = a / b;
        int r = n / q;
        int s = q + 1;
        int t = n / s;
        return r + t;
    }
}
