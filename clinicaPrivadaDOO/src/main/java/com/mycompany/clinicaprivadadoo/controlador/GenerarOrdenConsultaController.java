package com.mycompany.clinicaprivadadoo.controlador;

import com.mycompany.clinicaprivadadoo.dao.*;
import com.mycompany.clinicaprivadadoo.modelo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.sql.Connection;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.Alert.AlertType;

public class GenerarOrdenConsultaController {

    @FXML
    private Label nombrePacienteLabel;

    @FXML
    private Label dniPacienteLabel;

    @FXML
    private Label fechaLabel; // Nuevo Label para la fecha

    @FXML
    private ComboBox<String> servicioComboBox;

    @FXML
    private ComboBox<String> tipoConsultaComboBox;

    @FXML
    private ComboBox<String> medicoComboBox;

    @FXML
    private Label costoTotalLabel;

    private final ServicioDao servicioDao;
    private final TipoDeConsultaDao tipoDeConsultaDao;
    private final MedicoDao medicoDao;
    private final PacienteDao pacienteDao;

    public GenerarOrdenConsultaController() {
        servicioDao = new ServicioDao();
        tipoDeConsultaDao = new TipoDeConsultaDao();
        medicoDao = new MedicoDao();
        pacienteDao = new PacienteDao();
    }

    @FXML
    private void initialize() {
        cargarServicios();
        agregarListeners();
        mostrarFechaActual(); // Mostrar la fecha actual al inicializar
    }

    private void mostrarFechaActual() {
        LocalDate fechaHoy = LocalDate.now();
        fechaLabel.setText(fechaHoy.toString()); // Mostrar la fecha en formato YYYY-MM-DD
    }

    private void cargarServicios() {
        try {
            List<Servicio> servicios = servicioDao.obtenerServicios();
            servicioComboBox.getItems().clear();
            servicios.forEach(servicio -> servicioComboBox.getItems().add(servicio.getNombre()));
        } catch (Exception e) {
            mostrarError("Error al cargar los servicios", e);
        }
    }

    private void cargarTiposDeConsulta(String servicioSeleccionado) {
        try {
            tipoConsultaComboBox.getItems().clear();
            List<TipoDeConsulta> tiposDeConsulta = tipoDeConsultaDao.obtenerPorServicio(servicioSeleccionado);
            tiposDeConsulta.forEach(tipo -> tipoConsultaComboBox.getItems().add(tipo.getTipo()));
        } catch (Exception e) {
            mostrarError("Error al cargar los tipos de consulta", e);
        }
    }
private void cargarMedicosPorServicio(String servicioSeleccionado) {
    try {
        List<Medico> medicos = medicoDao.listarTodos();
        List<Medico> medicosFiltrados = medicos.stream()
                .filter(medico -> medico.getServicio().getNombre().equalsIgnoreCase(servicioSeleccionado))
                .collect(Collectors.toList());

        medicoComboBox.getItems().clear();
        medicosFiltrados.forEach(medico -> 
            medicoComboBox.getItems().add(medico.getLegajo() + " " + medico.getNombre() + " " + medico.getApellido()));
    } catch (Exception e) {
        mostrarError("Error al cargar los médicos", e);
    }
}


private void calcularCostoTotal() {
    String servicioSeleccionado = servicioComboBox.getValue();
    String tipoConsultaSeleccionado = tipoConsultaComboBox.getValue();

    if (servicioSeleccionado != null && tipoConsultaSeleccionado != null) {
        try {
            Servicio servicio = servicioDao.obtenerServicioPorNombre(servicioSeleccionado);
            TipoDeConsulta tipoDeConsulta = tipoDeConsultaDao.obtenerPorNombre(tipoConsultaSeleccionado).orElse(null);

            if (servicio == null || tipoDeConsulta == null) {
                costoTotalLabel.setText("Datos no encontrados");
                return;
            }

            // Delegamos el cálculo a la clase Servicio
            double costoTotal = servicio.calcularCostoTotal(tipoDeConsulta);
            costoTotalLabel.setText("Costo Total: $" + costoTotal);
        } catch (Exception e) {
            mostrarError("Error al calcular el costo total", e);
            costoTotalLabel.setText("Error en el cálculo");
        }
    } else {
        costoTotalLabel.setText("$0.0");
    }
}


    private void agregarListeners() {
        servicioComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarTiposDeConsulta(newValue);
                cargarMedicosPorServicio(newValue);
                calcularCostoTotal();
            }
        });

        tipoConsultaComboBox.valueProperty().addListener((observable, oldValue, newValue) -> calcularCostoTotal());
    }

    public void setPacienteDatos(String nombre, String dni) {
        nombrePacienteLabel.setText(nombre);
        dniPacienteLabel.setText(dni);
    }

@FXML
private void guardarOrden() {
    String servicioSeleccionado = servicioComboBox.getValue();
    String tipoConsultaSeleccionado = tipoConsultaComboBox.getValue();
    String medicoSeleccionado = medicoComboBox.getValue();

    if (servicioSeleccionado != null && tipoConsultaSeleccionado != null && medicoSeleccionado != null) {
        try {
            LocalDate fechaActual = LocalDate.now(); // Fecha actual

            // Crear una instancia de TurnoDao
            Connection connection = ConexionSql.getInstance().getConnection();
            TurnoDao turnoDao = new TurnoDao(connection);

            // Generar un nuevo turno
            Turno nuevoTurno = turnoDao.generarNuevoTurno(fechaActual);

            // Obtener el DNI del paciente
            String pacienteDni = dniPacienteLabel.getText();

            // Obtener el número de historia clínica y crear HistoriaClinica
            String numHistoriaClinica = pacienteDao.obtenerNumHistoriaClinicaPorDni(pacienteDni);

            if (numHistoriaClinica == null) {
                mostrarError("El número de historia clínica no se encontró para el paciente con DNI: " + pacienteDni, null);
                return;
            }

            HistoriaClinica historiaClinica = new HistoriaClinica(numHistoriaClinica, pacienteDni);
            Paciente paciente = new Paciente(historiaClinica); // Paciente con HistoriaClinica

            // Crear el objeto Medico con el legajo
            String legajoMedico = medicoSeleccionado.split(" ")[0];
            Medico medico = new Medico(legajoMedico);

            // Crear el objeto Servicio
            Servicio servicio = new Servicio(servicioSeleccionado);

            // Insertar la orden en la base de datos
            OrdenDeConsultaDao ordenDeConsultaDao = new OrdenDeConsultaDao();
            ordenDeConsultaDao.insertarOrdenDeConsulta(nuevoTurno, paciente, medico, servicio, true);

            // Mostrar mensaje de éxito
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registro Exitoso");
            alert.setHeaderText(null);
            alert.setContentText("La orden de consulta se registró exitosamente para el paciente con DNI: "
                    + pacienteDni + "\nTurno asignado: " + nuevoTurno.getNro());
            alert.showAndWait();

            dniPacienteLabel.getScene().getWindow().hide();
        } catch (Exception e) {
            mostrarError("Error al guardar la orden", e);
            mostrarAlertaError();
        }
    } else {
        mostrarAlertaAdvertencia();
    }
}

// Métodos de alerta para reutilización
private void mostrarAlertaError() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Ocurrió un error al guardar la orden. Por favor, inténtelo nuevamente.");
    alert.showAndWait();
}

private void mostrarAlertaAdvertencia() {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Campos Incompletos");
    alert.setHeaderText(null);
    alert.setContentText("Debe seleccionar un servicio, un tipo de consulta y un médico antes de guardar.");
    alert.showAndWait();
}


    private void mostrarError(String mensaje, Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
        System.out.println(mensaje);
    }
}
