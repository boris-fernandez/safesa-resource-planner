/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.model;

import java.time.LocalDate;

/**
 *
 * @author BORIS
 */
public class Cliente extends Persona{
    private int clienteID;
    private LocalDate fechaRegistro;
    private String dni;

    public Cliente(int clienteID, LocalDate fechaRegistro, String dni, int personaID, String nombre, String apellidos, String telefono, String email) {
        super(personaID, nombre, apellidos, telefono, email);
        this.clienteID = clienteID;
        this.fechaRegistro = fechaRegistro;
        this.dni = dni;
    }  
    
    public Cliente() {
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "clienteID=" + clienteID + super.toString() +", fechaRegistro=" + fechaRegistro + ", dni=" + dni + '}';
    }
    
}