package io.gurgel.ast.expression;

import java.util.List;
import java.util.Optional;

import io.gurgel.ast.Statement;

public record FunctionDeclaration(Optional<String> name, List<String> args, Statement block)
    implements Expression {

}
