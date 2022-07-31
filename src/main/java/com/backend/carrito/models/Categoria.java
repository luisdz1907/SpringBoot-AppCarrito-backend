package com.backend.carrito.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categoria")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la categoria no puede estar vacio.")
    @Column(length = 45, nullable = false, unique = true)
    @Size(min = 4, max = 45)
    private String nombre;


}