package com.restaurante.view;

import com.restaurante.controller.RestauranteController;
import com.restaurante.model.Cliente;
import com.restaurante.model.Pedido;
import com.restaurante.model.Plato;
import com.restaurante.exceptions.ClienteException;
import com.restaurante.exceptions.PedidoException;
import com.restaurante.exceptions.PlatoException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class RestauranteView {
    private final RestauranteController controller;
    private final Scanner scanner = new Scanner(System.in);

    public RestauranteView(RestauranteController controller) {
        this.controller = controller;
    }

    public void mostrarMenuPrincipal() throws ClienteException, PlatoException, PedidoException {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Platos");
            System.out.println("3. Gestionar Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1: menuClientes(); break;
                case 2: menuPlatos(); break;
                case 3: menuPedidos(); break;
                case 0: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuClientes() throws ClienteException {
        int opcion;
        do {
            System.out.println("\n-- Gestión de Clientes --");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar cliente por ID");
            System.out.println("4. Eliminar cliente");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1: registrarCliente(); break;
                case 2: listarClientes(); break;
                case 3: buscarCliente(); break;
                case 4: eliminarCliente(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuPlatos() throws PlatoException {
        int opcion;
        do {
            System.out.println("\n-- Gestión de Platos --");
            System.out.println("1. Agregar plato");
            System.out.println("2. Listar platos");
            System.out.println("3. Buscar plato por ID");
            System.out.println("4. Eliminar plato");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1: agregarPlato(); break;
                case 2: listarPlatos(); break;
                case 3: buscarPlato(); break;
                case 4: eliminarPlato(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuPedidos() throws PedidoException {
        int opcion;
        do {
            System.out.println("\n-- Gestión de Pedidos --");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Listar pedidos");
            System.out.println("3. Buscar pedido por ID");
            System.out.println("4. Cambiar estado de pedido");
            System.out.println("5. Eliminar pedido");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1: registrarPedido(); break;
                case 2: listarPedidos(); break;
                case 3: buscarPedido(); break;
                case 4: cambiarEstadoPedido(); break;
                case 5: eliminarPedido(); break;
                case 0: break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // --- Métodos para clientes ---
    private void registrarCliente() {
        try {
            System.out.print("ID cliente: ");
            int id = leerEntero();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Contacto: ");
            String contacto = scanner.nextLine();
            Cliente cliente = new Cliente(id, nombre, contacto);
            controller.registrarCliente(cliente);
            System.out.println("Cliente registrado correctamente.");
        } catch (ClienteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarClientes() throws ClienteException {
        Collection<Cliente> clientes = controller.listarClientes();
        System.out.println("Clientes registrados:");
        clientes.forEach(System.out::println);
    }

    private void buscarCliente() {
        try {
            System.out.print("ID cliente a buscar: ");
            int id = leerEntero();
            Cliente cliente = controller.buscarClientePorId(id);
            System.out.println(cliente);
        } catch (ClienteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarCliente() {
        try {
            System.out.print("ID cliente a eliminar: ");
            int id = leerEntero();
            controller.eliminarCliente(id);
            System.out.println("Cliente eliminado.");
        } catch (ClienteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Métodos para platos ---
    private void agregarPlato() {
        try {
            System.out.print("ID plato: ");
            int id = leerEntero();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Precio: ");
            double precio = leerDouble();
            System.out.print("Tipo (entrada, principal, postre...): ");
            String tipo = scanner.nextLine();
            controller.agregarPlato(id, nombre, precio, tipo);
            System.out.println("Plato agregado correctamente.");
        } catch (PlatoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarPlatos() throws PlatoException {
        Collection<Plato> platos = controller.listarPlatos();
        System.out.println("Platos en el menú:");
        platos.forEach(System.out::println);
    }

    private void buscarPlato() {
        try {
            System.out.print("ID plato a buscar: ");
            int id = leerEntero();
            Plato plato = controller.buscarPlatoPorId(id);
            System.out.println(plato);
        } catch (PlatoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarPlato() {
        try {
            System.out.print("ID plato a eliminar: ");
            int id = leerEntero();
            controller.eliminarPlato(id);
            System.out.println("Plato eliminado.");
        } catch (PlatoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Métodos para pedidos ---
    private void registrarPedido() {
        try {
            System.out.print("ID cliente: ");
            int idCliente = leerEntero();
            Cliente cliente = controller.buscarClientePorId(idCliente);

            List<Plato> platosPedido = new ArrayList<>();
            System.out.println("Ingrese los IDs de los platos del pedido (0 para terminar):");
            while (true) {
                System.out.print("ID plato: ");
                int idPlato = leerEntero();
                if (idPlato == 0) break;
                try {
                    Plato plato = controller.buscarPlatoPorId(idPlato);
                    platosPedido.add(plato);
                } catch (PlatoException e) {
                    System.out.println("Plato no encontrado. Intente de nuevo.");
                }
            }

            if (platosPedido.isEmpty()) {
                System.out.println("Debe registrar al menos un plato.");
                return;
            }

            controller.crearPedido(cliente, platosPedido);
            System.out.println("Pedido registrado correctamente.");
        } catch (ClienteException | PedidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarPedidos() throws PedidoException {
        Collection<Pedido> pedidos = controller.listarPedidos();
        System.out.println("Pedidos registrados:");
        pedidos.forEach(System.out::println);
    }

    private void buscarPedido() {
        try {
            System.out.print("ID pedido a buscar: ");
            int id = leerEntero();
            Pedido pedido = controller.buscarPedidoPorId(id);
            System.out.println(pedido);
        } catch (PedidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cambiarEstadoPedido() {
        try {
            System.out.print("ID pedido: ");
            int id = leerEntero();
            Pedido pedido = controller.buscarPedidoPorId(id);
            System.out.println("Estados disponibles:");
            for (Pedido.Estado estado : Pedido.Estado.values()) {
                System.out.println("- " + estado);
            }
            System.out.print("Nuevo estado: ");
            String estadoStr = scanner.nextLine().toUpperCase();
            Pedido.Estado nuevoEstado = Pedido.Estado.valueOf(estadoStr);
            controller.cambiarEstadoPedido(id, nuevoEstado);
            System.out.println("Estado del pedido actualizado.");
        } catch (PedidoException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarPedido() {
        try {
            System.out.print("ID pedido a eliminar: ");
            int id = leerEntero();
            controller.eliminarPedido(id);
            System.out.println("Pedido eliminado.");
        } catch (PedidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Métodos utilitarios para leer datos ---
    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }

    private double leerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
}