package behavioral.mediator;

/**
 * Mediator interface defining the contract for communication
 */
public interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}
