# Design Patterns Practice Project

This project contains implementations of core design patterns in Java, organized by categories:

## Creational Patterns
1. Singleton Pattern
2. Factory Pattern
3. Abstract Factory Pattern
4. Builder Pattern
5. Prototype Pattern

## Structural Patterns
1. Adapter Pattern
2. Bridge Pattern
3. Composite Pattern
4. Decorator Pattern
5. Facade Pattern

## Behavioral Patterns
1. Observer Pattern
2. Strategy Pattern
3. Command Pattern (Interactive Demo)
   - Visual animations of home automation
   - Sound effects and visual feedback
   - Mouse interaction with devices
   - Undo functionality
4. Template Pattern
5. Iterator Pattern

Each pattern is implemented with practical examples and comments explaining the pattern's purpose and usage.

## Interactive Demonstrations
The project includes interactive demonstrations for select patterns:

### Command Pattern Demo
- Control home automation devices (lights, fans, stereo)
- Visual animations with effects (ripples, sparkles, fades)
- Sound feedback for device interactions
- Mouse-based interaction
- Undo support for all operations
- Party mode with macro commands

## Building and Running
The project uses Apache Ant for building. Make sure you have Ant installed.

### Build Commands
```bash
# Clean and build the project
ant clean compile

# Run all tests
ant test

# Run the Command Pattern Demo
ant run-demo
```

## Project Structure
```
src/
├── creational/
│   ├── singleton/
│   ├── factory/
│   ├── abstractfactory/
│   ├── builder/
│   └── prototype/
├── structural/
│   ├── adapter/
│   ├── bridge/
│   ├── composite/
│   ├── decorator/
│   └── facade/
└── behavioral/
    ├── observer/
    ├── strategy/
    ├── command/
    │   ├── Command.java
    │   ├── Light.java
    │   ├── Fan.java
    │   ├── Stereo.java
    │   └── animation/
    │       ├── CommandPatternDemo.java
    │       ├── SoundManager.java
    │       └── VisualEffects.java
    ├── template/
    └── iterator/

test/
└── behavioral/
    └── command/
        └── CommandPatternTest.java
```

## Requirements
- Java 8 or higher
- Apache Ant (for building)
- JUnit (for testing)

## Contributing
Feel free to contribute by:
1. Adding new pattern implementations
2. Enhancing existing demonstrations
3. Adding more unit tests
4. Improving documentation

## License
This project is open source and available under the MIT License.
