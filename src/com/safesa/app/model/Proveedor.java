/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.model;

/**
 *
 * @author BORIS
 */
public class Proveedor extends Persona{
    private int proveedorID;
    private String dni;

    public Proveedor(int proveedorID, String dni, int personaID, String nombre, String apellidos, String telefono, String email) {
        super(personaID, nombre, apellidos, telefono, email);
        this.proveedorID = proveedorID;
        this.dni = dni;
    }

    public Proveedor() {
    }

    public void setProveedorID(int proveedorID) {
        this.proveedorID = proveedorID;
    }

    
    public int getProveedorID() {
        return proveedorID;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String ruc) {
        this.dni = ruc;
    } 

    @Override
    public String toString() {
        return "Proveedor{" + "roveedorID=" + proveedorID + super.toString() + ", dni=" + dni + '}';
    }
    
    
}
