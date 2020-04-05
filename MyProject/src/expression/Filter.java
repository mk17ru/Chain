package expression;

import exceptions.TypeException;
import expression.AbstractOper;
import expression.CommonExpression;
import operations.Operation;

import java.util.ArrayList;

public class Filter extends AbstractOper {

    public Filter(CommonExpression first) {
        super(first);
    }

    @Override
    public ArrayList<Integer> compute(ArrayList<Integer> arr) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (Integer cur : arr) {
            try {
                if (((LogicalInterface) fun).evaluate(cur)) {
                    temp.add(cur);
                }
            } catch (ClassCastException e) {
                throw new TypeException();
            }
        }
        return temp;
    }

    @Override
    protected String getOperationType() {
        return "filter";
    }

}
