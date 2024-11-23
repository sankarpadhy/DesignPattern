package behavioral.visitor;

public class VisitorDemo {
    public static void main(String[] args) {
        // Create document collection
        DocumentCollection collection = new DocumentCollection();
        
        // Add various documents
        collection.addDocument(new TextDocument("Hello World", "txt"));
        collection.addDocument(new ImageDocument("photo.jpg", "1920x1080", "jpg"));
        collection.addDocument(new SpreadsheetDocument(100, 50, "xlsx"));
        collection.addDocument(new TextDocument("Report", "doc"));
        collection.addDocument(new ImageDocument("diagram.png", "800x600", "png"));
        
        // Create and use statistics visitor
        System.out.println("Collecting document statistics:");
        DocumentStatisticsVisitor statsVisitor = new DocumentStatisticsVisitor();
        collection.accept(statsVisitor);
        statsVisitor.printStatistics();
        
        // Create and use export visitor
        System.out.println("\nExporting documents to PDF:");
        DocumentExportVisitor exportVisitor = new DocumentExportVisitor("PDF");
        collection.accept(exportVisitor);
    }
}
