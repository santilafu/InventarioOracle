package org.inventario.dao;

// Clase DAO para la gestión de productos en la base de datos - Importar las librerías necesarias aquí según se requiera
import java.sql.*;
import java.time.LocalDate;

import org.inventario.db.DB; // Importar la clase de gestión de la base de datos
import org.inventario.model.*; //Importar las clases de modelo de productos

public class ProductoDAO {

    /**
     * Guarda un producto en la tabla PRODUCTOS.
     * Usa polimorfismo: recibe Producto, pero se comporta según si es Ropa/Electronica/Alimentacion.
     */
    public void guardarProducto(Producto p) {
        String sql = """
            INSERT INTO PRODUCTOS (NOMBRE, PRECIO, TIPO, MODELO, TALLA, FECHA_CADUCIDAD)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"})) {

            // Campos comunes
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setString(3, p.getTipo());

            // Campos específicos según tipo (si no aplica, va null)
            if (p instanceof Electronica e) {
                ps.setString(4, e.getModelo());
                ps.setString(5, null);
                ps.setDate(6, null);
            } else if (p instanceof Ropa r) {
                ps.setString(4, null);
                ps.setString(5, r.getTalla());
                ps.setDate(6, null);
            } else if (p instanceof Alimentacion a) {
                ps.setString(4, null);
                ps.setString(5, null);
                ps.setDate(6, Date.valueOf(a.getFechaCaducidad()));
            } else {
                // Si llega un tipo nuevo no contemplado
                ps.setString(4, null);
                ps.setString(5, null);
                ps.setDate(6, null);
            }

            ps.executeUpdate();

            // Recuperar ID generado (opcional pero útil)
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    long idGenerado = rs.getLong(1);
                    p.setId(idGenerado);
                }
            }

            System.out.println("Guardado: " + p);

        } catch (SQLException ex) {
            System.out.println("Error guardando producto: " + ex.getMessage());
        }
    }

    /**
     * Obtiene un producto por ID.
     * Lee la fila y crea el objeto correcto según el campo TIPO.
     */
    public Producto obtenerProducto(long id) {
        String sql = "SELECT * FROM PRODUCTOS WHERE ID = ?";

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                String nombre = rs.getString("NOMBRE");
                double precio = rs.getDouble("PRECIO");
                String tipo = rs.getString("TIPO");

                String modelo = rs.getString("MODELO");
                String talla = rs.getString("TALLA");
                Date fechaSql = rs.getDate("FECHA_CADUCIDAD");

                Producto p;

                // Crear el objeto correcto según TIPO
                if ("ELECTRONICA".equalsIgnoreCase(tipo)) {
                    p = new Electronica(nombre, precio, modelo);
                } else if ("ROPA".equalsIgnoreCase(tipo)) {
                    p = new Ropa(nombre, precio, talla);
                } else if ("ALIMENTACION".equalsIgnoreCase(tipo)) {
                    LocalDate fecha = (fechaSql != null) ? fechaSql.toLocalDate() : null;
                    p = new Alimentacion(nombre, precio, fecha);
                } else {
                    // Si aparece un tipo desconocido, lo tratamos como genérico
                    p = new Electronica(nombre, precio, "DESCONOCIDO");
                }

                p.setId(id);
                return p;
            }

        } catch (SQLException ex) {
            System.out.println("Error obteniendo producto: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Modifica el precio de un producto por ID.
     */
    public void actualizarPrecio(long id, double nuevoPrecio) {
        String sql = "UPDATE PRODUCTOS SET PRECIO = ? WHERE ID = ?";

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, nuevoPrecio);
            ps.setLong(2, id);

            int filas = ps.executeUpdate();
            System.out.println("Filas actualizadas: " + filas);

        } catch (SQLException ex) {
            System.out.println("Error actualizando precio: " + ex.getMessage());
        }
    }
}