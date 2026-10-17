package com.mycompany.clinicaprivadadoo.modelo;

public class Domicilio {
    private int oid;
    private String calle;
    private int numero;
    private String barrio;
    private int piso;
    private String departamento;
    private String codigoPostal;
    private String personaDni;

    // Constructor
    public Domicilio(String calle, int numero, String barrio, int piso, String departamento, String codigoPostal, String personaDni) {
        this.calle = calle;
        this.numero = numero;
        this.barrio = barrio;
        this.piso = piso;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
        this.personaDni = personaDni;
    }

    // Getters y Setters
    public int getId() {
        return oid;
    }

    public void setId(int oid) {
        this.oid = oid;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPersonaDni() {
        return personaDni;
    }

    public void setPersonaDni(String personaDni) {
        this.personaDni = personaDni;
    }
}