package org.project;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.*;

public class MultiThreadedOrderProcessing {
    private static final int THREAD_COUNT = 4;
    private static final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                while (true) {
                    try {
                        Order order = orderQueue.take();
                        new OrderProcessor(order).run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }

        IncomingOrders();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void IncomingOrders() {
        new Thread(() -> {
            Customer customer = new Customer("Jan", "Kowalski", "jan@example.com", "123456789", "Ul. Przykładowa 1");
            Cart cart = new Cart();
            Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20); // Przykładowy produkt
            cart.addProductToCart(electronics, 1);

            for (int i = 0; i < 20; i++) {
                try {
                    Order order = new Order(UUID.randomUUID(), customer, cart, cart.calculateTotalPrice());
                    orderQueue.put(order);
                    System.out.println("Dodano nowe zamówienie: " + order.getOrderId());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
