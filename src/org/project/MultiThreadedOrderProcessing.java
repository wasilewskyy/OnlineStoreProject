package org.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MultiThreadedOrderProcessing {
    public static void main(String[] args) {
        Cart cart = new Cart();

        RAM ram1 = new RAM("HyperX", 8, RamUnit.GB);
        Processor processor1 = new Processor("Intel", "i7", 12, CoresUnit.GHz);
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Product computer1 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 10, processor1, ram1);
        Product smartphone1 = new Smartphone(UUID.randomUUID(), "Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1);
        Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

        cart.addProductToCart(computer1, 1);
        cart.addProductToCart(smartphone1, 2);
        cart.addProductToCart(electronics, 3);

        Customer customer = new Customer("Jan", "Kowalski", "jankowalski@example.com", "123456789", "ul. Polna 12, Warszawa");

        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 10; i++) { // ordery musza tu przychodzic
            Order order = new Order(UUID.randomUUID(), customer, cart, cart.calculateTotalPrice());
            Thread thread = new OrderProcessor(order);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Wszystkie zamówienia zostały przetworzone.");
    }
}

