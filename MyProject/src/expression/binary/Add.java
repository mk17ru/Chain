package expression.binary;

import expression.CommonExpression;
import operations.Operation;

public class Add extends BinaryEvaluate {

    public Add(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int compute(int first, int second) {
        return first + second;
    }

    @Override
    protected String getOperationType() {
        return " + ";
    }

}
