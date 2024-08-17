package com.inventory.dao;

import com.inventory.models.Producto;
import com.inventory.models.Stock;
import com.inventory.models.Usuario;
import com.inventory.utils.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private Connection connection;

    public ProductoDAO() {
        connection = ConexionDB.getConnection();
    }

    public int insertar(Producto producto) {
        String query = "INSERT INTO Productos (codigo, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            
        }
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }
        
        return 0;
    }

    public List<Producto> obtenerPorCodigo(String codigo) {
        List<Producto> productos = new ArrayList<>();
        Producto producto = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT p.id_producto, p.codigo, p.nombre, s.id_transaccion, s.cantidad, u.id_usuario, u.nombre userName FROM Productos p ");
        sb.append("LEFT JOIN Stock s on p.id_producto = s.id_producto ");
        sb.append("LEFT JOIN Usuarios u on s.id_usuario = u.id_usuario ");
        sb.append("WHERE LOWER(p.nombre) like ?");
        String query = sb.toString();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + codigo.toLowerCase() +"%" );
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    producto = new Producto();
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setCodigo(rs.getString("codigo"));
                    producto.setNombre(rs.getString("nombre"));

                    Stock stock = new Stock();
                    stock.setIdTransaccion(rs.getInt("id_transaccion"));
                    stock.setCantidad(rs.getInt("cantidad"));

                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("userName"));

                    stock.setUsuario(usuario);
                    producto.setStock(stock);
                    
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto por código: " + e.getMessage());
        }
        return productos;
    }

    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT p.id_producto, p.codigo, p.nombre, s.id_transaccion, s.cantidad, u.id_usuario, u.nombre userName FROM Productos p ");
        sb.append("LEFT JOIN Stock s on p.id_producto = s.id_producto ");
        sb.append("LEFT JOIN Usuarios u on s.id_usuario = u.id_usuario ");

        String query = sb.toString();

        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setNombre(rs.getString("nombre"));

                Stock stock = new Stock();
                stock.setIdTransaccion(rs.getInt("id_transaccion"));
                stock.setCantidad(rs.getInt("cantidad"));

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("userName"));

                stock.setUsuario(usuario);
                producto.setStock(stock);

                productos.add(producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los productos: " + e.getMessage());
        }
        return productos;
    }

    public void eliminarPorId(int id) {
        String query = "DELETE FROM Productos WHERE id_producto = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto por código: " + e.getMessage());
        }
    }

    public void actualizar(Producto producto) {
        String query = "UPDATE Productos SET nombre = ? WHERE id_producto = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }
}
