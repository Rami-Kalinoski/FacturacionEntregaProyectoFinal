package com.project.javaecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="detalles_ventas")
@NamedQuery(name="DetalleVenta.findAll", query="SELECT d FROM DetalleVenta d")
public class DetalleVenta {
    // VERSIONAR LA CLASE SERIALIZABLE
    private static final long serialVersionUID = 1L;
    // PROPIEDADES ---------------------------------------------------------------------------------
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Long id;

    // CADA REGISTRO DE DetalleVenta SOLO PERTENECE A UNA VENTA Y A UN PRODUCTO
    @ManyToOne
    @JoinColumn (name="venta_id")
    private Venta venta;
    @ManyToOne
    @JoinColumn (name="id_producto")
    private Producto producto;

    @Column (name="nombre_producto")
    private String nombreProducto; // nombre del producto elegido

    @Column (name="color_producto")
    private String colorProducto; // color del producto elegido (el mismo para cualquier cantidad, sea 1 solo producto o muchos)

    @Column (name="precio_producto")
    private Float precioProducto; // precio por unidad del producto al momento en que la venta a la que este registro de DetalleVenta pertenece se creó

    @Column (name="cantidad_producto")
    private Integer cantidadProducto; // cantidad de unidades de ese producto comprados en la venta a la que este registro de DetalleVenta pertenece
}