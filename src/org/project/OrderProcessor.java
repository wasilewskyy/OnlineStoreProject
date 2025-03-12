package org.project;

public class OrderProcessor {
    public void processOrder(Order order) {
        order.updateOrderTime();
        StringBuilder sb = new StringBuilder();
        sb.append("Przetwarzanie zamówienia dla: ").append(order.getCustomer().getCustomerName()).append("\n");
        sb.append("Data zamówienia: ").append(order.getOrderTime()).append("\n");
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

    public void generateInvoice(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Generowanie faktury...\n");
        sb.append("Faktura dla zamówienia o numerze: ").append(order.getOrderId()).append("\n");
        sb.append("Data zamówienia: ").append(order.getOrderTime()).append("\n");
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
