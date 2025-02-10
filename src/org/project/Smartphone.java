package org.project;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Smartphone extends Product {
    private Color color;
    private int batteryCapacity;

    private List<String> accessories;

    public Smartphone(int id, String name, BigDecimal price, int quantityAvailable, Color color, int batteryCapacity) {
        super(id, name, price, quantityAvailable);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = new ArrayList<>();
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

    public List<String> getAccessories() {
        return accessories;
    }

    public void addAccessory(String accessory) {
        accessories.add(accessory);
    }

    public static void displayAvailableColors() {
        System.out.println("Dostępne kolory:");
        Color[] colors = Color.values();
        for (int i = 0; i < colors.length; i++) {
            System.out.println((i + 1) + ". " + colors[i]);
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", color=" + color + ", batteryCapacity=" + batteryCapacity;
    }
}
