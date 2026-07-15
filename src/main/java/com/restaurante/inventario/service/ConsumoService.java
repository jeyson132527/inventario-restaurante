package com.restaurante.inventario.service;

import com.restaurante.inventario.entity.Consumo;
import com.restaurante.inventario.repository.ConsumoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumoService {

    private final ConsumoRepository repository;

    public ConsumoService(ConsumoRepository repository) {
        this.repository = repository;
    }

    public List<Consumo> listar() {
        return repository.findAll();
    }

    public Consumo guardar(Consumo consumo) {
        return repository.save(consumo);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Consumo> ultimosConsumos(){

    return repository.findTop5ByOrderByFechaDesc();

   }

}