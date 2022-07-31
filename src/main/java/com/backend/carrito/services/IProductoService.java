package com.backend.carrito.services;

import com.backend.carrito.dto.ProductoDto;
import com.backend.carrito.models.Producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductoService {
    public List<ProductoDto> findAll();

    public Page<Producto> findAll (Pageable pageable);
    public ProductoDto findById(Long id);
    public ProductoDto save(ProductoDto producto);

    public void delete(Long id);
}
