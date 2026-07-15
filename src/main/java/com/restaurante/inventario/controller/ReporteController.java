package com.restaurante.inventario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReporteController {

    @GetMapping("/reportes")
    public String index() {

        return "reportes/index";

    }

}