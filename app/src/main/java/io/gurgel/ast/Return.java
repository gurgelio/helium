package io.gurgel.ast;

import java.util.Optional;

import io.gurgel.ast.expression.Expression;

public record Return(Optional<Expression> returnValue) implements Statement {

}
