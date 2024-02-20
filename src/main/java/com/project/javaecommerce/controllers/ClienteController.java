package com.project.javaecommerce.controllers;

import com.project.javaecommerce.entities.Cliente;
import com.project.javaecommerce.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    // GET ----------------------
    @Operation (summary="Obtener todos los clientes", description="Retorna una lista con la información de cada cliente existente en la base de datos")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @GetMapping ("/listar")
    public List<Cliente> getClientes () {
        return service.obtenerClientes();
    }
    @Operation (summary="Obtener un cliente", description="Retorna la información de un cliente obtenido por su id")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @GetMapping ("/{id}")
    public ResponseEntity<Cliente> getCliente (@PathVariable Long id) {
        Cliente cliente = service.obtenerCliente(id);
        return ResponseEntity.ok(cliente);
    }

    // POST ---------------------
    @Operation (summary="Crear un cliente", description="Permite cargar un nuevo registro con la información de un cliente en la base de datos")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @PostMapping ("/crear")
    public ResponseEntity<Cliente> postCliente (@RequestBody Cliente cliente) {
        Cliente postedCliente = service.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(postedCliente);
    }

    // PUT ----------------------
    @Operation (summary="Modificar un cliente", description="Permite actualizar o modificar la información de un registro de un cliente ya existente en la base de datos, con su id")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @PutMapping ("/modificar/{id}")
    public ResponseEntity<Cliente> updateCliente (@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente updatedCliente = service.modificarCliente(id, cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    // DELETE -------------------
    @Operation (summary="Eliminar todos los clientes", description="Elimina todos los registros de clientes de la base de datos")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @DeleteMapping ("/eliminar/todos")
    public ResponseEntity<Void> deleteClientes () {
        service.eliminarClientes();
        return ResponseEntity.noContent().build();
    }
    @Operation (summary="Eliminar un cliente", description="Elimina el registro de un cliente de la base de datos, por su id")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @DeleteMapping ("/eliminar/{id}")
    public ResponseEntity<Void> deleteCliente (@PathVariable Long id) {
        service.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}