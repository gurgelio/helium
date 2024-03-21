package io.gurgel.lexer;

public record Location(String filename, int index, int row, int column) {
  public Location clone() {
    return new Location(filename, index, row, column);
  }

  @Override
  public String toString() {
    return String.format("%s:%d:%d", filename, row, column);
  }
}
