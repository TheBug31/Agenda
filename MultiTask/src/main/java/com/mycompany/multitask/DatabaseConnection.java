package com.mycompany.multitask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/agenda?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Cambia esto si tu usuario es diferente
    private static final String PASSWORD = ""; // Cambia esto si tienes contraseña

    // Método para obtener la conexión a la base de datos
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Cargar el controlador JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa a la base de datos!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el controlador de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión a la base de datos.");
            e.printStackTrace();
        }
        return connection; // Retorna la conexión (puede ser null si hay un error)
    }
}
