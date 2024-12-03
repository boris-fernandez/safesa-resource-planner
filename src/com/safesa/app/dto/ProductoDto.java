/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safesa.app.dto;

import com.safesa.app.config.ConexionBD;
import com.safesa.app.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author BORIS
 */
public class ProductoDto {
    //Agregar nuevo producto
    
    public PreparedStatement conectar(String query) throws SQLException{
        Connection con = ConexionBD.getConexion();
        return  con.prepareStatement(query);
    }
    
    public void agregarProducto(String nombre, String descripcion, double precio, int stock){
        String query = "insert into Productos(nombre,descripcion,precio,stock) values(?,?,?,?)";
        try{
            PreparedStatement buscar =conectar(query);
            buscar.setString(1, nombre);
            buscar.setString(2, descripcion);
            buscar.setDouble(3, precio);
            buscar.setInt(4, stock);
            buscar.executeUpdate();
        }catch(SQLException e){
        }
    }
     
     //Obtener todos los productos con todas sus caracteristicas
     public ArrayList<Producto> listaProducto(){
        String query = "select * from Productos";
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            PreparedStatement buscar =conectar(query);
            var rs = buscar.executeQuery();
            while(rs.next()){
                Producto producto = new Producto();
                producto.setProductoID(rs.getInt("productoId"));
                producto.setNombre(rs.getString("nombre"));
                producto.setStock(rs.getInt("stock"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio")); 
                producto.setDescripcion(rs.getString("descripcion"));
                productos.add(producto);
            }
        }catch(SQLException e){
        }
        return productos;
    }
     
    //Obtener todos los productos por nombre
    public ArrayList<String> listaProductoPorNombre() {
        String query = "SELECT nombre FROM Productos";
        ArrayList<String> productos = new ArrayList<>();
        try{
            PreparedStatement buscar = conectar(query);
            var rs = buscar.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                productos.add(nombre);
            }
        } catch (SQLException e) {
        }
        return productos;
    }
     
    //Actualizar producto
     public void actualizarProducto(String nombre,String descripcion,double precio, int id){
        String query  = """
                        update Productos 
                        set nombre = ?,
                        descripcion = ?,
                        precio = ? 
                        where productoId = ?""";
     
        try{
            PreparedStatement buscar =conectar(query);        
            buscar.setString(1, nombre);
            buscar.setString(2, descripcion);
            buscar.setDouble(3, precio);
            buscar.setInt(4, id);
            var rs = buscar.executeUpdate();
        }catch(SQLException e){
        }
    }
     
    //Buscar producto por nombre
    public Producto buscarProductoPorNombre(String nombre) {
        String query = "SELECT * FROM Productos WHERE nombre like ?";
        Producto producto = null; 

        try {
            PreparedStatement buscar = conectar(query);  
            buscar.setString(1, "%" + nombre + "%");
            var rs = buscar.executeQuery();
            
            if (rs.next()) {
                producto = new Producto(); 
                producto.setProductoID(rs.getInt("productoId"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));  
            }
        } catch (SQLException e) {
        }
        return producto;
    }
    
    //Eliminar producto
    public void eliminarProducto(int id) {
        String queryEliminarMovimientos = "DELETE FROM Movimientos WHERE productoId = ?";

        try {
            PreparedStatement eliminarMovimientos = conectar(queryEliminarMovimientos);
            eliminarMovimientos.setInt(1, id);
            eliminarMovimientos.executeUpdate();
            String queryEliminarProducto = "DELETE FROM Productos WHERE productoID = ?";
            PreparedStatement eliminarProducto = conectar(queryEliminarProducto);
            eliminarProducto.setInt(1, id);
            int filasAfectadas = eliminarProducto.executeUpdate();
        } catch (SQLException e) {
        }
    }

     
    // Obtener precio producto
    public double precioProducto(String nombre) {
        String query = "SELECT precio FROM Productos WHERE nombre = ?";
        double precio = 0;

        try {
            PreparedStatement buscar = conectar(query);
            buscar.setString(1, nombre);
            var rs = buscar.executeQuery();
            if (rs.next()) {
                precio = rs.getDouble("precio");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
        }
        return precio;
    }

    
    
    // Obtener ID del producto por nombre
    public int obtenerIdProducto(String nombre) {
        String query = "SELECT productoId FROM Productos WHERE nombre = ?";
        int productId = 0;

        try (PreparedStatement buscar = conectar(query)) {
            buscar.setString(1, nombre);
            var rs = buscar.executeQuery();

            if (rs.next()) {
                productId = rs.getInt("productoId");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
        }

        return productId;
    }     
    
    public int obtenerStockProducto(String nombre) {
        String query = "SELECT stock FROM Productos WHERE nombre = ?";
        int stock = 0;

        try (PreparedStatement buscar = conectar(query)) {
            buscar.setString(1, nombre);
            var rs = buscar.executeQuery();

            if (rs.next()) {
                stock = rs.getInt("stock");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
        }

        return stock;
    }   
    
}
