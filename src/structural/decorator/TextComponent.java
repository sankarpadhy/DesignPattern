package structural.decorator;

/**
 * Base component interface that defines the core operations
 * that can be decorated
 */
public interface TextComponent {
    String getText();
    void setText(String text);
}
