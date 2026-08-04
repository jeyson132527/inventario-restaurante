package com.restaurante.inventario.controller;

import com.restaurante.inventario.entity.Compra;
import com.restaurante.inventario.service.CompraService;
import com.restaurante.inventario.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;
    private final ProductoService productoService;

    public CompraController(CompraService compraService,
                            ProductoService productoService) {

        this.compraService = compraService;
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("compras", compraService.listar());

        return "compras/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        Compra compra = new Compra();

        model.addAttribute("compra", compra);
        model.addAttribute("productos", productoService.listar());

        return "compras/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Compra compra) {

        compraService.guardar(compra);

        return "redirect:/compras";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        model.addAttribute("compra", compraService.buscarPorId(id));
        model.addAttribute("productos", productoService.listar());

        return "compras/nuevo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        compraService.eliminar(id);

        return "redirect:/compras";
    }

}