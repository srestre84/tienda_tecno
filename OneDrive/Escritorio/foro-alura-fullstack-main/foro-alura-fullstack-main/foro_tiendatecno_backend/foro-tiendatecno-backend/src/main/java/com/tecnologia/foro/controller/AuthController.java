package com.tecnologia.foro.controller;

import com.tecnologia.foro.dto.DatosAutenticarUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody DatosAutenticarUsuario datos) {
        // Lógica de autenticación sin generación de token
        // (por ejemplo, validar credenciales y devolver una respuesta)
        return ResponseEntity.ok("Autenticación exitosa sin generar token");
    }
}

