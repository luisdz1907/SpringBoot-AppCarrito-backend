package com.backend.carrito.dto.mapper;

import com.backend.carrito.dto.ProductoDto;
import com.backend.carrito.models.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-31T06:01:20-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoDto modelToDto(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        ProductoDto productoDto = new ProductoDto();

        productoDto.setCategoria( producto.getCategoria() );
        productoDto.setDescripcion( producto.getDescripcion() );
        productoDto.setFechaRegistro( producto.getFechaRegistro() );
        productoDto.setId( producto.getId() );
        productoDto.setMarca( producto.getMarca() );
        productoDto.setNombre( producto.getNombre() );
        if ( producto.getPrecioCompra() != null ) {
            productoDto.setPrecioCompra( String.valueOf( producto.getPrecioCompra() ) );
        }
        if ( producto.getPrecioVenta() != null ) {
            productoDto.setPrecioVenta( String.valueOf( producto.getPrecioVenta() ) );
        }
        productoDto.setStock( producto.getStock() );

        return productoDto;
    }

    @Override
    public Producto dtoToModel(ProductoDto productoDto) {
        if ( productoDto == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setCategoria( productoDto.getCategoria() );
        producto.setDescripcion( productoDto.getDescripcion() );
        producto.setFechaRegistro( productoDto.getFechaRegistro() );
        producto.setId( productoDto.getId() );
        producto.setMarca( productoDto.getMarca() );
        producto.setNombre( productoDto.getNombre() );
        if ( productoDto.getPrecioCompra() != null ) {
            producto.setPrecioCompra( Double.parseDouble( productoDto.getPrecioCompra() ) );
        }
        if ( productoDto.getPrecioVenta() != null ) {
            producto.setPrecioVenta( Double.parseDouble( productoDto.getPrecioVenta() ) );
        }
        producto.setStock( productoDto.getStock() );

        return producto;
    }

    @Override
    public List<ProductoDto> modelDtos(List<Producto> producto) {
        if ( producto == null ) {
            return null;
        }

        List<ProductoDto> list = new ArrayList<ProductoDto>( producto.size() );
        for ( Producto producto1 : producto ) {
            list.add( modelToDto( producto1 ) );
        }

        return list;
    }
}
