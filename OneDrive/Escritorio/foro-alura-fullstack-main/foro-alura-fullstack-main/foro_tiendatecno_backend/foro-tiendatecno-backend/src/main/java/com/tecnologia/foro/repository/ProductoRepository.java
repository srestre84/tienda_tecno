package com.tecnologia.foro.repository;

import com.tecnologia.foro.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
