package org.example.practicasoportetecnico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                MenuPrincipalApp.class.getResource("menu-principal.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Soporte Técnico");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}