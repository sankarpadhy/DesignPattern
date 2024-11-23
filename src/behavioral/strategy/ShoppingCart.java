package behavioral.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Context class for the Strategy Pattern
 * Maintains a list of items and their prices, and uses different payment strategies
 * 
 * Key aspects demonstrated:
 * 1. Context maintains reference to a strategy
 * 2. Delegates payment processing to the strategy
 * 3. Strategies can be switched at runtime
 */
public class ShoppingCart {
    private List<Double> prices;
    private PaymentStrategy paymentStrategy;

    /**
     * Initializes an empty shopping cart
     */
    public ShoppingCart() {
        this.prices = new ArrayList<>();
    }

    /**
     * Adds an item to the cart
     * @param price The price of the item
     */
    public void addItem(double price) {
        prices.add(price);
    }

    /**
     * Sets the payment strategy to be used
     * @param strategy The payment strategy to use
     */
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    /**
     * Calculates the total cost of items in the cart
     * @return The total cost
     */
    public double calculateTotal() {
        return prices.stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Processes the payment using the selected strategy
     * @return true if payment was successful
     */
    public boolean checkout() {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        double total = calculateTotal();
        return paymentStrategy.pay(total);
    }

    /**
     * Displays the contents of the shopping cart
     */
    public void showCart() {
        System.out.println("\nShopping Cart Contents:");
        for (int i = 0; i < prices.size(); i++) {
            System.out.printf("Item %d: $%.2f\n", i + 1, prices.get(i));
        }
        System.out.printf("Total: $%.2f\n", calculateTotal());
        if (paymentStrategy != null) {
            System.out.println("Selected Payment Method: " + paymentStrategy.getPaymentMethod());
        }
    }

    /**
     * Gets the current payment method name
     * @return The name of the current payment method
     */
    public String getCurrentPaymentMethod() {
        return paymentStrategy != null ? paymentStrategy.getPaymentMethod() : "Not set";
    }
}
