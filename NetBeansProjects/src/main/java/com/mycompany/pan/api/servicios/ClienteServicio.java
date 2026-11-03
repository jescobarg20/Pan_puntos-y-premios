
package com.mycompany.pan.api.servicios;

import com.mycompany.pan.api.modelos.Cliente;
import com.mycompany.pan.api.repositorios.ClienteRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    // Constructor
    public ClienteServicio(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    // Obtener todos los clientes
    public List<Cliente> obtenerTodos() {
        return clienteRepo.findAll();
    }

    // Buscar un cliente por ID
    public Optional<Cliente> obtenerPorId(int id) {
        return clienteRepo.findById(id);
    }

    // Registrar un cliente
    public Cliente guardar(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    // Eliminar un cliente
    public void eliminar(int id) {
        clienteRepo.deleteById(id);
    }
}
