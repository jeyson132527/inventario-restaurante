package com.restaurante.inventario.service;

import com.restaurante.inventario.entity.Compra;
import com.restaurante.inventario.repository.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    private final CompraRepository repository;

    public CompraService(CompraRepository repository) {
        this.repository = repository;
    }

    public List<Compra> listar() {
        return repository.findAll();
    }

    public Compra guardar(Compra compra) {
        return repository.save(compra);
    }

    public Compra buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Compra> ultimasCompras() {
        return repository.findTop5ByOrderByFechaDesc();
    }

}