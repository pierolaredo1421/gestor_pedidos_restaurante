package com.restaurante;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class prueba {
    ExecutorService ex = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        prueba p = new prueba();

        // Lanzamos interrupción al hilo main después de 2 segundos
        Thread interrupcionExterna = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("🛑 Interrupción externa al hilo principal.");
                Thread mainThread = Thread.currentThread(); // incorrecto: este es el hilo del runnable
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        interrupcionExterna.start(); // ← este va a dormir 2 segundos pero no interrumpe main aún

        // Capturamos el hilo main para poder interrumpirlo
        Thread mainThread = Thread.currentThread();

        // Este otro hilo sí interrumpe al main después de 2 segundos
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("🛑 Interrupción externa real al hilo main.");
                mainThread.interrupt(); // ← interrumpe el hilo principal
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        p.tarea();   // ejecuta una tarea larga
        p.off();     // espera 5 segundos (pero será interrumpido a los 2)
    }

    public void tarea() {
        ex.submit(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Procesando paso " + i + "...");
                    TimeUnit.SECONDS.sleep(1); // tarea larga

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("⚠️ Hilo del pool interrumpido. Cancelando tarea...");
                        return;
                    }
                }
                System.out.println("✅ Tarea del pool completada.");
            } catch (InterruptedException e) {
                System.out.println("❗ Tarea del pool detenida mientras dormía (sleep).");
                Thread.currentThread().interrupt(); // restaurar interrupción
            }
        });
    }

    public void off() {
        ex.shutdown(); // primero apagamos
        try {
            System.out.println("⏳ Esperando a que los hilos terminen...");
            if (!ex.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("⏰ Tiempo agotado. Forzando apagado...");
                ex.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("⚠️ El hilo que llamó a off() fue interrumpido desde fuera.");
            ex.shutdownNow();
            Thread.currentThread().interrupt(); // restaurar interrupción
        }

        if (Thread.currentThread().isInterrupted()) {
            System.out.println("🚨 El hilo principal fue interrumpido (bandera restaurada).");
        } else {
            System.out.println("✅ El hilo principal terminó sin interrupción.");
        }
    }
}
