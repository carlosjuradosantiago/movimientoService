package com.devsu.movimientoservice.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Cliente extends Persona {
    private String clienteId;
    private String contraseña;
    private boolean estado;
}
