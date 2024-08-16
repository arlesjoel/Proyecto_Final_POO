package com.inventory.dao;   

import com.inventory.models.Subcategoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.inventory.utils.ConexionDB;

public class SubcategoriaDAO implements GenericDAO<Subcategoria> {

    private Connection connection;

    public SubcategoriaDAO() {
        connection = ConexionDB.getConnection();
    }

    @Override
    public void insertar(Subcategoria subcategoria) {
        try {
            String query = "INSERT INTO Subcategorias (nombre, id_categoria) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, subcategoria.getNombre());
            ps.setInt(2, subcategoria.getIdCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Subcategoria obtenerPorId(int id) {
        Subcategoria subcategoria = null;
        try {
            String query = "SELECT * FROM Subcategorias WHERE id_subcategoria = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subcategoria = new Subcategoria();
                subcategoria.setIdSubcategoria(rs.getInt("id_subcategoria"));
                subcategoria.setNombre(rs.getString("nombre"));
                subcategoria.setIdCategoria(rs.getInt("id_categoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subcategoria;
    }

    @Override
    public List<Subcategoria> obtenerTodos() {
        List<Subcategoria> subcategorias = new ArrayList<>();
        try {
            String query = "SELECT * FROM Subcategorias";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Subcategoria subcategoria = new Subcategoria();
                subcategoria.setIdSubcategoria(rs.getInt("id_subcategoria"));
                subcategoria.setNombre(rs.getString("nombre"));
                subcategoria.setIdCategoria(rs.getInt("id_categoria"));
                subcategorias.add(subcategoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subcategorias;
    }

    @Override
    public void actualizar(Subcategoria subcategoria) {
        try {
            String query = "UPDATE Subcategorias SET nombre = ?, id_categoria = ? WHERE id_subcategoria = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, subcategoria.getNombre());
            ps.setInt(2, subcategoria.getIdCategoria());
            ps.setInt(3, subcategoria.getIdSubcategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            String query = "DELETE FROM Subcategorias WHERE id_subcategoria = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
