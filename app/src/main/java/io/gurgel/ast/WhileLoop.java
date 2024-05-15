package io.gurgel.ast;

import io.gurgel.ast.expression.Expression;

public record WhileLoop(Expression condition, Statement statement) implements Statement {

}
