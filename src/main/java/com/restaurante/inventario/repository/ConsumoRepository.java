package com.restaurante.inventario.repository;

import com.restaurante.inventario.entity.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long> {
  List<Consumo> findTop5ByOrderByFechaDesc();
  long countByFecha(LocalDate fecha);
  List<Consumo> findByProductoNombreContainingIgnoreCase(String nombre);
}