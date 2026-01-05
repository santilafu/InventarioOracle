package org.inventario.model;

//Clase hija que representa productos de ropa
public class Ropa extends Producto {

    private String talla;

    // Constructor
    public Ropa(String nombre, double precio, String talla) {
        super(nombre, precio);
        this.talla = talla;
    }

    @Override // Cada hijo dira que tipo de producto es para guardar en la base de datos
    public String getTipo() {
        return "org.inventario.model.Ropa";
    }
    // Getters y Setters
    public String getTalla() {
        return talla;
    }
    public void setTalla(String talla) {
        this.talla = talla;
    }

    @Override
    public String toString() {
        return super.toString() + "org.inventario.model.Ropa{" + "talla=" + talla + '}';
    }

}