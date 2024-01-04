package com.tecnologia.foro.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tecnologia.foro.dto.DatosModificarTopico;
import com.tecnologia.foro.dto.DatosRegistroTopico;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fechaCreacion = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Usuario autor;

	@OneToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topico")
	private List<Respuesta> respuestas = new ArrayList<>();

	public Topico(String titulo, String mensaje, Producto producto) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.producto = producto;
	}

	public Topico(DatosRegistroTopico datosRegistroTopico) {
		this.titulo = datosRegistroTopico.titulo();
		this.mensaje = datosRegistroTopico.mensaje();
	}

	public void modificar(DatosModificarTopico datosModificarTopico) {
		if (datosModificarTopico.titulo() != null) {
			this.titulo = datosModificarTopico.titulo();
		}
		if (datosModificarTopico.mensaje() != null) {
			this.mensaje = datosModificarTopico.mensaje();
		}
		if (datosModificarTopico.statusTopico() != null) {
			this.status = datosModificarTopico.statusTopico();
		}
	}

	public void agregarRespuesta(Respuesta respuesta) {
		this.respuestas.add(respuesta);
	}
}
