package org.project;

import org.project.exception.OrderProcessingException;

public interface CommandLine {
    void startShopCLI() throws OrderProcessingException;
    void viewProducts();
    void addProductToCart();
    void removeProductFromCart();
    void viewCartContents();
    void checkout() throws OrderProcessingException;
}
