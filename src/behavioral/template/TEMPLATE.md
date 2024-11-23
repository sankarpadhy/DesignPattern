# Template Method Pattern

## Intent
Define the skeleton of an algorithm in a method, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.

## Class Diagram
```
+------------------+
|  AbstractClass   |
+------------------+
| + templateMethod()|
| # primitiveOp1() |
| # primitiveOp2() |
| # hook()         |
+------------------+
         ▲
         |
+------------------+
| ConcreteClass    |
+------------------+
| # primitiveOp1() |
| # primitiveOp2() |
| # hook()         |
+------------------+
```

## Components
1. **AbstractClass**: Defines abstract primitive operations and template method
2. **ConcreteClass**: Implements primitive operations
3. **Template Method**: Defines algorithm skeleton
4. **Primitive Operations**: Abstract operations implemented by subclasses
5. **Hook Methods**: Optional operations with default implementation

## Implementation Details
- Template method is typically marked as final
- Some operations can be implemented as hook methods
- Follows Hollywood Principle: "Don't call us, we'll call you"
- Can use abstract methods or hook methods

## When to Use
- Implement invariant parts of algorithm once
- Common behavior among subclasses should be factored and localized
- Control subclass extensions
- Avoid code duplication in related classes

## Example Use Cases
1. Data Mining Operations
2. Build Processes
3. Test Case Frameworks
4. Document Generation
5. Service Setup Operations

## Code Example
```java
public abstract class DataMiner {
    // Template method
    public final void mine(String path) {
        String rawData = extractData(path);
        List<String> processedData = parseData(rawData);
        List<Object> analyzedData = analyze(processedData);
        sendReport(analyzedData);
        cleanup();
    }
    
    // Abstract methods
    protected abstract String extractData(String path);
    protected abstract List<String> parseData(String rawData);
    
    // Hook method
    protected void cleanup() {
        System.out.println("Performing standard cleanup...");
    }
}
```

## Usage Example
```java
DataMiner pdfMiner = new PDFDataMiner();
pdfMiner.mine("sample.pdf");

DataMiner csvMiner = new CSVDataMiner();
csvMiner.mine("sample.csv");
```

## Advantages
1. Code reuse
2. Common behavior factored into a single class
3. Flexibility through hook methods
4. Inversion of control
5. Fine points of algorithms can be modified

## Disadvantages
1. Maintenance can be difficult
2. May violate Liskov Substitution Principle
3. Limited algorithm-level changes
4. Restricted to the structure defined in abstract class
5. Can lead to confusing hierarchy
