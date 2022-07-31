package com.backend.carrito.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import com.backend.carrito.dto.ClienteDto;
import com.backend.carrito.dto.mapper.ClienteMapper;
import com.backend.carrito.exception.ResourceNotFoundException;
import com.backend.carrito.models.Cliente;
import com.backend.carrito.repository.ClienteRepository;
import com.backend.carrito.services.IClienteService;


@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired(required = true)
    ClienteMapper clienteMapper;

    @Autowired
    ClienteServiceImpl clienteService;



    @Override
    @Transactional(readOnly = true)
    public List<ClienteDto> findAll() {
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Cliente> clientes = (List<Cliente>)clienteRepository.findAll();

        clienteDtos = clienteMapper.modelDtos(clientes);
        return clienteDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDto findById(Long id) {
        ClienteDto clienteDto = new ClienteDto();

        clienteRepository.findById(id).orElseThrow(
            () -> new ResourceAccessException("El Cliente con el ID " + id + " no existe."));

        clienteDto = clienteMapper.modelToDto(clienteRepository.findById(id).get());
        return clienteDto;
    }


    @Override
    @Transactional
    public ClienteDto save(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();

        cliente = clienteMapper.dtoToModel(clienteDto);
        cliente = clienteRepository.save(cliente);
        clienteDto = clienteMapper.modelToDto(cliente);
        return clienteDto;
    }

    
    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Cliente> cliente = Optional.ofNullable(clienteRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("El Cliente que desea eliminar con el ID " + id + " no existe.")
        ));

        clienteRepository.delete(cliente.get());
    }
}
