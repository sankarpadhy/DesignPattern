# Strategy Pattern

## Intent
Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

## Class Diagram
```
+---------------+       +-----------------+
|    Context    |       |    Strategy    |
+---------------+       +-----------------+
| - strategy    |------>| + algorithm()  |
| + execute()   |       +-----------------+
+---------------+              ▲
                              |
                    +-----------------+
                    |ConcreteStrategy |
                    +-----------------+
                    | + algorithm()   |
                    +-----------------+
```

## Components
1. **Strategy**: Declares interface common to all algorithms
2. **ConcreteStrategy**: Implements the algorithm using Strategy interface
3. **Context**: Maintains reference to Strategy object and may define interface to access data

## Implementation Details
- Encapsulates algorithm implementations in separate classes
- Makes algorithms interchangeable within that family
- Lets the algorithm vary independently from clients that use it
- Eliminates conditional statements

## When to Use
- Many related classes differ only in their behavior
- Need different variants of an algorithm
- Algorithm uses data that clients shouldn't know about
- A class defines many behaviors through conditionals

## Example Use Cases
1. Payment Processing Systems
2. Sorting Algorithms
3. Compression Algorithms
4. Route Navigation Systems

## Code Example
```java
// Strategy Interface
public interface PaymentStrategy {
    void pay(int amount);
}

// Concrete Strategies
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;
    
    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with credit card");
    }
}

public class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using PayPal");
    }
}

// Context
public class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}
```

## Usage Example
```java
ShoppingCart cart = new ShoppingCart();

// Pay with Credit Card
cart.setPaymentStrategy(new CreditCardPayment("1234-5678", "John Doe"));
cart.checkout(100);

// Pay with PayPal
cart.setPaymentStrategy(new PayPalPayment("john@example.com"));
cart.checkout(200);
```

## Advantages
1. Family of algorithms can be defined as a class hierarchy
2. Can switch algorithms at runtime
3. Eliminates conditional statements
4. Easier to extend and maintain
5. Promotes code reuse

## Disadvantages
1. Increased number of objects in the system
2. Clients must be aware of different strategies
3. Communication overhead between strategy and context
4. Increased complexity if only few algorithms
