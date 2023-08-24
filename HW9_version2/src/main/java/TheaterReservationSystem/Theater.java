package TheaterReservationSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Theater is a class that contains information about theater.
 */
public class Theater {

  private String name;
  private List<Row> rows;
  private List<Integer> wheelchairRows;
  private int totalRows;
  /**
   * the minimum row number is 1.
   */
  private static final int MINIMUM_ROW_AND_SEAT_NUMBER = 1;
  private static final int MAX_NUM_OF_SEAT = 26;


  /**
   * Constructs a new Theater object by given name, total number of rows, list of  wheelchair accessible rows
   * @param name name as String
   * @param totalRows total number of rows in the theater, as int.
   * @param numOfSeatInARow number of seat for each row,
   * @param wheelchairRows list of  wheelchair accessible rows,
   * @throws NoAccessibleRowsException if there is no wheelchair accessible rows at all in a theater
   */
  public Theater(String name, int totalRows, int numOfSeatInARow, List<Integer> wheelchairRows)
      throws NoAccessibleRowsException, IllegalArgumentException {
    // Validate inputs
    validateTheaterName(name);
//    checkHasAccessibleRow();
    validateWheelchairRows(wheelchairRows);
    validateSeatsInARow(numOfSeatInARow);

    this.name = name;
    this.totalRows = totalRows;
    this.wheelchairRows = new ArrayList<>(wheelchairRows);
    this.rows = new ArrayList<>();

    // Populating rows
    for (int i = MINIMUM_ROW_AND_SEAT_NUMBER; i <= totalRows; i++) {
      boolean isWheelchairAccessible = wheelchairRows.contains(i);
      this.rows.add(new Row(i, numOfSeatInARow, isWheelchairAccessible));
    }
  }

  private void validateSeatsInARow(int numOfSeatInARow) throws IllegalArgumentException {
    if (numOfSeatInARow < MINIMUM_ROW_AND_SEAT_NUMBER || numOfSeatInARow > MAX_NUM_OF_SEAT) {
      throw new IllegalArgumentException("Invalid number of seats in a row.");
    }
  }

  /**
   * helper function to check if there is accessible row for wheelchair.
   * @throws NoAccessibleRowsException  if there is no wheelchair accessible rows at all in a theater
   */
  private void checkHasAccessibleRow() throws NoAccessibleRowsException{
    if (wheelchairRows.isEmpty()) {
      throw new NoAccessibleRowsException("The theater cannot have no wheelchair rows.");
    }
  }

  /**
   * Getter for total number of rows
   * @return total number of rows as int
   */
  public int getTotalRows() {
    return totalRows;
  }

  /**
   * Getter for name
   * @return name as String
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for rows
   * @return rows as a list
   */
  public List<Row> getRows() {
    return rows;
  }

  /**
   * Getter for wheelchair rows
   * @return wheelchair rows as a list
   */
  public List<Integer> getWheelchairRows() {
    return wheelchairRows;
  }

  /**
   * helper function to check if theater has valid name.
   * @param name name as String
   * @throws IllegalArgumentException if theater's name is missing
   */
  private static void validateTheaterName(String name) throws IllegalArgumentException {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Theater name is missing.");
    }
  }

  /**
   * Helper function to check if there is wheelchair row in the theater
   * @param wheelchairRows wheelchair rows as list
   * @throws NoAccessibleRowsException if there is no wheelchair row in the theater
   */
  private static void validateWheelchairRows(List<Integer> wheelchairRows) throws NoAccessibleRowsException {
    if (wheelchairRows == null || wheelchairRows.isEmpty()) {
      throw new NoAccessibleRowsException("The theater must have at least one accessible row.");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Theater theater = (Theater) o;
    return Objects.equals(name, theater.name) && Objects.equals(rows, theater.rows) && Objects.equals(wheelchairRows, theater.wheelchairRows);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, rows, wheelchairRows);
  }

  @Override
  public String toString() {
    StringBuilder theaterString = new StringBuilder();

    for (Row row : rows) {
      if (row.getRowNumber() < 10) {
        theaterString.append(" ");  // Pad single digit row numbers with space
      }

      theaterString.append(row.getRowNumber()).append(" ");
      theaterString.append(row.toString());
      theaterString.append("\n");
    }

    return theaterString.toString();
  }

}

