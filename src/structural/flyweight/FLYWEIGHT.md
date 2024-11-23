# Flyweight Pattern

## Intent
Use sharing to support large numbers of fine-grained objects efficiently. The Flyweight pattern minimizes memory usage by sharing as much data as possible with similar objects.

## Class Diagram
```
+------------------+       +--------------------+
|     Client       |       |  FlyweightFactory |
+------------------+       +--------------------+
|                  |       | - flyweights      |
|                  |       | + getFlyweight()  |
+------------------+       +--------------------+
         |                          |
         |                          |
         ▼                          ▼
+------------------+       +--------------------+
|   Context        |       |     Flyweight     |
+------------------+       +--------------------+
| - extrinsicState |       | + operation()     |
+------------------+       +--------------------+
                                    ▲
                                    |
                          +--------------------+
                          | ConcreteFlyweight |
                          +--------------------+
                          | - intrinsicState  |
                          | + operation()     |
                          +--------------------+
```

## Components
1. **Flyweight**: Declares interface through which flyweights receive and act on extrinsic state
2. **ConcreteFlyweight**: Implements Flyweight interface and stores intrinsic state
3. **FlyweightFactory**: Creates and manages flyweight objects
4. **Client**: Maintains reference to flyweights and computes/stores extrinsic state
5. **Context**: Contains the extrinsic state of the flyweight

## Implementation Details
- Divides object state into intrinsic and extrinsic parts
- Intrinsic state is stored in the flyweight and shared
- Extrinsic state is stored or computed by client
- Factory ensures proper sharing of flyweight objects

## When to Use
- Application uses large number of objects
- Storage costs are high due to object quantity
- Most object state can be made extrinsic
- Many groups of objects may be replaced by few shared objects
- Application doesn't depend on object identity

## Example Use Cases
1. Text Formatting in Word Processors
2. Game Character Rendering
3. Network Connection Pooling
4. Font Management Systems

## Code Example
```java
// Flyweight Interface
public interface TextFormat {
    void apply(String text, int position, Color color); // position and color are extrinsic
}

// Concrete Flyweight
public class FormattedText implements TextFormat {
    private final String fontFamily;    // intrinsic
    private final int fontSize;         // intrinsic
    private final boolean isBold;       // intrinsic
    
    @Override
    public void apply(String text, int position, Color color) {
        // Apply formatting using intrinsic and extrinsic state
    }
}

// Flyweight Factory
public class TextFormatFactory {
    private static Map<String, TextFormat> formats = new HashMap<>();
    
    public static TextFormat getFormat(String fontFamily, int fontSize, boolean isBold) {
        String key = fontFamily + "_" + fontSize + "_" + isBold;
        return formats.computeIfAbsent(key, 
            k -> new FormattedText(fontFamily, fontSize, isBold));
    }
}
```

## Advantages
1. Reduces memory usage when many similar objects exist
2. Improves data caching and performance
3. Reduces total number of objects created
4. Centralizes state management for object families

## Disadvantages
1. May introduce complexity in code
2. Requires careful separation of intrinsic/extrinsic state
3. Context management can be tricky
4. Factory management overhead
