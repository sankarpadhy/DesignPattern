package structural.facade.subsystem;

/**
 * Subsystem component - Theater Lights
 */
public class TheaterLights {
    private static TheaterLights instance = new TheaterLights();
    private int brightness;

    private TheaterLights() {
        this.brightness = 100;
    }

    public static TheaterLights getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("Theater Lights are on");
        setBrightness(100);
    }

    public void off() {
        System.out.println("Theater Lights are off");
        setBrightness(0);
    }

    public void dim(int level) {
        setBrightness(level);
        System.out.println("Theater Lights dimming to " + level + "%");
    }

    private void setBrightness(int brightness) {
        this.brightness = brightness;
    }
}
