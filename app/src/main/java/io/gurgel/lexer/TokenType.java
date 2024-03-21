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
  String,
  Id,
  Number,
  Eof
}
