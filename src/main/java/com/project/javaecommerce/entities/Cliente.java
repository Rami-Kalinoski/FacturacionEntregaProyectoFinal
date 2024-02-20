package com.project.javaecommerce.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="clientes")
public class Cliente {
    // PROPIEDADES ---------------------------------------------------------------------------------
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    @Schema (description="ID del cliente autogestionado por la base de datos", requiredMode=Schema.RequiredMode.AUTO, example="1")
    private Long id;
    @Column
    @Schema (description="DNI del cliente", requiredMode=Schema.RequiredMode.AUTO, example="40000000")
    private Integer dni;
    @Column
    @Schema (description="Nombre del cliente", requiredMode=Schema.RequiredMode.AUTO, example="Pedro")
    private String nombre;
    @Column
    @Schema (description="Apellido del cliente", requiredMode=Schema.RequiredMode.AUTO, example="Sanchez")
    private String apellido;
    @Column
    @Schema (description="Email del cliente", requiredMode=Schema.RequiredMode.AUTO, example="pepesanchez@gmail.com")
    private String email;

    // VENTAS ASOCIADAS AL CLIENTE
    @OneToMany (mappedBy="cliente")
    private List<Venta> ventas;
}