package behavioral.memento;

public class Editor {
    private String content;
    private History history;
    
    public Editor() {
        this.history = new History();
    }
    
    public void write(String text) {
        history.push(createState());
        this.content = text;
    }
    
    public void undo() {
        if (!history.isEmpty()) {
            EditorState state = history.pop();
            content = state.getContent();
        }
    }
    
    private EditorState createState() {
        return new EditorState(content);
    }
    
    public String getContent() {
        return content;
    }
}
