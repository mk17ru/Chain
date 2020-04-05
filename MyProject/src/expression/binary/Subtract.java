package expression.binary;

import expression.CommonExpression;
import operations.Operation;

public class Subtract extends BinaryEvaluate {


    public Subtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int compute(int first, int second) {
        return first - second;
    }

    @Override
    protected String getOperationType() {
        return " - ";
    }

}