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



        Client Edward = new Client("Edward", "edward.carde@upr.edu", "787-612-7168");
        System.out.println(stateFarmStadium.makeReservation(Edward, "Field Level",
        Arrays.asList(344,345,346,347,348,349))); 
        System.out.println("Edward Reservations: " + Edward.getReservations(stateFarmStadium)); 
        System.out.println(stateFarmStadium.cancelReservation(Edward, Arrays.asList(344,345))); 
        System.out.println("Edward Reservations: " + Edward.getReservations(stateFarmStadium));
        


        //initializing command prompt variables
        int optionChosen = 0;
        Scanner scanner = new Scanner(System.in);

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
                    System.out.println("Enter your name: ");

                    scanner.nextLine();
                    String name = scanner.nextLine();
                
                    System.out.println("Enter your email: ");
                    String email = scanner.nextLine();

                    System.out.println("Enter your phone number: ");
                    String phone = scanner.nextLine();
                    
                    Client customer = new Client(name, email, phone);

                    System.out.println("Enter the section you want to reserve seats in (Field Level, Main Level, Grandstand Level): ");
                    
                    String section = scanner.nextLine();

                    System.out.println("Enter the chair numbers to reserve(comma-separated): ");
                    List<Integer> chairNumbers = new ArrayList<>();
                    
                    for(String chairNumber : scanner.nextLine().split(",")){
                        chairNumbers.add(Integer.parseInt(chairNumber.trim())); //Trim used to remove spaces, if there are any
                    }

                    String result = stateFarmStadium.makeReservation(customer, section, chairNumbers);
                    System.out.println(result);
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
                
                    System.out.println("Please enter the name under the reservation");
                    scanner.nextLine();
                    String name2 = scanner.nextLine();
            
                    System.out.println("Enter your email: ");
                    String email2 = scanner.nextLine();

                    System.out.println("Enter your phone number: ");
                    String phone2 = scanner.nextLine();
                
                    Client customer2 = new Client(name2, email2, phone2);

                    System.out.println("Enter the chair numbers to cancel reservation(comma-separated): ");
                    List<Integer> chairNumbers2 = new ArrayList<>();
                    
                    for(String chairNumber : scanner.nextLine().split(",")){
                        chairNumbers2.add(Integer.parseInt(chairNumber.trim())); //Trim used to remove spaces, if there are any
                    }

                    stateFarmStadium.cancelReservation(customer2, chairNumbers2);
                    stateFarmStadium.updateAvailableSeats();
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
