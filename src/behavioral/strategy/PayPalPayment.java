package behavioral.strategy;

/**
 * Concrete strategy for PayPal payments
 */
public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean pay(double amount) {
        // In real implementation, this would process the PayPal payment
        System.out.printf("Paid %.2f using PayPal account: %s\n", amount, email);
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "PayPal";
    }
}
