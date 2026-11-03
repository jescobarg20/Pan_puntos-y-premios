/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.controlador;

import com.mycompany.pan.puntos.y.premios.abastecimiento;
import com.mycompany.pan.puntos.y.premios.dao.AbastecimientoDAO;
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
@RequestMapping
public class AbastecimientoControlador {
    private final AbastecimientoDAO abastecimientoDAO =
            new AbastecimientoDAO();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("abastecimientos",
                abastecimientoDAO.listarTodos());

        if (!model.containsAttribute("abastecimiento")) {
            model.addAttribute("abastecimiento",
                    new abastecimiento(0, 0, "", 0, "", ""));
        }

        return "abastecimiento";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {

        abastecimiento encontrado =
                abastecimientoDAO.buscarPorId(id);

        if (encontrado != null) {
            model.addAttribute("abastecimiento", encontrado);
        } else {
            model.addAttribute("error",
                    "No se encontró el abastecimiento");
        }

        model.addAttribute("abastecimientos",
                abastecimientoDAO.listarTodos());

        return "abastecimiento";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute abastecimiento abastecimiento,
            RedirectAttributes ra) {
        try {
            boolean resultado;

            if (abastecimiento.getidAbastecimiento() == 0) {
                resultado = abastecimientoDAO.insert(abastecimiento);
            } else {
                resultado = abastecimientoDAO.actualizar(abastecimiento);
            }

            if (resultado) {
                ra.addFlashAttribute("mensaje",
                        "Abastecimiento guardado correctamente");
            } else {
                ra.addFlashAttribute("error",
                        "No se pudo guardar el abastecimiento");
            }

        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/abastecimiento";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id,
                           RedirectAttributes ra) {
        try {
            abastecimiento encontrado =
                    abastecimientoDAO.buscarPorId(id);

            if (encontrado != null
                    && abastecimientoDAO.eliminar(encontrado)) {
                ra.addFlashAttribute("mensaje",
                        "Abastecimiento eliminado correctamente");
            } else {
                ra.addFlashAttribute("error",
                        "No se pudo eliminar el abastecimiento");
            }

        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/abastecimiento";
    }
}
