package behavioral.observer;

// Java core imports
import java.util.ArrayList;
import java.util.List;

/**
 * Subject (Observable) class in the Observer Pattern
 * Maintains a list of observers and notifies them of weather condition changes
 * Demonstrates the core concept of the Observer Pattern where multiple observers
 * can subscribe to receive updates from a single subject
 */
public class WeatherStation {
    // List to store all observers
    private List<WeatherObserver> observers;
    
    // State variables
    private float temperature;
    private float humidity;

    /**
     * Initializes the weather station with empty observer list
     */
    public WeatherStation() {
        observers = new ArrayList<>();
    }

    /**
     * Adds a new observer to receive weather updates
     * @param observer The observer to be added
     */
    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the notification list
     * @param observer The observer to be removed
     */
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    /**
     * Updates weather measurements and notifies all observers
     * @param temperature New temperature reading
     * @param humidity New humidity reading
     */
    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }

    /**
     * Notifies all registered observers of the current weather conditions
     */
    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperature, humidity);
        }
    }

    /**
     * Gets the current temperature
     * @return Current temperature reading
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * Gets the current humidity
     * @return Current humidity reading
     */
    public float getHumidity() {
        return humidity;
    }
}
