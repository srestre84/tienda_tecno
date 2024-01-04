package com.tecnologia.foro.services;

import com.tecnologia.foro.dto.DatosRegistroProducto;
import com.tecnologia.foro.modelo.Producto;
import com.tecnologia.foro.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto registraProducto(DatosRegistroProducto datosRegistroProducto) {
        Producto producto = new Producto(datosRegistroProducto);

        return productoRepository.save(producto);
    }

    public List<Producto> obtenerProductos() {
        var productos = productoRepository.findAll();

        return productos;
    }
}
