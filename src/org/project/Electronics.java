package org.project;

import java.math.BigDecimal;
import java.util.UUID;

public class Electronics extends Product {
    public Electronics(UUID id, String productName, BigDecimal price, int quantityAvailable) {
        super(productName, price, quantityAvailable);
    }

    @Override
    protected Product clone() {
        return new Electronics(this.getId(), this.getName(), this.getPrice(), this.getQuantity());
    }
}

