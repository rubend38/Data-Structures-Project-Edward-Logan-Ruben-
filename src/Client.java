import java.util.*; 

public class Client {
    private String name;
    private String email;
    private String phone;

    //constructer for Client object
    public Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;

    }

    //series of getters for name, email and phone properties of client.
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    //returns the format for the program of client, email and phone which is a combined string.
    public String toString() {
        return "client[name=" + name + ", email=" + email + ", phone=" + phone + "]";
    }

    //gets the clients reservations
    public List<Integer> getReservations(Stadium stadium){
        return stadium.getClientReservations(this); 
    }

}