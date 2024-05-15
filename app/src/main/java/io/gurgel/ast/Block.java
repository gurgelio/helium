package io.gurgel.ast;

import java.util.List;

public record Block(List<Statement> block) implements Statement {

}
