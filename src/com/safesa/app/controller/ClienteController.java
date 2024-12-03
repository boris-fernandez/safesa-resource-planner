/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.controller;

import com.safesa.app.dto.ClienteDto;
import com.safesa.app.model.Cliente;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BORIS
 */
public class ClienteController {
    private final ClienteDto clienteDto;
    private final Cliente cliente;
    
    public ClienteController(){
        clienteDto = new ClienteDto();
        cliente = new Cliente();
    }
    
    public void listarClientes(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        ArrayList<Cliente> clientes = clienteDto.listaCliente(); 

        Object[] object = new Object[7];
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            object[0] = cliente.getClienteID();
            object[1] = cliente.getDni();
            object[2] = cliente.getNombre();
            object[3] = cliente.getApellidos(); 
            object[4] = cliente.getTelefono();
            object[5] = cliente.getEmail(); 
            object[6] = cliente.getFechaRegistro(); 
            modelo.addRow(object);
        }
    }
    
    public void buscarCliente(JTextField txtDNI, JTextField txtId, JTextField txtNombre, JTextField txtApellido,
                          JTextField txtTelefono, JTextField txtEmail, JTextField txtFechaRegistro) {
        String dni = txtDNI.getText().trim();

        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingrese un dni en el campo",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente cliente = clienteDto.buscarCliente(dni);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontró un cliente con el DNI ingresado.",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }else { 
            txtId.setText(String.valueOf(cliente.getClienteID()));
            txtDNI.setText(cliente.getDni());
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellidos());
            txtTelefono.setText(cliente.getTelefono());
            txtEmail.setText(cliente.getEmail());
            txtFechaRegistro.setText(String.valueOf(cliente.getFechaRegistro()));
        }
    }
    
    public void actualizarCliente(JTextField txtNombre, JTextField txtApellido, JTextField txtTelefono,
            JTextField txtEmail, JTextField txtId, JTable tabla){

        String idS = txtId.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        if(idS.isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Para actualizar primero debes buscar un cliente por DNI",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }else if (telefono.length() != 9 || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El número de teléfono debe tener exactamente 9 dígitos",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }else if (cliente.validarEmail(email)) {
            JOptionPane.showMessageDialog(null,
                    "El email ingresado es riesgoso. Intente con otro.",
                    "Advertencia de Email Inválido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }else if(clienteDto.buscarDni(idS)){
                JOptionPane.showMessageDialog(null,
                        "El cliente con este DNI no existe en la base de datos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        } else {
                clienteDto.actualizarCliente(nombre, apellido, telefono, email, idS);
                listarClientes(tabla); 
                JOptionPane.showMessageDialog(null,
                        "Se actualizó correctamente el cliente",
                        "Mensaje de Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                
        }
    }

    
    public void limpiarCampos(JTextField txtNombre, JTextField txtApellido,JTextField txtTelefono,
            JTextField txtEmail, JTextField txtId, JTextField txtDni, JTextField txtFechaRegistro){
        txtNombre.setText("");
        txtApellido.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtId.setText("");
        txtDni.setText("");
        txtFechaRegistro.setText("");
    }

    public void elimiarCliente(JTextField txtId, JTable tabla,
            JTextField txtNombre, JTextField txtApellido,JTextField txtTelefono,
            JTextField txtEmail, JTextField txtDni, JTextField txtFechaRegistr) {
        String idS = txtId.getText();
        if(idS.isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Para eliminar primero debes buscar un cliente por Dni",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }else{
            int id = Integer.parseInt(idS);
            clienteDto.eliminarClinete(id);
            JOptionPane.showMessageDialog(null,
                    "Se elimino correctamente el cliente",
                    "Mensaje de Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            listarClientes(tabla);
            limpiarCampos(txtNombre, txtApellido, txtTelefono, txtEmail, txtId, txtDni, txtFechaRegistr);
        }
        
    }
}
