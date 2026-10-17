package com.mycompany.clinicaprivadadoo.dao;

import com.mycompany.clinicaprivadadoo.modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenDeConsultaDao {

    private final Connection connection;

    public OrdenDeConsultaDao() {
        this.connection = ConexionSql.getInstance().getConnection();
    }

    // Insertar Orden con objetos
    public void insertarOrdenDeConsulta(Turno turno, Paciente paciente, Medico medico, Servicio servicio, boolean estado) {
        String sql = "INSERT INTO OrdenDeConsulta (turno, paciente, medico, servicio, estado) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, turno.getId());
            pstmt.setString(2, paciente.getHistoriaClinica().getNumHistoricaClinica()); // Actualización
            pstmt.setString(3, medico.getLegajo());
            pstmt.setString(4, servicio.getNombre());
            pstmt.setBoolean(5, estado);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la orden de consulta: " + e.getMessage(), e);
        }
    }

    // Obtener todas las órdenes
    public List<OrdenDeConsulta> obtenerOrdenesDeConsulta() {
        List<OrdenDeConsulta> ordenes = new ArrayList<>();
        String sql = "SELECT id, turno, paciente, medico, servicio, estado FROM OrdenDeConsulta";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                HistoriaClinica historiaClinica = new HistoriaClinica(rs.getString("paciente"), null);
                Paciente paciente = new Paciente(historiaClinica);

                OrdenDeConsulta orden = new OrdenDeConsulta(
                        rs.getInt("id"),
                        new Turno(rs.getInt("turno")),
                        paciente,
                        new Medico(rs.getString("medico")),
                        new Servicio(rs.getString("servicio")),
                        rs.getBoolean("estado")
                );
                ordenes.add(orden);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las órdenes de consulta: " + e.getMessage(), e);
        }
        return ordenes;
    }

    // Obtener orden por ID
    public OrdenDeConsulta obtenerOrdenPorId(int id) {
        String sql = "SELECT id, turno, paciente, medico, servicio, estado FROM OrdenDeConsulta WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    HistoriaClinica historiaClinica = new HistoriaClinica(rs.getString("paciente"), null);
                    Paciente paciente = new Paciente(historiaClinica);

                    return new OrdenDeConsulta(
                            rs.getInt("id"),
                            new Turno(rs.getInt("turno")),
                            paciente,
                            new Medico(rs.getString("medico")),
                            new Servicio(rs.getString("servicio")),
                            rs.getBoolean("estado")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la orden de consulta por ID: " + e.getMessage(), e);
        }
        return null;
    }

    // Actualizar Orden
    public void actualizarOrdenDeConsulta(OrdenDeConsulta orden) {
        String sql = "UPDATE OrdenDeConsulta SET turno = ?, paciente = ?, medico = ?, servicio = ?, estado = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orden.getTurno().getId());
            pstmt.setString(2, orden.getPaciente().getHistoriaClinica().getNumHistoricaClinica()); // Actualización
            pstmt.setString(3, orden.getMedico().getLegajo());
            pstmt.setString(4, orden.getServicio().getNombre());
            pstmt.setBoolean(5, orden.isEstado());
            pstmt.setInt(6, orden.getoId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la orden de consulta: " + e.getMessage(), e);
        }
    }

    // Eliminar Orden
    public void eliminarOrdenDeConsulta(int id) {
        String sql = "DELETE FROM OrdenDeConsulta WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la orden de consulta: " + e.getMessage(), e);
        }
    }
}
