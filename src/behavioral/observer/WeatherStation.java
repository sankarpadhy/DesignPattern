package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * WeatherStation class acts as the Subject (Observable)
 * 
 * Key aspects of the Observer pattern demonstrated here:
 * 1. Subject maintains a list of observers
 * 2. Provides methods to register and remove observers
 * 3. Notifies all observers when its state changes
 * 
 * This pattern is useful when:
 * - You need to maintain consistency between related objects
 * - You want to achieve loose coupling between objects
 * - You have a one-to-many relationship between objects
 */
public class WeatherStation {
    // List to store all observers
    private List<WeatherObserver> observers;
    
    // State variables
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    /**
     * Registers a new observer to receive updates
     * @param observer The observer to be added
     */
    public void registerObserver(WeatherObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes an observer from the notification list
     * @param observer The observer to be removed
     */
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers of the weather changes
     * This is called internally whenever the weather measurements change
     */
    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    /**
     * Updates the weather measurements and notifies all observers
     * This method simulates weather changes
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    // Getters for current weather conditions
    public float getTemperature() { return temperature; }
    public float getHumidity() { return humidity; }
    public float getPressure() { return pressure; }
}
