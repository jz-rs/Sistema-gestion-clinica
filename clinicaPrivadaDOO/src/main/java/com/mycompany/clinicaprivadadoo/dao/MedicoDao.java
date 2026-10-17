package com.mycompany.clinicaprivadadoo.dao;

import com.mycompany.clinicaprivadadoo.modelo.Medico;
import com.mycompany.clinicaprivadadoo.modelo.Servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDao implements Dao<Medico> {

    private final Connection connection;

    public MedicoDao() {
        // Obtiene la conexión desde la clase singleton de manejo de conexión.
        this.connection = ConexionSql.getInstance().getConnection();
    }

    // Método para obtener un médico por su DNI
    public Medico obtenerPorDni(String dni) {
        Medico medico = null;
        String sql = "SELECT p.dni, p.nombre, p.apellido, p.fechaNac, m.legajo, " +
                     "m.servicio, m.area, m.turno, m.fechaIngreso, m.fechaEgreso " +
                     "FROM Persona p " +
                     "JOIN Medico m ON p.dni = m.dni " +
                     "WHERE p.dni = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    medico = construirMedico(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el médico por DNI: " + e.getMessage(), e);
        }
        return medico;
    }

    // Método para crear un nuevo médico
  public void crear(Medico medico) {
    String sql = "INSERT INTO Medico (legajo, servicio, area, turno, fechaIngreso, fechaEgreso, dni) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, medico.getLegajo());
        pstmt.setString(2, medico.getServicio().getNombre()); // Obtener nombre del Servicio
        pstmt.setBoolean(3, medico.isArea());
        pstmt.setString(4, medico.getTurno());
        pstmt.setString(5, medico.getFechaIngreso());
        pstmt.setString(6, medico.getFechaEgreso());
        pstmt.setString(7, medico.getDni());
        pstmt.executeUpdate();
        connection.commit();
    } catch (SQLException e) {
        try {
            connection.rollback();
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        e.printStackTrace();
        throw new RuntimeException("Error al crear el médico: " + e.getMessage(), e);
    }
}


    // Método para listar todos los médicos
    @Override
    public List<Medico> listarTodos() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT p.dni, p.nombre, p.apellido, p.fechaNac, m.legajo, " +
                     "m.servicio, m.area, m.turno, m.fechaIngreso, m.fechaEgreso " +
                     "FROM Persona p " +
                     "JOIN Medico m ON p.dni = m.dni";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                medicos.add(construirMedico(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar todos los médicos: " + e.getMessage(), e);
        }
        return medicos;
    }

    // Método para construir un objeto Medico a partir de un ResultSet
  private Medico construirMedico(ResultSet rs) throws SQLException {
    Servicio servicio = new Servicio(rs.getString("servicio")); // Construir Servicio con su nombre

    return new Medico(
            rs.getString("dni"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("fechaNac"),
            null, // Implementa lógica para domicilios si es necesario
            rs.getString("legajo"),
            servicio, // Pasar el objeto Servicio
            rs.getBoolean("area"),
            rs.getString("turno"),
            rs.getString("fechaIngreso"),
            rs.getString("fechaEgreso")
    );
}


    @Override
    public boolean insertar(Medico medico) {
        crear(medico); // Reutiliza el método crear
        return true;
    }

    @Override
    public boolean modificar(Medico medico) {
        // Implementar lógica de modificación si es necesario
        return false;
    }

    @Override
    public boolean borrar(Medico medico) {
        // Implementar lógica de borrado si es necesario
        return false;
    }

    @Override
    public Medico buscar(Medico medico) {
        return obtenerPorDni(medico.getDni());
    }

    @Override
    public List<Medico> listarPorCriterio(Medico medico) {
        // Implementar lógica para listar por criterio si es necesario
        return null;
    }
}
