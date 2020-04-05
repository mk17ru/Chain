package expression.binary;

import expression.CommonExpression;
import expression.LogicalInterface;
import operations.Operation;

public class And extends BinaryLogicalOperation {

    public And(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    public boolean compute(boolean first, boolean second) {
        return first & second;
    }

    @Override
    protected String getOperationType() {
        return " & ";
    }

}

