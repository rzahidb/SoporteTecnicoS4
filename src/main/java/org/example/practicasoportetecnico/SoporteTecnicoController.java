package org.example.practicasoportetecnico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.practicasoportetecnico.model.Cliente;

import java.io.File;

public class SoporteTecnicoController {

    private Cliente cliente;

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtCorreoCliente;

    @FXML
    private TextField txtTipoCliente;

    @FXML
    private TextField txtAsunto;

    @FXML
    private ComboBox<String> cmbTipoServicio;

    @FXML
    private RadioButton rbBaja;

    @FXML
    private RadioButton rbMedia;

    @FXML
    private RadioButton rbAlta;

    @FXML
    private TextArea txtDescripcionProblema;

    @FXML
    private TextField txtArchivoAdjunto;

    @FXML
    private TextField txtCarpetaEvidencias;

    private ToggleGroup grupoPrioridad;

    @FXML
    public void initialize() {

        cmbTipoServicio.getItems().addAll(
                "Soporte de hardware",
                "Soporte de software",
                "Redes",
                "Mantenimiento",
                "Instalación"
        );

        grupoPrioridad = new ToggleGroup();

        rbBaja.setToggleGroup(grupoPrioridad);
        rbMedia.setToggleGroup(grupoPrioridad);
        rbAlta.setToggleGroup(grupoPrioridad);
    }

    public void cargarCliente(Cliente cliente) {

        this.cliente = cliente;

        txtCliente.setText(cliente.getNombre());
        txtCorreoCliente.setText(cliente.getCorreo());
        txtTipoCliente.setText(cliente.getTipoCliente());

        // Cuando el ticket viene desde RegistroCliente, estos datos
        // pertenecen al cliente guardado y no deben modificarse.
        txtCliente.setEditable(false);
        txtCorreoCliente.setEditable(false);
        txtTipoCliente.setEditable(false);
    }

    @FXML
    private void seleccionarArchivo() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar archivo adjunto");

        File archivo = fc.showOpenDialog(
                txtArchivoAdjunto.getScene().getWindow()
        );

        if (archivo != null) {
            txtArchivoAdjunto.setText(
                    archivo.getAbsolutePath()
            );
        }
    }

    @FXML
    private void seleccionarCarpeta() {

        DirectoryChooser dc = new DirectoryChooser();

        dc.setTitle("Seleccionar carpeta de evidencias");
        dc.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );

        File carpeta = dc.showDialog(
                txtCarpetaEvidencias
                        .getScene()
                        .getWindow()
        );

        if (carpeta != null) {

            txtCarpetaEvidencias.setText(
                    carpeta.getAbsolutePath()
            );
        }
    }

    private boolean validarFormulario() {

        String clienteNombre = txtCliente.getText().trim();
        String correo = txtCorreoCliente.getText().trim();
        String asunto = txtAsunto.getText().trim();
        String descripcion = txtDescripcionProblema.getText().trim();

        boolean clienteValido =
                clienteNombre.matches("[\\p{L} ]{3,}");

        boolean correoValido =
                correo.matches(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
                );

        boolean asuntoValido =
                asunto.matches(".{3,}");

        boolean descripcionValida =
                descripcion.matches(".{5,}");

        return clienteValido
                && correoValido
                && !txtTipoCliente.getText().isBlank()
                && asuntoValido
                && cmbTipoServicio.getValue() != null
                && grupoPrioridad.getSelectedToggle() != null
                && descripcionValida
                && !txtArchivoAdjunto.getText().isBlank()
                && !txtCarpetaEvidencias.getText().isBlank();
    }

    @FXML
    private void crearSolicitud() {

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
        confirmacion.setHeaderText("¿Desea crear esta solicitud?");
        confirmacion.setContentText(
                "Cliente: " + txtCliente.getText()
        );

        confirmacion.showAndWait()
                .ifPresent(respuesta -> {

                    if (respuesta == ButtonType.OK) {

                        Alert informacion = new Alert(
                                Alert.AlertType.INFORMATION
                        );

                        informacion.setTitle("Solicitud creada");
                        informacion.setHeaderText(null);
                        informacion.setContentText(
                                "La solicitud se creó correctamente para "
                                        + txtCliente.getText() + "."
                        );

                        informacion.showAndWait();
                    }
                });
    }

    @FXML
    private void guardarSolicitud() {

        if (!validarFormulario()) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);

            alerta.setTitle("Validación");
            alerta.setHeaderText("Datos incorrectos");
            alerta.setContentText(
                    "Complete correctamente todos los campos."
            );

            alerta.showAndWait();
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("Guardar");
        alerta.setHeaderText(null);
        alerta.setContentText(
                "Datos de la solicitud guardados para el cliente "
                        + txtCliente.getText() + "."
        );

        alerta.showAndWait();
    }

    @FXML
    private void limpiarFormulario() {

        // Si el ticket fue abierto desde RegistroCliente, los datos
        // del cliente guardado se conservan. Si se abrió desde el menú,
        // también se limpian porque fueron ingresados manualmente.
        if (cliente == null) {
            txtCliente.clear();
            txtCorreoCliente.clear();
            txtTipoCliente.clear();
        }

        txtAsunto.clear();

        cmbTipoServicio
                .getSelectionModel()
                .clearSelection();

        grupoPrioridad.selectToggle(null);

        txtDescripcionProblema.clear();
        txtArchivoAdjunto.clear();
        txtCarpetaEvidencias.clear();
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
