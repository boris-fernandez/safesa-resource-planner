/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.dto;

import com.safesa.app.config.ConexionBD;
import com.safesa.app.model.Cliente;
import com.safesa.app.model.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author BORIS
 */
public class ProveedorDto {

    public PreparedStatement conectar(String query) throws SQLException{
        Connection con = ConexionBD.getConexion();
        return  con.prepareStatement(query);
    }
    
    // Método para agregar un proveedor
    public int agregarProveedor(String dni, String nombre, String apellidos,String telefono, String email){
        String queryPersona = "insert into Personas(nombre, apellidos, telefono, email) values(?,?,?,?)";
        String queryProveedor = "insert into Proveedores(dni, personaId) values(?,?,?)";
        int proveedorId = 0;
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
                proveedorId = rs.getInt(1);
                PreparedStatement psCliente = con.prepareStatement(queryProveedor);
                psCliente.setString(1, dni);
                psCliente.setInt(3, proveedorId);
                psCliente.executeUpdate(); 
            }
        } catch(SQLException e){
        }
        return proveedorId;
    }

    //Obtener todos los proveedor
    public ArrayList<Proveedor> listaCliente() {
        String query = "SELECT c.clienteId, nombre, apellidos, telefono, email, dni, fechaRegistro " +
                              "FROM Personas p INNER JOIN Clientes c ON p.personaId = c.personaId";
        ArrayList<Proveedor> proveedores = new ArrayList<>();

        try {
            PreparedStatement buscar =conectar(query);
            var rs = buscar.executeQuery();
            while (rs.next()) {
                var proveedor = new Proveedor();
                proveedor.setProveedorID(rs.getInt("clienteId"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setApellidos(rs.getString("apellidos"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setEmail(rs.getString("email"));
                proveedor.setDni(rs.getString("dni"));
                proveedores.add(proveedor);
            }

        } catch (SQLException e) {
        }
        return proveedores;
    }
    
    //Actualizar proveedor
    public void actualizarProveedor(String nombre, String apellidos,String telefono, String email, int id){
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
    
    //Buscar proveedor
    public Proveedor buscarProveedor(int dni){
        String query = "SELECT c.proveedorId, nombre, apellidos, telefono, email, dni" +
               "FROM Personas p INNER JOIN Proveedores c ON p.personaId = c.personaId " +
               "WHERE c.dni = ?";
        var proveedor = new Proveedor();
        try{
            PreparedStatement buscar =conectar(query);
            buscar.setInt(1, dni);
            var rs = buscar.executeQuery();
            proveedor.setProveedorID(rs.getInt("clienteId"));
            proveedor.setNombre(rs.getString("nombre"));
            proveedor.setApellidos(rs.getString("apellidos"));
            proveedor.setTelefono(rs.getString("telefono"));
            proveedor.setEmail(rs.getString("email"));
            proveedor.setDni(rs.getString("dni"));
        }catch(SQLException e){
        }
        return proveedor;
    }
    
    //Eliminar proveedor
    public void eliminarProveedor(int id){
        String query = "DELETE FROM Proveedores WHERE proveedorId = ?";
        try {
            PreparedStatement eliminar = conectar(query);
            eliminar.setInt(1, id);
        var rowsAffected = eliminar.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
}
