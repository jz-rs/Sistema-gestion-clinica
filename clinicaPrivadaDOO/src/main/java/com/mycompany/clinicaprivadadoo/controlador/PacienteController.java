package com.mycompany.clinicaprivadadoo.controlador;

import com.mycompany.clinicaprivadadoo.dao.PacienteDao;
import com.mycompany.clinicaprivadadoo.dto.PacienteCompletoDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PacienteController {

    @FXML
    private TableView<PacienteCompletoDto> tablePacientes;

    @FXML
    private TableColumn<PacienteCompletoDto, String> colDni;

    @FXML
    private TableColumn<PacienteCompletoDto, String> colNombre;

    @FXML
    private TableColumn<PacienteCompletoDto, String> colApellido;

    @FXML
    private TableColumn<PacienteCompletoDto, String> colFechaNac;

    @FXML
    private TableColumn<PacienteCompletoDto, String> colHistoriaClinica;

    @FXML
    private TableColumn<PacienteCompletoDto, String> colJefeGrupoFamiliar;

    @FXML
    private TableColumn<PacienteCompletoDto, String> colCalle;

    @FXML
    private TableColumn<PacienteCompletoDto, Integer> colNro;

    @FXML
    private TableColumn<PacienteCompletoDto, String> colBarrio;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtFechaNac;

    @FXML
    private TextField txtHistoriaClinica;

    @FXML
    private TextField txtJefeGrupoFamiliar;

    @FXML
    private TextField txtCalle;

    @FXML
    private TextField txtNro;

    @FXML
    private TextField txtBarrio;

    private ObservableList<PacienteCompletoDto> listaPacientes;

    private final PacienteDao pacienteDao;

    public PacienteController() {
        this.pacienteDao = new PacienteDao();
    }

    @FXML
    public void initialize() {
        // Configuración de columnas
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));

        // Ajustar para obtener numHistoriaClinica desde HistoriaClinica
        colHistoriaClinica.setCellValueFactory(cellData -> {
            if (cellData.getValue().getHistoriaClinica() != null) {
                return new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getHistoriaClinica().getNumHistoricaClinica());
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });

        colJefeGrupoFamiliar.setCellValueFactory(new PropertyValueFactory<>("jefeGrupoFamiliar"));
        colCalle.setCellValueFactory(new PropertyValueFactory<>("calle"));
        colNro.setCellValueFactory(new PropertyValueFactory<>("nro"));
        colBarrio.setCellValueFactory(new PropertyValueFactory<>("barrio"));

        // Cargar los datos
        listaPacientes = FXCollections.observableArrayList(cargarPacientes());
        tablePacientes.setItems(listaPacientes);

        // Listener para actualizar los detalles del paciente seleccionado
        tablePacientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mostrarDetalles(newSelection);
            }
        });
    }

    private ObservableList<PacienteCompletoDto> cargarPacientes() {
        return FXCollections.observableArrayList(pacienteDao.listarPacientesCompletos());
    }

    private void mostrarDetalles(PacienteCompletoDto paciente) {
        txtDni.setText(paciente.getDni() != null ? paciente.getDni() : "");
        txtNombre.setText(paciente.getNombre() != null ? paciente.getNombre() : "");
        txtApellido.setText(paciente.getApellido() != null ? paciente.getApellido() : "");
        txtFechaNac.setText(paciente.getFechaNac() != null ? paciente.getFechaNac() : "");

        // Mostrar numHistoriaClinica desde HistoriaClinica
        txtHistoriaClinica.setText(
                paciente.getHistoriaClinica() != null ? paciente.getHistoriaClinica().getNumHistoricaClinica() : ""
        );

        txtJefeGrupoFamiliar.setText(paciente.getJefeGrupoFamiliar() != null ? paciente.getJefeGrupoFamiliar() : "");
        txtCalle.setText(paciente.getCalle() != null ? paciente.getCalle() : "");
        txtNro.setText(String.valueOf(paciente.getNro()));
        txtBarrio.setText(paciente.getBarrio() != null ? paciente.getBarrio() : "");
    }
}
