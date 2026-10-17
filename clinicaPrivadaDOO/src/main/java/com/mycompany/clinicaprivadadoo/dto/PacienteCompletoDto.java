package com.mycompany.clinicaprivadadoo.dto;

import com.mycompany.clinicaprivadadoo.modelo.HistoriaClinica;

public class PacienteCompletoDto {
    private String dni;
    private String nombre;
    private String apellido;
    private String fechaNac;
    private HistoriaClinica historiaClinica; // Objeto HistoriaClinica
    private String jefeGrupoFamiliar;
    private String calle;
    private int nro;
    private String barrio;

    public PacienteCompletoDto(String dni, String nombre, String apellido, String fechaNac,
                               HistoriaClinica historiaClinica, String jefeGrupoFamiliar,
                               String calle, int nro, String barrio) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.historiaClinica = historiaClinica;
        this.jefeGrupoFamiliar = jefeGrupoFamiliar;
        this.calle = calle;
        this.nro = nro;
        this.barrio = barrio;
    }

    // Getters y Setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getFechaNac() { return fechaNac; }
    public void setFechaNac(String fechaNac) { this.fechaNac = fechaNac; }

    public HistoriaClinica getHistoriaClinica() { return historiaClinica; }
    public void setHistoriaClinica(HistoriaClinica historiaClinica) { this.historiaClinica = historiaClinica; }

    public String getJefeGrupoFamiliar() { return jefeGrupoFamiliar; }
    public void setJefeGrupoFamiliar(String jefeGrupoFamiliar) { this.jefeGrupoFamiliar = jefeGrupoFamiliar; }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public int getNro() { return nro; }
    public void setNro(int nro) { this.nro = nro; }

    public String getBarrio() { return barrio; }
    public void setBarrio(String barrio) { this.barrio = barrio; }
}
