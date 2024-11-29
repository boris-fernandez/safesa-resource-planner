/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.safesa.app.main;

import com.safesa.app.dto.ClienteDto;
import com.safesa.app.model.Cliente;

/**
 *
 * @author BORIS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //int clienteID, String dni, String nombre, String apellidos, String telefono, String email
        var CLINETE1 = new Cliente("759841036","Boris", "Fernandez Cabrera", "974035479","borisfernandezcabrera@gmail.com");
        var cliente = new ClienteDto();
        cliente.agregarCliente(CLINETE1);
    }
    
}
