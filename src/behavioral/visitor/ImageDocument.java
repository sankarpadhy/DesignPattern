package behavioral.visitor;

public class ImageDocument implements DocumentElement {
    private String filename;
    private String resolution;
    private String format;

    public ImageDocument(String filename, String resolution, String format) {
        this.filename = filename;
        this.resolution = resolution;
        this.format = format;
    }

    public String getFilename() {
        return filename;
    }

    public String getResolution() {
        return resolution;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
