package expression;

import java.util.ArrayList;

public interface FunctionInterface extends CommonExpression {
    ArrayList<Integer> evaluate(ArrayList<Integer> a);
}
