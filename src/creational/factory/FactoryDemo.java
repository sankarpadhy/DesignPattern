package creational.factory;

public class FactoryDemo {
    public static void main(String[] args) {
        // Create a car using the factory
        Vehicle car = VehicleFactory.createVehicle("car");
        car.start();
        car.stop();

        System.out.println();

        // Create a bike using the factory
        Vehicle bike = VehicleFactory.createVehicle("bike");
        bike.start();
        bike.stop();

        // This will throw an IllegalArgumentException
        try {
            Vehicle unknown = VehicleFactory.createVehicle("unknown");
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}
