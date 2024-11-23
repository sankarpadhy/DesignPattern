package behavioral.command;

/**
 * Command interface for the Command Pattern
 * Defines the contract for all concrete commands
 * 
 * Key aspects of the Command Pattern demonstrated here:
 * 1. Encapsulates a request as an object
 * 2. Parameterizes clients with different requests
 * 3. Allows for queueing of requests
 * 4. Supports undoable operations
 */
public interface Command {
    /**
     * Executes the command
     * Each concrete command will implement its specific behavior
     */
    void execute();
    
    /**
     * Undoes the command (optional operation)
     * Restores the state before command execution
     */
    void undo();
}
