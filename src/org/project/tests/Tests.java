package org.project.tests;

import org.project.*;
import org.project.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

public class Tests {
    public static void main(String[] args) {
        creatingAndCheckingCorrectDisplayOfAllProducts();
        testingProductManager();
        testingTheUseOfTheShoppingCart();
//        creatingAndCheckingCorrectDisplayOfAllProducts();
//        testingProductManager();
//        testingTheUseOfTheShoppingCart();
        testingOrderSavingToTxt();
        testingTheUseOfOrderProcessor();
        testingTheUseOfOrderProcessor();
        testOrderTimeUpdateDuringProcessing();
    }

    private static void creatingAndCheckingCorrectDisplayOfAllProducts() {
        // Tworzenie obiektów klasy RAM
        RAM ram1 = new RAM("HyperX", 8, RamUnit.GB);
        RAM ram2 = new RAM("Corsair", 16, RamUnit.GB);
        RAM ram3 = new RAM("Kingston", 32, RamUnit.GB);

        // Tworzenie akcesoriów
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Accessory accessory2 = new Accessory("Etui", new BigDecimal(99), "Iphone 16 Pro", "Plastikowe");

        // Tworzenie procesorów
        Processor processor1 = new Processor("Intel", "i7", 12, CoresUnit.GHz);
        Processor processor2 = new Processor("Intel", "i8", 12, CoresUnit.GHz);
        Processor processor3 = new Processor("Intel", "i9", 12, CoresUnit.GHz);

        // Tworzenie komputerów
        Product computer1 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 10, processor1, ram1);
        Product computer2 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 20, processor2, ram2);
        Product computer3 = new Computer(UUID.randomUUID(), "MacBook Pro", new BigDecimal(1200), 30, processor3, ram3);

        // Tworzenie obiektu klasy Electronics
        Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

        // Tworzenie smartfonów
        Product smartphone1 = new Smartphone(UUID.randomUUID(), "Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1);
        Product smartphone2 = new Smartphone(UUID.randomUUID(), "Iphone 16 Pro", new BigDecimal(7999), 40, Color.RED, 4500, accessory2);

        System.out.println(computer1);
        System.out.println(computer2);
        System.out.println(computer3);
        System.out.println(electronics);
        System.out.println(smartphone1);
        System.out.println(smartphone2);

    }

    private static void testingProductManager() {
        ProductManager productManager = new ProductManager();

        // Tworzenie produktów
        RAM ram1 = new RAM("HyperX", 8, RamUnit.GB);
        Processor processor1 = new Processor("Intel", "i7", 12, CoresUnit.GHz);
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Product computer1 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 10, processor1, ram1);
        Product smartphone1 = new Smartphone(UUID.randomUUID(), "Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1);
        Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

        // Dodawanie produktów
        productManager.addProduct(computer1);
        productManager.addProduct(smartphone1);
        productManager.addProduct(electronics);

        // Wyświetlenie wszystkich produktów
        System.out.println("Lista produktów po dodaniu:");
        productManager.getAllProducts().forEach(System.out::println);

        // Tworzymy koszyk dla użytkownika
        UUID cartId = productManager.createCart();
        System.out.println("\nUtworzono nowy koszyk o ID: " + cartId);

        // Dodajemy produkt do koszyka
        productManager.addProductToCart(cartId, computer1.getId(), 3);
        productManager.addProductToCart(cartId, smartphone1.getId(), 5);
        productManager.addProductToCart(cartId, electronics.getId(), 5);

        // Wyświetlamy zawartość koszyka
        System.out.println("\nKoszyk po dodaniu produktów:");
        System.out.println(productManager.getCart(cartId));

        // Usuwamy część produktów z koszyka
        productManager.removeProductFromCart(cartId, smartphone1.getId(), 2);

        // Próbujemy dodać więcej produktów, niż mamy w magazynie
        try {
            productManager.addProductToCart(cartId, smartphone1.getId(), 20);
        } catch (IllegalArgumentException e) {
            System.out.println("\nBłąd: " + e.getMessage());
        }

        // Wyszukiwanie produktu po ID
        try {
            Product foundProduct = productManager.getProductById(computer1.getId());
            System.out.println("\nZnaleziono produkt: " + foundProduct);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Usunięcie produktu
        productManager.removeProduct(electronics.getId());
        System.out.println("\nLista produktów po usunięciu:");
        productManager.getAllProducts().forEach(System.out::println);

        // Próba pobrania nieistniejącego produktu
        try {
            productManager.getProductById(UUID.randomUUID());
        } catch (ProductNotFoundException e) {
            System.out.println("\nBłąd: " + e.getMessage());

        }

        // Usuwamy koszyk
        productManager.deleteCart(cartId);
        System.out.println("\nKoszyk został usunięty.");
    }

    private static void testingTheUseOfTheShoppingCart() {
        Cart cart = new Cart();

        // Tworzenie produktów
        RAM ram1 = new RAM("HyperX", 8, RamUnit.GB);
        Processor processor1 = new Processor("Intel", "i7", 12, CoresUnit.GHz);
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Product computer1 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 10, processor1, ram1);
        Product smartphone1 = new Smartphone(UUID.randomUUID(), "Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1);
        Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

        // Dodanie produktów do koszyka
        System.out.println("Dodawanie produktów do koszyka:");
        cart.addProductToCart(computer1, 1);
        cart.addProductToCart(smartphone1, 2);
        cart.addProductToCart(electronics, 3);
        cart.displayCartContents();

        // Usunięcie produktu z koszyka
        System.out.println("\nUsuwanie produktu z koszyka:");
        cart.removeProductFromCart(electronics.getId(), 1);
        cart.displayCartContents();

        // Obliczanie ceny całkowitej
        System.out.println("\nCałkowita cena koszyka: " + cart.calculateTotalPrice());

        // Finalizacja zamówienia
        System.out.println("\nFinalizacja zamówienia:");
        cart.checkout();
        cart.displayCartContents();

    }
    private static void testingTheUseOfOrderProcessor() {

        // Tworzenie koszyka
        Cart cart = new Cart();

        // Tworzenie komponentów i produktów
        RAM ram1 = new RAM("HyperX", 8, RamUnit.GB);
        Processor processor1 = new Processor("Intel", "i7", 12, CoresUnit.GHz);
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Product computer1 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 10, processor1, ram1);
        Product smartphone1 = new Smartphone(UUID.randomUUID(), "Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1);
        Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

        // Dodanie produktów do koszyka
        cart.addProductToCart(computer1, 1);
        cart.addProductToCart(smartphone1, 2);
        cart.addProductToCart(electronics, 3);

        // Tworzenie przykładowego klienta
        Customer customer = new Customer("Jan", "Kowalski",  "jan.kowalski@example.com", "123456789","ul. Przykładowa 12, Warszawa");

        // Tworzenie przykładowego zamówienia
        Order order = new Order(UUID.randomUUID(), customer, cart, cart.calculateTotalPrice());

        // Tworzenie OrderProcessor
        OrderProcessor processor = new OrderProcessor();

        // Testowanie klasy OrderProcessor
        processor.processOrder(order);
        System.out.println();
        processor.generateInvoice(order);

    }
    private static void testingShoppingCLI() {
        ShoppingCLI shoppingCLI = new ShoppingCLI();
        shoppingCLI.startShopCLI();
    }
    private static void testOrderTimeUpdateDuringProcessing() {
        Customer customer = new Customer("Jan", "Kowalski",  "jan.kowalski@example.com", "123456789","ul. Przykładowa 12, Warszawa");
        Cart cart = new Cart();

        RAM ram1 = new RAM("HyperX", 8, RamUnit.GB);
        Processor processor1 = new Processor("Intel", "i7", 12, CoresUnit.GHz);
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Product computer1 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 10, processor1, ram1);
        Product smartphone1 = new Smartphone(UUID.randomUUID(), "Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1);
        Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

        cart.addProductToCart(computer1, 1);
        cart.addProductToCart(smartphone1, 2);
        cart.addProductToCart(electronics, 3);

        Order order = new Order(UUID.randomUUID(), customer, cart, cart.calculateTotalPrice());

        LocalDateTime beforeProcessing = order.getOrderTime();

        OrderProcessor processor = new OrderProcessor();
        processor.processOrder(order);

        LocalDateTime afterProcessing = order.getOrderTime();

        System.out.println("Before: " + beforeProcessing);
        System.out.println("After: " + afterProcessing);
    }
    private static void testingTheUseOfOrderProcessor() {

        // Tworzenie koszyka
        Cart cart = new Cart();

        // Tworzenie komponentów i produktów
        RAM ram1 = new RAM("HyperX", 8, RamUnit.GB);
        Processor processor1 = new Processor("Intel", "i7", 12, CoresUnit.GHz);
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Product computer1 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 10, processor1, ram1);
        Product smartphone1 = new Smartphone(UUID.randomUUID(), "Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1);
        Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

        // Dodanie produktów do koszyka
        cart.addProductToCart(computer1, 1);
        cart.addProductToCart(smartphone1, 2);
        cart.addProductToCart(electronics, 3);

        // Tworzenie przykładowego klienta
        Customer customer = new Customer("Jan", "Kowalski",  "jan.kowalski@example.com", "123456789","ul. Przykładowa 12, Warszawa");

        // Tworzenie przykładowego zamówienia
        Order order = new Order(UUID.randomUUID(), customer, cart, cart.calculateTotalPrice());

        // Tworzenie OrderProcessor
        OrderProcessor processor = new OrderProcessor();

        // Testowanie klasy OrderProcessor
        processor.processOrder(order);
        System.out.println();
        processor.generateInvoice(order);

    }
    private static void testingOrderSavingToTxt() {

        // Tworzenie koszyka
        Cart cart = new Cart();

        // Tworzenie komponentów i produktów
        RAM ram1 = new RAM("HyperX", 8, RamUnit.GB);
        Processor processor1 = new Processor("Intel", "i7", 12, CoresUnit.GHz);
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Product computer1 = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 10, processor1, ram1);
        Product smartphone1 = new Smartphone(UUID.randomUUID(), "Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1);
        Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

        // Dodanie produktów do koszyka
        cart.addProductToCart(computer1, 1);
        cart.addProductToCart(smartphone1, 2);
        cart.addProductToCart(electronics, 3);

        // Tworzenie przykładowego klienta
        Customer customer = new Customer("Jan", "Kowalski", "jankowalski@example.com", "123456789", "ul. Polna 12, Warszawa");

        // Tworzenie przykładowego zamówienia
        Order order = new Order(UUID.randomUUID(), customer, cart, cart.calculateTotalPrice());

        // Tworzymy listę zamówień
        List<Order> orders = Arrays.asList(order);

        // Zapisanie zamówień do pliku
        SaveOrders.saveOrdersToTxtFile(orders);
    }
}