package com.example.Final.service;

import com.example.Final.model.Producto;
import com.example.Final.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto obtenerProductoPorId(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void actualizarStock(int id, int nuevoStock) {
        Producto producto = obtenerProductoPorId(id);
        if (producto != null) {
            producto.setStock(nuevoStock);
            productoRepository.save(producto);
        }
    }
}
