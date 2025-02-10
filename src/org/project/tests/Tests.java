package org.project.tests;

import org.project.*;

import java.math.BigDecimal;

public class Tests {
    public static void main(String[] args) {
        testTask1();

    }


    private static void testTask1() {

        RAM ram1 = new RAM(8, Unit.GB);
        RAM ram2 = new RAM(16, Unit.GB);
        RAM ram3 = new RAM(32, Unit.GB);
        Product computer1 = new Computer(1, "MacBook Air", new BigDecimal(1200), 10, "M2", ram1);
        Product computer2 = new Computer(2, "MacBook Air", new BigDecimal(1200), 20, "M3", ram2);
        Product computer3 = new Computer(3, "MacBook Pro", new BigDecimal(1200), 30, "M4", ram3);
        Product electronics = new Electronics(1, "Samsung TV", new BigDecimal(5999), 20);
        Product smartphone1 = new Smartphone(1,"Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500);
        Product smartphone2 = new Smartphone(2,"Iphone 16 Pro", new BigDecimal(7999) , 40, Color.RED, 4500);

        System.out.println(computer1);
        System.out.println(computer2);
        System.out.println(computer3);
        System.out.println(electronics);
        System.out.println(smartphone1);
        System.out.println(smartphone2);


    }
}
