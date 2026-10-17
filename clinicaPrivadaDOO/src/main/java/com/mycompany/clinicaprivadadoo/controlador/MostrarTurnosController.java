package com.mycompany.clinicaprivadadoo.controlador;

import com.mycompany.clinicaprivadadoo.dao.ConexionSql;
import com.mycompany.clinicaprivadadoo.dao.TurnoDao;
import com.mycompany.clinicaprivadadoo.dto.TurnoDetalleDto;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.List;

public class MostrarTurnosController {

    @FXML
    private TableView<TurnoDetalleDto> tablaTurnos;

    @FXML
    private TableColumn<TurnoDetalleDto, Integer> columnaId;

    @FXML
    private TableColumn<TurnoDetalleDto, Integer> columnaNro;

    @FXML
    private TableColumn<TurnoDetalleDto, LocalDate> columnaFecha;

    @FXML
    private TableColumn<TurnoDetalleDto, String> columnaPaciente;

    @FXML
    private TableColumn<TurnoDetalleDto, String> columnaDniPaciente; // Nueva columna para el DNI del paciente

    @FXML
    private TableColumn<TurnoDetalleDto, String> columnaServicio;

    private TurnoDao turnoDao;

    @FXML
    public void initialize() {
        // Inicializar TurnoDao con la conexión a la base de datos
        turnoDao = new TurnoDao(ConexionSql.getInstance().getConnection());

        // Configurar columnas
        configurarColumnas();

        // Cargar datos desde la base de datos
        cargarDatos();
    }

    private void configurarColumnas() {
        columnaId.setCellValueFactory(cellData -> 
            new SimpleObjectProperty<>(cellData.getValue().getId()));
        columnaNro.setCellValueFactory(cellData -> 
            new SimpleObjectProperty<>(cellData.getValue().getNro()));
        columnaFecha.setCellValueFactory(cellData -> 
            new SimpleObjectProperty<>(cellData.getValue().getFecha()));
        columnaPaciente.setCellValueFactory(cellData -> 
            new SimpleObjectProperty<>(cellData.getValue().getNombrePaciente()));
        columnaDniPaciente.setCellValueFactory(cellData -> 
            new SimpleObjectProperty<>(cellData.getValue().getDniPaciente())); // Asigna el DNI del paciente
        columnaServicio.setCellValueFactory(cellData -> 
            new SimpleObjectProperty<>(cellData.getValue().getNombreServicio()));
    }

    private void cargarDatos() {
        try {
            // Llamada al método que devuelve TurnoDetalleDto
            List<TurnoDetalleDto> turnosConDetalles = turnoDao.obtenerTurnosConDetalles();
            tablaTurnos.getItems().setAll(turnosConDetalles);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}
