package org.project;

import java.math.BigDecimal;
import java.util.UUID;

public class Computer extends Product {

    private Processor processor;
    private RAM ram;

    public Computer(UUID id, String name, BigDecimal price, int quantity, Processor processor, RAM ram) {
        super(name, price, quantity);
        this.id = UUID.randomUUID();
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
    protected Product clone() {
        return new Computer(this.id, this.getName(), this.getPrice(), this.getQuantity(), this.getProcessor(), this.getRam());
    }


    @Override
    public String toString() {
        return super.toString() + ", processor=" + processor + ", ram=" + ram;
    }
}
