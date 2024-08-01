package com.devsu.movimientoservice.service;

import com.devsu.movimientoservice.dto.CuentaDTO;
import com.devsu.movimientoservice.entity.Cuenta;
import com.devsu.movimientoservice.entity.Cliente;
import com.devsu.movimientoservice.mapper.CuentaMapper;
import com.devsu.movimientoservice.repository.ClienteRepository;
import com.devsu.movimientoservice.repository.CuentaRepository;
import com.devsu.movimientoservice.repository.MovimientoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaMapper cuentaMapper;

    public List<CuentaDTO> getAllCuentas() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        return cuentaMapper.toDTOs(cuentas);
    }

    public CuentaDTO getCuentaById(Long id) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        return cuenta.map(cuentaMapper::toDTO).orElseThrow(() -> new IllegalArgumentException("Cuenta not found with id " + id));
    }

    public CuentaDTO createCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);
        Cuenta savedCuenta = cuentaRepository.save(cuenta);
        return cuentaMapper.toDTO(savedCuenta);
    }

    public CuentaDTO updateCuenta(Long id, CuentaDTO cuentaDTO) {
        Optional<Cuenta> existingCuenta = cuentaRepository.findById(id);
        if (existingCuenta.isPresent()) {
            Cuenta cuenta = existingCuenta.get();

            if (cuentaDTO.getNumeroCuenta() != null) {
                cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
            }

            if (cuentaDTO.getTipoCuenta() != null) {
                cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
            }

            if (cuentaDTO.getSaldoDisponible() != 0) {
                cuenta.setSaldoDisponible(cuentaDTO.getSaldoDisponible());
            }

            cuenta.setEstado(cuentaDTO.isEstado());

            if (cuentaDTO.getClienteId() != null) {
                Cliente cliente = clienteRepository.findById(cuentaDTO.getClienteId())
                        .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + cuentaDTO.getClienteId()));
                cuenta.setCliente(cliente);
            }

            Cuenta updatedCuenta = cuentaRepository.save(cuenta);
            return cuentaMapper.toDTO(updatedCuenta);
        }
        throw new EntityNotFoundException("Cuenta no encontrada con id: " + id);
    }

    public void deleteCuenta(Long id) {
        Optional<Cuenta> existingCuenta = cuentaRepository.findById(id);
        if (existingCuenta.isPresent()) {
            Cuenta cuenta = existingCuenta.get();
            movimientoRepository.deleteByCuentaId(cuenta.getId());
            cuentaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Cuenta no encontrada con id: " + id);
        }
    }


    public void patchCuenta(Long id, Map<String, Object> updates) {
        Optional<Cuenta> existingCuenta = cuentaRepository.findById(id);
        if (existingCuenta.isPresent()) {
            Cuenta cuenta = existingCuenta.get();
            updates.forEach((k, v) -> {
                switch (k) {
                    case "numeroCuenta":
                        cuenta.setNumeroCuenta((String) v);
                        break;
                    case "tipoCuenta":
                        cuenta.setTipoCuenta((String) v);
                        break;
                    case "saldoDisponible":
                        cuenta.setSaldoDisponible((Double) v);
                        break;
                    case "estado":
                        cuenta.setEstado((Boolean) v);
                        break;
                }
            });
            cuentaRepository.save(cuenta);
        }
    }
}
