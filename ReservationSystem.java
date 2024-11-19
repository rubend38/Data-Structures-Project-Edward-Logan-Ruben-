import java.util.ArrayList; 
import java.util.LinkedList; 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Queue; 


public class ReservationSystem{

    class Asientos{
        private int numero; 
        private double precio; 
        private boolean disponible; 
        private String cliente; 
        private String seccion; 
        
        public Asientos(int numero,String seccion, double precio){
            this.numero = numero; 
            this.precio = precio; 
            this.disponible = true; 
            this.cliente = null; 
            this.seccion = seccion; 
        } 

        public boolean reservacion(String cliente){ 
            if(disponible) {
                this.disponible = false; 
                this.cliente = cliente; 
                return true; 
            }
            return false; 
        }

        public String cancelarReserva(){  //Devuelve el cliente que canceló la reserva
            if(!disponible) {
                this.disponible = true; 
                String clienteActual = cliente; 
                this.cliente = null; 
                return clienteActual; 

            }
            return null; 
        }

        public boolean isDisponible() {
            return disponible;
        }

        public int getNumero() {
            return numero;
        }

        public String getSeccion() {
            return seccion;
        }

        public double getPrecio() {
            return precio;
        }

    }   
    

}