package behavioral.visitor;

/**
 * Visitor interface declaring visit methods for each concrete element
 */
public interface DocumentVisitor {
    void visit(TextDocument document);
    void visit(ImageDocument document);
    void visit(SpreadsheetDocument document);
    void visit(DocumentCollection documents);
}
