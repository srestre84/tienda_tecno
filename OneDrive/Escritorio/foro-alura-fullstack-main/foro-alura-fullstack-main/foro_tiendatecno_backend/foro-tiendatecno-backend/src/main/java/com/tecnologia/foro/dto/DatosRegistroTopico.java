package com.tecnologia.foro.dto;

import lombok.Getter;

@Getter
public class DatosRegistroTopico {
    private final String titulo;
    private final String mensaje;
    private final Long autor_id;
    private final Long producto_id;

    public DatosRegistroTopico(String titulo, String mensaje, Long autor_id, Long producto_id) {
        // Realizar validaciones aquí si es necesario
        if (titulo == null || mensaje == null || autor_id == null || producto_id == null) {
            throw new IllegalArgumentException("Todos los campos deben tener valores no nulos");
        }

        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor_id = autor_id;
        this.producto_id = producto_id;
    }

    // Puedes agregar otros métodos según sea necesario

    @Override
    public String toString() {
        return "DatosRegistroTopico{" +
                "titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", autor_id=" + autor_id +
                ", producto_id=" + producto_id +
                '}';
    }

    public Long autor_id() {
        return autor_id;
    }

    public Long producto_id() {
return producto_id;
    }

    public String titulo() {
    return titulo;
    }

    public String mensaje() {
    return mensaje;
    }
}
