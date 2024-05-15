package io.gurgel.ast;

import java.util.Optional;

import io.gurgel.ast.expression.Expression;

public record IfCondition(Expression condition, Statement consequence, Optional<Statement> alternative)
    implements Statement {

}
