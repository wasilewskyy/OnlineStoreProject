package org.project;

public class Processor {
    private String brand;
    private String model;
    private int cores;
    private CoresUnit coresUnit;

    public Processor(String brand, String model, int cores, CoresUnit coresUnit) {
        this.brand = brand;
        this.model = model;
        this.cores = cores;
        this.coresUnit = coresUnit;
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

    public CoresUnit getCoresUnit() {return coresUnit;}

    public void setCoresUnit(CoresUnit coresUnit) {this.coresUnit = coresUnit;}

    @Override
    public String toString() {
        return "Processor{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", cores=" + cores + '\'' +
                " " + coresUnit +
                '}';
    }
}

