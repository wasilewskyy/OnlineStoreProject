package org.project;

public class RAM {
    private int capacity;
    private Unit unit;

    public RAM(int capacity, Unit unit) {
        this.capacity = capacity;
        this.unit = unit;
    }

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
        return super.toString() +"ram=" + capacity + "" + unit + '}';
    }
}
