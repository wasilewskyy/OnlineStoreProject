package org.project;

import org.project.exception.OrderProcessingException;
import org.project.exception.ProductNotAvailableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {
    private List<Product> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addProductToCart(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (product.getQuantity() < quantity || quantity <= 0) {
            throw new ProductNotAvailableException("Invalid quantity for product: " + product.getName());
        }

        Product cartProduct = findProductInCart(product.getId());

        if (cartProduct != null) {
            cartProduct.increaseQuantity(quantity);
        } else {
            addNewProductToCart(product, quantity);
        }
    }

    public void removeProductFromCart(UUID productId, int quantity) {
        Product cartProduct = findProductInCart(productId);
        if (cartProduct == null) {
            throw new ProductNotAvailableException("Product not found in cart.");
        }
        if (cartProduct.getQuantity() <= quantity) {
            cartItems.remove(cartProduct);
        } else {
            cartProduct.decreaseQuantity(quantity);
        }
    }

    private Product findProductInCart(UUID productId) {
        for (Product cartProduct : cartItems) {
            if (cartProduct.getId().equals(productId)) {
                return cartProduct;
            }
        }
        return null;
    }

    private void addNewProductToCart(Product product, int quantity) {
        Product clonedProduct = product.clone();
        clonedProduct.setQuantity(quantity);
        cartItems.add(clonedProduct);
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
            throw new OrderProcessingException("Cannot place an order, the cart is empty.");
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