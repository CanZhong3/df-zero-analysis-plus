package seminar.dataflow.targets;

public class ZeroAnalysis5 {
    public static int func(int p) {
        int y = 1;
        int x = 0;
        while (x != 1) {
            y =  y + 1;
            x = x - 1;
        }
        x = 10 / y;
        return y;
    }
}
