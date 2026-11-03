/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.dao;

import com.mycompany.pan.puntos.y.premios.ConexionDB;
import com.mycompany.pan.puntos.y.premios.inventario;
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
public class InventarioDAO implements CrudDAO<inventario>{

    @Override
    public boolean insert(inventario objeto) {
        String sql = "INSERT INTO INVENTARIO(Id_menu, nombre, unidades" + "VUELES (?, ?, ?)";
        
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, objeto.getidMenu());
            ps.setString(2, objeto.getnombre());
            ps.setInt(3, objeto.getunidades());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al ingresar inventario");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(inventario objeto) {
        String sql = "UPDATE INVENTARIO SET nombre = ?, unidades = ?" + "WHERE id_menu = ?";
        
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, objeto.getnombre());
            ps.setInt(2, objeto.getunidades());
            ps.setInt(3, objeto.getidMenu());
            
            return ps.executeUpdate() > 0;
       
        }catch(SQLException e){
            System.err.println("Error en la actualizacion de inventario");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(inventario objeto) {
        String sql = "DELETE FROM INVENTARIO WHERE id_menu = ?";
        
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, objeto.getidMenu());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al eliminar inventario");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public inventario buscarPorId(int id) {
        String sql = "SELECT Id_menu, nombre, unidades " + "FROM INVENTARIO WHERE Id_menu = ?";
        
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return mapearInventario(rs);
            }
        }catch (SQLException e){
            System.err.println("Error al buscar en el inventario");
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public List<inventario> listarTodos() {
        List<inventario> lista = new ArrayList<>();
        String sql = "SELECT Id_menu, nombre, unidades FROM INVENTARIO";
        
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                lista.add(mapearInventario(rs));
            }
        }catch (SQLException e){
            System.err.println("Error al listar el inventario");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    private inventario mapearInventario(ResultSet rs) throws SQLException{
        return new inventario(
        rs.getInt("Id_menu"),
        rs.getString("nombre"),
        rs.getInt("Unidades")
        );
    }
}
