package behavioral.state;

public class StateDemo {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        
        // Try to buy without inserting coin
        System.out.println("=== Attempting to buy without coin ===");
        machine.selectProduct("Coke");
        
        // Insert insufficient amount
        System.out.println("\n=== Inserting insufficient amount ===");
        machine.insertCoin(20);
        machine.selectProduct("Coke");
        
        // Insert more coins
        System.out.println("\n=== Inserting more coins ===");
        machine.insertCoin(10);
        machine.selectProduct("Coke");
        
        // Try new purchase
        System.out.println("\n=== Starting new purchase ===");
        machine.insertCoin(50);
        machine.selectProduct("Snacks");
        
        // Try to cancel during dispensing
        System.out.println("\n=== Attempting to cancel during dispensing ===");
        machine.cancel();
    }
}
