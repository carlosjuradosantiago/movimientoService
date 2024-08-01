package com.devsu.movimientoservice.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String clienteId;
    private String contraseña;
    private boolean estado;
}
