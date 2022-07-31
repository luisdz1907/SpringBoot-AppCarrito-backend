package com.backend.carrito.services.impl;

import com.backend.carrito.dto.ProductoDto;
import com.backend.carrito.dto.mapper.ProductoMapper;
import com.backend.carrito.exception.ResourceNotFoundException;
import com.backend.carrito.models.Categoria;
import com.backend.carrito.models.Producto;
import com.backend.carrito.repository.ProductoRepository;
import com.backend.carrito.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CategoriaServiceImpl categoriaService;

    @Autowired(required = true)
    ProductoMapper productoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDto> findAll() {

        List<ProductoDto> productoDtos = new ArrayList<>();

        List<Producto> productos = (List<Producto>) productoRepository.findAll();
        productoDtos = productoMapper.modelDtos(productos);

        return productoDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDto findById(Long id) {

        ProductoDto productoDto = new ProductoDto();

        productoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El producto con el ID " + id + " no existe."));
                
        productoDto = productoMapper.modelToDto(productoRepository.findById(id).get());

        return productoDto;
    }

    @Override
    @Transactional
    public ProductoDto save(ProductoDto producto) {
        Producto productos = new Producto();

        Optional<Categoria> categoria = Optional.ofNullable(categoriaService.findById(producto.getCategoria().getId()));

        if (!categoria.isPresent()) {
            throw new ResourceNotFoundException("El id de la categoria es nulo");
        }
        producto.setCategoria(categoria.get());

        productos = productoMapper.dtoToModel(producto);
        productos = productoRepository.save(productos);
        producto = productoMapper.modelToDto(productos);

        return producto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        
        Optional<Producto> producto = Optional.ofNullable(productoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El producto que desea eliminar con el ID " + id + " no existe.")));

        productoRepository.delete(producto.get());
    }

}