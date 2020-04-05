package parser;

public interface ExpressionSource {
    boolean hasNext();

    char next();

    int curPosition();

    String getSource();
}