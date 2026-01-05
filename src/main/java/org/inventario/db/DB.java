package org.inventario.db;
// Clase para la gestión de la base de datos - Importar las librerías necesarias aquí según se requiera
import java.sql.*;

public class DB {

    // Conexión a la base de datos
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/orcl";

    //Datos de autenticación
    private static final String USER = "system";
    private static final String PASSWORD = "Santilafu12";

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
