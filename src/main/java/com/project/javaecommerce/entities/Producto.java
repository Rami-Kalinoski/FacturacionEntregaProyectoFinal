package com.project.javaecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="productos")
public class Producto {
    // PROPIEDADES ---------------------------------------------------------------------------------
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Schema (description="ID del producto autogestionado por la base de datos", requiredMode=Schema.RequiredMode.AUTO, example="1")
    private Long id;
    @Column
    @Schema (description="Nombre y modelo del producto", requiredMode=Schema.RequiredMode.AUTO, example="Remera Oversize 'Phanterz Ind'")
    private String nombre;
    @Column
    @Schema (description="Color del producto", requiredMode=Schema.RequiredMode.AUTO, example="blanco")
    private String color;
    @Column
    @Schema (description="Stock disponible del producto", requiredMode=Schema.RequiredMode.AUTO, example="100")
    private Integer stock;
    @Column
    @Schema (description="Precio del producto por unidad", requiredMode=Schema.RequiredMode.AUTO, example="15000.50")
    private Float precio;
}