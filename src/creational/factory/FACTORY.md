# Factory Pattern

## Intent
Define an interface for creating objects but let subclasses decide which class to instantiate.

## Class Diagram
```
+---------------+       +-----------------+
|    Product    |       |    Factory     |
+---------------+       +-----------------+
| + operation() |       | + createProduct()|
+---------------+       +-----------------+
       ▲                         ▲
       |                         |
+---------------+       +-----------------+
|ConcreteProduct|       |ConcreteFactory |
+---------------+       +-----------------+
| + operation() |       | + createProduct()|
+---------------+       +-----------------+
```

## Components
1. **Product**: Defines the interface for objects created by factory
2. **ConcreteProduct**: Implements the Product interface
3. **Factory**: Declares the factory method
4. **ConcreteFactory**: Overrides factory method to return ConcreteProduct

## Implementation Details
- Factory method creates objects without exposing creation logic
- Uses inheritance and relies on subclasses
- Promotes loose coupling
- Follows "dependency inversion principle"

## When to Use
- Class can't anticipate object types it needs to create
- Class wants its subclasses to specify objects created
- Classes delegate responsibility to helper subclasses
- Need to work with various families of related objects

## Example Use Cases
1. Vehicle Manufacturing
2. Document Creation
3. UI Element Creation
4. Database Connection Management

## Code Example
```java
// Product interface
public interface Vehicle {
    void start();
    void stop();
}

// Concrete Products
public class Car implements Vehicle {
    @Override
    public void start() { /* implementation */ }
    @Override
    public void stop() { /* implementation */ }
}

// Factory
public class VehicleFactory {
    public Vehicle createVehicle(String type) {
        switch(type) {
            case "car": return new Car();
            case "bike": return new Bike();
            default: throw new IllegalArgumentException();
        }
    }
}
```

## Advantages
1. Loose coupling between creator and products
2. Single Responsibility Principle
3. Open/Closed Principle
4. Flexibility in adding new products

## Disadvantages
1. Can lead to many subclasses
2. May be overkill for simple object creation
3. Complexity increases with product variations
