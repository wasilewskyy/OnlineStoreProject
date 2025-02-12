package org.project;

public class RAM {
    private String producerName;
    private int capacity;
    private Unit unit;

    public RAM(String producerName, int capacity, Unit unit) {
        this.producerName = producerName;
        this.capacity = capacity;
        this.unit = unit;
    }

    public String getProducerName() {return producerName;}

    public void setProducerName(String producerName) {this.producerName = producerName;}

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "RAM{" +
                "name='" + producerName + '\'' +
                ", capacity=" + capacity + '\'' +
                "" + unit +
                '}';
    }
}
