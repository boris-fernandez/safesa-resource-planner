/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.dto;

import com.safesa.app.config.ConexionBD;
import com.safesa.app.model.Cliente;
import com.safesa.app.model.Movimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
/**
 *
 * @author BORIS
 */
public class MovimientoDto {
   
    public PreparedStatement conectar(String query) throws SQLException{
        Connection con = ConexionBD.getConexion();
        return  con.prepareStatement(query);
    }
    
    //Agregar ingreso
    public void agregarEgreso(int idProducto ,String nombreProducto, int cantidad,String metodoPago, String dni, String nombre, String apellidos,String telefono, String email){
        var cliente = new ClienteDto();
        int personaID = cliente.agregarCliente(dni, nombre, apellidos, telefono, email);
        var producto = new ProductoDto();
        double precioProducto = producto.precioProducto(idProducto);
        LocalDate fecha = LocalDate.now();
        String tipo = "Ingreso";
        Double monto = cantidad * precioProducto;
        String query = "insert into Movimientos(metodoPago, fecha, personaId, productoId, tipo, monto, cantidad) value (?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement buscar = conectar(query);
            buscar.setString(1, metodoPago);
            buscar.setDate(2, java.sql.Date.valueOf(fecha));
            buscar.setInt(3, personaID);
            buscar.setInt(4, idProducto);
            buscar.setString(5, tipo);
            buscar.setDouble(6, monto);
            buscar.setInt(7, cantidad);
        }catch(SQLException e){
        }
    }
    
    //Agregar egreso
    public void agregarIngreso(int idProducto ,String nombreProducto, int cantidad,String metodoPago, String dni, String nombre, String apellidos,String telefono, String email){
        var proveedor = new ProveedorDto();
        int personaID = proveedor.agregarProveedor(dni, nombre, apellidos, telefono, email);
        var producto = new ProductoDto();
        double precioProducto = producto.precioProducto(idProducto);
        LocalDate fecha = LocalDate.now();
        String tipo = "Egreso";
        Double monto = cantidad * precioProducto;
        String query = "insert into Movimientos(metodoPago, fecha, personaId, productoId, tipo, monto, cantidad) value (?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement buscar = conectar(query);
            buscar.setString(1, metodoPago);
            buscar.setDate(2, java.sql.Date.valueOf(fecha));
            buscar.setInt(3, personaID);
            buscar.setInt(4, idProducto);
            buscar.setString(5, tipo);
            buscar.setDouble(6, monto);
            buscar.setInt(7, cantidad);
        }catch(SQLException e){
        }
    }
    
    
    //Obtener todos los Movimientos
    public ArrayList<SimpleEntry<Movimiento, Cliente>> obtenerMovimientos() {
        String query = """
                        SELECT m.movimientoId, m.cantidad, m.monto, pe.nombre, pe.dni, m.tipo
                        FROM Movimientos m
                        INNER JOIN Productos Pro ON m.movimientoId = Pro.movimientoId
                        INNER JOIN Personas pe ON m.personaId = pe.personaId""";
        ArrayList<SimpleEntry<Movimiento, Cliente>> movimientos = new ArrayList<>();

        try{ 
            PreparedStatement buscar = conectar(query);
            var rs = buscar.executeQuery();
            Movimiento movimiento = new Movimiento();
            Cliente cliente = new Cliente();
            movimiento.setMovimientoID(rs.getInt("movimientoId"));
            movimiento.setCantidad(rs.getInt("cantidad"));
            movimiento.setMonto(rs.getDouble("monto"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setDni(rs.getString("dni"));
            movimientos.add(new SimpleEntry<>(movimiento, cliente));
        } catch (SQLException e) {
        }

        return movimientos;
    }

    //Actualizar Movimiento
    public void actualizarMovimiento(String metodoPago, String tipoMovimiento, int id) {
        String query = "UPDATE Movimientos SET metodoPago = ?, tipoMovimiento = ? WHERE movimientoId = ?";
        Movimiento movimiento = new Movimiento();
        
        try{
            PreparedStatement buscar = conectar(query);
            buscar.setString(1, metodoPago);
            buscar.setString(2, tipoMovimiento);
            buscar.setInt(3, id);
            var rs = buscar.executeUpdate();
            movimiento.setMovimientoID(id);
            movimiento.setMetodoPago(metodoPago);
            movimiento.setTipoMovimiento(tipoMovimiento);
        } catch (SQLException e) {
        }
    }

    //Eliminar Movimineto
    public void eliminarMovimiento(int id){
        String query = "delete Movimientos where movimientoId = ?";
        
        try{
            PreparedStatement buscar =conectar(query);
            buscar.setInt(1, id);
            var rs = buscar.executeUpdate();
        }catch(SQLException e){
        }
    }
    
}
