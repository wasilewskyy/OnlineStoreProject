package org.project;

import org.project.exception.ProductNotAvailableException;
import org.project.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.*;

public class ProductManager {
    private List<Product> products;
    private Map<UUID, Cart> carts;

    public ProductManager() {
        this.products = new ArrayList<>();
        this.carts = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(UUID id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    public void updateProduct(UUID id, String name, BigDecimal price, int quantity) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                break;
            }
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(UUID id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " has not been found."));
    }

    public UUID createCart() {
        UUID cartId = UUID.randomUUID();
        carts.put(cartId, new Cart());
        return cartId;
    }

    private Cart getCartById(UUID cartId) {
        Cart cart = carts.get(cartId);
        if (cart == null) {
            throw new IllegalArgumentException("Cart with ID " + cartId + " not found.");
        }
        return cart;
    }

    public void addProductToCart(UUID cartId, UUID productId, int quantity) {
        Product product = getProductById(productId);
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("There is not enough product in stock.");
        }

        Cart cart = getCartById(cartId);
        cart.addProductToCart(product, quantity);
        product.decreaseQuantity(quantity);
    }

    public void removeProductFromCart(UUID cartId, UUID productId, int quantity) {
        Cart cart = getCartById(cartId);
        cart.removeProductFromCart(productId, quantity);

        Product product = getProductById(productId);
        product.increaseQuantity(quantity);
    }

    public Cart getCart(UUID cartId) {
        return getCartById(cartId);
    }

    public void deleteCart(UUID cartId) {
        carts.remove(cartId);
    }
}