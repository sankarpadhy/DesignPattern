package behavioral.strategy;

/**
 * Strategy interface for the Payment Strategy Pattern
 * Defines the contract for different payment methods
 * 
 * Key aspects of the Strategy Pattern demonstrated here:
 * 1. Defines a family of algorithms (payment methods)
 * 2. Encapsulates each algorithm
 * 3. Makes them interchangeable at runtime
 */
public interface PaymentStrategy {
    /**
     * Processes a payment for the given amount
     * Each concrete strategy implements this method differently
     * @param amount The amount to be paid
     * @return true if payment was successful, false otherwise
     */
    boolean pay(double amount);
    String getPaymentMethod();
}
