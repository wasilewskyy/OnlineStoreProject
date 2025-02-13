package org.project;

import java.math.BigDecimal;

public class Accessory {
    private String name;
    private BigDecimal price;
    private String compatibility;
    private String material;

    public Accessory(String name, BigDecimal price, String compatibility, String material) {
        this.name = name;
        this.compatibility = compatibility;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Accessory{" +
                "Name=" + name +
                ", price=" + price +
                ", compatibility=" + compatibility +
                ", material=" + material +
                "}";
    }
}
