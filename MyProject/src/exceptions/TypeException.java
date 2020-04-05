package exceptions;

public class TypeException extends ClassCastException {
    public TypeException() {
        super("TYPE ERROR");
    }
}