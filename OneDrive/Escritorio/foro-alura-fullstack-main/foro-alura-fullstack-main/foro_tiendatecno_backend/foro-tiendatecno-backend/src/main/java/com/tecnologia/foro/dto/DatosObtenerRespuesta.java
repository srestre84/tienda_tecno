package com.tecnologia.foro.dto;

import com.tecnologia.foro.modelo.Respuesta;

import java.time.LocalDateTime;

public record DatosObtenerRespuesta(
        Long id,
        Long autor_id,
        String autor,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion
) {
    public DatosObtenerRespuesta(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getAutor().getId(),
                respuesta.getAutor().getNombre(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion()
        );
    }
}
