/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaprivadadoo.dao;

import com.mycompany.clinicaprivadadoo.modelo.HistoriaClinica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoriaClinicaDao {
    private Connection connection;

    public HistoriaClinicaDao(Connection connection) {
        this.connection = connection;
    }

    public void crearHistoriaClinica(HistoriaClinica historiaClinica) throws SQLException {
        String sql = "INSERT INTO HistoriaClinica (numHistoricaClinica, paciente) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, historiaClinica.getNumHistoricaClinica());
            pstmt.setString(2, historiaClinica.getPaciente());
            pstmt.executeUpdate();
        }
    }
}
