package com.tecnologia.foro.repository;

import com.tecnologia.foro.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloOrMensaje(String titulo, String mensaje);

    Topico[] findByAutorId(Long id);
}
