package com.project.javaecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {
    private Long id;
    private Date fecha;
    private Integer cantidad;
    private Float total;
    private Cliente cliente;
    private Set<DetalleVenta> detallesVenta;
}