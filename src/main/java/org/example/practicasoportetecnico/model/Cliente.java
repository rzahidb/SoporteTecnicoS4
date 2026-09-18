package org.example.practicasoportetecnico.model;

public class Cliente {

private String nombre;
private String correo;
private String telefono;
private String tipoCliente;
private String documentoIdentidad;
private String directorioCliente;

    public Cliente() {
    }

    public Cliente(String nombre, String correo, String telefono, String tipoCliente, String documentoIdentidad, String directorioCliente) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
        this.documentoIdentidad = documentoIdentidad;
        this.directorioCliente = directorioCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getDirectorioCliente() {
        return directorioCliente;
    }

    public void setDirectorioCliente(String directorioCliente) {
        this.directorioCliente = directorioCliente;
    }
}
