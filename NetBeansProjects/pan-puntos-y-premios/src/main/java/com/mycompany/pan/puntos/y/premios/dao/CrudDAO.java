/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.dao;

import java.util.List;

/**
 *
 * @author ejhon
 */
public interface CrudDAO<T> {
    
    boolean insert(T objeto);
    boolean actualizar (T objeto);
    boolean eliminar (T objeto);
    T buscarPorId(int id);
    List<T> listarTodos();
}
