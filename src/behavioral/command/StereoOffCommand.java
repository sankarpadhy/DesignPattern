package behavioral.command;

/**
 * Concrete Command for turning off stereo
 */
public class StereoOffCommand implements Command {
    private Stereo stereo;
    private String previousSource;
    private int previousVolume;

    /**
     * Creates a new command for turning off the stereo
     * @param stereo The stereo to control
     */
    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    /**
     * Executes the command by turning off the stereo,
     * saving previous state for undo
     */
    @Override
    public void execute() {
        previousSource = stereo.getSource();
        previousVolume = stereo.getVolume();
        stereo.off();
    }

    /**
     * Undoes the command by restoring previous state
     */
    @Override
    public void undo() {
        stereo.on();
        if (previousSource.equals("CD")) {
            stereo.setCD();
        } else if (previousSource.equals("DVD")) {
            stereo.setDVD();
        } else if (previousSource.equals("Radio")) {
            stereo.setRadio();
        }
        stereo.setVolume(previousVolume);
    }
}
