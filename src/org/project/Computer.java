package org.project;

import java.math.BigDecimal;

public class Computer extends Product {

    private Processor processor;
    private RAM ram;

    public Computer(int id, String name, BigDecimal price, int quantity, Processor processor, RAM ram) {
        super(id, name, price, quantity);
        this.processor = processor;
        this.ram = ram;

    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public String toString() {
        return super.toString() + ", processor=" + processor + ", ram=" + ram;
    }
}
