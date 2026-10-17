/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaprivadadoo.modelo;

public class HistoriaClinica {
    private String numHistoricaClinica;
    private String paciente;

    public HistoriaClinica(String numHistoricaClinica, String paciente) {
        this.numHistoricaClinica = numHistoricaClinica;
        this.paciente = paciente;
    }

    public String getNumHistoricaClinica() {
        return numHistoricaClinica;
    }

    public void setNumHistoricaClinica(String numHistoricaClinica) {
        this.numHistoricaClinica = numHistoricaClinica;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
}
