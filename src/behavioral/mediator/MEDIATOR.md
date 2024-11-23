# Mediator Pattern

## Intent
Define an object that encapsulates how a set of objects interact. Mediator promotes loose coupling by keeping objects from referring to each other explicitly, and it lets you vary their interaction independently.

## Class Diagram
```
+---------------+       +-----------------+
|   Mediator    |       |   Colleague    |
+---------------+       +-----------------+
| + mediate()   |       | + action()     |
+---------------+       | + notify()      |
        ▲               +-----------------+
        |                      ▲
        |                      |
+---------------+     +-----------------+
|ConcreteMediator|     |ConcreteColleague|
+---------------+     +-----------------+
| + mediate()   |     | + action()     |
+---------------+     | + notify()      |
```

## Components
1. **Mediator**: Defines interface for communicating with Colleague objects
2. **ConcreteMediator**: Implements cooperative behavior by coordinating Colleague objects
3. **Colleague**: Defines interface for objects communicating through mediator
4. **ConcreteColleague**: Implements Colleague interface and communicates with other Colleagues through mediator

## Implementation Details
- Centralizes complex communications between related objects
- Colleagues communicate with mediator rather than with each other
- Promotes loose coupling between colleagues
- Easy to add new mediators and colleagues

## When to Use
- Set of objects communicate in well-defined but complex ways
- Object reuse is difficult because it refers to many other objects
- Behavior distributed between several classes should be customizable
- Dependencies between objects are unstructured and difficult to understand

## Example Use Cases
1. Chat Room Applications
2. Air Traffic Control
3. GUI Widget Communication
4. Event Handling Systems

## Code Example
```java
public interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

public abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public abstract void send(String message);
    public abstract void receive(String message);
}

public class ChatRoom implements ChatMediator {
    private List<User> users;

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            if (u != user) {
                u.receive(message);
            }
        }
    }
}
```

## Usage Example
```java
ChatMediator chatRoom = new ChatRoom();
User john = new ChatUser(chatRoom, "John");
User alice = new ChatUser(chatRoom, "Alice");

chatRoom.addUser(john);
chatRoom.addUser(alice);

john.send("Hi Alice!");
alice.send("Hello John!");
```

## Advantages
1. Reduces coupling between components
2. Centralizes control
3. Simplifies object protocols
4. Simplifies maintenance
5. Makes object interaction more reusable

## Disadvantages
1. Mediator can become overly complex
2. Can centralize control too much
3. Can create a single point of failure
4. May be harder to test individual components
