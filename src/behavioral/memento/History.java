package behavioral.memento;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<EditorState> states = new ArrayList<>();
    
    public void push(EditorState state) {
        states.add(state);
    }
    
    public EditorState pop() {
        if (states.isEmpty()) {
            return null;
        }
        int lastIndex = states.size() - 1;
        EditorState lastState = states.get(lastIndex);
        states.remove(lastIndex);
        return lastState;
    }
    
    public boolean isEmpty() {
        return states.isEmpty();
    }
}
