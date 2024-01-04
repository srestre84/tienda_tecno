package com.tecnologia.foro.controller;

import com.tecnologia.foro.dto.DatosObtenerUsuario;
import com.tecnologia.foro.dto.DatosRegistroUsuario;
import com.tecnologia.foro.modelo.Usuario;
import com.tecnologia.foro.repository.UsuarioRepository;
import com.tecnologia.foro.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuarioRegistrado = usuarioService.registrarUsuario(datosRegistroUsuario);
        URI uri = uriComponentsBuilder.path("usuarios/{id}").buildAndExpand(usuarioRegistrado.getId()).toUri();
        return ResponseEntity.created(uri).body("Registro exitoso"); // Cambiado a ResponseEntity.ok()
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerDatosUsuario(@PathVariable Long id) {
        try {
            // Aquí usas el ID recuperado de la ruta para obtener los datos del usuario
            Usuario usuario = usuarioService.obtenerDatosUsuario(id);

            // Verificar si el usuario existe
            if (usuario == null) {
                return ResponseEntity.notFound().build(); // Devuelve un código 404 si el usuario no se encuentra
            }

            DatosObtenerUsuario datos = new DatosObtenerUsuario(usuario);
            return ResponseEntity.ok().body(datos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener datos del usuario"); // Manejar errores internos del servidor
        }
    }
}
