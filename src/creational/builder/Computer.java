package creational.builder;

/**
 * Computer class that demonstrates the Builder pattern
 * 
 * The Builder pattern is useful when:
 * 1. You need to create complex objects with many optional parameters
 * 2. You want to prevent telescoping constructors
 * 3. You want to create different representations of the same object
 */
public class Computer {
    // Required parameters
    private String processor;
    private String ram;
    
    // Optional parameters
    private boolean hasGPU;
    private boolean hasBluetoothEnabled;
    private int storageInGB;
    private String operatingSystem;

    private Computer(ComputerBuilder builder) {
        // Required parameters
        this.processor = builder.processor;
        this.ram = builder.ram;
        
        // Optional parameters
        this.hasGPU = builder.hasGPU;
        this.hasBluetoothEnabled = builder.hasBluetoothEnabled;
        this.storageInGB = builder.storageInGB;
        this.operatingSystem = builder.operatingSystem;
    }

    // Getters for all parameters
    public String getProcessor() { return processor; }
    public String getRam() { return ram; }
    public boolean hasGPU() { return hasGPU; }
    public boolean hasBluetoothEnabled() { return hasBluetoothEnabled; }
    public int getStorageInGB() { return storageInGB; }
    public String getOperatingSystem() { return operatingSystem; }

    @Override
    public String toString() {
        return "Computer Specifications:\n" +
               "- Processor: " + processor + "\n" +
               "- RAM: " + ram + "\n" +
               "- GPU: " + (hasGPU ? "Yes" : "No") + "\n" +
               "- Bluetooth: " + (hasBluetoothEnabled ? "Enabled" : "Disabled") + "\n" +
               "- Storage: " + storageInGB + "GB\n" +
               "- OS: " + (operatingSystem != null ? operatingSystem : "Not Installed");
    }

    /**
     * Builder class for Computer
     * Implements a fluent interface for easy chaining of method calls
     */
    public static class ComputerBuilder {
        // Required parameters
        private final String processor;
        private final String ram;
        
        // Optional parameters - initialized to default values
        private boolean hasGPU = false;
        private boolean hasBluetoothEnabled = false;
        private int storageInGB = 256; // Default storage
        private String operatingSystem = null;

        public ComputerBuilder(String processor, String ram) {
            this.processor = processor;
            this.ram = ram;
        }

        // Methods to set optional parameters
        public ComputerBuilder setGPU(boolean hasGPU) {
            this.hasGPU = hasGPU;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean enabled) {
            this.hasBluetoothEnabled = enabled;
            return this;
        }

        public ComputerBuilder setStorageInGB(int storageInGB) {
            this.storageInGB = storageInGB;
            return this;
        }

        public ComputerBuilder setOperatingSystem(String os) {
            this.operatingSystem = os;
            return this;
        }

        // Build method to create the Computer instance
        public Computer build() {
            return new Computer(this);
        }
    }
}
