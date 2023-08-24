package TheaterReservationSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TheaterTest {
  private Theater testTheater;

  @BeforeEach
  void setUp() throws NoAccessibleRowsException {
    List<Integer> wheelchairRows = new ArrayList<>();

    assertThrows(NoAccessibleRowsException.class, () -> {
      new Theater("Sydney Opera", 8, 10, wheelchairRows);
    });

    wheelchairRows.add(2);
    testTheater = new Theater("Sydney Opera", 8, 10, wheelchairRows);
  }

  @Test
  void getTotalRows() {
    assertEquals(8, testTheater.getTotalRows());
  }

  @Test
  void getName() {
    assertEquals("Sydney Opera", testTheater.getName());
  }

  @Test
  void getRows() {
    List<Row> rows = testTheater.getRows();
    assertEquals(testTheater.getTotalRows(), rows.size());
  }

  @Test
  void getWheelchairRows() {
    List<Integer> wheelchairRows = testTheater.getWheelchairRows();
    assertNotNull(wheelchairRows);
    assertEquals(1, wheelchairRows.size());
    assertEquals(2, wheelchairRows.get(0));
  }

//  @Test
//  void checkHasAccessibleRow(){
//    assertThrows(NoAccessibleRowsException.class, () -> {
//      new Theater("Sydney Opera", 8, 10, null);
//    });
//
//
//    List<Integer> wheelchairRows = new ArrayList<>();
//    assertThrows(NoAccessibleRowsException.class, () -> {
//      new Theater("Sydney Opera", 8, 10, wheelchairRows);
//    });
//  }

  @Test
  void testEquals() throws NoAccessibleRowsException {
    List<Integer> wheelchairRows = new ArrayList<>();
    wheelchairRows.add(2);
    Theater testTheater2 = new Theater("Sydney Opera", 8, 10, wheelchairRows);
    assertEquals(testTheater, testTheater2);
    assertEquals(testTheater, testTheater);

    wheelchairRows.add(5);
    Theater testTheater3 = new Theater("Sydney Opera", 8, 10, wheelchairRows);
    assertNotEquals(testTheater, testTheater3);
  }

  @Test
  void testHashCode() throws NoAccessibleRowsException {
    List<Integer> wheelchairRows = new ArrayList<>();
    wheelchairRows.add(2);
    Theater testTheater2 = new Theater("Sydney Opera", 8, 10, wheelchairRows);
    assertEquals(testTheater.hashCode(), testTheater2.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = " 1 _ _ _ _ _ _ _ _ _ _ \n" +
        " 2 = = = = = = = = = = \n" +
        " 3 _ _ _ _ _ _ _ _ _ _ \n" +
        " 4 _ _ _ _ _ _ _ _ _ _ \n" +
        " 5 _ _ _ _ _ _ _ _ _ _ \n" +
        " 6 _ _ _ _ _ _ _ _ _ _ \n" +
        " 7 _ _ _ _ _ _ _ _ _ _ \n" +
        " 8 _ _ _ _ _ _ _ _ _ _ \n";
    assertEquals(expectedString, testTheater.toString());
  }
}