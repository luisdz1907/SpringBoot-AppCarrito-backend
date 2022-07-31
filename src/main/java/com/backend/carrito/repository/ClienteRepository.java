package com.backend.carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.carrito.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}
