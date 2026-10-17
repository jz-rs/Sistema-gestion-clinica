package com.mycompany.clinicaprivadadoo.dto;

import java.time.LocalDate;

public class TurnoDetalleDto {

    private int id;
    private int nro;
    private LocalDate fecha;
    private String nombrePaciente;
    private String dniPaciente;
    private String nombreServicio;

    // Constructor corregido (incluye dniPaciente como parámetro)
    public TurnoDetalleDto(int id, int nro, LocalDate fecha, String nombrePaciente, String dniPaciente, String nombreServicio) {
        this.id = id;
        this.nro = nro;
        this.fecha = fecha;
        this.nombrePaciente = nombrePaciente;
        this.dniPaciente = dniPaciente; // Asignación corregida
        this.nombreServicio = nombreServicio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    @Override
    public String toString() {
        return "TurnoDetalleDto{"
                + "id=" + id
                + ", nro=" + nro
                + ", fecha=" + fecha
                + ", nombrePaciente='" + nombrePaciente + '\''
                + ", dniPaciente='" + dniPaciente + '\''
                + ", nombreServicio='" + nombreServicio + '\''
                + '}';
    }
}
