package com.tecnologia.foro.modelo;

import com.tecnologia.foro.dto.DatosRegistroProducto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@NoArgsConstructor
@Data
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String categoria;

	public Producto(String nombre, String categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}


	public Producto(DatosRegistroProducto datosRegistroProducto) {
		this.nombre = datosRegistroProducto.nombre();
		this.categoria = datosRegistroProducto.categoria();
	}
}
