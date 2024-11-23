package structural.decorator;

/**
 * Concrete decorator that adds bold formatting
 */
public class BoldDecorator extends TextDecorator {
    public BoldDecorator(TextComponent component) {
        super(component);
    }

    @Override
    public String getText() {
        return "<b>" + super.getText() + "</b>";
    }
}
