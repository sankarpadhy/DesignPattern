package behavioral.command;

import java.util.Stack;

/**
 * Invoker class in the Command Pattern
 * Represents a remote control that can execute commands
 * 
 * Key aspects demonstrated:
 * 1. Holds commands but knows nothing about their implementation
 * 2. Maintains command history for undo operations
 * 3. Can execute commands and track their history
 */
public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;
    private static final int NUM_COMMANDS = 7;

    /**
     * Creates a new remote control with a fixed number of slots
     * Initializes all slots with a no-operation command
     */
    public RemoteControl() {
        onCommands = new Command[NUM_COMMANDS];
        offCommands = new Command[NUM_COMMANDS];

        Command noCommand = new NoCommand();
        for (int i = 0; i < NUM_COMMANDS; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    /**
     * Sets a command pair for a specific slot
     * @param slot The slot number (0-6)
     * @param onCommand The command to execute when turned on
     * @param offCommand The command to execute when turned off
     */
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    /**
     * Executes the "on" command for a specific slot
     * @param slot The slot number (0-6)
     */
    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    /**
     * Executes the "off" command for a specific slot
     * @param slot The slot number (0-6)
     */
    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    /**
     * Undoes the last command that was executed
     * Uses the command history stack
     */
    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

    /**
     * Returns a string representation of all commands
     * @return A formatted string showing all command slots
     */
    @Override
    public String toString() {
        StringBuilder stringBuff = new StringBuilder();
        stringBuff.append("\n------ Remote Control -------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuff.append("[slot ").append(i).append("] ")
                     .append(onCommands[i].getClass().getSimpleName())
                     .append("    ")
                     .append(offCommands[i].getClass().getSimpleName())
                     .append("\n");
        }
        stringBuff.append("[undo] ").append(undoCommand.getClass().getSimpleName());
        return stringBuff.toString();
    }
}

/**
 * No-op command for initialization
 * Implements the null object pattern to avoid null checks
 */
class NoCommand implements Command {
    @Override
    public void execute() {}

    @Override
    public void undo() {}
}
