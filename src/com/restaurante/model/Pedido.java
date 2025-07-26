package com.restaurante.model;

import java.util.List;
import java.util.Objects;

public class Pedido {
    private final int id;
    private final Cliente cliente;
    private final List<Plato> platos;
    private double total;
    private Estado estado;

    public enum Estado {
        EN_PROCESO,
        PREPARADO,
        ENTREGADO;
    }

    public Pedido(int id, Cliente cliente, List<Plato> platos) {
        this.id = id;
        this.cliente = cliente;
        this.platos = platos;
        this.estado = Estado.EN_PROCESO;
        // hace que cada pedido tenga su propio precio total
        recalcularTotal();
    }

    // hace que cada pedido tenga su propio precio total por eso es void
    public void recalcularTotal(){
        this.total = platos.stream().mapToDouble(Plato::getprecio).sum();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id == pedido.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", platos=" + platos +
                ", total=" + total +
                ", estado=" + estado +
                '}';
    }
}
