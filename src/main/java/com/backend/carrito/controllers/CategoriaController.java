package com.backend.carrito.controllers;

import com.backend.carrito.models.Categoria;
import com.backend.carrito.services.impl.CategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("/api")
public class CategoriaController {

    Map<String, Object> response = new HashMap<>();

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @GetMapping(path = "/categorias")
    public List<Categoria> index(){
        return categoriaService.findAll();
    }

    @GetMapping(path = "/categorias/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id){
        response.clear();
        Categoria categoria = categoriaService.findById(id);
        return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);

    }
    @PostMapping(path = "/categorias")
    public  ResponseEntity<?> create(@Valid @RequestBody Categoria categoria, BindingResult result){
       

        
        // Manejos de errores en la validaciom
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        Categoria newCatgeoria = categoriaService.save(categoria);
        response.put("mensaje", "La categoria se creo con exito.");
        response.put("categoria", newCatgeoria);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/categorias/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Categoria categoria, BindingResult result,  @PathVariable("id") Long id){
        response.clear();
        Categoria categoriaActual = categoriaService.findById(id);
        Categoria newCategoria = null;

        
        // Manejos de errores en la validaciom
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        if (categoriaActual == null) {
            response.put("mensaje", "Error no se pudo actualizar la categoria con el ID: "
                    .concat(id.toString()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        categoriaActual.setNombre(categoria.getNombre());
        newCategoria = categoriaService.save(categoriaActual);

        response.put("mensaje", "La categoria se actualizo con exito.");
        response.put("categoria", newCategoria);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/categorias/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        response.clear();
        categoriaService.delete(id);
        response.put("mensaje", "categoria eliminada");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


}
