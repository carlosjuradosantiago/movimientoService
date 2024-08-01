package com.devsu.movimientoservice;

import com.devsu.movimientoservice.dto.MovimientoDTO;
import com.devsu.movimientoservice.entity.Cuenta;
import com.devsu.movimientoservice.entity.Movimiento;
import com.devsu.movimientoservice.repository.CuentaRepository;
import com.devsu.movimientoservice.repository.MovimientoRepository;
import com.devsu.movimientoservice.service.MovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovimientoServiceIntegrationTest {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @BeforeEach
    void setUp() {
        movimientoRepository.deleteAll();
        cuentaRepository.deleteAll();

        // Crear cuenta para pruebas
        Cuenta cuenta = new Cuenta();
        cuenta.setSaldoDisponible(1000.0);
        cuentaRepository.save(cuenta);
    }

    @Test
    void testCreateMovimiento() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        Cuenta cuenta = cuentas.get(0);

        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setValor(100.0);
        movimientoDTO.setTipo("DEBITO");
        movimientoDTO.setCuentaId(cuenta.getId());

        MovimientoDTO result = movimientoService.createMovimiento(movimientoDTO);

        assertNotNull(result);
        assertEquals(100.0, result.getValor());
        assertEquals(900.0, cuentaRepository.findById(cuenta.getId()).get().getSaldoDisponible());
    }

    @Test
    void testGetAllMovimientos() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        Cuenta cuenta = cuentas.get(0);

        Movimiento movimiento = new Movimiento();
        movimiento.setValor(100.0);
        movimiento.setTipo("DEBITO");
        movimiento.setCuenta(cuenta);
        movimientoRepository.save(movimiento);

        List<MovimientoDTO> result = movimientoService.getAllMovimientos();
        assertFalse(result.isEmpty());
        assertEquals(100.0, result.get(0).getValor());
    }
}
