package com.project.javaecommerce.controllers;

import com.project.javaecommerce.entities.Producto;
import com.project.javaecommerce.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService service;

    // GET ----------------------
    @GetMapping ("/listar")
    public List<Producto> getProductos () {
        return service.obtenerProductos();
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Producto> getProducto (@PathVariable Long id) {
        Producto producto = service.obtenerProducto(id);
        return ResponseEntity.ok(producto);
    }

    // POST ---------------------
    @PostMapping ("/crear")
    public ResponseEntity<Producto> postProducto (@RequestBody Producto producto) {
        Producto postedProducto = service.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postedProducto);
    }

    // PUT ----------------------
    @PutMapping ("/modificar/{id}")
    public ResponseEntity<Producto> updateProducto (@PathVariable Long id, @RequestBody Producto producto) {
        Producto updatedProducto = service.modificarProducto(id, producto);
        return ResponseEntity.ok(updatedProducto);
    }

    // DELETE -------------------
    @DeleteMapping ("eliminar/todos")
    public ResponseEntity<Void> deleteProductos () {
        service.eliminarProductos();
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping ("eliminar/{id}")
    public ResponseEntity<Void> deleteProducto (@PathVariable Long id) {
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}