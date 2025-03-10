package org.project;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.*;


public class MultiThreadedOrderProcessing {
    private static final int THREAD_COUNT = 4;
    private static final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) {
        startProcessingThreads();

        IncomingOrders();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Główny wątek został przerwany.");
        } finally {
            shutdownProcessing();
        }
    }

    private static void startProcessingThreads() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                while (!Thread.currentThread().isInterrupted() || !orderQueue.isEmpty()) {
                    try {
                        Order order = orderQueue.poll(2, TimeUnit.SECONDS);
                        if (order != null) {
                            System.out.println(Thread.currentThread().getName() + " przetwarza zamówienie: " + order.getOrderId());
                            executorService.execute(new OrderProcessor(order));
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println(Thread.currentThread().getName() + " został przerwany.");
                        break;
                    }
                }
            });
        }
    }

    private static void IncomingOrders() {
        new Thread(() -> {
            Customer customer = new Customer("Jan", "Kowalski", "jan@example.com", "123456789", "Ul. Przykładowa 1");
            Cart cart = new Cart();
            Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

            cart.addProductToCart(electronics, 1);

            for (int i = 0; i < 5; i++) {
                try {
                    Order order = new Order(UUID.randomUUID(), customer, cart, cart.calculateTotalPrice());
                    orderQueue.put(order);
                    System.out.println("Dodano nowe zamówienie: " + order.getOrderId());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Wątek dodający zamówienia został przerwany.");
                    break;
                }
            }
        }).start();
    }

    private static void shutdownProcessing() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}