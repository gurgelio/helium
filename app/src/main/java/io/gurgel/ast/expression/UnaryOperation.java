package io.gurgel.ast.expression;

import io.gurgel.lexer.Token;

public class UnaryOperation implements Expression {
  public Expression expression;
  public Type type;

  UnaryOperation(Expression expression, Token.Type operator) {
    this.expression = expression;
    this.type = Type.from(operator);
  }

  public enum Type {
    Not,
    Negative;

    public static Type from(Token.Type type) {
      switch (type) {
        case Token.Type.Bang:
          return Not;
        case Token.Type.Minus:
          return Negative;
        default:
          throw new Error(java.lang.String.format("expected unary operation, but got '%s'", type));
      }
    }
  }

}
