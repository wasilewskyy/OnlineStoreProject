package org.project;

public class RAM {
    private String producerName;
    private int capacity;
    private RamUnit ramUnit;

    public RAM(String producerName, int capacity, RamUnit ramUnit) {
        this.producerName = producerName;
        this.capacity = capacity;
        this.ramUnit = ramUnit;
    }

    public String getProducerName() {return producerName;}

    public void setProducerName(String producerName) {this.producerName = producerName;}

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public RamUnit getUnit() {
        return ramUnit;
    }

    public void setUnit(RamUnit ramUnit) {
        this.ramUnit = ramUnit;
    }

    @Override
    public String toString() {
        return "RAM{" +
                "name='" + producerName + '\'' +
                ", capacity=" + capacity + '\'' +
                "" + ramUnit +
                '}';
    }
}
