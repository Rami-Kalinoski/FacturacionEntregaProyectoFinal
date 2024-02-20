package com.project.javaecommerce.services;

import com.project.javaecommerce.entities.Producto;
import com.project.javaecommerce.repositories.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repo;

    // GET ----------------------
    public List<Producto> obtenerProductos () { return repo.findAll(); }
    public Producto obtenerProducto (Long id) {
        return repo.findById(id).orElseThrow( () -> new EntityNotFoundException ("Producto no encontrado con id: "+id));
    }

    // POST ---------------------
    public Producto crearProducto (Producto producto) {
        return repo.save(producto);
    }

    // PUT ----------------------
    public Producto modificarProducto (Long id, Producto producto) {
        producto.setId(id);
        return repo.save(producto);
    }

    // DELETE -------------------
    public void eliminarProductos () {
        repo.deleteAll();
    }
    public void eliminarProducto (Long id) {
        repo.deleteById(id);
    }
}