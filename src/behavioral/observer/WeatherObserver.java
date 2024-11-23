package behavioral.observer;

/**
 * Observer interface that defines the method to be called when the subject's state changes
 * This interface is implemented by all concrete observers
 */
public interface WeatherObserver {
    void update(float temperature, float humidity, float pressure);
}
