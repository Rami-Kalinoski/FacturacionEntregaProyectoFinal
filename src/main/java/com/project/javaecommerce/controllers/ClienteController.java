package com.project.javaecommerce.controllers;

import com.project.javaecommerce.entities.Cliente;
import com.project.javaecommerce.services.ClienteService;
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
    @GetMapping ("/listar")
    public List<Cliente> getClientes () {
        return service.obtenerClientes();
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Cliente> getCliente (@PathVariable Long id) {
        Cliente cliente = service.obtenerCliente(id);
        return ResponseEntity.ok(cliente);
    }

    // POST ---------------------
    @PostMapping ("/crear")
    public ResponseEntity<Cliente> postCliente (@RequestBody Cliente cliente) {
        Cliente postedCliente = service.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(postedCliente);
    }

    // PUT ----------------------
    @PutMapping ("/modificar/{id}")
    public ResponseEntity<Cliente> updateCliente (@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente updatedCliente = service.modificarCliente(id, cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    // DELETE -------------------
    @DeleteMapping ("/eliminar/todos")
    public ResponseEntity<Void> deleteClientes () {
        service.eliminarClientes();
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping ("/eliminar/{id}")
    public ResponseEntity<Void> deleteCliente (@PathVariable Long id) {
        service.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}