package org.project.tests;

import org.project.*;

import java.math.BigDecimal;
import java.util.UUID;

public class Tests {
    public static void main(String[] args) {
        creatingAndCheckingCorrectDisplayOfAllProducts();
    }

    private static void creatingAndCheckingCorrectDisplayOfAllProducts() {
        ProductManager productManager = new ProductManager();
        RAM ram1 = new RAM("HyperX",8, RamUnit.GB);
        RAM ram2 = new RAM("Corsair",16, RamUnit.GB);
        RAM ram3 = new RAM("Kingston",32, RamUnit.GB);
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Accessory accessory2 = new Accessory("Etui", new BigDecimal(99), "Iphone 16 Pro", "Plastikowe");
        Processor processor1 = new Processor("Intel", "i7", 12,CoresUnit.GHz);
        Processor processor2 = new Processor("Intel", "i8", 12, CoresUnit.GHz);
        Processor processor3 = new Processor("Intel", "i9", 12, CoresUnit.GHz);
        Product computer1 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200),10, processor1, ram1);
        Product computer2 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200),20, processor2, ram2);
        Product computer3 = new Computer(UUID.randomUUID(), "MacBook Pro", new BigDecimal(1200),30, processor3, ram3);
        Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);
        Product smartphone1 = new Smartphone(UUID.randomUUID(),"Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1);
        Product smartphone2 = new Smartphone(UUID.randomUUID(),"Iphone 16 Pro", new BigDecimal(7999) , 40, Color.RED, 4500, accessory2);
        System.out.println(computer1);
        System.out.println(computer2);
        System.out.println(computer3);
        System.out.println(electronics);
        System.out.println(smartphone1);
        System.out.println(smartphone2);

        //Testy ProductManager
        productManager.addProduct(computer1);
        UUID idToRemove = computer1.getId();
        productManager.removeProduct(idToRemove);
        UUID idToAdd = computer2.getId();
        productManager.updateProduct(idToAdd,"Macbook Air", new BigDecimal(1200),60);
        System.out.println(productManager.getAllProducts());
    }
}