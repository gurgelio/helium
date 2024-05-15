package io.gurgel.ast;

import io.gurgel.ast.expression.Expression;

public record VariableDeclaration(String variable, Expression value) implements Statement {
}
