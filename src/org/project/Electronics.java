package org.project;

import java.math.BigDecimal;
import java.util.UUID;

public class Electronics extends Product {
    public Electronics(UUID id, String productName, BigDecimal price, int quantityAvailable) {
        super(productName, price, quantityAvailable);
    }
}

