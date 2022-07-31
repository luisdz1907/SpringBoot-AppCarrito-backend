package com.backend.carrito.services;

import com.backend.carrito.models.Categoria;

import java.util.List;

public interface ICategoriaService {
    public List<Categoria> findAll();
    public Categoria findById(Long id);
    public Categoria save(Categoria categoria);
    public void delete(Long id);
}
