package io.gurgel.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.gurgel.lexer.Token.Type;

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

    while (currentToken.type() != Token.Type.Eof) {
      tokens.add(currentToken);
      currentToken = nextToken();
    }
    return tokens;
  }

  public Token nextToken() {
    skipWhitespace();

    Token.Type type = Type.from(getCurrentChar());

    switch (type) {
      case Type.String:
        return parseString();
      case Type.Id:
        return parseId();
      case Type.Number:
        return parseNumber();
      case Type.Eof:
        return buildToken("\0", Type.Eof);
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
    return new Token(code.substring(tokenLocation.index + 1, location.index - 1), Type.String, tokenLocation);
  }

  private Token parseId() {
    Location tokenLocation = location.clone();
    while (Character.isAlphabetic(getCurrentChar()) || Character.isDigit(getCurrentChar()) || getCurrentChar() == '_') {
      location.index++;
    }
    String content = code.substring(tokenLocation.index, location.index);

    return new Token(
        code.substring(tokenLocation.index, location.index),
        Type.lookupKeyword(content),
        tokenLocation);
  }

  private Token parseNumber() {
    Location tokenLocation = location.clone();
    while (Character.isDigit(getCurrentChar())) {
      location.index++;
    }
    return new Token(code.substring(tokenLocation.index, location.index), Type.Number, tokenLocation);
  }

  private Token buildToken(String content, Type type) {
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
