package com.inventory.dao;

import com.inventory.models.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.inventory.utils.ConexionDB;

public class CategoriaDAO implements GenericDAO<Categoria> {

    private Connection connection;

    public CategoriaDAO() {
        connection = ConexionDB.getConnection();
    }

    @Override
    public void insertar(Categoria categoria) {
        try {
            String query = "INSERT INTO Categorias (nombre) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, categoria.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Categoria obtenerPorId(int id) {
        Categoria categoria = null;
        try {
            String query = "SELECT * FROM Categorias WHERE id_categoria = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public List<Categoria> obtenerTodos() {
        List<Categoria> categorias = new ArrayList<>();
        try {
            String query = "SELECT * FROM Categorias";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    @Override
    public void actualizar(Categoria categoria) {
        try {
            String query = "UPDATE Categorias SET nombre = ? WHERE id_categoria = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getIdCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            String query = "DELETE FROM Categorias WHERE id_categoria = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

