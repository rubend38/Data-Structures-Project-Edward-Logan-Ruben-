import java.util.*; 

public class Stadium{
    private Set<Chair> availableChairs;
    private HashMap<Chair,Client> reservations;
    private Queue<Client> fieldWaitlist;
    private Queue<Client> mainWaitlist;
    private Queue<Client> grandstandWaitlist;
    private LinkedList<String> transactionHistory;
    private Stack<String> undoStack;
    private int fieldLevelCount = 0;
    private int mainLevelCount = 0;
    private int grandstandLevelCount = 0;


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
    public void updateAvailableSeats() {
        fieldLevelCount = 0;
        mainLevelCount = 0;
        grandstandLevelCount = 0;
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
    }
        
    public void showAvailableSeats() {
        updateAvailableSeats();
        System.out.println("Field Level:       "+  fieldLevelCount +" at $300" );
        System.out.println("Main Level:       " + mainLevelCount +" at $120" );
        System.out.println("Grandstand Level: " + grandstandLevelCount  +" at $45");
    }


    // public String makeReservation(Client customer, String section, List<Integer> chairNumbers) {

    //     List<Chair> chairsToReserve = new ArrayList<>();
    //     int price = 0;
    //     String normalizedSection = "";
    //     //Checking if section is full. Automatically adds the client to the queue if the section is full
    //     if(section.equalsIgnoreCase("Field Level")){
    //         price = 300;
    //         normalizedSection = "Field Level";
    //         if(fieldLevelCount == 0){
    //             fieldWaitlist.add(customer);
    //             return section + " section is full. Added " + customer.getName()+ " to " + section + " waitlist.";
    //         }
    //     } else if(section.equalsIgnoreCase("Main Level")){
    //         price = 120;
    //         normalizedSection = "Main Level";
    //         if(mainLevelCount == 0){
    //             mainWaitlist.add(customer);
    //             return section + " section is full. Added " + customer.getName()+ " to " + section + " waitlist.";
    //         }
    //     } else if(section.equalsIgnoreCase("Grandstand Level")){
    //         price = 45;
    //         normalizedSection = "Grandstand Level";
    //         if(grandstandLevelCount == 0){
    //             grandstandWaitlist.add(customer);
    //             return section + " section is full. Added " + customer.getName()+ " to " + section + " waitlist.";
    //         }
    //     } else {
    //         return "Invalid section.";
    //     }


    //     // Checking if chairs are available
    //     for (int chairNumber : chairNumbers) {
    //         boolean found = false;

    //         for (Chair chair : availableChairs) {

    //             if (chair.getnumber() == chairNumber) {
    //                 if (!chair.isavailable()) {
    //                     return "Chair number " + chairNumber + " is not available.";
    //                 }

    //                 chairsToReserve.add(chair);
    //                 found = true; 
    //             }
    //         }

    //         if (!found) {
    //             return "Chair number " + chairNumber + " does not exist.";

    //         }
    //     }

    //     // Reserve all required chairs
    //     for (Chair chair : chairsToReserve) {
    //         chair.reservation(customer.getName());
    //         reservations.put(chair, customer);
    //         transactionHistory
    //                 .add("Reserved: " + customer.getName() + " in " + section + " (Chair " + chair.getnumber() + ")");
    //         undoStack.push("RESERVE: " + chair.getnumber() + ":" + customer.getName());
    //     }
        
    //     return "Reservation succesful for " + customer.getName() + ": Chairs: " + chairNumbers + " in section " + normalizedSection + ". Your total is: $" + price * chairNumbers.size() + ".";

    // }
    
    // Hacer reservaciones de sillas en el estadio
    public String makeReservation(Client customer, String section, List<Integer> chairNumbers) {

        List<Chair> chairsToReserve = new ArrayList<>();

        // Checking if chairs are available

        for (int chairNumber : chairNumbers) {
            boolean found = false;

            for (Chair chair : availableChairs) {

                if (chair.getnumber() == chairNumber) {
                    if (!chair.isavailable()) {
                        return "Chair number " + chairNumber + " is not available.";
                    }
                    chairsToReserve.add(chair);
                    found = true;
                    break;
                }
            }

            if (!found) {
                return "Chair number " + chairNumber + " does not exist.";

            }
        }

        // Reserve all required chairs
        for (Chair chair : chairsToReserve) {
            chair.reservation(customer.getName());
            reservations.put(chair, customer);
            transactionHistory
                    .add("Reserved: " + customer.getName() + " in " + section + " (Chair " + chair.getnumber() + ")");
            undoStack.push("RESERVE:" + chair.getnumber() + ":" + customer.getName());
        }

        return "Reservation succesful for " + customer.getName() + " (Chairs " + chairNumbers + ").";

    }

    //Metodo que itera por el HashMap de reservaciones para saber que sillas estan reservadas 

    public List<Integer> getClientReservations(Client client){ 
        List<Integer> reservedChairs = new ArrayList<>(); 
        for(Map.Entry<Chair, Client> entry: reservations.entrySet()){
            if(entry.getValue().equals(client)){
                reservedChairs.add(entry.getKey().getnumber()); 
            }
        }
        return reservedChairs; 
    }

    //Cancelar reservacion 
    public String cancelReservation(Client customer, List<Integer> chairNumbers){
        List<Chair> chairsToCancel = new ArrayList<>(); 
        List<Chair> canceledChairs = new ArrayList<>(); 

        //Verificamos si es que el cliente tiene reservadas sillas específicas 

        for(int chairNumber: chairNumbers){
            boolean found = false; 

            for(Chair chair: reservations.keySet()){
                if(chair.getnumber() == chairNumber && reservations.get(chair).equals(customer)){
                    chairsToCancel.add(chair); 
                    found = true; 
                    System.out.println("Reservation Found and Cancelled");
                    break; 
                }
            }

            if(!found){
                return "Error: Chair number "+ chairNumber + " is either not reserved or not reserved by " +
                 customer.getName() + "."; 
            }
        }

        //Cancelar las reservaciones 
        for(Chair chair: chairsToCancel){
            chair.cancelReservation(); 
            reservations.remove(chair); //Removemos la silla de la lista de sillas reservadas 
            transactionHistory.add("Canceled: " + customer.getName() + " for Chair " + chair.getnumber()); 
            undoStack.push("CANCEL: "  + chair.getnumber() + ":" + customer.getName()); 
            canceledChairs.add(chair); 
        }

        //Manejar lista de espera  y reasignar las sillas libres

        for(Chair chair: canceledChairs){
            Queue<Client> waitlist = getWaitlist(chair.getsection()); 
            if(waitlist != null && !waitlist.isEmpty()){
                Client nextClient = waitlist.poll(); //Sacamos el cliente mas antiguo
                chair.reservation(nextClient.getName()); 
                reservations.put(chair, nextClient); 
                transactionHistory.add("Assigned from waitllist: " + nextClient.getName() + " for Chair " +
                 chair.getnumber()); 
                undoStack.push("RESERVE: " + chair.getnumber() + ":" + nextClient.getName()); 
            }
        }

        return "Cancellation successful for " + customer.getName() + ". Chairs: " + chairNumbers; 
    }

    //Metodo Auxiliar para obtener las listas de espera 
    private Queue<Client> getWaitlist(String section) {
        switch(section) {
            case "Field Level": 
                return fieldWaitlist; 
            case "main level":
                return mainWaitlist; 
            case "grandstand level":
                return grandstandWaitlist; 
            default:
                return null; 

        }
    }

    //Metodo que actua como "UNDO" revirtiendo la ultima accion realizada utilizando un Stack.  
    public String undoLastAction() {

        if(undoStack.isEmpty()){
            return "No actions to undo."; 
        }

        String lastAction = undoStack.pop(); 
        String[] actionParts = lastAction.split(":"); 
        String actionType = actionParts[0]; 
        int chairNumber = Integer.parseInt(actionParts[1]); 
        String clientName = actionParts[2]; 

        //Buscando la silla asociada a la reservacion 

        Chair targetChair = null; 
        for(Chair chair:availableChairs){
            if(chair.getnumber() == chairNumber){
                targetChair = chair; 
                break; 
            }
        }

        if(targetChair == null){
            return "Error: Chair "+ chairNumber + "not found.";  
        }

        switch(actionType){
            case "RESERVE":  //Revertir reservacion

                Client client = reservations.get(targetChair); 
                if(client != null && client.getName().equals(clientName)){
                    targetChair.cancelReservation(); 
                    reservations.remove(targetChair); 
                    return "Reservation undone for chair "+ chairNumber + "by " + clientName + "."; 

                }
                break; 
            case "CANCEL": //Restaurar cancelacion 
            targetChair.reservation(clientName); 
            reservations.put(targetChair, new Client(clientName, "unknown", "unknown")); 
            return "Cancellation undone for Chair " + chairNumber + " by " + clientName + "."; 

        }
        return "Undo operation failed"; 

    }

    public void showReservations() {
        if(reservations.isEmpty()){
            System.out.println("No active reservations."); 
            return ;
        }

        System.out.println("Active reservations:");
        for(Map.Entry<Chair,Client> entry: reservations.entrySet()){
            Chair chair = entry.getKey(); 
            Client client = entry.getValue(); 
            System.out.println("Chair " + chair.getnumber() + " - " + chair.getsection() +
             " - Reserved by " + client.getName()); 
        }
    }

    public void showWaitlist(){
        System.out.println("Waitlists"); 
        System.out.print("Field Level: "); 

        if(fieldWaitlist.isEmpty()){
            System.out.println("No clients in waitlist.");
        }
        else{
            for(Client client: fieldWaitlist){
                System.out.print(client.getName() + " ");

            }
            System.out.println(); 
            
        }

        System.out.print("Main Level: "); 
        if(mainWaitlist.isEmpty()){
            System.out.println("No clients in waitlist");
        }
        else{
            for(Client client: mainWaitlist){
                System.out.print(client.getName() + " "); 
            }
            System.out.println(); 
        }

        System.out.print("Grandstand Level: "); 
        if(grandstandWaitlist.isEmpty()){
            System.out.println("No clients in waitlist.");
        }
        else{
            for(Client client: grandstandWaitlist){
                System.out.print(client.getName() + " "); 
            }
            System.out.println(); 
            
        }

    }

    public Client findClientByName(String name) {
        for (Client client : reservations.values()) {
            if (client.getName().equalsIgnoreCase(name)) {
                return client;
            }
        }
        return null;
    }





    public static void main(String args[]) {

        Stadium stateFarmStadium = new Stadium(500,1000,2000);
        Client Edward = new Client("Edward", "edward.carde@upr.edu", "787-612-7168");
        System.out.println(stateFarmStadium.makeReservation(Edward, "Field Level",
         Arrays.asList(344,345,346,347,348,349))); 
        System.out.println("Edward Reservations: " + Edward.getReservations(stateFarmStadium)); 
        System.out.println(stateFarmStadium.cancelReservation(Edward, Arrays.asList(344,345))); 
        System.out.println("Edward Reservations: " + Edward.getReservations(stateFarmStadium));
       

    }

}