# Singleton Pattern

## Intent
Ensure a class has only one instance and provide a global point of access to it.

## Class Diagram
```
+----------------+
|   Singleton    |
+----------------+
| - instance     |
+----------------+
| - Singleton()  |
| + getInstance()|
+----------------+
```

## Components
1. **Private Static Instance**: Holds the single instance of the class
2. **Private Constructor**: Prevents direct instantiation
3. **Public Static Access Method**: Provides global access point

## Implementation Details
- Uses double-checked locking for thread safety
- Volatile keyword ensures proper initialization
- Protected against reflection and serialization
- Implements lazy initialization

## When to Use
- Need exactly one instance of a class
- Instance must be accessible globally
- Strict control over how and when instance is accessed

## Example Use Cases
1. Configuration Settings
2. Database Connections
3. Thread Pools
4. Logging Services

## Code Example
```java
public class Singleton {
    private static volatile Singleton instance;
    
    private Singleton() {
        if (instance != null) {
            throw new RuntimeException("Use getInstance()");
        }
    }
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

## Advantages
1. Controlled access to sole instance
2. Reduced namespace
3. Permits refinement of operations
4. Can control number of instances

## Disadvantages
1. Makes unit testing difficult
2. Can be overused
3. Violates Single Responsibility Principle
