package behavioral.command;

/**
 * Concrete Command for turning on stereo with CD
 * Demonstrates command that executes multiple operations in sequence
 */
public class StereoOnWithCDCommand implements Command {
    private Stereo stereo;
    private int previousVolume;

    /**
     * Creates a new command for stereo CD playback
     * @param stereo The stereo to control
     */
    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    /**
     * Executes the command by turning on stereo,
     * setting CD source, and setting default volume
     */
    @Override
    public void execute() {
        previousVolume = stereo.getVolume();
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11); // Default volume for CD
    }

    /**
     * Undoes the command by restoring previous state
     */
    @Override
    public void undo() {
        stereo.setVolume(previousVolume);
        if (previousVolume == 0) {
            stereo.off();
        }
    }
}
