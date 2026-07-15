package com.restaurante.inventario.repository;

import com.restaurante.inventario.entity.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long> {
  List<Consumo> findTop5ByOrderByFechaDesc();
}