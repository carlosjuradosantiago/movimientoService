package com.devsu.movimientoservice;

import com.devsu.movimientoservice.dto.MovimientoDTO;
import com.devsu.movimientoservice.entity.Movimiento;
import com.devsu.movimientoservice.mapper.MovimientoMapper;
import com.devsu.movimientoservice.repository.MovimientoRepository;
import com.devsu.movimientoservice.service.MovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovimientoServiceTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @InjectMocks
    private MovimientoService movimientoService;

    @Mock
    private MovimientoMapper movimientoMapper;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMovimientoById() {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setValor(100.0);

        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(1L);
        movimientoDTO.setValor(100.0);

        when(movimientoRepository.findById(1L)).thenReturn(Optional.of(movimiento));
        when(movimientoMapper.toDTO(movimiento)).thenReturn(movimientoDTO);

        MovimientoDTO result = movimientoService.getMovimientoById(1L);
        assertNotNull(result);
        assertEquals(100.0, result.getValor());
    }

    @Test
    void testCreateMovimiento() {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setValor(100.0);

        Movimiento movimiento = new Movimiento();
        movimiento.setValor(100.0);
        movimiento.setFecha(LocalDateTime.now());

        when(movimientoMapper.toEntity(movimientoDTO)).thenReturn(movimiento);
        when(movimientoRepository.save(any(Movimiento.class))).thenReturn(movimiento);
        when(movimientoMapper.toDTO(movimiento)).thenReturn(movimientoDTO);

        MovimientoDTO result = movimientoService.createMovimiento(movimientoDTO);
        assertNotNull(result);
        assertEquals(100.0, result.getValor());
    }

    @Test
    void testUpdateMovimiento() {
        Long id = 1L;
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setValor(150.0);

        Movimiento existingMovimiento = new Movimiento();
        existingMovimiento.setId(id);
        existingMovimiento.setValor(100.0);

        Movimiento updatedMovimiento = new Movimiento();
        updatedMovimiento.setId(id);
        updatedMovimiento.setValor(150.0);

        when(movimientoRepository.findById(id)).thenReturn(Optional.of(existingMovimiento));
        when(movimientoRepository.save(any(Movimiento.class))).thenReturn(updatedMovimiento);
        when(movimientoMapper.toDTO(updatedMovimiento)).thenReturn(movimientoDTO);

        MovimientoDTO result = movimientoService.updateMovimiento(id, movimientoDTO);
        assertNotNull(result);
        assertEquals(150.0, result.getValor());
    }

    @Test
    void testDeleteMovimiento() {
        Long id = 1L;
        doNothing().when(movimientoRepository).deleteById(id);

        movimientoService.deleteMovimiento(id);

        verify(movimientoRepository, times(1)).deleteById(id);
    }
}
