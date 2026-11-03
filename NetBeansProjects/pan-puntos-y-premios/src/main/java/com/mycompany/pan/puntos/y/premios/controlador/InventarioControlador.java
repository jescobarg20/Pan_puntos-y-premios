/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.controlador;

import com.mycompany.pan.puntos.y.premios.dao.InventarioDAO;
import com.mycompany.pan.puntos.y.premios.inventario;
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
@RequestMapping("/inventario")
public class InventarioControlador {

    private final InventarioDAO inventarioDAO = new InventarioDAO();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("inventarios", inventarioDAO.listarTodos());

        if (!model.containsAttribute("inventario")) {
            model.addAttribute("inventario",
                    new inventario(0, "", 0));
        }

        return "inventario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {

        inventario inventarioEncontrado =
                inventarioDAO.buscarPorId(id);

        if (inventarioEncontrado != null) {
            model.addAttribute("inventario", inventarioEncontrado);
        } else {
            model.addAttribute("error",
                    "No se encontró el registro de inventario");
        }

        model.addAttribute("inventarios", inventarioDAO.listarTodos());

        return "inventario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute inventario inventario,
                          RedirectAttributes ra) {
        try {
            boolean resultado;

            if (inventario.getidMenu() == 0) {
                resultado = inventarioDAO.insert(inventario);
            } else {
                resultado = inventarioDAO.actualizar(inventario);
            }

            if (resultado) {
                ra.addFlashAttribute("mensaje",
                        "Inventario guardado correctamente");
            } else {
                ra.addFlashAttribute("error",
                        "No se pudo guardar el inventario");
            }

        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/inventario";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id,
                           RedirectAttributes ra) {
        try {
            inventario inventarioEncontrado =
                    inventarioDAO.buscarPorId(id);

            if (inventarioEncontrado != null
                    && inventarioDAO.eliminar(inventarioEncontrado)) {
                ra.addFlashAttribute("mensaje",
                        "Registro eliminado correctamente");
            } else {
                ra.addFlashAttribute("error",
                        "No se pudo eliminar el registro");
            }

        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/inventario";
    }
}
