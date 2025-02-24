package org.project;

import org.project.exception.ProductNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductManager {
    private List<Product> products;
    private Cart cart;

    public ProductManager() {
        this.products = new ArrayList<>();
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

    public void addProductToCart(UUID productId, int quantity) {
        Product product = getProductById(productId);
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("There is not enough product in stock.");
        }
        cart.addProductToCart(product.clone());
        product.decreaseQuantity(quantity);
    }

    public void removeProductFromCart(UUID productId, int quantity) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be higher than zero.");
        }

        Product product = getProductById(productId);
        if (product == null) {
            throw new IllegalStateException("Product not found in the system.");
        }
        cart.removeProductFromCart(product.clone());
        product.increaseQuantity(quantity);
    }
}