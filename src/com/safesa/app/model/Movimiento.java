/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author BORIS
 */
public class Movimiento{
    private int movimientoID;
    private boolean metodoPago;
    private String descripcion;
    private LocalDate fecha;
    private boolean tipoMovimiento;
    private Persona persona;
    private ArrayList<ItemMovimiento> iteMovimientos = new ArrayList<>();

    public Movimiento(boolean metodoPago, String descripcion, boolean tipoMovimiento, Persona persona, ArrayList<ItemMovimiento> iteMovimientos) {
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
        this.tipoMovimiento = tipoMovimiento;
        this.persona = persona;
        this.iteMovimientos = iteMovimientos;
    }

    public Movimiento() {
    }

    public int getMovimientoID() {
        return movimientoID;
    }

    public boolean getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(boolean metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(boolean tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ArrayList<ItemMovimiento> getIteMovimientos() {
        return iteMovimientos;
    }

    public void setIteMovimientos(ArrayList<ItemMovimiento> iteMovimientos) {
        this.iteMovimientos = iteMovimientos;
    }

}
