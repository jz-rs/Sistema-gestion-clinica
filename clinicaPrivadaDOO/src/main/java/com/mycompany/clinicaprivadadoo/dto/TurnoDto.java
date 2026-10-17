/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaprivadadoo.dto;

import java.time.LocalDate;

public class TurnoDto {
    private int nro;
    private LocalDate fecha;

    public TurnoDto(int nro, LocalDate fecha) {
        this.nro = nro;
        this.fecha = fecha;
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

    @Override
    public String toString() {
        return "TurnoDto{" +
                "nro=" + nro +
                ", fecha=" + fecha +
                '}';
    }
}
