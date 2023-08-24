### Theater reservation system

###### Credits: Jinjing Zhang | Jie Chen | Xiaoti Hu


## Brief Project Introduction

Welcome to our Theater Reservation System project, where we've developed an automated solution for reserving the best available seats in a movie theater.
In this project, we've utilized a range of Java skills, including regular expressions for input parsing and iterators for efficient data traversal.

##Environment:## Java 17 with gradle


## Design Patterns Employed

1. **MVC Pattern (Structural):** The Model-View-Controller (MVC) pattern is adopted to clearly separate the application's concerns, ensuring better maintainability and scalability.
   - **Model**: Represented by classes `Seat`, `Row`, and `Theater`. They store data and define the operations that can be performed on this data.
   - **View**: This is encapsulated within the `ReservationView` class, which manages user interaction, accepting input, and rendering outputs based on model changes.
   - **Controller**: `ReservationService` functions as the controller, mediating between the View's input requests and the operations performed on the Model.
2. **Builder Pattern (Creational):** This design pattern is applied in `Theater` objects. Given the multiple steps involved in setting up a theater (from initializing rows to designating wheelchair-accessible areas), the Builder pattern ensures a controlled and stepwise construction. The method `createTheater` in the `Theater` class is indicative of this pattern.
3. **Factory Method Pattern (Creational):** The `ReservationService` class subtly introduces this pattern when creating `Seat` and `Row` instances as needed for the theater. By doing so, the instantiation logic is abstracted, allowing for flexibility in future object creation without altering the main code.
4. **Command Pattern (Behavioral):** This pattern is evident in the way user commands (like "reserve", "show", "done") are handled in the `ReservationView` class. Each command represents a request which is encapsulated as an object, thereby allowing users to parameterize clients with different requests, queue requests, and support undoable operations.
5. **Singleton Pattern (Creational):** Although not explicitly present in the code, the `ReservationSystem` could be turned into a singleton. The Singleton pattern ensures a class has only one instance and provides a point of access to this instance. In our context, it ensures that only one instance of the reservation system is active, maintaining consistency and preventing potential conflicts.





## Design Approach 

**Objective**: The primary aim of the system is to enable users to reserve seats in a theater, visualize seat occupancy, and understand the configuration of the theater.

1. **Core Data Components**:
   - **Seat**: Represents individual seating positions in the theater, capable of storing the occupancy status and specific details about the seat.
   - **Row**: Comprises multiple seats and can represent different types of rows (e.g., standard, wheelchair accessible).
   - **Theater**: Holds a collection of rows and is responsible for the overall seat arrangement.
2. **User Interaction and Control**:
   - **ReservationView**: Acts as the interface to the user, abstracting and simplifying the display and interaction aspects. By providing methods for various functionalities, it ensures that the user doesn't directly interact with the underlying complexities.
   - **ReservationSystem**: The main driver of the application. It initializes the theater, manages the user's commands, and leverages `ReservationService` to process these commands.
3. **Service Layer**:
   - **ReservationService**: Acts as the controller. It takes user requests from `ReservationSystem`, interfaces with the theater model to reserve seats, and returns the necessary feedback. This layer decouples the operations on the data (model) from the user interactions.
4. **Interactions & Flow**:
   - The `ReservationSystem` initializes the theater and starts the interaction loop.
   - User commands are read and processed. Depending on the command, different parts of the system are invoked.
   - For seat reservations, the `ReservationService` is used to check seat availability, reserve seats, and provide feedback.
   - `ReservationView` is utilized to present the theater's occupancy status and provide other informational displays.

By adopting this structure, each class assumes clear responsibilities, enabling easy modifications or extensions with minimal impact on other parts. The interaction flow is centrally orchestrated by ReservationSystem, ensuring coherent user command management.