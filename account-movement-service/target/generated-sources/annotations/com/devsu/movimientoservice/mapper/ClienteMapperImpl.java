package com.devsu.movimientoservice.mapper;

import com.devsu.movimientoservice.dto.ClienteDTO;
import com.devsu.movimientoservice.entity.Cliente;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-01T02:32:08-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO toDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId( cliente.getId() );
        clienteDTO.setNombre( cliente.getNombre() );
        clienteDTO.setGenero( cliente.getGenero() );
        clienteDTO.setEdad( cliente.getEdad() );
        clienteDTO.setIdentificacion( cliente.getIdentificacion() );
        clienteDTO.setDireccion( cliente.getDireccion() );
        clienteDTO.setTelefono( cliente.getTelefono() );
        clienteDTO.setClienteId( cliente.getClienteId() );
        clienteDTO.setContraseña( cliente.getContraseña() );
        clienteDTO.setEstado( cliente.isEstado() );

        return clienteDTO;
    }

    @Override
    public Cliente toEntity(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( clienteDTO.getId() );
        cliente.setNombre( clienteDTO.getNombre() );
        cliente.setGenero( clienteDTO.getGenero() );
        cliente.setEdad( clienteDTO.getEdad() );
        cliente.setIdentificacion( clienteDTO.getIdentificacion() );
        cliente.setDireccion( clienteDTO.getDireccion() );
        cliente.setTelefono( clienteDTO.getTelefono() );
        cliente.setClienteId( clienteDTO.getClienteId() );
        cliente.setContraseña( clienteDTO.getContraseña() );
        cliente.setEstado( clienteDTO.isEstado() );

        return cliente;
    }
}
