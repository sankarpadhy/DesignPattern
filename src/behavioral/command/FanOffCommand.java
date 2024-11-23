package behavioral.command;

/**
 * Concrete Command for turning off a fan
 */
public class FanOffCommand implements Command {
    private Fan fan;
    private int prevSpeed;

    /**
     * Creates a new command for turning off the fan
     * @param fan The fan to control
     */
    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    /**
     * Executes the command by turning off the fan,
     * saving previous speed for undo
     */
    @Override
    public void execute() {
        prevSpeed = fan.getSpeed();
        fan.off();
    }

    /**
     * Undoes the command by restoring previous speed
     */
    @Override
    public void undo() {
        switch (prevSpeed) {
            case Fan.HIGH:
                fan.high();
                break;
            case Fan.MEDIUM:
                fan.medium();
                break;
            case Fan.LOW:
                fan.low();
                break;
            default:
                fan.off();
        }
    }
}
