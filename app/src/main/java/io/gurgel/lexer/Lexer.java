package io.gurgel.lexer;

public class Lexer {
  String code;
  Location currentLocation = new Location("", 0, 0, 0);

  public Lexer(String code) {
    this.code = code;
  }

  public Token nextToken() {

    return new Token("", TokenType.Eof, currentLocation.clone());
  }
}
