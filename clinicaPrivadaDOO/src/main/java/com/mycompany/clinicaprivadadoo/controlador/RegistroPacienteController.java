package com.mycompany.clinicaprivadadoo.controlador;

import com.mycompany.clinicaprivadadoo.dao.DomicilioDao;
import com.mycompany.clinicaprivadadoo.dao.PacienteDao;
import com.mycompany.clinicaprivadadoo.dao.PersonaDao;
import com.mycompany.clinicaprivadadoo.modelo.Domicilio;
import com.mycompany.clinicaprivadadoo.modelo.HistoriaClinica;
import com.mycompany.clinicaprivadadoo.modelo.Paciente;
import com.mycompany.clinicaprivadadoo.modelo.Persona;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegistroPacienteController {

    @FXML
    private TextField dniTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField apellidoTextField;

    @FXML
    private TextField fechaNacTextField;

    @FXML
    private TextField historiaClinicaTextField;

    @FXML
    private TextField jefeGrupoFamiliarTextField;

    @FXML
    private TextField calleTextField;

    @FXML
    private TextField numeroTextField;

    @FXML
    private TextField barrioTextField;

    @FXML
    private TextField pisoTextField;

    @FXML
    private TextField departamentoTextField;

    @FXML
    private TextField codigoPostalTextField;

    @FXML
    private Label resultLabel;

    private PacienteDao pacienteDao;
    private PersonaDao personaDao;
    private DomicilioDao domicilioDao;

    public RegistroPacienteController() {
        this.pacienteDao = new PacienteDao();
        this.personaDao = new PersonaDao();
        this.domicilioDao = new DomicilioDao();
    }

    private int calcularEdad(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(fechaNacimiento, formatter);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @FXML
    private void registrar() {
        try {
            String dni = dniTextField.getText();

            // Verificar si el paciente ya existe
            if (pacienteDao.obtenerPorDni(dni) != null) {
                resultLabel.setText("El paciente con DNI " + dni + " ya está registrado.");
                return;
            }

            String nombre = nombreTextField.getText();
            String apellido = apellidoTextField.getText();
            String fechaNac = fechaNacTextField.getText();
            String historiaClinicaId = historiaClinicaTextField.getText();

            // Validar jefe de grupo familiar si es menor de edad
            int edad = calcularEdad(fechaNac);
            Paciente jefeGrupoFamiliar = null;

            if (edad < 18) {
                String jefeDni = jefeGrupoFamiliarTextField.getText();
                jefeGrupoFamiliar = pacienteDao.obtenerPorDni(jefeDni);
                if (jefeGrupoFamiliar == null) {
                    resultLabel.setText("El jefe del grupo familiar no existe.");
                    return;
                }
            }

            // Crear HistoriaClinica
            HistoriaClinica historiaClinica = new HistoriaClinica(historiaClinicaId, dni);

            // Crear Domicilio
            String calle = calleTextField.getText();
            int numero = Integer.parseInt(numeroTextField.getText());
            String barrio = barrioTextField.getText();
            int piso = pisoTextField.getText().isEmpty() ? 0 : Integer.parseInt(pisoTextField.getText());
            String departamento = departamentoTextField.getText();
            String codigoPostal = codigoPostalTextField.getText();
            Domicilio domicilio = new Domicilio(calle, numero, barrio, piso, departamento, codigoPostal, dni);

            // Crear Persona
            List<Domicilio> domicilios = new ArrayList<>();
            domicilios.add(domicilio);
            Persona persona = new Persona(dni, nombre, apellido, fechaNac, domicilios);

            // Crear Paciente
            Paciente paciente = new Paciente(dni, nombre, apellido, fechaNac, historiaClinica, jefeGrupoFamiliar);

            // Guardar datos en la base de datos
            personaDao.crear(persona);
            domicilioDao.crear(domicilio);
            pacienteDao.crear(paciente);

            resultLabel.setText("Paciente registrado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error al registrar paciente: " + e.getMessage());
        }
    }
}
