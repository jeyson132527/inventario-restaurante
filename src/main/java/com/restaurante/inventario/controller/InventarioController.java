package com.restaurante.inventario.controller;

import com.restaurante.inventario.service.KardexService;
import com.restaurante.inventario.service.ProductoService;
import com.restaurante.inventario.service.export.ExcelService;
import com.restaurante.inventario.service.export.PdfService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventarioController {

    private final ProductoService productoService;
    private final KardexService kardexService;
    private final ExcelService excelService;
    private final PdfService pdfService;

    public InventarioController(
            ProductoService productoService,
            KardexService kardexService,
            ExcelService excelService,
            PdfService pdfService) {

        this.productoService = productoService;
        this.kardexService = kardexService;
        this.excelService = excelService;
        this.pdfService = pdfService;
    }

    @GetMapping("/inventario")
    public String inventario(Model model) {

        model.addAttribute("productos", productoService.listar());
        model.addAttribute("kardexService", kardexService);

        return "inventario/index";
    }

    @GetMapping("/inventario/excel")
    public ResponseEntity<InputStreamResource> exportarExcel() {

        InputStreamResource archivo = new InputStreamResource(
                excelService.exportarProductos(productoService.listar()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=Inventario.xlsx")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(archivo);
    }

    @GetMapping("/inventario/pdf")
    public ResponseEntity<InputStreamResource> exportarPdf() {

        InputStreamResource archivo = new InputStreamResource(
                pdfService.exportarProductos(productoService.listar()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=Inventario.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(archivo);
    }

}