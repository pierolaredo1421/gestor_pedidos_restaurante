package com.restaurante.repository;

import com.restaurante.model.Pedido;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PedidoRepository {
    private final Map<Integer, Pedido> pedidos = new HashMap<>();

    public void guardarPedido(Pedido pedido) {
        pedidos.put(pedido.getId(), pedido);
    }

    public Optional<Pedido> obtenerPedidoPorId(int id) {
        return Optional.ofNullable(pedidos.get(id));
    }

    public boolean eliminarPedidoPorId(int id) {
        return pedidos.remove(id) != null;
    }

    public Collection<Pedido> obtenerTodosPedidos() {
        return pedidos.values();
    }
}
