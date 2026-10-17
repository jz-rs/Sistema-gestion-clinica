package com.mycompany.clinicaprivadadoo.modelo;

import java.util.List;

public class Medico extends Persona {
    private String legajo;
    private Servicio servicio; // Cambio: tipo Servicio
    private boolean area;
    private String turno;
    private String fechaIngreso;
    private String fechaEgreso;

    // Constructor completo
    public Medico(String dni, String nombre, String apellido, String fechaNac, List<Domicilio> domicilios,
                  String legajo, Servicio servicio, boolean area, String turno, String fechaIngreso, String fechaEgreso) {
        super(dni, nombre, apellido, fechaNac, domicilios);
        this.legajo = legajo;
        this.servicio = servicio;
        this.area = area;
        this.turno = turno;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }

    // Constructor con solo el legajo
    public Medico(String legajo) {
        super(null, null, null, null, null);
        this.legajo = legajo;
    }

    // Constructor sin domicilios
    public Medico(String dni, String nombre, String apellido, String fechaNac,
                  String legajo, Servicio servicio, boolean area, String turno, String fechaIngreso, String fechaEgreso) {
        super(dni, nombre, apellido, fechaNac);
        this.legajo = legajo;
        this.servicio = servicio;
        this.area = area;
        this.turno = turno;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }

    // Getters y setters
    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public Servicio getServicio() { // Cambio: devuelve un Servicio
        return servicio;
    }

    public void setServicio(Servicio servicio) { // Cambio: recibe un Servicio
        this.servicio = servicio;
    }

    public boolean isArea() {
        return area;
    }

    public void setArea(boolean area) {
        this.area = area;
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

    @Override
    public String toString() {
        return "Medico{" +
                "legajo='" + legajo + '\'' +
                ", servicio=" + (servicio != null ? servicio.getNombre() : "null") + // Muestra nombre del servicio
                ", area=" + area +
                ", turno='" + turno + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaEgreso=" + fechaEgreso +
                ", dni='" + getDni() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", fechaNac='" + getFechaNac() + '\'' +
                '}';
    }
}
