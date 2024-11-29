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

/**
 *
 * @author BORIS
 */
public class ProductoDto {
    //Agregar nuevo producto
     public void agregarProducto(Producto prodcuto){
         String query = "insert into Productos(nombre,descripcion,precio,stock) values(?,?,?,?)";
         try{
            Connection con = ConexionBD.getConexion();
            PreparedStatement psProducto = con.prepareStatement(query);
            psProducto.setString(1, prodcuto.getNombre());
            psProducto.setString(2, prodcuto.getDescripcion());
            psProducto.setDouble(3, prodcuto.getPrecio());
            psProducto.setInt(4, prodcuto.getStock());
            psProducto.executeUpdate();
         }catch(SQLException e){
         }
     }
}
