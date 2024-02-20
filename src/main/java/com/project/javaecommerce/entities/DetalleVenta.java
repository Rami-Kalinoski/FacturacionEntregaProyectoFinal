package com.project.javaecommerce.entities;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description="ID del detalle de la venta autogestionado por la base de datos", requiredMode=Schema.RequiredMode.AUTO, example="1")
    private Long id;

    // CADA REGISTRO DE DetalleVenta SOLO PERTENECE A UNA VENTA Y A UN PRODUCTO
    @ManyToOne
    @JoinColumn (name="venta_id")
    private Venta venta;
    @ManyToOne
    @JoinColumn (name="id_producto")
    private Producto producto;

    @Column (name="nombre_producto")
    @Schema (description="Nombre y modelo del producto del detalle de la venta", requiredMode=Schema.RequiredMode.AUTO, example="Remera Oversize 'Phanterz Ind'")
    private String nombreProducto; // nombre del producto elegido

    @Column (name="color_producto")
    @Schema (description="Color del producto del detalle de la venta", requiredMode=Schema.RequiredMode.AUTO, example="blanco")
    private String colorProducto; // color del producto elegido (el mismo para cualquier cantidad, sea 1 solo producto o muchos)

    @Column (name="precio_producto")
    @Schema (description="Precio del producto del detalle de la venta", requiredMode=Schema.RequiredMode.AUTO, example="15000.50")
    private Float precioProducto; // precio por unidad del producto al momento en que la venta a la que este registro de DetalleVenta pertenece se creó

    @Column (name="cantidad_producto")
    @Schema (description="Cantidad seleccionada del producto del detalle de la venta", requiredMode=Schema.RequiredMode.AUTO, example="5")
    private Integer cantidadProducto; // cantidad de unidades de ese producto comprados en la venta a la que este registro de DetalleVenta pertenece
}