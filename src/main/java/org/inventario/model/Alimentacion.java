//Clase hija que representa productos de Alimentación

import java.time.LocalDate;

public class Alimentacion extends Producto{

    private LocalDate fechaCaducidad;

    // Constructor
    public Alimentacion(String nombre, double precio, LocalDate fechaCaducidad) {
        super(nombre, precio);
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String getTipo() {
        return "Alimentacion";
    }

    // Getters y Setters
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String toString() {
        return super.toString() + "Alimentacion{" + "fechaCaducidad=" + fechaCaducidad + '}';
    }
}