package TheaterReservationSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The ReservationService class provides methods to reserve seats in a theater based on user preferences.
 * It considers wheelchair accessibility and proximity to the center row for seat selection.
 */
public class ReservationService {
    private Theater theater;
    /**
     * cut by the middle point, used to find the row closest to the center
     */
    private static final int CUT_BY_MIDDLE = 2;

    /**
     * Constructs a new ReservationService object with certain theater
     * @param theater theater, as a Theater object
     */
    public ReservationService(Theater theater) {
        this.theater = theater;
    }

    /**
     * Reserves the specified number of seats in the given theater for a party.
     *
     * @param theater             The theater in which seats will be reserved.
     * @param numSeats            The number of seats to be reserved.
     * @param name                The name of the party reserving seats.
     * @param wheelchairAccessible Whether wheelchair accessible seats are required.
     */
    public void reserveSeats(Theater theater, int numSeats, String name, boolean wheelchairAccessible) {
        if (numSeats <= 0) {
            System.out.println("Invalid number of seats.");
            return;
        }

        List<Row> candidateRows;
        if (wheelchairAccessible) {
            candidateRows = theater.getRows().stream()
                    .filter(Row::isWheelchairAccessible)
                    .collect(Collectors.toList());
        } else {
            candidateRows = theater.getRows().stream()
                    .filter(row -> !row.isWheelchairAccessible())
                    .collect(Collectors.toList());
        }

        Row bestRow = findBestRow(candidateRows);
        if (bestRow == null) {
            System.out.println("Sorry, we don’t have that many seats together for you.");
            return;
        }

        List<Seat> reservedSeats = new ArrayList<>();
        int numReserved = 0;
        for (Seat seat : bestRow) {
            if (numReserved == numSeats) {
                break;
            }
            if (seat.getReservedFor() == null) {
                seat.reserve(name);
                reservedSeats.add(seat);
                numReserved++;
            }
        }

        if (numReserved == numSeats) {
            System.out.println("I’ve reserved " + numSeats + " seats for you at the Roxy in row " + bestRow.getRowNumber() + ", " + name + ".");
        } else {
            System.out.println("Sorry, we don’t have that many seats together for you.");
            reservedSeats.forEach(Seat::notReserved);
        }
    }

    /**
     * method to check if the seat number is valid
     * @param usersInputForSeat user's input of seat number
     * @return true if it is valid, otherwise false
     */
    public boolean checkValidSeatNumber(String usersInputForSeat) {
        try {
            int seat = Integer.parseInt(usersInputForSeat);
            return seat > 0 && seat <= theater.getTotalRows();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * show the seat map
     */
    public void displaySeatMap() {
        System.out.println(theater);
    }

    /**
     * print the message to show that a reservation is made successfully.
     */
    public void reservationConfirmation() {
        System.out.println("Reservation confirmed!");
    }

    private Row findBestRow(List<Row> candidateRows) {
        // Simple approach: Find the row closest to the center
        int centerRow = candidateRows.size() / CUT_BY_MIDDLE;
        return candidateRows.stream()
                .min(Comparator.comparingInt(row -> Math.abs(row.getRowNumber() - centerRow)))
                .orElse(null);
    }
}