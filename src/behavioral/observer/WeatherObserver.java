package behavioral.observer;

/**
 * Observer interface for the Weather Station example
 * Part of the Observer Pattern implementation where observers receive updates about weather changes
 * Observers implement this interface to receive temperature and humidity updates
 */
public interface WeatherObserver {
    /**
     * Called by the subject (WeatherStation) when weather conditions change
     * @param temperature The current temperature reading
     * @param humidity The current humidity reading
     */
    void update(float temperature, float humidity);
}
