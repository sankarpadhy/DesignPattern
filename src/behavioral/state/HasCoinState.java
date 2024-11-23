package behavioral.state;

public class HasCoinState implements VendingMachineState {
    private String selectedProduct;
    
    @Override
    public void insertCoin(VendingMachine machine, int amount) {
        machine.setCurrentAmount(machine.getCurrentAmount() + amount);
        System.out.println("Current amount: " + machine.getCurrentAmount());
    }
    
    @Override
    public void selectProduct(VendingMachine machine, String product) {
        if (!machine.hasProduct(product)) {
            System.out.println("Product not available");
            return;
        }
        
        int price = machine.getPrice(product);
        if (machine.getCurrentAmount() < price) {
            System.out.println("Insufficient amount. Need " + (price - machine.getCurrentAmount()) + " more");
            return;
        }
        
        selectedProduct = product;
        machine.setState(new DispensingState(selectedProduct));
        machine.dispense();
    }
    
    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Please select a product first");
    }
    
    @Override
    public void cancel(VendingMachine machine) {
        System.out.println("Returning " + machine.getCurrentAmount());
        machine.setCurrentAmount(0);
        machine.setState(new NoCoinState());
    }
}
