package behavioral.iterator;

public class IteratorDemo {
    public static void main(String[] args) {
        String[] topics = {"Design Patterns", "Java", "Spring", "Microservices"};
        TopicList topicList = new TopicList(topics);
        
        Iterator<String> iterator = topicList.getIterator();
        
        System.out.println("=== First Iteration ===");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        
        System.out.println("\n=== After Reset ===");
        iterator.reset();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
