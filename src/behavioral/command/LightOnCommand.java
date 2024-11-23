package behavioral.command;

/**
 * Concrete Command class that implements the Command interface
 * Encapsulates the action of turning a light on
 * 
 * Key aspects demonstrated:
 * 1. Holds reference to receiver (Light)
 * 2. Implements execute() to delegate to receiver
 * 3. Implements undo() for reversible operations
 */
public class LightOnCommand implements Command {
    private final Light light;
    
    /**
     * Creates a new command to turn on a specific light
     * @param light The light to be controlled
     */
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    /**
     * Executes the command by turning the light on
     * Delegates the actual operation to the Light receiver
     */
    @Override
    public void execute() {
        light.turnOn();
    }
    
    /**
     * Undoes the command by turning the light off
     * Provides reversibility of the command
     */
    @Override
    public void undo() {
        light.turnOff();
    }
}
