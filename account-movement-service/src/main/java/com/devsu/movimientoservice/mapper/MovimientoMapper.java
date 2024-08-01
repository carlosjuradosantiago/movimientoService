package com.devsu.movimientoservice.mapper;

import com.devsu.movimientoservice.dto.MovimientoDTO;
import com.devsu.movimientoservice.entity.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    MovimientoDTO toDTO(Movimiento movimiento);
    Movimiento toEntity(MovimientoDTO movimientoDTO);
    void updateMovimientoFromDTO(MovimientoDTO movimientoDTO, @MappingTarget Movimiento movimiento);
    List<MovimientoDTO> toDTOs(List<Movimiento> movimientos);
}
