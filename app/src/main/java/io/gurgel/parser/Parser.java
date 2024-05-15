package io.gurgel.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import io.gurgel.ast.Assignment;
import io.gurgel.ast.Block;
import io.gurgel.ast.VariableDeclaration;
import io.gurgel.ast.WhileLoop;
import io.gurgel.ast.ForLoop;
import io.gurgel.ast.IfCondition;
import io.gurgel.ast.NoOp;
import io.gurgel.ast.Return;
import io.gurgel.ast.Statement;
import io.gurgel.ast.expression.BinaryOperation;
import io.gurgel.ast.expression.Expression;
import io.gurgel.ast.expression.FunctionCall;
import io.gurgel.ast.expression.FunctionDeclaration;
import io.gurgel.ast.expression.Variable;
import io.gurgel.lexer.Token;

public class Parser {
  private List<Token> tokens;
  private int currentIndex = 0;

  public Parser(List<Token> tokens) {
    this.tokens = tokens;
  }

  public ArrayList<Statement> parse() {
    ArrayList<Statement> statements = new ArrayList<>();

    while (currentIndex < tokens.size()) {
      statements.add(parseStatement());
    }

    return statements;
  }

  private Statement parseStatement() {
    switch (currentToken().type()) {
      case Token.Type.Let:
        return parseDeclaration();
      case Token.Type.Function:
        return parseFunctionDeclaration();
      case Token.Type.For:
        return parseForLoop();
      case Token.Type.While:
        return parseWhileLoop();
      case Token.Type.If:
        return parseIfCondition();
      case Token.Type.LeftBracket:
        return parseBlock();
      case Token.Type.Id:
        return parseIdStatement();
      case Token.Type.Return:
        return parseReturn();
      case Token.Type.Eof:
        return new NoOp();
      default:
        return parseExpression();
    }
  }

  /*
   * LeftParen, // (
   * RightParen, // )
   * LeftBrace, // [
   * RightBrace, // ]
   * Plus, // +
   * Minus, // -
   * String,
   * Id,
   * Number,
   */
  private Expression parseExpression() {
    switch (currentToken().type()) {
      case Token.Type.Id:
        return parseIdExpression();
    }
    return new FunctionCall("", Arrays.asList());
  }

  private Statement parseIdStatement() {
    switch (tokens.get(currentIndex + 1).type()) {
      case Token.Type.Equals:
        return parseAssignment();
      default:
        return parseExpression();
    }
  }

  private Expression parseIdExpression() {
    Token name = eat(Token.Type.Id);
    if (BinaryOperation.Type.from(currentToken().type()).isPresent()) {
      return parseBinaryExpression(new Variable(name.content()));
    }
  }

  private Expression parseBinaryExpression(Expression left) {
    Token operator = currentToken();
    Expression right = parseExpression();
    return new BinaryOperation(left, operator.type(), right);
  }

  private Return parseReturn() {
    eat(Token.Type.Return);
    // TODO
    return new Return(Optional.empty());
  }

  private IfCondition parseIfCondition() {
    eat(Token.Type.If);
    Expression condition = parseExpression();
    Statement consequence = parseStatement();
    Optional<Statement> alternative = eatIf(Token.Type.Else).isPresent() ? Optional.of(parseStatement())
        : Optional.empty();

    return new IfCondition(condition, consequence, alternative);
  }

  private WhileLoop parseWhileLoop() {
    eat(Token.Type.While);
    Expression condition = parseExpression();
    Statement loop = parseStatement();

    return new WhileLoop(condition, loop);
  }

  private VariableDeclaration parseDeclaration() {
    eat(Token.Type.Let);
    Token variable = eat(Token.Type.Id);
    eat(Token.Type.Equals);
    Expression expression = parseExpression();

    return new VariableDeclaration(variable.content(), expression);
  }

  private ForLoop parseForLoop() {
    eat(Token.Type.For);
    Token name = eat(Token.Type.Id);
    eat(Token.Type.In);
    Expression expression = parseExpression();

    return new ForLoop(name.content(), expression, parseBlock());
  }

  private Assignment parseAssignment() {
    Token variable = eat(Token.Type.Id);
    eat(Token.Type.Equals);
    Expression expression = parseExpression();

    return new Assignment(variable.content(), expression);
  }

  private FunctionDeclaration parseFunctionDeclaration() {
    eat(Token.Type.Function);
    Optional<Token> name = currentToken().type() == Token.Type.Id ? Optional.of(eat(Token.Type.Id)) : Optional.empty();
    eat(Token.Type.LeftParen);
    ArrayList<String> args = new ArrayList<String>();
    while (eatIf(Token.Type.RightParen).isEmpty()) {
      args.add(eat(Token.Type.Id).content());

      if (eatIf(Token.Type.Comma).isEmpty()) {
        eat(Token.Type.RightParen);
        break;
      }
    }

    return new FunctionDeclaration(name.map(Token::content), args, parseExpression());
  }

  private Block parseBlock() {
    eat(Token.Type.LeftBracket);
    ArrayList<Statement> statements = new ArrayList<Statement>();
    while (eatIf(Token.Type.RightBracket).isEmpty()) {
      statements.add(parseStatement());
    }
    eat(Token.Type.RightBracket);

    return new Block(statements);
  }

  private FunctionCall parseFunctionCall() {
    Token name = eat(Token.Type.Id);
    eat(Token.Type.LeftParen);
    ArrayList<Expression> args = new ArrayList<Expression>();
    while (eatIf(Token.Type.RightParen).isEmpty()) {
      args.add(parseExpression());
      if (eatIf(Token.Type.Comma).isEmpty()) {
        eat(Token.Type.RightParen);
        break;
      }
    }

    return new FunctionCall(name.content(), args);
  }

  private Optional<Token> eatIf(Token.Type expectedType) {
    if (tokens.get(currentIndex).type() == expectedType) {
      return Optional.of(tokens.get(currentIndex++));
    }
    return Optional.empty();
  }

  private Token eat(Token.Type expectedType) {
    if (tokens.get(currentIndex).type() != expectedType) {
      // TODO: custom error
      throw new Error("unexpected token!!");
    }

    return tokens.get(currentIndex++);
  }

  private Token currentToken() {
    return tokens.get(currentIndex);
  }
}
