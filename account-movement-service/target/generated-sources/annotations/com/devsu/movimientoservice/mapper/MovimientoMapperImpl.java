package com.devsu.movimientoservice.mapper;

import com.devsu.movimientoservice.dto.MovimientoDTO;
import com.devsu.movimientoservice.entity.Movimiento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-01T02:32:07-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class MovimientoMapperImpl implements MovimientoMapper {

    @Override
    public MovimientoDTO toDTO(Movimiento movimiento) {
        if ( movimiento == null ) {
            return null;
        }

        MovimientoDTO movimientoDTO = new MovimientoDTO();

        movimientoDTO.setId( movimiento.getId() );
        movimientoDTO.setFecha( movimiento.getFecha() );
        movimientoDTO.setTipo( movimiento.getTipo() );
        movimientoDTO.setSaldoInicial( movimiento.getSaldoInicial() );
        movimientoDTO.setValor( movimiento.getValor() );

        return movimientoDTO;
    }

    @Override
    public Movimiento toEntity(MovimientoDTO movimientoDTO) {
        if ( movimientoDTO == null ) {
            return null;
        }

        Movimiento movimiento = new Movimiento();

        movimiento.setId( movimientoDTO.getId() );
        movimiento.setFecha( movimientoDTO.getFecha() );
        movimiento.setTipo( movimientoDTO.getTipo() );
        movimiento.setSaldoInicial( movimientoDTO.getSaldoInicial() );
        movimiento.setValor( movimientoDTO.getValor() );

        return movimiento;
    }

    @Override
    public void updateMovimientoFromDTO(MovimientoDTO movimientoDTO, Movimiento movimiento) {
        if ( movimientoDTO == null ) {
            return;
        }

        movimiento.setId( movimientoDTO.getId() );
        movimiento.setFecha( movimientoDTO.getFecha() );
        movimiento.setTipo( movimientoDTO.getTipo() );
        movimiento.setSaldoInicial( movimientoDTO.getSaldoInicial() );
        movimiento.setValor( movimientoDTO.getValor() );
    }

    @Override
    public List<MovimientoDTO> toDTOs(List<Movimiento> movimientos) {
        if ( movimientos == null ) {
            return null;
        }

        List<MovimientoDTO> list = new ArrayList<MovimientoDTO>( movimientos.size() );
        for ( Movimiento movimiento : movimientos ) {
            list.add( toDTO( movimiento ) );
        }

        return list;
    }
}
