package com.mycompany.clinicaprivadadoo.modelo;

import java.util.List;

public class Servicio {
    private String nombre;
    private String descripcion;
    private float costo;
    private List<TipoDeConsulta> tipoDeConsultas;

    public Servicio(String nombre, String descripcion, float costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public List<TipoDeConsulta> getTipoDeConsultas() {
        return tipoDeConsultas;
    }

    public void setTipoDeConsultas(List<TipoDeConsulta> tipoDeConsultas) {
        this.tipoDeConsultas = tipoDeConsultas;
    }

    public Servicio(String nombre) {
    this.nombre = nombre;
}
    
        // Nuevo método para calcular el costo total
    public double calcularCostoTotal(TipoDeConsulta tipoConsulta) {
        if (tipoConsulta == null) {
            throw new IllegalArgumentException("Tipo de consulta no puede ser nulo.");
        }
        return this.costo + tipoConsulta.getCosto();
    }

}
