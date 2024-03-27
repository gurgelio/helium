package io.gurgel.lexer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {
  @Test
  void parseId() {
    Lexer lexer = new Lexer("let");
    Token token = lexer.nextToken();
    assertEquals("let", token.content());
    assertEquals(TokenType.Id, token.type());
    assertEquals(TokenType.Eof, lexer.nextToken().type());
  }

  @Test
  void parseNumber() {
    Lexer lexer = new Lexer("1998");
    Token token = lexer.nextToken();
    assertEquals("1998", token.content());
    assertEquals(TokenType.Number, token.type());
    assertEquals(TokenType.Eof, lexer.nextToken().type());
  }

  @Test
  void parseString() {
    Lexer lexer = new Lexer("\"hello world!\"");
    Token token = lexer.nextToken();
    assertEquals("hello world!", token.content());
    assertEquals(TokenType.String, token.type());
    assertEquals(TokenType.Eof, lexer.nextToken().type());
  }

  @Test
  void parseEmpty() {
    Lexer lexer = new Lexer("");
    assertEquals(TokenType.Eof, lexer.nextToken().type());
  }
}
