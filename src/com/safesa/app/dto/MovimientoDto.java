/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.dto;

import com.safesa.app.config.ConexionBD;
import com.safesa.app.model.Cliente;
import com.safesa.app.model.Movimiento;
import com.safesa.app.model.Producto;
import com.safesa.app.model.Proveedor;
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
    private final ClienteDto clienteDto;
   
    public MovimientoDto(){
        clienteDto = new ClienteDto();
    }
    
    public PreparedStatement conectar(String query) throws SQLException{
        Connection con = ConexionBD.getConexion();
        return  con.prepareStatement(query);
    }
    
    // Agregar ingreso
    public void agregarIngreso(String nombreProducto, int cantidad, 
                                String dni, String nombre, String apellidos, String telefono, String email, String metodoPago) {
        int personaID = clienteDto.obtenerIdCliente(dni);
        if (personaID == -1) { 
            var cliente = new ClienteDto();
            personaID = cliente.agregarCliente(dni, nombre, apellidos, telefono, email);
        }

        var producto = new ProductoDto();
        double precioProducto = producto.precioProducto(nombreProducto);
        int idProducto = producto.obtenerIdProducto(nombreProducto);
        LocalDate fecha = LocalDate.now();
        String tipo = "Ingreso";
        Double monto = cantidad * precioProducto;

        String queryMovimiento = "INSERT INTO Movimientos(fecha, personaId, productoId, metodoPago, monto, cantidad, tipoMovimiento) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement insertarMovimiento = conectar(queryMovimiento);
            insertarMovimiento.setDate(1, java.sql.Date.valueOf(fecha));
            insertarMovimiento.setInt(2, personaID);
            insertarMovimiento.setInt(3, idProducto);
            insertarMovimiento.setString(4, metodoPago);
            insertarMovimiento.setDouble(5, monto);
            insertarMovimiento.setInt(6, cantidad);
            insertarMovimiento.setString(7, tipo);
            insertarMovimiento.executeUpdate();

            String queryActualizarStock = "UPDATE Productos SET stock = stock - ? WHERE productoId = ?";
            PreparedStatement actualizarStock = conectar(queryActualizarStock);
            actualizarStock.setInt(1, cantidad);
            actualizarStock.setInt(2, idProducto);
            actualizarStock.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    //Agregar egreso
    public void agregarEgreso(String nombreProducto, int cantidad, 
                          String dni, String nombre, String apellidos, 
                          String telefono, String email, String metodoPago) {
        var proveedor = new ProveedorDto();
        int personaID = proveedor.obtenerIdProveedor(dni);
        if (personaID == -1) { 
            var cliente = new ClienteDto();
            personaID = cliente.agregarCliente(dni, nombre, apellidos, telefono, email);
        }

        var producto = new ProductoDto();
        double precioProducto = producto.precioProducto(nombreProducto);
        int idProducto = producto.obtenerIdProducto(nombreProducto);
        LocalDate fecha = LocalDate.now();
        String tipo = "Egreso";
        Double monto = cantidad * precioProducto;

        String queryInsert = "INSERT INTO Movimientos(fecha, personaId, productoId, metodoPago, monto, cantidad, tipoMovimiento) VALUES (?,?,?,?,?,?,?)";
        String queryUpdateStock = "UPDATE Productos SET stock = stock + ? WHERE productoId = ?";

        try {
            PreparedStatement insertar = conectar(queryInsert);
            insertar.setDate(1, java.sql.Date.valueOf(fecha));
            insertar.setInt(2, personaID);
            insertar.setInt(3, idProducto);
            insertar.setString(4, metodoPago);
            insertar.setDouble(5, monto);
            insertar.setInt(6, cantidad);
            insertar.setString(7, tipo);
            insertar.executeUpdate();

            PreparedStatement actualizarStock = conectar(queryUpdateStock);
            actualizarStock.setInt(1, cantidad);
            actualizarStock.setInt(2, idProducto);
            actualizarStock.executeUpdate();

        } catch (SQLException e) {
        }
    }

    // Obtener todos los Movimientos
    public ArrayList<SimpleEntry<Movimiento, Cliente>> obtenerMovimientos() {
        String query = """
                    SELECT m.movimientoId, Pro.nombre AS productoNombre, m.cantidad, m.monto, 
                                           per.nombre AS personaNombre, c.dni, m.tipoMovimiento, 
                                           per.nombre AS proveedorNombre,
                                           fecha
                                    FROM Movimientos m
                                    INNER JOIN Productos Pro ON m.productoId = Pro.productoId
                                    INNER JOIN Personas per ON per.personaId = m.personaId
                                    LEFT JOIN Clientes c ON per.personaId = c.personaId
                                    LEFT JOIN Proveedores p ON per.personaId = p.personaId""";

        ArrayList<SimpleEntry<Movimiento, Cliente>> movimientos = new ArrayList<>();

        try { 
            PreparedStatement buscar = conectar(query);
            var rs = buscar.executeQuery();
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                Cliente cliente = new Cliente();
                Producto producto = new Producto();
                movimiento.setMovimientoID(rs.getInt("movimientoId"));
                movimiento.setFecha(rs.getDate("fecha").toLocalDate());
                producto.setNombre(rs.getString("productoNombre"));
                movimiento.setCantidad(rs.getInt("cantidad"));
                movimiento.setMonto(rs.getDouble("monto"));
                movimiento.setTipoMovimiento(rs.getString("tipoMovimiento"));
                cliente.setNombre(rs.getString("personaNombre")); 
                cliente.setDni(rs.getString("dni"));
                movimiento.setProducto(producto);
                movimientos.add(new SimpleEntry<>(movimiento, cliente));
            }
        } catch (SQLException e) {
        }
        return movimientos;
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
    
    //Buscar Movimiento por dni
    public SimpleEntry<Movimiento, Object> buscarMovimiento(String dni) {
        String query = """
                    SELECT m.movimientoId, Pro.nombre AS productoNombre, m.cantidad, m.monto, 
                           per.nombre AS personaNombre, c.dni, m.tipoMovimiento, 
                           per.nombre AS proveedorNombre,
                           fecha
                    FROM Movimientos m
                    INNER JOIN Productos Pro ON m.productoId = Pro.productoId
                    INNER JOIN Personas per ON per.personaId = m.personaId
                    LEFT JOIN Clientes c ON per.personaId = c.personaId
                    LEFT JOIN Proveedores p ON per.personaId = p.personaId
                    WHERE c.dni = ? or p.dni = ?;
                """;

        SimpleEntry<Movimiento, Object> movimientoEncontrado = null; 

        try {
            PreparedStatement buscar = conectar(query);
            buscar.setString(1, dni);
            buscar.setString(2, dni); 
            var rs = buscar.executeQuery();

            if (rs.next()) {
                Movimiento movimiento = new Movimiento();
                Object persona = null;
                Producto producto = new Producto();

                movimiento.setMovimientoID(rs.getInt("movimientoId"));
                producto.setNombre(rs.getString("productoNombre"));
                movimiento.setCantidad(rs.getInt("cantidad"));
                movimiento.setMonto(rs.getDouble("monto"));
                movimiento.setTipoMovimiento(rs.getString("tipoMovimiento"));
                movimiento.setFecha(rs.getDate("fecha").toLocalDate());
                String proveedorNombre = rs.getString("proveedorNombre");
                if (proveedorNombre != null) {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setNombre(proveedorNombre);
                    persona = proveedor;
                } else {
                    Cliente cliente = new Cliente();
                    cliente.setNombre(rs.getString("personaNombre"));
                    cliente.setDni(rs.getString("dni"));
                    persona = cliente;
                }
                movimiento.setProducto(producto);
                movimientoEncontrado = new SimpleEntry<>(movimiento, persona);
            }
        } catch (SQLException e) {
        }
        return movimientoEncontrado;
    }
   
   //Obtener el monto total de ingreso
   public double montoTotalIngreso(){
        String query = "SELECT SUM(monto) AS [MontoIngreso] FROM Movimientos WHERE tipoMovimiento = 'Ingreso'";
        double totalIngreso = 0;
        try{
            PreparedStatement buscar = conectar(query);
            var rs = buscar.executeQuery();
            if(rs.next()){
                totalIngreso = rs.getDouble("MontoIngreso");
            }
        }catch(SQLException e){
        }
        return totalIngreso;
   }
   
   //Obtener el monto total de egreso
   public double montoTotalEgreso(){
        String query = "SELECT SUM(monto) AS [MontoEgreso] FROM Movimientos WHERE tipoMovimiento = 'Egreso'";
        double totalIngreso = 0;
        try{
            PreparedStatement buscar = conectar(query);
            var rs = buscar.executeQuery();
            if(rs.next()){
                totalIngreso = rs.getDouble("MontoEgreso");
            }
        }catch(SQLException e){
        }
        return totalIngreso;
   }
   
   //Obtener el monto total general
   public double montoTotalGenera(){
        String query = """
                       SELECT 
                           (SUM(CASE WHEN tipoMovimiento = 'Ingreso' THEN monto ELSE 0 END) -
                            SUM(CASE WHEN tipoMovimiento = 'Egreso' THEN monto ELSE 0 END)) AS TotalGeneral
                       FROM Movimientos;""";
        double totalIngreso = 0;
        try{
            PreparedStatement buscar = conectar(query);
            var rs = buscar.executeQuery();
            if(rs.next()){
                totalIngreso = rs.getDouble("TotalGeneral");
            }
        }catch(SQLException e){
        }
        return totalIngreso;
   }
   
    
    
}
