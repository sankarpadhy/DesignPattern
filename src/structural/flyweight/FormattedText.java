package structural.flyweight;

import java.awt.Color;

/**
 * Concrete Flyweight class
 * Contains intrinsic state that can be shared across multiple contexts
 */
public class FormattedText implements TextFormat {
    private final String fontFamily;
    private final int fontSize;
    private final boolean isBold;
    private final boolean isItalic;

    // Intrinsic state is passed in constructor
    public FormattedText(String fontFamily, int fontSize, boolean isBold, boolean isItalic) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.isBold = isBold;
        this.isItalic = isItalic;
    }

    // Extrinsic state is passed in method parameters
    @Override
    public void apply(String text, int position, Color color) {
        System.out.printf("Applying format at position %d: [%s, %dpt, %s%s, Color: %s] to text: '%s'\n",
                position,
                fontFamily,
                fontSize,
                isBold ? "bold, " : "",
                isItalic ? "italic, " : "",
                color.toString(),
                text);
    }

    // This method is used by the factory to check for existing flyweights
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormattedText that = (FormattedText) o;
        return fontSize == that.fontSize &&
                isBold == that.isBold &&
                isItalic == that.isItalic &&
                fontFamily.equals(that.fontFamily);
    }

    @Override
    public int hashCode() {
        int result = fontFamily.hashCode();
        result = 31 * result + fontSize;
        result = 31 * result + (isBold ? 1 : 0);
        result = 31 * result + (isItalic ? 1 : 0);
        return result;
    }
}
