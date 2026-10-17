package com.mycompany.clinicaprivadadoo.modelo;

import java.time.LocalDate;

public class Turno {
    private int oid;
    private int nro;
    private LocalDate fecha;

    // Constructor completo
    public Turno(int oid, int nro, LocalDate fecha) {
        this.oid = oid;
        this.nro = nro;
        this.fecha = fecha;
    }

    // Constructor con solo ID
    public Turno(int oid) {
        this.oid = oid;
        this.nro = 0;             // Valor por defecto
        this.fecha = LocalDate.now(); // Fecha por defecto
    }

    // Getters y Setters
    public int getId() {
        return oid;
    }

    public int getNro() {
        return nro;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.oid = oid;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Turno{" + "oid=" + oid + ", nro=" + nro + ", fecha=" + fecha + '}';
    }
}
