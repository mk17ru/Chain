package expression.binary;

import expression.CommonExpression;
import operations.Operation;

public class Multiply extends BinaryEvaluate {


    public Multiply(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int compute(int first, int second) {
        return first * second;
    }

    @Override
    protected String getOperationType() {
        return " * ";
    }
}