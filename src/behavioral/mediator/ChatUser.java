package behavioral.mediator;

/**
 * Concrete colleague class that represents a chat user
 */
public class ChatUser implements User {
    private ChatMediator mediator;
    private String name;

    public ChatUser(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    @Override
    public void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(name + " receives: " + message);
    }

    /**
     * Gets the name of this user
     * @return The user's name
     */
    public String getName() {
        return name;
    }
}
