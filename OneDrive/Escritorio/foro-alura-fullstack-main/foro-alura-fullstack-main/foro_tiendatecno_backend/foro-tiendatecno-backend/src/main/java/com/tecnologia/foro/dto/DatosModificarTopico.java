package com.tecnologia.foro.dto;

import com.tecnologia.foro.modelo.StatusTopico;
import com.tecnologia.foro.modelo.Topico;

public record DatosModificarTopico(
        Long id,
        String titulo,
        String mensaje,
//        LocalDateTime fecha,
        StatusTopico statusTopico
//        String autor,
//        String curso
) {
    public DatosModificarTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
//                topico.getFechaCreacion(),
                topico.getStatus()
//                topico.getAutor().getNombre(),
//                topico.getCurso().getNombre()
        );
    }
}
