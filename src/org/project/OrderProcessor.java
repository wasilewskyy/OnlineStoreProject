package org.project;

import java.time.format.DateTimeFormatter;

public class OrderProcessor implements Runnable {
    private final Order order;

    public OrderProcessor(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " przetwarza zamówienie: " + order.getOrderId());
        processOrder();
        generateInvoice();
        System.out.println("Order " + order.getOrderId() + " processed by " + Thread.currentThread().getName());
    }

    private void processOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("Przetwarzanie zamówienia dla: ").append(order.getCustomer().getCustomerName()).append("\n");
        sb.append(order.orderTime()).append("\n");
        sb.append("Produkty w zamówieniu:\n");
        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            sb.append("Brak produktów w zamówieniu.\n");
        } else {
            for (Product product : order.getProducts()) {
                sb.append("- ").append(product.getName()).append(" | Cena: ").append(product.getPrice()).append(" PLN\n");
            }
        }

        sb.append("Łączna kwota: ").append(order.getTotalPrice()).append(" PLN\n");
        System.out.println(sb);
    }

    private void generateInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("Generowanie faktury...\n");
        sb.append("Faktura dla zamówienia o numerze: ").append(order.getOrderId()).append("\n");
        sb.append(order.orderTime()).append("\n");
        Customer customer = order.getCustomer();
        sb.append("Klient: ").append(customer.getCustomerName()).append(" ")
                .append(customer.getCustomerLastName()).append("\n");
        sb.append("Numer telefonu: ").append(customer.getPhoneNumber()).append("\n");
        sb.append("Adres email: ").append(customer.getEmail()).append("\n");
        sb.append("Adres dostawy: ").append(customer.getAddress()).append("\n");
        sb.append("Produkty:\n");
        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            sb.append("Brak produktów.\n");
        } else {
            for (Product product : order.getProducts()) {
                sb.append("- ").append(product.getName()).append(" - ").append(product.getPrice()).append(" PLN\n");
            }
        }
        sb.append("Łączna suma zamówienia: ").append(order.getTotalPrice()).append(" PLN\n");
        sb.append("Faktura wygenerowana pomyślnie.\n");
        System.out.println(sb);
    }
}
