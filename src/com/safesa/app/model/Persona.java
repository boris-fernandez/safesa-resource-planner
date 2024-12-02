/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author BORIS
 */
public abstract class Persona{
    private int personaID;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public Persona(int personaID, String nombre, String apellidos, String telefono, String email) {
        this.personaID = personaID;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }
 
    public Persona() {
    }

    public int getPersonaID() {
        return personaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public boolean validarEmail(String email){
        var consultaApi = new ConsultaApi();
        VerificarEmailDTO verificar = consultaApi.verificarEmail(email);
      
        if (verificar.emailRiskScore() > 79 && verificar.temporaryEmail()) {
            return false;
        } 
        return true;
    }

    @Override
    public String toString() {
        return ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email + ", movimientos=" + movimientos + '}';
    }
    
    
}