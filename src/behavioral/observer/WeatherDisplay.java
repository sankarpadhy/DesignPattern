package behavioral.observer;

/**
 * Concrete observer implementation that displays weather information
 * This class demonstrates how observers receive and handle updates from the subject
 */
public class WeatherDisplay implements WeatherObserver {
    private String displayName;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherDisplay(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    /**
     * Displays the current weather conditions
     * This method is called automatically whenever updates are received
     */
    private void display() {
        System.out.println("\n" + displayName + " - Current Conditions:");
        System.out.printf("Temperature: %.1f°C\n", temperature);
        System.out.printf("Humidity: %.1f%%\n", humidity);
        System.out.printf("Pressure: %.1f hPa\n", pressure);
    }
}
