/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.dao;

import com.mycompany.pan.puntos.y.premios.ConexionDB;
import com.mycompany.pan.puntos.y.premios.abastecimiento;
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
public class AbastecimientoDAO implements CrudDAO<abastecimiento>{

    @Override
    public boolean insert(abastecimiento objeto) {
        String sql = "INSERT INTO ABASTECIMIENTO "
                   + "(Id_menu, nombreProducto, cantidad, proveedor, fecha) "
                   + "VALUES (?, ?, ?, ?, ?)";
        
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, objeto.getidMenu());
            ps.setString(2, objeto.getnombreProducto());
            ps.setInt(3, objeto.getCantidad());
            ps.setString(4, objeto.getproveedor());
            ps.setString(5, objeto.getFecha());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al ingresar abastecimientos");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(abastecimiento objeto) {
        String sql = "UPDATE ABASTECIMIENTO SET Id_menu = ?, "
                   + "nombreProducto = ?, cantidad = ?, proveedor = ?, "
                   + "fecha = ? WHERE id_abastecimiento = ?";
        
        try{
          Connection conn = ConexionDB.iniciarConexion();
          PreparedStatement ps = conn.prepareStatement(sql);
          
          ps.setInt(1, objeto.getidMenu());
          ps.setString(2, objeto.getnombreProducto());
          ps.setInt(3, objeto.getCantidad());
          ps.setString(4, objeto.getproveedor());
          ps.setString(5, objeto.getFecha());
          ps.setInt(6, objeto.getidAbastecimiento());
          
          return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al actualizar un abastecimiento");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(abastecimiento objeto) {
        String sql = "DELETE FROM ABASTECIMIENTO " 
                    + "WHERE id_abastecimiento = ?";
        
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, objeto.getidAbastecimiento());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
          System.err.println("Error al eliminar un abastecimiento");
            e.printStackTrace();
            return false;  
        }
    }

    @Override
    public abastecimiento buscarPorId(int id) {
        String sql = "SELECT id_abastecimiento, Id_menu, nombreProducto, "
                   + "cantidad, proveedor, fecha FROM ABASTECIMIENTO "
                   + "WHERE id_abastecimiento = ?";
        
        try{
           Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            
           if (rs.next()) {
                return mapearAbastecimiento(rs);
            } 
        }catch (SQLException e){
            System.err.println("Error al buscar un abastecimiento");
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public List<abastecimiento> listarTodos() {
        List<abastecimiento> lista = new ArrayList<>();
        
        String sql = "SELECT id_abastecimiento, Id_menu, nombreProducto, "
                   + "cantidad, proveedor, fecha FROM ABASTECIMIENTO";
        try{
           Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
             while (rs.next()) {
                lista.add(mapearAbastecimiento(rs));
             }
        }catch (SQLException e){
            System.err.println("Error al listar abastecimientos");
            e.printStackTrace();
        }
        return lista; 
    }
    
    private abastecimiento mapearAbastecimiento(ResultSet rs)
            throws SQLException {
        return new abastecimiento(
            rs.getInt("id_abastecimiento"),
            rs.getInt("Id_menu"),
            rs.getString("nombreProducto"),
            rs.getInt("cantidad"),
            rs.getString("proveedor"),
            rs.getString("fecha")
        );
    }
}
