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
     
     //Obtener todos los productos
     public ArrayList<Producto> listaProducto(){
        String query = "select * from Productos";
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            PreparedStatement buscar =conectar(query);
            var rs = buscar.executeQuery();
            if(rs.next()){
                Producto producto = new Producto();
                producto.setProductoID(rs.getInt("productoId"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio")); 
                productos.add(producto);
            }
        }catch(SQLException e){
        }
        return productos;
    }
     
    //Actualizar producto
     public void actualizarProducto(String nombre, String descripcion,double precio, int id){
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
     
    //Buscar producto
    public Producto buscarPoducto(String nombre){
        String query = "select * from Productos where nombre = ?";
        var producto = new Producto();
         
        try{
           PreparedStatement buscar =conectar(query);  
           buscar.setString(1, nombre);
           var rs = buscar.executeQuery();
           producto.setProductoID(rs.getInt("productoId"));
           producto.setNombre(rs.getString("nombre"));
           producto.setDescripcion(rs.getString("descripcion"));
           producto.setPrecio(rs.getDouble("precio")); 
        }catch(SQLException e){
        }
        return producto;
    }
    
    //Eliminar producto
    public void eliminarProducto(int id){
        String query = "delete Productos where productoId = ?";
        
        try{
            PreparedStatement buscar =conectar(query);
            buscar.setInt(1, id);
            var rs = buscar.executeUpdate();
        }catch(SQLException e){
        }
    }
     
     
}
