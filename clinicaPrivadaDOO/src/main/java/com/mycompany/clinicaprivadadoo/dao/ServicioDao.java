package com.mycompany.clinicaprivadadoo.dao;

import com.mycompany.clinicaprivadadoo.dto.ServicioDto;
import com.mycompany.clinicaprivadadoo.modelo.Servicio;
import com.mycompany.clinicaprivadadoo.modelo.TipoDeConsulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioDao implements Dao<ServicioDto>{
    private final Connection connection;

    public ServicioDao() {
        this.connection = ConexionSql.getInstance().getConnection();
    }

    public void crearServicio(Servicio servicio) throws SQLException {
        String sql = "INSERT INTO Servicio (nombre, descripcion, costo) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, servicio.getNombre());
            pstmt.setString(2, servicio.getDescripcion());
            pstmt.setFloat(3, servicio.getCosto());
            pstmt.executeUpdate();
        }
    }

    public Servicio obtenerServicioPorNombre(String nombre) {
        Servicio servicio = null;
        String sql = "SELECT * FROM Servicio WHERE nombre = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                servicio = new Servicio(
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getFloat("costo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicio;
    }

    public List<Servicio> obtenerServicios() throws SQLException {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT * FROM Servicio";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                float costo = rs.getFloat("costo");

                Servicio servicio = new Servicio(nombre, descripcion, costo);
                servicios.add(servicio);
            }
        }
        return servicios;
    }

    /**
     * Método para obtener los tipos de consulta asociados a un servicio.
     */
    public List<TipoDeConsulta> obtenerTiposDeConsultaPorServicio(String servicioNombre) {
        List<TipoDeConsulta> tiposDeConsulta = new ArrayList<>();
        String sql = "SELECT tc.tipo, tc.costo FROM TipoDeConsulta tc " +
                     "JOIN ServicioTipoDeConsulta stc ON tc.tipo = stc.tipoConsulta_tipo " +
                     "WHERE stc.servicio_nombre = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, servicioNombre);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TipoDeConsulta tipoDeConsulta = new TipoDeConsulta(
                    rs.getString("tipo"),
                    rs.getFloat("costo")
                );
                tiposDeConsulta.add(tipoDeConsulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los tipos de consulta por servicio: " + e.getMessage(), e);
        }
        return tiposDeConsulta;
    }

    @Override
    public List<ServicioDto> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertar(ServicioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificar(ServicioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean borrar(ServicioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ServicioDto buscar(ServicioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ServicioDto> listarPorCriterio(ServicioDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
