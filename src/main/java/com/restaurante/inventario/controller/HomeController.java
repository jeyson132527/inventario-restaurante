package com.restaurante.inventario.controller;

import com.restaurante.inventario.service.CategoriaService;
import com.restaurante.inventario.service.CompraService;
import com.restaurante.inventario.service.ConsumoService;
import com.restaurante.inventario.service.KardexService;
import com.restaurante.inventario.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final CompraService compraService;
    private final ConsumoService consumoService;
    private final KardexService kardexService;

    public HomeController(
            ProductoService productoService,
            CategoriaService categoriaService,
            CompraService compraService,
            ConsumoService consumoService,
            KardexService kardexService) {

        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.compraService = compraService;
        this.consumoService = consumoService;
        this.kardexService = kardexService;
    }

    @GetMapping("/")
    public String inicio(Model model) {

        cargarDashboard(model);

        return "dashboard/index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        cargarDashboard(model);

        return "dashboard/index";
    }

    /**
     * Carga toda la información del Dashboard.
     */
    private void cargarDashboard(Model model) {

        model.addAttribute("totalProductos",
                productoService.listar().size());

        model.addAttribute("totalCategorias",
                categoriaService.listar().size());

        model.addAttribute("productosStockBajo",
               kardexService.contarProductosStockBajo());

        model.addAttribute("ultimasCompras",
                compraService.ultimasCompras());

        model.addAttribute("ultimosConsumos",
                consumoService.ultimosConsumos());

        model.addAttribute("productos",
                productoService.listar());

        model.addAttribute("kardexService",
                kardexService);

        model.addAttribute("stockService",
                kardexService);

        model.addAttribute("totalCompras",
                compraService.listar().size());

        model.addAttribute("totalConsumos",
                consumoService.listar().size());

        model.addAttribute("productosStockBajo",
               kardexService.contarProductosStockBajo());

        model.addAttribute("listaStockBajo",
               kardexService.obtenerProductosStockBajo());

        model.addAttribute("comprasHoy",
                compraService.obtenerComprasHoy());

        model.addAttribute("consumosHoy",
                consumoService.obtenerConsumosHoy());

        model.addAttribute("alertas",
                kardexService.contarProductosStockBajo());

        model.addAttribute("stockBajo",
                kardexService.contarProductosStockBajo());

        model.addAttribute("stockBajo",
                kardexService.contarProductosStockBajo());

        model.addAttribute("comprasHoy",
                compraService.obtenerComprasHoy());

        model.addAttribute("consumosHoy",
                consumoService.obtenerConsumosHoy());

        model.addAttribute("alertas",
                kardexService.contarProductosStockBajo());
    }

}