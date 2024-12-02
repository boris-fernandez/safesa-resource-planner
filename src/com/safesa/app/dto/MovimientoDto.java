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
    private ClienteDto clienteDto;
   
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
            personaID = cliente.agregarCliente(dni, nombre, apellidos, telefono,email);
        }

        var producto = new ProductoDto();
        double precioProducto = producto.precioProducto(nombreProducto);
        int idProducto = producto.obtenerIdProducto(nombreProducto);
        LocalDate fecha = LocalDate.now();
        String tipo = "Ingreso";
        Double monto = cantidad * precioProducto;
        
        String query = "INSERT INTO Movimientos(fecha, personaId, productoId, metodoPago, monto, cantidad, tipoMovimiento) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement buscar = conectar(query)) {
            buscar.setDate(1, java.sql.Date.valueOf(fecha));
            buscar.setInt(2, personaID);
            buscar.setInt(3, idProducto);
            buscar.setString(4, metodoPago);
            buscar.setDouble(5, monto);
            buscar.setInt(6, cantidad);
            buscar.setString(7, tipo);
            var rs = buscar.executeUpdate(); 
        } catch (SQLException e) {
        }
    }
    
    //Agregar egreso
    public void agregarEgreso(String nombreProducto, int cantidad, 
                            String dni, String nombre, String apellidos, String telefono, String email, String metodoPago){
        var proveedor = new ProveedorDto();
        int personaID = proveedor.obtenerIdProveedor(dni);
        if (personaID == -1) { 
            var cliente = new ClienteDto();
            personaID = cliente.agregarCliente(dni, nombre, apellidos, telefono,email);
        }
        var producto = new ProductoDto();
        double precioProducto = producto.precioProducto(nombreProducto);
        int idProducto = producto.obtenerIdProducto(nombreProducto);
        LocalDate fecha = LocalDate.now();
        String tipo = "Egreso";
        Double monto = cantidad * precioProducto;
        String query = "INSERT INTO Movimientos(fecha, personaId, productoId, metodoPago, monto, cantidad, tipoMovimiento) VALUES (?,?,?,?,?,?,?)";
        
        try (PreparedStatement buscar = conectar(query)) {
            buscar.setDate(1, java.sql.Date.valueOf(fecha));
            buscar.setInt(2, personaID);
            buscar.setInt(3, idProducto);
            buscar.setString(4, metodoPago);
            buscar.setDouble(5, monto);
            buscar.setInt(6, cantidad);
            buscar.setString(7, tipo);
            var rs = buscar.executeUpdate(); 
        } catch (SQLException e) {
        }
    }
    
    
    // Obtener todos los Movimientos
    public ArrayList<SimpleEntry<Movimiento, Cliente>> obtenerMovimientos() {
    String query = """
                SELECT m.movimientoId, Pro.nombre AS productoNombre, m.cantidad, m.monto, 
                                       per.nombre AS personaNombre, c.dni, m.tipoMovimiento, 
                                       per.nombre AS proveedorNombre
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
    
   public SimpleEntry<Movimiento, Object> buscarMovimiento(String dni) {
        String query = """
                    SELECT m.movimientoId, Pro.nombre AS productoNombre, m.cantidad, m.monto, 
                           per.nombre AS personaNombre, c.dni, m.tipoMovimiento, 
                           per.nombre AS proveedorNombre
                    FROM Movimientos m
                    INNER JOIN Productos Pro ON m.productoId = Pro.productoId
                    INNER JOIN Personas per ON per.personaId = m.personaId
                    LEFT JOIN Clientes c ON per.personaId = c.personaId
                    LEFT JOIN Proveedores p ON per.personaId = p.personaId
                    WHERE c.dni = ?;
                """;

        SimpleEntry<Movimiento, Object> movimientoEncontrado = null; 

        try {
            PreparedStatement buscar = conectar(query);
            buscar.setString(1, dni);  // Usar el dni como parámetro
            var rs = buscar.executeQuery();

            if (rs.next()) {  // Solo obtenemos el primer resultado
                Movimiento movimiento = new Movimiento();
                Object persona = null;  // Puede ser un Cliente o un Proveedor
                Producto producto = new Producto();

                // Set Movimiento attributes
                movimiento.setMovimientoID(rs.getInt("movimientoId"));
                producto.setNombre(rs.getString("productoNombre"));
                movimiento.setCantidad(rs.getInt("cantidad"));
                movimiento.setMonto(rs.getDouble("monto"));
                movimiento.setTipoMovimiento(rs.getString("tipoMovimiento"));

                // Verificar si es un Cliente o un Proveedor y asignar
                String proveedorNombre = rs.getString("proveedorNombre");
                if (proveedorNombre != null) {
                    // Es un Proveedor (Egreso)
                    Proveedor proveedor = new Proveedor();
                    proveedor.setNombre(proveedorNombre);
                    persona = proveedor;
                } else {
                    // Es un Cliente (Ingreso)
                    Cliente cliente = new Cliente();
                    cliente.setNombre(rs.getString("personaNombre"));
                    cliente.setDni(rs.getString("dni"));
                    persona = cliente;
                }

                // Asignar Producto al Movimiento
                movimiento.setProducto(producto);

                // Guardar el movimiento encontrado y su asociado (Cliente o Proveedor)
                movimientoEncontrado = new SimpleEntry<>(movimiento, persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimientoEncontrado;
    }
    
    
    
}
