package org.project;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    public void updateProduct(int id, String name, double price, int quantity) {
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

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}