/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios;

/**
 *
 * @author ejhon
 */
public class abastecimiento {
    
    private int idAbastecimiento;
    private int idMenu;
    private String nombreProducto;
    private int cantidad;
    private String proveedor;
    private String fecha; 
    
    public abastecimiento(int idAbastecimiento, int idMenu, String nombreProducto, int cantidad, String proveedor, String fecha){
        
        this.idAbastecimiento = idAbastecimiento;
        this.idMenu = idMenu;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.fecha = fecha;
    }
    
    public int getidAbastecimiento(){
        return idAbastecimiento;
    }
    
    public void setidAbastecimiento(int idAbastecimiento){
        this.idAbastecimiento = idAbastecimiento;
    }
    
    public int getidMenu(){
        return idMenu;
    }
    
    public void setidMenu(int idMenu){
        this.idMenu = idMenu;
    }
    
    public String getnombreProducto() {
        return nombreProducto;
    }

    public void setnombreProducto(String nombreProducto){
        this.nombreProducto = nombreProducto;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        if(cantidad > 0){
            this.cantidad = cantidad;
        }else{
            System.out.println("La cantidad debe ser mayor a cero");
        }
    }
    
    public String getproveedor(){
        return proveedor;
    }
    
    public void setproveedor(String proveedor){
        this.proveedor = proveedor;
    }
    
    public String getFecha(){
        return fecha;
    }
    
    public void setFecha(String fecha){ 
        this.fecha = fecha;
    }
    
    public void mostrarInformacion(){
        System.out.println("ID del abastecimiento: " + idAbastecimiento);
        System.out.println("ID del menu: " + idMenu);
        System.out.println("Producto: " + nombreProducto);
        System.out.println("Cantidad recibida: " + cantidad);
        System.out.println("Proveedores: " + proveedor);
        System.out.println("Fecha: " + fecha);
    }
}
