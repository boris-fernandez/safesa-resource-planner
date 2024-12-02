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

    public void agregarIngreso(JComboBox<String> nombreProd, JTextField txtCantidad, JTextField txtMonto, 
           JTextField txtDNI, JTextField txtNombre, JTextField txtApellido, JTextField txtTelefono,JTextField txtemail, JTextField txtmetodPago){
       var cliente = new Cliente();
       String nombreProducto = (String) nombreProd.getSelectedItem();
       int cantidadProducto = Integer.parseInt(txtCantidad.getText());
       
       String montoTotal = txtMonto.getText().trim();
       String dniCliente = txtDNI.getText().trim();
       String nombreCliente = txtNombre.getText().trim();
       String apellidoCliente = txtApellido.getText().trim();
       String telefonoCliente = txtTelefono.getText().trim();
       String emailCliente = txtemail.getText().trim();
       String metodoPago = txtmetodPago.getText().trim();
       
        if (!metodoPago.equalsIgnoreCase("Efectivo") && !metodoPago.toLowerCase().startsWith("tarjeta")) {
           JOptionPane.showMessageDialog(null, 
                   "Metodo de pago Incorrecto", 
                   "Advertencia de metodo de pago", 
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
            JOptionPane.showMessageDialog(null, 
                    "Se registro correctamente el ingreso", 
                    "Ingreso registrado", 
                    JOptionPane.INFORMATION_MESSAGE, 
                    null);
            txtCantidad.setText("");
            txtTelefono.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            txtemail.setText("");
            txtMonto.setText("");
            txtDNI.setText("");
            txtmetodPago.setText("");
       } 
    }
   
    public void mostrarIngresoMonto(JComboBox<String> txtnombreProd, JTextField txtcantidad, JTextField txtMonto){
        String nombreProducto = (String) txtnombreProd.getSelectedItem();
        int stock = productoDto.obtenerStockProducto(nombreProducto);
        int cantidadProducto = Integer.parseInt(txtcantidad.getText());
        if(cantidadProducto > stock){
            JOptionPane.showMessageDialog(null, 
                    "La cantidad ingresada es mayor a su stock", 
                    "Cantidad no disponible", 
                    JOptionPane.INFORMATION_MESSAGE, 
                    null);
        }else{
            double precioProducto = productoDto.precioProducto(nombreProducto);
            int idProducto = productoDto.obtenerIdProducto(nombreProducto);
            double monto = precioProducto*cantidadProducto;
            txtMonto.setText(""+monto);
        } 
    }
    
    public void mostrarEgresoMonto(JComboBox<String> txtnombreProd, JTextField txtcantidad, JTextField txtMonto){
        String nombreProducto = (String) txtnombreProd.getSelectedItem();
        int stock = productoDto.obtenerStockProducto(nombreProducto);
        int cantidadProducto = Integer.parseInt(txtcantidad.getText());
        
        double precioProducto = productoDto.precioProducto(nombreProducto);
        int idProducto = productoDto.obtenerIdProducto(nombreProducto);
        double monto = precioProducto*cantidadProducto;
        txtMonto.setText(""+monto);
    }
   
    public void mostrarDatosCliente(JTextField txtdni, JTextField txtnombre, JTextField txtapellidos, JTextField txtTelefono, JTextField txtEmail){
        String dni = txtdni.getText().trim();
        if(dni.isEmpty()){
            JOptionPane.showMessageDialog(null, 
                    "El campo dni esta vacio", 
                    "Advertencia de dni vacio", 
                    JOptionPane.WARNING_MESSAGE, 
                    null);
        }else if(dni.length()!= 8){
            JOptionPane.showMessageDialog(null, 
                   "El DNI ingresado debe tener 8 dígitos", 
                   "Advertencia de de longuitud del dni", 
                   JOptionPane.WARNING_MESSAGE, 
                   null);
        }    
        else if(cd.buscarDni(dni) == false){
            JOptionPane.showMessageDialog(null, 
                    "El dni ingresado no existe", 
                    "Advertencia de dni no existe", 
                    JOptionPane.WARNING_MESSAGE, 
                    null);
        }else{
            var cliente = new Cliente();
            cliente = cd.buscarCliente(dni);
            txtnombre.setText(cliente.getNombre());
            txtapellidos.setText(cliente.getApellidos());
            txtTelefono.setText(cliente.getTelefono());
            txtEmail.setText(cliente.getEmail());
        }
    }
   
    public void agregarEgreso(JComboBox<String> nombreProd, JTextField txtCantidad, JTextField txtMonto, 
           JTextField txtDNI, JTextField txtNombre, JTextField txtApellido, JTextField txtTelefono,JTextField txtemail, JTextField txtmetodPago){
        var proveedor = new Proveedor();
        String nombreProducto = (String) nombreProd.getSelectedItem();
        int cantidadProducto = Integer.parseInt(txtCantidad.getText());
        String montoTotal = txtMonto.getText();
        String dniProveedor = txtDNI.getText();
        String nombreProveedor = txtNombre.getText();
        String apellidoProveedor = txtApellido.getText();
        String telefonoProveedor = txtTelefono.getText();
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
            JOptionPane.showMessageDialog(null, 
                    "Se registro correctamente el egreso", 
                    "Egreso registrado", 
                    JOptionPane.INFORMATION_MESSAGE, 
                    null);
            txtCantidad.setText("");
            txtTelefono.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            txtemail.setText("");
            txtMonto.setText("");
            txtDNI.setText("");
            txtmetodPago.setText("");
        }
    }
   
    public void mostrarDatosProveedores(JTextField txtdni, JTextField txtnombre, JTextField txtapellidos, JTextField txtTelefono, JTextField txtEmail){
        String dni = txtdni.getText().trim();
        if(dni.isEmpty()){
            JOptionPane.showMessageDialog(null, 
                    "El campo dni esta vacio", 
                    "Advertencia de dni vacio", 
                    JOptionPane.WARNING_MESSAGE, 
                    null);
        }else if(dni.length()!= 8){
            JOptionPane.showMessageDialog(null, 
                   "El DNI ingresado debe tener 8 dígitos", 
                   "Advertencia de de longuitud del dni", 
                   JOptionPane.WARNING_MESSAGE, 
                   null);
        }    
        else if(pd.buscarDni(dni) == false){
            JOptionPane.showMessageDialog(null, 
                    "El dni ingresado no existe", 
                    "Advertencia de dni no existe", 
                    JOptionPane.WARNING_MESSAGE, 
                    null);
        }else{
            var proveedor = new Proveedor();
            proveedor = pd.buscarProveedor(dni);
            txtnombre.setText(proveedor.getNombre());
            txtapellidos.setText(proveedor.getApellidos());
            txtTelefono.setText(proveedor.getTelefono());
            txtEmail.setText(proveedor.getEmail());  
        }
    }
    
    
    public void listarMovimientos(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        ArrayList<AbstractMap.SimpleEntry<Movimiento, Cliente>> movimientos = movimientoDto.obtenerMovimientos(); 

        Object[] object = new Object[8];
        for (int i = 0; i < movimientos.size(); i++) {
            Movimiento movimiento = movimientos.get(i).getKey();
            Cliente cliente = movimientos.get(i).getValue();
            object[0] = movimiento.getMovimientoID();
            object[1] = movimiento.getFecha();
            object[2] = movimiento.getProducto().getNombre();
            object[3] = movimiento.getCantidad(); 
            object[4] = movimiento.getMonto();
            object[5] = cliente.getNombre(); 
            object[6] = cliente.getDni(); 
            object[7] = movimiento.getTipoMovimiento();
            modelo.addRow(object);
        }
    }
    
    public void buscarMovimiento(JTextField txtDni, JTextField txtNumero, JTextField txtCantidad, 
            JTextField txtNombreProd, JTextField txtMovimiento, JTextField txtNombreCP, JTextField txtMonto, JTextField txtFecha) {
        String dni = txtDni.getText().trim();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                    "Campo dni se encuentra vacío. Completar el campo", 
                    "Advertencia de dni vacío", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        AbstractMap.SimpleEntry<Movimiento, Object> resultado = movimientoDto.buscarMovimiento(dni);
        if (resultado != null) {
            Movimiento movimiento = resultado.getKey();
            Object persona = resultado.getValue();
            txtNumero.setText(""+movimiento.getMovimientoID());
            txtCantidad.setText(""+movimiento.getCantidad());
            txtNombreProd.setText(movimiento.getProducto().getNombre());
            txtMovimiento.setText(movimiento.getTipoMovimiento());
            txtMonto.setText(""+movimiento.getMonto());
            txtFecha.setText(""+movimiento.getFecha());
            if (persona instanceof Cliente) {
                Cliente cliente = (Cliente) persona;
                txtNombreCP.setText(cliente.getNombre());
            } else if (persona instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) persona;
                txtNombreCP.setText(proveedor.getNombre());
            }
        }else {
            JOptionPane.showMessageDialog(null, 
                "El DNI ingresado no se encuentra", 
                "Advertencia de DNI no encontrado", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void eliminarMovimiento(JTextField txtNumero, JTable tabla, JTextField txtTotalEgreso, JTextField txtTotalIngreso, JTextField txtTotalGeneral ) {
        try {
            int numero = Integer.parseInt(txtNumero.getText().trim());
            movimientoDto.eliminarMovimiento(numero); 
            JOptionPane.showMessageDialog(null, "Movimiento eliminado exitosamente.");
            totalEgreso(txtTotalEgreso);
            totalIngreso(txtTotalIngreso);
            totalGeneral(txtTotalGeneral);
            listarMovimientos(tabla);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, 
                    """
                                Para poder eliminar primero 
                            debes buscar un movimiento por dni""", 
                    "Advertencia de dni vacio", 
                    JOptionPane.WARNING_MESSAGE, 
                    null);
        }
    }
    
    public void limpiarCasillas(JTextField txtCantidad, JTextField txtNumero, JTextField txtNombreProd, JTextField txtMovimiento,
                                JTextField txtNombreCP, JTextField txtMonto,JTextField txtDNI, JTextField txtFecha){
        txtCantidad.setText("");
        txtNumero.setText("");
        txtNombreProd.setText("");
        txtMovimiento.setText("");
        txtNombreCP.setText("");
        txtMonto.setText("");
        txtDNI.setText("");
    }
    
    public void totalEgreso(JTextField txtTotalEgreso){
        txtTotalEgreso.setText(""+movimientoDto.montoTotalEgreso());
    }
    
    public void totalIngreso(JTextField txtTotalIngreso){
        txtTotalIngreso.setText(""+movimientoDto.montoTotalIngreso());
    }
    
    public void totalGeneral(JTextField txtTotalGeneral){
        txtTotalGeneral.setText(""+movimientoDto.montoTotalGenera());
    }

}
