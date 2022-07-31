package com.backend.carrito.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

    private Long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private Date fechaNacimiento;
    private String documento;
    private String telefono;
}
