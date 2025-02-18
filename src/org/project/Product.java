package org.project;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {
    public UUID id;
    private String name;
    private BigDecimal price;
    private int quantity;

    public Product( String name, BigDecimal price, int quantity) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {this.name = name;}

    public void setPrice(BigDecimal price) {this.price = price;}

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity;
    }
}
