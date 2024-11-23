package behavioral.observer;

/**
 * Concrete Observer implementation for displaying weather information
 * Implements the WeatherObserver interface to receive and display weather updates
 * This class demonstrates how observers react to changes in the subject's state
 */
public class WeatherDisplay implements WeatherObserver {
    private String displayName;
    private float temperature;
    private float humidity;

    /**
     * Creates a new weather display with a specific name
     * @param displayName Name identifier for this display
     */
    public WeatherDisplay(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Receives and processes weather updates from the WeatherStation
     * Updates internal state and displays new weather conditions
     * @param temperature The current temperature reading
     * @param humidity The current humidity reading
     */
    @Override
    public void update(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    /**
     * Displays the current weather conditions
     * This method formats and outputs the weather data
     */
    private void display() {
        System.out.printf("%s: Temperature: %.1f°C, Humidity: %.1f%%\n",
                         displayName, temperature, humidity);
    }
}
