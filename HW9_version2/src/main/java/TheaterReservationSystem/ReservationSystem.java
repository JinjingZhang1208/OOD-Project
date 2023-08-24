package TheaterReservationSystem;

import java.util.List;
import java.util.Scanner;

/**
 * ReservationSystem  takes input from user, run the reservation service as needed for a theater.
 */
public class ReservationSystem {

    private static final String DELIMITER = "\\s+";

    /**
     * This class demonstrates the usage of the Theater, ReservationService, and ReservationView classes
     * to facilitate user interaction for reserving seats in a theater.
     *
     * @param args The command-line arguments passed to the program
     * @throws IllegalArgumentException If invalid arguments are provided during theater initialization.
     * @throws NoAccessibleRowsException If the specified wheelchair rows are not accessible in the theater.
     */
    public static void main(String[] args) throws IllegalArgumentException, NoAccessibleRowsException {
        // Initialize the Model new Theater(Name, NumberOfRow, NumberOfSeatInEachRow, NumberOfWheelChairRows)
        // Could be other kind of constructor.

        Theater theater = new Theater("Roxy", 15, 10, List.of(6,10));


        ReservationService reservationService = new ReservationService(theater);
        // Initialize the View ReservationService
        ReservationView reservationView = new ReservationView(theater, reservationService);

        reservationView.startUserInteraction();


    }
}