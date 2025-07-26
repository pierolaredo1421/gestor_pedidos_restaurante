package com.restaurante.factory;

import com.restaurante.model.Cliente;
import com.restaurante.model.Pedido;
import com.restaurante.model.Plato;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PedidoFactory {
    private static final AtomicInteger generadorId = new AtomicInteger(1);

    public static Pedido crearPedido(Cliente cliente, List<Plato> platos) {
        int nuevoId = generadorId.getAndIncrement();
        return new Pedido(nuevoId, cliente, platos);
    }
}
