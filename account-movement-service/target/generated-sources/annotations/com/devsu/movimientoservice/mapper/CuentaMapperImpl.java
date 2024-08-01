package com.devsu.movimientoservice.mapper;

import com.devsu.movimientoservice.dto.CuentaDTO;
import com.devsu.movimientoservice.entity.Cliente;
import com.devsu.movimientoservice.entity.Cuenta;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-01T02:44:23-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class CuentaMapperImpl implements CuentaMapper {

    @Override
    public CuentaDTO toDTO(Cuenta cuenta) {
        if ( cuenta == null ) {
            return null;
        }

        CuentaDTO cuentaDTO = new CuentaDTO();

        cuentaDTO.setClienteId( cuentaClienteId( cuenta ) );
        cuentaDTO.setId( cuenta.getId() );
        cuentaDTO.setNumeroCuenta( cuenta.getNumeroCuenta() );
        cuentaDTO.setTipoCuenta( cuenta.getTipoCuenta() );
        cuentaDTO.setSaldoDisponible( cuenta.getSaldoDisponible() );
        cuentaDTO.setEstado( cuenta.isEstado() );

        return cuentaDTO;
    }

    @Override
    public Cuenta toEntity(CuentaDTO cuentaDTO) {
        if ( cuentaDTO == null ) {
            return null;
        }

        Cuenta cuenta = new Cuenta();

        cuenta.setCliente( CuentaMapper.clienteFromId( cuentaDTO.getClienteId() ) );
        cuenta.setId( cuentaDTO.getId() );
        cuenta.setNumeroCuenta( cuentaDTO.getNumeroCuenta() );
        cuenta.setTipoCuenta( cuentaDTO.getTipoCuenta() );
        cuenta.setSaldoDisponible( cuentaDTO.getSaldoDisponible() );
        cuenta.setEstado( cuentaDTO.isEstado() );

        return cuenta;
    }

    @Override
    public void updateCuentaFromDTO(CuentaDTO cuentaDTO, Cuenta cuenta) {
        if ( cuentaDTO == null ) {
            return;
        }

        cuenta.setCliente( CuentaMapper.clienteFromId( cuentaDTO.getClienteId() ) );
        cuenta.setId( cuentaDTO.getId() );
        cuenta.setNumeroCuenta( cuentaDTO.getNumeroCuenta() );
        cuenta.setTipoCuenta( cuentaDTO.getTipoCuenta() );
        cuenta.setSaldoDisponible( cuentaDTO.getSaldoDisponible() );
        cuenta.setEstado( cuentaDTO.isEstado() );
    }

    @Override
    public List<CuentaDTO> toDTOs(List<Cuenta> cuentas) {
        if ( cuentas == null ) {
            return null;
        }

        List<CuentaDTO> list = new ArrayList<CuentaDTO>( cuentas.size() );
        for ( Cuenta cuenta : cuentas ) {
            list.add( toDTO( cuenta ) );
        }

        return list;
    }

    private Long cuentaClienteId(Cuenta cuenta) {
        if ( cuenta == null ) {
            return null;
        }
        Cliente cliente = cuenta.getCliente();
        if ( cliente == null ) {
            return null;
        }
        Long id = cliente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
