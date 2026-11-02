/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios;

/**
 *
 * @author ejhon
 */
public class clientes extends Persona {
    
    private int idcliente;
    private int puntos;
    
    public clientes(int idcliente, String nombre, String apellidos, int puntos) {
        super(nombre, apellidos);
        
        this.idcliente = idcliente;
        this.puntos = puntos;
    }
    
    public int getIdcliente(){
        return idcliente;
    }
    
    public void setIdcliente(int idcliente){
        this.idcliente = idcliente;
    }
    
    public int getPuntos(){
       return puntos;
    }
    
    public void setPuntos(int puntos){
        this.puntos = puntos;
    }
    
     @Override
    public void mostrarInformacion(){
        System.out.println("ID cliente: " + idcliente);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.println("Puntos: " + puntos);
    }
}
