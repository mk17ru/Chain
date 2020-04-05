package expression;

public class Const implements OperationExpression {
    private final Integer val;

    public Const(int val) {
        this.val = val;
    }

    public int evaluate(int x) {
        return this.val;
    }

    public String toString() {
        return val.toString();
    }
}