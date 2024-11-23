package behavioral.visitor;

/**
 * Concrete visitor that simulates exporting documents to different formats
 */
public class DocumentExportVisitor implements DocumentVisitor {
    private final String exportFormat;

    public DocumentExportVisitor(String exportFormat) {
        this.exportFormat = exportFormat;
    }

    @Override
    public void visit(TextDocument document) {
        System.out.println("Exporting text document from " + document.getFormat() + 
                         " to " + exportFormat);
    }

    @Override
    public void visit(ImageDocument document) {
        System.out.println("Exporting image " + document.getFilename() + 
                         " from " + document.getFormat() + 
                         " to " + exportFormat);
    }

    @Override
    public void visit(SpreadsheetDocument document) {
        System.out.println("Exporting " + document.getRows() + "x" + document.getColumns() + 
                         " spreadsheet from " + document.getFormat() + 
                         " to " + exportFormat);
    }

    @Override
    public void visit(DocumentCollection documents) {
        System.out.println("Starting document collection export to " + exportFormat);
    }
}
