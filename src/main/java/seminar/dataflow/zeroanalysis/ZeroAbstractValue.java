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
        if (this.equals(BOTTOM) || another.equals(BOTTOM)) {
            return BOTTOM;
        }

        if (this.equals(TOP) || another.equals(TOP)) {
            return TOP;
        }

        if (this.equals(ZERO)) {
            return another;
        }

        if (another.equals(ZERO)) {
            return this;
        }

        if (this.equals(another)) {
            // NOT_ZERO + NOT_ZERO can be negative, zero or positive
            return this.equals(NOT_ZERO) ? TOP : this;
        }

        if (this.equals(NEGATIVE)) {
            // NEGATIVE + NON_POSITIVE stays negative; everything else can be any sign
            return another.equals(NON_POSITIVE) ? NEGATIVE : TOP;
        }

        if (this.equals(POSITIVE)) {
            return another.equals(NON_NEGATIVE) ? POSITIVE : TOP;
        }

        if (this.equals(NON_POSITIVE)) {
            return another.equals(NEGATIVE) ? NEGATIVE : TOP;
        }

        if (this.equals(NON_NEGATIVE)) {
            return another.equals(POSITIVE) ? POSITIVE : TOP;
        }

        // NOT_ZERO with any other value
        return TOP;
    }


    /**
     * Returns the result of the division between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the division.
     */
    public ZeroAbstractValue divideBy(ZeroAbstractValue another) {
        if (this.equals(BOTTOM) || another.equals(BOTTOM)) {
            return BOTTOM;
        }

        // A division by zero has no result; the statement is reported separately.
        if (another.equals(ZERO)) {
            return BOTTOM;
        }

        if (this.equals(ZERO)) {
            return ZERO;
        }

        if (this.equals(TOP) || another.equals(TOP)) {
            return TOP;
        }

        // Integer division truncates (1/2 = 0, -1/2 = 0): every non-zero
        // numerator can give 0, so only the sign of the divisor matters.
        if (another.equals(NOT_ZERO)) {
            return TOP;
        }

        if (another.equals(POSITIVE) || another.equals(NON_NEGATIVE)) {
            if (this.equals(NEGATIVE)) {
                return NON_POSITIVE;
            }
            if (this.equals(POSITIVE)) {
                return NON_NEGATIVE;
            }
            if (this.equals(NON_POSITIVE)) {
                return NON_POSITIVE;
            }
            if (this.equals(NON_NEGATIVE)) {
                return NON_NEGATIVE;
            }
            return TOP;
        }

        if (another.equals(NEGATIVE) || another.equals(NON_POSITIVE)) {
            if (this.equals(NEGATIVE)) {
                return NON_NEGATIVE;
            }
            if (this.equals(POSITIVE)) {
                return NON_POSITIVE;
            }
            if (this.equals(NON_POSITIVE)) {
                return NON_NEGATIVE;
            }
            if (this.equals(NON_NEGATIVE)) {
                return NON_POSITIVE;
            }
            return TOP;
        }

        return BOTTOM;
    }


    /**
     * Returns the result of the multiplication between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the multiplication.
     */
    public ZeroAbstractValue multiplyBy(ZeroAbstractValue another) {
        if(this.equals(BOTTOM) || another.equals(BOTTOM)){
            return BOTTOM;
        }

        if(this.equals(ZERO) || another.equals(ZERO)){
            return ZERO;
        }

        if(this.equals(TOP) || another.equals(TOP)){
            return TOP;
        }

        if(this.equals(NEGATIVE)){
            if(another.equals(NEGATIVE)){
                return POSITIVE;
            }
            if(another.equals(POSITIVE)){
                return NEGATIVE;
            }
            if(another.equals(NON_NEGATIVE)){
                return NON_POSITIVE;
            }
            if(another.equals(NON_POSITIVE)){
                return NON_NEGATIVE;
            }
            if(another.equals(NOT_ZERO)){
                return NOT_ZERO;
            }
        }

        if(this.equals(POSITIVE)){
            return another;
        }

        if(this.equals(NON_POSITIVE)){
            if(another.equals(NEGATIVE) || another.equals(NON_POSITIVE)){
                return NON_NEGATIVE;
            }
            if(another.equals(POSITIVE) || another.equals(NON_NEGATIVE)){
                return NON_POSITIVE;
            }
            return TOP;
        }

        if(this.equals(NON_NEGATIVE)){
            if(another.equals(NEGATIVE) || another.equals(NON_POSITIVE)){
                return NON_POSITIVE;
            }
            if(another.equals(POSITIVE) || another.equals(NON_NEGATIVE)){
                return NON_NEGATIVE;
            }
            return TOP;
        }

        if(this.equals(NOT_ZERO)){
            if(another.equals(NEGATIVE) || another.equals(POSITIVE) || another.equals(NOT_ZERO)){
                return NOT_ZERO;
            }
            return TOP;
        }

        return BOTTOM;
    }

    /**
     * Returns the result of the subtraction between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the subtraction.
     */
    public ZeroAbstractValue substract(ZeroAbstractValue another) {
        if(this.equals(BOTTOM) || another.equals(BOTTOM)){
            return BOTTOM;
        }

        if(this.equals(TOP) || another.equals(TOP)){
            return TOP;
        }

        if(this.equals(NEGATIVE)){
            if(another.equals(NEGATIVE) || another.equals(NON_POSITIVE) || another.equals(NOT_ZERO)){
                return TOP;
            }
            if(another.equals(ZERO) || another.equals(POSITIVE) || another.equals(NON_NEGATIVE)){
                return NEGATIVE;
            }
        }

        if(this.equals(ZERO)){
            if(another.equals(NEGATIVE)){
                return POSITIVE;
            }
            if(another.equals(ZERO)){
                return ZERO;
            }
            if(another.equals(POSITIVE)){
                return NEGATIVE;
            }
            if(another.equals(NON_POSITIVE)){
                return NON_NEGATIVE;
            }
            if(another.equals(NON_NEGATIVE)){
                return NON_POSITIVE;
            }
            if(another.equals(NOT_ZERO)){
                return NOT_ZERO;
            }
        }

        if(this.equals(POSITIVE)){
            if(another.equals(NEGATIVE) || another.equals(ZERO) || another.equals(NON_POSITIVE)){
                return POSITIVE;
            }
            if(another.equals(POSITIVE) || another.equals(NON_NEGATIVE) || another.equals(NOT_ZERO)){
                return TOP;
            }
        }

        if(this.equals(NON_POSITIVE)){
            if(another.equals(NEGATIVE) || another.equals(NON_POSITIVE) || another.equals(NOT_ZERO)){
                return TOP;
            }
            if(another.equals(ZERO) || another.equals(NON_NEGATIVE)){
                return NON_POSITIVE;
            }
            if(another.equals(POSITIVE)){
                return NEGATIVE;
            }
        }

        if(this.equals(NON_NEGATIVE)){
            if(another.equals(NEGATIVE)){
                return POSITIVE;
            }
            if(another.equals(ZERO) || another.equals(NON_POSITIVE)){
                return NON_NEGATIVE;
            }
            return TOP;
        }

        if(this.equals(NOT_ZERO)){
            if(another.equals(ZERO)){
                return NOT_ZERO;
            }
            return TOP;
        }

        return BOTTOM;
    }

    /**
     * Returns the result of the merge between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the merge.
     */
    public ZeroAbstractValue merge(ZeroAbstractValue another) {
        if (this.equals(TOP) || another.equals(TOP)){
            return TOP;
        }

        if(this.equals(BOTTOM)){
            return another;
        }

        if(another.equals(BOTTOM)){
            return this;
        }

        if(this.equals(another)){
            return this;
        }

        if(this.equals(NEGATIVE) && another.equals(ZERO)){
            return NON_POSITIVE;
        }

        if(this.equals(NEGATIVE) && another.equals(POSITIVE)){
            return NOT_ZERO;
        }

        if(this.equals(NEGATIVE) && another.equals(NON_POSITIVE)){
            return NON_POSITIVE;
        }

        if(this.equals(NEGATIVE) && another.equals(NON_NEGATIVE)){
            return TOP;
        }

        if(this.equals(NEGATIVE) && another.equals(NOT_ZERO)){
            return NOT_ZERO;
        }

        if(this.equals(ZERO) && another.equals(NEGATIVE)){
            return NON_POSITIVE;
        }

        if(this.equals(ZERO) && another.equals(POSITIVE)){
            return NON_NEGATIVE;
        }

        if(this.equals(ZERO) && another.equals(NON_POSITIVE)){
            return NON_POSITIVE;
        }

        if(this.equals(ZERO) && another.equals(NON_NEGATIVE)){
            return NON_NEGATIVE;
        }

        if(this.equals(ZERO) && another.equals(NOT_ZERO)){
            return TOP;
        }

        if(this.equals(POSITIVE)){
            if(another.equals(NEGATIVE)){
                return NOT_ZERO;
            }
            if(another.equals(ZERO)){
                return NON_NEGATIVE;
            }
            if(another.equals(POSITIVE)){
                return POSITIVE;
            }
            if(another.equals(NON_POSITIVE)){
                return TOP;
            }
            if(another.equals(NON_NEGATIVE)){
                return NON_NEGATIVE;
            }
            if(another.equals(NOT_ZERO)){
                return NOT_ZERO;
            }
        }

        if(this.equals(NON_POSITIVE)){
            if(another.equals(NEGATIVE)){
                return NON_POSITIVE;
            }
            if(another.equals(ZERO)){
                return NON_POSITIVE;
            }
            if(another.equals(POSITIVE)){
                return TOP;
            }
            if(another.equals(NON_NEGATIVE)){
                return TOP;
            }
            if(another.equals(NOT_ZERO)){
                return TOP;
            }
        }

        if(this.equals(NON_NEGATIVE)){
            if(another.equals(NEGATIVE) || another.equals(NON_POSITIVE)){
                return TOP;
            }
            if(another.equals(ZERO) || another.equals(POSITIVE)){
                return NON_NEGATIVE;
            }
            if(another.equals(NOT_ZERO)){
                return TOP;
            }
        }

        if(this.equals(NOT_ZERO)){
            if(another.equals(NEGATIVE) || another.equals(POSITIVE)){
                return NOT_ZERO;
            }
            return TOP;
        }

        return BOTTOM;
    }

}
