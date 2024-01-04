package com.tecnologia.foro.dto;

import com.tecnologia.foro.modelo.Respuesta;

public record DatosModificarRespuesta(
        Long id,
        String mensaje,
        Boolean solucion
) {
    public DatosModificarRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getSolucion()
        );
    }
}
