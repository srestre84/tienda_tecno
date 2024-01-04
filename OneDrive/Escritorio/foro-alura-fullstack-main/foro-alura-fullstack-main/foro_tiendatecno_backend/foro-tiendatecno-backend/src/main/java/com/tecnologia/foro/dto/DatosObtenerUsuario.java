package com.tecnologia.foro.dto;

import com.tecnologia.foro.modelo.Usuario;

import java.util.List;

public record DatosObtenerUsuario(
        Long id,
        String nombre,
        List<DatosObtenerTopico> topicos,
        List<DatosObtenerRespuesta> respuestas
) {
    public DatosObtenerUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getTopicos().stream().map(DatosObtenerTopico::new).toList(),
                usuario.getRespuestas().stream().map(DatosObtenerRespuesta::new).toList()
        );
    }
}
