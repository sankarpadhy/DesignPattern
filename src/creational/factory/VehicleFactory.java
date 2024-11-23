package creational.factory;

/**
 * Factory Pattern Example
 * This class demonstrates the Factory pattern by creating different types of vehicles
 */
public class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        
        switch (type.toLowerCase()) {
            case "car":
                return new Car();
            case "bike":
                return new Bike();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
