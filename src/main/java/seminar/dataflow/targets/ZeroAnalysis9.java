package seminar.dataflow.targets;

public class ZeroAnalysis9 {
    public static int func(int m, int n) {
        int x = 1;
        if (m > 0) {
            x = -1;
        }
        int j = n / x;
        int y = x * x;
        int k = n / y;
        int z = x + x;
        return j + k + z;
    }
}
