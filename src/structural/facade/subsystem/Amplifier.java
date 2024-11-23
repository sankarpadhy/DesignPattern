package structural.facade.subsystem;

/**
 * Subsystem component - Amplifier
 */
public class Amplifier {
    private static Amplifier instance = new Amplifier();
    private int volume;

    private Amplifier() {
        this.volume = 0;
    }

    public static Amplifier getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("Amplifier is on");
    }

    public void off() {
        System.out.println("Amplifier is off");
    }

    public void setVolume(int level) {
        this.volume = level;
        System.out.println("Setting volume to " + level);
    }

    public void setDvd(DVDPlayer dvd) {
        System.out.println("Setting DVD player to amplifier");
    }

    public void setSurroundSound() {
        System.out.println("Surround sound enabled");
    }

    public void setTwoChannelAudio() {
        System.out.println("Two channel audio enabled");
    }
}
