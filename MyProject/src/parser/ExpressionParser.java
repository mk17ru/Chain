package parser;

import exceptions.ParsingException;
import expression.*;
import expression.binary.*;
import expression.unary.*;
import operations.Operation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class ExpressionParser extends BaseParser implements Parser {

    private final static int PRIORITY_MAX = 4;

    private final ArrayList<CommonExpression> queue = new ArrayList<>();

    public ExpressionParser(StringSource stringSource) {
        super(stringSource);
    }

    public ExpressionParser() {
    }



    @Override
    public ArrayList<CommonExpression> parse(String expression) throws ParsingException {
        queue.clear();
        changeSource(new StringSource(expression));
        nextChar();
        do {
            skipWhitespace();
            CommonExpression res = parseExpression(0);
            skipWhitespace();
            queue.add(res);
        } while(test("%>%"));
        skipWhitespace();
        if (isEnd()) {
            return queue;
        } else {
            throw new ParsingException();
        }
    }

    private CommonExpression parseExpression(int priority) throws ParsingException {
        skipWhitespace();
        if (priority == PRIORITY_MAX) {
            return parseStore();
        }
        CommonExpression first = parseExpression(priority + 1);
        while (true) {
            skipWhitespace();
            OperationType operation = null;

            if (curChar() == ')' || curChar() == '}' || curChar() == '%') {
                return first;
            }

            if (curChar() == '\0') {
                return first;
            }

            switch(priority) {
                case 0:
                    if (test("&")) {
                        operation = OperationType.AND;
                    } else if (test("|")) {
                        operation = OperationType.OR;
                    }
                    break;
                case 1:
                    if (test(">")) {
                        operation = OperationType.MORE;
                    } else if (test("<")) {
                        operation = OperationType.LESS;
                    } else if (test("=")) {
                        operation = OperationType.EQUALS;
                    }
                case 2:
                    if (test("+")) {
                        operation = OperationType.ADD;
                    } else if (test("-")) {
                        operation = OperationType.SUB;
                    }
                    break;
                case 3:
                    if (test("*")) {
                        operation = OperationType.MUL;
                    }
                    break;
            }
            if (operation == null) {
                if (priority == 0) {
                    throw new ParsingException();
                } else {
                    return first;
                }
            }

            CommonExpression second = parseExpression(priority + 1);
            switch (operation) {
                case ADD:
                    first = new Add(first, second);
                    break;
                case SUB:
                    first = new Subtract(first, second);
                    break;
                case MUL:
                    first = new Multiply(first, second);
                    break;
                case AND:
                    first = new And(first, second);
                    break;
                case OR:
                     first = new Or(first, second);
                    break;
                case LESS:
                     first = new Less(first, second);
                    break;
                case MORE:
                     first = new More(first, second);
                    break;
                case EQUALS:
                    first = new Equals(first, second);
                    break;
                default:
                    throw new ParsingException();
            }
        }
    }

    private CommonExpression parseStore() throws ParsingException {
        skipWhitespace();
        if (test('(')) {
            CommonExpression arg = parseExpression(0);
            skipWhitespace();
            expect(')');
            return arg;
        }
        if (test('-')) {
            skipWhitespace();
            if (isDigit()) {
                return parseConst(true);
            }
            return new Negate(parseStore());
        }
        if (isDigit()) {
            return parseConst(false);
        }

        if (test("filter")) {
            expect("{");
            CommonExpression temp = new Filter(parseExpression(0));
            expect("}");
            return temp;
        }

        if (test("map")) {
            expect("{");
            CommonExpression temp = new Map(parseExpression(0));
            expect("}");
            return temp;
        }

        if (test("element")) {
            return parseVariable();
        }

        throw new ParsingException();

    }

    private CommonExpression parseConst(boolean minus) throws ParsingException {
        StringBuilder sb = new StringBuilder();
        if (minus) {
            sb.append('-');
        }
        copyInteger(sb);
        skipWhitespace();
        if (isDigit()) {
            throw new ParsingException();
        }
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new ParsingException();
        }
    }

    private CommonExpression parseVariable() {
        final String var = "element";
        return new Variable(var);
    }
}
