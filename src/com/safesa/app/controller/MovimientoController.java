/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.controller;

import com.safesa.app.dto.ClienteDto;
import com.safesa.app.dto.MovimientoDto;
import com.safesa.app.dto.ProductoDto;
import com.safesa.app.dto.ProveedorDto;
import com.safesa.app.model.Cliente;
import com.safesa.app.model.Movimiento;
import com.safesa.app.model.Producto;
import com.safesa.app.model.Proveedor;
import java.util.AbstractMap;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BORIS
 */
public class MovimientoController {
   private final MovimientoDto movimientoDto;
   private final ProductoDto productoDto;
   private final ClienteDto cd;
   private final ProveedorDto pd;
   private final Movimiento movimiento;

   
    public MovimientoController(){
        movimientoDto = new MovimientoDto();
        productoDto = new ProductoDto();
        cd = new ClienteDto();
        pd = new ProveedorDto();
        movimiento = new Movimiento();
    }

    public void agregarIngreso(JComboBox<String> nombreProd, JTextField cantidad, JTextField monto, 
           JTextField dni, JTextField nombre, JTextField apellido, JTextField telefono,JTextField txtemail, JTextField txtmetodPago){
       var cliente = new Cliente();
       String nombreProducto = (String) nombreProd.getSelectedItem();
       int cantidadProducto = Integer.parseInt(cantidad.getText());
       int stock = productoDto.obtenerStockProducto(nombreProducto);
       String montoTotal = monto.getText().trim();
       String dniCliente = dni.getText().trim();
       String nombreCliente = nombre.getText().trim();
       String apellidoCliente = apellido.getText().trim();
       String telefonoCliente = telefono.getText().trim();
       String emailCliente = txtemail.getText().trim();
       String metodoPago = txtmetodPago.getText().trim();
       if(cantidadProducto > stock){
           JOptionPane.showMessageDialog(null, 
                   "La cantidad ingresada es mayor a tu stock", 
                   "Acvertencia de stock", 
                   JOptionPane.WARNING_MESSAGE, 
                   null);
       }else if (!metodoPago.equalsIgnoreCase("Efectivo") && !metodoPago.toLowerCase().startsWith("tarjeta")) {
           JOptionPane.showMessageDialog(null, 
                   "Metodo de pago Incorrecto", 
                   "Advertencia de metodo de pago", 
                   JOptionPane.WARNING_MESSAGE, 
                   null);
       }else if(dniCliente.length()!= 8){
           JOptionPane.showMessageDialog(null, 
                   "El DNI ingresado debe tener 8 dígitos", 
                   "Advertencia de de longuitud del dni", 
                   JOptionPane.WARNING_MESSAGE, 
                   null);
       }else if(telefonoCliente.length() != 9){
           JOptionPane.showMessageDialog(null, 
                   "El Telefono ingresado debe tener 9 dígitos", 
                   "Advertencia de longuitud del telefono", 
                   JOptionPane.WARNING_MESSAGE, 
                   null);
       }else if(!cliente.validarEmail(emailCliente)){
           JOptionPane.showMessageDialog(null, 
                   "El Email ingresado es riesgoso pruebe con otro valido", 
                   "Advertencia de email riesgoso", 
                   JOptionPane.WARNING_MESSAGE, 
                   null);
       }else{
            movimientoDto.agregarIngreso(nombreProducto, cantidadProducto, dniCliente, nombreCliente, apellidoCliente, telefonoCliente,emailCliente, metodoPago);
  
       } 
    }
   
    public void mostrarMonto(JComboBox<String> txtnombreProd, JTextField txtcantidad, JTextField txtMonto){
       String nombreProducto = (String) txtnombreProd.getSelectedItem();
       double precioProducto = productoDto.precioProducto(nombreProducto);
       int cantidadProducto = Integer.parseInt(txtcantidad.getText());
       int idProducto = productoDto.obtenerIdProducto(nombreProducto);
       double monto = precioProducto*cantidadProducto;
       txtMonto.setText(""+monto);
    }
   
    public void mostrarDatosCliente(JTextField txtdni, JTextField txtnombre, JTextField txtapellidos, JTextField txtTelefono, JTextField txtEmail){
       String dni = txtdni.getText();
       var cliente = new Cliente();
       cliente = cd.buscarCliente(dni);
       txtnombre.setText(cliente.getNombre());
       txtapellidos.setText(cliente.getApellidos());
       txtTelefono.setText(cliente.getTelefono());
       txtEmail.setText(cliente.getEmail());
    }
   
   public void agregarEgreso(JComboBox<String> nombreProd, JTextField cantidad, JTextField monto, 
           JTextField dni, JTextField nombre, JTextField apellido, JTextField telefono,JTextField txtemail, JTextField txtmetodPago){
        var proveedor = new Proveedor();
        String nombreProducto = (String) nombreProd.getSelectedItem();
        int cantidadProducto = Integer.parseInt(cantidad.getText());
        String montoTotal = monto.getText();
        String dniProveedor = dni.getText();
        String nombreProveedor = nombre.getText();
        String apellidoProveedor = apellido.getText();
        String telefonoProveedor = telefono.getText();
        String emailProveedor = txtemail.getText();
        String metodoPago = txtmetodPago.getText();
        if (!metodoPago.equalsIgnoreCase("Efectivo") && !metodoPago.toLowerCase().startsWith("tarjeta")) {
            JOptionPane.showMessageDialog(null, 
                    "Metodo de pago Incorrecto", 
                    "Advertencia de metodo de pago", 
                    JOptionPane.WARNING_MESSAGE, 
                    null);
        }else if(dniProveedor.length()!= 8 || dniProveedor.isEmpty()){
            JOptionPane.showMessageDialog(null, 
                    "El DNI ingresado debe tener 8 dígitos", 
                    "Advertencia de de longuitud del dni", 
                    JOptionPane.WARNING_MESSAGE, 
                    null);
        }else if(telefonoProveedor.length() != 9 || dniProveedor.isEmpty()){
            JOptionPane.showMessageDialog(null, 
                    "El Telefono ingresado debe tener 9 dígitos", 
                    "Advertencia de longuitud del telefono", 
                    JOptionPane.WARNING_MESSAGE, 
                    null);
        }else if(!proveedor.validarEmail(emailProveedor)){
            JOptionPane.showMessageDialog(null, 
                    "El Email ingresado es riesgoso pruebe con otro valido", 
                    "Advertencia de email riesgoso", 
                    JOptionPane.WARNING_MESSAGE, 
                    null);
        }else{
            movimientoDto.agregarEgreso(nombreProducto, cantidadProducto, dniProveedor, nombreProveedor, apellidoProveedor, telefonoProveedor,emailProveedor, metodoPago);
        }
   }
   
    public void mostrarDatosProveedores(JTextField txtdni, JTextField txtnombre, JTextField txtapellidos, JTextField txtTelefono, JTextField txtEmail){
        String dni = txtdni.getText();
        var proveedor = new Proveedor();
        proveedor = pd.buscarProveedor(dni);
        txtnombre.setText(proveedor.getNombre());
        txtapellidos.setText(proveedor.getApellidos());
        txtTelefono.setText(proveedor.getTelefono());
        txtEmail.setText(proveedor.getEmail());       
    }
    
    
    public void listarMovimientos(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        ArrayList<AbstractMap.SimpleEntry<Movimiento, Cliente>> movimientos = movimientoDto.obtenerMovimientos(); 

        Object[] object = new Object[7];
        for (int i = 0; i < movimientos.size(); i++) {
            Movimiento movimiento = movimientos.get(i).getKey();
            Cliente cliente = movimientos.get(i).getValue();
            object[0] = movimiento.getMovimientoID();
            object[1] = movimiento.getProducto().getNombre();
            object[2] = movimiento.getCantidad(); 
            object[3] = movimiento.getMonto();
            object[4] = cliente.getNombre(); 
            object[5] = cliente.getDni(); 
            object[6] = movimiento.getTipoMovimiento();
            modelo.addRow(object);
        }
    }
    
    public void buscarMovimiento(JTextField txtDni, JTextField txtNumero, JTextField txtCantidad, 
            JTextField txtNombreProd, JTextField txtMovimiento, JTextField txtNombreCP, JTextField txtMonto) {
        String dni = txtDni.getText().trim();
        AbstractMap.SimpleEntry<Movimiento, Object> resultado = movimientoDto.buscarMovimiento(dni);
        if (resultado != null) {
            Movimiento movimiento = resultado.getKey();
            Object persona = resultado.getValue();
            txtNumero.setText(String.valueOf(movimiento.getMovimientoID()));
            txtCantidad.setText(String.valueOf(movimiento.getCantidad()));
            txtNombreProd.setText(movimiento.getProducto().getNombre());
            txtMovimiento.setText(movimiento.getTipoMovimiento());
            txtMonto.setText(String.valueOf(movimiento.getMonto()));
            if (persona instanceof Cliente) {
                Cliente cliente = (Cliente) persona;
                txtNombreCP.setText(cliente.getNombre());
            } else if (persona instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) persona;
                txtNombreCP.setText(proveedor.getNombre());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el movimiento para el DNI ingresado.");
        }
    }
    
    public void eliminarMovimiento(JTextField txtNumero, JTable tabla) {
        try {
            int numero = Integer.parseInt(txtNumero.getText().trim());
            movimientoDto.eliminarMovimiento(numero); 
            JOptionPane.showMessageDialog(null, "Movimiento eliminado exitosamente.");
            listarMovimientos(tabla);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
        }
    }


   
}
