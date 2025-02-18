package org.project;


import java.math.BigDecimal;
import java.util.UUID;

public class Smartphone extends Product {
    private Color color;
    private int batteryCapacity;
    private Accessory accessory;

    public Smartphone(UUID id, String name, BigDecimal price, int quantityAvailable, Color color, int batteryCapacity, Accessory accessory) {
        super(name, price, quantityAvailable);
        this.id = UUID.randomUUID();
        this.color = color;
        this.batteryCapacity = batteryCapacity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String toString() {
        return super.toString() + ", color=" + color + ", batteryCapacity=" + batteryCapacity;
    }
}
