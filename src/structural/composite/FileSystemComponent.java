package structural.composite;

/**
 * Component interface defining common operations for files and directories
 */
public abstract class FileSystemComponent {
    protected String name;
    protected String path;

    public FileSystemComponent(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public abstract void ls();
    public abstract long getSize();
    
    public String getName() {
        return name;
    }
    
    public String getPath() {
        return path;
    }
}
