package com.project.javaecommerce.controllers;

import com.project.javaecommerce.entities.Venta;
import com.project.javaecommerce.entities.VentaDTO;
import com.project.javaecommerce.repositories.VentaRepository;
import com.project.javaecommerce.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/ventas")
public class VentaController {
    @Autowired
    private VentaService service;

    // GET ----------------------
    @GetMapping ("/listar")
    public List<VentaDTO> getVentas () { return service.obtenerVentasDTO(); }
    @GetMapping ("/{id}")
    public ResponseEntity<VentaDTO> getVenta (@PathVariable Long id) {
        VentaDTO ventaDTO = service.obtenerVentaDTO(id);
        return ResponseEntity.ok(ventaDTO);
    }

    // POST ---------------------
    @PostMapping ("/crear")
    public ResponseEntity<VentaDTO> postVenta (@RequestBody Venta venta) {
        VentaDTO postedVentaDTO = service.crearVentaDTO(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(postedVentaDTO);
    }

    // PUT ----------------------
    @PutMapping ("/modificar/{id}")
    public ResponseEntity<VentaDTO> updateVenta (@PathVariable Long id, @RequestBody Venta venta) {
        VentaDTO updatedVentaDTO = service.modificarVentaDTO(id, venta);
        return ResponseEntity.ok(updatedVentaDTO);
    }

    // DELETE -------------------
    @DeleteMapping ("/eliminar/todas")
    public ResponseEntity<Void> deleteVentas () {
        service.eliminarVentas();
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping ("/eliminar/{id}")
    public ResponseEntity<Void> deleteVenta (@PathVariable Long id) {
        service.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}