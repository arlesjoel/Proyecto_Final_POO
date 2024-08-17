/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

import com.inventory.models.Stock;
import com.inventory.utils.ConexionDB;
import java.sql.*;
import java.util.List;


public class StockDAO  implements GenericDAO<Stock> {

    private Connection connection;

    public StockDAO() {
        connection = ConexionDB.getConnection();
    }
   
    @Override
    public void insertar(Stock t) {
      String query = "INSERT INTO Stock (id_producto, cantidad, id_usuario) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, t.getIdProducto());
            ps.setInt(2, t.getCantidad());
            ps.setInt(3, t.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }        
    }
    
    

    @Override
    public Stock obtenerPorId(int id) {
        Stock stock = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM Stock s ");
        sb.append("WHERE s.id_producto = ?");
        String query = sb.toString();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    stock = new Stock();
                    stock.setIdTransaccion(rs.getInt("id_transaccion"));
                    stock.setIdProducto(rs.getInt("id_producto"));
                    stock.setCantidad(rs.getInt("cantidad"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto por código: " + e.getMessage());
        }
        return stock;
    }

    @Override
    public List<Stock> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Stock t) {
        String query = "UPDATE Stock SET cantidad = ? WHERE id_producto = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, t.getCantidad());
            ps.setInt(2, t.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
