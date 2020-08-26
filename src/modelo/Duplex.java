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
public class Duplex extends Casa{
 private String d = "D";
//Declaramos los atributos.
    
   protected  double tamPlanta1;
   protected  double tamPlanta2;
   
   
//Método Constructor.
   public Duplex (int referencia, double precio, double superficie, int habitaciones, double tamPlanta1, double tamPlanta2, String d){
   
   super(referencia, precio, superficie, habitaciones);
   
   this.referencia = referencia;
   this.precio = precio;
   this.superficie = superficie;
   this.habitaciones = habitaciones;
   this.tamPlanta1 = tamPlanta1;
   this.tamPlanta2 = tamPlanta2;
   
   }

   
//Método toString.

    @Override
    public String toString() {
        return "Duplex: Tamaño planta 1: " + tamPlanta1 
                    + " Tamaño planta 2: " + tamPlanta2 
                                 + " Precio: " + precio 
                         + " Superficie: " + superficie
                        + "Habitación: " + habitaciones
                + " Tipo Casa: " + d;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public double getTamPlanta1() {
        return tamPlanta1;
    }

    public void setTamPlanta1(double tamPlanta1) {
        this.tamPlanta1 = tamPlanta1;
    }

    public double getTamPlanta2() {
        return tamPlanta2;
    }

    public void setTamPlanta2(double tamPlanta2) {
        this.tamPlanta2 = tamPlanta2;
    }

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
   
   
   
   
    }
    
  
   

