package structural.composite;

/**
 * Leaf component representing a file in the file system
 */
public class File extends FileSystemComponent {
    private long size;
    private String extension;

    public File(String name, String path, long size, String extension) {
        super(name, path);
        this.size = size;
        this.extension = extension;
    }

    @Override
    public void ls() {
        System.out.println(String.format("%s [%d bytes]", path + "/" + name + "." + extension, size));
    }

    @Override
    public long getSize() {
        return size;
    }

    public String getExtension() {
        return extension;
    }
}
