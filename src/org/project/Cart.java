package org.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart extends ProductManager {
    private List<Product> cartItems;

    public Cart() {
        super();
        this.cartItems = new ArrayList<>();
    }

    public void addProduct(int productId) {
        Product product = getProductById(productId);
        if (product != null && product.getQuantity() > 0) {
            cartItems.add(product);
            product.setQuantity(product.getQuantity() - 1);
        }
    }

    public void removeProduct(int productId) {
        Product productToRemove = null;
        for (Product product : cartItems) {
            if (product.getId() == productId) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            productToRemove.setQuantity(productToRemove.getQuantity() + 1);
            cartItems.remove(productToRemove);
        }
    }

    public BigDecimal getTotalPrice() {
        return cartItems.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Koszyk jest pusty.");
        } else {
            System.out.println("Aktualna zawartość koszyka: ");
            cartItems.forEach(System.out::println);
        }
    }

    public void placeOrder() {
        if (cartItems.isEmpty()) {
            System.out.println("Nie można złożyć zamówienia, koszyk jest pusty.");
        } else {
            System.out.println("Zamówienie zostało złożone:");
            viewCart();
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
