package expression;

import exceptions.TypeException;

import java.util.ArrayList;

public abstract class AbstractOper implements FunctionInterface {
    protected final CommonExpression fun;

    public AbstractOper(CommonExpression first) {
        this.fun = first;
    }

    protected abstract ArrayList<Integer> compute(ArrayList<Integer> a) throws TypeException;

    @Override
    public ArrayList<Integer> evaluate(ArrayList<Integer> a) throws TypeException {
        return compute(a);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append(getOperationType());
        stringBuilder.append(fun.toString());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    protected abstract String getOperationType();

}