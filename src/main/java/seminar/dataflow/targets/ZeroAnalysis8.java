package seminar.dataflow.targets;

public class ZeroAnalysis8 {
    public static int func(int n) {
        int i = 0;
        while (i < n) {
            i = i + 1;
        }
        int k = i + 1;
        int j = n / k;
        int r = n / i;
        return j + r;
    }
}
