# State Pattern

## Intent
Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.

## Class Diagram
```
+---------------+       +-----------------+
|    Context    |       |      State     |
+---------------+       +-----------------+
| - state       |<>---->| + handle()     |
| + request()   |       +-----------------+
+---------------+              ▲
                              |
                   +----------+----------+
                   |                     |
        +------------------+  +------------------+
        |   ConcreteStateA |  |   ConcreteStateB |
        +------------------+  +------------------+
        | + handle()       |  | + handle()       |
        +------------------+  +------------------+
```

## Components
1. **Context**: Maintains an instance of ConcreteState subclass that defines current state
2. **State**: Defines interface for state-specific behavior
3. **ConcreteState**: Each subclass implements behavior associated with a state

## Implementation Details
- Context delegates state-specific behavior to current State object
- Context can pass itself as parameter to State object
- State transitions can be controlled by Context or State objects
- State objects can be shared among Context objects

## When to Use
- Object's behavior depends on its state
- Complex conditional statements control object behavior
- Transitions between states need to be explicit
- State-specific behavior should be independent

## Example Use Cases
1. Vending Machine States
2. TCP Connection States
3. Document Processing States
4. Game Character States

## Code Example
```java
public interface VendingMachineState {
    void insertCoin(VendingMachine machine, int amount);
    void selectProduct(VendingMachine machine, String product);
    void dispense(VendingMachine machine);
    void cancel(VendingMachine machine);
}

public class VendingMachine {
    private VendingMachineState currentState;
    private int currentAmount;

    public VendingMachine() {
        currentState = new NoCoinState();
        currentAmount = 0;
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public void insertCoin(int amount) {
        currentState.insertCoin(this, amount);
    }
}
```

## Usage Example
```java
VendingMachine machine = new VendingMachine();
machine.insertCoin(25);
machine.selectProduct("Coke");
```

## Advantages
1. Single Responsibility Principle
2. Open/Closed Principle
3. Eliminates complex conditionals
4. Improves cohesion
5. Makes state transitions explicit

## Disadvantages
1. Can increase number of classes
2. Can be overkill for simple state machines
3. Can make state transitions less obvious
4. Increased complexity for simple scenarios
