package expression.unary;


import expression.CommonExpression;
import operations.Operation;

public final class Negate extends UnaryEvaluate {

    public Negate(CommonExpression value) {
        super(value);
    }

    @Override
    protected int compute(int x) {
        return -x;
    }

    @Override
    protected String getOperationType() {
        return "-";
    }
}