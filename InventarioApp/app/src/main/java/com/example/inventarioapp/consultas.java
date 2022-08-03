package com.example.inventarioapp;

public class consultas {
    String apellidos,cedula,nombres;
    String precioFinal;
    String tratamientos;

    public consultas(String apellidos, String cedula, String nombres, String precioFinal, String tratamientos) {
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.nombres = nombres;
        this.precioFinal = precioFinal;
        this.tratamientos = tratamientos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(String precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(String tratamientos) {
        this.tratamientos = tratamientos;
    }

    public consultas() {}

}
