package behavioral.visitor;

public class TextDocument implements DocumentElement {
    private String content;
    private String format;

    public TextDocument(String content, String format) {
        this.content = content;
        this.format = format;
    }

    public String getContent() {
        return content;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
