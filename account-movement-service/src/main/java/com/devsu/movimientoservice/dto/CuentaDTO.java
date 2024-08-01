        package com.devsu.movimientoservice.dto;

        import lombok.Data;

        @Data
        public class CuentaDTO {
            private Long id;
            private String numeroCuenta;
            private String tipoCuenta;
            private double saldoDisponible;
            private boolean estado;
            private Long clienteId;
        }
