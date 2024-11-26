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
    }


    public static void main(String args[]){
        
        Stadium stateFarmStadium = new Stadium(500, 1000, 2000);

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

            if(optionChosen > 5 || optionChosen < 1){
                System.out.println("Option must be within 1 and 5");

                }
            }
        System.out.println("-------------------------------------------------------------");
        System.out.println("Thank You! ");
        scanner.close(); 

    }
}
