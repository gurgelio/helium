package io.gurgel;

import io.gurgel.lexer.Lexer;

public class HeliumCompiler {
  public static void main(String[] args) {
    Lexer lexer = new Lexer("let a = ''");

    System.out.println(lexer.getTokens());
  }
}
