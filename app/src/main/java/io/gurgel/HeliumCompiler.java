package io.gurgel;

import io.gurgel.lexer.Lexer;

public class HeliumCompiler {
  public static void main(String[] args) {
    Lexer lexer = new Lexer("");
    System.out.println(lexer.nextToken());
  }
}
