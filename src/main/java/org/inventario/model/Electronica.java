package org.inventario.model;

// Clase hija que representa productos de electrónica
public class Electronica extends Producto {

    private String modelo;

    // Constructor
    public Electronica(String nombre, double precio, String modelo) {
        super(nombre, precio);
        this.modelo = modelo;
    }

    @Override
    public String getTipo() {
        return "org.inventario.model.Electronica";
    }
    // Getters y Setters
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return super.toString() + "org.inventario.model.Electronica{" + "modelo=" + modelo + '}';
    }
}