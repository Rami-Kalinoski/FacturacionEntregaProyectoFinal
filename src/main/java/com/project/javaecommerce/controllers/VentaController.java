package com.project.javaecommerce.controllers;

import com.project.javaecommerce.entities.Venta;
import com.project.javaecommerce.entities.VentaDTO;
import com.project.javaecommerce.repositories.VentaRepository;
import com.project.javaecommerce.services.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary="Obtener todas las ventas", description="Retorna una lista con la información de cada venta existente en la base de datos")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @GetMapping ("/listar")
    public List<VentaDTO> getVentas () { return service.obtenerVentasDTO(); }
    @Operation (summary="Obtener una venta", description="Retorna la información de una venta obtenida por su id")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @GetMapping ("/{id}")
    public ResponseEntity<VentaDTO> getVenta (@PathVariable Long id) {
        VentaDTO ventaDTO = service.obtenerVentaDTO(id);
        return ResponseEntity.ok(ventaDTO);
    }

    // POST ---------------------
    @Operation (summary="Crear una venta", description="Permite cargar un nuevo registro con la información de una venta en la base de datos")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @PostMapping ("/crear")
    public ResponseEntity<VentaDTO> postVenta (@RequestBody Venta venta) {
        VentaDTO postedVentaDTO = service.crearVentaDTO(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(postedVentaDTO);
    }

    // PUT ----------------------
    @Operation (summary="Modificar una venta", description="Permite actualizar o modificar la información de un registro de una venta ya existente en la base de datos, con su id")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @PutMapping ("/modificar/{id}")
    public ResponseEntity<VentaDTO> updateVenta (@PathVariable Long id, @RequestBody Venta venta) {
        VentaDTO updatedVentaDTO = service.modificarVentaDTO(id, venta);
        return ResponseEntity.ok(updatedVentaDTO);
    }

    // DELETE -------------------
    @Operation (summary="Eliminar todas las ventas", description="Elimina todos los registros de ventas de la base de datos")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @DeleteMapping ("/eliminar/todas")
    public ResponseEntity<Void> deleteVentas () {
        service.eliminarVentas();
        return ResponseEntity.noContent().build();
    }
    @Operation (summary="Eliminar una venta", description="Elimina el registro de una venta de la base de datos, por su id")
    @ApiResponses (value={
            @ApiResponse(responseCode="200", description="Operación exitosa"),
            @ApiResponse(responseCode="400", description="Operación fallida")
    })
    @DeleteMapping ("/eliminar/{id}")
    public ResponseEntity<Void> deleteVenta (@PathVariable Long id) {
        service.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}