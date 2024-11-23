# Chain of Responsibility Pattern

## Intent
Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request. Chain the receiving objects and pass the request along the chain until an object handles it.

## Class Diagram
```
+---------------+
|    Handler    |
+---------------+
| + successor   |
| + handleReq() |
+---------------+
       ▲
       |
+---------------+
|ConcreteHandler|
+---------------+
| + handleReq() |
+---------------+
```

## Components
1. **Handler**: Defines interface for handling requests and optional successor link
2. **ConcreteHandler**: Handles requests it is responsible for, can access successor
3. **Client**: Initiates requests to any handler in the chain

## Implementation Details
- Each handler decides either to process the request or pass it to the next handler
- Promotes loose coupling
- Handlers can be added or removed dynamically
- Request can be handled by any handler in the chain

## When to Use
- More than one object may handle a request
- Set of handlers isn't known a priori
- Handler should be determined automatically
- Request should be issued to several objects without specifying receiver explicitly
- Set of objects that can handle requests should be specified dynamically

## Example Use Cases
1. Logging Frameworks
2. Event Handling Systems
3. Exception Handling
4. Request Processing Pipelines

## Code Example
```java
public abstract class Logger {
    protected LogLevel level;
    protected Logger nextLogger;

    public Logger setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
        return nextLogger;
    }

    public void log(LogLevel messageLevel, String message) {
        if (messageLevel.getLevel() >= level.getLevel()) {
            writeMessage(message);
        }
        if (nextLogger != null) {
            nextLogger.log(messageLevel, message);
        }
    }

    protected abstract void writeMessage(String message);
}

public class ConsoleLogger extends Logger {
    @Override
    protected void writeMessage(String message) {
        System.out.println("Console: " + message);
    }
}

public class FileLogger extends Logger {
    @Override
    protected void writeMessage(String message) {
        System.out.println("File: " + message);
    }
}
```

## Usage Example
```java
Logger consoleLogger = new ConsoleLogger(LogLevel.INFO);
Logger fileLogger = new FileLogger(LogLevel.DEBUG);
Logger errorLogger = new ErrorLogger(LogLevel.ERROR);

consoleLogger.setNext(fileLogger).setNext(errorLogger);
consoleLogger.log(LogLevel.ERROR, "System crash!");
```

## Advantages
1. Reduced coupling between sender and receiver
2. Added flexibility in assigning responsibilities
3. Receipt isn't guaranteed
4. Single Responsibility Principle
5. Open/Closed Principle

## Disadvantages
1. Request might go unhandled
2. Debugging can be challenging
3. Runtime configurations might be complex
4. No guarantee of request being handled
