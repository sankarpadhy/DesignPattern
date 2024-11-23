package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Factory
 * Creates and manages flyweight objects
 * 
 * Key aspects of the Flyweight Factory:
 * 1. Maintains a pool of existing flyweights
 * 2. Returns an existing flyweight if it exists
 * 3. Creates a new flyweight if it doesn't exist
 * 4. Ensures that flyweights are shared properly
 */
public class TextFormatFactory {
    private static final Map<String, TextFormat> formats = new HashMap<>();

    public static TextFormat getFormat(String fontFamily, int fontSize, boolean isBold, boolean isItalic) {
        // Create a key for the format combination
        String key = String.format("%s_%d_%b_%b", fontFamily, fontSize, isBold, isItalic);

        // Return existing format if it exists, create new one if it doesn't
        return formats.computeIfAbsent(key, k -> new FormattedText(fontFamily, fontSize, isBold, isItalic));
    }

    public static int getFormatCount() {
        return formats.size();
    }

    // Clear the cache - mainly for testing purposes
    public static void clearCache() {
        formats.clear();
    }
}
