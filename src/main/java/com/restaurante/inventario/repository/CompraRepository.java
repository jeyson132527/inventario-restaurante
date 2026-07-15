package com.restaurante.inventario.repository;

import com.restaurante.inventario.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{

    List<Compra> findTop5ByOrderByFechaDesc();

}