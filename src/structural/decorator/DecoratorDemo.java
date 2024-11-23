package structural.decorator;

/**
 * Demonstration of the Decorator pattern using text formatting
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        // Create a basic text component
        TextComponent text = new PlainTextComponent("Hello, World!");
        System.out.println("Plain text: " + text.getText());

        // Add bold formatting
        text = new BoldDecorator(text);
        System.out.println("Bold text: " + text.getText());

        // Add italic formatting to bold text
        text = new ItalicDecorator(text);
        System.out.println("Bold and italic text: " + text.getText());

        // Add underline formatting to bold and italic text
        text = new UnderlineDecorator(text);
        System.out.println("Bold, italic, and underlined text: " + text.getText());

        // Create a different combination
        TextComponent anotherText = new PlainTextComponent("Different text");
        anotherText = new UnderlineDecorator(
            new ItalicDecorator(anotherText)
        );
        System.out.println("Underlined and italic text: " + anotherText.getText());
    }
}
