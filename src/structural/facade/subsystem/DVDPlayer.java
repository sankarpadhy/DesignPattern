package structural.facade.subsystem;

/**
 * Subsystem component - DVD Player
 */
public class DVDPlayer {
    private static DVDPlayer instance = new DVDPlayer();

    private DVDPlayer() {}

    public static DVDPlayer getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("DVD Player is on");
    }

    public void off() {
        System.out.println("DVD Player is off");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    public void stop() {
        System.out.println("DVD Player stopped");
    }

    public void eject() {
        System.out.println("DVD ejected");
    }
}
