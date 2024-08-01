package com.devsu.movimientoservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numerocuenta")
    private String numeroCuenta;

    @Column(name = "tipocuenta")
    private String tipoCuenta;

    @Column(name = "saldodisponible")
    private double saldoDisponible;

    @Column(name = "estado")
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
