package com.restaurante.inventario.repository;

import com.restaurante.inventario.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{

    List<Compra> findTop5ByOrderByFechaDesc();
    
    List<Compra> findByProductoNombreContainingIgnoreCase(String nombre);

    long countByFecha(LocalDate fecha);

}