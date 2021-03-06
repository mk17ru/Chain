package expression.binary;

import exceptions.TypeException;
import expression.CommonExpression;
import expression.LogicalInterface;
import expression.OperationExpression;

public abstract class BinaryLogicalEvaluate implements LogicalInterface {
    private OperationExpression argLeft;
    private OperationExpression argRight;

    public BinaryLogicalEvaluate(CommonExpression first, CommonExpression second) {
        try {
            this.argLeft = (OperationExpression) first;
            this.argRight = (OperationExpression) second;
        } catch (ClassCastException e) {
            throw new TypeException();
        }
    }

    protected abstract boolean compute(int x, int y) throws TypeException;


    @Override
    public boolean evaluate(int x) throws TypeException {
        return compute(argLeft.evaluate(x), argRight.evaluate(x));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("(");
        stringBuilder.append(argLeft.toString());
        stringBuilder.append(getOperationType());
        stringBuilder.append(argRight.toString());
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    protected abstract String getOperationType();

}