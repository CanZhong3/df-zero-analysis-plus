package seminar.dataflow.zeroanalysis;

/**
 * This enum represents the possible values of the zero analysis for a variable.
 * This is the extended lattice: besides -, 0 and +, it can say that a variable is <= 0, >= 0 or != 0.
 */
public enum ZeroAbstractValue {

    /**
     * We don't have information about the variable.
     */
    BOTTOM("bottom"),

    /**
     * The variable is negative (< 0).
     */
    NEGATIVE("negative"),

    /**
     * The variable is zero.
     */
    ZERO("zero"),

    /**
     * The variable is positive (> 0).
     */
    POSITIVE("positive"),

    /**
     * The variable is negative or zero (<= 0).
     */
    NON_POSITIVE("non-positive"),

    /**
     * The variable is positive or zero (>= 0).
     */
    NON_NEGATIVE("non-negative"),

    /**
     * The variable is not zero (< 0 or > 0).
     */
    NOT_ZERO("not-zero"),

    /**
     * The variable is top (can be any value).
     */
    TOP("top");


    /**
     * The name of the ZeroAbstractValue.
     */
    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

    ZeroAbstractValue(String name) {
        this.name = name;
    }

    /**
     * Returns the result of the addition between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the addition.
     */
    public ZeroAbstractValue add(ZeroAbstractValue another) {
        // TODO: IMPLEMENT
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the result of the division between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the division.
     */
    public ZeroAbstractValue divideBy(ZeroAbstractValue another) {
        // TODO: IMPLEMENT
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the result of the multiplication between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the multiplication.
     */
    public ZeroAbstractValue multiplyBy(ZeroAbstractValue another) {
        // TODO: IMPLEMENT
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the result of the subtraction between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the subtraction.
     */
    public ZeroAbstractValue substract(ZeroAbstractValue another) {
        // TODO: IMPLEMENT
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the result of the merge between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the merge.
     */
    public ZeroAbstractValue merge(ZeroAbstractValue another) {
        // TODO: IMPLEMENT
        throw new UnsupportedOperationException();
    }

}
