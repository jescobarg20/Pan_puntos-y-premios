/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.dao;

import com.mycompany.pan.puntos.y.premios.ConexionDB;
import com.mycompany.pan.puntos.y.premios.pedido;
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
public class PedidoDAO implements CrudDAO<pedido>{
String sql = "INSERT INTO PEDIDOS "
                   + "(Id_cliente, Lista_deProductos, TotalPrecio, Estado) "
                   + "VALUES (?, ?, ?, ?)";
    @Override
    public boolean insert(pedido objeto) {
        
        try{
            Connection conn = ConexionDB.inicarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, objeto.getidCliente());
            ps.setString(2, objeto.getlistadeProductos());
            ps.setDouble(3, objeto.gettotalPrecio());
            ps.setString(4, objeto.getEstado());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al ingresar el pedido");
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean actualizar(pedido objeto) {
        String sql = "UPDATE PEDIDOS SET Id_cliente = ?, "
                   + "Lista_deProductos = ?, TotalPrecio = ?, Estado = ? "
                   + "WHERE Id_pedido = ?";
        
        try{
            Connection conn = ConexionDB.inicarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, objeto.getidCliente());
            ps.setString(2, objeto.getlistadeProductos());
            ps.setDouble(3, objeto.gettotalPrecio());
            ps.setString(4, objeto.getEstado());
            ps.setInt(5, objeto.getidPedido());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al actualizar el pedido");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(pedido objeto) {
        String sql = "DELETE FROM PEDIDOS WHERE Id_pedido = ?";
        
        try{
            Connection conn = ConexionDB.inicarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, objeto.getidPedido());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al eliminar el pedido");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public pedido buscarPorId(int id) {
        String sql = "SELECT Id_pedido, Id_cliente, Lista_deProductos, "
                   + "TotalPrecio, Estado FROM PEDIDOS WHERE Id_pedido = ?";
        
        try{
            Connection conn = ConexionDB.inicarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return mapearPedido(rs);
            }
        }catch (SQLException e){
            System.err.println("Error al burcan un pedido");
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public List<pedido> listarTodos() {
        List<pedido> lista = new ArrayList<>();
        
      String sql = "SELECT Id_pedido, Id_cliente, Lista_deProductos, "
                   + "TotalPrecio, Estado FROM PEDIDOS";
      
      try{
          Connection conn = ConexionDB.inicarConexion();
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              lista.add(mapearPedido(rs));
          }
      }catch (SQLException e){
          System.err.println("Error al listar los pedidos");
          e.printStackTrace();
      }
      
      return lista;
    } 
    
    private pedido mapearPedido(ResultSet rs) throws SQLException{
        return new pedido(
        rs.getInt("Id_pedido"),
        rs.getInt("Id_cliente"),
        rs.getString("Lista_deProductos"),
        rs.getDouble("TotalPrecio"),
        rs.getString("EStado")
        );
    }
}
