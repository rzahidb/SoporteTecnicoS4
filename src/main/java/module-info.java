module org.example.practicasoportetecnico {

    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.practicasoportetecnico to javafx.fxml;

    exports org.example.practicasoportetecnico;
}