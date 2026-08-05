package com.restaurante.inventario.service;

import com.restaurante.inventario.entity.Producto;
import com.restaurante.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    public Producto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {

    repository.deleteById(id);

}

    public List<Producto> buscar(String nombre) {

        if (nombre == null || nombre.isBlank()) {
            return repository.findAll();
        }

        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    /**
     * Verifica si ya existe un producto con el mismo código.
     */
    public boolean existeCodigo(String codigo) {

        return repository.findByCodigo(codigo).isPresent();

    }
  
    public Producto buscarPorCodigo(String codigo) {

    return repository.findByCodigo(codigo).orElse(null);

}

}