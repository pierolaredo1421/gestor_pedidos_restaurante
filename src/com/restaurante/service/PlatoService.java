package com.restaurante.service;

import com.restaurante.exceptions.PlatoException;
import com.restaurante.factory.PlatoFactory;
import com.restaurante.model.Plato;
import com.restaurante.repository.PlatoRepository;

import java.util.Collection;

public class PlatoService {
    private final PlatoRepository platoRepository;

    public PlatoService(PlatoRepository platoRepository) {
        this.platoRepository = platoRepository;
    }

    public void agregarPlato(int id, String nombre, double precio, String tipo) throws PlatoException {
        if (platoRepository.buscarPlatoPorId(id).isPresent()) {
            throw new PlatoException("El plato con id " + id + " ya existe");
        }
        if (precio <= 0) {
            throw new PlatoException("El precio del plato tiene que ser mayor a s/0.0");
        }
        Plato plato = PlatoFactory.crearPlato(id, nombre, tipo, precio); // uso del factory
        platoRepository.agregarPlato(plato); // uso del repository
    }

    public Plato buscarPlatoPorId(int id) throws PlatoException {
        return platoRepository.buscarPlatoPorId(id).orElseThrow(() -> new PlatoException("El plato no encontrado con id: " + id));
    }

    public void eliminarPlato(int id) throws PlatoException {
        if (!platoRepository.eliminarPlato(id)) {
            throw new PlatoException("El plato no encontrado con id: " + id);
        }
    }

    public Collection<Plato> listarPlatos() throws PlatoException {
        return platoRepository.listarPlatos();
    }
}
