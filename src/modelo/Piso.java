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
public class Piso extends Casa {
    
    
//Declaramos los atributos.
    protected int planta;
    private  String p = "P";
    
// Constructor.
    public Piso (int referencia, double precio, double superficie, int habitaciones, int planta, String p){
    super (referencia, precio, superficie, habitaciones);
    
    this.referencia = referencia;
    this.precio = precio;
    this.superficie = superficie; 
    this.habitaciones = habitaciones;
    this.planta = planta;
    
    }
    
//Método toString().

    @Override
    public String toString() {
        return "Piso Referencia: " + referencia + "\nPlanta: " + planta + " Precio: " + precio + " Superficie: "
                + superficie + " Habitacion: " + habitaciones + " Tipo de Casa: " + p;
    }

    public int getPlanta() {
        return planta;
    }

    public String getP() {
        return p;
    }
    
    
    
    
}
