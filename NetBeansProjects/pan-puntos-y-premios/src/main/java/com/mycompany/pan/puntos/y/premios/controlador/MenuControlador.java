/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.controlador;

import com.mycompany.pan.puntos.y.premios.Menu;
import com.mycompany.pan.puntos.y.premios.dao.MenuDAO;
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
@RequestMapping("/menus")
public class MenuControlador {
    private final MenuDAO menuDAO = new MenuDAO();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("menus", menuDAO.listarTodos());

        if (!model.containsAttribute("menu")) {
            model.addAttribute("menu",
                    new Menu(0, "", 0.0, false));
        }

        return "menus";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {

        Menu encontrado = menuDAO.buscarPorId(id);

        if (encontrado != null) {
            model.addAttribute("menu", encontrado);
        } else {
            model.addAttribute("error", "No se encontró el menú");
        }

        model.addAttribute("menus", menuDAO.listarTodos());

        return "menus";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Menu menu,
                          RedirectAttributes ra) {
        try {
            boolean resultado;

            if (menu.getIdMenu() == 0) {
                resultado = menuDAO.insert(menu);
            } else {
                resultado = menuDAO.actualizar(menu);
            }

            if (resultado) {
                ra.addFlashAttribute("mensaje",
                        "Menú guardado correctamente");
            } else {
                ra.addFlashAttribute("error",
                        "No se pudo guardar el menú");
            }

        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/menus";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id,
                           RedirectAttributes ra) {
        try {
            Menu encontrado = menuDAO.buscarPorId(id);

            if (encontrado != null && menuDAO.eliminar(encontrado)) {
                ra.addFlashAttribute("mensaje",
                        "Menú eliminado correctamente");
            } else {
                ra.addFlashAttribute("error",
                        "No se pudo eliminar el menú");
            }

        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/menus";
    }
}
