package com.mycompany.clinicaprivadadoo.dao;

import com.mycompany.clinicaprivadadoo.modelo.Recepcionista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecepcionistaDao {
    private final Connection connection;

    public RecepcionistaDao() {
        this.connection = ConexionSql.getInstance().getConnection();
    }

    public Recepcionista obtenerPorLegajo(String legajo) {
        Recepcionista recepcionista = null;
        String sql = "SELECT p.dni, p.nombre, p.apellido, p.fechaNac, r.turno, r.fechaIngreso, r.fechaEgreso " +
                     "FROM Persona p " +
                     "JOIN Recepcionista r ON p.dni = r.persona_dni " +
                     "WHERE r.legajo = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, legajo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String fechaNac = rs.getString("fechaNac");
                String turno = rs.getString("turno");
                String fechaIngreso = rs.getString("fechaIngreso");
                String fechaEgreso = rs.getString("fechaEgreso");

                recepcionista = new Recepcionista(nombre, apellido, fechaNac, dni, null, legajo, turno, fechaIngreso, fechaEgreso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recepcionista;
    }

    public void crear(Recepcionista recepcionista) {
        String sqlPersona = "INSERT INTO Persona (dni, nombre, apellido, fechaNac) VALUES (?, ?, ?, ?)";
        String sqlRecepcionista = "INSERT INTO Recepcionista (legajo, turno, fechaIngreso, fechaEgreso, persona_dni) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmtPersona = connection.prepareStatement(sqlPersona);
             PreparedStatement pstmtRecepcionista = connection.prepareStatement(sqlRecepcionista)) {
            
            connection.setAutoCommit(false);

            // Insertar en Persona
            pstmtPersona.setString(1, recepcionista.getDni());
            pstmtPersona.setString(2, recepcionista.getNombre());
            pstmtPersona.setString(3, recepcionista.getApellido());
            pstmtPersona.setString(4, recepcionista.getFechaNac());
            pstmtPersona.executeUpdate();

            // Insertar en Recepcionista
            pstmtRecepcionista.setString(1, recepcionista.getLegajo());
            pstmtRecepcionista.setString(2, recepcionista.getTurno());
            pstmtRecepcionista.setString(3, recepcionista.getFechaIngreso());
            pstmtRecepcionista.setString(4, recepcionista.getFechaEgreso());
            pstmtRecepcionista.setString(5, recepcionista.getDni());
            pstmtRecepcionista.executeUpdate();

            connection.commit(); // Confirmar la transacción

        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir los cambios en caso de error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true); // Volver al modo de autocommit
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void actualizar(Recepcionista recepcionista) {
        String sql = "UPDATE Recepcionista SET turno = ?, fechaIngreso = ?, fechaEgreso = ? WHERE legajo = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, recepcionista.getTurno());
            pstmt.setString(2, recepcionista.getFechaIngreso());
            pstmt.setString(3, recepcionista.getFechaEgreso());
            pstmt.setString(4, recepcionista.getLegajo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(String legajo) {
        String sql = "DELETE FROM Recepcionista WHERE legajo = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, legajo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Recepcionista> obtenerTodos() {
        List<Recepcionista> recepcionistas = new ArrayList<>();
        String sql = "SELECT p.dni, p.nombre, p.apellido, p.fechaNac, r.legajo, r.turno, r.fechaIngreso, r.fechaEgreso " +
                     "FROM Persona p " +
                     "JOIN Recepcionista r ON p.dni = r.persona_dni";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String fechaNac = rs.getString("fechaNac");
                String legajo = rs.getString("legajo");
                String turno = rs.getString("turno");
                String fechaIngreso = rs.getString("fechaIngreso");
                String fechaEgreso = rs.getString("fechaEgreso");

                Recepcionista recepcionista = new Recepcionista(nombre, apellido, fechaNac, dni, null, legajo, turno, fechaIngreso, fechaEgreso);
                recepcionistas.add(recepcionista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recepcionistas;
    }
}
