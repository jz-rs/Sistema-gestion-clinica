package com.mycompany.clinicaprivadadoo.modelo;

import com.mycompany.clinicaprivadadoo.dao.ConexionSql;
import com.mycompany.clinicaprivadadoo.dao.PacienteDao;
import com.mycompany.clinicaprivadadoo.dao.TurnoDao;
import com.mycompany.clinicaprivadadoo.dao.OrdenDeConsultaDao;

public class Recepcionista extends Persona {
    private String legajo;
    private String turno;
    private String fechaIngreso;
    private String fechaEgreso;

    // Declaración de los DAOs
    private PacienteDao pacienteDao;
    private TurnoDao turnoDao;
    private OrdenDeConsultaDao ordenDeConsultaDao;

    public Recepcionista(String nombre, String apellido, String fechaNac, String dni, String legajo,
                         String turno, String fechaIngreso, String fechaEgreso) {
        super(nombre, apellido, fechaNac, dni, null);
        this.legajo = legajo;
        this.turno = turno;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;

        // Inicializamos los DAOs con la conexión Singleton
        this.pacienteDao = new PacienteDao();
        this.turnoDao = new TurnoDao(ConexionSql.getInstance().getConnection());
        this.ordenDeConsultaDao = new OrdenDeConsultaDao();
    }

    public Recepcionista(String nombre, String apellido, String fechaNac, String dni, Object object, String legajo, String turno, String fechaIngreso, String fechaEgreso) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método para consultar un paciente por DNI
    public Paciente consultarPaciente(String dni) {
        return pacienteDao.obtenerPorDni(dni);
    }

    // Método para registrar un nuevo paciente
    public void registrarNuevoPaciente() {
        System.out.println("Iniciar proceso de registro de nuevo paciente.");
    }

    // Método para generar una orden de consulta
    public void generarOrdenConsulta(Turno turno, Paciente paciente, Medico medico, Servicio servicio) {
        ordenDeConsultaDao.insertarOrdenDeConsulta(turno, paciente, medico, servicio, true);
        System.out.println("Orden de consulta generada correctamente.");
    }

    // Método auxiliar para preparar datos del paciente para generar una orden
    public String[] prepararDatosGenerarOrden(String resultado, String dni) {
        String resultadoLimpio = resultado.replace("Paciente encontrado: ", "").trim();
        String[] datos = resultadoLimpio.split(" ");
        return new String[]{datos[0] + " " + datos[1], dni};
    }

    // Getters y Setters adicionales
    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(String fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }
}
