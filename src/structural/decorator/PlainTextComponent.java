package structural.decorator;

/**
 * Concrete component implementing the base functionality
 */
public class PlainTextComponent implements TextComponent {
    private String text;

    public PlainTextComponent(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}
