package io.gurgel.ast.expression;

import java.util.Optional;

import io.gurgel.lexer.Token;

public class BinaryOperation implements Expression {
  public Expression left;
  public Expression right;
  public Type type;

  public BinaryOperation(Expression left, Token.Type operator, Expression right) {
    this.left = left;
    this.right = right;
    this.type = Type.from(operator).orElseThrow();
  }

  public enum Type {
    Addition,
    Subtraction,
    Division,
    Multiplication,
    Exponentiation,
    Equals,
    NotEquals,
    GreaterThan,
    GreaterThanOrEquals,
    LessThan,
    LessThanOrEquals;

    public static Optional<Type> from(Token.Type type) {
      switch (type) {
        case Token.Type.Plus:
          return Optional.of(Addition);
        case Token.Type.Minus:
          return Optional.of(Subtraction);
        case Token.Type.Slash:
          return Optional.of(Division);
        case Token.Type.Asterisk:
          return Optional.of(Multiplication);
        case Token.Type.DoubleAsterisk:
          return Optional.of(Exponentiation);
        case Token.Type.DoubleEquals:
          return Optional.of(Equals);
        case Token.Type.BangEquals:
          return Optional.of(NotEquals);
        case Token.Type.GreaterThan:
          return Optional.of(GreaterThan);
        case Token.Type.GreaterThanOrEquals:
          return Optional.of(GreaterThanOrEquals);
        case Token.Type.LessThan:
          return Optional.of(LessThan);
        case Token.Type.LessThanOrEquals:
          return Optional.of(LessThanOrEquals);
        default:
          return Optional.empty();
      }
    }
  }
}
