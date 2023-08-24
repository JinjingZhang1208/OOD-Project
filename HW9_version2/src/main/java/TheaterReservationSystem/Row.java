package TheaterReservationSystem;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Row is a class that extends the Seat class. It contains information about rows in a theater.
 * A row is a list of seats.
 */
public class Row extends ArrayList<Seat> {

  /**
   * row number, as int
   */
  private int rowNumber;
  /**
   * boolean to check if it is wheelchair accessible.
   */
  private boolean wheelchairAccessible;
  /**
   * number to count how many of seats in a row, as int
   */
  private int numOfSeatInARow;
  /**
   * Minimum row is 1, (1 is closest to the screen, etc)
   */
  private static final int MINIMUM_ROW = 1;
  /**
   * Adjust the seat position
   */
  private static final int ADJUSTING_SCALE_FOR_SEAT = 1;

  /**
   * Constructs a new Row object using given row number, number of seat in a orw and whether if it is wheelchair accessible.
   * @param rowNumber number to indicate the row's position, starting from 1.
   * @param numOfSeatInARow number of seat in a row, as int.
   * @param wheelchairAccessible check whether it is wheelchair accessible.
   */
  public Row(int rowNumber, int numOfSeatInARow, boolean wheelchairAccessible) {
    this.rowNumber = rowNumber;
    this.wheelchairAccessible = wheelchairAccessible;
    this.numOfSeatInARow = numOfSeatInARow;

    for (int i = MINIMUM_ROW; i <= numOfSeatInARow; i++) {
      this.add(new Seat(String.valueOf((char) ('A' + i - ADJUSTING_SCALE_FOR_SEAT)), null));
    }
  }

  /**
   * Getter for row number
   * @return row number as int
   */
  public int getRowNumber() {
    return rowNumber;
  }

  /**
   * Getter for number of seat in a row
   * @return number of seat in a row as int
   */
  public int getNumOfSeatInARow() {
    return numOfSeatInARow;
  }

  /**
   * Getter for wheelchair accessible
   * @return ture if it is wheelchair accessible, otherwise false.
   */
  public boolean isWheelchairAccessible() {
    return wheelchairAccessible;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Row seats = (Row) o;
    return rowNumber == seats.rowNumber && wheelchairAccessible == seats.wheelchairAccessible
        && numOfSeatInARow == seats.numOfSeatInARow;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), rowNumber, wheelchairAccessible, numOfSeatInARow);
  }

  /**
   * represent the row Whereas seats in rows that are not accessible are represented by “_”, seats in rows that are
   * wheelchair accessible should be represented by “=”.
   * @return the representation of the Row object.
   */
  @Override
  public String toString() {
    StringBuilder rowString = new StringBuilder();

    for (Seat seat : this) {
      if (seat.getReservedFor() != null) {
        rowString.append("X");
      } else if (wheelchairAccessible) {
        rowString.append("=");
      } else {
        rowString.append("_");
      }
      rowString.append(" ");
    }
    return rowString.toString();
  }
}

