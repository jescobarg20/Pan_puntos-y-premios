/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.controlador;

import com.mycompany.pan.puntos.y.premios.dao.ProductoDAO;
import com.mycompany.pan.puntos.y.premios.producto;
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
@RequestMapping("/productos")
public class ProductoControlador {
    
    private final ProductoDAO productoDAO = new ProductoDAO();
    
    @GetMapping
    public String listar(Model model){
        model.addAttribute("productos", productoDAO.listarTodos());
        
        if(!model.containsAttribute(("producto"))){
            model.addAttribute("producto", new producto(0, "", "", 0.0, 0, false));
        }
        return "productos";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
       
        producto productoEncontrado = productoDAO.buscarPorId(id);
        
        if(productoEncontrado != null){
            model.addAttribute("producto", productoEncontrado);
        }else{
            model.addAttribute("error", "No se encontro el producto");
        }
        
        return "productos";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute producto producto, RedirectAttributes ra){
      
        try{
            boolean resultado;
            
            if(producto.getIdproducto() == 0){
                resultado = productoDAO.insert(producto);

            }else{
                resultado = productoDAO.actualizar(producto);
            }
            
            if(resultado){
                ra.addFlashAttribute("mensaje", "Producto guardado correctamente");
            }
        }catch (Exception e){
            ra.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/productos";
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes ra){
        
        try{
            producto productoEncontrado = productoDAO.buscarPorId(id);
            
            if(productoEncontrado != null && productoDAO.eliminar(productoEncontrado)){
                ra.addFlashAttribute("mensaje", "Producto eliminado correctamente");
            }else{
                ra.addFlashAttribute("error", "No se pudo eliminar el producto");
            }
        }catch (Exception e){
            ra.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/productos";
    }
}
