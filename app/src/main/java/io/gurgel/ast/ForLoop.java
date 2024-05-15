package io.gurgel.ast;

import io.gurgel.ast.expression.Expression;

public record ForLoop(String name, Expression list, Block block) implements Statement {

}
