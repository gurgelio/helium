package io.gurgel.lexer;

public enum TokenType {
  LeftParen, // (
  RightParen, // )
  LeftBrace, // [
  RightBrace, // ]
  LeftBracket, // {
  RightBracket, // }
  Comma, // ,
  Plus, // +
  Minus, // -
  Asterisk, // *
  Slash, // /
  LessThan, // <
  GreaterThan, // >
  Equals, // =
  String,
  Id,
  Number,
  Eof;

  public static TokenType from(Character c) {
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
        return TokenType.String;
      case '+':
        return Plus;
      case '-':
        return Minus;
      case '*':
        return Asterisk;
      case '/':
        return Slash;
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
}
