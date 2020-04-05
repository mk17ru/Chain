package exceptions;

public class ParsingException extends Exception {
    public ParsingException() {
        super("SYNTAX ERROR");
    }
}