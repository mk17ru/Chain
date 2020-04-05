package expression;

public class Variable implements OperationExpression {
    private final String val;

    public Variable(String val) {
        this.val = val;
    }

    public int evaluate(int x) {
        return x;
    }

    public String toString() {
        return val;
    }
}