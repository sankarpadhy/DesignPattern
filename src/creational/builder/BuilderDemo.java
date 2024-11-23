package creational.builder;

/**
 * Demonstration of the Builder pattern
 * Shows how to create different computer configurations using the same builder
 */
public class BuilderDemo {
    public static void main(String[] args) {
        // Create a basic computer with only required parameters
        Computer basicComputer = new Computer.ComputerBuilder("Intel i3", "8GB")
                .build();
        System.out.println("Basic Computer Configuration:");
        System.out.println(basicComputer);
        
        System.out.println("\n" + "=".repeat(50) + "\n");

        // Create a gaming computer with all optional parameters
        Computer gamingComputer = new Computer.ComputerBuilder("Intel i9", "32GB")
                .setGPU(true)
                .setBluetoothEnabled(true)
                .setStorageInGB(2000)
                .setOperatingSystem("Windows 11")
                .build();
        System.out.println("Gaming Computer Configuration:");
        System.out.println(gamingComputer);
        
        System.out.println("\n" + "=".repeat(50) + "\n");

        // Create a work computer with some optional parameters
        Computer workComputer = new Computer.ComputerBuilder("Intel i7", "16GB")
                .setStorageInGB(512)
                .setOperatingSystem("Ubuntu 22.04")
                .build();
        System.out.println("Work Computer Configuration:");
        System.out.println(workComputer);
    }
}
