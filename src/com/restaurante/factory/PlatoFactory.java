package com.restaurante.factory;

import com.restaurante.model.Plato;

public class PlatoFactory {
    public static Plato crearPlato(int id, String nombre, String tipo, double precio) {
        return new Plato(id, nombre, tipo, precio);
    }
}
