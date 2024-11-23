package behavioral.observer;

/**
 * Demonstration of the Observer Pattern using a Weather Station example
 * Shows how multiple displays can observe and react to changes in weather conditions
 * 
 * Key aspects demonstrated:
 * 1. One-to-many dependency between objects
 * 2. Automatic notification of state changes
 * 3. Loose coupling between subject and observers
 */
public class ObserverDemo {
    /**
     * Main method to demonstrate the Observer pattern
     * Creates a weather station and multiple displays to show weather updates
     */
    public static void main(String[] args) {
        // Create the subject (WeatherStation)
        WeatherStation weatherStation = new WeatherStation();

        // Create observers (displays)
        WeatherDisplay mainDisplay = new WeatherDisplay("Main Display");
        WeatherDisplay mobileDisplay = new WeatherDisplay("Mobile App");
        WeatherDisplay webDisplay = new WeatherDisplay("Web Interface");

        // Register observers with the subject
        weatherStation.registerObserver(mainDisplay);
        weatherStation.registerObserver(mobileDisplay);
        weatherStation.registerObserver(webDisplay);

        System.out.println("Weather Station Started\n" + "=".repeat(50));
        
        // Simulate weather changes
        System.out.println("\nFirst weather update:");
        weatherStation.setMeasurements(25.2f, 65.0f, 1013.1f);

        System.out.println("\nRemoving Mobile App display...");
        weatherStation.removeObserver(mobileDisplay);

        System.out.println("\nSecond weather update (Mobile App removed):");
        weatherStation.setMeasurements(24.8f, 70.0f, 1014.2f);
    }
}
