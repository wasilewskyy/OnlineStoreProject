package org.project;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID orderId;
    private Customer customer;
    private Cart cart;
    private List<Product> products;
    private BigDecimal totalPrice;
    private ZonedDateTime orderTime;
    private BigDecimal discountAmount = BigDecimal.ZERO;

    public Order(UUID orderId, Customer customer, Cart cart) {
        this.orderId = orderId;
        this.customer = customer;
        this.cart = cart;
        this.totalPrice = cart.calculateTotalPrice();
        this.orderTime = ZonedDateTime.now();
    }

    public ZonedDateTime getOrderTime() {
        return orderTime;
    }

    public String orderTime() {
        ZoneId clientZone = ZoneId.systemDefault();
        ZonedDateTime orderTime = ZonedDateTime.now(clientZone);
        System.out.println("Order time (" + clientZone + "): " + orderTime);
        this.orderTime = orderTime;
        return "Data zamówienia (" + clientZone + "): " + orderTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void applyPercentageDiscount(BigDecimal percentage) {
        if (percentage.compareTo(BigDecimal.ZERO) <= 0 || percentage.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("Invalid discount percentage. Must be between 0 and 100.");
        }
        BigDecimal discount = totalPrice.multiply(percentage).divide(new BigDecimal("100"));
        totalPrice = totalPrice.subtract(discount);
    }

    public void applyFixedDiscount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || amount.compareTo(totalPrice) > 0) {
            throw new IllegalArgumentException("Invalid discount amount. Must be greater than 0 and less than total price.");
        }
        totalPrice = totalPrice.subtract(amount);
    }

    public void applyPromotions() {
        if (totalPrice.compareTo(new BigDecimal("500")) > 0) {
            applyPercentageDiscount(new BigDecimal("10"));
        }
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderID=" + orderId +
                ", customer data=" + customer +
                ", cart items=" + cart +
                ", total amount=" + cart.calculateTotalPrice() +
                ", discount applied=" + discountAmount +
                '}';
    }
}