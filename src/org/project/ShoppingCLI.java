package org.project;

import org.project.exception.OrderProcessingException;
import org.project.exception.ProductNotAvailableException;
import java.math.BigDecimal;
import java.util.*;

public class ShoppingCLI implements CommandLine {
    private final Scanner scanner = new Scanner(System.in);
    private final Cart cart = new Cart();
    private final List<Product> availableProducts = new ArrayList<>();

    public ShoppingCLI() {
        RAM ram1 = new RAM("HyperX", 8, RamUnit.GB);
        Processor processor1 = new Processor("Intel", "i7", 12, CoresUnit.GHz);
        Accessory accessory1 = new Accessory("Etui", new BigDecimal(29), "Iphone 14 Pro", "Skórzane");
        availableProducts.add(new Computer(UUID.randomUUID(), "MacBook Air", new BigDecimal(1200), 10, processor1, ram1));
        availableProducts.add(new Smartphone(UUID.randomUUID(), "Iphone 14 Pro", new BigDecimal(4999), 50, Color.PINK, 2500, accessory1));
        availableProducts.add(new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20));
    }

    @Override
    public void startShopCLI() {
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
                case 1 -> viewProducts();
                case 2 -> addProductToCart();
                case 3 -> removeProductFromCart();
                case 4 -> viewCartContents();
                case 5 -> checkout();
                case 6 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @Override
    public void viewProducts() {
        System.out.println("Available products:");
        for (int i = 0; i < availableProducts.size(); i++) {
            System.out.println((i + 1) + ". " + availableProducts.get(i));
        }
    }

    @Override
    public void addProductToCart() {
        System.out.print("Enter product number to add to cart: ");
        int productNumberToAdd = scanner.nextInt();
        if (productNumberToAdd >= 1 && productNumberToAdd <= availableProducts.size()) {
            System.out.print("Enter quantity: ");
            int quantityToAdd = scanner.nextInt();
            Product productToAdd = availableProducts.get(productNumberToAdd - 1);
            if (quantityToAdd > productToAdd.getQuantity()) {
                throw new ProductNotAvailableException("Not enough stock for " + productToAdd.getName());
            }
            cart.addProductToCart(productToAdd, quantityToAdd);
            System.out.println("Product added to cart.");
        } else {
            System.out.println("Invalid product number.");
        }
    }

    @Override
    public void removeProductFromCart() {
        System.out.print("Enter product number to remove from cart: ");
        int productNumberToRemove = scanner.nextInt();
        if (productNumberToRemove >= 1 && productNumberToRemove <= availableProducts.size()) {
            System.out.print("Enter quantity to remove: ");
            int quantityToRemove = scanner.nextInt();
            cart.removeProductFromCart(availableProducts.get(productNumberToRemove - 1).getId(), quantityToRemove);
            System.out.println("Product removed from cart.");
        } else {
            System.out.println("Invalid product number.");
        }
    }

    @Override
    public void viewCartContents() {
        cart.displayCartContents();
    }

    @Override
    public void checkout() {
        try {
            cart.checkout();
            System.out.println("Checkout completed successfully.");
        } catch (OrderProcessingException e) {
            System.out.println("Error during checkout: " + e.getMessage());
        }
    }
}
