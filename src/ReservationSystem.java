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
        System.out.println("5. Nothing Else");
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
        
        while(optionChosen != 5){
            commandPrompt();
            System.out.println();
            System.out.print("Please enter your option here: ");
            optionChosen = scanner.nextInt();

            // if(optionChosen > 5 || optionChosen < 1){
            //     System.out.println("Option must be within 1 and 5");

            // }

            switch(optionChosen){

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
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Thank You! ");
                    // scanner.close(); 
                    break; 

                default:
                    System.out.println("Option must be within 1 and 5");
                    break; 



            }
            
        }
        





    }

    public static void main(String args[]){
        IntegratedSystem(); 
    }
}
