package io.gurgel.ast;

import io.gurgel.ast.expression.Expression;

public record Assignment(String variable, Expression value) implements Statement {

}
