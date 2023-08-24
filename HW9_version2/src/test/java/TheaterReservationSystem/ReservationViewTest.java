package TheaterReservationSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ReservationViewTest {

    private Theater testTheater;
    private ReservationService testService;
    private ReservationView testView;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws NoAccessibleRowsException {
        testTheater = new Theater("Roxy", 15, 10, List.of(6, 7));
        testService = new ReservationService(testTheater);
        testView = new ReservationView(testTheater, testService);
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Test
    void getTheater() {
        assertEquals(testView.getTheater(), testTheater);
    }

    @Test
    void getReservationService() {
        assertEquals(testView.getReservationService(), testService);
    }

    @Test
    void startUserInteraction_ValidReserve() {
        String input = "reserve 5\nAmy\nyes\ndone\n"; // Simulating name and wheelchair need
        //new ByteArrayInputStream(input.getBytes()) creates a new ByteArrayInputStream that
        // will read bytes from the given byte array.
        // When something in your code then tries to read from this "stream",
        // it will get the data from the byte array instead of the usual source
        System.setIn(new ByteArrayInputStream(input.getBytes())); //input.getBytes()- converts the input string into a byte array.
        //Scanner scanner = new Scanner(System.in);

        // Assume reservationService.checkValidSeatNumber returns true
        //Instead of printing to the console, the output will be stored in the outContent object.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the method under test
        testView.startUserInteraction();

        //These assertions are checking whether the mocked input led the program to print out certain expected messages.
        assertTrue(outContent.toString().contains("What’s your name?"));
        assertTrue(outContent.toString().contains("Do you need wheelchair accessible seats?"));
    }

    @Test
    void startUserInteraction_InvalidReserve() {
        String simulatedUserInput = "reserve -2\ndone\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        testView.startUserInteraction();

        String expectedOutput = "Please enter a valid seat number to reserve" + System.lineSeparator();
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void startUserInteraction_ValidHelp(){

        String simulatedUserInput = "help\ndone\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        testView.startUserInteraction();


        assertTrue(outContent.toString().contains("Theater"));
        assertTrue(outContent.toString().contains("Thank you! Have a nice day!"));
    }

    @Test
    void startUserInteraction_InvalidCommand(){

        String simulatedUserInput = "xx\ndone\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        testView.startUserInteraction();

        assertTrue(outContent.toString().contains("Please enter a valid command."));

    }

    @Test
    void startUserInteraction_ValidShow(){
        String simulatedUserInput = "show\ndone\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the method under test
        testView.startUserInteraction();
        assertTrue(outContent.toString().contains(" 3 _ _ _ _ _ _ _ _ _ _ \n"));
        assertTrue(outContent.toString().contains(" 6 = = = = = = = = = = \n"));
    }

    @Test
    void runReserveCommand_InvalidNumberOfArguments() {
        String simulatedUserInput = "reserve"; // This won't split into 2
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        testView.runReserveCommand(scanner, simulatedUserInput);

        String expectedOutput = "Please enter a valid seat number to reserve" + System.lineSeparator();
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void testEquals() throws NoAccessibleRowsException {
        ReservationView newView = new ReservationView(testTheater, testService);
        assertEquals(testView, testView);
        assertEquals(testView, newView);

        Theater anotherTheater = new Theater("Max", 10, 5, List.of(3, 4));
        ReservationService newService = new ReservationService(anotherTheater);
        ReservationView view2 = new ReservationView(anotherTheater, newService);
        assertNotEquals(testView, view2);

        assertNotEquals(testView,"String");
        assertNotEquals(testView, null);
    }

    @Test
    void testHashCode() throws NoAccessibleRowsException {
        ReservationView newView = new ReservationView(testTheater, testService);
        assertEquals(testView.hashCode(), testView.hashCode());

        Theater newTheater = new Theater("Max", 10, 5, List.of(3, 4));
        ReservationService newService = new ReservationService(newTheater);
        ReservationView newView2 = new ReservationView(newTheater, newService);
        assertNotEquals(testView.hashCode(), newView2.hashCode());

    }

    @Test
    void testToString() {
        String expectedString = "ReservationView{theater=" + testTheater.toString() +
                ", reservationService=" + testService.toString() + "}";
        assertEquals(expectedString, testView.toString());
    }
}