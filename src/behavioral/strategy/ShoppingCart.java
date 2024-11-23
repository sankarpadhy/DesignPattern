package behavioral.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Context class that uses the payment strategy
 * This class demonstrates how strategies can be used interchangeably
 */
public class ShoppingCart {
    private List<Double> items;
    private PaymentStrategy paymentStrategy;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(double price) {
        items.add(price);
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public boolean checkout() {
        double total = calculateTotal();
        return paymentStrategy.pay(total);
    }

    private double calculateTotal() {
        return items.stream().mapToDouble(Double::doubleValue).sum();
    }

    public void showCart() {
        System.out.println("\nShopping Cart Contents:");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("Item %d: $%.2f\n", i + 1, items.get(i));
        }
        System.out.printf("Total: $%.2f\n", calculateTotal());
        if (paymentStrategy != null) {
            System.out.println("Selected Payment Method: " + paymentStrategy.getPaymentMethod());
        }
    }
}
