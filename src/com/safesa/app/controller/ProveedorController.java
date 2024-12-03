/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.controller;

import com.safesa.app.dto.ProveedorDto;
import com.safesa.app.model.Cliente;
import com.safesa.app.model.Proveedor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BORIS
 */
public class ProveedorController {

    private final ProveedorDto proveedorDto;

    public ProveedorController() {
        proveedorDto = new ProveedorDto();
    }

    public void listarProveedores(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        ArrayList<Proveedor> proveedores = proveedorDto.listaProveedores();

        Object[] object = new Object[6];
        for (Proveedor proveedor : proveedores) {
            object[0] = proveedor.getProveedorID();
            object[1] = proveedor.getDni();
            object[2] = proveedor.getNombre();
            object[3] = proveedor.getApellidos();
            object[4] = proveedor.getTelefono();
            object[5] = proveedor.getEmail();
            modelo.addRow(object);
        }
    }

    public void buscarProveedor(JTextField txtDNI, JTextField txtId, JTextField txtNombre, JTextField txtApellido,
                            JTextField txtTelefono, JTextField txtEmail) {
        String dni = txtDNI.getText().trim();

        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingrese un DNI en el campo",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Proveedor proveedor = proveedorDto.buscarProveedor(dni);

        if (proveedor == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontró un proveedor con el DNI ingresado.",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            txtId.setText(String.valueOf(proveedor.getProveedorID()));
            txtDNI.setText(proveedor.getDni());
            txtNombre.setText(proveedor.getNombre());
            txtApellido.setText(proveedor.getApellidos());
            txtTelefono.setText(proveedor.getTelefono());
            txtEmail.setText(proveedor.getEmail());
        }
    }



   public void actualizarProveedor(JTextField txtNombre, JTextField txtApellido, JTextField txtTelefono,
                                JTextField txtEmail, JTextField txtId, JTable tabla) {
        String idS = txtId.getText();
        if (idS.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Para actualizar primero debes buscar un proveedor por DNI",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            String id = txtId.getText();
            proveedorDto.actualizarProveedor(nombre, apellido, telefono, email, id);
            listarProveedores(tabla);
            JOptionPane.showMessageDialog(null,
                    "Se actualizó correctamente el proveedor",
                    "Mensaje de Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public void limpiarCampos(JTextField txtNombre, JTextField txtApellido, JTextField txtTelefono,
                              JTextField txtEmail, JTextField txtId, JTextField txtDNI) {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtId.setText("");
        txtDNI.setText("");
    }

    public void eliminarProveedor(JTextField txtId, JTable tabla, JTextField txtNombre, JTextField txtApellido,
                                  JTextField txtTelefono, JTextField txtEmail, JTextField txtDNI) {
        String idS = txtId.getText();
        if (idS.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Para eliminar primero debes buscar un proveedor por DNI",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            int id = Integer.parseInt(idS);
            proveedorDto.eliminarProveedor(id);
            JOptionPane.showMessageDialog(null,
                    "Se eliminó correctamente el proveedor",
                    "Mensaje de Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos(txtNombre, txtApellido, txtTelefono, txtEmail, txtId, txtDNI);
            listarProveedores(tabla);
        }
    }
}
