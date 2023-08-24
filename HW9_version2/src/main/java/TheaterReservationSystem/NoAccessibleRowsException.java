package TheaterReservationSystem;

/**
 * NoAccessibleRosException is an Exception class that will show message when counter situation that
 * there is no accessible rows in the theater.
 */
public class NoAccessibleRowsException extends Exception {

  /**
   * Constructs a new NoAccessibleRowsException object
   * @param message message will be showed when situation happens
   */
  public NoAccessibleRowsException(String message) {
    super(message);
  }
}
