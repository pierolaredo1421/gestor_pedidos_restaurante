package com.restaurante.controller;

import com.restaurante.exceptions.ClienteException;
import com.restaurante.exceptions.PedidoException;
import com.restaurante.exceptions.PlatoException;
import com.restaurante.model.Cliente;
import com.restaurante.model.Pedido;
import com.restaurante.model.Plato;
import com.restaurante.service.ClienteService;
import com.restaurante.service.PedidoService;
import com.restaurante.service.PlatoService;

import java.util.Collection;
import java.util.List;

public class RestauranteController {
    private final ClienteService clienteService;
    private final PlatoService platoService;
    private final PedidoService pedidoService;

    public RestauranteController(ClienteService clienteService, PlatoService platoService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.platoService = platoService;
        this.pedidoService = pedidoService;
    }

    // CLIENTES
    public void registrarCliente(Cliente cliente) throws ClienteException {
        clienteService.registrarCliente(cliente);
    }

    public Cliente buscarClientePorId(int id) throws ClienteException {
        return clienteService.buscarClientePorId(id);
    }

    public void eliminarCliente(int id) throws ClienteException {
        clienteService.eliminarCliente(id);
    }

    public Collection<Cliente> listarClientes() {
        return clienteService.mostrarClientes();
    }

    // PLATOS
    public void agregarPlato(int id, String nombre, double precio, String tipo) throws PlatoException {
        platoService.agregarPlato(id, nombre, precio, tipo);
    }

    public Plato buscarPlatoPorId(int id) throws PlatoException {
        return platoService.buscarPlatoPorId(id);
    }

    public void eliminarPlato(int id) throws PlatoException {
        platoService.eliminarPlato(id);
    }

    public Collection<Plato> listarPlatos() throws PlatoException {
        return platoService.listarPlatos();
    }

    // PEDIDOS
    public void crearPedido(Cliente cliente, List<Plato> platos) throws PedidoException {
        pedidoService.registrarPedido(cliente, platos);
    }

    public Pedido buscarPedidoPorId(int id) throws PedidoException {
        return pedidoService.buscarPedido(id);
    }

    public void eliminarPedido(int id) throws PedidoException {
        pedidoService.eliminarPedido(id);
    }

    public Collection<Pedido> listarPedidos() throws PedidoException {
        return pedidoService.listaPedidos();
    }

    public void cambiarEstadoPedido(int id, Pedido.Estado estado) throws PedidoException {
        pedidoService.cambiarEstadoPedido(id, estado);

    }
}
