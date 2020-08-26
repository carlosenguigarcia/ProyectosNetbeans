/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author CARLOS
 */
public class Casa implements ALaVenta{
   
    
//Declaramos como privados los atributos de clase.
    protected int referencia;
    protected double precio;
    protected double superficie;
    protected int habitaciones;
    

 //Constructor con parámetros.

    public Casa(int referencia, double precio, double superficie, int habitaciones) {
        this.referencia = referencia;
        this.precio = precio;
        this.superficie = superficie;
        this.habitaciones = habitaciones;
    }

 //Métodos getter y setter.
    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

                    //DEMÁS MÉTODOS:

// Método subirPrecio().
    
    @Override    
    public void subirPrecio(){
    
       precio= getPrecio();
        double n=(precio*5)/100;
        precio=precio+n;  
        precio=Math.round(precio*100)/100.0;
    
    }
    

//Método bajarPrecio().
    @Override  
    public void bajarPrecio(){
    
        precio= getPrecio();
        double n=(precio*5)/100;
        precio=precio-n;  
        precio=Math.round(precio*100)/100.0;
    }
  
//Método toString().
    @Override
    public String toString() {
        return "Casa " + "Referencia: " + referencia + "\nPrecio: "
                + precio + ", Superficie: " + superficie + ", habitaciones: " + habitaciones;
    }

//Métodos hasCode y equals.
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
  
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Casa other = (Casa) obj;
        if (this.referencia != other.referencia) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (Double.doubleToLongBits(this.superficie) != Double.doubleToLongBits(other.superficie)) {
            return false;
        }
        if (this.habitaciones != other.habitaciones) {
            return false;
        }
        return true;
    }
   
    
}
