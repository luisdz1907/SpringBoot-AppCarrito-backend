package com.backend.carrito.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
@Builder
@Setter
@Getter
public class ErrorMensaje {
    private int codigo;
    private Date timestamp;
    private String mensaje;
    private String descripcion;

    public ErrorMensaje(int codigo, Date timestamp, String mensaje, String descripcion) {
        this.codigo = codigo;
        this.timestamp = timestamp;
        this.mensaje = mensaje;
        this.descripcion = descripcion;
    }
}
