package org.project;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private UUID orderId;
    private Customer customer;
    private Cart cart;
    private BigDecimal totalPrice;
    private LocalDateTime orderTime;
    public Order(UUID orderId, Customer customer, Cart cart, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.customer = customer;
        this.cart = cart;
        this.totalPrice = totalPrice;
        this.orderTime = LocalDateTime.now();
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
                ", products=" + cart +
                ", total amount=" + totalPrice +
                '}';
    }
}
