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
    
    public PreparedStatement conectar(String query) throws SQLException{
        Connection con = ConexionBD.getConexion();
        return  con.prepareStatement(query);
    }
    
    // Método para agregar un cliente
    public void agregarCliente(String dni, String nombre, String apellidos,String telefono, String email){
        LocalDate fechaRegistro = LocalDate.now();
        String queryPersona = "insert into Personas(nombre, apellidos, telefono, email) values(?,?,?,?)";
        String queryCliente = "insert into Clientes(dni, fechaRegistro, personaId) values(?,?,?)";
        try{
            Connection con = ConexionBD.getConexion(); 
            PreparedStatement psPersona  = con.prepareStatement(queryPersona, PreparedStatement.RETURN_GENERATED_KEYS);
            psPersona.setString(1, nombre);
            psPersona.setString(2, apellidos);
            psPersona.setString(3, telefono);
            psPersona.setString(4, email);
            psPersona.executeUpdate();

            var rs = psPersona.getGeneratedKeys();
            if(rs.next()){
                int personaID = rs.getInt(1);
                PreparedStatement psCliente = con.prepareStatement(queryCliente);
                psCliente.setString(1, dni);
                psCliente.setObject(2, fechaRegistro);
                psCliente.setInt(3, personaID);
                psCliente.executeUpdate(); 
            }
        } catch(SQLException e){
        }
    }

    //Obtener todos los clientes
    public ArrayList<Cliente> listaCliente() {
        String query = "SELECT c.clienteId, nombre, apellidos, telefono, email, dni, fechaRegistro " +
                              "FROM Personas p INNER JOIN Clientes c ON p.personaId = c.personaId";
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            PreparedStatement buscar =conectar(query);
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
    public void actualizarCliente(String nombre, String apellidos,String telefono, String email, int id){
        String query  = """
                       UPDATE Personas 
                       SET nombre = ?,
                       apellidos=?,
                       telefono = ?, 
                       email = ?
                       WHERE personaId = ?""";
     
        try{
            PreparedStatement buscar =conectar(query);          
            buscar.setString(1, nombre);
            buscar.setString(2, apellidos);
            buscar.setString(3, telefono);
            buscar.setString(4, email);
            buscar.setInt(5, id);
            var rs = buscar.executeUpdate();
        }catch(SQLException e){
        }
    }
    
    //Buscar cliente
    public Cliente buscarCliente(int dni){
        String query = "SELECT c.clienteId, nombre, apellidos, telefono, email, dni, fechaRegistro " +
               "FROM Personas p INNER JOIN Clientes c ON p.personaId = c.personaId " +
               "WHERE c.dni = ?";
        Cliente cliente = new Cliente();
        try{
            PreparedStatement buscar =conectar(query);
            buscar.setInt(1, dni);
            var rs = buscar.executeQuery();
            cliente.setClienteID(rs.getInt("clienteId"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellidos(rs.getString("apellidos"));
            cliente.setTelefono(rs.getString("telefono"));
            cliente.setEmail(rs.getString("email"));
            cliente.setDni(rs.getString("dni"));
            cliente.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
        }catch(SQLException e){
        }
        return cliente;
    }
    
    //Eliminar cliente
    public void eliminarClinete(int id){
        String query = "DELETE FROM Clientes WHERE clienteId = ?";
        try {
            PreparedStatement eliminar = conectar(query);
            eliminar.setInt(1, id);
        var rowsAffected = eliminar.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
