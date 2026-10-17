package com.mycompany.clinicaprivadadoo.dto;

import java.util.List;

public class RecepcionistaDto {
    private String nombre;
    private String apellido;
    private String fechaNac;
    private String dni;
    private List<String> domicilio;
    private String legajo;
    private String turno;
    private String fechaIngreso;
    private String fechaEgreso;

    // Constructor
    public RecepcionistaDto(String nombre, String apellido, String fechaNac, String dni, List<String> domicilio,
                            String legajo, String turno, String fechaIngreso, String fechaEgreso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.dni = dni;
        this.domicilio = domicilio;
        this.legajo = legajo;
        this.turno = turno;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<String> getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(List<String> domicilio) {
        this.domicilio = domicilio;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(String fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }
}
