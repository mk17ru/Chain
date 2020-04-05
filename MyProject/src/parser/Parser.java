package parser;

import exceptions.ParsingException;
import expression.*;

import java.util.ArrayList;

public interface Parser {
    ArrayList<CommonExpression> parse(String expression) throws ParsingException;
}