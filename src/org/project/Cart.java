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
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (product.getQuantity() <= 0) {
            throw new IllegalArgumentException("No pieces of product available: " + product.getName());
        }

        for (Product cartProduct : cartItems) {
            if (cartProduct.getId().equals(product.getId())) {
                cartProduct.increaseQuantity(1);
                return;
            }
        }
        Product clonedProduct = product.clone();
        clonedProduct.setQuantity(1);
        cartItems.add(clonedProduct);
    }

    public void removeProductFromCart(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        for (Product cartProduct : cartItems) {
            if (cartProduct.getId().equals(product.getId())) {
                if (cartProduct.getQuantity() > 1) {
                    cartProduct.decreaseQuantity(1);
                } else {
                    cartItems.remove(cartProduct);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Product is not in the cart.");
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