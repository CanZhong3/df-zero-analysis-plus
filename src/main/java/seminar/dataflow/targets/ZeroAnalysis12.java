package seminar.dataflow.targets;

public class ZeroAnalysis12 {
    public static int func(int m) {
        int i = 0;
        int x = i - 1;
        while (i < m) {
            x = x - 1;
            i = i + 1;
        }
        int y = x * x;
        int z = 100 / y;
        int w = x * i;
        int v = w - 1;
        int u = m / v;
        return z + u;
    }
}
