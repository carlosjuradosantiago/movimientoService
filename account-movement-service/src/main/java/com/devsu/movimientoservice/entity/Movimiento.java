package com.devsu.movimientoservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cuenta cuenta;

    private LocalDateTime fecha;

    private String tipo;

    @Column(name = "saldoinicial")
    private double saldoInicial;

    private double valor;
}
