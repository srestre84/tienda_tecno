package com.tecnologia.foro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroUsuario (
        @NotNull
        String nombre,
        @NotNull
        String usuario,
        @NotNull
        @Email
        String email,
        @NotNull
        @Pattern(regexp = "^[a-zA-Z0-9]{6}.*$") // min 6 charachters
        String contrasena
) {
    public CharSequence getContrasena() {
        return contrasena;
    }
}
