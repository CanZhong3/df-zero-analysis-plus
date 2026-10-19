package seminar.dataflow;

import seminar.dataflow.util.taie.TaieRunner;
import seminar.dataflow.zeroanalysis.DivisionByZeroAnalysis;
import pascal.taie.ir.IR;
import pascal.taie.ir.stmt.Stmt;

/**
 * Launcher class for the division by zero analysis.
 */
public class Launcher {

    private static DivisionByZeroAnalysis lastDivisionByZeroAnalysis;

    /**
     * Runs the analysis on a class and prints, for each statement, its IN state and whether it
     * may divide by zero. Used by `./gradlew zeroAnalysis -PtargetClass=...`.
     * @param args the fully qualified name of the class to analyze.
     */
    public static void main(String[] args) {
        if (args.length == 0 || args[0].isEmpty()) {
            System.out.println("WARNING: No targetClass specified. Please specify a targetClass using the -PtargetClass "
                    + "argument. For example: -PtargetClass=seminar.dataflow.targets.ZeroAnalysis1");
            return;
        }
        System.out.println("Running DivisionByZeroAnalysis on class " + args[0]);
        for (IR ir : TaieRunner.loadMethods(args[0])) {
            DivisionByZeroAnalysis analysis = analyzeMethod(ir);
            System.out.println();
            System.out.println(ir.getMethod().getSignature());
            for (Stmt stmt : ir) {
                System.out.printf("  line %-3s %-30s IN: %s%n", stmt.getLineNumber(), stmt, analysis.getINSet(stmt));
            }
        }
    }

    /**
     * Runs the division by zero analysis on every method of a given target class.
     * The analysis of the last method is available through {@link #getLastDivisionByZeroAnalysis()}.
     * @param targetClass the fully qualified name of the class to analyze.
     */
    public static void analyzeClass(String targetClass) {
        for (IR ir : TaieRunner.loadMethods(targetClass)) {
            analyzeMethod(ir);
        }
    }

    private static DivisionByZeroAnalysis analyzeMethod(IR ir) {
        // Run the DivisionByZeroAnalysis on the method's control-flow graph.
        lastDivisionByZeroAnalysis = new DivisionByZeroAnalysis(TaieRunner.buildCFG(ir));

        // A Stmt is a statement of Tai-e's IR, such as an assignment, a method call, a return statement, etc.
        // For each statement, check if it is offending (i.e., if it may divide by zero).
        for (Stmt stmt : ir) {
            if (lastDivisionByZeroAnalysis.stmtIsOffending(stmt)) {
                System.out.println("Found a possible division by zero in line " + stmt.getLineNumber() + ": " + stmt);
            }
        }
        return lastDivisionByZeroAnalysis;
    }

    /**
     * Returns the last division by zero analysis that was run.
     * @return a division by zero analysis, or null.
     */
    public static DivisionByZeroAnalysis getLastDivisionByZeroAnalysis() {
        return lastDivisionByZeroAnalysis;
    }
}
