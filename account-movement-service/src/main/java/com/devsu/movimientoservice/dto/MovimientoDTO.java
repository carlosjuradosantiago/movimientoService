package com.devsu.movimientoservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimientoDTO {
    private Long id;
    private Long cuentaId;
    private LocalDateTime fecha;
    private String tipo;
    private double saldoInicial;
    private double valor;
}
