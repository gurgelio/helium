package io.gurgel.lexer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenTypeTest {
  @Test
  void equals() {
    assertEquals(TokenType.Comma, TokenType.Comma);
  }

  @Test
  void notEquals() {
    assertNotEquals(TokenType.Id, TokenType.String);
  }

  @Test
  void tokenTypeToString() {
    assertEquals(TokenType.Id.toString(), "Id");
  }
}
