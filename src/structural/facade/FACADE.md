# Facade Pattern

## Intent
Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.

## Class Diagram
```
+---------------+       +-------------------+
|    Client     |       |      Facade      |
+---------------+       +-------------------+
|               |------>| + operation1()    |
|               |       | + operation2()    |
+---------------+       +-------------------+
                               |
                               |
                 +-------------+-------------+
                 |             |             |
        +---------------+---------------+---------------+
        |  SubsystemA   |  SubsystemB   |  SubsystemC   |
        +---------------+---------------+---------------+
        | + operation() | + operation() | + operation() |
        +---------------+---------------+---------------+
```

## Components
1. **Facade**: Provides unified interface to subsystem interfaces
2. **Subsystem classes**: Implement subsystem functionality
3. **Client**: Uses facade to access subsystem

## Implementation Details
- Facade doesn't encapsulate subsystem classes
- Client can still access subsystem directly
- Facade may provide multiple versions of complex operations
- Promotes weak coupling between subsystem and clients

## When to Use
- Need simple interface to complex subsystem
- Many dependencies exist between clients and abstraction implementation
- Want to layer your subsystems
- Need to decouple subsystem from clients and other subsystems

## Example Use Cases
1. Home Theater Systems
2. Computer System Startup
3. Online Shopping Cart
4. Banking Operations

## Code Example
```java
public class HomeTheaterFacade {
    private Amplifier amp;
    private DVDPlayer dvd;
    private Projector projector;
    private TheaterLights lights;

    public HomeTheaterFacade() {
        this.amp = Amplifier.getInstance();
        this.dvd = DVDPlayer.getInstance();
        this.projector = Projector.getInstance();
        this.lights = TheaterLights.getInstance();
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(10);
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setDvd(dvd);
        amp.setSurroundSound();
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down movie theater...");
        lights.on();
        dvd.stop();
        dvd.eject();
        dvd.off();
        amp.off();
        projector.off();
    }
}
```

## Usage Example
```java
HomeTheaterFacade homeTheater = new HomeTheaterFacade();
homeTheater.watchMovie("Inception");
// ... watch movie ...
homeTheater.endMovie();
```

## Advantages
1. Isolates clients from subsystem components
2. Promotes weak coupling
3. Reduces compilation dependencies
4. Provides entry point to each subsystem level
5. Layers can evolve independently

## Disadvantages
1. Facade can become a god object coupled to all classes
2. Can introduce new problems when subsystem changes
3. May limit client access to subsystem features
4. Can violate Single Responsibility Principle if not careful
