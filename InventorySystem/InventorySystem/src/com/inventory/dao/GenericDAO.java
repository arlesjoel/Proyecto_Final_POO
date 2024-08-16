/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.inventory.dao;

import java.util.List;

/**
 *
 * @author AJAVI
 */
public interface GenericDAO<T> {
    void insertar(T t);
    T obtenerPorId(int id);
    List<T> obtenerTodos();
    void actualizar(T t);
    void eliminar(int id);
}
