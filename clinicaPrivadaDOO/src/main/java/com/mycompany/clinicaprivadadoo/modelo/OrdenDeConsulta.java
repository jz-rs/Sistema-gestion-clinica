package com.mycompany.clinicaprivadadoo.modelo;

public class OrdenDeConsulta {
    private int oid;
    private Turno turno;          // Objeto Turno
    private Paciente paciente;    // Objeto Paciente
    private Medico medico;        // Objeto Medico
    private Servicio servicio;    // Objeto Servicio
    private boolean estado;

    // Constructor principal
    public OrdenDeConsulta(int oid, Turno turno, Paciente paciente, Medico medico, Servicio servicio, boolean estado) {
        this.oid = oid;
        this.turno = turno;
        this.paciente = paciente;
        this.medico = medico;
        this.servicio = servicio;
        this.estado = estado;
    }

    // Constructor alternativo para compatibilidad
    public OrdenDeConsulta(int oid, int turnoId, String pacienteHistoriaId, String medicoId, String servicioId, boolean estado) {
        this.oid = oid;
        this.turno = new Turno(turnoId);
        this.paciente = new Paciente(new HistoriaClinica(pacienteHistoriaId, null)); // HistoriaClinica en lugar de String
        this.medico = new Medico(medicoId);
        this.servicio = new Servicio(servicioId);
        this.estado = estado;
    }

    // Getters y Setters
    public int getoId() {
        return oid;
    }

    public void setId(int oid) {
        this.oid = oid;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public int getTurnoId() {
        return turno.getId();  // Devuelve solo el ID
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getPacienteId() {
        return paciente.getHistoriaClinica().getNumHistoricaClinica(); // Ajuste aquí
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getMedicoId() {
        return medico.getLegajo();
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getServicioNombre() {
        return servicio.getNombre();
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "OrdenDeConsulta{" +
                "oid=" + oid +
                ", turno=" + turno +
                ", paciente=" + paciente +
                ", medico=" + medico +
                ", servicio=" + servicio +
                ", estado=" + estado +
                '}';
    }
}
