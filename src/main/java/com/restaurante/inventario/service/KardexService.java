package com.restaurante.inventario.service;

import com.restaurante.inventario.dto.KardexDTO;
import com.restaurante.inventario.entity.Compra;
import com.restaurante.inventario.entity.Consumo;
import com.restaurante.inventario.entity.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KardexService {

    private final CompraService compraService;
    private final ConsumoService consumoService;
    private final ProductoService productoService;

    public KardexService(CompraService compraService,
                         ConsumoService consumoService,
                         ProductoService productoService) {

        this.compraService = compraService;
        this.consumoService = consumoService;
        this.productoService = productoService;
    }

    /**
     * Genera el kardex general.
     */
    public List<KardexDTO> generarKardex() {

        List<KardexDTO> kardex = new ArrayList<>();

        double saldo = 0;

        for (Compra compra : compraService.listar()) {

            saldo += compra.getCantidad();

            kardex.add(new KardexDTO(
                    compra.getFecha(),
                    "COMPRA",
                    compra.getProducto().getNombre(),
                    compra.getCantidad(),
                    0,
                    saldo
            ));
        }

        for (Consumo consumo : consumoService.listar()) {

            saldo -= consumo.getCantidad();

            kardex.add(new KardexDTO(
                    consumo.getFecha(),
                    "CONSUMO",
                    consumo.getProducto().getNombre(),
                    0,
                    consumo.getCantidad(),
                    saldo
            ));
        }

        kardex.sort(Comparator.comparing(KardexDTO::getFecha));

        return kardex;
    }

    /**
     * Obtiene el stock actual de un producto.
     */
    public double obtenerStockProducto(Long productoId) {

        double entradas = compraService.listar().stream()
                .filter(c -> c.getProducto().getId().equals(productoId))
                .mapToDouble(Compra::getCantidad)
                .sum();

        double salidas = consumoService.listar().stream()
                .filter(c -> c.getProducto().getId().equals(productoId))
                .mapToDouble(Consumo::getCantidad)
                .sum();

        return entradas - salidas;
    }

    /**
     * Indica si un producto tiene stock bajo.
     */
    public boolean stockBajo(Long productoId, double stockMinimo) {

        return obtenerStockProducto(productoId) <= stockMinimo;

    }

    /**
     * Cuenta cuántos productos tienen stock bajo.
     */
    public long contarProductosStockBajo() {

        return productoService.listar().stream()
                .filter(p -> stockBajo(p.getId(), p.getStockMinimo()))
                .count();

    }

    /**
     * Devuelve la lista de productos con stock bajo.
     */
    public List<Producto> obtenerProductosStockBajo() {

        return productoService.listar().stream()
                .filter(p -> stockBajo(p.getId(), p.getStockMinimo()))
                .collect(Collectors.toList());

    }

}