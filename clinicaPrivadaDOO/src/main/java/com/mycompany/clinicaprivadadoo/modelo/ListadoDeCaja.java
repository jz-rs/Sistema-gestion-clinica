/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaprivadadoo.modelo;

public class ListadoDeCaja {
    private int id;
    private int datosConsulta;
    private float total;

    public ListadoDeCaja(int datosConsulta, float total) {
        this.datosConsulta = datosConsulta;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDatosConsulta() {
        return datosConsulta;
    }

    public void setDatosConsulta(int datosConsulta) {
        this.datosConsulta = datosConsulta;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ListadoDeCaja{" +
                "id=" + id +
                ", datosConsulta=" + datosConsulta +
                ", total=" + total +
                '}';
    }
}
