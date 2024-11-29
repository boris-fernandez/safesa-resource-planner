/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.dto;
//hola

import com.safesa.app.config.ConexionBD;
import com.safesa.app.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author BORIS
 */
public class ClienteDto {
    // Método para agregar un cliente
    public void agregarCliente(Cliente cliente){
        LocalDate fechaRegistro = LocalDate.now();
        String queryPersona = "insert into Personas(nombre, apellidos, telefono, email) values(?,?,?,?)";
        String queryCliente = "insert into Clientes(dni, fechaRegistro, personaId) values(?,?,?)";
        try{
            Connection con = ConexionBD.getConexion(); 
            PreparedStatement psPersona  = con.prepareStatement(queryPersona, PreparedStatement.RETURN_GENERATED_KEYS);
            psPersona.setString(1, cliente.getNombre());
            psPersona.setString(2, cliente.getApellidos());
            psPersona.setString(3, cliente.getTelefono());
            psPersona.setString(4, cliente.getEmail());
            psPersona.executeUpdate();

            var rs = psPersona.getGeneratedKeys();
            if(rs.next()){
                int personaID = rs.getInt(1);
                PreparedStatement psCliente = con.prepareStatement(queryCliente);
                psCliente.setString(1, cliente.getDni());
                psCliente.setObject(2, cliente.getFechaRegistro());
                psCliente.setInt(3, personaID);
                psCliente.executeUpdate(); 
            }
        } catch(SQLException e){
        }
    }

    // Método para obtener todos los clientes y devolverlos en un ArrayList
    public ArrayList<Cliente> listaCliente() {
        String query = "SELECT c.clienteId, nombre, apellidos, telefono, email, dni, fechaRegistro " +
                              "FROM Personas p INNER JOIN Clientes c ON p.personaId = c.personaId";
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement buscar = con.prepareStatement(query);
            var rs = buscar.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteID(rs.getInt("clienteId"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDni(rs.getString("dni"));
                cliente.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                clientes.add(cliente);
            }

        } catch (SQLException e) {
        }
        return clientes;
    }
    
    //Actualizar Cliente
    public void ActualizarCliente(Cliente cliente, int id){
        String querypersona  = """
                       UPDATE Personas 
                       SET nombre = ?,
                       apellidos=?,
                       telefono = ?, 
                       email = ?
                       WHERE personaId = ?""";
     
        try{
            Connection con = ConexionBD.getConexion();
            PreparedStatement buscar = con.prepareStatement(querypersona);            
            buscar.setString(1, cliente.getNombre());
            buscar.setString(2, cliente.getApellidos());
            buscar.setString(3, cliente.getTelefono());
            buscar.setString(4, cliente.getEmail());
            buscar.setInt(5, id);
            var rs = buscar.executeUpdate();
        }catch(SQLException e){
        }
    }
    
    //Buscar cliente por DNI
    public Cliente buscarCliente(int dni){
        String query = "SELECT c.clienteId, nombre, apellidos, telefono, email, dni, fechaRegistro " +
               "FROM Personas p INNER JOIN Clientes c ON p.personaId = c.personaId " +
               "WHERE c.dni = ?";
        Cliente cliente = new Cliente();
        try{
            Connection con = ConexionBD.getConexion();
            PreparedStatement buscar = con.prepareStatement(query); 
            buscar.setInt(1, dni);
            var rs = buscar.executeQuery();
            if (rs.next()) {
                cliente.setClienteID(rs.getInt("clienteId"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDni(rs.getString("dni"));
                cliente.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
            }
        }catch(SQLException e){
        }
        return cliente;
    }
}
