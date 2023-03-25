package pl.edu.agh.python_colouring;

enum TokenType {
    KEYWORD,
    IDENTIFIER,
    LITERAL,
    OPERATOR,
    PUNCTUATION_MARK,

    SPACE,
    NEW_LINE,
    CARRIAGE_RETURN,
    COMMENT
}

class Token {
    TokenType tokenType;
    String text;

    Token(TokenType tokenType, String text) {
        this.tokenType = tokenType;
        this.text = text;
    }

    Token(TokenType tokenType, char text) {
        this.tokenType = tokenType;
        this.text = String.valueOf(text);
    }

    @Override
    public String toString() {
        return tokenType.name() + ": " + text;
    }
}