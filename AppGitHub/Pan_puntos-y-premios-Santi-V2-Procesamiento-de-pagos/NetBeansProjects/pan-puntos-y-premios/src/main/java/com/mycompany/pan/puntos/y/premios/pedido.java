/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios;

/**
 *
 * @author ejhon
 */
public class pedido {
    
    private int idPedido;
    private int idCliente;
    private String listadeProductos;
    private double totalPrecio;
    private String estado;
    
    public pedido(int idPedido, int idCliente, String listadeProductos, double totalPrecio, String estado){
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.listadeProductos = listadeProductos;
        this.totalPrecio = totalPrecio;
        this.estado = estado;
    }
    
    public int getidPedido(){
        return idPedido;
    }
    
    public int getidCliente(){
        return idCliente;
    }
    
    public String getlistadeProductos(){
        return listadeProductos;
    }
    
    public void setlistadeProductos(String listadeproductos){
        this.listadeProductos = listadeProductos;
    }
    
    public double gettotalPrecio(){
        return totalPrecio;
    }
    
    public void settotalPrecio(double totalPrecio){
        if(totalPrecio >= 0){
            this.totalPrecio = totalPrecio;
        }else{
            System.out.println("El total no puede ser negativo");
        }
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public void mostrarInformacion(){
        System.out.println(" ID del pedido: " + idPedido);
        System.out.println("ID del cliente: " + idCliente);
        System.out.println("Productos: " + listadeProductos);
        System.out.println("Estado: " + estado);
    }
}
