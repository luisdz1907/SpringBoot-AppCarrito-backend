package com.backend.carrito.controllers;

import com.backend.carrito.dto.ProductoDto;
import com.backend.carrito.exception.ResourceNotFoundException;
import com.backend.carrito.models.Categoria;
import com.backend.carrito.models.Producto;
import com.backend.carrito.services.ICategoriaService;
import com.backend.carrito.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200/" })
@RestController
@RequestMapping("/api")
public class ProductoController {

    Map<String, Object> response = new HashMap<>();

    @Autowired
    private IProductoService productoService;

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping(path = "/productos")
    public List<ProductoDto> index() {
        return productoService.findAll();
    }

    @GetMapping("/productos/page/{page}")
    public Page<Producto> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return productoService.findAll(pageable);
    }

    @GetMapping(path = "/productos/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        response.clear();
        ProductoDto productoDto = null;
        productoDto = productoService.findById(id);
        return new ResponseEntity<ProductoDto>(productoDto, HttpStatus.OK);

    }

    @PostMapping(path = "/productos")
    public ResponseEntity<?> create(@Valid  @RequestBody ProductoDto productoDto) {
        response.clear();
        ProductoDto newProducto = productoService.save(productoDto);


        response.put("mensaje", "Producto creado con exito.");
        response.put("producto", newProducto);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/productos/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductoDto productoDto, BindingResult result,
            @PathVariable Long id) {

        response.clear();
        ProductoDto productoActual = productoService.findById(id);
        ProductoDto productoUpdate = null;

        // Manejos de errores en la validaciom
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (productoActual == null) {
            response.put("mensaje", "Error no se pudo actualizar el producto con el ID: "
                    .concat(id.toString()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        Optional<Categoria> categoria = Optional
                .ofNullable(categoriaService.findById(productoDto.getCategoria().getId()));
        if (!categoria.isPresent()) {
            throw new ResourceNotFoundException("El id de la categoria es nulo");
        }

        productoDto.setCategoria(categoria.get());
        productoActual.setNombre(productoDto.getNombre());
        productoActual.setMarca(productoDto.getMarca());
        productoActual.setStock(productoDto.getStock());
        productoActual.setFechaRegistro(productoDto.getFechaRegistro());
        productoActual.setPrecioCompra(productoDto.getPrecioCompra());
        productoActual.setPrecioVenta(productoDto.getPrecioVenta());
        productoActual.setDescripcion(productoDto.getDescripcion());

        productoUpdate = productoService.save(productoActual);

        response.put("mensaje", "Producto actualizado con exito.");
        response.put("producto", productoUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/productos/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        response.clear();
        productoService.delete(id);
        response.put("mensaje", "Producto eliminado con exito.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}