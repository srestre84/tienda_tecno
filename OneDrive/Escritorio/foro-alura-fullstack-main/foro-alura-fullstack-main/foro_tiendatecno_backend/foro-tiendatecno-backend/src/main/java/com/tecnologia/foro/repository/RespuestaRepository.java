package com.tecnologia.foro.repository;

import com.tecnologia.foro.modelo.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    Respuesta[] findByAutorId(Long id);
}
