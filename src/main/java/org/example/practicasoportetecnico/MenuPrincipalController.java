package org.example.practicasoportetecnico;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalController {

    @FXML
    private void abrirClientes() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("registro-cliente.fxml")
        );

        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setTitle("Registro de Clientes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void abrirSolicitudServicio() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("soporte-tecnico.fxml")
        );

        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setTitle("Solicitud de Servicio");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void salir() {
        Platform.exit();
    }
}