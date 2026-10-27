/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios;

/**
 *
 * @author ejhon
 */
public class producto {
    
    private int idproducto;
    private String nombre;
    private String categoria;
    private double precio;
    private int existencia;
    private boolean activo;
    
    public producto(int idproducto, String nombre, String categoria, double precio, int existencia, boolean activo){
        
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.existencia = existencia;
        this.activo = activo;
    }
    
    public int getIdproducto(){
        return idproducto;
    }
    
    public void setIdproducto(int idproducto){
        this.idproducto = idproducto;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getCategoria(){
        return categoria;
    }
    
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    
    public double getprecio(){
        return precio;
    }
    
    public void setprecio(double precio){
        this.precio = precio;
    }
    
    public int getExistencia(){
        return existencia;
    }
    
    public void setExistencia(int existencia){
        this.existencia = existencia;
    }
    
    public boolean isActivo(){
        return activo;
    }
    
    public void mostrarInformacion(){
        System.out.println("ID del producto: " + idproducto);
        System.out.println("Nombre: " + nombre);
        System.out.println("Categoria: " + categoria);
        System.out.println("Existencias: " + existencia);
        System.out.println("Estado: " + (activo ? "Activo" : "Inactivo"));
    }
}
