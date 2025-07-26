package com.restaurante.view;

import com.restaurante.exceptions.ClienteException;
import com.restaurante.exceptions.PedidoException;
import com.restaurante.exceptions.PlatoException;
import com.restaurante.repository.ClienteRepository;
import com.restaurante.repository.PlatoRepository;
import com.restaurante.repository.PedidoRepository;
import com.restaurante.service.ClienteService;
import com.restaurante.service.PlatoService;
import com.restaurante.service.PedidoService;
import com.restaurante.controller.RestauranteController;
import com.restaurante.view.RestauranteView;

public class Main {
    public static void main(String[] args) throws PedidoException, ClienteException, PlatoException {
        // Instanciar repositorios
        ClienteRepository clienteRepo = new ClienteRepository();
        PlatoRepository platoRepo = new PlatoRepository();
        PedidoRepository pedidoRepo = new PedidoRepository();

        // Instanciar servicios
        ClienteService clienteService = new ClienteService(clienteRepo);
        PlatoService platoService = new PlatoService(platoRepo);
        PedidoService pedidoService = new PedidoService(pedidoRepo);

        // Instanciar controlador
        RestauranteController controller = new RestauranteController(
                clienteService, platoService, pedidoService
        );

        // Instanciar vista y mostrar menú principal
        RestauranteView view = new RestauranteView(controller);
        view.mostrarMenuPrincipal();
    }
}