package com.backend.carrito.services.impl;

import com.backend.carrito.exception.ResourceNotFoundException;
import com.backend.carrito.models.Categoria;
import com.backend.carrito.repository.CategoriaRepository;
import com.backend.carrito.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        if (categoriaRepository.findAll() == null) {
            throw new ResourceNotFoundException("No hay registros en la base de datos.");
        }
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria findById(Long id) {

        categoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La categoria con el ID " + id + " no existe."));

        return categoriaRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La categoria con el ID: " + id + " no existe."));
        categoriaRepository.deleteById(id);
    }
}
