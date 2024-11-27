import java.util.*; 

public class ReservationSystem {


    public static void commandPrompt() {
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
        Stadium stateFarmStadium = new Stadium(500, 1000, 2000);



        Client Edward = new Client("Edward", "edward.carde@upr.edu", "787-612-7168");
        System.out.println(stateFarmStadium.makeReservation(Edward, "Field Level",
        Arrays.asList(344,345,346,347,348,349))); 
        System.out.println("Edward Reservations: " + Edward.getReservations(stateFarmStadium)); 
        System.out.println(stateFarmStadium.cancelReservation(Edward, Arrays.asList(344,345))); 
        System.out.println("Edward Reservations: " + Edward.getReservations(stateFarmStadium));
        



        int optionChosen = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------");
        System.out.println("Available seats:" );
        System.out.println();
        stateFarmStadium.showAvailableSeats();
        
        while(optionChosen != 6){
            commandPrompt();
            System.out.println();
            stateFarmStadium.updateAvailableSeats();
            System.out.print("Please enter your option here: ");
            optionChosen = scanner.nextInt();

            // if(optionChosen > 5 || optionChosen < 1){
            //     System.out.println("Option must be within 1 and 5");

            // }

            switch(optionChosen){
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
                    stateFarmStadium.showReservations(); 
                    break; 

                case 3:
                    stateFarmStadium.showAvailableSeats(); 
                    break; 

                case 4:
                    stateFarmStadium.showWaitlist(); 
                    break; 
                
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
