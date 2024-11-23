package behavioral.command;

/**
 * Command interface
 * Defines the contract for all concrete command implementations
 * 
 * The Command pattern encapsulates a request as an object, thereby letting you:
 * 1. Parameterize clients with different requests
 * 2. Queue or log requests
 * 3. Support undoable operations
 */
public interface Command {
    void execute();
    void undo();
}
