package behavioral.state;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private VendingMachineState currentState;
    private int currentAmount;
    private Map<String, Integer> inventory;
    private Map<String, Integer> prices;
    
    public VendingMachine() {
        currentState = new NoCoinState();
        currentAmount = 0;
        inventory = new HashMap<>();
        prices = new HashMap<>();
        initializeInventory();
    }
    
    private void initializeInventory() {
        inventory.put("Coke", 5);
        inventory.put("Pepsi", 5);
        inventory.put("Snacks", 5);
        
        prices.put("Coke", 25);
        prices.put("Pepsi", 25);
        prices.put("Snacks", 35);
    }
    
    public void setState(VendingMachineState state) {
        this.currentState = state;
    }
    
    public void insertCoin(int amount) {
        currentState.insertCoin(this, amount);
    }
    
    public void selectProduct(String product) {
        currentState.selectProduct(this, product);
    }
    
    public void dispense() {
        currentState.dispense(this);
    }
    
    public void cancel() {
        currentState.cancel(this);
    }
    
    public int getCurrentAmount() {
        return currentAmount;
    }
    
    public void setCurrentAmount(int amount) {
        this.currentAmount = amount;
    }
    
    public boolean hasProduct(String product) {
        return inventory.containsKey(product) && inventory.get(product) > 0;
    }
    
    public int getPrice(String product) {
        return prices.getOrDefault(product, 0);
    }
    
    public void dispenseProduct(String product) {
        if (hasProduct(product)) {
            inventory.put(product, inventory.get(product) - 1);
            System.out.println("Dispensing " + product);
        }
    }
    
    public VendingMachineState getCurrentState() {
        return currentState;
    }
}
