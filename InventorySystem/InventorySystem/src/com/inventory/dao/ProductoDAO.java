package com.inventory.dao;

import com.inventory.models.Producto;
import com.inventory.utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private Connection connection;

    public ProductoDAO() {
        connection = ConexionDB.getConnection();
    }

    public void insertar(Producto producto) {
        String query = "INSERT INTO Productos (codigo, nombre, descripcion, nivel_minimo, nivel_maximo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getNivelMinimo());
            ps.setInt(5, producto.getNivelMaximo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }
    }

    public Producto obtenerPorCodigo(String codigo) {
        Producto producto = null;
        String query = "SELECT * FROM Productos WHERE codigo = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setCodigo(rs.getString("codigo"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setNivelMinimo(rs.getInt("nivel_minimo"));
                    producto.setNivelMaximo(rs.getInt("nivel_maximo"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto por código: " + e.getMessage());
        }
        return producto;
    }

    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Productos";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setNivelMinimo(rs.getInt("nivel_minimo"));
                producto.setNivelMaximo(rs.getInt("nivel_maximo"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los productos: " + e.getMessage());
        }
        return productos;
    }

    public void eliminarPorCodigo(String codigo) {
        String query = "DELETE FROM Productos WHERE codigo = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto por código: " + e.getMessage());
        }
    }

    public void actualizar(Producto producto) {
        String query = "UPDATE Productos SET nombre = ?, descripcion = ?, nivel_minimo = ?, nivel_maximo = ? WHERE codigo = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getNivelMinimo());
            ps.setInt(4, producto.getNivelMaximo());
            ps.setString(5, producto.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }
}


