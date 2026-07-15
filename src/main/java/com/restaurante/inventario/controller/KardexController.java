package com.restaurante.inventario.controller;

import com.restaurante.inventario.service.KardexService;
import com.restaurante.inventario.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KardexController {

    private final KardexService kardexService;
    private final ProductoService productoService;

    public KardexController(KardexService kardexService,
                            ProductoService productoService) {

        this.kardexService = kardexService;
        this.productoService = productoService;

    }

    @GetMapping("/kardex")
    public String kardex(Model model) {

        model.addAttribute("movimientos",
                kardexService.generarKardex());

        model.addAttribute("productos",
                productoService.listar());

        return "kardex/index";

    }

}