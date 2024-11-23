package behavioral.observer;

/**
 * Demonstration of the Observer pattern using the Weather Station example
 * Shows how multiple observers can receive updates from a single subject
 */
public class ObserverDemo {
    public static void main(String[] args) {
        // Create the WeatherStation (Subject)
        WeatherStation weatherStation = new WeatherStation();

        // Create different displays (Observers)
        WeatherDisplay mainDisplay = new WeatherDisplay("Main Display");
        WeatherDisplay mobileDisplay = new WeatherDisplay("Mobile App");
        WeatherDisplay webDisplay = new WeatherDisplay("Web Interface");

        // Register observers with the weather station
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
