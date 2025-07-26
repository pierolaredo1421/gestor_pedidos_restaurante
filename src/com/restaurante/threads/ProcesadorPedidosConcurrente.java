package com.restaurante.threads;

import com.restaurante.model.Pedido;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ProcesadorPedidosConcurrente {
    public final int tiempoPreparationPorPlato;
    private final ExecutorService pool;

    public ProcesadorPedidosConcurrente(int numeroCocineros, int tiempoPreparationPorPlato) {
        this.pool = Executors.newFixedThreadPool(numeroCocineros);
        this.tiempoPreparationPorPlato = tiempoPreparationPorPlato;
    }

    public void procesarPedidos(List<Pedido> pedidos, Consumer<Pedido> callback) {
        for (Pedido pedido : pedidos) {
            pool.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep((long) pedido.getPlatos().size() * tiempoPreparationPorPlato);
                    pedido.setEstado(Pedido.Estado.PREPARADO);
                    // notifica que el pedido está listo, recibe un valor y despúes ago algo con él, como notificar o modificar
                    callback.accept(pedido);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    public void apagar() {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
