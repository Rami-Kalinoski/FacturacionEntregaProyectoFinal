package com.project.javaecommerce.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="ventas")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
    // VERSIONAR LA CLASE SERIALIZABLE
    private static final long serialVersionUID = 1L;
    // PROPIEDADES ---------------------------------------------------------------------------------
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    @Schema(description="ID de la venta autogestionado por la base de datos", requiredMode=Schema.RequiredMode.AUTO, example="1")
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Schema (description="Fecha en que se realizó la venta", requiredMode=Schema.RequiredMode.AUTO)
    private Date fecha;
    @Column
    @Schema (description="Monto final total de la venta", requiredMode=Schema.RequiredMode.AUTO, example="100000.00")
    private Float total;

    // CLIENTE
    @ManyToOne
    @JoinColumn (name="id_cliente")
    private Cliente cliente;
    // PRODUCTOS
    @OneToMany (mappedBy="venta", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private Set<DetalleVenta> detallesVenta;

    // MÉTODOS -------------------------------------------------------------------------------------
    public DetalleVenta addDetalleVenta (DetalleVenta detalleVenta) {
        getDetallesVenta().add(detalleVenta);
        detalleVenta.setVenta(this);
        return detalleVenta;
    }

    public DetalleVenta removeDetalleVenta (DetalleVenta detalleVenta) {
        getDetallesVenta().remove(detalleVenta);
        detalleVenta.setVenta(null);
        return detalleVenta;
    }
}