package io.gurgel.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
  String code;
  Location location = new Location("", 0, 0, 0);
  private List<Character> whitespaceChars = Arrays.asList(' ', '\n', '\r', '\t');

  public Lexer(String code) {
    this.code = code.trim();
  }

  public ArrayList<Token> getTokens() {
    ArrayList<Token> tokens = new ArrayList<Token>();
    Token currentToken = nextToken();

    while (currentToken.type() != TokenType.Eof) {
      tokens.add(currentToken);
      currentToken = nextToken();
    }
    return tokens;
  }

  public Token nextToken() {
    skipWhitespace();

    TokenType type = TokenType.from(getCurrentChar());

    switch (type) {
      case TokenType.String:
        return parseString();
      case TokenType.Id:
        return parseId();
      case TokenType.Number:
        return parseNumber();
      case TokenType.Eof:
        return buildToken("\0", TokenType.Eof);
      default:
        return buildToken(getCurrentChar().toString(), type);
    }
  }

  private Token parseString() {
    Location tokenLocation = location.clone();
    Character openingQuote = getCurrentChar();
    location.index++;
    while (getCurrentChar() != openingQuote) {
      location.index++;
    }
    location.index++;
    return new Token(code.substring(tokenLocation.index + 1, location.index - 1), TokenType.String, tokenLocation);
  }

  private Token parseId() {
    Location tokenLocation = location.clone();
    while (Character.isAlphabetic(getCurrentChar()) || Character.isDigit(getCurrentChar()) || getCurrentChar() == '_') {
      location.index++;
    }
    return new Token(code.substring(tokenLocation.index, location.index), TokenType.Id, tokenLocation);
  }

  private Token parseNumber() {
    Location tokenLocation = location.clone();
    while (Character.isDigit(getCurrentChar())) {
      location.index++;
    }
    return new Token(code.substring(tokenLocation.index, location.index), TokenType.Number, tokenLocation);
  }

  private Token buildToken(String content, TokenType type) {
    Location tokenLocation = location.clone();
    location.index += content.length();
    return new Token(content, type, tokenLocation);
  }

  private void skipWhitespace() {
    while (whitespaceChars.contains(getCurrentChar())) {
      if (getCurrentChar().equals('\n')) {
        location.row++;
        location.column = 0;
      }
      location.index++;
    }
  }

  private Character getCurrentChar() {
    if (location.index >= code.length())
      return '\0';
    return code.charAt(location.index);
  }
}
