# Command Pattern

## Intent
Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

## Class Diagram
```
+---------------+       +-----------------+
|    Client     |       |     Invoker    |
+---------------+       +-----------------+
|               |       | - command      |
|               |       | + execute()    |
+---------------+       +-----------------+
        |                      ▲
        |                      |
        ▼                      |
+---------------+       +-----------------+
|   Receiver    |       |    Command     |
+---------------+       +-----------------+
| + action()    |       | + execute()    |
+---------------+       | + undo()       |
        ▲              +-----------------+
        |                     ▲
        |                     |
        |              +-----------------+
        +------------->|ConcreteCommand |
                      +-----------------+
                      | + execute()     |
                      | + undo()        |
                      +-----------------+
```

## Components
1. **Command**: Declares interface for executing operation
2. **ConcreteCommand**: Defines binding between Receiver and action
3. **Client**: Creates ConcreteCommand and sets its Receiver
4. **Invoker**: Asks command to carry out request
5. **Receiver**: Knows how to perform the operations

## Implementation Details
- Decouples object that invokes operation from object that performs it
- Commands can be manipulated and extended like any other object
- Commands can be assembled into composite commands
- Easy to add new commands without changing existing code

## When to Use
- Need to parameterize objects by an action to perform
- Need to specify, queue, and execute requests at different times
- Need to support undo operations
- Need to support logging changes that can be reapplied after a crash
- Need to structure a system around high-level operations

## Example Use Cases
1. Smart Home Automation
2. Menu Systems
3. Command Line Interfaces
4. Macro Recording Systems

## Code Example
```java
// Command Interface
public interface Command {
    void execute();
    void undo();
}

// Receiver
public class Light {
    public void turnOn() {
        System.out.println("Light is on");
    }
    
    public void turnOff() {
        System.out.println("Light is off");
    }
}

// Concrete Command
public class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOn();
    }
    
    @Override
    public void undo() {
        light.turnOff();
    }
}

// Invoker
public class RemoteControl {
    private Command command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void pressButton() {
        command.execute();
    }
    
    public void pressUndo() {
        command.undo();
    }
}
```

## Advantages
1. Decouples classes that invoke operations from classes that perform them
2. Easy to add new commands
3. Supports undo/redo operations
4. Supports command logging and transaction-like functionality
5. Supports composite commands

## Disadvantages
1. Increase in number of classes for each individual command
2. Memory overhead if commands are long-lived
3. Complex command hierarchies can be difficult to maintain
