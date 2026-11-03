/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios;

/**
 *
 * @author ejhon
 */
public class Persona {
    
    protected String nombre;
    protected String apellidos;
    
    public Persona(String nombre, String apellidos){
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellidos(){
        return apellidos;
    }
    
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    
    public void mostrarInformacion(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
    }
}
