package org.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductManager {
    private List<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(UUID id) {
        products.removeIf(product -> product.getId() == id);
    }

    public void updateProduct (UUID id, String name, BigDecimal price, int quantity) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setQuantity(quantity);
                break;
            }
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(UUID id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}