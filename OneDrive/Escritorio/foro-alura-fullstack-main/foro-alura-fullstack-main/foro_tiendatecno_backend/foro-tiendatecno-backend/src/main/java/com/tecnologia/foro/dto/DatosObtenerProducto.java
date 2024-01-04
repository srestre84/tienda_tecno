package com.tecnologia.foro.dto;

import com.tecnologia.foro.modelo.Producto;

public record DatosObtenerProducto(
        Long id,
        String nombre,
        String categoria
) {
    public DatosObtenerProducto(Producto producto){
        this(
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria()
        );
    }
}
