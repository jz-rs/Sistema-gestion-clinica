package com.mycompany.clinicaprivadadoo.dao;

import com.mycompany.clinicaprivadadoo.dto.TipoDeConsultaDto;
import com.mycompany.clinicaprivadadoo.modelo.TipoDeConsulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TipoDeConsultaDao implements Dao<TipoDeConsultaDto>{
    private final Connection connection;

    public TipoDeConsultaDao() {
        this.connection = ConexionSql.getInstance().getConnection();
    }

    // Obtener todos los tipos de consulta
    public List<TipoDeConsulta> obtenerTodosLosTiposDeConsulta() {
        List<TipoDeConsulta> tiposDeConsulta = new ArrayList<>();
        String sql = "SELECT * FROM TipoDeConsulta";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                TipoDeConsulta tipoDeConsulta = new TipoDeConsulta(
                        rs.getString("tipo"),
                        rs.getFloat("costo")
                );
                tiposDeConsulta.add(tipoDeConsulta);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los tipos de consulta: " + e.getMessage());
            throw new RuntimeException("Error al obtener los tipos de consulta.", e);
        }
        return tiposDeConsulta;
    }

    // Obtener un tipo de consulta por nombre
    public Optional<TipoDeConsulta> obtenerPorNombre(String tipo) {
        String sql = "SELECT * FROM TipoDeConsulta WHERE tipo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tipo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                TipoDeConsulta tipoDeConsulta = new TipoDeConsulta(
                        rs.getString("tipo"),
                        rs.getFloat("costo")
                );
                return Optional.of(tipoDeConsulta);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el tipo de consulta por nombre: " + e.getMessage());
            throw new RuntimeException("Error al obtener el tipo de consulta por nombre.", e);
        }
        return Optional.empty();
    }

    // Obtener tipos de consulta por servicio
    public List<TipoDeConsulta> obtenerPorServicio(String nombreServicio) {
        List<TipoDeConsulta> tiposDeConsulta = new ArrayList<>();
        String sql = "SELECT tc.tipo, tc.costo FROM TipoDeConsulta tc " +
                     "INNER JOIN ServicioTipoDeConsulta stc ON tc.tipo = stc.tipoConsulta_tipo " +
                     "WHERE stc.servicio_nombre = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombreServicio);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TipoDeConsulta tipoDeConsulta = new TipoDeConsulta(
                        rs.getString("tipo"),
                        rs.getFloat("costo")
                );
                tiposDeConsulta.add(tipoDeConsulta);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los tipos de consulta por servicio: " + e.getMessage());
            throw new RuntimeException("Error al obtener los tipos de consulta por servicio.", e);
        }
        return tiposDeConsulta;
    }

    @Override
    public List<TipoDeConsultaDto> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertar(TipoDeConsultaDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificar(TipoDeConsultaDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean borrar(TipoDeConsultaDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TipoDeConsultaDto buscar(TipoDeConsultaDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TipoDeConsultaDto> listarPorCriterio(TipoDeConsultaDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
