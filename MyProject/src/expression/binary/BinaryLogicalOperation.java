package expression.binary;

import exceptions.TypeException;
import expression.CommonExpression;
import expression.LogicalInterface;
import operations.Operation;

import java.util.Objects;

public abstract class BinaryLogicalOperation implements LogicalInterface {
    private LogicalInterface argLeft;
    private LogicalInterface argRight;

    public BinaryLogicalOperation(CommonExpression first, CommonExpression second) {
        try {
            this.argLeft = (LogicalInterface) first;
            this.argRight = (LogicalInterface) second;
        } catch (ClassCastException e) {
            throw new TypeException();
        }
    }

    protected abstract boolean compute(boolean x, boolean y) throws TypeException;


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