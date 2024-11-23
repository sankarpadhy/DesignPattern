package behavioral.mediator;

/**
 * Demonstration of Mediator Pattern
 */
public class MediatorDemo {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();
        
        User john = new ChatUser(chatRoom, "John");
        User alice = new ChatUser(chatRoom, "Alice");
        User bob = new ChatUser(chatRoom, "Bob");
        
        chatRoom.addUser(john);
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        
        System.out.println("=== Chat Room Demo ===");
        john.send("Hi everyone!");
        System.out.println();
        
        alice.send("Hey John, how are you?");
        System.out.println();
        
        bob.send("Great to see you all!");
    }
}
