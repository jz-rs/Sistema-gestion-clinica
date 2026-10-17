package com.mycompany.clinicaprivadadoo.modelo;

import java.util.List;

public class Persona {
    private String dni;
    private String nombre;
    private String apellido;
    private String fechaNac;
    private List<Domicilio> domicilios;

    
       public Persona() {
        // Constructor vacío
    }
       
    // Constructor principal con lista de domicilios
    public Persona(String dni, String nombre, String apellido, String fechaNac, List<Domicilio> domicilios) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.domicilios = domicilios;
    }

    // Constructor secundario sin lista de domicilios
    public Persona(String dni, String nombre, String apellido, String fechaNac) {
        this(dni, nombre, apellido, fechaNac, null);
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

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

    public List<Domicilio> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(List<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }

    public String getFechaNacimiento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
