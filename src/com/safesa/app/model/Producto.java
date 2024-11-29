/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.model;

/**
 *
 * @author BORIS
 */
public class Producto{
    private int productoID;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private ItemMovimiento itemMovimiento;

    public Producto(String nombre, String descripcion, double precio, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public int getProductoID() {
        return productoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ItemMovimiento getItemMovimiento() {
        return itemMovimiento;
    }

    public void setItemMovimiento(ItemMovimiento itemMovimiento) {
        this.itemMovimiento = itemMovimiento;
    }

}
