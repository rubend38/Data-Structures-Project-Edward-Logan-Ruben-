import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Queue;

public class ReservationSystem {

    static class Chair {
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

    static class client {
        private String name;
        private String email;
        private String phone;

        public client(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;

        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String toString() {
            return "client[name=" + name + ", email=" + email + ", phone=" + phone + "]";
        }

    }

    public static void main(String args[]) {
        
        client Edward = new client("Edward", "edward.carde@upr.edu", "787-612-7168");
        System.out.println(Edward.toString());

        Chair Chair17 = new Chair(17, "Field Level", 300);
        Chair17.reservation(Edward.getName()); 


    }

}