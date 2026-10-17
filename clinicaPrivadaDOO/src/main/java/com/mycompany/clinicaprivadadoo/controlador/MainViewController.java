package com.mycompany.clinicaprivadadoo.controlador;

import com.mycompany.clinicaprivadadoo.dao.ConexionSql;
import com.mycompany.clinicaprivadadoo.dao.TurnoDao;
import com.mycompany.clinicaprivadadoo.modelo.Paciente;
import com.mycompany.clinicaprivadadoo.modelo.Recepcionista;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController {

    @FXML
    private TextField dniTextField;

    @FXML
    private Label resultLabel;

    @FXML
    private Button registrarButton;

    @FXML
    private Button generarOrdenButton;

    @FXML
    private Button mostrarTurnosButton;

    private final TurnoDao turnoDao;
    private final Recepcionista recepcionista;

    public MainViewController() {
        this.turnoDao = new TurnoDao(ConexionSql.getInstance().getConnection());
        this.recepcionista = new Recepcionista(null, null, null, null, null, null, null, null); // Inicializamos Recepcionista
    }

    @FXML
    public void initialize() {
        registrarButton.setVisible(false);
        generarOrdenButton.setVisible(false);
    }

    @FXML
    public void consultarPaciente() {
        String dni = dniTextField.getText();
        Paciente paciente = recepcionista.consultarPaciente(dni);

        if (paciente != null) {
            resultLabel.setText("Paciente encontrado: " + paciente.getNombre() + " " + paciente.getApellido());
            registrarButton.setVisible(false);
            generarOrdenButton.setVisible(true);
        } else {
            resultLabel.setText("Paciente no encontrado.");
            registrarButton.setVisible(true);
            generarOrdenButton.setVisible(false);
        }
    }

    @FXML
    public void registrarNuevoPaciente() {
        try {
            recepcionista.registrarNuevoPaciente(); // Lógica delegada a Recepcionista

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/clinicaprivadadoo/RegistroPaciente.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Registrar Nuevo Paciente");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error al abrir el formulario de registro.");
        }
    }
 @FXML
    public void abrirVisualizacionPacientes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/clinicaprivadadoo/PacientesView.fxml"));
            Parent root = loader.load();

            // Configurar la nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Visualizar Pacientes");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // Bloquear la ventana principal mientras esta está activa
            stage.setResizable(false); // Opcional: evitar que la ventana se redimensione

            // Mostrar la ventana
            stage.showAndWait(); // Esperar a que se cierre la ventana antes de continuar

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al abrir la visualización de pacientes: " + e.getMessage());
        }
    }
    @FXML
    public void generarOrdenConsulta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/clinicaprivadadoo/GenerarOrdenConsulta.fxml"));
            Parent root = loader.load();

            GenerarOrdenConsultaController controller = loader.getController();

            String resultado = resultLabel.getText().replace("Paciente encontrado: ", "").trim();
            String[] datos = resultado.split(" ");
            String nombre = datos[0] + " " + datos[1];
            String dni = dniTextField.getText();

            controller.setPacienteDatos(nombre, dni);

            Stage stage = new Stage();
            stage.setTitle("Generar Orden de Consulta");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error al generar la orden de consulta.");
        }
    }

    @FXML
    public void mostrarTurnos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/clinicaprivadadoo/MostrarTurnos.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Turnos y Órdenes de Consulta");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error al mostrar los turnos.");
        }
    }
}
