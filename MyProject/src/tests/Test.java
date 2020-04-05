package tests;

import exceptions.ParsingException;
import exceptions.TypeException;
import expression.CommonExpression;
import expression.FunctionInterface;
import parser.ExpressionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    private static ExpressionParser expressionParser = new ExpressionParser();

    private static void test() {
        List<String> tests = List.of(
                "filter{(element>10)}%>%filter{(element<20)}",
                "filter{(element>10)&(element<20)}%>%map{element}",
                "map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}",
                "filter{((element+10)>10)}%>%map{((element+10)*(element+10))}",
                "map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}",
                "filter{(element>0)}%>%map{((element*element)+((element*20)+100))}",
                "filter{(element>0)}%>%filter{(element<0)}%>%map{(element*element)}",
                "filter{(1=0)}%>%map{element}"
        );
        ArrayList<Integer> array = new ArrayList<>(List.of(1, 2, 3, 10, 5, 12, 7, -4, 2, 7));
        ArrayList<Integer> temp;
        for (int i = 0; i < tests.size(); ++i) {
            temp = array;
            ArrayList<CommonExpression> expression;
            try {
                expression = expressionParser.parse(tests.get(i));
            } catch (ParsingException e) {
                System.err.println("Unexpected Syntax error: " + tests.get(i));
                return;
            }
            try {
                for (CommonExpression t : expression) {
                    temp = ((FunctionInterface) t).evaluate(temp);
                }
            } catch (TypeException e) {
                System.err.println("Unexpected Type error: " + tests.get(i));
                return;
            }
        }
        System.out.println("Right tests completed!");
        List<String> wrongTests = List.of(
                "filter{(element>10 + )}%>%filter{( | element<20)}",
                "filter{(element>10)&(element<20 | )}%>%map{element}",
                "map{(element+10)}%>%filter{(lement>10)}%>%map{(element*element)}",
                "map{(element+10)}%>%filter(element>10-)}%>%map{(element*element)}",
                "filter{((element+10)>10)}%>%map{{((element+10)*(element+10))}",
                "map{(element+10)}%>%filter{((element>10)}%>%map{(element*element)}",
                "filter{(element>0)}%>%ap{((element*element)+((element*20)+100))}",
                "filter{(element  0)}%>%fiter{(element<0)}%>%map{(element*element)}",
                "filter{(1=0)}%>map{element & 1}",
                "map{ele + 1}"
        );

        for (int i = 0; i < wrongTests.size(); ++i) {
            temp = array;
            ArrayList<CommonExpression> expression;
            expressionParser = new ExpressionParser();
            try {
                expression = expressionParser.parse(wrongTests.get(i));
                try {
                    for (CommonExpression t : expression) {
                        temp = ((FunctionInterface) t).evaluate(temp);
                    }
                    System.err.println("Uncaught test: " + wrongTests.get(i));
                    return;
                } catch (TypeException e) {
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Wrong tests caught!");
    }


    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        test();

    }
}
