package behavioral.visitor;

public class SpreadsheetDocument implements DocumentElement {
    private int rows;
    private int columns;
    private String format;

    public SpreadsheetDocument(int rows, int columns, String format) {
        this.rows = rows;
        this.columns = columns;
        this.format = format;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
