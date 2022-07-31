package com.backend.carrito.dto.mapper;

import com.backend.carrito.dto.ClienteDto;
import com.backend.carrito.models.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-31T06:48:36-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDto modelToDto(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setApellidos( cliente.getApellidos() );
        clienteDto.setDireccion( cliente.getDireccion() );
        clienteDto.setDocumento( cliente.getDocumento() );
        clienteDto.setFechaNacimiento( cliente.getFechaNacimiento() );
        clienteDto.setId( cliente.getId() );
        clienteDto.setNombre( cliente.getNombre() );
        clienteDto.setTelefono( cliente.getTelefono() );

        return clienteDto;
    }

    @Override
    public Cliente dtoToModel(ClienteDto clienteDto) {
        if ( clienteDto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setApellidos( clienteDto.getApellidos() );
        cliente.setDireccion( clienteDto.getDireccion() );
        cliente.setDocumento( clienteDto.getDocumento() );
        cliente.setFechaNacimiento( clienteDto.getFechaNacimiento() );
        cliente.setId( clienteDto.getId() );
        cliente.setNombre( clienteDto.getNombre() );
        cliente.setTelefono( clienteDto.getTelefono() );

        return cliente;
    }

    @Override
    public List<ClienteDto> modelDtos(List<Cliente> cliente) {
        if ( cliente == null ) {
            return null;
        }

        List<ClienteDto> list = new ArrayList<ClienteDto>( cliente.size() );
        for ( Cliente cliente1 : cliente ) {
            list.add( modelToDto( cliente1 ) );
        }

        return list;
    }
}
