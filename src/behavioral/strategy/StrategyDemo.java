package behavioral.strategy;

/**
 * Demonstration of the Strategy pattern using a shopping cart example
 * Shows how different payment strategies can be used interchangeably
 */
public class StrategyDemo {
    public static void main(String[] args) {
        // Create a shopping cart and add some items
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(100.0);
        cart.addItem(50.0);
        cart.addItem(75.5);

        System.out.println("Shopping Cart Demo with Strategy Pattern");
        System.out.println("=" .repeat(50));
        
        // Show initial cart contents
        cart.showCart();

        // Pay with Credit Card
        System.out.println("\nPaying with Credit Card:");
        cart.setPaymentStrategy(new CreditCardPayment(
            "1234-5678-9012-3456",
            "John Doe",
            "123",
            "12/25"
        ));
        cart.showCart();
        cart.checkout();

        // Create a new cart for PayPal payment
        System.out.println("\nNew order with PayPal payment:");
        ShoppingCart cart2 = new ShoppingCart();
        cart2.addItem(200.0);
        cart2.addItem(299.99);
        
        cart2.setPaymentStrategy(new PayPalPayment(
            "john.doe@example.com",
            "password123"
        ));
        cart2.showCart();
        cart2.checkout();
    }
}
