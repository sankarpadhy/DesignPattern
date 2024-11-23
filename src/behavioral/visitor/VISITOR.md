# Visitor Pattern

## Intent
Represent an operation to be performed on elements of an object structure. Visitor lets you define a new operation without changing the classes of the elements on which it operates.

## Class Diagram
```
+----------------+        +----------------+
|  Element       |        |  Visitor      |
+----------------+        +----------------+
| +accept()      |        | +visit(ElementA)|
+----------------+        | +visit(ElementB)|
        ▲                 +----------------+
        |                         ▲
        |                         |
+----------------+        +----------------+
|ConcreteElementA|        |ConcreteVisitor|
+----------------+        +----------------+
| +accept()      |        | +visit(ElementA)|
| +operationA()  |        | +visit(ElementB)|
+----------------+        +----------------+
```

## Components
1. **Visitor**: Interface declaring visit operations for each element type
2. **ConcreteVisitor**: Implements each operation declared by Visitor
3. **Element**: Interface defining accept operation
4. **ConcreteElement**: Implements accept operation
5. **ObjectStructure**: Can enumerate its elements

## Implementation Details
- Double dispatch mechanism
- Each element accepts a visitor
- Visitor contains a method for each element type
- Elements call appropriate visitor method

## When to Use
1. Object structure contains many classes with differing interfaces
2. Many distinct and unrelated operations need to be performed
3. Classes defining object structure rarely change
4. Operations need to change frequently

## Example Use Cases
1. Document Processing
2. AST Processing in Compilers
3. Shopping Cart Price Calculation
4. Report Generation
5. GUI Element Traversal

## Code Example
```java
public interface DocumentVisitor {
    void visit(TextDocument document);
    void visit(ImageDocument document);
    void visit(SpreadsheetDocument document);
}

public class DocumentStatisticsVisitor implements DocumentVisitor {
    @Override
    public void visit(TextDocument document) {
        // Process text document
    }
    
    @Override
    public void visit(ImageDocument document) {
        // Process image document
    }
}
```

## Usage Example
```java
DocumentCollection collection = new DocumentCollection();
collection.addDocument(new TextDocument("content", "txt"));
collection.addDocument(new ImageDocument("photo.jpg", "HD", "jpg"));

DocumentVisitor visitor = new DocumentStatisticsVisitor();
collection.accept(visitor);
```

## Advantages
1. Single Responsibility Principle
2. Open/Closed Principle
3. Gather related operations
4. Add new operations easily
5. Accumulate state while visiting

## Disadvantages
1. Breaking encapsulation
2. Complex for changing element hierarchy
3. Need to update all visitors when adding element
4. Dependency management
5. Complex state management

## Related Patterns
- Command Pattern
- Iterator Pattern
- Composite Pattern
