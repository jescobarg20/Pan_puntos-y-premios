/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.controlador;

import com.mycompany.pan.puntos.y.premios.clientes;
import com.mycompany.pan.puntos.y.premios.dao.ClientesDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ejhon
 */

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {
    
    private final ClientesDAO clienteDAO = new ClientesDAO();
    
    @GetMapping
    public String listar(Model model){
        model.addAttribute("clientes",clienteDAO.listarTodos());
        
        if(!model.containsAttribute("clientes")){
            model.addAttribute("cliente", new clientes(0, "", "", 0));
        }
        
        return "clientes";
    }
    
    @GetMapping("/editar/{id]")
    public String editar(@PathVariable int id, Model model){
        clientes clienteEncontrado = clienteDAO.buscarPorId(id);
        
        if(clienteEncontrado !=null){
            model.addAttribute("cliente", clienteEncontrado);
        }else{
            model.addAttribute("error", "No se encontro al cliente");
        }
        
        model.addAttribute("clientes", clienteDAO.listarTodos());
        
        return "clientes";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute clientes cliente, RedirectAttributes ra){
        
        try{
            boolean resultado;
            
            if(cliente.getIdcliente() == 0){
                resultado = clienteDAO.insert(cliente);
            }else{
                resultado = clienteDAO.actualizar(cliente);
            }
            
            if(resultado){
                ra.addFlashAttribute("mensaje", "Cliente se ha guardado correctamente");
            }else{
                ra.addFlashAttribute("error", "No se pudo guardar el cliente");
            }
        }catch(Exception e){
            ra.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/clientes";
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes ra){
        
        try{
            clientes cliente = clienteDAO.buscarPorId(id);
            
            if(cliente != null && clienteDAO.eliminar(cliente)){
                ra.addFlashAttribute("mensaje", "Cliente se ha eliminado correctamente");
            }else{
                ra.addFlashAttribute("error", "No se pudo eliminar el cliente");
            }
        }catch (Exception e){
            ra.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/clientes";
    }
}
