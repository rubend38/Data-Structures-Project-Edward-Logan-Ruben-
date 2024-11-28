# Data-Structures-Project-Edward-Logan-Ruben-
# Stadium Reservation System

## Description
The Stadium Reservation System is a Java-based application designed to manage seat reservations for a stadium. It allows users to make, view, and cancel reservations across different seating sections, while also handling waitlists for fully booked sections.

## System Logic
The system comprises four main components:
- **ReservationSystem**: Acts as the main interface for user interaction. It provides a menu-driven interface for making reservations, viewing available seats, and more.
- **Stadium**: Manages the reservations, available seats, and waitlists for different sections: Field Level, Main Level, and Grandstand Level.
- **Chair**: Represents individual seats with properties like availability, price, and section.
- **Client**: Represents a user making reservations, storing their personal information.

### Key Functionalities:
1. **Make Reservations**: Users specify the section and seat numbers. If seats are available, the reservation is confirmed; otherwise, the user is added to the section's waitlist.
2. **Cancel Reservations**: Users can cancel existing reservations. If seats are freed, they are automatically reassigned to the next client in the waitlist.
3. **View Available Seats**: Displays the number of available seats per section and their respective prices.
4. **View Existing Reservations and Waitlists**: Lists all active reservations and users in waitlists.

## Design Approach
### Data Structures:
- **HashSet<Chair>**: Efficiently tracks available chairs.
- **HashMap<Chair, Client>**: Maps reserved chairs to clients.
- **Queue<Client>**: Manages waitlists for each section.
- **Stack<String>**: Tracks operations for undo functionality.
  
### Why These Choices?
- `HashSet` ensures quick lookups for available seats.
- `HashMap` allows efficient reservation retrieval.
- `Queue` maintains the first-come-first-serve order for waitlists.
- `Stack` provides a simple way to undo the last reservation or cancellation action.

## How to Use
1. Run the `ReservationSystem` class to start the application.
2. Choose an option from the menu:
   - **Option 1**: Make a reservation by providing your details, section, and seat numbers.
   - **Option 2**: View all current reservations.
   - **Option 3**: View available seats in each section.
   - **Option 4**: View the waitlist for each section.
   - **Option 5**: Cancel a reservation by specifying your details and seat numbers.
   - **Option 6**: Remakes the reservation if you cancelled it and cancells it if you made it.  
   - **Option 7**: Exit the application.