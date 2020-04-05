package expression;

public interface LogicalInterface extends CommonExpression {
    boolean evaluate(int x);
    String toString();
}
