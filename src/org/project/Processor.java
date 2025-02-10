package org.project;

public class Processor {
    private String brand;
    private String model;
    private int cores;

    public Processor(String brand, String model, int cores) {
        this.brand = brand;
        this.model = model;
        this.cores = cores;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", cores=" + cores +
                '}';
    }
}

