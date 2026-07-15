package com.restaurante.inventario.controller;

import com.restaurante.inventario.entity.Consumo;
import com.restaurante.inventario.service.ConsumoService;
import com.restaurante.inventario.service.KardexService;
import com.restaurante.inventario.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consumos")
public class ConsumoController {

    private final ConsumoService consumoService;
    private final ProductoService productoService;
    private final KardexService kardexService;

    public ConsumoController(ConsumoService consumoService,
                             ProductoService productoService,
                             KardexService kardexService) {

        this.consumoService = consumoService;
        this.productoService = productoService;
        this.kardexService = kardexService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("consumos", consumoService.listar());

        return "consumos/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("consumo", new Consumo());
        model.addAttribute("productos", productoService.listar());

        return "consumos/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Consumo consumo,
                          Model model) {

        double stockActual = kardexService.obtenerStockProducto(
                consumo.getProducto().getId());

        if (consumo.getCantidad() > stockActual) {

            model.addAttribute("error",
                    "Stock insuficiente. Disponible: " + stockActual);

            model.addAttribute("consumo", consumo);
            model.addAttribute("productos", productoService.listar());

            return "consumos/nuevo";
        }

        double stock = kardexService.obtenerStockProducto(
        consumo.getProducto().getId());

if (consumo.getCantidad() > stock) {

    model.addAttribute("error",
            "No existe suficiente stock.");

    
}

        return "redirect:/consumos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        consumoService.eliminar(id);

        return "redirect:/consumos";
    }

}