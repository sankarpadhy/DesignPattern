package behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

public class DocumentCollection implements DocumentElement {
    private List<DocumentElement> documents = new ArrayList<>();

    public void addDocument(DocumentElement document) {
        documents.add(document);
    }

    public List<DocumentElement> getDocuments() {
        return new ArrayList<>(documents);
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
        for (DocumentElement document : documents) {
            document.accept(visitor);
        }
    }
}
