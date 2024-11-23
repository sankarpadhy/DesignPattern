package behavioral.strategy;

/**
 * Concrete strategy implementing credit card payment
 * Demonstrates how a specific payment algorithm is encapsulated
 */
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cvv;
    private String expiryDate;

    /**
     * Constructs a credit card payment strategy with card details
     * @param cardNumber The credit card number
     * @param cvv The card's CVV security code
     * @param expiryDate The card's expiry date
     */
    public CreditCardPayment(String cardNumber, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    /**
     * Processes a credit card payment
     * In a real implementation, this would connect to a payment gateway
     * @param amount The amount to charge to the credit card
     * @return true if payment was successful
     */
    @Override
    public boolean pay(double amount) {
        System.out.println("Paying " + amount + " using Credit Card");
        System.out.println("Card Number: " + maskCardNumber());
        return true;
    }

    /**
     * Gets the name of this payment method
     * @return The string "Credit Card"
     */
    @Override
    public String getPaymentMethod() {
        return "Credit Card";
    }

    /**
     * Masks the credit card number for security
     * Shows only the last 4 digits
     * @return The masked card number
     */
    private String maskCardNumber() {
        return "xxxx-xxxx-xxxx-" + cardNumber.substring(cardNumber.length() - 4);
    }
}
