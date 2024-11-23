package behavioral.memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EditorTest {
    private Editor editor;
    
    @BeforeEach
    void setUp() {
        editor = new Editor();
    }
    
    @Test
    void testInitialState() {
        assertNull(editor.getContent());
    }
    
    @Test
    void testWriteContent() {
        editor.write("Hello World");
        assertEquals("Hello World", editor.getContent());
    }
    
    @Test
    void testSingleUndo() {
        editor.write("First");
        editor.write("Second");
        editor.undo();
        
        assertEquals("First", editor.getContent());
    }
    
    @Test
    void testMultipleUndo() {
        editor.write("First");
        editor.write("Second");
        editor.write("Third");
        
        editor.undo();
        assertEquals("Second", editor.getContent());
        
        editor.undo();
        assertEquals("First", editor.getContent());
        
        editor.undo();
        assertNull(editor.getContent());
    }
    
    @Test
    void testUndoEmptyHistory() {
        editor.undo(); // Should not throw exception
        assertNull(editor.getContent());
    }
    
    @Test
    void testWriteAfterUndo() {
        editor.write("First");
        editor.write("Second");
        editor.undo();
        editor.write("Third");
        
        assertEquals("Third", editor.getContent());
        editor.undo();
        assertEquals("First", editor.getContent());
    }
}
