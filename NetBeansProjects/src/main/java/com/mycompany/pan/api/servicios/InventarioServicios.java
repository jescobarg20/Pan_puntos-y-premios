/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.api.servicios;

/**
 *
 * @author santi
 */

import com.mycompany.pan.api.modelos.Inventario;
import com.mycompany.pan.api.repositorios.InventarioRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class InventarioServicios {

    private final InventarioRepo inventarioRepo;

    public InventarioServicios(InventarioRepo inventarioRepo) {
        this.inventarioRepo = inventarioRepo;
    }

    public List<Inventario> obtenerTodos() {
        return inventarioRepo.findAll();
    }

    public Optional<Inventario> obtenerPorId(int id) {
        return inventarioRepo.findById(id);
    }

    public Inventario guardar(Inventario inventario) {
        return inventarioRepo.save(inventario);
    }

    public void eliminar(int id) {
        inventarioRepo.deleteById(id);
    }
}