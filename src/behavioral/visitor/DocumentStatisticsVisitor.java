package behavioral.visitor;

/**
 * Concrete visitor that collects statistics about documents
 */
public class DocumentStatisticsVisitor implements DocumentVisitor {
    private int textDocuments = 0;
    private int imageDocuments = 0;
    private int spreadsheetDocuments = 0;
    private int totalDocuments = 0;

    @Override
    public void visit(TextDocument document) {
        textDocuments++;
        totalDocuments++;
        System.out.println("Visiting text document: " + document.getFormat());
    }

    @Override
    public void visit(ImageDocument document) {
        imageDocuments++;
        totalDocuments++;
        System.out.println("Visiting image document: " + document.getFilename());
    }

    @Override
    public void visit(SpreadsheetDocument document) {
        spreadsheetDocuments++;
        totalDocuments++;
        System.out.println("Visiting spreadsheet: " + document.getRows() + "x" + document.getColumns());
    }

    @Override
    public void visit(DocumentCollection documents) {
        System.out.println("Visiting document collection...");
    }

    public void printStatistics() {
        System.out.println("\nDocument Statistics:");
        System.out.println("Text Documents: " + textDocuments);
        System.out.println("Image Documents: " + imageDocuments);
        System.out.println("Spreadsheet Documents: " + spreadsheetDocuments);
        System.out.println("Total Documents: " + totalDocuments);
    }
}
