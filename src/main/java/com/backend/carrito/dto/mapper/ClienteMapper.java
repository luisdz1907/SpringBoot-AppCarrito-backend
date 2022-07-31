package com.backend.carrito.dto.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Configurable;

import com.backend.carrito.dto.ClienteDto;
import com.backend.carrito.models.Cliente;

@Mapper(componentModel = "spring")
@Configurable
public interface ClienteMapper {
    ClienteMapper MAPPER = Mappers.getMapper(ClienteMapper.class);

    ClienteDto modelToDto (Cliente cliente);
    @InheritConfiguration
    Cliente dtoToModel (ClienteDto clienteDto);

    List<ClienteDto> modelDtos(List<Cliente> cliente);
}
