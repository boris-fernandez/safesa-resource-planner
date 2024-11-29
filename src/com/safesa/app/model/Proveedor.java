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
    private int ruc;

    public Proveedor(int proveedorID, int ruc, String nombre, String apellidos, String telefono, String email) {
        super(nombre, apellidos, telefono, email);
        this.proveedorID = proveedorID;
        this.ruc = ruc;
    }

    public Proveedor() {
    }

    public int getProveedorID() {
        return proveedorID;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    } 
    
}
