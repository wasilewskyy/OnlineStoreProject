package org.project;

import java.time.LocalDateTime;

public class OrderProcessor implements Runnable {
    private final Order order;

    public OrderProcessor(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        processOrder(order);
        generateInvoice(order);
        System.out.println("Order " + order.getOrderId() + " processed");
    }

    public void processOrder(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Przetwarzanie zamówienia dla: ").append(order.getCustomer().getCustomerName()).append("\n");
        sb.append("Produkty w zamówieniu:\n");
        for (Product product : order.getProducts()) {
            sb.append("- ").append(product.getName()).append(" | Cena: ").append(product.getPrice()).append(" PLN\n");
        }
        sb.append("Łączna kwota: ").append(order.getTotalPrice()).append(" PLN\n");
        System.out.println(sb.toString());
    }
    public void generateInvoice(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Generowanie faktury...\n");
        sb.append("Faktura dla zamówienia o numerze: ").append(order.getOrderId()).append("\n");
        sb.append("Data zamówienia: ").append(LocalDateTime.now()).append("\n");
        sb.append("Klient: ").append(order.getCustomer().getCustomerName()).append(" ")
                .append(order.getCustomer().getCustomerLastName()).append("\n");
        sb.append("Numer telefonu klienta: ").append(order.getCustomer().getPhoneNumber()).append("\n");
        sb.append("Adres email: ").append(order.getCustomer().getEmail()).append("\n");
        sb.append("Adres dostawy: ").append(order.getCustomer().getAddress()).append("\n");
        sb.append("Produkty:\n");
        for (Product product : order.getProducts()) {
            sb.append("- ").append(product.getName()).append(" - ").append(product.getPrice()).append(" PLN\n");
        }
        sb.append("Łączna suma zamówienia: ").append(order.getTotalPrice()).append(" PLN\n");
        sb.append("Pomyślnie wygenerowano fakturę.\n");
        System.out.println(sb.toString());
    }
}
