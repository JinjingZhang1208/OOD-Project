package TheaterReservationSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RowTest {
  private Row testRow;

  @BeforeEach
  void setUp() {
    testRow = new Row(2, 10, true);
  }

  @Test
  void getRowNumber() {
    assertEquals(2, testRow.getRowNumber());
  }

  @Test
  void getNumOfSeatInARow() {
    assertEquals(10, testRow.getNumOfSeatInARow());
  }

  @Test
  void isWheelchairAccessible() {
    assertTrue(testRow.isWheelchairAccessible());
  }

  @Test
  void testEqual() {
    Row testRow2 = new Row(2, 10, true);

    assertEquals(testRow, testRow2);
    assertEquals(testRow, testRow);

    Row testRow3 = new Row(3, 10, true);
    Row testRow4 = new Row(2, 9, true);
    Row testRow5 = new Row(2, 10, false);

    assertNotEquals(testRow, testRow3);
    assertNotEquals(testRow, testRow4);
    assertNotEquals(testRow, testRow5);
  }

  @Test
  void testHashCode() {
    Row testRow2 = new Row(2, 10, true);

    assertEquals(testRow.hashCode(), testRow2.hashCode());
  }

  @Test
  void testToString() {
    testRow.get(3).reserve("Anna");
    String expectedRowString = "= = = X = = = = = = ";
    assertEquals(expectedRowString, testRow.toString());
  }
}