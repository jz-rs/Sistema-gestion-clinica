/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaprivadadoo.dao;

import com.mycompany.clinicaprivadadoo.modelo.ListadoDeCaja;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListadoDeCajaDao {

    private ConexionSql conexionSql;

    public ListadoDeCajaDao(ConexionSql conexionSql) {
        this.conexionSql = conexionSql;
    }

    public void crearListadoDeCaja(ListadoDeCaja listadoDeCaja) throws SQLException {
        String sql = "INSERT INTO ListadoDeCaja (datosConsulta, total) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexionSql.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, listadoDeCaja.getDatosConsulta());
            pstmt.setFloat(2, listadoDeCaja.getTotal());
            pstmt.executeUpdate();
        }
    }

    public List<ListadoDeCaja> obtenerListadosDeCaja() throws SQLException {
        List<ListadoDeCaja> listadosDeCaja = new ArrayList<>();
        String sql = "SELECT * FROM ListadoDeCaja";
        try (Statement stmt = conexionSql.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ListadoDeCaja listadoDeCaja = new ListadoDeCaja(
                        rs.getInt("datosConsulta"),
                        rs.getFloat("total")
                );
                listadoDeCaja.setId(rs.getInt("id"));
                listadosDeCaja.add(listadoDeCaja);
            }
        }
        return listadosDeCaja;
    }
}
