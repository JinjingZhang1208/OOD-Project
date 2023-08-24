package TheaterReservationSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SeatTest {
  private Seat testSeat;

  @BeforeEach
  void setUp() {
    testSeat = new Seat("B", null);
  }

  @Test
  void getSeatName() {
    assertEquals("B", testSeat.getSeatName());
  }

  @Test
  void getReservedFor() {
    assertEquals(null, testSeat.getReservedFor());
  }

  @Test
  void reserve() {
    testSeat.reserve("Jinjing");
    assertEquals("Jinjing", testSeat.getReservedFor());
  }

  @Test
  void notReserved() {
    testSeat.reserve("Jinjing");
    testSeat.notReserved();
    assertEquals(null, testSeat.getReservedFor());
  }

  @Test
  void testEquals() {
    Seat testSeat2 = new Seat("C", "Anna");
    Seat testSeat3 = new Seat("B", "Anna");
    Seat testSeat4 = new Seat("B", null);

    assertEquals(testSeat, testSeat4);
    assertEquals(testSeat, testSeat);

    assertNotEquals(testSeat, testSeat2);
    assertNotEquals(testSeat, testSeat3);
  }

  @Test
  void testHashCode() {
    Seat testSeat2 = new Seat("C", "Anna");
    Seat testSeat3 = new Seat("B", "Anna");
    Seat testSeat4 = new Seat("B", null);

    assertEquals(testSeat.hashCode(), testSeat4.hashCode());
    assertNotEquals(testSeat.hashCode(), testSeat2.hashCode());
    assertNotEquals(testSeat.hashCode(), testSeat3.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Seat{" +
        "name='B', reservedFor='null'}";
    assertEquals(expectedString, testSeat.toString());
  }
}