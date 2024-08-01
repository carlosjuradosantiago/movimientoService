package com.devsu.movimientoservice.repository;

import com.devsu.movimientoservice.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
