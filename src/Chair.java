public class Chair {
    private int number;
    private int price;
    private boolean available;
    private String client;
    private String section;

    //chair object constructer
    public Chair(int number, String section, int price) {
        this.number = number;
        this.price = price;
        this.available = true;
        this.client = null;
        this.section = section;
    }
    //checks to see if the current object of chair is available or not.
    public boolean reservation(String client) {
        if (available) {
            this.available = false;
            this.client = client;
            System.out.println("The client " + client + " reserved chair number " + getnumber() + ".");

            return true;

        }
        return false;
    }
    //makes the chair available if it was previously unavailable.
    public String cancelReservation() { // Devuelve el client que canceló la reserva
        if (!available) {
            this.available = true;
            String clientActual = client;
            this.client = null;
            return clientActual;

        }
        return null;
    }

    //the methods below are a set of getters. 
    public boolean isavailable() {
            return available;
            }

    public int getnumber() {
        return number;
        }

    public String getsection() {
        return section;
        }

    public double getprice() {
        return price;
    }

}
