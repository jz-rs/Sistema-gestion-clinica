package com.mycompany.clinicaprivadadoo.dao;

import com.mycompany.clinicaprivadadoo.dto.TurnoDetalleDto;
import com.mycompany.clinicaprivadadoo.modelo.Turno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TurnoDao implements Dao<TurnoDetalleDto> {
    private final Connection connection;

    public TurnoDao(Connection connection) {
        this.connection = connection;
    }

    // Método para crear un nuevo turno
    public void crearTurno(Turno turno) throws SQLException {
        String sql = "INSERT INTO Turno (nro, fecha) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, turno.getNro());
            stmt.setString(2, turno.getFecha().toString());
            stmt.executeUpdate();
        }
    }

    // Nuevo método: Obtener turnos con detalles (incluye paciente, servicio y DNI)
    public List<TurnoDetalleDto> obtenerTurnosConDetalles() throws SQLException {
        List<TurnoDetalleDto> detalles = new ArrayList<>();

        String sql = "SELECT t.id AS TurnoID, t.nro AS NumeroTurno, t.fecha AS FechaTurno, " +
                     "p.nombre AS NombrePaciente, p.apellido AS ApellidoPaciente, " +
                     "p.dni AS DNIPaciente, " +
                     "s.nombre AS NombreServicio " +
                     "FROM Turno t " +
                     "JOIN OrdenDeConsulta oc ON t.id = oc.turno " +
                     "JOIN Paciente pa ON oc.paciente = pa.numHistoricaClinica " +
                     "JOIN Persona p ON pa.persona_dni = p.dni " +
                     "JOIN Servicio s ON oc.servicio = s.nombre " +
                     "ORDER BY t.fecha, t.nro";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("TurnoID");
                int nro = rs.getInt("NumeroTurno");
                LocalDate fecha = LocalDate.parse(rs.getString("FechaTurno"));
                String nombrePaciente = rs.getString("NombrePaciente") + " " + rs.getString("ApellidoPaciente");
                String dniPaciente = rs.getString("DNIPaciente");  // Obtener el DNI del paciente
                String servicio = rs.getString("NombreServicio");

                // Agregar al DTO
                detalles.add(new TurnoDetalleDto(id, nro, fecha, nombrePaciente, dniPaciente, servicio));
            }
        }
        return detalles;
    }

    // Obtener el último turno del día
    public int obtenerUltimoTurnoDelDia(LocalDate fecha) throws SQLException {
        String sql = "SELECT MAX(nro) AS ultimoTurno FROM Turno WHERE fecha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fecha.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ultimoTurno");
                }
            }
        }
        return 0;
    }

    // Generar un nuevo turno
    public Turno generarNuevoTurno(LocalDate fecha) throws SQLException {
        String sqlSelect = "SELECT MAX(nro) AS ultimo_turno FROM Turno WHERE fecha = ?";
        String sqlInsert = "INSERT INTO Turno (nro, fecha) VALUES (?, ?)";
        int nuevoNumeroTurno = 1;
        int idGenerado = -1;

        try (PreparedStatement stmtSelect = connection.prepareStatement(sqlSelect)) {
            stmtSelect.setString(1, fecha.toString());
            try (ResultSet rs = stmtSelect.executeQuery()) {
                if (rs.next() && rs.getInt("ultimo_turno") > 0) {
                    nuevoNumeroTurno = rs.getInt("ultimo_turno") + 1;
                }
            }
        }

        try (PreparedStatement stmtInsert = connection.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmtInsert.setInt(1, nuevoNumeroTurno);
            stmtInsert.setString(2, fecha.toString());
            stmtInsert.executeUpdate();

            try (ResultSet generatedKeys = stmtInsert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idGenerado = generatedKeys.getInt(1);
                }
            }
        }

        if (idGenerado == -1) {
            throw new SQLException("No se pudo generar el turno correctamente, ID no generado.");
        }

        return new Turno(idGenerado, nuevoNumeroTurno, fecha);
    }

    @Override
    public List<TurnoDetalleDto> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean insertar(TurnoDetalleDto dto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean modificar(TurnoDetalleDto dto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean borrar(TurnoDetalleDto dto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TurnoDetalleDto buscar(TurnoDetalleDto dto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TurnoDetalleDto> listarPorCriterio(TurnoDetalleDto dto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
