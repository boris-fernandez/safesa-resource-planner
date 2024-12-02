/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.controller;

import com.safesa.app.dto.ProductoDto;
import com.safesa.app.model.Producto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BORIS
 */
public class ProductoController{
    private final ProductoDto productoDto;

     public ProductoController() {
        productoDto = new ProductoDto();
    }
    
    public void agregarNuevoProducto(JTextField txtnombre, JTextField txtprecio, JTextField txtstock, JTextArea txtdescripcion){
        String nombre = txtnombre.getText();
        String precioS = txtprecio.getText();
        String stockS = txtstock.getText();
        String descripcion = txtdescripcion.getText();
        
        if (precioS.isEmpty() || stockS.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Los campos precio y stock no pueden estar vacíos",
                    "Error de validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        double precio = Double.parseDouble(precioS);
        int stock = Integer.parseInt(stockS);
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El campo nombre no debe estar vacio",
                    "Error de validación",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if (precio <= 0) {
            JOptionPane.showMessageDialog(null,
                    "El campo precio debe ser mayor a 0",
                    "Error de validación",
                    JOptionPane.WARNING_MESSAGE);
        }
        else if (stock <= 0) {
            JOptionPane.showMessageDialog(null,
                    "El campo stock debe ser mayor a 0",
                    "Error de validación",
                    JOptionPane.WARNING_MESSAGE);
        }else{
            nombre = nombre.toUpperCase().charAt(0) + nombre.substring(1, nombre.length());
            if(descripcion.isEmpty()){
                descripcion = "";
            }else{
                descripcion = descripcion.toUpperCase().charAt(0) + descripcion.substring(1, descripcion.length());
            }
            productoDto.agregarProducto(nombre, descripcion, precio, stock);
            JOptionPane.showMessageDialog(null,
                "Se agrego correctamente el Producto", "Mensaje de confirmación", 
                JOptionPane.INFORMATION_MESSAGE, null);
            txtnombre.setText("");
            txtprecio.setText("");
            txtstock.setText("");
            txtdescripcion.setText("");
        }
    }
    
    public void listarProductos(JTable tabla){
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        ArrayList<Producto> productos = productoDto.listaProducto(); 

        Object[] object = new Object[5];
        for (int i = 0; i < productos.size(); i++) {
            object[0] = productos.get(i).getProductoID();
            object[1] = productos.get(i).getNombre();
            object[2] = productos.get(i).getPrecio();
            object[3] = productos.get(i).getStock();
            object[4] = productos.get(i).getDescripcion();
            modelo.addRow(object);
        }
    }

    
    public void buscarProducto(JTextField txtNombre, JTextField txtNumero, JTextField txtStock, JTextArea txtDescripcion, JTextField txtPrecio) {
        String nombre = txtNombre.getText().trim();
        
        if (nombre == null || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Por favor, ingrese un nombre para buscar el producto.",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Producto producto = productoDto.buscarProductoPorNombre(nombre);
        if (producto == null) {
            JOptionPane.showMessageDialog(null,
                    "Producto no encontrado. Verifique el nombre ingresado.",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            txtNumero.setText(String.valueOf(producto.getProductoID()));
            txtStock.setText(String.valueOf(producto.getStock()));
            txtNombre.setText(producto.getNombre());
            txtDescripcion.setText(producto.getDescripcion());
            txtPrecio.setText(String.valueOf(producto.getPrecio()));
        }
    }

    
    public void limpiarCampos(JTextField txtnombre, JTextField txtNumero, JTextField txtStock, JTextArea txtDescripcion, JTextField txtPrecio){
        txtnombre.setText("");
        txtNumero.setText("");
        txtStock.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
    }

    public void eliminarProducto(JTextField txtid, JTextField txtnombre, JTextField txtStock, JTextField txtPrecio, JTextArea txtDescripcion, JTable tabla){
        String idS = txtid.getText().trim();
        
        if (idS.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Para eliminar primero debes buscar un producto",
                    "Mensaje de Advertencia", 
                    JOptionPane.QUESTION_MESSAGE, null);
            return;
        }
        try {
            Integer id = Integer.valueOf(idS); 
            productoDto.eliminarProducto(id);
            JOptionPane.showMessageDialog(null,
                    "Se elimino exitosamente el producto",
                    "Mensaje de confirmación", 
                    JOptionPane.INFORMATION_MESSAGE, null);
            limpiarCampos(txtnombre, txtid,txtStock, txtDescripcion, txtPrecio);
            listarProductos(tabla);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "El valor ingresado no es un número válido.",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
        }
    } 
    
    public void modificar(JTextField txtid, JTextField txtnombre, JTextField txtStock, JTextField txtPrecio, JTextArea txtDescripcion, JTable tabla){
        String idS = txtid.getText().trim();
        if (idS.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Para modificar primero debes buscar un producto",
                    "Mensaje de Advertencia", 
                    JOptionPane.QUESTION_MESSAGE, null);
            return;
        }
        try {
            String nombre = txtnombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            String descripcion = txtDescripcion.getText().trim();
            Integer id = Integer.valueOf(idS); 
            productoDto.actualizarProducto(nombre, descripcion, precio, id);
            JOptionPane.showMessageDialog(null,
                    "Se actualizo exitosamente el producto",
                    "Mensaje de confirmación", 
                    JOptionPane.INFORMATION_MESSAGE, null);
            limpiarCampos(txtnombre, txtid,txtStock, txtDescripcion, txtPrecio);
            listarProductos(tabla);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "El valor ingresado no es un número válido.",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
