
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;



    public class Stadium{
        private Set<Chair> availableChairs;
        private HashMap<Chair,Client> reservations;
        private Queue<String> fieldWaitlist;
        private Queue<String> mainWaitlist;
        private Queue<String> grandstandWaitlist;
        private LinkedList<String> transactionHistory;
        private Stack<String> undoStack;


        public Stadium(int fieldCapacity, int mainCapacity, int grandstandCapacity){
            availableChairs = new HashSet<>();
            reservations = new HashMap<>();
            fieldWaitlist = new LinkedList<>();
            mainWaitlist = new LinkedList<>();
            grandstandWaitlist = new LinkedList<>();
            transactionHistory = new LinkedList<>();
            undoStack = new Stack<>();


            for (int i = 1; i <= fieldCapacity; i++) {
               availableChairs.add(new Chair(i, "Field Level", 300));
            }
            for (int i = 1; i <= mainCapacity; i++) {
                availableChairs.add(new Chair(i + fieldCapacity, "Main Level", 120));
            }
            for (int i = 1; i <= grandstandCapacity; i++) {
                availableChairs.add(new Chair(i + fieldCapacity + mainCapacity , "Grandstand Level", 45));
            }
            
        }
        public void showAvailableSeats() {
            int fieldLevelCount = 0;
            int mainLevelCount = 0;
            int grandstandLevelCount = 0;
    
            for (Chair chair : availableChairs) {
                if (chair.isavailable()) {
                    switch (chair.getsection()) {
                        case "Field Level":
                            fieldLevelCount++;
                            break;
                        case "Main Level":
                            mainLevelCount++;
                            break;
                        case "Grandstand Level":
                            grandstandLevelCount++;
                            break;
                    }
                }
            }
            System.out.println("Field Level:       "+  fieldLevelCount +" at $300" );
            System.out.println("Main Level:       " + mainLevelCount +" at $120" );
            System.out.println("Grandstand Level: " + grandstandLevelCount  +" at $45");
        }

    public static void commandPrompt(){
        System.out.println("-------------------------------------------------------------");
        System.out.println("Available Options:    ");
        System.out.println();

        System.out.println("1. Make reservation.");
        System.out.println("2. See existing reservations.");
        System.out.println("3. See available seating.");
        System.out.println("4. See wait lists.");
        System.out.println("5. Nothing Else");
    }

    public static void main(String args[]) {

        Stadium stateFarmStadium = new Stadium(500,1000,2000);

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
        System.out.println("-------------------------------------------------------------"); 

        
        Client Edward = new Client("Edward", "edward.carde@upr.edu", "787-612-7168");
        //System.out.println(Edward.toString());

        Chair Chair17 = new Chair(17, "Field Level", 300);
        //Chair17.reservation(Edward.getName()); 

      

    }

}