package io.gurgel.lexer;

public record Token(String content, TokenType type, Location location) {

  @Override
  public String toString() {
    return String.format("'%s' at %s", content, location);
  }
}
