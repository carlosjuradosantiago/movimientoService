package com.devsu.movimientoservice.controller;

import com.devsu.movimientoservice.dto.MovimientoDTO;
import com.devsu.movimientoservice.dto.MovimientoDetalleDTO;
import com.devsu.movimientoservice.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoDTO> createMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO createdMovimiento = movimientoService.createMovimiento(movimientoDTO);
        return ResponseEntity.ok(createdMovimiento);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> getAllMovimientos() {
        List<MovimientoDTO> movimientos = movimientoService.getAllMovimientos();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> getMovimientoById(@PathVariable Long id) {
        MovimientoDTO movimientoDTO = movimientoService.getMovimientoById(id);
        if (movimientoDTO != null) {
            return ResponseEntity.ok(movimientoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDTO> updateMovimiento(@PathVariable Long id, @RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO updatedMovimiento = movimientoService.updateMovimiento(id, movimientoDTO);
        if (updatedMovimiento != null) {
            return ResponseEntity.ok(updatedMovimiento);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<MovimientoDetalleDTO>> getMovimientosByFecha(
            @RequestParam("fechaInicio") String fechaInicioStr,
            @RequestParam("fechaFin") String fechaFinStr) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr + " 00:00:00", formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr + " 23:59:59", formatter);

        List<MovimientoDetalleDTO> movimientos = movimientoService.getMovimientosByFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(movimientos);
    }

}
