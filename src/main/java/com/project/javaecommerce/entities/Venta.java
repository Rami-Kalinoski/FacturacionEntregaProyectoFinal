package com.project.javaecommerce.entities;

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
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column
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