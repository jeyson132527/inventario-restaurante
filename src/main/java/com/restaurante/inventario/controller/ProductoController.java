package com.restaurante.inventario.controller;

import com.restaurante.inventario.entity.Producto;
import com.restaurante.inventario.service.CategoriaService;
import com.restaurante.inventario.service.KardexService;
import com.restaurante.inventario.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final KardexService kardexService;

    public ProductoController(
            ProductoService productoService,
            CategoriaService categoriaService,
            KardexService kardexService) {

        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.kardexService = kardexService;
    }

    @GetMapping
    public String listar(
            @RequestParam(required = false) String buscar,
            Model model) {

        model.addAttribute("productos", productoService.buscar(buscar));
        model.addAttribute("buscar", buscar);
        model.addAttribute("kardexService", kardexService);

        return "productos/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listar());

        return "productos/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto,
                          Model model) {

        Producto existente = productoService.buscarPorCodigo(producto.getCodigo());

        // Si es un producto nuevo
        if (producto.getId() == null) {

            if (existente != null) {

                model.addAttribute("error",
                        "Ya existe un producto con ese código.");

                model.addAttribute("producto", producto);
                model.addAttribute("categorias", categoriaService.listar());

                return "productos/nuevo";
            }

        }

        // Si es edición
        else {

            if (existente != null &&
                    !existente.getId().equals(producto.getId())) {

                model.addAttribute("error",
                        "Ya existe un producto con ese código.");

                model.addAttribute("producto", producto);
                model.addAttribute("categorias", categoriaService.listar());

                return "productos/nuevo";
            }

        }

        productoService.guardar(producto);

        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model model) {

        model.addAttribute("producto",
                productoService.buscarPorId(id));

        model.addAttribute("categorias",
                categoriaService.listar());

        return "productos/nuevo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        productoService.eliminar(id);

        return "redirect:/productos";
    }

}