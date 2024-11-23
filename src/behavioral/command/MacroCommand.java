package behavioral.command;

/**
 * Concrete Command that executes multiple commands in sequence
 * Demonstrates the Composite pattern within Command pattern
 * 
 * This allows for macro operations that combine multiple commands
 * into a single command that can be executed or undone as a unit
 */
public class MacroCommand implements Command {
    private Command[] commands;

    /**
     * Creates a new macro command
     * @param commands Array of commands to execute in sequence
     */
    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    /**
     * Executes all commands in sequence
     */
    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    /**
     * Undoes all commands in reverse sequence
     */
    @Override
    public void undo() {
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
    }
}
