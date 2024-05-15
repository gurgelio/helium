package io.gurgel.ast.expression;

import java.util.List;

public record FunctionCall(String name, List<Expression> args) implements Expression {

}
