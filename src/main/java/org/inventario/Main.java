package org.inventario;

import org.inventario.dao.ProductoDAO;
import org.inventario.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        ProductoDAO dao = new ProductoDAO();

        // 1) Crear objetos (herencia)
        Producto p1 = new Electronica("Portátil", 899.99, "Lenovo ThinkPad");
        Producto p2 = new Ropa("Camiseta", 19.95, "M");
        Producto p3 = new Alimentacion("Leche", 1.25, LocalDate.of(2026, 1, 31));

        // 2) Guardarlos (polimorfismo: mismo método, distintos comportamientos)
        dao.guardarProducto(p1);
        dao.guardarProducto(p2);
        dao.guardarProducto(p3);

        // 3) Obtener uno por ID (si se asignó id en guardarProducto)
        if (p1.getId() != null) {
            Producto encontrado = dao.obtenerProducto(p1.getId());
            System.out.println("Encontrado: " + encontrado);

            // 4) Modificar
            dao.actualizarPrecio(p1.getId(), 799.99);

            Producto actualizado = dao.obtenerProducto(p1.getId());
            System.out.println("Actualizado: " + actualizado);
        }
    }
}
