package com.tecnologia.foro.services;

import com.tecnologia.foro.dto.DatosRegistroUsuario;
import com.tecnologia.foro.modelo.Respuesta;
import com.tecnologia.foro.modelo.Topico;
import com.tecnologia.foro.modelo.Usuario;
import com.tecnologia.foro.repository.RespuestaRepository;
import com.tecnologia.foro.repository.TopicoRepository;
import com.tecnologia.foro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        Usuario usuario = new Usuario(datosRegistroUsuario);
        usuario.setContrasena(passwordEncoder.encode(datosRegistroUsuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerDatosUsuario(Long id) {
        boolean existeUsuario = usuarioRepository.existsById(id);
        if (existeUsuario) {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            if (usuario != null) {
                List<Topico> obtenerTopicos = Arrays.stream(topicoRepository.findByAutorId(id)).collect(Collectors.toList());
                List<Respuesta> obtenerRespuestas = Arrays.stream(respuestaRepository.findByAutorId(id)).collect(Collectors.toList());

                usuario.setTopicos(obtenerTopicos);
                usuario.setRespuestas(obtenerRespuestas);

                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
}
