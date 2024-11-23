# Observer Pattern

## Intent
Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

## Class Diagram
```
+---------------+       +-----------------+
|   Subject     |       |    Observer    |
+---------------+       +-----------------+
| + attach()    |       | + update()     |
| + detach()    |       +-----------------+
| + notify()    |               ▲
+---------------+               |
        ▲                      |
        |                      |
+---------------+       +-----------------+
|ConcreteSubject|       |ConcreteObserver|
+---------------+       +-----------------+
| - state       |       | - state        |
| + getState()  |       | + update()     |
| + setState()  |       +-----------------+
+---------------+
```

## Components
1. **Subject**: Knows its observers and provides interface for attaching/detaching
2. **Observer**: Defines updating interface for objects that should be notified
3. **ConcreteSubject**: Stores state of interest and sends notifications
4. **ConcreteObserver**: Maintains reference to ConcreteSubject and implements Observer updating interface

## Implementation Details
- Supports broadcast communication
- Abstract coupling between Subject and Observer
- Observers can be added/removed at any time
- Provides basis for event handling systems

## When to Use
- When change to one object requires changing others
- When object should notify other objects without assumptions
- When an abstraction has two aspects, one dependent on the other
- When a change to one object requires changing unknown number of others

## Example Use Cases
1. Weather Monitoring Systems
2. Event Management Systems
3. Stock Market Data Distribution
4. User Interface Updates

## Code Example
```java
// Observer Interface
public interface Observer {
    void update(String message);
}

// Subject Interface
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// Concrete Subject
public class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String weatherUpdate;
    
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update(weatherUpdate);
        }
    }
    
    public void setWeatherUpdate(String update) {
        this.weatherUpdate = update;
        notifyObservers();
    }
}

// Concrete Observer
public class WeatherDisplay implements Observer {
    private String name;
    
    public WeatherDisplay(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String weatherUpdate) {
        System.out.println(name + " received update: " + weatherUpdate);
    }
}
```

## Advantages
1. Loose coupling between Subject and Observer
2. Support for broadcast communication
3. Easy to add new Observers
4. Subject doesn't need to know Observer details

## Disadvantages
1. Observers might be notified in random order
2. Memory leaks if observers aren't detached properly
3. Unexpected updates if complex dependencies exist
4. Performance impact with many observers
