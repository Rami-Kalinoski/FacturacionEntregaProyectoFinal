package com.project.javaecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaDTO {
    private Long id;
    private Venta venta;
    private Producto producto;
    private String nombreProducto;
    private String colorProducto;
    private Float precioProducto;
    private Integer cantidadProducto;
}