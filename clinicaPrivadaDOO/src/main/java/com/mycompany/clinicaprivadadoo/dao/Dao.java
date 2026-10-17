/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.clinicaprivadadoo.dao;

import java.sql.Connection;
import java.util.List;

public interface Dao<T> {
    List<T> listarTodos();
    boolean insertar(T dto);
    boolean modificar(T dto);
    boolean borrar(T dto);
    T buscar(T dto);
    List<T> listarPorCriterio(T dto);
    
    default Connection getConnection (){
        return ConexionSql.getInstance().getConnection();
    }
}