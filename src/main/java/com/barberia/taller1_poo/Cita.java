package com.barberia.taller1_poo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Cita {
    private String cedula;
    private String nombre;
    private int edad;
    private String fecha;
    private String hora;

    public Cita() {}

    public Cita(String cedula, String nombre, int edad, String fecha, String hora) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y Setters
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getFecha() { return fecha; }

    public String getHora() { return hora; }
}
