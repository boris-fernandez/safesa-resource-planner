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
public class Movimiento{
    private int movimientoID;
    private String metodoPago;
    private int cantidad;
    private double monto;
    private LocalDate fecha;
    private String tipoMovimiento;
    private Persona persona;
    private Producto producto;

    public Movimiento(int movimientoID, String metodoPago, int cantidad, double monto, LocalDate fecha, String tipoMovimiento, Persona persona, Producto producto) {
        this.movimientoID = movimientoID;
        this.metodoPago = metodoPago;
        this.cantidad = cantidad;
        this.monto = monto;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.persona = persona;
        this.producto = producto;
    }

    public Movimiento() {
    }

    public void setMovimientoID(int movimientoID) {
        this.movimientoID = movimientoID;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
    
    public int getMovimientoID() {
        return movimientoID;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Persona getPersona() {
        return persona;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    
    @Override
    public String toString() {
        return "Movimiento{" + "movimientoID=" + movimientoID + ", metodoPago=" + metodoPago + ", cantidad=" + cantidad + ", fecha=" + fecha + ", tipoMovimiento=" + tipoMovimiento + ", persona=" + persona + ", producto=" + producto + '}';
    }

}
