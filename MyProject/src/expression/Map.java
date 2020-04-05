package expression;

import exceptions.TypeException;
import expression.AbstractOper;
import expression.CommonExpression;
import operations.Operation;

import java.util.ArrayList;

public class Map extends AbstractOper {

    public Map(CommonExpression first) {
        super(first);
    }

    @Override
    public ArrayList<Integer> compute(ArrayList<Integer> arr) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (Integer cur : arr) {
            try {
                temp.add(((OperationExpression)fun).evaluate(cur));
            } catch (ClassCastException e) {
                throw new TypeException();
            }
        }
        return temp;
    }
    @Override
    protected String getOperationType() {
        return "map";
    }

}
