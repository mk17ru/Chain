package expression.binary;

import expression.CommonExpression;
import expression.LogicalInterface;
import operations.Operation;

public class Less extends BinaryLogicalEvaluate {

    public Less(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    public boolean compute(int first, int second) {
        return first < second;
    }

    @Override
    protected String getOperationType() {
        return " < ";
    }

}

