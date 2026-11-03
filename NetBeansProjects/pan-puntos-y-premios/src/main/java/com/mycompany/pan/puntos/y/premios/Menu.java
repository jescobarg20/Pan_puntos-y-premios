/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios;

/**
 *
 * @author ejhon
 */
public class Menu {
    
    private int idMenu;
    private String nombre;
    private double precio;
    private boolean individual;
    
    public Menu(int idMenu, String nombre, double precio, boolean individual){
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.precio = precio;
        this.individual = individual;
    }
    
    public int getIdMenu(){
        return idMenu;
    }
    
    public void setIdMenu(int idMenu){
        this.idMenu = idMenu;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public void setPrecio(double precio){
        this.precio = precio;
    }
    
    public boolean isIndividual(){
        return individual;
    }
    
    public void setIdividual(boolean individual){
        this.individual = individual;
    }
    
    public void mostrarInformacion(){
        System.out.println("ID del menu: " + idMenu);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: Q" + precio);
        System.out.println("Individual: " + individual);
    }
}
