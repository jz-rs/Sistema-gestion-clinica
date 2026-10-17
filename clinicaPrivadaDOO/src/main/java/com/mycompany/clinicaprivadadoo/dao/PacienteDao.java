package com.mycompany.clinicaprivadadoo.dao;

import com.mycompany.clinicaprivadadoo.dto.PacienteCompletoDto;
import com.mycompany.clinicaprivadadoo.modelo.HistoriaClinica;
import com.mycompany.clinicaprivadadoo.modelo.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {

    private final Connection connection;

    public PacienteDao() {
        this.connection = ConexionSql.getInstance().getConnection();
    }

    // Método auxiliar para obtener un objeto HistoriaClinica
    private HistoriaClinica obtenerHistoriaClinica(String numHistoricaClinica) {
        String sql = "SELECT * FROM HistoriaClinica WHERE numHistoricaClinica = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, numHistoricaClinica);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new HistoriaClinica(
                        rs.getString("numHistoricaClinica"),
                        rs.getString("paciente")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la historia clínica: " + e.getMessage(), e);
        }
        return null;
    }

    // Método para obtener un Paciente por su DNI
    public Paciente obtenerPorDni(String dni) {
        String sql = "SELECT p.dni, p.nombre, p.apellido, p.fechaNac, pa.numHistoricaClinica, pa.jefeGrupoFamiliar "
                + "FROM Persona p "
                + "JOIN Paciente pa ON p.dni = pa.persona_dni "
                + "WHERE p.dni = ?";

        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                HistoriaClinica historiaClinica = obtenerHistoriaClinica(rs.getString("numHistoricaClinica"));

                Paciente jefeGrupoFamiliar = null;
                String jefeDni = rs.getString("jefeGrupoFamiliar");
                if (jefeDni != null) {
                    jefeGrupoFamiliar = obtenerPorDni(jefeDni);
                }

                return new Paciente(
                        dni,
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("fechaNac"),
                        historiaClinica,
                        jefeGrupoFamiliar
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener paciente por DNI: " + e.getMessage(), e);
        }
        return null;
    }

    // Método para crear un Paciente
    public void crear(Paciente paciente) {
        String sql = "INSERT INTO Paciente (numHistoricaClinica, jefeGrupoFamiliar, persona_dni) VALUES (?, ?, ?)";

        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, paciente.getHistoriaClinica().getNumHistoricaClinica());
            pstmt.setString(2, paciente.getJefeGrupoFamiliar() != null ? paciente.getJefeGrupoFamiliar().getDni() : null);
            pstmt.setString(3, paciente.getDni());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear paciente: " + e.getMessage(), e);
        }
    }

    // Método para listar todos los pacientes
    public List<Paciente> listarTodosPacientes() {
        String sql = "SELECT p.dni, p.nombre, p.apellido, p.fechaNac, pa.numHistoricaClinica, pa.jefeGrupoFamiliar "
                + "FROM Persona p "
                + "LEFT JOIN Paciente pa ON pa.persona_dni = p.dni";

        List<Paciente> pacientes = new ArrayList<>();

        try ( PreparedStatement pstmt = connection.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                HistoriaClinica historiaClinica = obtenerHistoriaClinica(rs.getString("numHistoricaClinica"));

                Paciente jefeGrupoFamiliar = null;
                String jefeDni = rs.getString("jefeGrupoFamiliar");
                if (jefeDni != null) {
                    jefeGrupoFamiliar = obtenerPorDni(jefeDni);
                }

                pacientes.add(new Paciente(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("fechaNac"),
                        historiaClinica, // Objeto HistoriaClinica
                        jefeGrupoFamiliar
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar todos los pacientes: " + e.getMessage(), e);
        }
        return pacientes;
    }

    // Método para obtener el número de historia clínica por DNI
    public String obtenerNumHistoriaClinicaPorDni(String pacienteDni) {
        String sql = "SELECT numHistoricaClinica FROM Paciente WHERE persona_dni = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pacienteDni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("numHistoricaClinica");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el número de historia clínica por DNI: " + e.getMessage(), e);
        }
        return null;
    }

public List<PacienteCompletoDto> listarPacientesCompletos() {
    String sql = "SELECT p.dni, p.nombre, p.apellido, p.fechaNac, pa.numHistoricaClinica, " +
                 "pa.jefeGrupoFamiliar, d.calle, d.nro, d.barrio " +
                 "FROM Persona p " +
                 "LEFT JOIN Paciente pa ON p.dni = pa.persona_dni " +
                 "LEFT JOIN Domicilio d ON d.persona_dni = p.dni";

    List<PacienteCompletoDto> pacientes = new ArrayList<>();

    try (PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            // Crear HistoriaClinica a partir del número
            HistoriaClinica historiaClinica = null;
            String numHistoriaClinica = rs.getString("numHistoricaClinica");
            if (numHistoriaClinica != null) {
                historiaClinica = new HistoriaClinica(numHistoriaClinica, null);
            }

            // Obtener el nombre del jefe del grupo familiar
            String jefeNombre = null;
            String jefeDni = rs.getString("jefeGrupoFamiliar");
            if (jefeDni != null) {
                Paciente jefe = obtenerPorDni(jefeDni);
                if (jefe != null) {
                    jefeNombre = jefe.getNombre() + " " + jefe.getApellido();
                }
            }

            pacientes.add(new PacienteCompletoDto(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("fechaNac"),
                    historiaClinica, // Aquí pasamos el objeto
                    jefeNombre,
                    rs.getString("calle"),
                    rs.getInt("nro"),
                    rs.getString("barrio")
            ));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al listar pacientes completos: " + e.getMessage(), e);
    }
    return pacientes;
}


    private String obtenerNombreJefeGrupo(String jefeDni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
