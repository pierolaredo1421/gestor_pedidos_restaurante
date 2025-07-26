package com.restaurante.repository;

import com.restaurante.model.Plato;

import java.util.*;

public class PlatoRepository {
    private final Map<Integer, Plato> platos = new HashMap<>();

    public void agregarPlato(Plato plato) {
        platos.put(plato.getId(), plato);
    }

    public Optional<Plato> buscarPlatoPorId(int id) {
        return Optional.ofNullable(platos.get(id));
    }

    public boolean eliminarPlato(int id) {
        return platos.remove(id) != null;
    }

    public Collection<Plato> listarPlatos(){
        return platos.values();
    }
}
