/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.dao;

import com.mycompany.pan.puntos.y.premios.ConexionDB;
import com.mycompany.pan.puntos.y.premios.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ejhon
 */
public class ProductoDAO implements CrudDAO<producto>{

    @Override
    public boolean insert(producto objeto) {
        String sql = "INSERT INTO PRODUCTOS "
                   + "(nombre, categoria, precio, existencia, activo) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try{
            Connection conn = ConexionDB.inciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getCategoria());
            ps.setDouble(3, objeto.getprecio());
            ps.setInt(4, objeto.getExistencia());
            ps.setBoolean(5, objeto.isActivo());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al ingresar el producto");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(producto objeto) {
        String sql = "UPDATE PRODUCTOS SET nombre = ?, categoria = ?, "
                   + "precio = ?, existencia = ?, activo = ? "
                   + "WHERE id_producto = ?";
        
        try{
            Connection conn = ConexionDB.inciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getCategoria());
            ps.setDouble(3, objeto.getIdproducto());
            ps.setInt(4, objeto.getExistencia());
            ps.setBoolean(5, objeto.isActivo());
            ps.setInt(6, objeto.getIdproducto());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al actualizar un producto");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(producto objeto) {
        String sql = "DELETE FROM PRODUCTOS WHERE id_producto = ?";
        
        try{
            Connection conn = ConexionDB.inciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, objeto.getIdproducto());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al eliminar un producto");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public producto buscarPorId(int id) {
        String sql = "SELECT id_producto, nombre, categoria, precio, "
                   + "existencia, activo FROM PRODUCTOS "
                   + "WHERE id_producto = ?";
        
        try{
            Connection conn = ConexionDB.inciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return mapearProducto(rs);
            }
        }catch (SQLException e){
            System.err.println("Error al buscar producto");
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public List<producto> listarTodos() {
        List<producto> lista = new ArrayList<>();
        
        String sql = "SELECT id_producto, nombre, categoria, precio, "
                   + "existencia, activo FROM PRODUCTOS";
        
        try{
            Connection conn = ConexionDB.inciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                lista.add(mapearProducto(rs));
            }
        }catch (SQLException e){
            System.err.println("Error al listar productos");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    private producto mapearProducto(ResultSet rs) throws SQLException{
        return new producto(
        rs.getInt("id_producto"),
            rs.getString("nombre"),
            rs.getString("categoria"),
            rs.getDouble("precio"),
            rs.getInt("existencia"),
            rs.getBoolean("activo")
        );
    }
}
