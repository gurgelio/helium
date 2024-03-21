package io.gurgel.lexer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
  @Test
  void equals() {
    Location l = new Location("test.he", 10, 2, 5);
    assertEquals(l, l.clone());
  }

  @Test
  void notEquals() {
    assertNotEquals(new Location("test.he", 10, 2, 6), new Location("test_file.he", 10, 2, 6));
  }

  @Test
  void locationToString() {
    assertEquals(new Location("test.he", 10, 2, 5).toString(), "test.he:2:5");
  }
}
