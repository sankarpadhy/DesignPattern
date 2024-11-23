package structural.decorator;

/**
 * Concrete decorator that adds underline formatting
 */
public class UnderlineDecorator extends TextDecorator {
    public UnderlineDecorator(TextComponent component) {
        super(component);
    }

    @Override
    public String getText() {
        return "<u>" + super.getText() + "</u>";
    }
}
