package behavioral.command;

/**
 * Receiver class
 * Contains the actual business logic for operations
 */
public class Light {
    private String location;
    private boolean isOn;

    public Light(String location) {
        this.location = location;
        this.isOn = false;
    }

    public void turnOn() {
        isOn = true;
        System.out.println(location + " light is now ON");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(location + " light is now OFF");
    }

    public boolean isOn() {
        return isOn;
    }
}
