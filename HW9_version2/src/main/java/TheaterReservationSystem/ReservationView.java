package TheaterReservationSystem;

import java.util.Objects;
import java.util.Scanner;

/**
 * Represents the user interface for making reservations in a theater, design as View in MVC Architect
 * Users can interact with this interface to reserve seats, display the current available seating
 * in the theater, or shut down the system.
 */
public class ReservationView {

    private static final String DELIMITER = "\\s+";
    private static final String COMMAND_RESERVE = "reserve";
    private static final String COMMAND_SHOW = "show";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_HELP = "help";
    private  Theater theater;
    private  ReservationService reservationService;
    private static final int COMMAND_POSITION_SEAT = 1;
    private static final int COMMAND_RESERVE_MINIMAL_LENGTH = 2;

    /**
     * Constructs reservation view a specific theater and its reservation service.
     * @param theater the theater associated with this view.
     * @param reservationService the service handling reservations for the theater
     */
    public ReservationView(Theater theater,ReservationService reservationService) {
        this.theater = theater;
        this.reservationService = reservationService;
    }

    /**
     * Getters to get the theater associated with this view.
     * @return the theater user specified
     */
    public Theater getTheater() {
        return theater;
    }

    /**
     * Getter to get the reservation service associated with this view.
     * @return the reservation service user specified
     */
    public ReservationService getReservationService() {
        return reservationService;
    }

    /**
     * Method to Start the interactive user interface for making reservations.
     */
    public void startUserInteraction() {
        Scanner inputScanner = new Scanner(System.in);
        boolean runSystem = true;

        while (runSystem) {
            System.out.println("What would you like to do?");
            String userAnswer = inputScanner.nextLine().trim().toLowerCase();

            if (userAnswer.contains(COMMAND_RESERVE)) {
                runReserveCommand(inputScanner, userAnswer);
            } else if (userAnswer.contains(COMMAND_SHOW)) {
                reservationService.displaySeatMap();
            } else if (userAnswer.contains(COMMAND_DONE)) {
                runSystem = false;
            } else if (userAnswer.contains(COMMAND_HELP)) {
                printHelpMessage();
            } else {
                System.out.println("Please enter a valid command. To get command guidance, "
                        + "please enter 'help' in console");
            }
        }

        System.out.println("Thank you! Have a nice day!");
    }

    /**
     * Processes the "reserve" command entered by the user.
     *
     * @param inputScanner the scanner for user input
     * @param command the command string entered by the user
     */
    public void runReserveCommand(Scanner inputScanner, String command) {
        String[] split = command.split(DELIMITER);
        if (split.length < COMMAND_RESERVE_MINIMAL_LENGTH) {
            System.out.println("Please enter a valid seat number to reserve");
            return;
        }

        if (reservationService.checkValidSeatNumber(split[COMMAND_POSITION_SEAT])) {
            int reservedSeatNumber = Integer.parseInt(split[COMMAND_POSITION_SEAT]);
            // Get user's name
            System.out.println("What’s your name?");
            String name = inputScanner.nextLine().trim();

            // Check if the user needs wheelchair accessibility
            boolean validWheelchairInput = false;
            boolean wheelchairAccessible = false;
            while (!validWheelchairInput) {
                System.out.println("Do you need wheelchair accessible seats? (yes/no)");
                String wheelchairResponse = inputScanner.nextLine().trim().toLowerCase();
                if ("yes".equals(wheelchairResponse) || "no".equals(wheelchairResponse)) {
                    validWheelchairInput = true;
                    wheelchairAccessible = "yes".equals(wheelchairResponse);
                } else {
                    System.out.println("Invalid input. Please answer with 'yes' or 'no'.");
                }
            }

            // Reserve seats with the given parameters
            reservationService.reserveSeats(theater, reservedSeatNumber, name, wheelchairAccessible);

        } else {
            System.out.println("Please enter a valid seat number to reserve");
        }
    }

    /**
     * Helper method to print a help message guiding the user on how to use the system.
     */
    private void printHelpMessage() {
        int seatsInARow = theater.getRows().get(0).getNumOfSeatInARow();
        System.out.println( "Theater "+ theater.getName() +" has total " + theater.getTotalRows() + " rows with " + seatsInARow
                + " seats for each row. The row number " + theater.getWheelchairRows() + " is(are) row(s) for wheelchairs");
        System.out.println(" ");
        System.out.println("Command Guidance:");
        System.out.println(COMMAND_RESERVE + ": enter '" + COMMAND_RESERVE + " <numberOfSeats>' to reserve seat(s).");
        System.out.println(COMMAND_SHOW + ": display the current available seating in the theater, "+
                "If you want to check the actual location of the seat, "
                + "please enter '" + COMMAND_SHOW + "' after receiving the confirmation message.");
        System.out.println(COMMAND_DONE + ": shut down the system.");
    }

    // ===== Below are equals(),hashcode() and toString() =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationView that = (ReservationView) o;
        return Objects.equals(theater, that.theater) && Objects.equals(reservationService, that.reservationService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theater, reservationService);
    }

    @Override
    public String toString() {
        return "ReservationView{" +
                "theater=" + theater +
                ", reservationService=" + reservationService +
                '}';
    }
}
