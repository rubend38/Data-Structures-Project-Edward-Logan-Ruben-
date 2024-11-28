import java.util.*; 

public class ReservationSystem {


    public static void commandPrompt() {

        //Gives user available options related to reservations.
        System.out.println("-------------------------------------------------------------");
        System.out.println("Available Options:    ");
        System.out.println();

        System.out.println("1. Make reservation.");
        System.out.println("2. See existing reservations.");
        System.out.println("3. See available seating.");
        System.out.println("4. See wait lists.");
        System.out.println("5 Cancel reservation");
        System.out.println("6. Exit");
        System.out.println("-------------------------------------------------------------");

    }

    public static void IntegratedSystem(){

        //Creates the stadium to be manipulated
        Stadium stateFarmStadium = new Stadium(500, 1000, 2000);

        //initializing command prompt variables
        int optionChosen = 0;
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Client> registeredClients = new HashMap<>(); 

        System.out.println("-------------------------------------------------------------");
        System.out.println("Available seats:" );
        System.out.println();
        stateFarmStadium.showAvailableSeats();//shows the user the available seating areas to choose from
        
        //continues to run the command prompt loop until the option chosen is 6.
        while(optionChosen != 6){
            commandPrompt();
            System.out.println();
            stateFarmStadium.updateAvailableSeats();//updates any changes made to the seating reservations
            System.out.print("Please enter your option here: ");
            //Saves user input into variable for option selection
            optionChosen = scanner.nextInt();

            switch(optionChosen){

                //if 1 is entered as the desired option then information is asked to the user and a list of chairs is reserved.
                case 1:
                    System.out.println("-------------------------------------------------------------");

                    while (true) {
                        // Solicitar el nombre del cliente
                        System.out.println("Enter your name (or type 'exit' to return to the main menu): ");

                        if (scanner.hasNextLine()) {
                            scanner.nextLine(); // Consumir el salto de línea residual (si lo hay)
                        }

                        String name = scanner.nextLine().trim(); // Leer el nombre del cliente
                        if (name.equalsIgnoreCase("exit")) {
                            break; // Salir al menú principal
                        }

                        Client customer;

                        // Verificar si el cliente ya existe
                        if (registeredClients.containsKey(name)) {
                            customer = registeredClients.get(name);
                            System.out.println("Welcome back, " + customer.getName() + "!");
                        } else {
                            // Nuevo cliente: pedir email y teléfono
                            System.out.println("New customer. Please provide your email: ");
                            String email = scanner.nextLine().trim();

                            System.out.println("Please provide your phone number: ");
                            String phone = scanner.nextLine().trim();

                            customer = new Client(name, email, phone); // Crear el cliente
                            registeredClients.put(name, customer); // Registrar al cliente
                            System.out.println("Welcome, " + customer.getName() + "!");
                        }

                        // Elegir sección
                        System.out.println("Choose a section to reserve seats:");
                        System.out.println("1. Field Level\n2. Main Level\n3. Grandstand Level");

                        // Leer la sección seleccionada
                        int sectionOption = -1;
                        try {
                            sectionOption = scanner.nextInt();
                            scanner.nextLine(); // Consumir el salto de línea después del entero
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number between 1 and 3.");
                            scanner.nextLine(); // Limpiar el buffer
                            continue; // Volver al inicio del loop
                        }

                        String section = "";
                        switch (sectionOption) {
                            case 1:
                                section = "Field Level";
                                break;
                            case 2:
                                section = "Main Level";
                                break;
                            case 3:
                                section = "Grandstand Level";
                                break;
                            default:
                                System.out.println("Invalid section. Please try again.");
                                continue; // Volver al inicio del loop
                        }

                        // Leer los números de sillas
                        System.out.println("Enter the chair numbers to reserve (comma-separated): ");
                        List<Integer> chairNumbers = new ArrayList<>();
                        for (String chairNumber : scanner.nextLine().split(",")) {
                            chairNumbers.add(Integer.parseInt(chairNumber.trim()));
                        }

                        // Hacer la reservación
                        String result = stateFarmStadium.makeReservation(customer, section, chairNumbers);
                        System.out.println(result);

                        // Preguntar si desea hacer otra reservación
                        System.out.println("Do you want to make another reservation? (yes/no): ");
                        String continueReservation = scanner.nextLine().trim();
                        if (continueReservation.equalsIgnoreCase("no")) {
                            break; // Salir del loop interno
                        }
                    }
                    break;
                case 2:
                //if 2 is inputed by the user then the list of made reservations is showed.
                    stateFarmStadium.showReservations(); 
                    break; 

                //shows available seating for new reservations. 
                case 3:
                    stateFarmStadium.showAvailableSeats(); 
                    break; 
                //Shows the people on waitlist for new section seats.
                case 4:
                    stateFarmStadium.showWaitlist(); 
                    break; 
                
                //if option 5 is chosen then the system will ask the user for information and cancel any reservation under that information.
                case 5:
                    System.out.println("Please enter the name under the reservation:");
                    scanner.nextLine(); // Consumir el salto de línea
                    String cancelName = scanner.nextLine();

                    // Buscar cliente por nombre
                    Client cancelClient = stateFarmStadium.findClientByName(cancelName);
                    if (cancelClient == null) {
                        System.out.println("No reservations found under the name: " + cancelName);
                        break;
                    }

                    System.out.println("Reservations under " + cancelName + ": "
                            + stateFarmStadium.getClientReservations(cancelClient));
                    System.out.println("Enter the chair numbers to cancel reservation (comma-separated): ");
                    List<Integer> chairNumbers2 = new ArrayList<>();
                    for (String chairNumber : scanner.nextLine().split(",")) {
                        chairNumbers2.add(Integer.parseInt(chairNumber.trim()));
                    }

                    String cancelResult = stateFarmStadium.cancelReservation(cancelClient, chairNumbers2);
                    System.out.println(cancelResult);
                    break;
                    //if option 6 is chosen then the system's command prompt will close and the program will stop running
                case 6:
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Thank You! ");
                    // scanner.close(); 
                    break; 

                default:
                    System.out.println("Option must be within 1 and 6.");
                    break; 


            }
            
        }
        





    }

    public static void main(String args[]){
        IntegratedSystem(); 
    }
}
