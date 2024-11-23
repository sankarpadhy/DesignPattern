package structural.flyweight;

import java.awt.Color;

/**
 * Demonstration of the Flyweight pattern
 * Shows how to use shared text formats efficiently
 * 
 * The Flyweight pattern is useful when:
 * 1. An application needs a large number of objects
 * 2. Storage costs are high because of the quantity of objects
 * 3. Most object state can be made extrinsic
 * 4. Many groups of objects may be replaced by relatively few shared objects
 */
public class FlyweightDemo {
    public static void main(String[] args) {
        System.out.println("Text Formatting with Flyweight Pattern");
        System.out.println("=====================================");

        // Create some text formats
        TextFormat heading1 = TextFormatFactory.getFormat("Arial", 24, true, false);
        TextFormat heading2 = TextFormatFactory.getFormat("Arial", 20, true, false);
        TextFormat body = TextFormatFactory.getFormat("Times New Roman", 12, false, false);
        TextFormat emphasis = TextFormatFactory.getFormat("Times New Roman", 12, false, true);

        // Use the same format multiple times with different extrinsic state
        System.out.println("\nApplying heading format to different texts:");
        heading1.apply("Chapter 1: Introduction", 0, Color.BLACK);
        heading1.apply("Chapter 2: Getting Started", 100, Color.BLACK);

        System.out.println("\nApplying body format to different texts:");
        body.apply("This is the first paragraph.", 200, Color.DARK_GRAY);
        body.apply("This is another paragraph.", 300, Color.DARK_GRAY);

        System.out.println("\nApplying emphasis format:");
        emphasis.apply("important point", 250, Color.DARK_GRAY);

        // Show how many format objects were actually created
        System.out.println("\nTotal number of format objects created: " + TextFormatFactory.getFormatCount());

        // Demonstrate reuse of existing format
        System.out.println("\nCreating a format that already exists:");
        TextFormat heading1Duplicate = TextFormatFactory.getFormat("Arial", 24, true, false);
        System.out.println("Total number of format objects after duplicate request: " + 
                          TextFormatFactory.getFormatCount());

        // Memory usage comparison
        System.out.println("\nMemory Usage Analysis:");
        System.out.println("Without Flyweight: Would need 6 format objects");
        System.out.println("With Flyweight: Using only " + TextFormatFactory.getFormatCount() + " format objects");
    }
}
