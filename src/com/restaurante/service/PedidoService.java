package com.restaurante.service;

import com.restaurante.exceptions.PedidoException;
import com.restaurante.factory.PedidoFactory;
import com.restaurante.model.Cliente;
import com.restaurante.model.Pedido;
import com.restaurante.model.Plato;
import com.restaurante.repository.PedidoRepository;

import java.util.Collection;
import java.util.List;

public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void registrarPedido(Cliente cliente, List<Plato> platos) throws PedidoException {
        if (cliente == null) {
            throw new PedidoException("El pedido debe tener un cliente.");
        }
        if (platos == null || platos.isEmpty()) {
            throw new PedidoException("El pedido debe tener al menos un plato.");
        }
        Pedido pedido = PedidoFactory.crearPedido(cliente, platos); // uso del factory
        pedidoRepository.guardarPedido(pedido); // uso del repository
    }

    public Pedido buscarPedido(int id) throws PedidoException {
        return pedidoRepository.obtenerPedidoPorId(id).orElseThrow(() -> new PedidoException("El pedido no existe"));
    }

    public void eliminarPedido(int id) throws PedidoException {
        if (!pedidoRepository.eliminarPedidoPorId(id)) {
            throw new PedidoException("No se pudo eliminar, pedido no encontrado con id: " + id);
        }
    }

    public void cambiarEstadoPedido(int id, Pedido.Estado nuevoEstado) throws PedidoException {
        Pedido pedido = buscarPedido(id);
        pedido.setEstado(nuevoEstado);
        pedidoRepository.guardarPedido(pedido);
    }

    public Collection<Pedido> listaPedidos () throws PedidoException {
        return pedidoRepository.obtenerTodosPedidos();
    }
}
