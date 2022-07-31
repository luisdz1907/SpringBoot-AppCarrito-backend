package com.backend.carrito.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank
    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @NotBlank
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    @Past
    @JsonFormat(pattern = "MM-dd-yy")
    private Date fechaNacimiento;

    @NotBlank
    @Column(name = "documento", nullable = false)
    private String documento;

    @NotBlank
    @Column(name = "telefono", nullable = false)
    private String telefono;

}
