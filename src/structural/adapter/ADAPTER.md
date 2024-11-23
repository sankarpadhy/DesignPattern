# Adapter Pattern

## Intent
Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.

## Class Diagram
```
+---------------+    +-----------------+
|    Target     |    |    Adaptee     |
+---------------+    +-----------------+
| + request()   |    | + specificReq() |
+---------------+    +-----------------+
       ▲                     ▲
       |                     |
+---------------+           |
|    Adapter    |-----------+
+---------------+
| + request()   |
+---------------+
```

## Components
1. **Target**: Defines domain-specific interface client uses
2. **Adaptee**: Defines existing interface that needs adapting
3. **Adapter**: Adapts Adaptee to Target interface
4. **Client**: Collaborates with objects conforming to Target interface

## Implementation Details
- Can use inheritance (class adapter) or composition (object adapter)
- Translates calls to one interface into calls to another
- Can provide different interfaces to same object
- Handles interface incompatibility issues

## When to Use
- Want to use existing class but interface doesn't match
- Need to create reusable class working with unforeseen classes
- Need to adapt components that may change
- Need to make independently developed classes work together

## Example Use Cases
1. Media Player Formats
2. Payment Gateway Integration
3. Third-party Library Integration
4. Legacy System Integration

## Code Example
```java
// Target Interface
public interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee Interface
public interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

// Adapter
public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if(audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
```

## Advantages
1. Increases reusability of existing code
2. Provides flexibility in adding new adapters
3. Separates interface conversion from core business logic
4. Single Responsibility Principle compliance

## Disadvantages
1. Complexity increases with multiple adapters
2. All requests are forwarded, adding overhead
3. Sometimes confusing to debug
4. May need to adapt multiple classes
