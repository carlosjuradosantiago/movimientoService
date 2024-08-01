package com.devsu.movimientoservice.controller;

import com.devsu.movimientoservice.dto.CuentaDTO;
import com.devsu.movimientoservice.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<CuentaDTO> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> getCuentaById(@PathVariable Long id) {
        CuentaDTO cuentaDTO = cuentaService.getCuentaById(id);
        if (cuentaDTO != null) {
            return ResponseEntity.ok(cuentaDTO);
        }
        return ResponseEntity.notFound().build();
    }

        @PostMapping
        public ResponseEntity<CuentaDTO> createCuenta(@RequestBody CuentaDTO cuentaDTO) {
            CuentaDTO createdCuenta = cuentaService.createCuenta(cuentaDTO);
            return ResponseEntity.ok(createdCuenta);
        }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTO> updateCuenta(@PathVariable Long id, @RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO updatedCuenta = cuentaService.updateCuenta(id, cuentaDTO);
        if (updatedCuenta != null) {
            return ResponseEntity.ok(updatedCuenta);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchCuenta(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        cuentaService.patchCuenta(id, updates);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
