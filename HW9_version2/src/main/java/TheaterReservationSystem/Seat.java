package TheaterReservationSystem;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Seat is a class that contains information of Theater's seats.
 */
public class Seat {
  private String seatName;
  private String reservedFor;
  /**
   * the seat name length would be 1 since it is capital letter from A-Z.
   */
  private static final int SEAT_NAME_LENGTH = 1;

  /**
   * Constructs a new Seat object by given seat name and a reserved-for value
   * @param seatName seat name, a string value representing a capital letter from A to Z.
   * @param reservedFor value representing the name of the person for whom it has been reserved, or null if the seat has not been reserved.
   * @throws IllegalArgumentException if seat name's length is not 1 or is not capital letter from A-Z
   */
  public Seat(String seatName, String reservedFor) throws IllegalArgumentException{
    String regex = "^[A-Z]$";
    boolean isNameCapital = Pattern.matches(regex, seatName);
    if (!isNameCapital || seatName.length() != SEAT_NAME_LENGTH ) {
      throw new IllegalArgumentException("Name should be a capital letter from A-Z");
    }
    this.seatName = seatName;
    this.reservedFor = reservedFor;
  }

  /**
   * Getter for seat name
   * @return seat name as String
   */
  public String getSeatName() {
    return seatName;
  }

  /**
   * Getter for reserved for value
   * @return reserved for as String
   */
  public String getReservedFor() {
    return reservedFor;
  }

  /**
   * method to reserve a seat
   * @param name name as String
   */
  public void reserve(String name) {
    reservedFor = name;
  }

  /**
   * status that when a seat is not reserved, set it to null.
   */
  public void notReserved() {
    reservedFor = null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Seat seat = (Seat) o;
    return Objects.equals(seatName, seat.seatName) && Objects.equals(reservedFor, seat.reservedFor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seatName, reservedFor);
  }

  @Override
  public String toString() {
    return "Seat{" +
        "name='" + seatName + '\'' +
        ", reservedFor='" + reservedFor + '\'' +
        '}';
  }
}


