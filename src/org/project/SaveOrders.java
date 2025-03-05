package org.project;

import org.project.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SaveOrders {
    private static final String TXT_FILE_PATH = "src/orders.txt";
    public static void saveOrdersToTxtFile(List<Order> orders) {
        try (FileWriter writer = new FileWriter(TXT_FILE_PATH)) {
            String ordersText = orders.stream()
                    .map(SaveOrders::formatOrder)
                    .collect(Collectors.joining("\n"));

            writer.write(ordersText);
            System.out.println("Zamówienie zostało zapisane do pliku " + TXT_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas zapisywania zamówienia: " + e.getMessage());
        }
    }
    private static String formatOrder(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID zamówienia: ").append(order.getOrderId()).append("\n");
        sb.append("Zamówione produkty: ").append(order.getCart()).append("\n");
        sb.append("Wartość zamówienia: ").append(order.getTotalPrice()).append("\n");
        sb.append("Imię klienta: ").append(order.getCustomer().getCustomerName()).append("\n");
        sb.append("Nazwisko: ").append(order.getCustomer().getCustomerLastName()).append("\n");
        sb.append("Adres Email klienta: ").append(order.getCustomer().getEmail()).append("\n");
        sb.append("Numer telefonu klienta: ").append(order.getCustomer().getPhoneNumber()).append("\n");
        sb.append("Adres klienta: ").append(order.getCustomer().getAddress()).append("\n");
        sb.append("Zamówienie złożono: ").append(order.getOrderTime()).append("\n");
        sb.append("\n");
        return sb.toString();
    }
}
