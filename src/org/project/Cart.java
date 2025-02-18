package org.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        if (product != null && product.getQuantity() > 0) {
            cartItems.add(product);
            product.decreaseQuantity(1);
        }
    }

    public void removeProductFromCart(Product product) {
        if (cartItems.contains(product)) {
            product.increaseQuantity(1);
            cartItems.remove(product);
        }
    }

    public BigDecimal calculateTotalPrice() {
        return cartItems.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void displayCartContents() {
        if (cartItems.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            System.out.println("Current cart contents:");
            cartItems.forEach(System.out::println);
        }
    }

    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Cannot place an order, the cart is empty.");
        } else {
            System.out.println("Order has been placed:");
            displayCartContents();
            cartItems.clear();
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                '}';
    }
}