package seminar.dataflow.targets;

public class ZeroAnalysis4 {
    public static int func(int m, int n) {
        int x = 0;
        if (m != 0) {
            x = m;
        } else {
            x = 1;
        }
        int y = x + 1;
        int j = n / y;
        return j;
    }
}
