package seminar.dataflow.targets;

public class ZeroAnalysis10 {
    public static int func(int n) {
        int x = 0;
        if (n > 0) {
            x = -5;
        }
        int y = x - 1;
        int j = n / y;
        int k = n / x;
        return j + k;
    }
}
