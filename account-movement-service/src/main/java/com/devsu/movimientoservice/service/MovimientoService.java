package com.devsu.movimientoservice.service;

import com.devsu.movimientoservice.dto.MovimientoDTO;
import com.devsu.movimientoservice.dto.MovimientoDetalleDTO;
import com.devsu.movimientoservice.entity.Cuenta;
import com.devsu.movimientoservice.entity.Movimiento;
import com.devsu.movimientoservice.mapper.MovimientoMapper;
import com.devsu.movimientoservice.repository.CuentaRepository;
import com.devsu.movimientoservice.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoMapper movimientoMapper;

    public List<MovimientoDTO> getAllMovimientos() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        return movimientoMapper.toDTOs(movimientos);
    }

    public MovimientoDTO getMovimientoById(Long id) {
        Optional<Movimiento> movimiento = movimientoRepository.findById(id);
        return movimiento.map(movimientoMapper::toDTO).orElse(null);
    }

    public MovimientoDTO createMovimiento(MovimientoDTO movimientoDTO) {
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(movimientoDTO.getCuentaId());

        if (!cuentaOpt.isPresent()) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }

        Cuenta cuenta = cuentaOpt.get();

        if (!cuenta.isEstado()) {
            throw new IllegalArgumentException("La cuenta está inactiva");
        }

        Movimiento movimiento = movimientoMapper.toEntity(movimientoDTO);
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setSaldoInicial(cuenta.getSaldoDisponible());

        if (movimiento.getSaldoInicial() < 0) {
            throw new IllegalArgumentException("El saldo inicial debe ser mayor o igual a 0");
        }

        if (movimiento.getTipo().equalsIgnoreCase("RETIRO")) {
            if (movimiento.getValor() > 0) {
                throw new IllegalArgumentException("El valor de un retiro debe ser negativo");
            }
            if (cuenta.getSaldoDisponible() < Math.abs(movimiento.getValor())) {
                throw new IllegalArgumentException("Saldo no disponible");
            }
        }

        if (movimiento.getTipo().equalsIgnoreCase("DEPOSITO")) {
            if (movimiento.getValor() < 0) {
                throw new IllegalArgumentException("El valor de un depósito debe ser positivo");
            }
        }

        cuenta.setSaldoDisponible(cuenta.getSaldoDisponible() + movimiento.getValor());
        cuentaRepository.save(cuenta);

        Movimiento savedMovimiento = movimientoRepository.save(movimiento);
        return movimientoMapper.toDTO(savedMovimiento);
    }




    public MovimientoDTO updateMovimiento(Long id, MovimientoDTO movimientoDTO) {
        Optional<Movimiento> existingMovimiento = movimientoRepository.findById(id);
        if (existingMovimiento.isPresent()) {
            Movimiento movimiento = existingMovimiento.get();

            if (!movimiento.getCuenta().isEstado()) {
                throw new IllegalArgumentException("La cuenta está inactiva");
            }

            double saldoInicial = movimiento.getCuenta().getSaldoDisponible();
            movimiento.setSaldoInicial(saldoInicial);

            if (movimientoDTO.getTipo().equalsIgnoreCase("Retiro")) {
                if (movimientoDTO.getValor() > saldoInicial) {
                    throw new IllegalArgumentException("Saldo no disponible");
                }
                movimientoDTO.setValor(-Math.abs(movimientoDTO.getValor()));
            } else if (movimientoDTO.getTipo().equalsIgnoreCase("Depósito")) {
                movimientoDTO.setValor(Math.abs(movimientoDTO.getValor()));
            } else {
                throw new IllegalArgumentException("Tipo de movimiento no válido");
            }

            movimiento.setTipo(movimientoDTO.getTipo());
            movimiento.setValor(movimientoDTO.getValor());

            movimiento.getCuenta().setSaldoDisponible(saldoInicial + movimientoDTO.getValor());

            Movimiento updatedMovimiento = movimientoRepository.save(movimiento);
            cuentaRepository.save(movimiento.getCuenta());

            return movimientoMapper.toDTO(updatedMovimiento);
        } else {
            throw new IllegalArgumentException("Movimiento no encontrado");
        }
    }

    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }


    public List<MovimientoDetalleDTO> getMovimientosByFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        return movimientos.stream()
                .filter(mov -> mov.getFecha().isAfter(fechaInicio) && mov.getFecha().isBefore(fechaFin))
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private MovimientoDetalleDTO convertToResponse(Movimiento movimiento) {
        MovimientoDetalleDTO movimientoDetalleDTODTO = new MovimientoDetalleDTO();
        movimientoDetalleDTODTO.setFecha(movimiento.getFecha());
        movimientoDetalleDTODTO.setCliente(movimiento.getCuenta().getCliente().getNombre());
        movimientoDetalleDTODTO.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        movimientoDetalleDTODTO.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
        movimientoDetalleDTODTO.setSaldoInicial(movimiento.getSaldoInicial());
        movimientoDetalleDTODTO.setEstado(movimiento.getCuenta().isEstado());
        movimientoDetalleDTODTO.setMovimiento(movimiento.getValor());
        movimientoDetalleDTODTO.setSaldoDisponible(movimiento.getSaldoInicial() + movimiento.getValor());
        return movimientoDetalleDTODTO;
    }

}
