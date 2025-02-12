package org.project.tests;

import org.project.*;

import java.math.BigDecimal;

public class Tests {
    public static void main(String[] args) {
        creatingAndCheckingCorrectDisplayOfAllProducts();
    }

    private static void creatingAndCheckingCorrectDisplayOfAllProducts() {
        RAM ram1 = new RAM("HyperX",8, Unit.GB);
        RAM ram2 = new RAM("Corsair",16, Unit.GB);
        RAM ram3 = new RAM("Kingston",32, Unit.GB);
        Accessories accessories1 = new Accessories("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Accessories accessories2 = new Accessories("Etui", new BigDecimal(99), "Iphone 16 Pro", "Plastikowe");
        Processor processor1 = new Processor("Intel", "i7", 12);
        Processor processor2 = new Processor("Intel", "i8", 12);
        Processor processor3 = new Processor("Intel", "i9", 12);
        Product computer1 = new Computer(1, "MacBook Air", new BigDecimal(1200), 10, processor1, ram1);
        Product computer2 = new Computer(2, "MacBook Air", new BigDecimal(1200), 20, processor2, ram2);
        Product computer3 = new Computer(3, "MacBook Pro", new BigDecimal(1200), 30, processor3, ram3);
        Product electronics = new Electronics(1, "Samsung TV", new BigDecimal(5999), 20);
        Product smartphone1 = new Smartphone(1,"Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessories1);
        Product smartphone2 = new Smartphone(2,"Iphone 16 Pro", new BigDecimal(7999) , 40, Color.RED, 4500, accessories2);
        System.out.println(computer1);
        System.out.println(computer2);
        System.out.println(computer3);
        System.out.println(electronics);
        System.out.println(smartphone1);
        System.out.println(smartphone2);
    }
}