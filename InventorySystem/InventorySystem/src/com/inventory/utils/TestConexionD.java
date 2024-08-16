/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.utils;

/**
 *
 * @author ing
 */
public class TestConexionD {
    public static void main(String[] args) {
        // Intentar obtener una conexión a la base de datos
        ConexionDB.getConnection();
        
        // Cerrar la conexión (opcional, solo si quieres probar cerrar la conexión manualmente)
        ConexionDB.closeConnection();
    }
}
