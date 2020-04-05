package expression;

import exceptions.ParsingException;
import exceptions.TypeException;
import parser.ExpressionParser;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionParser expressionParser = new ExpressionParser();
        ArrayList<CommonExpression> expression;
        try {
            expression = expressionParser.parse(scanner.nextLine());
        } catch(ParsingException e) {
            System.out.println("SYNTAX ERROR");
            return;
        }
        // Expression to string
        //System.out.println(expression.toString());
        ArrayList<Integer> array = new ArrayList<>(List.of(1, 2, 3, 10, 5, 12, 7));
        ArrayList<Integer> temp = array;
        for (CommonExpression t : expression) {
            try {
                temp = ((FunctionInterface) t).evaluate(temp);
            } catch (ClassCastException e) {
                System.out.println("TYPE ERROR");
                return;
            }
        }

        for (Integer el : temp) {
            System.out.print(el + " ");
        }
        System.out.println("\n");
    }

}