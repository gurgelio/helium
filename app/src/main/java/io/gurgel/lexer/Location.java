package io.gurgel.lexer;

// why not a record? it needs to be mutable
public class Location {
  public String filename;
  public int index;
  public int row;
  public int column;

  public Location(String filename, int index, int row, int column) {
    this.filename = filename;
    this.index = index;
    this.row = row;
    this.column = column;
  }

  public Location clone() {
    return new Location(filename, index, row, column);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Location)
      return equals((Location) o);

    return false;
  }

  public boolean equals(Location o) {
    return filename == o.filename && index == o.index && row == o.row && column == o.column;
  }

  @Override
  public String toString() {
    return String.format("%s:%d:%d", filename, row, column);
  }
}
