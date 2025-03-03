package org.project;

import java.math.BigDecimal;
import java.util.*;

public class ShoppingCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RAM ram1 = new RAM("HyperX", 8, RamUnit.GB);
        Processor processor1 = new Processor("Intel", "i7", 12, CoresUnit.GHz);
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        Product computer = new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 10, processor1, ram1);
        Product smartphone = new Smartphone(UUID.randomUUID(), "Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1);
        Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

        Product[] availableProducts = {computer, smartphone, electronics};

        Cart cart = new Cart();

        while (true) {
            System.out.println("\n1. View products");
            System.out.println("2. Add product to cart");
            System.out.println("3. Remove product from cart");
            System.out.println("4. View cart contents");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Available products:");
                    for (int i = 0; i < availableProducts.length; i++) {
                        System.out.println((i + 1) + ". " + availableProducts[i]);
                    }
                    break;

                case 2:
                    System.out.print("Enter product number to add to cart: ");
                    int productNumberToAdd = scanner.nextInt();
                    if (productNumberToAdd >= 1 && productNumberToAdd <= availableProducts.length) {
                        System.out.print("Enter quantity: ");
                        int quantityToAdd = scanner.nextInt();
                        try {
                            cart.addProductToCart(availableProducts[productNumberToAdd - 1], quantityToAdd);
                            System.out.println("Product added to cart.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid product number.");
                    }
                    break;

                case 3:
                    System.out.print("Enter product number to remove from cart: ");
                    int productNumberToRemove = scanner.nextInt();
                    if (productNumberToRemove >= 1 && productNumberToRemove <= availableProducts.length) {
                        System.out.print("Enter quantity to remove: ");
                        int quantityToRemove = scanner.nextInt();
                        try {
                            cart.removeProductFromCart(availableProducts[productNumberToRemove - 1].getId(), quantityToRemove);
                            System.out.println("Product removed from cart.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid product number.");
                    }
                    break;

                case 4:
                    cart.displayCartContents();
                    break;

                case 5:
                    cart.checkout();
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}