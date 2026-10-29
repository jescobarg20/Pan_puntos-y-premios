/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.dao;

import com.mycompany.pan.puntos.y.premios.ConexionDB;
import com.mycompany.pan.puntos.y.premios.clientes;
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
public class ClientesDAO implements CrudDAO<clientes>{

    @Override
    public boolean insert(clientes objeto) {
        
        String sql = "INSERT INTO CLIENTES (nombre, apellidos, puntos" + "values(?, ?, ?)";
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getApellidos());
            ps.setInt(3, objeto.getPuntos());
            
            return ps.executeUpdate() > 0;
            
        }catch (SQLException e){
            System.err.println("Error al ingresar un cliente");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(clientes objeto) {
        String sql = "UPDATE CLIENTES SET nombres = ?, apellidos = ?, " + "puntos = ? WHERE id_cliente = ?";
        
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getApellidos());
            ps.setInt(3, objeto.getPuntos());
            ps.setInt(4, objeto.getIdcliente());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al actualizar a este cliente");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(clientes objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public clientes buscarPorId(int id) {
        String sql = "DELETE FROM CLIENTES WHERE Id_cliente = ?";
        
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return mapearCliente(rs);
            }
        }catch (SQLException e){
            System.err.println("Error al tratar de eliminar al cliente");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<clientes> listarTodos() {
        List<clientes> lista = new ArrayList<>();
        String sql = "SELEC Id_cliente, nombres, apellidos, puntos " + "FROM CLIENTES";
        
        try{
            Connection conn = ConexionDB.iniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                lista.add(mapearCliente(rs));
            }
        }catch (SQLException e){
            System.err.println("Error al listar al cliente");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    private clientes mapearCliente(ResultSet rs) throws SQLException{
        return new clientes(
        rs.getInt("Id_cliente"),
        rs.getString("nombres"),
        rs.getString("apellidos"),
        rs.getInt("puntos")
        );
    }
    
}
