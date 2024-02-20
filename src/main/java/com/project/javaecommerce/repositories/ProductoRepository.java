package com.project.javaecommerce.repositories;

import com.project.javaecommerce.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}