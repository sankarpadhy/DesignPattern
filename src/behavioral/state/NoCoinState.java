package behavioral.state;

public class NoCoinState implements VendingMachineState {
    @Override
    public void insertCoin(VendingMachine machine, int amount) {
        machine.setCurrentAmount(amount);
        System.out.println("Coin inserted: " + amount);
        machine.setState(new HasCoinState());
    }
    
    @Override
    public void selectProduct(VendingMachine machine, String product) {
        System.out.println("Please insert coin first");
    }
    
    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Please insert coin first");
    }
    
    @Override
    public void cancel(VendingMachine machine) {
        System.out.println("No coin to return");
    }
}
