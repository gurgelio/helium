package io.gurgel.lexer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenTest {
  @Test
  void equals() {
    assertEquals(
        new Token("*", TokenType.Asterisk, new Location("test.he", 0, 1, 2)),
        new Token("*", TokenType.Asterisk, new Location("test.he", 0, 1, 2)));
  }

  @Test
  void notEquals() {
    assertNotEquals(
        new Token("*", TokenType.Asterisk, new Location("test.he", 0, 1, 2)),
        new Token("-", TokenType.Asterisk, new Location("test.he", 0, 1, 2)));
  }

  @Test
  void tokenToString() {
    assertEquals(new Token("let", TokenType.Id, new Location("main.he", 0, 0, 0)).toString(), "'let' at main.he:0:0");
  }
}
