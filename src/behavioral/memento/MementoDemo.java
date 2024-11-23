package behavioral.memento;

public class MementoDemo {
    public static void main(String[] args) {
        Editor editor = new Editor();
        
        // First edit
        editor.write("First line of text");
        System.out.println("Current content: " + editor.getContent());
        
        // Second edit
        editor.write("Second line of text");
        System.out.println("Current content: " + editor.getContent());
        
        // Third edit
        editor.write("Third line of text");
        System.out.println("Current content: " + editor.getContent());
        
        // Undo last edit
        System.out.println("\nUndo last edit");
        editor.undo();
        System.out.println("Current content: " + editor.getContent());
        
        // Undo again
        System.out.println("\nUndo again");
        editor.undo();
        System.out.println("Current content: " + editor.getContent());
    }
}
