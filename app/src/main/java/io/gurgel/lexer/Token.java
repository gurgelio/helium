package io.gurgel.lexer;

public record Token(String content, Type type, Location location) {

  @Override
  public String toString() {
    return String.format("'%s' at %s", content, location);
  }

  public enum Type {
    LeftParen, // (
    RightParen, // )
    LeftBrace, // [
    RightBrace, // ]
    LeftBracket, // {
    RightBracket, // }
    Comma, // ,
    Plus, // +
    Minus, // -
    DoubleAsterisk, // **
    Asterisk, // *
    Slash, // /
    LessThan, // <
    GreaterThan, // >
    DoubleEquals, // ==
    GreaterThanOrEquals, // >=
    LessThanOrEquals, // <=
    Equals, // =
    Bang, // !
    BangEquals, // !=
    String,
    Id,
    Number,
    Eof,
    Let,
    Function,
    While,
    For,
    In,
    If,
    Else,
    Return;

    public static Type from(Character c) {
      if (Character.isDigit(c))
        return Number;
      if (Character.isAlphabetic(c) || c == '_')
        return Id;
      switch (c) {
        case '(':
          return LeftParen;
        case ')':
          return RightParen;
        case '[':
          return LeftBrace;
        case ']':
          return RightBrace;
        case '{':
          return LeftBracket;
        case '}':
          return RightBracket;
        case '"':
        case '\'':
          return Type.String;
        case '+':
          return Plus;
        case '-':
          return Minus;
        case '*':
          return Asterisk;
        case '/':
          return Slash;
        case '!':
          return Bang;
        case '<':
          return LessThan;
        case '>':
          return GreaterThan;
        case ',':
          return Comma;
        case '=':
          return Equals;
        case '\0':
          return Eof;
        default:
          throw new Error(java.lang.String.format("undefined token for '%c'", c));
      }
    }

    public static Type lookupKeyword(String content) {
      switch (content) {
        case "let":
          return Type.Let;
        case "fn":
          return Type.Function;
        case "for":
          return Type.For;
        case "while":
          return Type.While;
        case "if":
          return Type.If;
        case "else":
          return Type.Else;
        case "return":
          return Type.Return;
        case "in":
          return Type.In;
        default:
          return Type.Id;
      }
    }
  }
}
