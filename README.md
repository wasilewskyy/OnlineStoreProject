# 🛒 Online Store Project  

## 📌 Project Description  
This project is a comprehensive online store system that enables product management, shopping cart handling, and order processing. The system supports different product types, allows order customization, and utilizes multithreading and asynchronous processing for efficient order handling.  

## 🚀 Features  

### 1. Product Management  
- Implementation of the `Product` class containing ID, name, price, and available quantity.  
- `ProductManager` allows adding, removing, updating, and browsing products.  
- Support for specific product types:  
  - **Computer**: Allows specification selection (processor, RAM, etc.).  
  - **Smartphone**: Allows choosing color, battery capacity, and accessories.  
  - **Electronics**: No additional configuration required.  

### 2. Shopping Cart Handling  
- Implementation of the `Cart` class for storing products added by the customer.  
- Functions for adding to the cart, viewing contents, and placing an order.  

### 3. Order Processing  
- `Order` class storing customer data, products, and total order value.  
- `OrderProcessor` for processing orders and generating invoices.  

### 4. User Interaction  
- Command-line interface for managing products, cart, and orders.  

### 5. Time Management & Data Persistence  
- Tracking order placement time using `LocalDateTime`.  
- Exception handling (e.g., out-of-stock products, order processing errors).  
- Saving orders to a file or list for data persistence.  

### 6. Multithreading & Asynchronous Processing  
- Multithreading for concurrent order processing.  
- Implementation of promotions and discounts.  
- Code documentation.  
- Use of asynchronous processing for handling orders.  
- Time zone management in date processing.  

## 🛠 Technologies  
- Java (JDK 11+)  
- Java Collections & Streams  
- Java Concurrency (`ExecutorService`, `BlockingQueue`)  
- `LocalDateTime` for date handling  
- Files / Lists for data storage  

## 🏁 Running the Application  

Clone the repository:  
```bash
git clone <REPOSITORY_URL>

Navigate to the project directory:

cd online-store

Compile the code:

javac -d bin src/*.java

Run the application:

java -cp bin Main

```bash

## 📄 Authors

Project created by Jakub Wasilewski.
