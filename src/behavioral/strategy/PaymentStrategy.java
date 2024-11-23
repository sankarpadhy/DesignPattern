package behavioral.strategy;

/**
 * Strategy interface for different payment methods
 * 
 * The Strategy pattern defines a family of algorithms,
 * encapsulates each one, and makes them interchangeable.
 * It lets the algorithm vary independently from clients that use it.
 */
public interface PaymentStrategy {
    boolean pay(double amount);
    String getPaymentMethod();
}
