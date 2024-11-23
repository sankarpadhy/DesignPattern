package behavioral.command;

import java.util.Stack;

/**
 * Invoker class
 * Holds and executes commands, maintains command history for undo operations
 * 
 * This class demonstrates key features of the Command pattern:
 * 1. Command queueing and history
 * 2. Undo functionality
 * 3. Decoupling of command execution from command implementation
 */
public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Stack<Command> commandHistory;
    private static final int SLOTS = 7;

    public RemoteControl() {
        onCommands = new Command[SLOTS];
        offCommands = new Command[SLOTS];
        commandHistory = new Stack<>();

        // Initialize with no-op commands
        Command noCommand = new NoCommand();
        for (int i = 0; i < SLOTS; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonPressed(int slot) {
        onCommands[slot].execute();
        commandHistory.push(onCommands[slot]);
    }

    public void offButtonPressed(int slot) {
        offCommands[slot].execute();
        commandHistory.push(offCommands[slot]);
    }

    public void undoButtonPressed() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
        } else {
            System.out.println("No commands to undo");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------ Remote Control -------\n");
        for (int i = 0; i < SLOTS; i++) {
            sb.append("[slot ").append(i).append("] ")
              .append(onCommands[i].getClass().getSimpleName()).append("    ")
              .append(offCommands[i].getClass().getSimpleName()).append("\n");
        }
        return sb.toString();
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
