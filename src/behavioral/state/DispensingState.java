package behavioral.state;

public class DispensingState implements VendingMachineState {
    private String product;
    
    public DispensingState(String product) {
        this.product = product;
    }
    
    @Override
    public void insertCoin(VendingMachine machine, int amount) {
        System.out.println("Please wait, dispensing product");
    }
    
    @Override
    public void selectProduct(VendingMachine machine, String product) {
        System.out.println("Please wait, dispensing product");
    }
    
    @Override
    public void dispense(VendingMachine machine) {
        machine.dispenseProduct(product);
        if (machine.getCurrentAmount() > machine.getPrice(product)) {
            int change = machine.getCurrentAmount() - machine.getPrice(product);
            System.out.println("Returning change: " + change);
        }
        machine.setCurrentAmount(0);
        machine.setState(new NoCoinState());
    }
    
    @Override
    public void cancel(VendingMachine machine) {
        System.out.println("Cannot cancel while dispensing");
    }
}
