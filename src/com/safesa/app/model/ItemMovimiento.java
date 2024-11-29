/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.model;

/**
 *
 * @author BORIS
 */
public class ItemMovimiento {
    private int itemMovimientoID;
    private int cantidad;
    private double precioTotal;
    private Movimiento movimiento;
    private Producto producto;

    public ItemMovimiento(int cantidad, double precioTotal, Movimiento movimiento, Producto producto) {
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.movimiento = movimiento;
        this.producto = producto;
    }

    public int getItemMovimientoID() {
        return itemMovimientoID;
    }

    public void setItemMovimientoID(int itemMovimientoID) {
        this.itemMovimientoID = itemMovimientoID;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
      
}
