package expression.unary;

import exceptions.TypeException;
import expression.CommonExpression;
import expression.OperationExpression;

public abstract class UnaryEvaluate implements CommonExpression {

    private final OperationExpression value;

    public UnaryEvaluate(CommonExpression value) {
        try {
            this.value = (OperationExpression) value;
        } catch (ClassCastException e) {
            throw new TypeException();
        }
    }

    protected abstract int compute(int x);


    public int evaluate(int x) {
        return compute(value.evaluate(x));
    }

    protected abstract String getOperationType();

    @Override
    public String toString() {
        return getOperationType() + value;
    }

}