package com.devsu.movimientoservice.repository;

import com.devsu.movimientoservice.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    void deleteByCuentaId(Long cuentaId);
}

