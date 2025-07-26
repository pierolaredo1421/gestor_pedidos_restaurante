package com.restaurante.service;

import com.restaurante.exceptions.ClienteException;
import com.restaurante.model.Cliente;
import com.restaurante.repository.ClienteRepository;

import java.util.Collection;

public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void registrarCliente(Cliente cliente) throws ClienteException {
        if (clienteRepository.buscarClientePorId(cliente.getId()).isPresent()) {
            throw new ClienteException("El cliente con id " + cliente.getId() + " ya existe");
        }
        clienteRepository.registrarCliente(cliente);
    }

    public Cliente buscarClientePorId(int id) throws ClienteException {
        return clienteRepository.buscarClientePorId(id)
                .orElseThrow(() -> new ClienteException("Cliente no encontrado con id: " + id));
    }

    public Collection<Cliente> mostrarClientes(){
        return clienteRepository.listarClientes();
    }

    public void eliminarCliente(int id) throws ClienteException {
        if (!clienteRepository.eliminarCliente(id)){
            throw new ClienteException("No se pudo eliminar, cliente no encontrado con id: " + id);
        }
    }
}
