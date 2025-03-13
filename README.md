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

##  Key Feature:

### 💼 Business Need

As the number of orders grew, we needed to increase the efficiency of order processing to reduce delays and improve the user experience. We decided to implement a mechanism that allows handling multiple orders simultaneously.

### 🛠️ Technical Solution

To efficiently process orders asynchronously, we used ExecutorService with a thread pool, ensuring that multiple orders can be processed at the same time without blocking the system. To prevent race conditions in shared resources, we used synchronized where necessary.

### 📄 Code

```bash
public class MultiThreadedOrderProcessing {
    private static final int THREAD_COUNT = 4;
    private static final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) {
        startProcessingThreads();

        IncomingOrders();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Główny wątek został przerwany.", e);
        } finally {
            shutdownProcessing();
        }
    }

    private static void startProcessingThreads() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                while (!Thread.currentThread().isInterrupted() || !orderQueue.isEmpty()) {
                    try {
                        Order order = orderQueue.poll(2, TimeUnit.SECONDS);
                        if (order != null) {
                            System.out.println(Thread.currentThread().getName() + " przetwarza zamówienie: " + order.getOrderId());
                            executorService.execute(new OrderProcessor(order));
                        }
                    } catch (InterruptedException e) {
                        throw new ThreadInterruptedException(Thread.currentThread().getName() + " został przerwany.");
                    }
                }
            });
        }
    }

    private static void IncomingOrders() {
        new Thread(() -> {
            Customer customer = new Customer("Jan", "Kowalski", "jan@example.com", "123456789", "Ul. Przykładowa 1");
            Cart cart = new Cart();
            Product electronics = new Electronics(UUID.randomUUID(), "Samsung TV", new BigDecimal(5999), 20);

            cart.addProductToCart(electronics, 1);

            for (int i = 0; i < 5; i++) {
                try {
                    Order order = new Order(UUID.randomUUID(), customer, cart);
                    orderQueue.put(order);
                    System.out.println("Dodano nowe zamówienie: " + order.getOrderId());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new ThreadInterruptedException("Wątek dodający zamówienia został przerwany.");
                }
            }
        }).start();
    }

    private static void shutdownProcessing() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
```
### 💡 Summary
- Orders are processed in separate threads, preventing system slowdowns.

- ExecutorService ensures controlled concurrency.

- Users experience faster order processing without delays.

## 🛠 Technologies  
- Java (JDK 11+)  
- Java Collections & Streams  
- Java Concurrency (`ExecutorService`, `BlockingQueue`)  
- `LocalDateTime` for date handling  
- Files / Lists for data storage  

## 🏁 Running the Application  
 
```bash
Clone the repository:
git clone <REPOSITORY_URL>

Navigate to the project directory:
cd online-store

Compile the code:
javac -d bin src/*.java

Run the application:
java -cp bin Main
```

## 👨‍🎓 Authors
Project created by Jakub Wasilewski.
