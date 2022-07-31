package com.backend.carrito.dto;

import com.backend.carrito.models.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductoDto {


    private Long id;
    private String nombre;
    private String marca;
    private String stock;
    private Date fechaRegistro;
    private String precioCompra;
    private String precioVenta;
    private String descripcion;
    private Categoria categoria;
}
