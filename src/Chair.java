    public class Chair {
        private int number;
        private int price;
        private boolean available;
        private String client;
        private String section;

        public Chair(int number, String section, int price) {
            this.number = number;
            this.price = price;
            this.available = true;
            this.client = null;
            this.section = section;
        }

        public boolean reservation(String client) {
            if (available) {
                this.available = false;
                this.client = client;
                System.out.println("The client " + client + " reserved chair number " + getnumber() + ".");

                return true;

            }
            return false;
        }

        public String cancelReservation() { // Devuelve el client que canceló la reserva
            if (!available) {
                this.available = true;
                String clientActual = client;
                this.client = null;
                return clientActual;

            }
            return null;
        }

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
