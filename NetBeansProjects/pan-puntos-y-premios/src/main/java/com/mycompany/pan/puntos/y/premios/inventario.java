/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios;

/**
 *
 * @author ejhon
 */
public class inventario {
    
    private int idMenu;
    private String nombre;
    private int unidades;
    
    public inventario(int idMenu, String nombre, int unidades){
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.unidades = unidades;
    }
    
    public int getidMenu(){
        return idMenu;
    }
    
    public void setidMenu(int idMenu){
        this.idMenu = idMenu;
    }
    
    public String getnombre(){
        return nombre;
    }
    
    public void setnombre(String nombre){
        this.nombre= nombre;
    }
    
    public void setunidades(int unidades){
        if(unidades >= 0){
            this.unidades = unidades;
        }else{
            System.out.println("No se pueden cargar univades negativas");
        }
    }
    
    public void mostrarInformacion(){
        System.out.println("ID del menu: " + idMenu);
        System.out.println("Nombre del producto: " + nombre);
        System.out.println("Unidades disponibles: " + unidades);
    }
}
