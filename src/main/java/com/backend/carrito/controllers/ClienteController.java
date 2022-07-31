package com.backend.carrito.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.carrito.dto.ClienteDto;
import com.backend.carrito.services.impl.ClienteServiceImpl;

@RestController
@CrossOrigin(origins = { "http://localhost:4200/" })
@RequestMapping("/api")
public class ClienteController {
    Map<String, Object> response = new HashMap<>();

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping(path = "/clientes")
    public List<ClienteDto> index(){
        return clienteService.findAll();
    }

    @GetMapping(path = "/clientes/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        response.clear();
        ClienteDto cliente = null;
        cliente = clienteService.findById(id);

        return new ResponseEntity<ClienteDto>(cliente, HttpStatus.OK);
    }

    @PostMapping(path = "/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody ClienteDto clienteDto){
        response.clear();
        ClienteDto newCliente = clienteService.save(clienteDto);

        response.put("mensaje", "Cliente creado con exito.");
        response.put("cliente", newCliente);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ClienteDto clienteDto,
            @PathVariable Long id) {

        response.clear();
        ClienteDto clienteActual = clienteService.findById(id);
        ClienteDto clienteUpdate = null;

        if (clienteActual == null) {
            response.put("mensaje", "Error no se pudo actualizar el cliente con el ID: "
                    .concat(id.toString()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }


        clienteActual.setNombre(clienteDto.getNombre());
        clienteActual.setApellidos(clienteDto.getApellidos());
        clienteActual.setDireccion(clienteDto.getDireccion());
        clienteActual.setFechaNacimiento(clienteDto.getFechaNacimiento());
        clienteActual.setDocumento(clienteDto.getDocumento());

        clienteUpdate = clienteService.save(clienteActual);

        response.put("mensaje", "Cliente actualizado con exito.");
        response.put("cliente", clienteUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        response.clear();
        clienteService.delete(id);
        response.put("mensaje", "Cliente eliminado con exito.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
