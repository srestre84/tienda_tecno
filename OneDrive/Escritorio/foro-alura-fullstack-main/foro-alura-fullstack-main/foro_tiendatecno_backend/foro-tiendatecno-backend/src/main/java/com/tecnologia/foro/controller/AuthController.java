package com.tecnologia.foro.controller;

import com.tecnologia.foro.dto.DatosAutenticarUsuario;
import com.tecnologia.foro.modelo.Usuario;
import com.tecnologia.foro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody DatosAutenticarUsuario datos) {
        // Lógica de autenticación sin generación de token
        // (por ejemplo, validar credenciales y devolver una respuesta)

        // Obtiene el usuario como UserDetails (de spring security)
        var usuarioAutenticado = usuarioRepository.findByUsuario(datos.usuario());
        // convierte el userDetail a usuario
        Usuario usuario = (Usuario) usuarioAutenticado;

        // si la contraseña es incorrecta
        if (!passwordEncoder.matches(datos.contrasena(), usuario.getContrasena())) {
            return ResponseEntity.badRequest().body("contraseña incorrecta");
        }
        // envia respuesta 200 (ok), ademas de enviar el usuario (en json) como respuesta
        return ResponseEntity.ok().body(usuario);
    }
}

