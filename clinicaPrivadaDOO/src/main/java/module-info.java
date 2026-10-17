module com.mycompany.clinicaprivadadoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.clinicaprivadadoo to javafx.fxml;
    opens com.mycompany.clinicaprivadadoo.controlador to javafx.fxml;
    opens com.mycompany.clinicaprivadadoo.dto to javafx.base;  // Añade esta línea
    opens com.mycompany.clinicaprivadadoo.modelo to javafx.base;


    exports com.mycompany.clinicaprivadadoo;
    exports com.mycompany.clinicaprivadadoo.controlador;
}
