package com.tecnologia.foro.services;

import com.tecnologia.foro.modelo.Topico;
import com.tecnologia.foro.repository.ProductoRepository;
import com.tecnologia.foro.repository.RespuestaRepository;
import com.tecnologia.foro.repository.TopicoRepository;
import com.tecnologia.foro.repository.UsuarioRepository;
import com.tecnologia.foro.dto.DatosModificarTopico;
import com.tecnologia.foro.dto.DatosObtenerTopico;
import com.tecnologia.foro.dto.DatosObtenerTopicos;
import com.tecnologia.foro.dto.DatosRegistroTopico;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private RespuestaService respuestaService;

    public Topico registrarTopico(DatosRegistroTopico datosRegistroTopico) {
        Long autorId = datosRegistroTopico.autor_id();
        Long productoId = datosRegistroTopico.producto_id();

        if (autorId == null || productoId == null) {
            throw new IllegalArgumentException("El autor_id y el producto_id no pueden ser nulos");
        }

        var usuario = usuarioRepository.findById(autorId).orElseThrow(() ->
                new EntityNotFoundException(String.format("El usuario con el ID %d no fue encontrado", autorId)));

        var producto = productoRepository.findById(productoId).orElseThrow(() ->
                new EntityNotFoundException(String.format("El producto con el ID %d no fue encontrado", productoId)));

        if (topicoRepository.existsByTituloOrMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            throw new ValidationException("No se pueden tópicos duplicados");
        }

        Topico topico = new Topico(datosRegistroTopico);
        topico.setAutor(usuario);
        topico.setProducto(producto);
        return topicoRepository.save(topico);
    }

    public Page<DatosObtenerTopicos> obtenerTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosObtenerTopicos::new);
    }

    public DatosObtenerTopico obtenerTopico(Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            if (topico.getRespuestas() != null) {
                System.out.println(topico.getRespuestas().stream().toList());
            }
            return new DatosObtenerTopico(topico);
        } else {
            throw new EntityNotFoundException(String.format("El tópico con el ID %d no fue encontrado", id));
        }
    }

    public DatosModificarTopico modificarTopico(DatosModificarTopico datosModificarTopico) {
        Long topicoId = datosModificarTopico.id();

        if (topicoId == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }

        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("El tópico con el ID %d no fue encontrado", topicoId)));

        topico.modificar(datosModificarTopico);
        return new DatosModificarTopico(topico);
    }

    public void eliminarTopico(Long id) {
        if (id != null) {
            topicoRepository.deleteById(id);
            // No es necesario verificar si se encuentra el tópico, ya que deleteById manejará la lógica automáticamente
        } else {
            throw new ValidationException("El id no puede ser nulo");
        }
    }
}
