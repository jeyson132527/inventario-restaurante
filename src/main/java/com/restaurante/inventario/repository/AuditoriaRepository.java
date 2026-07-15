package com.restaurante.inventario.repository;

import com.restaurante.inventario.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

}