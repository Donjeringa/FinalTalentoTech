package com.example.Final.service;

import com.example.Final.exception.StockInsuficienteException;
import com.example.Final.model.LineaPedido;
import com.example.Final.model.Pedido;
import com.example.Final.model.Producto;
import com.example.Final.repository.PedidoRepository;
import com.example.Final.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Pedido crearPedido(Pedido pedido) {
        try {
            for (LineaPedido linea : pedido.getLineas()) {
                Producto p = productoRepository.findById(linea.getProducto().getId()).orElse(null);
                if (p == null || p.getStock() < linea.getCantidad()) {
                    throw new StockInsuficienteException("Stock insuficiente para el producto: " + linea.getProducto().getId());
                }
                p.setStock(p.getStock() - linea.getCantidad());
                productoRepository.save(p);
            }
            pedido.setEstado("pendiente");
            return pedidoRepository.save(pedido);
        } catch (StockInsuficienteException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el pedido: " + e.getMessage());
        }
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}
