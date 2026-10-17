package com.mycompany.clinicaprivadadoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.mycompany.clinicaprivadadoo.dao.ConexionSql;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            stage.setScene(scene);
            
            scene.getStylesheets().add(getClass().getResource("/com/mycompany/clinicaprivadadoo/styles.css").toExternalForm());
            stage.setTitle("Clinica Privada");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        ConexionSql.getInstance().closeConnection();
    }

    public static void main(String[] args) {
        launch();
    }
}
