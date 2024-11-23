package structural.decorator;

/**
 * Concrete decorator that adds italic formatting
 */
public class ItalicDecorator extends TextDecorator {
    public ItalicDecorator(TextComponent component) {
        super(component);
    }

    @Override
    public String getText() {
        return "<i>" + super.getText() + "</i>";
    }
}
