# Behavioral Design Patterns

This directory contains implementations of behavioral design patterns:

1. **Chain of Responsibility Pattern** (`chainofresponsibility/`)
   - Passes requests along a chain of handlers
   - Each handler decides to process or pass to the next in chain

2. **Command Pattern** (`command/`)
   - Encapsulates a request as an object
   - Allows parameterization of clients with requests
   - Supports undoable operations

3. **Interpreter Pattern** (`interpreter/`)
   - Defines a grammar for simple languages
   - Provides an interpreter to interpret sentences

4. **Iterator Pattern** (`iterator/`)
   - Provides a way to access elements sequentially
   - Hides the underlying representation

5. **Mediator Pattern** (`mediator/`)
   - Defines an object that encapsulates how objects interact
   - Promotes loose coupling between objects

6. **Memento Pattern** (`memento/`)
   - Captures and restores an object's internal state
   - Implements undo mechanisms

7. **Observer Pattern** (`observer/`)
   - Defines a one-to-many dependency between objects
   - When one object changes state, all dependents are notified

8. **State Pattern** (`state/`)
   - Allows an object to alter its behavior when its internal state changes
   - Object appears to change its class

9. **Strategy Pattern** (`strategy/`)
   - Defines a family of algorithms
   - Makes them interchangeable within that family

10. **Template Method Pattern** (`template/`)
    - Defines the skeleton of an algorithm in a method
    - Lets subclasses override specific steps

11. **Visitor Pattern** (`visitor/`)
    - Represents an operation to be performed on elements of an object structure
    - Lets you define a new operation without changing the classes of the elements
