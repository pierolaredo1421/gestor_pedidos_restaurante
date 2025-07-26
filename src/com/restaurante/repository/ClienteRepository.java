package com.restaurante.repository;

import com.restaurante.model.Cliente;

import java.util.*;

public class ClienteRepository {
    private final Map<Integer, Cliente> clientes = new HashMap<>();

    public void registrarCliente(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
    }

    public Optional<Cliente> buscarClientePorId(int id) {
        return Optional.ofNullable(clientes.get(id));
    }

    public boolean eliminarCliente(int id) {
        return clientes.remove(id) != null;
    }

    public Collection<Cliente> listarClientes() {
        return clientes.values();
    }
}
