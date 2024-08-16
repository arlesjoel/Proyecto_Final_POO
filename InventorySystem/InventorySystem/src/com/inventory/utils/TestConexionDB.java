package com.inventory.utils;

public class TestConexionDB {
    public static void main(String[] args) {
        // Intentar obtener una conexión a la base de datos
        ConexionDB.getConnection();
        
        // Cerrar la conexión (opcional, solo si quieres probar cerrar la conexión manualmente)
        ConexionDB.closeConnection();
    }
}
