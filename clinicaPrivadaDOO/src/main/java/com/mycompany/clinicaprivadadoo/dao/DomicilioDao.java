package com.mycompany.clinicaprivadadoo.dao;

import com.mycompany.clinicaprivadadoo.modelo.Domicilio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DomicilioDao {
    private final Connection connection;

    public DomicilioDao() {
        this.connection = ConexionSql.getInstance().getConnection();
    }

    public void crear(Domicilio domicilio) {
        String sql = "INSERT INTO Domicilio (calle, nro, barrio, piso, depto, cp, persona_dni) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, domicilio.getCalle());
            pstmt.setInt(2, domicilio.getNumero());
            pstmt.setString(3, domicilio.getBarrio());
            pstmt.setInt(4, domicilio.getPiso());
            pstmt.setString(5, domicilio.getDepartamento());
            pstmt.setString(6, domicilio.getCodigoPostal());
            pstmt.setString(7, domicilio.getPersonaDni());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
