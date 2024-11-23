package behavioral.visitor;

/**
 * Element interface that defines accept method for visitors
 */
public interface DocumentElement {
    void accept(DocumentVisitor visitor);
}
