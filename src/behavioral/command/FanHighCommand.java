package behavioral.command;

/**
 * Concrete Command for setting a fan to high speed
 * Demonstrates command pattern with state tracking for undo
 */
public class FanHighCommand implements Command {
    private Fan fan;

    /**
     * Creates a new command for setting fan to high speed
     * @param fan The fan to control
     */
    public FanHighCommand(Fan fan) {
        this.fan = fan;
    }

    /**
     * Executes the command by setting fan to high speed
     */
    @Override
    public void execute() {
        fan.high();
    }

    /**
     * Undoes the command by restoring previous speed
     */
    @Override
    public void undo() {
        switch (fan.getPrevSpeed()) {
            case 0:
                fan.off();
                break;
            case 1:
                fan.low();
                break;
            case 2:
                fan.medium();
                break;
            case 3:
                fan.high();
                break;
        }
    }
}
