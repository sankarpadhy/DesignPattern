package structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite component representing a directory in the file system
 */
public class Directory extends FileSystemComponent {
    private List<FileSystemComponent> children;

    public Directory(String name, String path) {
        super(name, path);
        this.children = new ArrayList<>();
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    public List<FileSystemComponent> getChildren() {
        return new ArrayList<>(children);
    }

    @Override
    public void ls() {
        System.out.println(path + "/" + name + " (dir)");
        for (FileSystemComponent component : children) {
            component.ls();
        }
    }

    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }

    public void ls(int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + name + " (dir)");
        for (FileSystemComponent component : children) {
            if (component instanceof Directory) {
                ((Directory) component).ls(depth + 1);
            } else {
                System.out.println(indent + "  " + component.getName() + 
                                 (component instanceof File ? "." + ((File) component).getExtension() : "") +
                                 " [" + component.getSize() + " bytes]");
            }
        }
    }
}
