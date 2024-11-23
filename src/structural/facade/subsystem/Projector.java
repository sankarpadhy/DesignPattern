package structural.facade.subsystem;

/**
 * Subsystem component - Projector
 */
public class Projector {
    private static Projector instance = new Projector();

    private Projector() {}

    public static Projector getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("Projector is on");
    }

    public void off() {
        System.out.println("Projector is off");
    }

    public void wideScreenMode() {
        System.out.println("Projector in widescreen mode (16:9 aspect ratio)");
    }

    public void tvMode() {
        System.out.println("Projector in tv mode (4:3 aspect ratio)");
    }
}
