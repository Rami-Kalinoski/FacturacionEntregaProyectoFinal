package com.project.javaecommerce.services;

import com.project.javaecommerce.entities.Cliente;
import com.project.javaecommerce.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    // GET ----------------------
    public List<Cliente> obtenerClientes () {
        return repo.findAll();
    }
    public Cliente obtenerCliente (Long id) {
        return repo.findById(id).orElseThrow( () -> new EntityNotFoundException ("Cliente no encontrado con id: "+id));
    }

    // POST ---------------------
    public Cliente crearCliente (Cliente cliente) {
        return repo.save(cliente);
    }

    // PUT ----------------------
    public Cliente modificarCliente (Long id, Cliente cliente) {
        cliente.setId(id);
        return repo.save(cliente);
    }

    // DELETE -------------------
    public void eliminarClientes () {
        repo.deleteAll();
    }
    public void eliminarCliente (Long id) {
        repo.deleteById(id);
    }
}