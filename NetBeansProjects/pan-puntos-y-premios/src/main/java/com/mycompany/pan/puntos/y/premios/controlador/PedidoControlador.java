/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pan.puntos.y.premios.controlador;

import com.mycompany.pan.puntos.y.premios.dao.PedidoDAO;
import com.mycompany.pan.puntos.y.premios.pedido;
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
@RequestMapping("/pedidos")
public class PedidoControlador {
     private final PedidoDAO pedidoDAO = new PedidoDAO();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoDAO.listarTodos());

        if (!model.containsAttribute("pedido")) {
            model.addAttribute("pedido",
                    new pedido(0, 0, "", 0.0, ""));
        }

        return "pedidos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {

        pedido encontrado = pedidoDAO.buscarPorId(id);

        if (encontrado != null) {
            model.addAttribute("pedido", encontrado);
        } else {
            model.addAttribute("error", "No se encontró el pedido");
        }

        model.addAttribute("pedidos", pedidoDAO.listarTodos());

        return "pedidos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute pedido pedido,
                          RedirectAttributes ra) {
        try {
            boolean resultado;

            if (pedido.getidPedido() == 0) {
                resultado = pedidoDAO.insert(pedido);
            } else {
                resultado = pedidoDAO.actualizar(pedido);
            }

            if (resultado) {
                ra.addFlashAttribute("mensaje",
                        "Pedido guardado correctamente");
            } else {
                ra.addFlashAttribute("error",
                        "No se pudo guardar el pedido");
            }

        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/pedidos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id,
                           RedirectAttributes ra) {
        try {
            pedido encontrado = pedidoDAO.buscarPorId(id);

            if (encontrado != null && pedidoDAO.eliminar(encontrado)) {
                ra.addFlashAttribute("mensaje",
                        "Pedido eliminado correctamente");
            } else {
                ra.addFlashAttribute("error",
                        "No se pudo eliminar el pedido");
            }

        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/pedidos";
    }
}
