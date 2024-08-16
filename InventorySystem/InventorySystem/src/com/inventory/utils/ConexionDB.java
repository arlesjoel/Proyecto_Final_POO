package com.inventory.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static Connection connection = null;

    // Información de la conexión a la base de datos
    private static final String URL = "jdbc:mysql://ajavierfm7.mssql.somee.com:3306/ajavierfm7";
    private static final String USER = "ajajvierfm7_SQLLogin_1";
    private static final String PASSWORD = "tgncm297vv";

    // Método para obtener la conexión
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al conectar a la base de datos");
            }
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar la conexión a la base de datos");
            }
        }
    }
}
