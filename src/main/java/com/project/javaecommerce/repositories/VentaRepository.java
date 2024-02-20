package com.project.javaecommerce.repositories;

import com.project.javaecommerce.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}