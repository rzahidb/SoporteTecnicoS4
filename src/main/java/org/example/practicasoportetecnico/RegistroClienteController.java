package org.example.practicasoportetecnico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.practicasoportetecnico.model.Cliente;

import java.io.File;
import java.io.IOException;

public class RegistroClienteController {

    private Cliente clienteGuardado;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<String> cmbTipoCliente;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtDirectorio;

    @FXML
    public void initialize() {

        cmbTipoCliente.getItems().addAll(
                "Individual",
                "Empresa",
                "Institución"
        );
    }

    @FXML
    private void seleccionarDocumento() {

        FileChooser fc = new FileChooser();

        fc.setTitle("Seleccionar documento de identificación");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(
                        "Documentos",
                        "*.pdf",
                        "*.jpg",
                        "*.jpeg",
                        "*.png"
                )
        );

        File archivo = fc.showOpenDialog(
                txtDocumento.getScene().getWindow()
        );

        if (archivo != null) {
            txtDocumento.setText(archivo.getAbsolutePath());
        }
    }

    @FXML
    private void seleccionarDirectorio() {

        DirectoryChooser dc = new DirectoryChooser();

        dc.setTitle("Seleccionar directorio del cliente");

        dc.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );

        File directorio = dc.showDialog(
                txtDirectorio.getScene().getWindow()
        );

        if (directorio != null) {
            txtDirectorio.setText(directorio.getAbsolutePath());
        }
    }

    private boolean validarFormulario() {

        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String telefono = txtTelefono.getText().trim();

        boolean nombreValido =
                nombre.matches("[\\p{L} ]{3,}");

        boolean correoValido =
                correo.matches(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
                );

        boolean telefonoValido =
                telefono.matches("\\d{8}");

        return nombreValido
                && correoValido
                && telefonoValido
                && cmbTipoCliente.getValue() != null
                && !txtDocumento.getText().isBlank()
                && !txtDirectorio.getText().isBlank();
    }

    @FXML
    private void guardarCliente() {

        if (!validarFormulario()) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);

            alerta.setTitle("Validación");
            alerta.setHeaderText("Datos incorrectos");
            alerta.setContentText(
                    "Verifique que todos los campos estén completos y sean válidos."
            );

            alerta.showAndWait();
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);

        confirmacion.setTitle("Confirmación");
        confirmacion.setHeaderText("¿Desea guardar este cliente?");
        confirmacion.setContentText("Cliente: " + txtNombre.getText());

        confirmacion.showAndWait().ifPresent(respuesta -> {

            if (respuesta == ButtonType.OK) {

                clienteGuardado = new Cliente(
                        txtNombre.getText().trim(),
                        txtCorreo.getText().trim(),
                        txtTelefono.getText().trim(),
                        cmbTipoCliente.getValue(),
                        txtDocumento.getText(),
                        txtDirectorio.getText()
                );

                Alert informacion = new Alert(Alert.AlertType.INFORMATION);

                informacion.setTitle("Cliente guardado");
                informacion.setHeaderText(null);
                informacion.setContentText(
                        "Cliente registrado correctamente. Ya puede crear la solicitud de servicio."
                );

                informacion.showAndWait();
            }
        });
    }

    @FXML
    private void crearSolicitud() throws IOException {

        if (clienteGuardado == null) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);

            alerta.setTitle("Cliente no guardado");
            alerta.setHeaderText("Debe guardar el cliente");
            alerta.setContentText(
                    "Primero debe guardar los datos del cliente antes de crear una solicitud."
            );

            alerta.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("soporte-tecnico.fxml")
        );

        Scene scene = new Scene(loader.load());

        SoporteTecnicoController controller = loader.getController();
        controller.cargarCliente(clienteGuardado);

        Stage stage = new Stage();

        stage.setTitle("Solicitud de Servicio");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void limpiarFormulario() {

        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        cmbTipoCliente.getSelectionModel().clearSelection();
        txtDocumento.clear();
        txtDirectorio.clear();

        clienteGuardado = null;
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {

        Stage stage = (Stage)
                ((Node) event.getSource())
                        .getScene()
                        .getWindow();

        stage.close();
    }
}
