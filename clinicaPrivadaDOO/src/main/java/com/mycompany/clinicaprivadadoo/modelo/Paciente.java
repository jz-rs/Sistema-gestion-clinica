package com.mycompany.clinicaprivadadoo.modelo;

public class Paciente extends Persona {
    private HistoriaClinica historiaClinica; // Cambiado a tipo HistoriaClinica
    private Paciente jefeGrupoFamiliar;

    // Constructor completo
    public Paciente(String dni, String nombre, String apellido, String fechaNac, HistoriaClinica historiaClinica, Paciente jefeGrupoFamiliar) {
        super(dni, nombre, apellido, fechaNac);
        this.historiaClinica = historiaClinica;
        this.jefeGrupoFamiliar = jefeGrupoFamiliar;
    }

    // Constructor con menos parámetros
    public Paciente(HistoriaClinica historiaClinica, Paciente jefeGrupoFamiliar, String dni) {
        super(dni, null, null, null);
        this.historiaClinica = historiaClinica;
        this.jefeGrupoFamiliar = jefeGrupoFamiliar;
    }

    // Constructor que acepta solo el objeto HistoriaClinica
    public Paciente(HistoriaClinica historiaClinica) {
        super(null, null, null, null);
        this.historiaClinica = historiaClinica;
        this.jefeGrupoFamiliar = null;
    }

    // Getter y Setter para historiaClinica
    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    // Getter y Setter para jefeGrupoFamiliar
    public Paciente getJefeGrupoFamiliar() {
        return jefeGrupoFamiliar;
    }

    public void setJefeGrupoFamiliar(Paciente jefeGrupoFamiliar) {
        this.jefeGrupoFamiliar = jefeGrupoFamiliar;
    }
}
