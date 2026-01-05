package org.inventario.model;

// Clase Padre que representa un producto genérico
public abstract class Producto {

    // Atributos comunes a todos los productos
    protected Long id;
    protected String nombre;
    protected double precio;

    // Constructor - No incluir id ya que es autogenerado.
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Cada hijo dira que tipo de producto es para guardar en la base de datos
    public abstract String getTipo();

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override // toString para facilitar la impresión de objetos org.inventario.model.Producto
    public String toString() {
        return "org.inventario.model.Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
    }
}
