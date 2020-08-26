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
public class Chalet extends Casa {
    
//Declaramos los atributos.
    protected double tamParcela;
    private  String c= "C";
//Método constructor.
    
    public Chalet (int referencia, double precio, double superficie, 
                                 int habitaciones, double tamParcela, String c){
    
    super (referencia, precio, superficie, habitaciones);
    
    this.referencia = referencia;
    this.precio = precio;
    this.superficie = superficie;
    this.habitaciones = habitaciones;
    this.tamParcela = tamParcela;
    
    }
    
//Método toString().    

    @Override
    public String toString() {
        return "Chalet Referencia: " + referencia + "\n"
                + "Tamaño Parcela: " + tamParcela
                + " Precio: " + precio 
                + " Superficie: " + superficie
                + " Habitación: " + habitaciones
                + " Tipo Casa: " + c;
    }

    public double getTamParcela() {
        return tamParcela;
    }

    public void setTamParcela(double tamParcela) {
        this.tamParcela = tamParcela;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
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
