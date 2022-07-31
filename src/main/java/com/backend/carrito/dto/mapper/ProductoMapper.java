package com.backend.carrito.dto.mapper;

import com.backend.carrito.dto.ProductoDto;
import com.backend.carrito.models.Producto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;

@Mapper(componentModel = "spring")
@Configurable
public interface ProductoMapper {
    ProductoMapper MAPPER = Mappers.getMapper(ProductoMapper.class);

    ProductoDto modelToDto (Producto producto);

    @InheritConfiguration
    Producto dtoToModel (ProductoDto productoDto);
    
    List<ProductoDto> modelDtos (List<Producto> producto);
}