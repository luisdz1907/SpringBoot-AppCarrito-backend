package com.backend.carrito.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonInclude(JsonInclude.Include.ALWAYS)
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @NotBlank
    @Column(name = "marca", nullable = false)
    private String marca;

    @NotBlank
    @Column(name = "stock", nullable = false)
    private String stock;

    @Column(name = "fechaRegistro",nullable = false)
    @Past
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MM-dd-yy")
    private Date fechaRegistro;

    @Column(name = "precioCompra",nullable = false)
    private Double precioCompra;

    @Column(name = "precioVenta", nullable = false)
    private Double precioVenta;

    @Size(min = 4, max = 45, message = "El tamaño debe estar entre 4 y 45 caracteres")
    @Column(name = "descripcion",nullable = false)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @PrePersist
    public void perPersist(){
        fechaRegistro = new Date();
    }
}
