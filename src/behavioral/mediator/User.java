package behavioral.mediator;

/**
 * Abstract colleague interface for chat users
 */
public interface User {
    /**
     * Sends a message to other users through the mediator
     * @param message The message to send
     */
    void send(String message);

    /**
     * Receives a message from another user through the mediator
     * @param message The message received
     */
    void receive(String message);
}
