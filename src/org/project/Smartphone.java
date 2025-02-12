package org.project;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Smartphone extends Product {
    private Color color;
    private int batteryCapacity;
    private Accessories accessories;

    public Smartphone(int id, String name, BigDecimal price, int quantityAvailable, Color color, int batteryCapacity, Accessories accessories) {
        super(id, name, price, quantityAvailable);
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
