package structural.decorator;

/**
 * Base decorator class that implements the component interface
 * and holds a reference to a component
 */
public abstract class TextDecorator implements TextComponent {
    protected TextComponent component;

    public TextDecorator(TextComponent component) {
        this.component = component;
    }

    @Override
    public String getText() {
        return component.getText();
    }

    @Override
    public void setText(String text) {
        component.setText(text);
    }
}
