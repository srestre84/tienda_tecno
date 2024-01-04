package com.tecnologia.foro.controller;

import com.tecnologia.foro.dto.DatosObtenerProducto;
import com.tecnologia.foro.dto.DatosRegistroProducto;
import com.tecnologia.foro.services.ProductoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@SecurityRequirement(name = "bearer-key")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    @Transactional
    public ResponseEntity registrarProducto(@RequestBody DatosRegistroProducto datosRegistroProducto) {
        try {
            var producto = productoService.registraProducto(datosRegistroProducto);
            return ResponseEntity.ok().body(producto);
        } catch (Exception e) {
            // Manejar la excepción adecuadamente
            return ResponseEntity.status(500).body("Error al registrar el producto.");
        }
    }

    @GetMapping
    public ResponseEntity obtenerProductos() {
        var productos = productoService.obtenerProductos().stream().map(DatosObtenerProducto::new).toList();

        return ResponseEntity.ok().body(productos);
    }
}
