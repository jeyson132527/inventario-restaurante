package com.restaurante.inventario.controller;

import com.restaurante.inventario.service.KardexService;
import com.restaurante.inventario.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListaComprasController {

    private final ProductoService productoService;
    private final KardexService kardexService;

    public ListaComprasController(
            ProductoService productoService,
            KardexService kardexService) {

        this.productoService = productoService;
        this.kardexService = kardexService;
    }

    @GetMapping("/lista-compras")
    public String lista(Model model) {

        model.addAttribute("productos",
                productoService.listar());

        model.addAttribute("kardexService",
                kardexService);

        return "compras/lista";

    }

}