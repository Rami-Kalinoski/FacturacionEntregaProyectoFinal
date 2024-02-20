package com.project.javaecommerce.controllers;

import com.project.javaecommerce.entities.Producto;
import com.project.javaecommerce.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary="Obtener todos los productos", description="Retorna una lista con la información de cada producto existente en la base de datos")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @GetMapping ("/listar")
    public List<Producto> getProductos () {
        return service.obtenerProductos();
    }
    @Operation (summary="Obtener un producto", description="Retorna la información de un producto obtenido por su id")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @GetMapping ("/{id}")
    public ResponseEntity<Producto> getProducto (@PathVariable Long id) {
        Producto producto = service.obtenerProducto(id);
        return ResponseEntity.ok(producto);
    }

    // POST ---------------------
    @Operation (summary="Crear un producto", description="Permite cargar un nuevo registro con la información de un producto en la base de datos")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @PostMapping ("/crear")
    public ResponseEntity<Producto> postProducto (@RequestBody Producto producto) {
        Producto postedProducto = service.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postedProducto);
    }

    // PUT ----------------------
    @Operation (summary="Modificar un producto", description="Permite actualizar o modificar la información de un registro de un producto ya existente en la base de datos, con su id")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @PutMapping ("/modificar/{id}")
    public ResponseEntity<Producto> updateProducto (@PathVariable Long id, @RequestBody Producto producto) {
        Producto updatedProducto = service.modificarProducto(id, producto);
        return ResponseEntity.ok(updatedProducto);
    }

    // DELETE -------------------
    @Operation (summary="Eliminar todos los productos", description="Elimina todos los registros de productos de la base de datos")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @DeleteMapping ("eliminar/todos")
    public ResponseEntity<Void> deleteProductos () {
        service.eliminarProductos();
        return ResponseEntity.noContent().build();
    }
    @Operation (summary="Eliminar un producto", description="Elimina el registro de un producto de la base de datos, por su id")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @DeleteMapping ("eliminar/{id}")
    public ResponseEntity<Void> deleteProducto (@PathVariable Long id) {
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}