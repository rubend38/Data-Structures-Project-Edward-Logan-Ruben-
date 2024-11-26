
// import src.Client; 
// import src.Chair; 

import java.util.*; 




public class Stadium{
    private Set<Chair> availableChairs;
    private HashMap<Chair,Client> reservations;
    private Queue<Client> fieldWaitlist;
    private Queue<Client> mainWaitlist;
    private Queue<Client> grandstandWaitlist;
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

        // Reserve all requred chairs
        for (Chair chair : chairsToReserve) {
            chair.reservation(customer.getName());
            reservations.put(chair, customer);
            transactionHistory
                    .add("Reserved: " + customer.getName() + " in " + section + " (Chair " + chair.getnumber() + ")");
            undoStack.push("RESERVE: " + chair.getnumber() + ":" + customer.getName());

        }

        return "Reservation succesful for " + customer.getName() + " (Chairs " + chairNumbers + ").";

    }

    public List<Integer> getClientReservations(Client client){ 
        List<Integer> reservedChairs = new ArrayList<>(); 
        for(Map.Entry<Chair, Client> entry: reservations.entrySet()){
            if(entry.getValue().equals(client)){
                reservedChairs.add(entry.getKey().getnumber()); 
            }
        }
        return reservedChairs; 
    }

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
                    break; 
                }
            }

            if(!found){
                return "Error: Chair number "+ chairNumber + " is either not reserved or not reserved by " + customer.getName() + "."; 
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
                transactionHistory.add("Assigned from waitllist: " + nextClient.getName() + " for Chair " + chair.getnumber()); 
                undoStack.push("RESERVE: " + chair.getnumber() + ":" + nextClient.getName()); 
            }
        }

        return "Cancellation successful for " + customer.getName() + ". Chairs: " + chairNumbers; 
    }

    private Queue<Client> getWaitlist(String section) {
        switch(section) {
            case "Field Level": 
                return fieldWaitlist; 
            case "Main Level":
                return mainWaitlist; 
            case "Grandstand Level":
                return grandstandWaitlist; 
            default:
                return null; 

        }
    }



    public static void main(String args[]) {

        Stadium stateFarmStadium = new Stadium(500,1000,2000);
        Client Edward = new Client("Edward", "edward.carde@upr.edu", "787-612-7168");
        System.out.println(stateFarmStadium.makeReservation(Edward, "Field Level", Arrays.asList(344,345,346,347,348,349))); 
        System.out.println("Edward Reservations: " + Edward.getReservations(stateFarmStadium)); 
        System.out.println(stateFarmStadium.cancelReservation(Edward, Arrays.asList(344,345))); 
        System.out.println("Edward Reservations: " + Edward.getReservations(stateFarmStadium));
        // System.out.println("-------------------------------------------------------------"); 

        
        //System.out.println(Edward.toString());

        // Chair Chair17 = new Chair(17, "Field Level", 300);
        //Chair17.reservation(Edward.getName()); 

    }

}