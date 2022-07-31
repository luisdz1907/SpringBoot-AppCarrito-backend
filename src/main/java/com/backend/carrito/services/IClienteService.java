package com.backend.carrito.services;

import java.util.List;

import com.backend.carrito.dto.ClienteDto;

public interface IClienteService {


    public List<ClienteDto> findAll();
    public ClienteDto findById(Long id);
    public ClienteDto save(ClienteDto clienteDto);
    public void delete(Long id);
}
