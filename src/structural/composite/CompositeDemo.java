package structural.composite;

/**
 * Demonstration of Composite pattern with file system structure
 */
public class CompositeDemo {
    public static void main(String[] args) {
        Directory root = new Directory("root", "");
        
        // Create projects directory
        Directory projects = new Directory("projects", root.getPath() + "/root");
        File readme = new File("readme", projects.getPath(), 100, "md");
        Directory src = new Directory("src", projects.getPath());
        File app = new File("app", src.getPath(), 200, "java");
        File config = new File("config", src.getPath(), 150, "xml");
        
        // Create documents directory
        Directory documents = new Directory("documents", root.getPath() + "/root");
        File doc1 = new File("document1", documents.getPath(), 300, "doc");
        File doc2 = new File("document2", documents.getPath(), 400, "pdf");
        
        // Build the directory structure
        src.add(app);
        src.add(config);
        projects.add(readme);
        projects.add(src);
        documents.add(doc1);
        documents.add(doc2);
        root.add(projects);
        root.add(documents);
        
        // Display the directory structure
        System.out.println("Directory Structure:");
        root.ls(0);
        
        // Display total size
        System.out.println("\nTotal Size: " + root.getSize() + " bytes");
        
        // Display specific directory sizes
        System.out.println("Projects Size: " + projects.getSize() + " bytes");
        System.out.println("Documents Size: " + documents.getSize() + " bytes");
    }
}
