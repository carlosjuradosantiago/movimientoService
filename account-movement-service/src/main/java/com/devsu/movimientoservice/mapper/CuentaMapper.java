package com.devsu.movimientoservice.mapper;

import com.devsu.movimientoservice.dto.CuentaDTO;
import com.devsu.movimientoservice.entity.Cuenta;
import com.devsu.movimientoservice.entity.Cliente;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    @Mappings({
            @Mapping(target = "clienteId", source = "cliente.id")
    })
    CuentaDTO toDTO(Cuenta cuenta);

    @Mappings({
            @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "clienteFromId")
    })
    Cuenta toEntity(CuentaDTO cuentaDTO);

    @Mappings({
            @Mapping(target = "cliente", source = "clienteId", qualifiedByName = "clienteFromId")
    })
    void updateCuentaFromDTO(CuentaDTO cuentaDTO, @MappingTarget Cuenta cuenta);

    List<CuentaDTO> toDTOs(List<Cuenta> cuentas);

    @Named("clienteFromId")
    static Cliente clienteFromId(Long id) {
        if (id == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return cliente;
    }
}
