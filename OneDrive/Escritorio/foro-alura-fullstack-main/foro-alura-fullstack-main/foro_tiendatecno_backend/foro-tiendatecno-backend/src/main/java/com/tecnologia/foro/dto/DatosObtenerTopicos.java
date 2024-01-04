package com.tecnologia.foro.dto;

import com.tecnologia.foro.modelo.StatusTopico;
import com.tecnologia.foro.modelo.Topico;

import java.time.LocalDateTime;
import java.util.List;

public record DatosObtenerTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        StatusTopico statusTopico,
        String autor,
        Long autorId,
        String curso,
        List<DatosObtenerRespuesta> respuestas
) {
    public DatosObtenerTopicos(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getAutor().getId(),
                topico.getProducto().getNombre(),
                topico.getRespuestas().stream().map(DatosObtenerRespuesta::new).toList()
        );
    }
}
